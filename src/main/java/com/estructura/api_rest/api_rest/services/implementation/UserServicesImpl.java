package com.estructura.api_rest.api_rest.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estructura.api_rest.api_rest.exception.UserNotFoundException;
import com.estructura.api_rest.api_rest.persistence.dao.interfaces.IUserDAO;
import com.estructura.api_rest.api_rest.persistence.entity.User;
import com.estructura.api_rest.api_rest.persistence.entity.dto.UserDTO;
import com.estructura.api_rest.api_rest.services.interfaces.IUserService;

@Service
public class UserServicesImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        logger.info("Fetching all users");
        return userDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userDAO.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Creating new user");
        User user = convertToEntity(userDTO);
        userDAO.saveUser(user);
        return convertToDTO(user);
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.info("Updating user with id: {}", id);
        return userDAO.findById(id)
                .map(user -> {
                    user.setName(userDTO.getName());
                    user.setLastName(userDTO.getLastName());
                    userDAO.updateUser(user);
                    return convertToDTO(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Transactional
    @Override
    public UserDTO deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        return userDAO.findById(id)
                .map(user -> {
                    userDAO.deleteUser(user);
                    return convertToDTO(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}