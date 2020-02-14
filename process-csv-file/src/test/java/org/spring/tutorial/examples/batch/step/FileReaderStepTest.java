package org.spring.tutorial.examples.batch.step;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.spring.tutorial.examples.batch.BatchApplication;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.service.IOService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchApplication.class, JobLauncherTestUtils.class})
public class FileReaderStepTest {

    @MockBean
    IOService ioService;

    @Autowired
    @InjectMocks
    FileReaderStep fileReaderStep;

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Before
    public void setUp() {

        when(ioService.getInputFile()).thenReturn(Paths.get("target/test-classes/step/input_example"));
    }

    @Test
    @DirtiesContext //to launch every test on a new spring context (only if we have more than one test)
    public void testReadInputFile() {

        JobExecution jobExecution = jobLauncherTestUtils.launchStep(ApplicationConstants.FILE_READER_STEP);
        assertThat(jobExecution).isNotNull();
        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        Map<String, String[]> entryRecords = (Map<String, String[]>) jobExecution.getExecutionContext().get(ApplicationConstants.INPUT_FILE);
        assertThat(entryRecords).hasSize(4);
    }
}
