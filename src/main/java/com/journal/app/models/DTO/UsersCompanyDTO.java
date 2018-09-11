package com.journal.app.models.DTO;

import java.util.List;

//DTO for stating company for related user
public class UsersCompanyDTO {

    private Long ucId;
    private Long companyId;
    private String name;
    private String schema;
    private Long partiesId;
    private Boolean status;
    private List<String> authorityList;

    public Long getUcId() {
        return ucId;
    }

    public void setUcId(Long ucId) {
        this.ucId = ucId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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

    public Long getPartiesId() {
        return partiesId;
    }

    public void setPartiesId(Long partiesId) {
        this.partiesId = partiesId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }
}
