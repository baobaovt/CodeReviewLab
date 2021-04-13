package com.baonguyen2.InjectionVulnDemo;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.baonguyen2.InjectionVulnDemo.Model.User;
import com.baonguyen2.InjectionVulnDemo.Repo.UserRepo;


@SpringBootApplication
public class InjectionVulnDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(InjectionVulnDemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(UserRepo userRepo) {
        return (args) -> {


            userRepo.save(new User("Bao", "Nguyen", "baont", "baont", "HCMCity"));
            userRepo.save(new User("Minh", "HOCHI", "HCM", "hcm", "Chu Tich"));
            userRepo.save(new User("Bin", "Bin", "test", "tester", "Vuln-Tester"));
            userRepo.save(new User("admin", "instrator", "admin", "admin", "administrator"));
            userRepo.save(new User("Ghe", "Lo", "demoghe", "demoghe", "JustADemoGhe"));


        };
    }


}