package com.baonguyen2.DemoDatabase.Controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.baonguyen2.DemoDatabase.Model.User;
import com.baonguyen2.DemoDatabase.Service.UserService;



@RestController
public class UserController {  
	@Autowired
    private UserService service;
	
    private static final Logger logger = LogManager.getLogger("HelloWorld");

	@GetMapping("/log4j")
    public String index(@RequestHeader("X-Api-Version") String apiVersion) {
        logger.info("Received a request for API version " + apiVersion);
        return "Hello, world!";
    }
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