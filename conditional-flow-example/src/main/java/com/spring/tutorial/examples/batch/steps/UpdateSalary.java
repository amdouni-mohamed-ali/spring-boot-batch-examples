package com.spring.tutorial.examples.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class UpdateSalary extends AbstractStep {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        // the salary will be a random number between 0 and 100
        int rows = getJdbcTemplate().update("UPDATE employee SET salary=FLOOR(RAND()*100)");
        logger.info("{} salaries has been updated", rows);
        return RepeatStatus.FINISHED;
    }
}
