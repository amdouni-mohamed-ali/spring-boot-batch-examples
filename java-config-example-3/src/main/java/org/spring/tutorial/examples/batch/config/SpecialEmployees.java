package org.spring.tutorial.examples.batch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:config/special.properties")
public class SpecialEmployees {

    @Value("#{'${ids}'.split(';')}")
    private List<String> specialEmployees;

    public List<String> getSpecialEmployees() {
        return specialEmployees;
    }

    public SpecialEmployees setSpecialEmployees(List<String> specialEmployees) {
        this.specialEmployees = specialEmployees;
        return this;
    }
}
