package org.spring.tutorial.examples.batch.readers;

import org.spring.tutorial.examples.batch.domain.User;
import org.spring.tutorial.examples.batch.repository.IUserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSourceReader implements ItemReader<List<User>> {

//    @Autowired
//    IUserRepository userRepository;

    private int count = 0;

    @Override
    public List<User> read(){

        List<User> users = new ArrayList<User>();
        users.add(new User(1,"maa","maa"));
        users.add(new User(1,"mbb","mbb"));
        users.add(new User(1,"mnn","mnn"));
//        return userRepository.findAll();
        return users;
    }
}
