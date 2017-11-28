package com.journal.app.database;

import com.journal.app.controllers.AppController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by admin on 3/15/2017.
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {


    private static final Logger logger = LogManager.getLogger(AppController.class);

    @Override
    public String resolveCurrentTenantIdentifier() {
        String userSchema = "public";
        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            userSchema = httpServletRequest.getHeader("schema");
            System.out.println("userSchema current tenant identifier resolver: " + userSchema);
            if (userSchema == null) {
                logger.info("resolveCurrentTenantIdentifier to public (user=" + userSchema + ")");
                return "public";
            } else {
                logger.info("resolveCurrentTenantIdentifier to " + userSchema + "");
                return userSchema;
            }
        } catch (Exception e) {
            logger.error("cannot resolve current tenant identifier. Initializing Default tenant(user=" + userSchema + ")");
            return "public";
        }
    }


    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
