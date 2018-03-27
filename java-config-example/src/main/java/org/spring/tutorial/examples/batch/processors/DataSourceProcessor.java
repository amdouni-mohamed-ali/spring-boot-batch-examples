package org.spring.tutorial.examples.batch.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String data){
        return data.toUpperCase();
    }
}