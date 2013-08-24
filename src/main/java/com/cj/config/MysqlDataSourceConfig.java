package com.cj.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@PropertySource("/mysqlDataSource.properties")
@Configuration
@Profile({ "default","product" })
class MysqlDataSourceConfig {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);
		return adapter;
	}

//	@Bean
//	public DataSource dataSource(Environment env) {
//		DataSource datasouce=new DataSource();
//		datasouce.setDriverClassName(env.getProperty("jdbc.driver").trim());
//		datasouce.setUrl(env.getProperty("jdbc.url").trim());
//		datasouce.setUsername(env.getProperty("jdbc.username").trim());
//		datasouce.setPassword(env.getProperty("jdbc.password").trim());
//		datasouce.setMaxActive(40);
//		datasouce.setMaxIdle(5);
//		datasouce.setDefaultAutoCommit(false);
//		datasouce.setTimeBetweenEvictionRunsMillis(3600000);
//		datasouce.setMinEvictableIdleTimeMillis(3600000);
//		
//		return datasouce;
//	}
	
	@Bean
	  public DataSource dataSource(Environment env){
		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
	    ds.setDriverClassName(env.getProperty("jdbc.driver").trim());
	    ds.setUrl(env.getProperty("jdbc.url").trim());
	    ds.setUsername(env.getProperty("jdbc.username").trim());
	    ds.setPassword(env.getProperty("jdbc.password").trim());
	    return ds;
	  }
}