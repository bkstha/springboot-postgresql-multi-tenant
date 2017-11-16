package com.journal.app.models.domain;

import com.journal.app.models.enums.BloodGroup;
import com.journal.app.models.enums.FoodType;
import com.journal.app.models.enums.PartiesType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lakhey on 15/08/2017.
 */
@Entity
@Table(name = "parties")
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
public class Parties extends AbstractModel {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "code", nullable = false, length = 20, unique = true)
    private String code;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "document_detail", length = 50)
    private String documentDetail;

    @Column(name = "profile_path", length = 100)
    private String profilePath;

    @Column(name = "blood_group", length = 5)
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "parties_type", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private PartiesType partiesType;

    @Column(name = "food_type", length = 2)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Column(name = "institute_name")
    private String instituteName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "study_level", length = 20)
    private String studyLevel;

    @Column(name = "stay_duration")
    private Integer stayDuration;

    @Column(name = "disease", length = 25)
    private String disease;

    @Column(name = "guardian_instruction", length = 100)
    private String guardianInstruction;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date")
    private Date bookingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "admission_date")
    private Date admissionDate;

    @Column(name = "monthly_fee")
    private Double monthlyFee;

    @Column(name = "room_no", length = 10)
    private String roomNo;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "note", length = 100)
    private String note;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_account_id"))
    private AccountGroup account;

    @Transient
    private BigDecimal openingBalance=new BigDecimal(0);

    @OneToMany(mappedBy = "parties", cascade = CascadeType.ALL)
    private List<Guardian> guardianList;

}
