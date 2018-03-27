package org.spring.tutorial.examples.batch.writers;


import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSourceWriter implements ItemWriter<List<User>> {

    @Override
    public void write(List<? extends List<User>> users) throws Exception {

        for (Object user : users) {
            System.out.println("Writing the data " + user);
        }
    }
}
