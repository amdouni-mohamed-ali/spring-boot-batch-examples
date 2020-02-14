package org.spring.tutorial.examples.batch.tasklets;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@StepScope
public class ToUpperCaseTasklet implements Tasklet {

    @Value("${generateError}")
    private Boolean generateError;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        //get data
        List<User> users = Arrays.asList(
                new User(1, "john", "john@example.com"),
                new User(2, "jack", "jack@example.com"),
                new User(3, "jeremy", "jeremy@example.com")
        );

        //process data
        // it's not a good practise to use peek to change data don't use it on a real project (use foreach instead)
        users = users
                .stream()
                .peek(user -> user.setEmail(
                        user.getEmail().toUpperCase()
                        )
                )
                .peek(user -> user.setName(
                        user.getName().toUpperCase()
                ))
                .collect(Collectors.toList());

        //write data
        users.forEach(System.out::println);

        if (generateError)
            throw new RuntimeException("The user demands to generate this runtime error");

        return RepeatStatus.FINISHED;
    }
}
