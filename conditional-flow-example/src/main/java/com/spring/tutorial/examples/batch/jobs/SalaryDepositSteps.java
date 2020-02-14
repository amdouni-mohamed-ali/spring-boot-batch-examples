package com.spring.tutorial.examples.batch.jobs;

import com.spring.tutorial.examples.batch.constants.AppConstants;
import com.spring.tutorial.examples.batch.listeners.StepListener;
import com.spring.tutorial.examples.batch.tasklets.*;
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

    @Bean
    protected Step createEmployeesTableStep(CreateEmployeeTable createEmployeeTable) {

        return stepBuilderFactory
                .get(AppConstants.CREATE_EMPLOYEES_TABLE)
                .tasklet(createEmployeeTable)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step populateEmployeesTableStep(PopulateEmployeeTable populateEmployeeTable) {

        return stepBuilderFactory
                .get(AppConstants.POPULATE_EMPLOYEES_TABLE)
                .tasklet(populateEmployeeTable)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step displayEmployeesStep(DisplayEmployees displayEmployees) {

        return stepBuilderFactory
                .get(AppConstants.DISPLAY_EMPLOYEES)
                .tasklet(displayEmployees)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step distributeBonusToEmployeesStep(DistributeBonusToEmployees distributeBonusToEmployees) {

        return stepBuilderFactory
                .get(AppConstants.DISTRIBUTE_BONUS_EMPLOYEES)
                .tasklet(distributeBonusToEmployees)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step giveBonusFortunateEmployeesStep(GiveBonusFortunateEmployees giveBonusFortunateEmployees) {

        return stepBuilderFactory
                .get(AppConstants.GIVE_FORTUNATE_BONUS)
                .tasklet(giveBonusFortunateEmployees)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step lookForFortunateEmployeesStep(LookForFortunateEmployees lookForFortunateEmployees) {

        return stepBuilderFactory
                .get(AppConstants.LOOK_FOR_FORTUNATE)
                .tasklet(lookForFortunateEmployees)
                .listener(stepListener)
                .build();
    }

    @Bean
    protected Step updateSalaryStep(UpdateSalary updateSalary) {

        return stepBuilderFactory
                .get(AppConstants.UPDATE_SALARY)
                .tasklet(updateSalary)
                .listener(stepListener)
                .build();
    }
}
