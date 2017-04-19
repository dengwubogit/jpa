package cc.wubo.jpa;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import cc.wubo.jpa.entity.Book;
import cc.wubo.jpa.repository.BookCriteriaQueryRepository;

public class CriteriaQueryTest2 {
	private BookCriteriaQueryRepository repository = null;

	@Before
	public void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				App.class);
		repository = context.getBean(BookCriteriaQueryRepository.class);

	}

	/**
	 * 根据条件获取一条结果
	 * 执行的查询语句，要和当前方法名对应，findOne之能查询单条记录
	 */
	@Test
	public void findOne() throws InterruptedException, ExecutionException {

		Book book = repository.findOne(new Specification<Book>() { // 泛型表示：操作的实体
					// 操作的时一个实体，但是结果返回并不一定是一个实体
					public Predicate toPredicate(Root<Book> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						//最终的查询结果只能有一个，否则报错
						return cb.equal(root.get("id"), 1);
					}
				});
		System.out.println(book);

	}
	/**
	 * 查询并排序
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void findAll() throws InterruptedException, ExecutionException {
		List<Book> findAll = repository.findAll(new Specification<Book>() {
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.desc(root.get("price")),cb.asc(root.get("type")));
				return cb.isNotNull(root.get("title"));
			}
		});
		  for (Book book : findAll) {
			System.out.println(book);
		}
		System.out.println("==================第二种排序方式====================");
		 findAll = repository.findAll(new Specification<Book>() {
			
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				return cb.isNotNull(root.get("title"));
			}
		},new Sort(new Order(Direction.DESC,"price"),new Order(Direction.ASC, "type"))
		);
		 for (Book book : findAll) {
				System.out.println(book);
			}
		 
	}
	
	/**
	 * 分页--无排序
	 */
	@Test
	public void findByPage() {
		Page<Book> page = repository.findAll(new Specification<Book>() {
			
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				return cb.isNotNull(root.get("title"));
			}
		},new PageRequest(0, 3));
		List<Book> content = page.getContent();
		for (Book book : content) {
			System.out.println(book);
		}
	}

	/**
	 * 有排序的分页
	 * PageRequest 初始化方法是可以填入排序参数
	 */
	@Test
	public void findByPageAndSort() {
		Page<Book> page = repository.findAll(new Specification<Book>() {
			
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				return cb.isNotNull(root.get("title"));
			}
		},new PageRequest(0, 3,new Sort(new Order(Direction.DESC,"price"),new Order(Direction.ASC, "type"))));
		
		page.getContent().forEach(System.out::println);
	}
	/**
	 * count查询
	 */
	@Test
	public void getCount() {
		long count=repository.count(new Specification<Book>() {
			
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate between = cb.between(root.get("price"), 50, 60);
				return between;
			}
		});
		System.out.println("count:"+count);
	}
	
}
