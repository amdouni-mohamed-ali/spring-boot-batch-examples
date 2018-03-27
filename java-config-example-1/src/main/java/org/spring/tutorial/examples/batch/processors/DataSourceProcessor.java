package org.spring.tutorial.examples.batch.processors;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSourceProcessor implements ItemProcessor<List<User>, List<User>> {

    @Override
    public List<User> process(List<User> users){

        for(User user : users){
            user.setEmail(user.getEmail().toUpperCase());
            user.setName(user.getName().toUpperCase());
        }
        return users;
    }
}