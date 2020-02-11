package org.spring.tutorial.examples.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.entity.Employee;
import org.spring.tutorial.examples.batch.service.IOService;
import org.spring.tutorial.examples.batch.utils.CSVUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileWriteDataStep implements Tasklet, StepExecutionListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriteDataStep.class);
    private List<Employee> employees;
    private IOService ioService;

    public FileWriteDataStep(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        LOGGER.info("start the write data to a file step ...");
        LOGGER.debug("get the data from the context");
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        employees = (List<Employee>) context.get(ApplicationConstants.EMPLOYEES);
        LOGGER.debug("the last operation was ended successfully");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.info("Write the generated file successfully ended");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LOGGER.trace("generated file");
        if (LOGGER.isTraceEnabled()) {
            employees.forEach(e -> LOGGER.trace(e.toString()));
        }
        LOGGER.debug("start writing data");
        CSVUtils.writeCsvLine(ioService.getOutputFile(), employees);
        LOGGER.debug("end writing data");
        return RepeatStatus.FINISHED;
    }
}
