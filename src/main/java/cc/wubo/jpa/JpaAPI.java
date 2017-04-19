package cc.wubo.jpa;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.wubo.jpa.entity.User;
import cc.wubo.jpa.repository.UserRepository_sort;
import cc.wubo.jpa.service.UserService;


@EnableAsync
public class JpaAPI {

	@Test
	public void addUserByEntityManagerFactory() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserService userService = context.getBean(UserService.class);
		userService.addUserByEntityManagerFactory();
		
		
		
	}
	@Test
	public void addUserByEntityManager() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserService userService = context.getBean(UserService.class);
		userService.addUserByEntityManager();
		
		
		
	}
	@Test
	public void query() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserService userService = context.getBean(UserService.class);
		userService.query();
		
		
		
	}
	
	@Test
	public void delete() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserService userService = context.getBean(UserService.class);
		userService.delete();
		
		
		
	}
	@Test
	public void merge() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserService userService = context.getBean(UserService.class);
		userService.merge();
		
		
		
	}
	
	
	
	
}
