package com.cj.lion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReplyerConfig.class,BatchConfig.class})
public class LionRootConfig {
}
