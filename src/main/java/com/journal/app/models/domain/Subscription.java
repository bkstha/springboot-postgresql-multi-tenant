package com.journal.app.models.domain;

import com.journal.app.models.enums.SubscriptionPlan;
import com.journal.app.models.enums.SubscriptionThrough;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="subscriptions")
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
public class Subscription extends AbstractModel {

    @ManyToOne
    @NotNull(message = "Select Company")
    @JoinColumn(name="company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_company_id"))
    private Company company;

    @Column(name = "tax_amount", columnDefinition = "decimal(20,2) default 0.00", nullable = false)
    private BigDecimal taxAmount=new BigDecimal(0.00);


    @Column(name = "amount", columnDefinition = "decimal(20,2)", nullable = false)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Temporal(TemporalType.DATE)
    @Transient
    private Date eDate;

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan", length = 2)
    private SubscriptionPlan subscriptionPlan;

    @Enumerated(EnumType.STRING)
    @Column(name = "subs_through", nullable = false)
    private SubscriptionThrough subscriptionThrough;

    @Column(name = "subs_through_id")
    private Long subscriptionThroughId;

    @Transient
    private int duration;


    //getter-setter


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public SubscriptionThrough getSubscriptionThrough() {
        return subscriptionThrough;
    }

    public void setSubscriptionThrough(SubscriptionThrough subscriptionThrough) {
        this.subscriptionThrough = subscriptionThrough;
    }

    public Long getSubscriptionThroughId() {
        return subscriptionThroughId;
    }

    public void setSubscriptionThroughId(Long subscriptionThroughId) {
        this.subscriptionThroughId = subscriptionThroughId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
