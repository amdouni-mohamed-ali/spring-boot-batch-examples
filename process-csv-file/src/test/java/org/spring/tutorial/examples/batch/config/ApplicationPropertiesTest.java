package org.spring.tutorial.examples.batch.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationPropertiesTest {

    @Autowired
    ApplicationProperties applicationProperties;

    @Test
    public void testProperties() {

        assertThat(applicationProperties.getInputFile()).isNotEmpty();
        assertThat(applicationProperties.getOutputFile()).isNotEmpty();
    }
}
