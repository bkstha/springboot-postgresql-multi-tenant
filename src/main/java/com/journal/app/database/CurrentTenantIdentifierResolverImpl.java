package com.journal.app.database;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;


/**
 * Created by admin on 3/15/2017.
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
//        String userSchema = App.getUserSchema();
        String userSchema = "schema1";
        if (userSchema == null) {
//            Logger.info("resolveCurrentTenantIdentifier to public (user=" + userSchema + ")");
            return "public";
        } else {
//            Logger.info("resolveCurrentTenantIdentifier to " + userSchema + "");
            return userSchema;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
