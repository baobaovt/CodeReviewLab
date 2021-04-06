package com.webAPI.training;

public class Users {
    private long id;
    private String name;
    private int age;
    private double salary;
 
    public Users() {
        id = 0;
    }
 
    public Users(long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public double getSalary() {
        return salary;
    }
 
    public void setSalary(double salary) {
        this.salary = salary;
    }
 
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
}

