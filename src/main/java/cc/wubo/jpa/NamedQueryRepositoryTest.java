package cc.wubo.jpa;

import java.util.List;
/**
 * @NamedQuery 需要注意：
 * 1.必须指定resultClass
 * 2.resultClass指定的类，必须是Entity（也就是说必须要有@Entity注解，并且Entity要有主键注解@Id）
 * 3.使用sql语句做命名查询，参数不能和Sort配合使用，但是可以和Pageable配合使用
 * @author dengwubo
 *
 */
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.wubo.jpa.entity.Phone;
import cc.wubo.jpa.entity.User;
import cc.wubo.jpa.repository.UserNamedQueryRepository;
@EnableTransactionManagement
@EnableAsync
public class NamedQueryRepositoryTest {
	
	
	
	@Test
	public void byId() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		User user = repository.byId();
		System.out.println(user);
	}
	@Test
	public void byList() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		List<User> user = repository.byList();
		System.out.println(user);
	}
	@Test
	public void byUsername() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		User user = repository.byUsername("lisi");
		System.out.println(user);
	}
	@Test
	public void byPage() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
	
		Page<User> byPage = repository.byPage(1, new PageRequest(0, 3));
		List<User> content = byPage.getContent();
		System.out.println(content);
	}
	@Test
	public void byPage2() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		
		Page<User> byPage = repository.byPage2(1, new PageRequest(0, 3));
		List<User> content = byPage.getContent();
		System.out.println(content);
	}
	@Test
	public void byId2() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		
		User byId = repository.byId(1);
		System.out.println(byId);
	}
	@Test
	public void bySql() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		List<User> bySql = repository.bySql(1);
		System.out.println(bySql);
	}
	@Test
	public void bySqlPhone() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		List<Phone> bySql = repository.bySqlPhone(1); 
		System.out.println(bySql);
	}
	//自定义sql语句不支持排序
/*	@Test
	public void bySort() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		List<User> sort = repository.bySort(new Sort(new Order(Direction.DESC, "username")));
		System.out.println(sort);
	}*/
	//自定义sql语句支持分页
	@Test
	public void pageList() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserNamedQueryRepository repository = context.getBean(UserNamedQueryRepository.class);
		List<User> pageList = repository.pageList(new PageRequest(0, 3));
		System.out.println(pageList);
	}
}
