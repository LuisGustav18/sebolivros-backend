package com.luis.sebolivros.config;

import com.luis.sebolivros.domain.common.Service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public CommandLineRunner instanciaDB(){
        return args -> this.dbService.instaciaDB();
    }
}
