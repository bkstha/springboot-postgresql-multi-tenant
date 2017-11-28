package com.journal.app.database;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MultiTenantSelection {

    public static String getTenantSchema() {
        try {
//            System.out.println("httpServletRequest");
//            System.out.println(requestHeader.getHeader("schema"));
//            String schema= session("userSchema");
            String schema = "public";
            return ((schema != null) ? schema : MultiTenantConfiguration.BASE_SCHEMA);
        } catch (Exception e) {
//            Logger.error("no schema: retrieving public schema");
            return MultiTenantConfiguration.BASE_SCHEMA;
        }

    }
}
