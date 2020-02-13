package com.spring.tutorial.examples.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CheckEmployeeTableExistence implements Tasklet {

    private final JdbcTemplate jdbcTemplate;

    public CheckEmployeeTableExistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        String found = jdbcTemplate.queryForObject(
                "select count(*) from information_schema.tables where table_name = ?",
                new Object[]{"employee"},
                String.class
        );
        Assert.hasLength(found, "Employee table does not exist");
        Assert.doesNotContain(found, "0", "Employee table does not exist");
        return RepeatStatus.FINISHED;
    }
}
