package com.baonguyen2.DemoDatabase.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class User {
	/**
	 * 
	 */
	
	private String name;
	private Date dob;
	private int age;
	private String email;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	public User(){
	}
	public User(int id,String name, int age, Date dob, String email) {
		this.id =id;
		this.name =name;
		this.age =age;
		this.dob =dob;
		this.email =email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
}
