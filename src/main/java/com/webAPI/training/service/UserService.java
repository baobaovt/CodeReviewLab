package com.webAPI.training.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.webAPI.training.Users;

public interface UserService {
 
    Users findById(long id);
 
    Users findByName(String name);
 
    void saveUser(Users user);
 
    void updateUser(Users user);
 
    void deleteUserById(long id);
 
    List<Users> findAllUsers();
 
    void deleteAllUsers();
 
    public boolean isUserExist(Users user);
}
