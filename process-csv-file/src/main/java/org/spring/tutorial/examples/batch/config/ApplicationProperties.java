package org.spring.tutorial.examples.batch.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("batch")
public class ApplicationProperties {

    @Value("${batch.in-file}")
    private String inputFile;
    private String outputFile;

    public String getInputFile() {
        return inputFile;
    }

    public ApplicationProperties setInputFile(String inputFile) {
        this.inputFile = inputFile;
        return this;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public ApplicationProperties setOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }
}
