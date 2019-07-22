/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.stfc.utils.Constants;
import java.beans.PropertyVetoException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author dong.dv
 */
@Configuration
@EnableTransactionManagement
public class BeanConfig {

    private static final Logger logger = LogManager.getLogger(BeanConfig.class);
    @Autowired
    private Environment env;

    //DB Support
    @Bean
    public ComboPooledDataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("jupiter");
        try {
            dataSource.setDriverClass(env.getProperty("datasource.driver-class-name"));
        } catch (PropertyVetoException pve) {
            logger.info("Cannot load datasource driver (" + env.getProperty("datasource.driver-class-name") + ") : " + pve.getMessage());
            return null;
        }
        dataSource.setJdbcUrl(env.getProperty("datasource.url"));
        dataSource.setUser(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("initialPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("minPoolSize")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("maxPoolSize")));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("idleConnectionTestPeriod")));
        dataSource.setCheckoutTimeout(Integer.parseInt(env.getProperty("checkoutTimeout")));

        return dataSource;
    }

    @Bean
    @Qualifier(Constants.CONFIG.SESSION_FACTORY)
    public SessionFactory getSessionFactory() throws Exception {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.format-sql"));
        properties.put("current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        // Package contain entity classes
        factoryBean.setPackagesToScan(env.getProperty("package.scan"));
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        return sf;
    }

    @Autowired
    @Bean
    @Qualifier(Constants.CONFIG.TRANSACTION)
    public HibernateTransactionManager getTransactionManager(@Qualifier(Constants.CONFIG.SESSION_FACTORY) SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

}
