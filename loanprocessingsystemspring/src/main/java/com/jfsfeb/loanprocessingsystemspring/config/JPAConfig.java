package com.jfsfeb.loanprocessingsystemspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class JPAConfig {
@Bean
public LocalEntityManagerFactoryBean getEntityFactoy() {
	LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
	factoryBean.setPersistenceUnitName("TestPersistence");
	
	return factoryBean;
}
}
