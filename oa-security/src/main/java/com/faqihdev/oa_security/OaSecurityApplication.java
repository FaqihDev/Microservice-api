package com.faqihdev.oa_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {
		"com.faqihdev.oa.shared.data"
})
@EnableJpaRepositories(basePackages = {
		"com.faqihdev.oa.shared.dao"
})
@SpringBootApplication
public class OaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaSecurityApplication.class, args);
	}

}
