package com.journal.app.controllers;

import com.journal.app.models.DTO.users.UserDTO;
import com.journal.app.models.DTO.auth.LoginResponseDTO;
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
        UserDTO user = userService.get(jwtAuthenticationRequest.getUsername(), BCrypt.hashpw(jwtAuthenticationRequest.getPassword(), salt));
        response.setUser(user);
        response.setToken(jwtTokenUtil.generateToken(user.getUsername(), device));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
