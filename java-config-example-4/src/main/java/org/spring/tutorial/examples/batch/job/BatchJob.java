package org.spring.tutorial.examples.batch.job;

import org.spring.tutorial.examples.batch.generic.AbstractJob;
import org.spring.tutorial.examples.batch.step.CollectDataStep;
import org.spring.tutorial.examples.batch.step.ProcessDataStep;
import org.spring.tutorial.examples.batch.step.ShowDataStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJob extends AbstractJob {

    private final StepBuilderFactory stepBuilderFactory;
    private final CollectDataStep collectDataStep;
    private final ProcessDataStep processDataStep;
    private final ShowDataStep showDataStep;

    public BatchJob(StepBuilderFactory stepBuilderFactory, CollectDataStep collectDataStep, ProcessDataStep processDataStep, ShowDataStep showDataStep) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.collectDataStep = collectDataStep;
        this.processDataStep = processDataStep;
        this.showDataStep = showDataStep;
    }

    @Override
    protected String getJobName() {
        return "BatchJob";
    }

    @Bean
    protected Step readData() {

        return stepBuilderFactory
                .get("ReadDataStep")
                .tasklet(collectDataStep)
                .build();
    }

    @Bean
    protected Step processData() {

        return stepBuilderFactory
                .get("ProcessDataStep")
                .tasklet(processDataStep)
                .build();
    }

    @Bean
    protected Step writeData() {

        return stepBuilderFactory
                .get("WriteDataStep")
                .tasklet(showDataStep)
                .build();
    }

    @Bean
    protected Job job() {

        return super.job(readData(), processData(), writeData());
    }
}
