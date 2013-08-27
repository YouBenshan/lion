package com.cj.lion.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.cj.lion.web")
@EnableWebMvc
public class LionWebMvcConfig {

}
