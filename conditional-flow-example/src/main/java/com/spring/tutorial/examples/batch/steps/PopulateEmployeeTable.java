package com.spring.tutorial.examples.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PopulateEmployeeTable extends CreateEmployeeTable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateEmployeeTable.class);

    @Value("classpath:populateEmployees.sql")
    private Resource resource;

    public PopulateEmployeeTable(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        String sql = fileAsString();
        LOGGER.debug("Sql script to populate employee table : {}", sql);
        jdbcTemplate.execute(sql);
        return RepeatStatus.FINISHED;
    }

    @Override
    public Resource getResource() {
        return resource;
    }
}
