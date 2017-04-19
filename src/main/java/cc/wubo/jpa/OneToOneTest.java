package cc.wubo.jpa;

import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	@Test
	public void run() {
		
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
	/**
	 * 双向保存
	 */
	@Test
	@Transactional
	public void doubleOneToOne() {
		
		Person p = new Person();
		p.setAge(1);
		p.setName("biaoge");
		IdCard card = new IdCard();
		card.setCardNum("94100");
		card.setPerson(p);
		p.setIdCard(card);
		
		idCardRepository.save(card);
	}
	
	/**
	 * 解决双向一对一递归查询
	 */
	@Test
	public void doubleSelect() {
		
		System.out.println(personRepository.findOne(1));
	}
	/**
	 * 解决双向一对一递归删除
	 */
	@Test
	public void doubledelete() {
		
		personRepository.delete(1);
	}
	/**
	 * 双向一对一关系中，需要注意的是，
	 * 1. 双方不能是fatch=FetchType.EAGER，必须要有一方是LAZY
	 * 2. 如果双方有一方设置了LAZY，则LAZY的一方不能再去使用另一方了，因为你使用另一方的话就又去加载了，容易递归
	 * 
	 * 两种事项一对一关联关系的方法
	 * 1. 双方都使用@OneToOne，配合@JoinColumn（双方对应的表都会有一个双方的id）
	 * 2. 双方有使用@OneToOne,然后在被拥有方设置mappedBy（只在被拥有方会持有拥有放的id）
	 */

}
