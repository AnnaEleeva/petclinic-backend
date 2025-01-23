package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User findUserByName(String name);

    List<User> findAllUsers();
}
