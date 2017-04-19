package cc.wubo.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cc.wubo.jpa.entity.User;
import cc.wubo.jpa.repository.UserOnlyRepository;
import cc.wubo.jpa.repository.UserRepository;

public class OnlyRepositoryTest {

	@Test
	public void findByUsername() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		User findByUsername = repository.findByUsername("lisi");
		System.out.println(findByUsername);
		context.close();
		
	} 
	@Test
	public void findById() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		User findById = repository.findById(3);
		System.out.println(findById);
		context.close();
		
	} 
	@Test
	public void findByStatus() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User>findByStatus = repository.findByStatus(1);
		System.out.println(findByStatus);
		
		repository.findByStatus(1).forEach(new Consumer<User>() {
			public void accept(User t) {
				System.out.println(t);
			}
			
		});
		context.close();
		
	} 
	@Test
	public void findByUsernameAndPhone() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		User findByUsernameAndPhone = repository.findByUsernameAndPhone("qiqi", "77777");
		System.out.println(findByUsernameAndPhone);
		context.close();
		
	} 
	@Test
	public void findByUsernameOrPhone() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User> findByUsernameOrPhone = repository.findByUsernameOrPhone("qiqi", "77777");
		System.out.println(findByUsernameOrPhone);
		context.close();
		
	} 
	@Test
	public void findByPhoneIn() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List phones = new ArrayList<String>();
		phones.add("77777");
		phones.add("88888");
		phones.add("99999");
		
		List<User> findByPhoneIn = repository.findByPhoneIn(phones);
		System.out.println(findByPhoneIn);
		context.close();
		
	} 
	@Test
	public void findByIdBetween() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User> findByIdBetween = repository.findByIdBetween(1, 4);
		System.out.println(findByIdBetween);
		context.close();
		
	} 
	@Test
	public void findByPhoneInOrderByUsername() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User> findByPhoneInOrderByUsername = repository.findByPhoneInOrderByUsername(Arrays.asList("77777","88888","99999"));
		System.out.println(findByPhoneInOrderByUsername);
		context.close();
		
	} 
	@Test
	public void findByPhoneInOrderByUsernameDesc() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User> findByPhoneInOrderByUsernameDesc = repository.findByPhoneInOrderByUsernameDesc(Arrays.asList("77777","88888","99999"));
		System.out.println(findByPhoneInOrderByUsernameDesc);
		context.close();
		
	} 
	
	@Test
	public void findByIdLessThan() {
		// 启用注解扫描，使所有注解注入的形式生效
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserOnlyRepository repository = context.getBean(UserOnlyRepository.class);
		List<User> findByIdLessThan = repository.findByIdLessThan(6);
		System.out.println(findByIdLessThan);
		context.close();
		
	} 
	
	
	
	
}
