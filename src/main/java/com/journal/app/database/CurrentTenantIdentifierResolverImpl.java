package com.journal.app.database;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by admin on 3/15/2017.
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    HttpServletRequest

    @Override
    public String resolveCurrentTenantIdentifier() {
        HttpServletRequest request =
//        String userSchema = App.getUserSchema();
        String userSchema = "public";
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
