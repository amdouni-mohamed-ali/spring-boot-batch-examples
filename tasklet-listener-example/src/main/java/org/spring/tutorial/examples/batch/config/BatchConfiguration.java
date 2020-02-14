package org.spring.tutorial.examples.batch.config;

import org.spring.tutorial.examples.batch.listeners.JobPlanningListener;
import org.spring.tutorial.examples.batch.listeners.StepListener;
import org.spring.tutorial.examples.batch.tasklets.ToUpperCaseTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobPlanningListener jobPlanningListener;
    @Autowired
    private StepListener stepListener;
    @Autowired
    private ToUpperCaseTasklet toUpperCaseTasklet;

    @Bean
    public Job processDataJob() {
        return jobBuilderFactory.get("processDataJob")
                .listener(jobPlanningListener)
                .flow(toUpperCaseStep())
                .end()
                .build();
    }

    @Bean
    public Step toUpperCaseStep() {
        return stepBuilderFactory.get("toUpperCaseStep")
                .tasklet(toUpperCaseTasklet)
                .listener(stepListener)
                .build();
    }
}
