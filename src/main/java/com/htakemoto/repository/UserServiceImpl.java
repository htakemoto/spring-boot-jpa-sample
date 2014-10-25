package com.htakemoto.repository;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.htakemoto.domain.User;
import com.htakemoto.service.exception.NoUserExistsException;
import com.htakemoto.service.exception.UserAlreadyExistsException;

@Service
@Validated
public class UserServiceImpl implements UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        if (userRepository.exists(user.getUserId())) {
            throw new UserAlreadyExistsException (
                    String.format("There already exists a user with id=%s", user.getUserId()));
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        LOGGER.debug("Retrieving the list of all users");
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(long id) {
        LOGGER.debug("Retrieving a user by user id={}", id);
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NoUserExistsException(
                    String.format("No user exists with id=%d", id));
        }
        return user;
    }

    @Override
    @Transactional
    public User update(@NotNull @Valid final User user) {
        LOGGER.debug("Updating {}", user);
        User existing = userRepository.findOne(user.getUserId());
        if (existing == null) {
            throw new NoUserExistsException(
                    String.format("No user exists with id=%s", user.getUserId()));
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User delete(@NotNull @Valid final long userId) {
        LOGGER.debug("Deleting {}", userId);
        User existing = userRepository.findOne(userId);
        if (existing == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%s", userId));
        }
        userRepository.delete(userId);
        return existing;
    }
}
