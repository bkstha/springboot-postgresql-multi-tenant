package com.journal.app.models.services;

import com.journal.app.models.DTO.PageResponseDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.auth.RegisterRequestDTO;
import com.journal.app.models.DTO.users.UserRequestDTO;
import com.journal.app.models.DTO.users.UserResponseDTO;



public interface UserService {
//    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
//    @Autowired
//    private UserRepository userRepository;

//    //returns all users
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        userRepository.findAll().forEach(users::add);
//        return users;
//    }


    //add user
    UserResponseDTO addUser(UserRequestDTO userRequestDTO);

    //add user from register form
    UserResponseDTO addUser(RegisterRequestDTO registerRequestDTO);

    //delete user
    void deleteUser(long id);

    //update user
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);

    //get user
    UserResponseDTO getUser(long id);

    //get user list
    PageResponseDTO<UserResponseDTO> getUsers();

    //get user by username and password
    UserDTO get(String username, String password);

    //checks if table empty
//    Boolean isTableEmpty(Session session);

    UserDTO getByUsername(String username);

    UserDTO getByEmail(String email);



}

