package org.spring.tutorial.examples.batch.generic;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    protected abstract String getJobName();

    protected Job job(Step step, Step... steps) {

        SimpleJobBuilder jobBuilder = jobBuilderFactory
                .get(getJobName())
                .start(step);

        for (Step s : steps) {
            jobBuilder.next(s);
        }

        return jobBuilder
                .build();
    }
}
