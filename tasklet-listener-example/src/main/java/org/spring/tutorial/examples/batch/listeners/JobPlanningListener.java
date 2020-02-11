package org.spring.tutorial.examples.batch.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;


@Component
public class JobPlanningListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("before job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("after job");
    }
}
