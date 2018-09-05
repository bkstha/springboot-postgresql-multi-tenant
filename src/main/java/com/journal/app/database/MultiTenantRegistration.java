package com.journal.app.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumSet;

@Component
public class MultiTenantRegistration implements ServiceRegistryAwareService, Integrator {

    private static MultiTenantConnectionProviderImpl multiTenantConnectionProvider;
    private static ServiceRegistryImplementor serviceRegistry;

    private static SessionFactoryServiceRegistry sessionFactoryServiceRegistry;
    private static SessionFactoryImplementor sessionFactoryImplementor;
    private static Metadata metadata;
    private static boolean initialised;

    private static final Logger logger = LogManager.getLogger(MultiTenantRegistration.class);

    public MultiTenantRegistration() {
    }

    public MultiTenantRegistration(MultiTenantConnectionProviderImpl multiTenantConnectionProvider) {
        this.multiTenantConnectionProvider = multiTenantConnectionProvider;
    }

    public boolean isReady() {
        return serviceRegistry != null;
    }

    private void init() {
        if (isReady() && !initialised) {
            logger.info("initializing default database");
            initialised = true;
            createPublicSchema();
        }
    }

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        this.metadata = metadata;
        this.sessionFactoryImplementor = sessionFactory;
        this.sessionFactoryServiceRegistry = serviceRegistry;
        init();
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // do nothing
    }

    public static void createPublicSchema() {
        logger.info("Creating public schema");
        createSchema("support@support.com", MultiTenantConfiguration.BASE_SCHEMA, false);
    }

    public static void createUserSchema(String email, String schema) {
        createSchema(email, schema, true);
    }

    private static void createSchema(String userMail, String schema, boolean errorIfSchemaExists) {
        logger.info("createSchema for " + userMail + " with schema " + schema);
        if (schema == null) {
            throw new NullPointerException("tenantIdentifier");
        }
        try (Connection connection = multiTenantConnectionProvider.getConnection(schema);
             Statement statement = connection.createStatement()) {
            try {
                statement.execute("CREATE SCHEMA " + schema + "");
                logger.info("Schema " + schema + " created...");
            } catch (SQLException e) {
                String message = "Schema " + schema + " for user " + userMail + " already exists";
                if (errorIfSchemaExists) {
                    logger.error("ERROR: " + message);
                    throw new RuntimeException(message);
                } else {
                    logger.info(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Failed to create schema " + schema + " for user " + userMail;
            logger.error(message);
            throw new RuntimeException(message);
        }
        try (Connection connection = multiTenantConnectionProvider.getConnection(schema)) {
            logger.info("creating tables for schema " + schema);
            if (!connection.getSchema().equals(schema)) {
                logger.info("invalid schema detected", schema, connection.getSchema());
                throw new RuntimeException("Invalid schema detected");
            }

            MetadataSources metadataSources = getMetadataSources(connection);
            SchemaExport schemaExport = new SchemaExport();
            if (schema.equals(connection.getSchema())) {
                schemaExport.execute(EnumSet.of(TargetType.DATABASE), SchemaExport.Action.CREATE, metadataSources.buildMetadata());
            } else {
                logger.warn("Invalid connection session...");
            }
            logger.info("Tables for Schema " + schema + " created");
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Failed to create tables in schema " + schema + " for user " + userMail;
            logger.error(message);
            throw new RuntimeException(message);
        }
        try (Connection connection = multiTenantConnectionProvider.getConnection(schema);
             Statement statement = connection.createStatement()) {
            logger.info("deleting tables for schema: " + schema);
            boolean schemaExists;
            try (ResultSet rs = statement.executeQuery("SELECT EXISTS(SELECT 1 FROM pg_namespace WHERE nspname = '" + schema + "') as schema_exists")) {
                rs.next();
                schemaExists = rs.getBoolean("schema_exists");
            }
            if (!MultiTenantConfiguration.isPublicSchema(schema) && schema.equals(connection.getSchema()) && !connection.getSchema().equals("public")) {
                if (schemaExists) {
                    for (String sharedTable : MultiTenantConfiguration.getSharedTablesList()) {
                        try {
                            statement.execute("DROP TABLE " + schema + "." + sharedTable + " CASCADE ");
                        } catch (SQLException e) {
                            logger.error("Unable to drop table " + sharedTable, e);
                        }
                    }
                } else {
                    logger.warn("Schema " + schema + " does not exists...");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Failed to create tables in schema " + schema + " for user " + userMail;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    private static MetadataSources getMetadataSources(Connection connection) {
        try {
            MetadataSources metadataSources = new MetadataSources(
                    new StandardServiceRegistryBuilder()
                            .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
                            .applySetting("javax.persistence.schema-generation-connection", connection)
                            .build());

            for (PersistentClass annotatedClass : metadata.getEntityBindings()) {
                metadataSources.addAnnotatedClass(annotatedClass.getMappedClass());
            }
            return metadataSources;
        } catch (Exception e) {
            logger.error("error: ", e);
            return null;
        }
    }

    public static Connection getConnection(String tenantIdentifier) throws SQLException {
        return multiTenantConnectionProvider.getConnection(tenantIdentifier);
    }

    public static Session getSession(Connection connection) {
        return sessionFactoryImplementor.withOptions().connection(connection).openSession();
    }
}
