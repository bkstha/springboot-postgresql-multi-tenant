package com.journal.app.security.service;

import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import com.journal.app.models.repositories.UserCompanyRepository;
import com.journal.app.models.repositories.UserRepository;
import com.journal.app.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public UserDetails loadUserByUsername(String username, List<String> roles) throws UsernameNotFoundException {
        System.out.println("get user from database on api call");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user, roles);
        }
    }

}
