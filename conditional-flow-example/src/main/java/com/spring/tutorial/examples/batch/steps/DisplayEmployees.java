package com.spring.tutorial.examples.batch.steps;

import com.spring.tutorial.examples.batch.Entity.Employee;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayEmployees extends AbstractStep {

    // for the steps that don't use table in theirs sql queries we will use the AbstractStep class

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        String sql = "SELECT * FROM employee";
        List<Employee> employees = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Employee.class));
        employees.forEach(System.out::println);
        return RepeatStatus.FINISHED;
    }
}
