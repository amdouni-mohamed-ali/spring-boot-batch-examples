package com.spring.tutorial.examples.batch.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LookForFortunateEmployees extends AbstractStep {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        int currentMont = LocalDate.now().getMonthValue();
        String monthToUse = currentMont < 10 ? "0" + currentMont : "" + currentMont;
        String query = "SELECT distinct 1 FROM employee WHERE birthDate  LIKE '%' || ? || '%'";
        String result = getJdbcTemplate().queryForObject(
                query,
                new Object[]{monthToUse},
                String.class
        );
        if (!"1".equals(result))
            throw new RuntimeException("There is no fortunate employee(s) this month");
        return RepeatStatus.FINISHED;
    }
}
