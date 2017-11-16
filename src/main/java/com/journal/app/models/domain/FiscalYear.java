package com.journal.app.models.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "fiscal_year")
@GenericGenerator(
        name = "idgen",
        strategy = "com.journal.app.models.UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_private"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class FiscalYear extends AbstractModel {
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_company_id"))
    private Company company;

    @Column(name = "year_start", nullable = false)
    private Calendar yearStart;

    @Column(name = "year_end", nullable = false)
    private Calendar yearEnd;

    @Column(name = "fiscal_year", length=9, nullable = false)
    private String fiscalYear;

    @OneToMany(mappedBy = "fiscalYear")
    private List<Closing> closingList;

    @OneToMany(mappedBy = "fiscalYear")
    private List<AccountTransaction> transactionList;


    public FiscalYear() {

    }

    public FiscalYear(Company company, Calendar yearStart, Calendar yearEnd, String fiscalYear) {
        this.company = company;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.fiscalYear = fiscalYear;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Calendar getYearStart() {
        return yearStart;
    }

    public void setYearStart(Calendar yearStart) {
        this.yearStart = yearStart;
    }

    public Calendar getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Calendar yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public List<Closing> getClosingList() {
        return closingList;
    }

    public void setClosingList(List<Closing> closingList) {
        this.closingList = closingList;
    }

    public List<AccountTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AccountTransaction> transactionList) {
        this.transactionList = transactionList;
    }
}
