package com.journal.app.models.DTO.auth;

import java.util.List;

public class UserCompanyResponseDTO {


    private Long ucid;

    private Long companyId;

    private List<String> authorities;

    private String DateType;

    private Boolean status = false;

    private Boolean enable = false;


    //getter-setter


    public Long getUcid() {
        return ucid;
    }

    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getDateType() {
        return DateType;
    }

    public void setDateType(String dateType) {
        DateType = dateType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
