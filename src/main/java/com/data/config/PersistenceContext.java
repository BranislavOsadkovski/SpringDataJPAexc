/**
 * Copyright the original author or authors.
 */
package com.data.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Branislav
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.data.dao" }) 
public class PersistenceContext {
	private static final String[] ENTITY_PACKAGES = {"com.data.entites"};

	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {

		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSourceConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSourceConfig.setUsername(env.getProperty("spring.datasource.username"));
		dataSourceConfig.setPassword(env.getProperty("spring.datasource.password"));

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	@Autowired(required = true)
	public EntityManagerFactory entityManagerFactory(Environment env,DataSource dataSource) {
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(env.getProperty("hibernate-dialect"));
		vendorAdapter.setDatabase(Database.MYSQL); 
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(ENTITY_PACKAGES);
		factory.setDataSource(dataSource);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
		properties.setProperty("spring.jpa.properties.hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		factory.setJpaProperties(properties);

		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

}
