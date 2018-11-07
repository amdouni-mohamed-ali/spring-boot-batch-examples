package org.spring.tutorial.examples.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.service.IOService;
import org.spring.tutorial.examples.batch.utils.CSVUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class FileReaderStep implements Tasklet, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderStep.class);
    private Map<String, String[]> entryRecords;
    private IOService ioService;

    public FileReaderStep(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        LOGGER.info("start the FileReader step ...");
        entryRecords = new LinkedHashMap<>();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ApplicationConstants.INPUT_FILE, entryRecords);
        LOGGER.debug("entry file was successfully saved to the batch context");
        LOGGER.info("FileReader step ended successfully");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {


        LOGGER.debug("Start reading the input file from {}", ioService.getInputFile().toString());
        LOGGER.trace("received data");
        CSVUtils.readCsvLines(ioService.getInputFile()).forEach(
                line -> {

                    entryRecords.put(line[3], line); //line[3] is the employee id
                    if (LOGGER.isTraceEnabled()) {
                        StringBuilder record = new StringBuilder();
                        Arrays.asList(line).forEach(s -> record.append(s).append(" | "));
                        LOGGER.trace(record.toString());
                    }
                }
        );
        LOGGER.debug("finish reading the input file");
        return RepeatStatus.FINISHED;
    }
}
