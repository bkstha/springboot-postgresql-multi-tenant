package com.journal.app.controllers;

import com.journal.app.models.DTO.CompanyDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.UsersCompanyDTO;
import com.journal.app.models.DTO.auth.LoginResponseDTO;
import com.journal.app.models.domain.Authority;
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

import java.util.ArrayList;
import java.util.List;

import static com.journal.app.utils.ApiConstant.API_VER;
import static com.journal.app.utils.ApiConstant.LOGIN;

@RestController
@RequestMapping(API_VER)
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Value("${app.crypto.salt}")
    private String salt;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(method = RequestMethod.POST, value = LOGIN)
    @ResponseBody
    public ResponseEntity<LoginResponseDTO> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, Device device) {

        LoginResponseDTO response = new LoginResponseDTO();
//        List<String> errors = new ArrayList<>();
        UserDTO user = userService.get(jwtAuthenticationRequest.getUsername(), BCrypt.hashpw(jwtAuthenticationRequest.getPassword(), salt));
        response.setUser(user);
        response.setToken(jwtTokenUtil.generateToken(user.getUsername(), device));

        if (user.getCompanyList()!= null && user.getCompanyList().size() == 1) {
//            UsersCompanyDTO usersCompany = user.getCompanyList().get(0);
//            List<Authority> authorities = usersCompany.getAuthorityList();
//            CompanyDTO companyDTO = new CompanyDTO(userCompany.getSchema(), userCompany.getName());

//            response.setUsersCompany(usersCompany);
//            response.setUsersCompany(companyDTO);
//            response.setUcid(userCompany.getUcId());

            List<String> roles = new ArrayList<>();
//            user.getCompanyList().get
//
//            authorities.forEach((authority) -> {
//                if (authority.getRole().equals(UserRole.A) || authority.getRole().equals(UserRole.AA) || authority.getRole().equals(UserRole.Z)) {
//                    response.setToCompanyList(true);
//                }
//                roles.add(authority.getRole().name());
//            });

            if (!response.getToCompanyList() && roles.size() > 0) {
                response.setToken(jwtTokenUtil.generateToken(user.getUsername(), user.getCompanyList().get(0).getSchema(), roles, device));
            }
        } else {
            response.setToCompanyList(true);
        }
//        response.setEmail(user.getEmail());
//        response.setUsername(user.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
