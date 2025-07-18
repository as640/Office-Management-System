package com.officemanagement.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.officemanagement.office.dao.repository")
@EntityScan(basePackages = "com.officemanagement.office.dao.model")
@EnableJpaAuditing // Only if you use @CreatedDate / @LastModifiedDate
public class OfficeApplication {
	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}
}
