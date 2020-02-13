package com.spring.tutorial.examples.batch.jobs;

import com.spring.tutorial.examples.batch.constants.AppConstants;
import com.spring.tutorial.examples.batch.listeners.JobListener;
import org.springframework.batch.core.ExitStatus;
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
    private final JobListener jobListener;

    public SalaryDepositJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, JobListener jobListener) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobListener = jobListener;
    }

    @Bean
    protected Job job(
            Step checkEmployeeTableExistenceStep,
            Step createEmployeesTableStep
    ) {

        return jobBuilderFactory
                .get(AppConstants.JOB_NAME)
                .listener(jobListener)

                .flow(checkEmployeeTableExistenceStep)
                .on(ExitStatus.FAILED.getExitCode())
                .to(createEmployeesTableStep)

                .end()
                .build();
    }
}
