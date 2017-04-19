package cc.wubo.jpa;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import cc.wubo.jpa.entity.onetoone.IdCard;
import cc.wubo.jpa.entity.onetoone.Person;
import cc.wubo.jpa.repository.onetoone.IdCardRepository;
import cc.wubo.jpa.repository.onetoone.PersonRepository;
import cc.wubo.jpa.service.onetoone.PersonService;

public class OneToOneTest {
 	 PersonRepository personRepository =null;
 	 IdCardRepository idCardRepository =null;
 	 PersonService personService =null;
	@Before
	public void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		personRepository = context.getBean(PersonRepository.class);
		idCardRepository = context.getBean(IdCardRepository.class);
		personService = context.getBean(PersonService.class);
		
	}
	/**
	 * 先保存从表再保存主表，没有级联关系
	 */
	@Test
	public void add() {
		IdCard card = new IdCard();
		card.setCardNum(UUID.randomUUID().toString());
		idCardRepository.save(card);
		Person person = new Person();
		person.setAge(11);
		person.setIdCard(card);
		person.setName("zhangsan");
		personRepository.save(person);
		
	}
	/**
	 * 级联保存
	 */
	@Test
	public void cascadeAdd() {
		IdCard card = new IdCard();
		card.setCardNum(UUID.randomUUID().toString());
		Person person = new Person();
		person.setAge(12);
		person.setIdCard(card);
		person.setName("李四");
		personRepository.save(person);
		
	}
	/**
	 * 懒加载之立即加载
	 */
	@Test
	public void fetchEAGER() {
		Person person = personRepository.findOne(1);
		System.out.println(person);
	}
	/**
	 * 懒加载
	 * 使用懒加载的时候，需要注意，懒加载的对象需要再事务范围内获取，否则会报错
	 */
	
	@Test
	
	public void fetchLAZY() {
		 
		//IdCard idCard = personService.getIdCard(idCardRepository, 1);
		personService.lazy();
		Person person = personRepository.findOne(1);
		System.out.println(person.getId());
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getIdCard()); // 获取不到
	}

}
