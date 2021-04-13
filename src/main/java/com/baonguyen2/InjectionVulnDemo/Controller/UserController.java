package com.baonguyen2.InjectionVulnDemo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baonguyen2.InjectionVulnDemo.Repo.SearchRepo;
import com.baonguyen2.InjectionVulnDemo.Repo.UserRepo;


@Controller
public class UserController {

    @Autowired
    SearchRepo searchRepo;
    


    @GetMapping("/users")
    public String allUsers(Model model) {
        return "users";
    }

    @PostMapping("/users")
    public String searchSubmit(Model model, @RequestParam String username) {
        model.addAttribute("users", searchRepo.findUsersByUsername(username));
        return "users";
    }



}