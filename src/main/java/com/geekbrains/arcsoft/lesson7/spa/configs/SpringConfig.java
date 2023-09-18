package com.geekbrains.arcsoft.lesson7.spa.configs;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import com.geekbrains.arcsoft.lesson7.spa.persistences.PersonsDAO;
import com.geekbrains.arcsoft.lesson7.spa.services.BusinessService;
import com.geekbrains.arcsoft.lesson7.spa.services.PersonsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class SpringConfig {
    @Bean(name = "personsBusinessService")
    public BusinessService<Person> getPersonsBusinessService() {
        return new PersonsService();
    }
}
