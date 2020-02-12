package org.spring.tutorial.examples.batch.config;


import org.spring.tutorial.examples.batch.domain.User;
import org.spring.tutorial.examples.batch.processors.DataSourceProcessor;
import org.spring.tutorial.examples.batch.readers.DataSourceReader;
import org.spring.tutorial.examples.batch.writers.DataSourceWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BatchJobsConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSourceProcessor dataSourceProcessor;

    @Autowired
    public DataSourceReader dataSourceReader;

    @Autowired
    public DataSourceWriter dataSourceWriter;

    @Bean
    public Job processDataJob() {
        return jobBuilderFactory.get("processDataJob")
                .flow(toUpperCaseStep())
                .end()
                .build();
    }

    @Bean
    public Step toUpperCaseStep() {
        return stepBuilderFactory.get("toUpperCaseStep")
                .<User, String> chunk(1)
                .reader(dataSourceReader)
                .processor(dataSourceProcessor)
                .writer(dataSourceWriter)
                .build();
    }
}
