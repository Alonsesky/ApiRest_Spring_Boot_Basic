package com.estructura.api_rest.api_rest.persistence.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.estructura.api_rest.api_rest.persistence.dao.interfaces.IUserDAO;
import com.estructura.api_rest.api_rest.persistence.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }


}// End class
