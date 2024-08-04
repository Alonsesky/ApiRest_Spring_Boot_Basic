package com.estructura.api_rest.api_rest.services.interfaces;

import java.util.List;

import com.estructura.api_rest.api_rest.persistence.entity.dto.UserDTO;

public interface IUserService {
    
    List<UserDTO> findAll();
    
    UserDTO findById(Long id);
    
    UserDTO createUser(UserDTO userDTO);
    
    UserDTO updateUser(Long id, UserDTO userDTO);
    
    UserDTO deleteUser(Long id);
}
