package com.journal.app.models.domain;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "account_transactions")
@GenericGenerator(
        name = "idgen",
        strategy = "UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_account_transaction"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class AccountTransaction extends AbstractModel {

    @Column(name = "voucher_number", nullable = false)
    private String voucherNumber;

    @Column(name = "voucher_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date voucherDate;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "fk_account_id"))
    private AccountGroup account;

    @Column(name = "dr_amount", columnDefinition = "decimal(20,6)", nullable = false)
    private BigDecimal drAmount = new BigDecimal(0);

    @Column(name = "cr_amount", columnDefinition = "decimal(20,6)", nullable = false)
    private BigDecimal crAmount = new BigDecimal(0);

    @Column(name = "source", nullable = false)
    private String source;

    @ManyToOne
    @JoinColumn(name = "fiscal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_fiscal_id"))
    private FiscalYear fiscalYear;

    public AccountTransaction() {
    }

    public AccountTransaction(String voucherNumber, Date voucherDate, AccountGroup code, BigDecimal drAmount, BigDecimal crAmount, String source) {
        this.voucherNumber = voucherNumber;
        this.voucherDate = voucherDate;
        this.account = code;
        this.drAmount = drAmount;
        this.crAmount = crAmount;
        this.source = source;
    }


//    getter-setter


    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public AccountGroup getAccount() {
        return account;
    }

    public void setAccount(AccountGroup account) {
        this.account = account;
    }

    public BigDecimal getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(BigDecimal drAmount) {
        this.drAmount = drAmount;
    }

    public BigDecimal getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(BigDecimal crAmount) {
        this.crAmount = crAmount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FiscalYear getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(FiscalYear fiscalYear) {
        this.fiscalYear = fiscalYear;
    }
}
