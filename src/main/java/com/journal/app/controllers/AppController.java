package com.journal.app.controllers;


import com.journal.app.database.MultiTenantRegistration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@RestController
public class AppController {

    @PersistenceContext
    EntityManager em;

    @RequestMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() {
        em.createNativeQuery("Select 1").getSingleResult();

        return "Hello Index Page";
    }
    @RequestMapping("/CreateSchema/{schema}")
    @ResponseBody
    @Transactional(readOnly = true)
    public String createSchema(@PathVariable(name = "schema") String schema) {
        System.out.println(schema);
        MultiTenantRegistration.createPublicSchema();
        MultiTenantRegistration.createUserSchema("email@shresthab.com.np", schema);
        return "Schema Created";
    }
}
