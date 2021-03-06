package org.spring.tutorial.examples.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableBatchProcessing
public class BatchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {

        LOGGER.info("start batch ...");
        SpringApplication.run(BatchApplication.class, args);
        LOGGER.info("the batch was ended. please check its status.");
    }
}
