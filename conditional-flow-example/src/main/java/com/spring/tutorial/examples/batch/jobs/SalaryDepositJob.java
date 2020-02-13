package com.spring.tutorial.examples.batch.jobs;

import com.spring.tutorial.examples.batch.constants.BatchObjectsNames;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalaryDepositJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SalaryDepositJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    protected Job job(
            Step checkEmployeeTableExistenceStep
    ) {

        return jobBuilderFactory
                .get(BatchObjectsNames.JOB_NAME)
                .start(checkEmployeeTableExistenceStep)
                .build();
    }
}
