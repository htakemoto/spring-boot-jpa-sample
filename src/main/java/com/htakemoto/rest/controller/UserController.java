package com.htakemoto.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.htakemoto.domain.Item;
import com.htakemoto.domain.User;
import com.htakemoto.repository.ItemRepository;
import com.htakemoto.repository.ItemService;
import com.htakemoto.repository.UserRepository;
import com.htakemoto.repository.UserService;
import com.htakemoto.rest.domain.UserResponse;
import com.htakemoto.service.exception.ItemAlreadyExistsException;
import com.htakemoto.service.exception.NoItemExistsException;
import com.htakemoto.service.exception.NoUserExistsException;
import com.htakemoto.service.exception.UserAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;
	private final ItemService itemService;
	
	@Autowired
	protected ModelMapper pojoMapper;
	
    @Inject
    public UserController(final UserService userService, final ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }
	
	/*
	 *  User Controller
	 */
	
	// POST: http://localhost:8080/users
	// Content-Type: application/json
	// Payload: { "firstname": "Steve", "lastname": "Jobs" }
	@RequestMapping(value="", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
		LOGGER.debug("Received request to create the {}", user);
		return userService.save(user);
    }
	
	// GET: http://localhost:8080/users
	@RequestMapping(value="", method=RequestMethod.GET)
    public List<UserResponse> getUsers() {
		LOGGER.debug("Received request to list all users");
        List<User> users = new ArrayList<User>();
		users = (List<User>) userService.findAll();
		// convert User to UserResponse
		List<UserResponse> usersResponse = new ArrayList<UserResponse>();
		for (User user : users) {
			usersResponse.add(pojoMapper.map(user, UserResponse.class));
		}
        return usersResponse;
    }

	// GET: http://localhost:8080/users/1
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public User getUser(@PathVariable long userId) {
		return userService.findOne(userId);
    }
	
	// PUT: http://localhost:8080/users/1
	// Content-Type: application/json
	// Payload: { "firstname": "Modified", "lastname": "Name" }
	@RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    public User updateUser(@PathVariable long userId, @RequestBody User user) {
		user.setUserId(userId);
		return userService.update(user);
    }
	
	// DELETE: http://localhost:8080/users/1
	@RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable long userId) {
		userService.delete(userId);
		return userId + " is Deleted";
    }
	
	
	/*
	 *  Item Controller
	 */
	
	// POST: http://localhost:8080/users/1/items
	// Content-Type: application/json
	// Payload: [{ "itemName": "Java Tutorial Book", "quantity": 3 },{ "itemName": "Concert Tickets", "quantity": 2 }]
	@RequestMapping(value="/{userId}/items", method=RequestMethod.POST)
    public List<Item> createUserItems(@PathVariable Long userId, @RequestBody List<Item> items) {
		return (List<Item>) itemService.save(items, userId);
    }
	
	// GET: http://localhost:8080/users/1/items
	@RequestMapping(value="/{userId}/items", method=RequestMethod.GET)
    public List<Item> getUserItems(@PathVariable Long userId) {
        return itemService.findByUserId(userId);
    }
	
	// PUT: http://localhost:8080/users/1/items/1
	// Content-Type: application/json
	// Payload: { "itemName": "C Tutorial Book", "quantity": 3 }
	@RequestMapping(value="/{userId}/items/{itemId}", method=RequestMethod.PUT)
    public Item updateUserItems(@PathVariable long userId, @PathVariable long itemId, @RequestBody Item item) {
		return itemService.update(item, itemId, userId);
    }
	
	// DELETE: http://localhost:8080/users/1/items/1
	@RequestMapping(value="/{userId}/items/{itemId}", method=RequestMethod.DELETE)
    public String deleteUserItem(@PathVariable long userId, @PathVariable long itemId) {
		itemService.delete(itemId, userId);
		return itemId + " is Deleted";
    }
	
	
	/*
	 *  Exception Handlers
	 */
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public String handleNonExistingUserException(NoUserExistsException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleItemAlreadyExistsException(ItemAlreadyExistsException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public String handleNonExistingItemException(NoItemExistsException e) {
        return e.getMessage();
    }
}