package training.common.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import training.common.repository.BaseRepositoryFactoryBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "training" },  repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@PropertySource("application.properties")
public class DataSourceConfiguration {

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("training.**");
		final Properties props = new Properties();
		props.setProperty("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class.getName());
		props.setProperty("hibernate.show_sql", "true");
		entityManagerFactoryBean.setJpaProperties(props);
		entityManagerFactoryBean.afterPropertiesSet();

		return entityManagerFactoryBean.getObject();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public AbstractPlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
}
