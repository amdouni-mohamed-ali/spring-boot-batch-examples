package org.spring.tutorial.examples.batch.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public abstract class AbstractStep implements Tasklet, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStep.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {

        LOGGER.info(" ### start the {} step ...", getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.info(" ### {} step ended successfully", getStepName());
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        this.executeJob(stepContribution, chunkContext);
        return RepeatStatus.FINISHED;
    }

    protected abstract void executeJob(StepContribution stepContribution, ChunkContext chunkContext);

    protected abstract String getStepName();
}
