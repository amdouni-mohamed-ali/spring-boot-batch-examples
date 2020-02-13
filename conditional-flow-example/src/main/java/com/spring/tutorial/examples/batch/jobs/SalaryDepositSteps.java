package com.spring.tutorial.examples.batch.jobs;

import com.spring.tutorial.examples.batch.constants.AppConstants;
import com.spring.tutorial.examples.batch.listeners.StepListener;
import com.spring.tutorial.examples.batch.steps.CheckEmployeeTableExistence;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalaryDepositSteps {

    private final StepBuilderFactory stepBuilderFactory;
    private final StepListener stepListener;

    public SalaryDepositSteps(StepBuilderFactory stepBuilderFactory, StepListener stepListener) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.stepListener = stepListener;
    }

    @Bean
    protected Step checkEmployeeTableExistenceStep(CheckEmployeeTableExistence checkEmployeeTableExistence) {

        return stepBuilderFactory
                .get(AppConstants.CHECK_EMPLOYEE_TABLE_EXISTENCE)
                .tasklet(checkEmployeeTableExistence)
                .listener(stepListener)
                .build();
    }
}
