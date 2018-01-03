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

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserCompanyRepository userCompanyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
//        UserCompany user = userCompanyRepository.findOne(Long.parseLong(username));

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
//            return JwtUserFactory.create(user);
//            return new UserDetails() {
//                @Override
//                public Collection<? extends GrantedAuthority> getAuthorities() {
//                    return null;
//                }
//
//                @Override
//                public String getPassword() {
//                    return null;
//                }
//
//                @Override
//                public String getUsername() {
//                    return null;
//                }
//
//                @Override
//                public boolean isAccountNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isAccountNonLocked() {
//                    return false;
//                }
//
//                @Override
//                public boolean isCredentialsNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isEnabled() {
//                    return false;
//                }
//            };
        }
    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username, String oneMore) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
////        UserCompany user = userCompanyRepository.findOne(Long.parseLong(username));
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
////            return JwtUserFactory.create(user);
////            return JwtUserFactory.create(user);
//            return new UserDetails() {
//                @Override
//                public Collection<? extends GrantedAuthority> getAuthorities() {
//                    return null;
//                }
//
//                @Override
//                public String getPassword() {
//                    return null;
//                }
//
//                @Override
//                public String getUsername() {
//                    return null;
//                }
//
//                @Override
//                public boolean isAccountNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isAccountNonLocked() {
//                    return false;
//                }
//
//                @Override
//                public boolean isCredentialsNonExpired() {
//                    return false;
//                }
//
//                @Override
//                public boolean isEnabled() {
//                    return false;
//                }
//            };
//        }
//    }
}
