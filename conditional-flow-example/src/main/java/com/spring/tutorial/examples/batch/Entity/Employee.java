package com.spring.tutorial.examples.batch.Entity;

import java.io.Serializable;

public class Employee implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Character sex;
    private Double salary;
    private String birthDate;
    private String entryDate;
    private String releaseDate;

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Character getSex() {
        return sex;
    }

    public Employee setSex(Character sex) {
        this.sex = sex;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Employee setSalary(Double salary) {
        this.salary = salary;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Employee setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public Employee setEntryDate(String entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Employee setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", salary=" + salary +
                ", birthDate='" + birthDate + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
