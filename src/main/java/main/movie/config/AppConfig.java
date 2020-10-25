package main.movie.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = {
        "main.movie.dao",
        "main.movie.service",
        "main.movie.security",
        "main.movie.model.dto.mapper"})
@PropertySource("classpath:db.properties")
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(environment.getProperty("db.driver"));
        source.setUrl(environment.getProperty("db.url"));
        source.setUsername(environment.getProperty("db.username"));
        source.setPassword(environment.getProperty("db.password"));
        return source;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("main.movie.model");
        return factoryBean;
    }
}
