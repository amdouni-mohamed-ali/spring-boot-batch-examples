package org.spring.tutorial.examples.batch.job;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.spring.tutorial.examples.batch.BatchApplication;
import org.spring.tutorial.examples.batch.config.SpecialEmployees;
import org.spring.tutorial.examples.batch.service.IOService;
import org.spring.tutorial.examples.batch.step.FileProcessDataStep;
import org.spring.tutorial.examples.batch.step.FileReaderStep;
import org.spring.tutorial.examples.batch.step.FileWriteDataStep;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchApplication.class, JobLauncherTestUtils.class})
public class BatchJobTest {

    public static final String INPUT_FILE = "target/test-classes/job/input/input_example";
    public static final String OUTPUT_FILE = "target/test-classes/job/output/output";
    public static final String EXPECTED_FILE = "target/test-classes/job/expected/output-example";
    @MockBean
    IOService ioService;
    @MockBean
    SpecialEmployees specialEmployees;

    @Autowired
    @InjectMocks
    FileReaderStep fileReaderStep;
    @Autowired
    @InjectMocks
    FileWriteDataStep fileWriteDataStep;
    @Autowired
    @InjectMocks
    FileProcessDataStep fileProcessDataStep;

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Before
    public void setUp() {

        when(ioService.getInputFile()).thenReturn(Paths.get(INPUT_FILE));
        when(ioService.getOutputFile()).thenReturn(Paths.get(OUTPUT_FILE));
        when(specialEmployees.getSpecialEmployees()).thenReturn(Arrays.asList("1358"));
    }

    @Test
    public void testJob() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE), new FileSystemResource(OUTPUT_FILE));
    }
}
