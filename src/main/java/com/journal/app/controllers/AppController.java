package com.journal.app.controllers;


import com.journal.app.database.MultiTenantRegistration;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.domain.Company;
import com.journal.app.models.domain.User;
import com.journal.app.models.enums.CalendarType;
import com.journal.app.models.enums.Gender;
import com.journal.app.models.enums.InvoiceType;
import com.journal.app.models.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.Calendar;

@RestController
public class AppController {

    private static final Logger logger = LogManager.getLogger(AppController.class);

    @Autowired
    private UserService userService;

    @Value("${app.crypto.salt}")
    private String salt;

    @RequestMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() {
        return "Hello Index Page";
    }

    @RequestMapping("/admin/CreateSchema/{schema}")
    @ResponseBody
    public String createSchema(@PathVariable(name = "schema") String schema) {
        Connection schemaConnection = null;
        Session schemaSession = null;
        Transaction schemaTransaction = null;
        System.out.println(schema);
        MultiTenantRegistration.createPublicSchema();
        MultiTenantRegistration.createUserSchema("email@shresthab.com.np", schema);
        try {
            App.setDefaultData(schema, schemaSession, schemaTransaction);
            schemaTransaction.commit();
        } catch (Exception e) {
            schemaTransaction.rollback();
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }

        return "Schema Created";
    }


    @RequestMapping("/InitializeApp")
    @ResponseBody
    @Transactional()
    public String initializeApp() {


        Connection schemaConnection = null;
        Session schemaSession = null;
        Transaction schemaTransaction = null;
        try {
            schemaConnection = MultiTenantRegistration.getConnection("public");
            schemaSession = MultiTenantRegistration.getSession(schemaConnection);
            schemaTransaction = schemaSession.beginTransaction();
            App.setDefaultData("public", schemaSession, schemaTransaction);

            if (UserService.isTableEmpty(schemaSession)) {
                logger.info("Inserting default User...");
                User user = new User();
                user.setUsername("admin");
                user.setEmail("email@shresthab.com.np");
                user.setPassword(BCrypt.hashpw("password", salt));
                user.setLoginAccess(true);
                user.setName("Bikash Shrestha");
                user.setContactNumber("9849636369");
                user.setGender(Gender.M);
                schemaSession.save(user);
            }
            String surajAppSchema = "surajappsc";
            if (!App.schemaExist(surajAppSchema, schemaSession)) {

                MultiTenantRegistration.createUserSchema("email@shresthab.com.np", surajAppSchema);
                logger.info("Initializing default data for " + surajAppSchema);

                Connection schemaConnection2 = null;
                Session schemaSession2 = null;
                Transaction schemaTransaction2 = null;

                try {
                    schemaConnection2 = MultiTenantRegistration.getConnection(surajAppSchema);
                    schemaSession2 = MultiTenantRegistration.getSession(schemaConnection2);
                    schemaTransaction2 = schemaSession2.beginTransaction();
                    App.setDefaultData(surajAppSchema, schemaSession2, schemaTransaction2);

                    logger.info("creating company " + surajAppSchema);
                    Company company = new Company();
                    company.setName("suraj-company");
                    company.setAddress("kuleshwor-14, ktm");
                    company.setCity("kathmandu");
                    company.setEmail("email@shresthab.com.np");
                    company.setMobileNumber("9849636369");
                    company.setPhoneNumber("9849636369");
                    company.setSchema(surajAppSchema);
                    company.setStartMonth("shrawan");
                    company.setState("bagmati");
                    company.setZipCode("44600");
                    company.setStartDate(Calendar.getInstance());
                    company.setCalendarType(CalendarType.IN);
                    company.setInvoiceType(InvoiceType.Hostel);
                    company.setSchemaStatus(true);
                    company.setSetupStatus(true);
                    schemaSession.save(company);

                    schemaTransaction2.commit();
                } catch (Exception e) {
                    logger.error("Error Occurred while adding default data to " + surajAppSchema, e);
                    schemaTransaction2.rollback();
                }
            }
            schemaTransaction.commit();
            logger.info("Default public data created");
        } catch (Exception e) {
            schemaTransaction.rollback();
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }
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
