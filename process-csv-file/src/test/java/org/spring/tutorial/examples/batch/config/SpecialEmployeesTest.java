package org.spring.tutorial.examples.batch.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/special-test.properties")
public class SpecialEmployeesTest {

    /*
     * if you want to override the special.properties file on tests use this configuration
     */

    @Autowired
    SpecialEmployees specialEmployees;

    @Test
    public void testProperties() {

        assertThat(specialEmployees.getSpecialEmployees()).hasSize(2);
        assertThat(specialEmployees.getSpecialEmployees().get(0)).isEqualTo("999");
        assertThat(specialEmployees.getSpecialEmployees().get(1)).isEqualTo("888");
    }
}
