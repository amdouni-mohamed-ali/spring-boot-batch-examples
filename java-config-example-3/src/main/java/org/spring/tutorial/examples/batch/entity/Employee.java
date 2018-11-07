package org.spring.tutorial.examples.batch.entity;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class Employee implements Serializable {

    @CsvBindByPosition(position = 0)
    private Long empId;
    @CsvBindByPosition(position = 1)
    private String firstName;
    @CsvBindByPosition(position = 2)
    private String lastName;
    @CsvBindByPosition(position = 3)
    private Character sex;
    @CsvBindByPosition(position = 4)
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

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", salary=" + salary +
                '}';
    }
}
