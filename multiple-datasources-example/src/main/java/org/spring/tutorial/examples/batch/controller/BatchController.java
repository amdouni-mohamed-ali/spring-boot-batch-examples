package org.spring.tutorial.examples.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchController.class);
    private final JobLauncher jobLauncher;
    private final Job job;
    // In the case you have more than one job use its name. for example, in my case it's gonna be processDataJob

    public BatchController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public ResponseEntity<String> runAction(@RequestParam String action) {

        LOGGER.info("Received action is {}", action);
        Assert.hasLength(action, "The action must not be empty");

        if (action.equalsIgnoreCase("start")) {

            try {

                JobExecution jobExecution = this.run();
                return new ResponseEntity<>(
                        String.format("%s", jobExecution.getStatus())
                        , HttpStatus.OK
                );
            } catch (Exception e) {

                LOGGER.error("Error has occurred while trying to run the job ", e);
                return new ResponseEntity<>("An error has occurred after running the batch", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {

            return new ResponseEntity<>("Not yet implemented", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    private JobExecution run() throws Exception {
        JobParameters jobParameters =
                new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                        .toJobParameters();
        return jobLauncher.run(job, jobParameters);
    }
}
