package org.spring.tutorial.examples.batch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.config.ApplicationProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class IOService implements InitializingBean {

    private final ApplicationProperties applicationProperties;
    private static final Logger LOGGER = LoggerFactory.getLogger(IOService.class);
    private Path inputFile;
    private Path outputFile;

    public IOService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public Path getInputFile() {
        return inputFile;
    }

    public IOService setInputFile(Path inputFile) {
        this.inputFile = inputFile;
        return this;
    }

    public Path getOutputFile() {
        return outputFile;
    }

    public IOService setOutputFile(Path outputFile) {
        this.outputFile = outputFile;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.inputFile = Paths.get(applicationProperties.getInputFile());
        LOGGER.debug("create the java path from the input file : {}", applicationProperties.getInputFile());
        this.outputFile = Paths.get(applicationProperties.getOutputFile());
        LOGGER.debug("create the java path from the output file : {}", applicationProperties.getOutputFile());
    }
}
