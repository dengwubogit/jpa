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
import cc.wubo.jpa.service.BookQueryService;
import cc.wubo.jpa.service.UserService;


@EnableAsync
public class CriteriaQueryTest {


	@Test
	public void queryAll() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.queryAll();
		
		
	}
	
	@Test
	public void getById() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.getById();
		
		
	}
	
	@Test
	public void like() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.like();
		
		
	}
	
	
	@Test
	public void in() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.in();
		
		
	}
	
	@Test
	public void between() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.between();
		
		
	}
	@Test
	public void betweenAndIn() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.betweenAndIn();
		
		
	}
	
	@Test
	public void orderBy() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.orderBy();
		
		
	}
	
	@Test
	public void order() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.order();
		
		
	}
	
	@Test
	public void orderAndThen() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.orderAndThen();
		
		
	}
	
	@Test
	public void orderTeturn() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.orderTeturn();
		
		
	}
	@Test
	public void groupBy() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryService bookService = context.getBean(BookQueryService.class);
		bookService.groupBy();
		
		
	}
	
	
	
	
}
