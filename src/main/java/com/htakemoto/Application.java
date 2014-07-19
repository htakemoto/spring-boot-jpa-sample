package com.htakemoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.htakemoto")
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application 
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
