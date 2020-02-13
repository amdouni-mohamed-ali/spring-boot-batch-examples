package com.spring.tutorial.examples.batch.steps;

import com.spring.tutorial.examples.batch.Entity.Employee;
import com.spring.tutorial.examples.batch.constants.AppConstants;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class DistributeBonusToEmployees extends AbstractStep {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        // get all employees
        String query = "SELECT * FROM employee";
        List<Employee> employees = getJdbcTemplate().query(
                query,
                new BeanPropertyRowMapper<>(Employee.class)
        );

        Assert.notEmpty(employees, "the list of employees cannot be empty");
        double bonus = AppConstants.BONUS / employees.size();

        // update salary
        employees.forEach(employee ->
                getJdbcTemplate().update(
                        "UPDATE employee SET salary=? WHERE id=?",
                        new Object[]{
                                employee.getSalary() + bonus,
                                employee.getId()
                        }
                )
        );

        return RepeatStatus.FINISHED;
    }
}
