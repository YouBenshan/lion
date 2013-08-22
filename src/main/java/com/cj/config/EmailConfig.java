package com.cj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@PropertySource("/email.properties")
@Configuration
public class EmailConfig {

	// @Bean
	public JavaMailSender javaMailSender(Environment env) {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(env.getProperty("email.host").trim());
		return javaMailSenderImpl;
	}
}
