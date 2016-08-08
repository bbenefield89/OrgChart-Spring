package com.nexient.orgchart.data.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.beans.PropertyVetoException;
import org.springframework.beans.factory.annotation.Value;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * Created by dhoover on 7/21/2016.
 */
@Configuration
@Import(PropertyPlaceholderConfig.class)
@EnableJpaRepositories({ "com.nexient.orgchart.data.repository" })
@EnableTransactionManagement
public class JpaConfig {

    public static final String ENTITIES_PACKAGE = "com.nexient.orgchart.data.entity";

    @Value("${orgchart.jdbc.maxPoolSize}")
    private int maxPoolSize;

    @Value("${orgchart.jdbc.maxStatements}")
    private int maxStatements;

    @Value("${orgchart.jdbc.minPoolSize}")
    private int minPoolSize;

    @Value("${orgchart.jdbc.testConnection}")
    private Boolean testConnection;

    @Value("${orgchart.db.password}")
    private String databasePassword;

    @Value("${orgchart.db.url}")
    private String databaseUrl;

    @Value("${orgchart.db.username}")
    private String databaseUsername;

    @Value("${orgchart.db.driverClassName}")
    private String driverClassName;

    @Value("${orgchart.db.showSql}")
    private Boolean showSql;

    @Bean(destroyMethod = "close")

    public DataSource dataSource() throws PropertyVetoException {

        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(this.driverClassName);
        dataSource.setJdbcUrl(this.databaseUrl);
        dataSource.setUser(this.databaseUsername);
        dataSource.setPassword(this.databasePassword);
        dataSource.setMinPoolSize(this.minPoolSize);
        dataSource.setMaxPoolSize(this.maxPoolSize);
        dataSource.setMaxStatements(this.maxStatements);
        dataSource.setTestConnectionOnCheckout(this.testConnection);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(this.showSql);
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(this.dataSource());

        // Once JPA 2.2 implements support for the java8 time package, this
        // converter can be removed.

        factory.setPackagesToScan(JpaConfig.ENTITIES_PACKAGE, "org.springframework.data.jpa.convert.threeten");

        return factory;
    }



    @Bean

    public PlatformTransactionManager transactionManager() {

        return new JpaTransactionManager();
    }

}
