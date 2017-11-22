package com.journal.app.models.domain;

import com.journal.app.controllers.App;
import com.journal.app.controllers.AppController;
import com.journal.app.models.enums.ACCOUNT;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "account_groups")
@GenericGenerator(
        name = "idgen",
        strategy = "com.journal.app.models.UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_app"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class AccountGroup extends AbstractModel {

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private ACCOUNT account;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "modify_status")
    private Boolean modifyStatus = true;

    @Column(name = "account_status")
    private Boolean accountStatus = false;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "opening_balance", columnDefinition = "decimal(20,2)", nullable = false)
    private BigDecimal openingBalance = new BigDecimal(0);

    @Column(name = "paid_opening_balance", columnDefinition = "decimal(20,2)", nullable = false)
    private BigDecimal paidOpeningBalance = new BigDecimal(0);

    @Column(name = "current_balance", columnDefinition = "decimal(20,2)", nullable = false)
    private BigDecimal currentBalance = new BigDecimal(0);

    @Column(name = "note", length = 100)
    private String note;

//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<JournalDetail> journalDetailList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<AccountTransaction> accountTransactionList;

//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<Tax> taxList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "expenseAccount")
//    private List<ExpenseClaimDetail> expenseClaimDetailList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "paidAccount")
//    private List<ExpenseClaim> expenseClaimList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "tAccount")
//    private List<Banking> bankingToAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "fAccount")
//    private List<Banking> bankingFromAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "adjustmentAccount")
//    private List<InventoryAdjustment> inventoryAdjustmentList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "inventoryAccount")
//    private List<InventoryItem> inventoryAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "cogsAccount")
//    private List<InventoryItem> cogsAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "incomeAccount")
//    private List<InventoryItem> incomeAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<SalesReturn> salesReturnList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "customer")
//    @OrderBy("dueDate")
//    private List<SalesInvoice> salesInvoiceList;
//
//
//    @OneToMany(mappedBy = "account")
//    private List<TempRestaurantSettlement> resSettlementList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "vendor")
//    private List<PurchaseInvoice> purchaseInvoiceList;
//
//
//    @OneToMany(mappedBy = "account")
//    private List<Emi> emiList;
//    @OneToMany(mappedBy = "account")
//    private List<EmiPaymentHistory> emiPaymentHistoryList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "vendor")
//    private List<AdditionalExpense> additionalExpenseVendorList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "expense")
//    private List<AdditionalExpense> additionalExpenseList;
//
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "defaultAccount")
//    private List<Warehouse> warehouseList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "incomeAccount")
//    private List<FixedAssetsItem> fixedIncomeList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "expenseAccount")
//    private List<FixedAssetsItem> fixedExpenseList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "contraAssetsAccount")
//    private List<FixedAssetsItem> fixedContraList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "assetsAccount")
//    private List<FixedAssetsItem> fixedAssetsList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "vendor")
//    private List<FixedAssetsItem> fixedVendorList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "vendor")
//    private List<FixedAssetLandingCost> fixedLandingVendorList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "expense")
//    private List<FixedAssetLandingCost> fixedLandingExpenseList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<Payment> paymentList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<PurchaseReturn> purchaseReturnList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<AdditionalCharge> additionalChargeList;
//
//    @JsonBackReference


    @OneToOne(mappedBy = "account")
    private Parties parties;
//
//    @JsonBackReference
//    @OneToOne(mappedBy = "account")
//    private FixedAssetsItem fixedAssetsItem;
//
//    @JsonBackReference
//    @OneToOne(mappedBy = "account")
//    private AssetsRate assetsRate;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "customer")
//    private List<TableInvoice> tableInvoiceList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<ChequeManager> chequeManagerList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "expenseAccount")
//    private List<PayHead> payHeadList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "bank")
//    private List<LoanManager> loanManagerList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<InvoiceSettlement> invoiceSettlementList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<OutstationExpenses> outstationExpensesList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "paidAccount")
//    private List<Outstation> outstationList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "paidAccount")
//    private List<EmployeePayment> employeePaymentList;

    @OneToMany(mappedBy = "account")
    private List<Closing> closingList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "account")
//    private List<LegacyReturn> legacyReturnAccountList;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "party")
//    private List<LegacyReturn> legacyReturnParties;


    public AccountGroup() {
    }

    public AccountGroup(ACCOUNT account, String name, Long parentId, Boolean modifyStatus, Boolean accountStatus) {
        this.account = account;
        this.name = App.trim(name);
        this.parentId = parentId;
        this.modifyStatus = modifyStatus;
        this.accountStatus = accountStatus;
    }




//    getter-setter


    public ACCOUNT getAccount() {
        return account;
    }

    public void setAccount(ACCOUNT account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(Boolean modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getPaidOpeningBalance() {
        return paidOpeningBalance;
    }

    public void setPaidOpeningBalance(BigDecimal paidOpeningBalance) {
        this.paidOpeningBalance = paidOpeningBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<AccountTransaction> getAccountTransactionList() {
        return accountTransactionList;
    }

    public void setAccountTransactionList(List<AccountTransaction> accountTransactionList) {
        this.accountTransactionList = accountTransactionList;
    }

    public Parties getParties() {
        return parties;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    public List<Closing> getClosingList() {
        return closingList;
    }

    public void setClosingList(List<Closing> closingList) {
        this.closingList = closingList;
    }
}
