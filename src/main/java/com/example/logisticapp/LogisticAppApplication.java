package com.example.logisticapp;

import com.example.logisticapp.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisticAppApplication implements ApplicationRunner {

    @Autowired
    InitDbService initDbService;

    public static void main(String[] args) {
        SpringApplication.run(LogisticAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //initDbService.initDb();
        //initDbService.initUsers();
    }
}
