package com.journal.app.models.domain;

import com.journal.app.models.enums.UserRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "authorities")
@GenericGenerator(
        name = "idgen",
        strategy = "com.journal.app.models.UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_user_auth"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class Authority extends AbstractModel{

    @Column(name = "name", length = 2)
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<UserCompany> users;


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<UserCompany> getUsers() {
        return users;
    }

    public void setUsers(List<UserCompany> users) {
        this.users = users;
    }
}