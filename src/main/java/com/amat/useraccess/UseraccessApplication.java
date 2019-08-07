package com.amat.useraccess;

import com.amat.useraccess.repository.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
@EnableJpaRepositories(basePackages = "com.amat.useraccess.repository", repositoryBaseClass = BaseRepositoryImpl.class)

public class UseraccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseraccessApplication.class, args);
    }

}
