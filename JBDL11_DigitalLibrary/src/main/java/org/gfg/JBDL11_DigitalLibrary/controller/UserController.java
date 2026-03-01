package org.gfg.JBDL11_DigitalLibrary.controller;

import org.gfg.JBDL11_DigitalLibrary.Request.UserCreationRequest;
import org.gfg.JBDL11_DigitalLibrary.Response.UserCreationResponse;
import org.gfg.JBDL11_DigitalLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user account
    @PostMapping("/create")
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest request) {
        UserCreationResponse response = userService.createUser(request);
        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

