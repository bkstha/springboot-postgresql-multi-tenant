package com.journal.app.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    //    private final Long ucid;
    private final String username;
    private final String name;
    private final String password;
    private final String email;
    //    private final String schema;
//    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
            Long id,
//          Long ucid,
            String username,
            String name,
            String email,
            String password,
//          String schema,
//          Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
//        this.ucid = ucid;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
//        this.schema = schema;
//        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

//    public JwtUser(
//          Long id,
//          Long ucid,
//          String username,
//          String name,
//          String email,
//          String password,
//          String schema,
//          Collection<? extends GrantedAuthority> authorities,
//          boolean enabled,
//          Date lastPasswordResetDate
//    ) {
//        this.id = id;
//        this.username = username;
//        this.name=name;
//        this.email = email;
//        this.password = password;
//        this.enabled = enabled;
//        this.lastPasswordResetDate = lastPasswordResetDate;
//    }


//
//    public Long getUcid() {
//        return ucid;
//    }
//
//    public String getSchema() {
//        return schema;
//    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
