package com.journal.app.models.DTO;

public class CompanyDTO {
    private String name;
    private String schema;

    public CompanyDTO(String name, String schema) {
        this.name = name;
        this.schema = schema;
    }

    //getter-setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
