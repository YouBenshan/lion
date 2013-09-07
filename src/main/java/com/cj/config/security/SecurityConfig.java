package com.cj.config.security;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

	public static final String SHIRO_FILTER_NAME = "shiroFilter";

	@Bean
	public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Autowired
	private JpaAuthorizingRealm jpaAuthorizingRealm;

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			RealmSecurityManager sealmSecurityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor
				.setSecurityManager(sealmSecurityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public RealmSecurityManager sealmSecurityManager() {
		RealmSecurityManager realmSecurityManager = new DefaultWebSecurityManager();
		realmSecurityManager.setRealm(jpaAuthorizingRealm);
		return realmSecurityManager;
	}

	@Bean
	@SneakyThrows
	public AbstractShiroFilter shiroFilter(
			RealmSecurityManager sealmSecurityManager) {
		AbstractShiroFilter shiroFilter = (AbstractShiroFilter) this
				.shiroFilterFactoryBean(sealmSecurityManager).getObject();
		return shiroFilter;
	}

	private ShiroFilterFactoryBean shiroFilterFactoryBean(
			RealmSecurityManager sealmSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(sealmSecurityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/admin");
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/resources/**", "anon");
		filterChainDefinitionMap.put("/wechatReceiver", "anon");
		filterChainDefinitionMap.put("/guest/**", "anon");
		filterChainDefinitionMap.put("/admin/**", "authc");
		filterChainDefinitionMap.put("/api/**", "authc");
		filterChainDefinitionMap.put("/h2/**", "authc");
		filterChainDefinitionMap.put("/template/**", "authc");

		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
}
