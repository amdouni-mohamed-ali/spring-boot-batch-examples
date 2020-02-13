package com.spring.tutorial.examples.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateSalary implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSalary.class);
    private final JdbcTemplate jdbcTemplate;

    public UpdateSalary(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        int rows = jdbcTemplate.update("UPDATE employee SET salary=RAND(70.0)");
        LOGGER.info("{} salaries has been updated", rows);
        return RepeatStatus.FINISHED;
    }
}
