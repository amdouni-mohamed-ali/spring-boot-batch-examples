package org.spring.tutorial.examples.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepListener extends StepExecutionListenerSupport {


    @Override
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("before step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println("after step");
        return stepExecution.getExitStatus();
    }

}
