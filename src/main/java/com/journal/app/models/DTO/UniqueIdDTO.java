package com.journal.app.models.DTO;

import javax.persistence.Column;

public class UniqueIdDTO {
    private Long id;

    private String code;

    private Long uniqueId;

    public UniqueIdDTO() {
    }

    public UniqueIdDTO(Long id, String code, Long uniqueId) {
        this.id= id;
        this.code = code;
        this.uniqueId = uniqueId;
    }

    //getter-setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }
}
