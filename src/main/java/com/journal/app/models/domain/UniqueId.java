package com.journal.app.models.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "unique_id")
@GenericGenerator(
        name = "idgen",
        strategy = "UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_app"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class UniqueId extends AbstractModel {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "unique_id", nullable = false)
    private Long uniqueId;

    public UniqueId() {
    }

    public UniqueId(String code, long uniqueId) {
        this.code = code;
        this.uniqueId = uniqueId;
    }


    //getter-setter
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
