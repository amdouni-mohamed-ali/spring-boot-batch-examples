package org.spring.tutorial.examples.batch.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSourceWriter implements ItemWriter<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceWriter.class);

    @Override
    public void write(List<? extends String> emails) {

        if (emails != null && !emails.isEmpty()) {

            emails.forEach(user -> LOGGER.info("{}", user));
        } else {

            LOGGER.info("There is no users in the database");
        }
    }
}
