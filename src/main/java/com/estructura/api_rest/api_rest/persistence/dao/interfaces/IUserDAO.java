package com.estructura.api_rest.api_rest.persistence.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.estructura.api_rest.api_rest.persistence.entity.User;

public interface IUserDAO {

    List<User> findAll();

    Optional<User> findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

}//End Interface
