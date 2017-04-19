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

@EnableTransactionManagement
@EnableAsync
public class CustomRepository_sort_Test {
//=============================排序============================
	@Test
	public void sort1() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		List<User> list = repository.orderByUsername(1);
		for (User user : list) {
			System.out.println(user);
		}
		
		
	}
	
	@Test
	public void sort2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		List<User> list = repository.queryByStatus(1, new Sort(new Order(Direction.DESC,"username")));
		for (User user : list) {
			System.out.println(user);
		}
		
		
	}
	/**
	 * 多条件排序
	 */
	@Test
	public void sort3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		List<User> list = repository.queryAll(new Sort(new Order(Direction.DESC,"username"),new Order(Direction.ASC,"status")));
		for (User user : list) {
			System.out.println(user);
		}
		
		
	}
//===============================分页================================
	@Test
	public void page1() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		Page<User> page = repository.findByStatus(1, new PageRequest(1, 3,new Sort(new Order(Direction.DESC, "username"))));// 分页+排序
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		List<User> list = page.getContent();
		for (User user : list) {
			System.out.println(user);
		}
		
	}
	@Test
	public void page2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		Page<User> findAll = repository.findAll(new PageRequest(0, 3,new Sort(new Order(Direction.DESC,"username"))));
		List<User> list = findAll.getContent();
		
		for (User user : list) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void async_future() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserRepository_sort repository = context.getBean(UserRepository_sort.class);
		Future<User> queryById = repository.queryById(1);
		User user = queryById.get();
		System.out.println(user);
		
		
		
	}
	
	
	
	
}
