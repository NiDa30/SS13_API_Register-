package com.example.HelloSpringSecurity.model;

public class Employee {
    private Long id;
    private String fullName;
    private Double salary;

    // Default constructor
    public Employee() {
    }

    // All-args constructor
    public Employee(Long id, String fullName, Double salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
