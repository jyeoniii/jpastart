package com.example.jpa.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableJpaAuditing
@Configuration
@ConditionalOnClass(DataSourceBuilder.class)
@EntityScan(basePackages = {"com.example.jpa.model"})
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private Environment env;

    public Map<String, String> getHibernateProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", getEnvProperty("hibernate.admin.dialect"));
        props.put("hibernate.format_sql", getEnvProperty("hibernate.admin.format_sql"));
        props.put("hibernate.show_sql", getEnvProperty("hibernate.admin.show_sql"));
        props.put("hibernate.hbm2ddl.auto", getEnvProperty("hibernate.admin.hbm2ddl.auto"));
        props.put("hibernate.physical_naming_strategy", getEnvProperty("hibernate.admin.physical_naming_strategy"));
        return props;
    }

    private String getEnvProperty(String key) {
        return env.getProperty(key);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getEnvProperty("datasource.jdbcUrl"));
        hikariConfig.setUsername("datasource.username");
        hikariConfig.setPassword("datasource.password");

        return new HikariDataSource(hikariConfig);
    }
}