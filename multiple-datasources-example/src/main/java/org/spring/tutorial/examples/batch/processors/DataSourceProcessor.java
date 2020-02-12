package org.spring.tutorial.examples.batch.processors;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProcessor implements ItemProcessor<User, String> {

    @Override
    public String process(User user) throws Exception{

        return user.getEmail().toUpperCase();
    }
}