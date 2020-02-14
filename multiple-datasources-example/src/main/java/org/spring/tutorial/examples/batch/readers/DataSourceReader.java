package org.spring.tutorial.examples.batch.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.domain.User;
import org.spring.tutorial.examples.batch.repository.IUserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSourceReader implements ItemReader<User>, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceReader.class);

    @Autowired
    IUserRepository userRepository;

    private List<User> users;
    private int count = 0;

    @Override
    public User read() {

        if (count < users.size()) {

            LOGGER.info("Reading : {}", users.get(count));
            return users.get(count++);
        } else {
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        users = userRepository.findAll();
        LOGGER.info("The list of users is : {}", users);
    }
}
