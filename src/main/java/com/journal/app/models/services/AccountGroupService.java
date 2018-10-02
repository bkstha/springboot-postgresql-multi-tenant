package com.journal.app.models.services;

import com.journal.app.models.domain.AccountGroup;
import com.journal.app.models.repositories.AccountGroupRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountGroupService {

    @Autowired
    private AccountGroupRepository accountGroupRepository;

    //returns all accountGroups
    public List<AccountGroup> getAllAcccountGroups() {
        List<AccountGroup> accountGroupList = new ArrayList<>();
        accountGroupRepository.findAll().forEach(accountGroupList::add);
        return accountGroupList;
    }

    public static boolean isTableEmpty(Session session) {
        boolean b;
        Long total = (Long) session
                .createQuery("select count(c) from AccountGroup c")
                .getSingleResult();
        b = total <= 0;
        return b;
    }

    public Long getCount() {
        return accountGroupRepository.count();
    }

}
