package com.journal.app.controllers;


import com.journal.app.database.MultiTenantRegistration;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.domain.User;
import com.journal.app.models.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.sql.Connection;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

@RestController
public class AppController {

    private static final Logger logger = LogManager.getLogger(AppController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() {
        return "Hello Index Page";
    }

    @RequestMapping("/CreateSchema/{schema}")
    @ResponseBody
    public String createSchema(@PathVariable(name = "schema") String schema) {
        Connection schemaConnection = null;
        Session schemaSession = null;
        Transaction schemaTransaction = null;
        System.out.println(schema);
        MultiTenantRegistration.createPublicSchema();
        MultiTenantRegistration.createUserSchema("email@shresthab.com.np", schema);
        try{
            App.setDefaultData(schema, schemaSession, schemaTransaction);
            schemaTransaction.commit();
        }catch (Exception e){
            schemaTransaction.rollback();
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }

        return "Schema Created";
    }


    @RequestMapping("/SetupDefaultData")
    @ResponseBody
    @Transactional()
    public String setupDefaultData() {

//        return CompletableFuture.runAsync(() -> {
        Connection schemaConnection = null;
        Session schemaSession = null;
        Transaction schemaTransaction = null;
        try {
            schemaConnection = MultiTenantRegistration.getConnection("public");
            schemaSession = MultiTenantRegistration.getSession(schemaConnection);
            schemaTransaction = schemaSession.beginTransaction();
            App.setDefaultData("public", schemaSession, schemaTransaction);
//                if (!App.schemaExist("suraj-app", jpaApi)) {
//            MultiTenantRegistration.createUserSchema("email@shresthab.com.np", "surajappschema");
//                Logger.info("Initializing default data for surajappschema");
//                App.setDefaultData("surajappschema", schemaSession, schemaTransaction);

//                Logger.info("creating company surajappschema");
//                Company company = new Company();
//                company.name = "suraj-company";
//                company.address = "kuleshwor-14, ktm";
//                company.city = "kathmandu";
//                company.email = "email@shresthab.com.np";
//                company.mobileNumber = "9849636369";
//                company.phoneNumber = "9849636369";
//                company.schema = "surajappschema";
//                company.startMonth = "shrawan";
//                company.state = "bagmati";
//                company.zipCode = "44600";
//                company.country = Country.findByName("Nepal", jpaApi);
//                company.startDate = Calendar.getInstance();
//                company.currency = company.country;
//                company.calendarType = CalendarType.IN;
//                company.invoiceType = InvoiceType.Hostel;
//                company.schemaStatus = true;
//                company.setupStatus = true;
//                schemaSession.save(company);

//                }

            schemaTransaction.commit();
            logger.info("Default public data created");
        } catch (Exception e) {
            schemaTransaction.rollback();
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }
//            });
//        }).toString();
//        App.setDefaultData();
        return "Default Data Created";
    }


    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public UserDTO addUser(@RequestBody UserDTO user) {
        userService.addUser(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public User addUser(@RequestBody User user, @PathVariable(value = "id") Long userId) {
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void addUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
