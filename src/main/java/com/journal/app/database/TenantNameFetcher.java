//package com.journal.app.database;
//
//
//import com.journal.app.models.domain.UserCompany;
//import com.journal.app.models.repositories.UserCompanyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Alon Segal on 24/03/2017.
// */
//@Component
//public class TenantNameFetcher extends UnboundTenantTask<UserCompany> {
//
//    @Autowired
//    private UserCompanyRepository userCompanyRepository;
//
//    @Override
//    protected UserCompany callInternal() {
//        UserCompany utr = userCompanyRepository.findOne(Long.parseLong(this.username));
//        return utr;
//    }
//}
