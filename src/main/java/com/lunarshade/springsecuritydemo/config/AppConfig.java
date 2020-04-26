package com.lunarshade.springsecuritydemo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.lunarshade.springsecuritydemo")
@EnableWebMvc
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

    //Переменная для пропертей
    @Autowired
    private Environment env;

    //Define bean for ViewResolver
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public DataSource securityDataSource() {
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
        {
            try {
                    securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
                } catch (PropertyVetoException e) {
                    throw new RuntimeException(e);
                }
                securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
                securityDataSource.setUser(env.getProperty("jdbc.user"));
                securityDataSource.setPassword(env.getProperty("jdbc.password"));
        }

        {
            securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
            securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
            securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
            securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
        }

        return securityDataSource;
    }

    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);
        return Integer.parseInt(propVal);
    }
}
