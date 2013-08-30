package com.cj.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile({ "test" })
class EmbeddedDataSourceConfig {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);
		return adapter;
	}

	/**
	 * You can access this H2 database at <a href =
	 * "http://localhost:8080/admin/console">the H2 administration console</a>.
	 */
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setName(appProperties
				.getDatabaseName()).setType(EmbeddedDatabaseType.H2).build();
	}
}