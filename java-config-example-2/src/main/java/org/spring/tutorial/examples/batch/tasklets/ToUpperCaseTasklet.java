package org.spring.tutorial.examples.batch.tasklets;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
public class ToUpperCaseTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        try {

            //get data
            List<User> users = new ArrayList<User>();
            users.add(new User(1,"maa","maa"));
            users.add(new User(1,"mbb","mbb"));
            users.add(new User(1,"mnn","mnn"));

            //process data
            for(User user : users){
                user.setEmail(user.getEmail().toUpperCase());
                user.setName(user.getName().toUpperCase());
            }

            //write data
            for(User user : users){
                System.out.println(user);
            }

        }catch (Exception e){

            stepContribution.setExitStatus(ExitStatus.FAILED);
            throw e;
        }

        return RepeatStatus.FINISHED;
    }
}
