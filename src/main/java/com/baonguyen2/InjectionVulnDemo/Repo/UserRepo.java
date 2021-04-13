package com.baonguyen2.InjectionVulnDemo.Repo;


import org.springframework.data.repository.CrudRepository;

import com.baonguyen2.InjectionVulnDemo.Model.User;

public interface UserRepo extends CrudRepository<User, Long> {

}
