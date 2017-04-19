package cc.wubo.jpa;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.wubo.jpa.entity.User;
import cc.wubo.jpa.repository.UserCustomRepository;

@EnableTransactionManagement
public class CustomRepositoryTest {

	@Test
	public void list() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserCustomRepository repository = context.getBean(UserCustomRepository.class);
		List<User> list = repository.list();
		for (User user : list) {
			System.out.println(user);
		}
		
		
	}
	@Test
	public void takeByPhone() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserCustomRepository repository = context.getBean(UserCustomRepository.class);
		List<User> takeByPhone = repository.takeByPhone("88888","lisi");
		System.out.println(takeByPhone);
	}
	
	@Test
	public void updateUser() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		
		UserCustomRepository repository = context.getBean(UserCustomRepository.class);
		Integer updateUser = repository.updateUser();
		System.out.println(updateUser);
		
	}
	
	
	
}
