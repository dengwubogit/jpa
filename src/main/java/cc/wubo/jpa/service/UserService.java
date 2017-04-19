package cc.wubo.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.wubo.jpa.entity.User;

@Service
public class UserService {

	@Autowired
	private EntityManagerFactory emf;
	@PersistenceUnit
	private EntityManagerFactory emf2;
	
	
	/**
	 * 直接使用EntityManagerFactory创建EntityManager对象操作数据库的时候，需要手动管理事务
	 * 注意不能使用@@Transactional注解添加事务
	 * 需要手工从创建的EntityManager对象中获取事务
	 * 
	 * 不推荐使用
	 */
	public void addUserByEntityManagerFactory() {
		//对象相同
		System.out.println(emf==emf2);
		EntityManager em = emf2.createEntityManager();
		em.getTransaction().begin();
		User user = new User();
		user.setPhone("10086");
		user.setStatus(1);
		user.setUsername("dabiaoge");
		em.persist(user);
		em.getTransaction().commit();
	}
	
	
	/**
	 * 使用spring容器中获取EntityManager对象，不需要手动管理使用，
	 * insert，update，delete需要事务
	 * 使用事务，直接在方法上加@Transactional注解
	 * 
	 * 推荐使用
	 */
	@Autowired
	EntityManager em ;
	@PersistenceContext
	EntityManager em2 ;
	@Transactional
	public void addUserByEntityManager() {
		//对象不同
		System.out.println(em==em2);
		User user = new User();
		user.setPhone("10086");
		user.setStatus(1);
		user.setUsername("dabiaoge2");
		em.persist(user);
	}
	
	public void queryAll() {
		List list = em.createQuery("select u from User u").getResultList();
		for (Object object : list) {
			System.out.println(object);
		}
		
	}
	
	public void query() {
		Query query = em.createQuery("select u from User u where u.status = ?1");
		query.setParameter(1, 3);
		List list = query.getResultList();
		for (Object object : list) {
			System.out.println(object);
			
		}
	}
	@Transactional
	public void delete() {
		User find = em.find(User.class, 11);
		em.remove(find);
	}
	@Transactional
	public void merge() {
		
		User find =em.find(User.class,12);
		find.setStatus(2);
		em.merge(find);
		
	}
	
	
}
