package com.infosys.capacitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.infosys.capacitor.dao" })
public class JpaConfiguration {

  @Autowired
  private Environment env;

  private String username = env.getProperty("DATABASE_USERNAME", "");
  private String password = env.getProperty("DATABASE_PASSWORD", "");
  private String hostname = env.getProperty("DATABASE_HOSTNAME", "localhost");
  private String databasePort = env.getProperty("DATABASE_PORT", "6379");
  private String databaseName = env.getProperty("DATABASE_NAME", "");
  private String databaseType = env.getProperty("DATABASE_TYPE", "sqlite");


  public JpaConfiguration() {
    super();
  }

  @Bean
  public DataSource dataSource() {
    String databaseUrl = getDatabaseUrl();
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("JPA_DRIVER_CLASS_NAME"));
    if (!(this.username.isEmpty()) && !(this.password.isEmpty())) {
      dataSource.setUsername(this.username);
      dataSource.setPassword(this.password);
    }
    dataSource.setUrl(databaseUrl);
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();
    manager.setDataSource(dataSource());
    manager.setPackagesToScan(env.getProperty("JPA_ENTITY_PACKAGES"));

    final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    manager.setJpaVendorAdapter(vendorAdapter);
    manager.setJpaProperties(getAdditionalProperties());
    return manager;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  final Properties getAdditionalProperties() {
    final Properties properties = new Properties();

    return properties;
  }

  private String getDatabaseUrl() {
    return String.format("jdbc:%s://%s:%s/%s", this.databaseType, this.hostname, this.databasePort, this.databaseName);
  }
}