package com.journal.app.controllers;

import com.journal.app.exceptions.UserAlreadyExistsException;
import com.journal.app.exceptions.ValidationException;
import com.journal.app.models.DTO.auth.RegisterRequestDTO;
import com.journal.app.models.DTO.users.UserDTO;
import com.journal.app.models.DTO.users.UserResponseDTO;
import com.journal.app.models.services.UserService;
import com.journal.app.responseMessage.ApiMessageResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.journal.app.utils.ApiConstant.*;
import static com.journal.app.utils.MessageConstant.USER_ADD_MESSAGE;

@RestController
@RequestMapping(API_VER + USERS_PATH)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = REGISTER)
    public ResponseEntity<ApiMessageResponse> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        System.out.println(registerRequestDTO.toString());
        ApiMessageResponse apiMessageResponse = new ApiMessageResponse();

        if (registerRequestDTO.getPassword().compareTo(registerRequestDTO.getRepeatPassword()) != 0) {
            throw new ValidationException("Password do not match.");
        }

        userService.addUser(registerRequestDTO);
        apiMessageResponse.setMessage(USER_ADD_MESSAGE);
        return new ResponseEntity<>(apiMessageResponse, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        LOGGER.info("getting user with id " + id);
        return userService.getUserDetails(id);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/users")
//    public UserDTO addUser(@RequestBody UserDTO user) {
//        userService.addUser(user);
//        return user;
//    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
//    public User addUser(@RequestBody User user, @PathVariable(value = "id") Long userId) {
//        userService.updateUser(user);
//        return user;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}/companies")
    @ResponseBody
    public Long getCompanyList(@PathVariable(value = "id") Long userId) {
        System.out.println("at company list controller method");
//        List<UserCompanyResponseDTO> userCompanyResponseDTOList = new ArrayList<>();
//        userCompanyService.findByUserId(userId).forEach((data)->{
//            UserCompanyResponseDTO userCompany= new UserCompanyResponseDTO();
//            System.out.println(data.getUsersCompany().getSchema());
//            System.out.println(data.getUsersCompany().getName());
//            System.out.println(data.getUsersCompany().getId());
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
