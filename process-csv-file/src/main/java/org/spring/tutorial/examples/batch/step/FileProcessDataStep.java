package org.spring.tutorial.examples.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.config.SpecialEmployees;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.entity.Employee;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FileProcessDataStep implements Tasklet, StepExecutionListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderStep.class);
    private Map<String, String[]> entryRecords;
    private List<Employee> employees;
    private SpecialEmployees specialEmployees;

    public FileProcessDataStep(SpecialEmployees specialEmployees) {
        this.specialEmployees = specialEmployees;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("start the process data step ...");
        LOGGER.debug("get the data from the context");
        employees = new ArrayList<>();
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        entryRecords = (Map<String, String[]>) context.get(ApplicationConstants.INPUT_FILE);
        LOGGER.debug("the last operation was ended successfully");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ApplicationConstants.EMPLOYEES, employees);
        LOGGER.debug("employees was successfully saved to the job context");
        LOGGER.info("Process data step ended successfully");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        LOGGER.debug("start process the data");
        entryRecords.forEach((id, record) -> {

            boolean special = false;
            if (specialEmployees.getSpecialEmployees().contains(id))
                special = true;
            Employee e = mapRecord(record, special);
            employees.add(e);
        });
        if (LOGGER.isTraceEnabled())
            employees.forEach(e -> LOGGER.trace("{}", e));
        LOGGER.debug("end process data");
        return RepeatStatus.FINISHED;
    }

    private Employee mapRecord(String[] record, boolean special) {

        Employee e = new Employee()
                .setEmpId(Long.valueOf(record[3]))
                .setFirstName(record[4])
                .setLastName(record[5].toUpperCase())
                .setSex((record[6].charAt(0)))
                .setSalary(Double.valueOf(record[7]));
        if (special)
            e.setSalary(e.getSalary() + 25.0);
        return e;
    }
}
