package org.spring.tutorial.examples.batch.processors;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) {

        user.setEmail(user.getEmail().toUpperCase());
        user.setName(user.getName().toUpperCase());
        return user;
    }
}