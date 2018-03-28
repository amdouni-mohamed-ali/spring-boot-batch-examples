package org.spring.tutorial.examples.batch.readers;

import org.spring.tutorial.examples.batch.domain.User;
import org.spring.tutorial.examples.batch.repository.IUserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSourceReader implements ItemReader<User> ,InitializingBean {

    @Autowired
    IUserRepository userRepository;

    private List<User> users;
    private int count = 0;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (count < users.size()) {
            return users.get(count++);
        } else {
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        users = userRepository.findAll();
    }
}
