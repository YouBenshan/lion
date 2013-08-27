package com.cj.lion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ReplyerConfig.class)
public class LionRootConfig {
	
	public static final String WEB_BASE_LINK="http://s-66915.gotocdn.com/lion";
}
