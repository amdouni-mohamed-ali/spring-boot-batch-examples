package com.spring.tutorial.examples.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {

        LOGGER.info("### Before JOB - {} {} : {}", jobExecution.getJobInstance().getInstanceId(), jobExecution.getJobInstance().getJobName(), jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("### After JOB - #{} : {} - {}", jobExecution.getJobInstance().getInstanceId(),
                jobExecution.getJobInstance().getJobName(), jobExecution.getStatus(), jobExecution.getExitStatus().getExitCode());
    }
}
