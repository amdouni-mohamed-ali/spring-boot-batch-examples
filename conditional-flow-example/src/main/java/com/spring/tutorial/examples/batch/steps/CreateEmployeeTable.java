package com.spring.tutorial.examples.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class CreateEmployeeTable implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateEmployeeTable.class);
    protected final JdbcTemplate jdbcTemplate;
    @Value("classpath:createEmployees.sql")
    private Resource resource;

    public CreateEmployeeTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        String createEmployeesSql = fileAsString();
        LOGGER.debug("Sql script to create employees : {}", createEmployeesSql);
        jdbcTemplate.execute(createEmployeesSql);
        return RepeatStatus.FINISHED;
    }

    public String fileAsString() throws IOException {
        try (Reader reader = new InputStreamReader(this.getResource().getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }

    public Resource getResource() {
        return resource;
    }
}
