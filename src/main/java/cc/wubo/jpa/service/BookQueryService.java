package cc.wubo.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.wubo.jpa.entity.Book;
import cc.wubo.jpa.entity.BookInfo;

@Service
public class BookQueryService {
	@Autowired
	private EntityManager em;

	/**
	 * select * from books 
	 * CriteriaBuilder： 创建CriteriaQuery，Predicate的工厂
	 * CriteriaQuery<Book>: 查询主语句,括号中是查询返回的数据类型，本质是返回Object[]
	 */
	public void queryAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		query.from(Book.class);

		// 获取List结果集
		List<Book> list = em.createQuery(query).getResultList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * select * from books where id = ?1 Root:定义查询的From字句中能出现的类型
	 */
	public void getById() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// 创建谓语 也就是条件
		Predicate predicate = builder.equal(root.get("id"), 1);
		query.where(predicate);

		// 只获取一个结果
		Book book = em.createQuery(query).getSingleResult();
		System.out.println(book);
	}

	/*
	 * 查找标题中含有java的 select * from books where title like '%java%'
	 */
	public void like() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// 创建谓语 也就是条件
		Predicate predicate = builder.like(root.get("title"), "%" + "java"+ "%");
		query.where(predicate);
		// 获取List结果集
		List<Book> list = em.createQuery(query).getResultList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * select * from books where type in (6,7)
	 */
	public void in() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// 创建谓语 也就是条件
		Predicate in = root.get("type").in(6, 7);
		query.where(in);
		// 获取List结果集
		List<Book> list = em.createQuery(query).getResultList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * select * from books where price between 50 and 60
	 */
	public void between() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// 创建谓语 也就是条件
		Predicate between = builder.between(root.get("price"), 50, 60);

		query.where(between);
		// 获取List结果集
		List<Book> list = em.createQuery(query).getResultList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * 多条件查询
	 */

	public void betweenAndIn() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// 创建谓语 也就是条件
		Predicate between = builder.between(root.get("price"), 50, 60);
		Predicate in = root.get("type").in(6, 7);
		query.where(between, in);
		// 获取List结果集
		List<Book> list = em.createQuery(query).getResultList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * 多条件查询，然后排序
	 * select * from books where type = 2 order by price desc;
	 */
	public void orderBy() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Predicate equal = builder.equal(root.get("type"), 2);
		query.where(equal);
		query.orderBy(builder.desc(root.get("price")));
		em.createQuery(query).getResultList().forEach(System.out::println);
		
	}
	
	/**
	 * type升序，price 降序
	 */
	public void order() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		query.orderBy(builder.asc(root.get("type")),builder.desc(root.get("price")));
		em.createQuery(query).getResultList().forEach(System.out::println);
	}
	
	/**
	 * select title,price from books order by type asc,price desc;
	 * 必须加构造函数
	 */
	public void orderAndThen() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		
		//查询实体的部分对象：1. 被查询对象必须按照查询结果创建构造函数，并且构造函数的顺序必须一致！
		query.multiselect(root.get("title"),root.get("price")); 
		query.where(builder.equal(root.get("type"), 2));
		query.orderBy(builder.desc(root.get("price")));
		em.createQuery(query).getResultList().forEach(System.out::println);
	}
	
	/**
	 * 只查询我需要的字段
	 * 
	 * 部分字段查询结果集，List<Object[]> 的返回形式,
	 * List<Object[]> 中包含多条查询结果 obgects,
	 * objects中包含 查询出的每个字段值,
	 * 遍历objects拿出那个字段值。
	 */
	public void orderTeturn() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Book> root = query.from(Book.class);
		
		query.multiselect(root.get("title"),root.get("price"));
		query.where(builder.equal(root.get("type"), 2));
		query.orderBy(builder.desc(root.get("price")));
		List<Object[]> list = em.createQuery(query).getResultList();
		for (Object[] objects : list) {
			System.out.println(objects[0]+"::"+objects[1]);
		}
	}
	
	/**
	 * select type, max(price) maxPrice,sum(price sumPrice) from books group by type;
	 */
	public void groupBy() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		//返回对象
		CriteriaQuery<BookInfo> query = builder.createQuery(BookInfo.class);
		//查询对象
		Root<Book> root = query.from(Book.class);
		query.multiselect(root.get("type"),builder.max(root.get("price")).alias("maxPrice"),builder.sum(root.get("price")).alias("sumPrice"));
		query.groupBy(root.get("type"));
		 em.createQuery(query).getResultList().forEach(System.out::println);
		
	}
	
	
	
}
