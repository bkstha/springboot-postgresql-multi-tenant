package com.journal.app.models.domain;

import com.journal.app.models.enums.UserDate;
import com.journal.app.models.enums.UserRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "user_company")
@GenericGenerator(
        name = "idgen",
        strategy = "UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_private"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class UserCompany extends AbstractModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_company_id"))
    private Company company;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "parties_id")
    private Long partiesId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "date_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserDate dateType = UserDate.INT;

//    @Column(name = "due_date_format", nullable = false)
//    @Enumerated(EnumType.ORDINAL)
//    private UserDate dueDateFormat = UserDate.INT;


//    @JsonBackReference
//    @OneToMany(mappedBy = "attendedBy")
//    private List<SalesDetail> salesDetailList;

//    @JsonBackReference
//    @OneToMany(mappedBy = "referencedBy")
//    private List<SalesInvoice> salesInvoiceList;

//    @JsonBackReference
//    @OneToMany(mappedBy = "taskCreated")
//    private Set<Task> taskSet;

//    @JsonBackReference
//    @OneToMany(mappedBy = "waiter")
//    private Set<TableInvoice> tableInvoiceSet;


    //getter-setter

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPartiesId() {
        return partiesId;
    }

    public void setPartiesId(Long partiesId) {
        this.partiesId = partiesId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserDate getDateType() {
        return dateType;
    }

    public void setDateType(UserDate dateType) {
        this.dateType = dateType;
    }
}
