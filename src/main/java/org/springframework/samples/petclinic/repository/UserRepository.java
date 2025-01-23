package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user) throws DataAccessException;

    User findByUsername(String username) throws DataAccessException;

    List<User> findAll() throws DataAccessException;;
}
