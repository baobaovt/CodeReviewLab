package com.baonguyen2.DemoDatabase.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baonguyen2.DemoDatabase.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
}
