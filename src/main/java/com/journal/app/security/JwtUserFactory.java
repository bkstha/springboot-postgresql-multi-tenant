package com.journal.app.security;

import com.journal.app.models.domain.Authority;
import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserCompany userCompany) {
        return new JwtUser(
                userCompany.getUser().getId(),
                userCompany.getId(),
                userCompany.getUser().getUsername(),
                userCompany.getUser().getName(),
                userCompany.getUser().getEmail(),
                userCompany.getUser().getPassword(),
                userCompany.getCompany().getSchema(),
                mapToGrantedAuthorities(userCompany.getAuthorities()),
                userCompany.getEnabled(),
                userCompany.getUser().getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .collect(Collectors.toList());
    }
}
