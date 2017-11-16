package com.journal.app.models.domain;

import com.journal.app.models.enums.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "companies")
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
public class Company extends AbstractModel {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 10, nullable = false)
    private String schema;

    @Column(name = "start_month")
    private String startMonth;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", length = 40)
    private String phoneNumber;

    @Column(name = "mobile", length = 20)
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "country", nullable = false, foreignKey = @ForeignKey(name = "fk_country_id"))
    private Country country;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Calendar startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "audited_date")
    private Date auditedDate;

    @Transient
    private String stringAuditedDate;

    @Column(name = "fiscal_year", length = 9)
    private String fiscalYear;

    @ManyToOne
    @JoinColumn(name = "currency", nullable = false, foreignKey = @ForeignKey(name = "fk_currency_id"))
    private Country currency;

    @Column(name = "tax_id")
    private String taxId;

    @Enumerated(EnumType.STRING)
    @Column(name = "calendar_type")
    private CalendarType calendarType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "invoice_type")
    private InvoiceType invoiceType;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    @Column(name = "schema_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean schemaStatus = false;

    @Column(name = "setup_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean setupStatus = false;

    @Column(name = "rounding", nullable = false, columnDefinition = "boolean default false")
    private Boolean rounding = false;

    @Column(name = "print_draft", nullable = false, columnDefinition = "boolean default true")
    private Boolean printDraft = true;

    @Column(name = "rm_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean rawStatus = false;

    @Column(name="os_status", columnDefinition="boolean default false", nullable=false)
    private Boolean osStatus=false;

    @Column(name="company_header", columnDefinition="boolean default true", nullable=false)
    private Boolean companyHeader=true;

    @Column(name = "tds")
    private Long tds;

    @Column(name = "si_tax")
    private Long siTax;

    @Column(name = "ii_tax")
    private Long iiTax;

    @Enumerated(EnumType.STRING)
    @Column(name = "print_type", length = 2)
    private PrintType printType= PrintType.N;

    @Column(name = "res_tax")
    private Long resTax;

    @Column(name = "rsp")
    private BigDecimal servicePercent;

    @Column(name = "hsp")
    private BigDecimal servicePercentHotel;

    @Column(name = "invoice_seq", columnDefinition = "int default 1", nullable = false)
    private Integer invoiceSequence;

    @Column(name = "user_limit", columnDefinition = "int default 2", nullable = false)
    private Integer userLimit=99;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan", length = 2)
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "expire_date")
    private Calendar expireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private AppMode mode= AppMode.N;

    @Column(name = "warehouse_limit",nullable = false, columnDefinition = "int default 1")
    private Integer warehouseLimit=1;

    @Column(name = "chequeManager", nullable = false, columnDefinition = "boolean default false")
    private Boolean chequeManager = false;

    @Column(name = "fixedAsset", nullable = false, columnDefinition = "boolean default false")
    private Boolean fixedAsset = false;

    @Column(name = "payRoll", nullable = false, columnDefinition = "boolean default false")
    private Boolean payRoll = false;

    @OneToMany(mappedBy = "company")
    private List<UserCompany> userCompanyList;


    @OneToMany(mappedBy = "company")
    private List<FiscalYear> fiscalYearList;

    @OneToMany(mappedBy = "company")
    private List<Subscription> subscriptionList;
    
    
//    getter-setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Date getAuditedDate() {
        return auditedDate;
    }

    public void setAuditedDate(Date auditedDate) {
        this.auditedDate = auditedDate;
    }

    public String getStringAuditedDate() {
        return stringAuditedDate;
    }

    public void setStringAuditedDate(String stringAuditedDate) {
        this.stringAuditedDate = stringAuditedDate;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Country getCurrency() {
        return currency;
    }

    public void setCurrency(Country currency) {
        this.currency = currency;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getSchemaStatus() {
        return schemaStatus;
    }

    public void setSchemaStatus(Boolean schemaStatus) {
        this.schemaStatus = schemaStatus;
    }

    public Boolean getSetupStatus() {
        return setupStatus;
    }

    public void setSetupStatus(Boolean setupStatus) {
        this.setupStatus = setupStatus;
    }

    public Boolean getRounding() {
        return rounding;
    }

    public void setRounding(Boolean rounding) {
        this.rounding = rounding;
    }

    public Boolean getPrintDraft() {
        return printDraft;
    }

    public void setPrintDraft(Boolean printDraft) {
        this.printDraft = printDraft;
    }

    public Boolean getRawStatus() {
        return rawStatus;
    }

    public void setRawStatus(Boolean rawStatus) {
        this.rawStatus = rawStatus;
    }

    public Boolean getOsStatus() {
        return osStatus;
    }

    public void setOsStatus(Boolean osStatus) {
        this.osStatus = osStatus;
    }

    public Boolean getCompanyHeader() {
        return companyHeader;
    }

    public void setCompanyHeader(Boolean companyHeader) {
        this.companyHeader = companyHeader;
    }

    public Long getTds() {
        return tds;
    }

    public void setTds(Long tds) {
        this.tds = tds;
    }

    public Long getSiTax() {
        return siTax;
    }

    public void setSiTax(Long siTax) {
        this.siTax = siTax;
    }

    public Long getIiTax() {
        return iiTax;
    }

    public void setIiTax(Long iiTax) {
        this.iiTax = iiTax;
    }

    public PrintType getPrintType() {
        return printType;
    }

    public void setPrintType(PrintType printType) {
        this.printType = printType;
    }

    public Long getResTax() {
        return resTax;
    }

    public void setResTax(Long resTax) {
        this.resTax = resTax;
    }

    public BigDecimal getServicePercent() {
        return servicePercent;
    }

    public void setServicePercent(BigDecimal servicePercent) {
        this.servicePercent = servicePercent;
    }

    public BigDecimal getServicePercentHotel() {
        return servicePercentHotel;
    }

    public void setServicePercentHotel(BigDecimal servicePercentHotel) {
        this.servicePercentHotel = servicePercentHotel;
    }

    public Integer getInvoiceSequence() {
        return invoiceSequence;
    }

    public void setInvoiceSequence(Integer invoiceSequence) {
        this.invoiceSequence = invoiceSequence;
    }

    public Integer getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    public AppMode getMode() {
        return mode;
    }

    public void setMode(AppMode mode) {
        this.mode = mode;
    }

    public Integer getWarehouseLimit() {
        return warehouseLimit;
    }

    public void setWarehouseLimit(Integer warehouseLimit) {
        this.warehouseLimit = warehouseLimit;
    }

    public Boolean getChequeManager() {
        return chequeManager;
    }

    public void setChequeManager(Boolean chequeManager) {
        this.chequeManager = chequeManager;
    }

    public Boolean getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(Boolean fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    public Boolean getPayRoll() {
        return payRoll;
    }

    public void setPayRoll(Boolean payRoll) {
        this.payRoll = payRoll;
    }

    public List<UserCompany> getUserCompanyList() {
        return userCompanyList;
    }

    public void setUserCompanyList(List<UserCompany> userCompanyList) {
        this.userCompanyList = userCompanyList;
    }

    public List<FiscalYear> getFiscalYearList() {
        return fiscalYearList;
    }

    public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
        this.fiscalYearList = fiscalYearList;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
