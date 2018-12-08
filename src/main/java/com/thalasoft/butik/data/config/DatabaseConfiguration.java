package com.thalasoft.butik.data.config;

import com.thalasoft.toolbox.spring.PackageBeanNameGenerator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(nameGenerator = PackageBeanNameGenerator.class, basePackages = { "com.thalasoft.butik.data.config" })
@EntityScan(basePackages = { "com.thalasoft.butik.data.jpa.domain" })
@EnableJpaRepositories(basePackages = { "com.thalasoft.butik.data.jpa.repository" })
public class DatabaseConfiguration {
}
