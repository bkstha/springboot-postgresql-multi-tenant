package com.journal.app.database;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

    @Autowired
    private HikariDataSource dataSource;


    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistryImplementor) {
        System.out.println("injecting services");
        new MultiTenantRegistration(this).injectServices(serviceRegistryImplementor);
    }


    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        releaseConnection(MultiTenantConfiguration.BASE_SCHEMA, connection);
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
//        Logger.info("getConnection for " + tenantIdentifier);
        System.out.println("getConnection for " + tenantIdentifier);
        Connection connection = dataSource.getConnection();
//        System.out.println("schema: "+connection.getSchema());
        try (Statement statement = connection.createStatement()) {
            String publicSchema = MultiTenantConfiguration.BASE_SCHEMA;
            if (publicSchema.equals(tenantIdentifier)) {
                statement.execute(MultiTenantConfiguration.SET_SEARCH_PATH_TO + publicSchema);
            } else {
                statement.execute(MultiTenantConfiguration.SET_SEARCH_PATH_TO + tenantIdentifier + "," + publicSchema);
            }
        } catch (SQLException e) {
            System.out.println("Tenant " + tenantIdentifier + " has not been provisioned in database " + e.getMessage());
//            Logger.error("Tenant " + tenantIdentifier + " has not been provisioned in database " + jndiName, e);
        }
//        System.out.println("schema: "+connection.getSchema());
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        System.out.println("releaseConnection for " + tenantIdentifier);
//        Logger.info("releaseConnection for " + tenantIdentifier);
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }


    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        } else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }


}
