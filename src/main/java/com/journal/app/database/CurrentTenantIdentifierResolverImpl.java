package com.journal.app.database;

import com.journal.app.controllers.AppController;
import com.journal.app.security.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String PUBLIC = "public";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userToken = httpServletRequest.getHeader("token");
            String userSchema = jwtTokenUtil.getSchemaFromToken(userToken);

            if (userToken == null || userToken == null) {
                logger.info("resolveCurrentTenantIdentifier to public (user=" + PUBLIC + ")");
                return PUBLIC;
            } else {
//                JwtTokenUtil token = new JwtTokenUtil();
//                token.getAllClaimsFromToken

//                userSchema = JwtTokenUtil.getSchema(new JwtTokenUtil().getAllClaimsFromToken(userToken));

                logger.info("resolveCurrentTenantIdentifier to " + userSchema + "");
                return userSchema;
            }
        } catch (Exception e) {
            logger.error("cannot resolve current tenant identifier. Initializing Default tenant(user=" + PUBLIC + ")");
            return PUBLIC;
        }
    }


    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
