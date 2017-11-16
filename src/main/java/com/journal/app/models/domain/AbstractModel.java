package com.journal.app.models.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;


@MappedSuperclass
public abstract class AbstractModel implements Serializable {


//    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "timestamp with time zone")
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "timestamp with time zone")
    private Calendar updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    @PrePersist
    public void updateTime() {
        if (null == createdAt) {
            this.createdAt = Calendar.getInstance();
            try {
//                this.createdBy = Long.parseLong(Controller.session("uid"));
            } catch (Exception ne) {
                this.createdBy = null;
            }
        }
        try {
//            this.updatedBy = Long.parseLong(Controller.session("uid"));
        } catch (Exception ne) {
            this.updatedBy = null;
        }
        this.updatedAt = Calendar.getInstance();
    }
}
