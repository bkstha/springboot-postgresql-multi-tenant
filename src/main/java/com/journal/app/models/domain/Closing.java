package com.journal.app.models.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "closings")
@GenericGenerator(
        name = "idgen",
        strategy = "com.journal.app.models.UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_public"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class Closing extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id", nullable = false, foreignKey = @ForeignKey(name = "fk_fiscal_id"))
    public FiscalYear fiscalYear;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "fk_account_id"))
    public AccountGroup account;

    @Column(name = "amount", columnDefinition = "decimal(20, 2)", nullable = false)
    public BigDecimal amount;

    public Closing(){}

    public Closing(FiscalYear fiscalYear, AccountGroup account, BigDecimal amount) {
        this.fiscalYear = fiscalYear;
        this.account = account;
        this.amount = amount;
    }

//
//    public void save() throws Exception{
//        JPA.em().persist(this);
//        String tableName = this.getClass().getSimpleName();
//        Application.addSync(tableName, this.getId());
//    }
//
//
//    public void update() throws Exception{
//        JPA.em().merge(this);
//        if(this.getUpdate()) {
//            String tableName = this.getClass().getSimpleName();
//            Application.updateSync(tableName, this.getId());
//        }
//    }

//    public static Closing findById(Long id) {
//        return JPA.em().find(Closing.class, id);
//    }
}
