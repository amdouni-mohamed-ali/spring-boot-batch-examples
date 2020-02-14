package org.spring.tutorial.examples.batch;


import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        ConfigurableApplicationContext context = SpringApplication.run(BatchApplication.class, args);

        // This code is not really necessary as spring boot will run automatically the configured job
        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job processJob = (Job) context.getBean("processDataJob");
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        JobExecution execution = jobLauncher.run(processJob, jobParameters);
        System.out.println("Exit Status : " + execution.getStatus());

        context.close();
    }
}
