package org.spring.tutorial.examples.batch.readers;

import org.spring.tutorial.examples.batch.domain.User;
import org.spring.tutorial.examples.batch.repository.IUserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Component
public class DataSourceReader implements ItemReader<User> {

    private final IUserRepository userRepository;
    private Iterator<User> usersIterator;

    public DataSourceReader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void init() {

        usersIterator = userRepository.findAll().iterator();
    }

    @Override
    public User read() {

        if (usersIterator.hasNext())
            return usersIterator.next();
        return null;
    }
}
