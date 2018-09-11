package com.journal.app.models.services.impl;

import com.journal.app.exceptions.DataNotFoundException;
import com.journal.app.exceptions.UserAlreadyExistsException;
import com.journal.app.models.DTO.MapDTO;
import com.journal.app.models.DTO.PageResponseDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.auth.RegisterRequestDTO;
import com.journal.app.models.DTO.users.UserRequestDTO;
import com.journal.app.models.DTO.users.UserResponseDTO;
import com.journal.app.models.domain.User;
import com.journal.app.models.repositories.UserRepository;
import com.journal.app.models.services.UserService;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Value("${app.crypto.salt}")
    private String salt;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO addUser(RegisterRequestDTO registerRequestDTO) {
        User user = userRepository.findByEmail(registerRequestDTO.getEmail().toLowerCase());

        if (user != null) {
            throw new UserAlreadyExistsException("User already exists with this email: " + registerRequestDTO.getEmail());
        }

        user = userRepository.findByUsername(registerRequestDTO.getUsername().toLowerCase());

        if (user != null) {
            throw new UserAlreadyExistsException("User already exists with this username: " + registerRequestDTO.getUsername());
        }
        User newUser = modelMapper.map(registerRequestDTO, User.class);
        newUser.setPassword(BCrypt.hashpw(registerRequestDTO.getPassword(), salt));
        newUser.setLoginAccess(true);
        newUser.setEnabled(true);
        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserResponseDTO.class);

    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO getUser(long id) {
//        return userRepository.findOne(id);
        return null;
    }

    @Override
    public PageResponseDTO<UserResponseDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO get(String username, String password) {
        LOGGER.info("getting user: " + username);
        User user = userRepository.findByUserAndPassword(username, password);
        if (user == null) {
            throw new DataNotFoundException("Invalid username or password.");
        } else {
            return MapDTO.userToUserDTO(user);
        }

    }

    @Override
    public UserDTO getByUsername(String username) {
        try {
            return MapDTO.userToUserDTO(userRepository.findByUsername(username));
        } catch (NullPointerException e) {
            LOGGER.warning("User not found");
            return null;
        }
    }

    @Override
    public UserDTO getByEmail(String email) {
        try {
            return MapDTO.userToUserDTO(userRepository.findByEmail(email));
        } catch (NullPointerException e) {
            LOGGER.warning("User not found");
            return null;
        }
    }

    public static Boolean isTableEmpty(Session session) {
        boolean b;
        Long total = (Long) session
                .createQuery("select count(c) from User c")
                .getSingleResult();
        b = total <= 0;
        return b;
    }


}
