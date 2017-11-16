package com.journal.app.models.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by lakhey on 15/08/2017.
 */
@Entity
@Table(name = "guardians")
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
public class Guardian extends AbstractModel {

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "relation", length = 20)
    private String relation;
    @Column(name = "occupation", length = 50)
    private String occupation;
    @Column(name = "contact_no", length = 15)
    private String contactNumber;
    @Column(name = "address", length = 50)
    private String address;

    @ManyToOne
    @JoinColumn(name = "parties_id", nullable = false, foreignKey = @ForeignKey(name = "fk_parties_id"))
    private Parties parties;


    //getter-setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Parties getParties() {
        return parties;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }
}
