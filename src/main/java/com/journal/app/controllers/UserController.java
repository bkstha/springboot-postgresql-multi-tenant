package com.journal.app.controllers;

import com.journal.app.models.DTO.CompanyDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.auth.LoginResponseDTO;
import com.journal.app.models.DTO.auth.RegisterRequestDTO;
import com.journal.app.models.DTO.auth.RegisterResponseDTO;
import com.journal.app.models.domain.Authority;
import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;
import com.journal.app.models.enums.UserRole;
import com.journal.app.models.services.UserService;
import com.journal.app.security.JwtAuthenticationRequest;
import com.journal.app.security.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Value("${app.crypto.salt}")
    private String salt;


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public ResponseEntity<LoginResponseDTO> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, Device device) {

        System.out.println(jwtAuthenticationRequest);
        System.out.println("device");
        System.out.println(device);

        LoginResponseDTO response = new LoginResponseDTO();
        List<String> errors = new ArrayList<>();
        System.out.println(jwtAuthenticationRequest.getUsername());
        System.out.println(jwtAuthenticationRequest.getPassword());
        System.out.println(BCrypt.hashpw(jwtAuthenticationRequest.getPassword(), salt));
        User user = userService.findByUserAndPassword(jwtAuthenticationRequest.getUsername(), BCrypt.hashpw(jwtAuthenticationRequest.getPassword(), salt));
        if (user == null) {
            logger.error("Unable to find user");
            errors.clear();
            errors.add("Invalid username or password");
            response.setErrors(errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setUid(user.getId());
        response.setToken(jwtTokenUtil.generateToken(user.getUsername(), device));
        if (user.getUserCompanyList().size() == 1) {
            UserCompany userCompany = user.getUserCompanyList().get(0);
            List<Authority> authorities = userCompany.getAuthorities();
            CompanyDTO companyDTO = new CompanyDTO(userCompany.getCompany().getSchema(), userCompany.getCompany().getName());

            response.setCompany(companyDTO);
            response.setUcid(userCompany.getId());

            List<String> roles = new ArrayList<>();

            authorities.forEach((authority) -> {
                System.out.println("user roles: " + authority.getRole());
                if (authority.getRole().equals(UserRole.A) || authority.getRole().equals(UserRole.AA) || authority.getRole().equals(UserRole.Z)) {
                    response.setToCompanyList(true);
                }
                roles.add(authority.getRole().name());
            });

            if (roles.size() > 0) {
                response.setToken(jwtTokenUtil.generateToken(user.getUsername(), companyDTO.getSchema(), roles, device));
            }
        } else {
            response.setToCompanyList(true);
        }
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    @ResponseBody
    public String register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        System.out.println(registerRequestDTO.toString());

        return "success";
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

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}/companies")
    @ResponseBody
    public Long getCompanyList(@PathVariable(value = "id") Long userId) {
        System.out.println("at company list controller method");
//        List<UserCompanyResponseDTO> userCompanyResponseDTOList = new ArrayList<>();
//        userCompanyService.findByUserId(userId).forEach((data)->{
//            UserCompanyResponseDTO userCompany= new UserCompanyResponseDTO();
//            System.out.println(data.getCompany().getSchema());
//            System.out.println(data.getCompany().getName());
//            System.out.println(data.getCompany().getId());
//            System.out.println(data.getDateType().name());
//            System.out.println(data.getStatus());
//            System.out.println(data.getEnabled());
//        });
        return userId;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void addUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
