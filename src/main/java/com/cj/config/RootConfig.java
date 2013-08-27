package com.cj.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = {"com.cj.repository","com.cj.lion.repository"})
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.cj.config" })
@Configuration
public class RootConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			HibernateJpaVendorAdapter adapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPackagesToScan("com.cj.domain","com.cj.lion.domain");
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(adapter);
		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		emf.setJpaPropertyMap(jpaProperties);
		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
