package com.elte.synchome.service;

import com.elte.synchome.entity.User;
import com.elte.synchome.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> saveUser(User user) {
        if(!isUsernameUnique(user)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Choose another username, this one is used!");
        }
        if(!isEmailUnique(user)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Choose another email, this one is used!");
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    private boolean isEmailUnique(final User user) {
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithSameEmail != null) {
            logger.info("There is another user that has the email: {0}", user.getEmail());
            return false;
        }
        return true;
    }
    private boolean isUsernameUnique(final User user){
        User userWithSameUsername = userRepository.findByUsername(user.getUsername());
        if (userWithSameUsername != null) {
            logger.info("There is another user that has the username: {0}", user.getUsername());
            return false;
        }
        return true;
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(String id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            userRepository.save(existingUser);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<String> authenticateUser(String enteredUsername, String enteredPassword) {
        User user = userRepository.findByUsername(enteredUsername);
        if (user == null) {
            return handleUserNotFound();
        }
        if (isPasswordValid(user, enteredPassword)) {
            return handleAuthenticationSuccess();
        } else {
            return handleAuthenticationFailure();
        }
    }
    private boolean isPasswordValid(User user, String enteredPassword) {
        return enteredPassword.equals(user.getPasswordHash());
    }

    private ResponseEntity<String> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials. Please check your username.");
    }

    private ResponseEntity<String> handleAuthenticationSuccess() {
        return ResponseEntity.status(HttpStatus.OK).body("Authentication successful. Welcome!");
    }

    public ResponseEntity<String> handleAuthenticationFailure() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password. Please try again.");
    }
}
