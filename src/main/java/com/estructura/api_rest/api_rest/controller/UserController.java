package com.estructura.api_rest.api_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estructura.api_rest.api_rest.persistence.entity.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        return null;
    }
}//End class
