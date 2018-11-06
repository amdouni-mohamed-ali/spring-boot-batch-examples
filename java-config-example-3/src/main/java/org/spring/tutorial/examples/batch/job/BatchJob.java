package org.spring.tutorial.examples.batch.job;

import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.step.FileReaderStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchJob {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private FileReaderStep fileReaderStep;

    public BatchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, FileReaderStep fileReaderStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.fileReaderStep = fileReaderStep;
    }

    @Bean
    protected Step readFile() {

        return stepBuilderFactory
                .get(ApplicationConstants.FILE_READER_STEP)
                .tasklet(fileReaderStep)
                .build();
    }

    @Bean
    protected Job job() {

        return jobBuilderFactory
                .get(ApplicationConstants.JOB_NAME)
                .start(readFile())
                .build();
    }
}
