package com.journal.app.controllers;


import com.journal.app.database.MultiTenantRegistration;
import com.journal.app.models.DTO.CompanyDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.auth.LoginResponseDTO;
import com.journal.app.models.DTO.auth.UserCompanyResponseDTO;
import com.journal.app.models.domain.Authority;
import com.journal.app.models.domain.Company;
import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import com.journal.app.models.enums.CalendarType;
import com.journal.app.models.enums.Gender;
import com.journal.app.models.enums.InvoiceType;
import com.journal.app.models.enums.UserRole;
import com.journal.app.models.services.UserCompanyService;
import com.journal.app.models.services.UserService;
import com.journal.app.models.services.impl.UserServiceImpl;
import com.journal.app.security.JwtAuthenticationRequest;
import com.journal.app.security.JwtTokenUtil;
import com.journal.app.security.service.JwtAuthenticationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class AppController {

    private static final Logger logger = LogManager.getLogger(AppController.class);

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
//    @Autowired
//    private UserCompanyService userCompanyService;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Value("${app.crypto.salt}")
    private String salt;

    @RequestMapping("/")
    @ResponseBody
    @Transactional(readOnly = true)
    public String helloWorld() {
        return "Hello Index Page";
    }

    @RequestMapping("/CreateToken")
    @ResponseBody
    @Transactional(readOnly = true)
    public String createToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, Device device) {

//        System.out.println(authenticationRequest);
        System.out.println("device");
        System.out.println(device);

//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getUsername(),
//                        authenticationRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        System.out.println("username: ");
//        System.out.println(authenticationRequest.getUsername());
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
        final String userAuthToken = jwtTokenUtil.generateToken(jwtAuthenticationRequest.getUsername(), device);
//        final String token = jwtTokenUtil.generateToken(userDetails, device);
//        System.out.println("token: "+token);
//         Return the token
//        return new JwtAuthenticationResponse(token).toString();
        return userAuthToken;
    }

    @RequestMapping("/admin/CreateSchema/{schema}")
    @ResponseBody
    public String createSchema(@PathVariable(name = "schema") String schema) {
        Connection schemaConnection;
        Session schemaSession;
        Transaction schemaTransaction = null;
        System.out.println(schema);
        MultiTenantRegistration.createPublicSchema();
        MultiTenantRegistration.createUserSchema("email@shresthab.com.np", schema);
        try {
            schemaConnection = MultiTenantRegistration.getConnection(schema);
            schemaSession = MultiTenantRegistration.getSession(schemaConnection);
            schemaTransaction = schemaSession.beginTransaction();
            App.setDefaultData(schema, schemaSession, schemaTransaction);
            schemaTransaction.commit();
        } catch (Exception e) {
            if (schemaTransaction != null) {
                schemaTransaction.rollback();
            }
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }

        return "Schema Created";
    }


    @RequestMapping("/InitializeApp")
    @ResponseBody
    @Transactional()
    public String initializeApp() {
        Connection publicConnection;
        Session publicSession;
        Transaction publicTransaction = null;
        try {
            publicConnection = MultiTenantRegistration.getConnection("public");
            publicSession = MultiTenantRegistration.getSession(publicConnection);
            publicTransaction = publicSession.beginTransaction();
            App.setDefaultData("public", publicSession, publicTransaction);

            if (UserServiceImpl.isTableEmpty(publicSession)) {
                logger.info("Inserting default User...");
                User user = new User();
                user.setUsername("admin");
                user.setEmail("email@shresthab.com.np");
                user.setPassword(BCrypt.hashpw("PASSWORD", salt));
                user.setLoginAccess(true);
                user.setName("Bikash Shrestha");
                user.setContactNumber("9849636369");
                user.setGender(Gender.M);
                publicSession.save(user);
            }
            String surajAppSchema = "surajappsc";
            if (!App.schemaExist(surajAppSchema, publicSession)) {

                MultiTenantRegistration.createUserSchema("email@shresthab.com.np", surajAppSchema);
                logger.info("Initializing default data for " + surajAppSchema);

                Connection schemaConnection2;
                Session schemaSession2;
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
                    publicSession.save(company);

                    schemaTransaction2.commit();
                } catch (Exception e) {
                    logger.error("Error Occurred while adding default data to " + surajAppSchema, e);
                    if (schemaTransaction2 != null) {
                        schemaTransaction2.rollback();
                    }
                }
            }
            publicTransaction.commit();
            logger.info("Default public data created");
        } catch (Exception e) {
            if (publicTransaction != null) {
                publicTransaction.rollback();
            }
            logger.error("Unable to initialize default data");
            logger.error("Error Occurred: ", e);
        }
        return "Default Data Created";
    }



}
