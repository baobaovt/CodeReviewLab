package com.webAPI.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
import com.webAPI.training.Users;
import com.webAPI.training.service.UserService;
 
@RestController
public class UserRestController {
 
    @Autowired
    UserService userService;
 
    // Retrieve all users
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }
 
    // Retrieve user by id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Users user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
 
    // Create a new user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    // Update a User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
        System.out.println("Updating User " + id);
 
        Users currentUser = userService.findById(id);
 
        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.updateUser(currentUser);
        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
    }
 
    // Delete a User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Users user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
 
    // Delete All User
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
}
