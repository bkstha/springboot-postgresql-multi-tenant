package com.journal.app.database;


public class MultiTenantSelection {

    public static String getTenantSchema() {
        try {
//            String schema= session("userSchema");
            String schema= "public";
            return ((schema!=null) ? schema : MultiTenantConfiguration.BASE_SCHEMA);
        } catch (Exception e) {
//            Logger.error("no schema: retrieving public schema");
            return MultiTenantConfiguration.BASE_SCHEMA;
        }

    }
}
