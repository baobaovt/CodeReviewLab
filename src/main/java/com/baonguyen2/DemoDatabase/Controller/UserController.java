package com.baonguyen2.DemoDatabase.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.baonguyen2.DemoDatabase.Model.User;
import com.baonguyen2.DemoDatabase.Service.UserService;



@RestController
public class UserController {  
	@Autowired
    private UserService service;
	
	
	@GetMapping("/users")
	public List<User> list() {
	    return service.listAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id){
		try {
			User user = service.get(id);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		}
		
	}
	
	
  
}