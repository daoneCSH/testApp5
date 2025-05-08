package com.springboot.testapp4.config;

import com.springboot.testapp4.commons.DynamicDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.springboot.testapp4.data.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)

public class DataSourceConfig {
    private static final String MARIADB_URL = "jdbc:mariadb://localhost:3307/testApp4";
    private static final String MARIADB_USER = "root";
    private static final String MARIADB_PASSWORD = "112233";

    private static final String PGSQL_URL = "jdbc:postgresql://localhost:5432/testApp4db";
    private static final String PGSQL_USER = "postgres";
    private static final String PGSQL_PASSWORD = "112233";

    @Bean
    public DataSource db1() {
        return DataSourceBuilder.create()
                .url(MARIADB_URL)
                .username(MARIADB_USER)
                .password(MARIADB_PASSWORD)
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    @Bean
    public DataSource db2() {
        return DataSourceBuilder.create()
                .url(PGSQL_URL)
                .username(PGSQL_USER)
                .password(PGSQL_PASSWORD)
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Primary
    @Bean
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("DB1", db1());
        dataSources.put("DB2", db2());
        dataSources.put("MARIADB", db1());
        dataSources.put("PGSQL", db2());

        DynamicDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setDefaultTargetDataSource(db1());
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dynamicDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dynamicDataSource);
        emf.setPackagesToScan("com.springboot.testapp4.data.entity"); // 엔티티 경로
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.hbm2ddl.auto", "update");
        jpaProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        emf.setJpaPropertyMap(jpaProps);

        return emf;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
