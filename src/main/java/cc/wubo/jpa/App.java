package cc.wubo.jpa;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.wubo.jpa.entity.User;
import cc.wubo.jpa.repository.UserRepository;
/**
 * 1. 加入spring data jpa 依赖
 * 2. 加入数据库驱动的依赖
 * 3. 加入jpa实现的依赖
 * 4. 定义Entity
 * 5. 在spring容器中，定义三个bean， DataSource、EntityManagerFactory、PlatformTransactionManager
 * 6. 定义Repository
 * 7. 操作使用
 * @author dengwubo
 * 定义Repository方法
 * 方法一： 继承如下接口：
 * Repository
 * CrudRepository
 * PagingAndSortingRepository
 * JpaRepository
 *
 *方法二：
 *使用@RepositoryDefinition
 *
 *不生成实现类使用：@NoRepositoryBean
 *
 */

@ComponentScan
@EnableJpaRepositories //自动将UserRepositories接口代理为实现类
@EnableTransactionManagement
public class App {

	
	@Test
	public void testsave() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserRepository repository = context.getBean(UserRepository.class);
		User user = new User();
		user.setPhone("109010");
		user.setStatus(11);
		user.setUsername("zhangsan333");
		
		repository.save(user);
		
		context.close();
		
	}
	@Test
	public void testfind() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserRepository repository = context.getBean(UserRepository.class);

		User findOne = repository.findOne(1);
		System.out.println(findOne);
		
		context.close();
		
	}
	@Test
	public void testupdate() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserRepository repository = context.getBean(UserRepository.class);
		
		User user = repository.findOne(1);
		user.setPhone("11111");
		repository.save(user);
		context.close();
		
	}
	@Test
	public void testfindAll() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserRepository repository = context.getBean(UserRepository.class);
		
		List<User> findAll = (List<User>) repository.findAll();
		System.out.println(findAll);
		context.close();
		
	}
	@Test
	public void testdelete() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserRepository repository = context.getBean(UserRepository.class);
		
		User user = repository.findOne(1);
		repository.delete(user);
		context.close();
		
	}
	
}
