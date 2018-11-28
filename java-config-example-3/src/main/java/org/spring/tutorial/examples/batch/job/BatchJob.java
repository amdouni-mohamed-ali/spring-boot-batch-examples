package org.spring.tutorial.examples.batch.job;

import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.step.FileProcessDataStep;
import org.spring.tutorial.examples.batch.step.FileReaderStep;
import org.spring.tutorial.examples.batch.step.FileWriteDataStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJob {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private FileReaderStep fileReaderStep;
    private FileProcessDataStep fileProcessDataStep;
    private FileWriteDataStep fileWriteDataStep;

    public BatchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, FileReaderStep fileReaderStep, FileProcessDataStep fileProcessDataStep, FileWriteDataStep fileWriteDataStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.fileReaderStep = fileReaderStep;
        this.fileProcessDataStep = fileProcessDataStep;
        this.fileWriteDataStep = fileWriteDataStep;
    }

    @Bean
    protected Step readFile() {

        return stepBuilderFactory
                .get(ApplicationConstants.FILE_READER_STEP)
                .tasklet(fileReaderStep)
                .build();
    }

    @Bean
    protected Step processData() {

        return stepBuilderFactory
                .get(ApplicationConstants.PROCESS_DATA_STEP)
                .tasklet(fileProcessDataStep)
                .build();
    }

    @Bean
    protected Step writeData() {

        return stepBuilderFactory
                .get(ApplicationConstants.WRITIE_DATA_STEP)
                .tasklet(fileWriteDataStep)
                .build();
    }

    @Bean
    protected Job job() {

        return jobBuilderFactory
                .get(ApplicationConstants.JOB_NAME)
                .start(readFile())
                .next(processData())
                .next(writeData())
                .build();
    }
}
