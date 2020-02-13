package com.spring.tutorial.examples.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepListener extends StepExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {

        LOGGER.info(String.format("### Before Step - JOB #%s STEP #%s %s : %s", stepExecution.getJobExecution().getJobInstance().getInstanceId(),
                stepExecution.getId(), stepExecution.getStepName(), stepExecution.getStatus()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.info(String.format("### After Step - JOB #%s STEP #%s %s : %s - %s", stepExecution.getJobExecution().getJobInstance().getInstanceId(),
                stepExecution.getId(), stepExecution.getStepName(), stepExecution.getStatus(), stepExecution.getExitStatus().getExitCode()));

        return stepExecution.getExitStatus();
    }

}
