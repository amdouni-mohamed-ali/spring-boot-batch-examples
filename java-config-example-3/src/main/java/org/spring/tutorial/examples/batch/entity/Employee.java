package org.spring.tutorial.examples.batch.entity;

public class Employee {

    private Long empId;
    private String firstName;
    private String lastName;
    private Character sex;
    private Double salary;

    public Long getEmpId() {
        return empId;
    }

    public Employee setEmpId(Long empId) {
        this.empId = empId;
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
}
