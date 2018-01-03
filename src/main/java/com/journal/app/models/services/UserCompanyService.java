package com.journal.app.models.services;

import com.journal.app.models.domain.UserCompany;
import com.journal.app.models.repositories.UserCompanyRepository;
import com.journal.app.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCompanyService {

    @Autowired
    private UserCompanyRepository userCompanyRepository;


//    public UserDetails loadUserByUserIdAndCompanyId(long userId, long companyId) throws UsernameNotFoundException {
//        UserCompany user = userCompanyRepository.findByUserIdAndCompanyId(userId, companyId);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
//            return JwtUserFactory.create(user);
//        }
//    }

    public List<UserCompany> findByUserId(Long userId) {
        return userCompanyRepository.findByUserId(userId);
    }

}
