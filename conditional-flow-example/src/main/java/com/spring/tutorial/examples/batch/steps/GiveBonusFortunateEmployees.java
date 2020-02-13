package com.spring.tutorial.examples.batch.steps;

import com.spring.tutorial.examples.batch.Entity.Employee;
import com.spring.tutorial.examples.batch.constants.AppConstants;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Component
public class GiveBonusFortunateEmployees extends AbstractStep {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        // get fortunate employees
        int currentMont = LocalDate.now().getMonthValue();
        String monthToUse = currentMont < 10 ? "0" + currentMont : "" + currentMont;
        String query = "SELECT * FROM employee WHERE birthDate like %?%";
        List<Employee> employees = getJdbcTemplate().query(
                query,
                new Object[]{monthToUse},
                new BeanPropertyRowMapper<>(Employee.class)
        );
        employees.forEach(employee -> logger.debug("Fortunate employee : {}", employee));

        Assert.notEmpty(employees, "fortunate employees list cannot be empty");
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
