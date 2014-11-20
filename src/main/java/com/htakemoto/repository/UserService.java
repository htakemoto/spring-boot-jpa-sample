package com.htakemoto.repository;

import java.util.List;

import com.htakemoto.domain.User;

public interface UserService {
    
    User save(User user);
    List<User> findAll();
    User findOne(long userId);
    List<User> findByFirstnameStartingWith(String firstname);
    User update(User user);
    User delete(long user);
}
