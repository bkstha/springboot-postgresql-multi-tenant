package com.journal.app.database;

import java.util.Arrays;
import java.util.List;

/**
 * Created by akalico on 30/03/15.
 */
public class MultiTenantConfiguration {
    public static final String BASE_SCHEMA = "public";
    public static final String SET_SEARCH_PATH_TO = "SET SEARCH_PATH TO ";

    private static final String SHARED_TABLES[] = { "users", "companies", "subscriptions", "countries", "user_company", "authorities", "user_authorities"};
    private static List<String> SHARED_TABLES_LIST = Arrays.asList(SHARED_TABLES);

    public static List<String> getSharedTablesList() {
        return SHARED_TABLES_LIST;
    }

    public static boolean isSharedTable(String tableName) {
        return SHARED_TABLES_LIST.contains(tableName);
    }

    public static boolean isPublicSchema(String s) {
        return BASE_SCHEMA.equals(s);
    }

}
