package org.gfg.JBDL11_DigitalLibrary.service;

import org.gfg.JBDL11_DigitalLibrary.Request.UserCreationRequest;
import org.gfg.JBDL11_DigitalLibrary.Response.UserCreationResponse;
import org.gfg.JBDL11_DigitalLibrary.model.User;
import org.gfg.JBDL11_DigitalLibrary.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserCreationResponse createUser(UserCreationRequest request) {
        UserCreationResponse response = new UserCreationResponse();

        try {
            // Validate if user already exists by email
            User existingUser = userRepository.getUserByEmail(request.getUserEmail());
            if (existingUser != null) {
                response.setSuccess(false);
                response.setMessage("User with this email already exists!");
                response.setUser(null);
                return response;
            }

            // Create new user
            User newUser = new User();
            newUser.setUserName(request.getUserName());
            newUser.setUserEmail(request.getUserEmail());
            newUser.setUserPassword(encryptPasswordMD5(request.getUserPassword()));
            newUser.setUserPhone(request.getUserPhone());
            newUser.setUserAddress(request.getUserAddress());

            // Save to database
            userRepository.createUser(newUser);

            response.setSuccess(true);
            response.setMessage("User created successfully!");
            response.setUser(newUser);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating user: " + e.getMessage());
            response.setUser(null);
        }

        return response;
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }


    public static String encryptPasswordMD5(String password) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert the byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }


}
