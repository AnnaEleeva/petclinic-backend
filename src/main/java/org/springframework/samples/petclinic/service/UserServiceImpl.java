package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Role;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) {

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have at least a role set!");
        }

        for (Role role : user.getRoles()) {
            if (!role.getName().startsWith("ROLE_")) {
                role.setName("ROLE_" + role.getName());
            }

            if (role.getUser() == null) {
                role.setUser(user);
            }
        }

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByName(String name) {
        User user;
        try {
            user = userRepository.findByUsername(name);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
