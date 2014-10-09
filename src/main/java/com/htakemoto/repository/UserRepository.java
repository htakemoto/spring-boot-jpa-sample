package com.htakemoto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htakemoto.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

    // No need to put query annotation if method name follows naming rule.
    // For details, check Spring Data JPA documentation
    List<User> findByLastname(String lastname);
}
