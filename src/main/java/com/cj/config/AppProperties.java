package com.cj.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource("classpath:app.properties")
@Configuration
@Getter
public class AppProperties {
	// http://www.baeldung.com/2012/02/06/properties-with-spring/
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${server.tocken}")
	private String serverTocken;

	@Value("${wechat.id}")
	private String wecahtId;

	@Value("${H2database.name}")
	private String databaseName;

	@Value("${site.base}")
	private String siteBase;

	@Value("${account.username}")
	private String username;

	@Value("${account.password}")
	private String password;
}
