package cc.wubo.jpa.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class JPAConfiguration {
	/*
	 * 配置数据源
	 */
	@Bean(name="dataSource")
 	public DataSource createDataSource() {
 		MysqlDataSource dataSource = new MysqlDataSource();
 		dataSource.setUrl("jdbc:mysql://localhost:3306/jpa?useUnicode=true&characterEncoding=utf8");
 		dataSource.setUser("root");
 		dataSource.setPassword("123");
 		return dataSource; 
 	}
	/**
	 * 配置JPA实体管理工厂
	 * @param dataSource
	 * @return
	 */
	@Bean(name="entityManagerFactory")
	public EntityManagerFactory createEntityManagerFactory(DataSource dataSource) { //声明式，初始化时自动获取
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		
		//设置实体bean的包路径
		entityManagerFactory.setPackagesToScan("cc.wubo.jpa.entity");
		//指定一个JAP的实现,因为JPA只制定了规范，所以需要一个具体的实现来操作
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		//初始化bean的时候可以针对某一个bean进行配置
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory.getObject();
		
	}
	/**
	 * 配置事务
	 */
	@Bean(name="transactionManager")
	public PlatformTransactionManager createPlatformTransactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
		
	} 
	
}
