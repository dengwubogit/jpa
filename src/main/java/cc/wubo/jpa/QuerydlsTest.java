package cc.wubo.jpa;

import java.util.concurrent.ExecutionException;import javax.persistence.criteria.Expression;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.EnableAsync;

import com.querydsl.core.types.Predicate;

import cc.wubo.jpa.entity.Book;
import cc.wubo.jpa.entity.QBook;
import cc.wubo.jpa.repository.BookQuerydlsRepository;
import cc.wubo.jpa.service.BookQuerydslService;


@EnableAsync
public class QuerydlsTest {

	/**
	 * 单一条件查询
	 */
	@Test
	public void queryOne() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Book book = repository.findOne(QBook.book.id.eq(1));//括号内是谓词 Querydls框架已经帮你生成好了
		System.out.println(book);
	}
	
	@Test
	public void queryAll() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Predicate predicate = null; // 将谓语置为null 就是查询所有
		Iterable<Book> findAll = repository.findAll(predicate);
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	
	@Test
	public void like() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.title.like("%java%"));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	@Test
	public void in() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.type.in(6,7));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	@Test
	public void between() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	
	/**
	 * 多条件查询
	 */
	@Test
	public void betweenAndIn() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60).and(QBook.book.type.in(6,7)));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	
	/**
	 * order by 排序
	 */
	@Test
	public void orderBy() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60),new Sort(new Order(Direction.DESC,"price")));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	/**
	 * order by 排序
	 */
	@Test
	public void orderBy2() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60),QBook.book.price.desc());
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	/**
	 * order by 多次排序
	 */
	@Test
	public void orderBy3() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60),QBook.book.price.desc(),QBook.book.type.asc());
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	
	/**
	 * 分页查询	
	 */
	@Test
	public void byPage() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		// 可以返回Page对象
		//Page<Book> page = repository.findAll(QBook.book.price.between(50,60),new PageRequest(0, 3));
		Iterable<Book> findAll = repository.findAll(QBook.book.price.between(50,60),new PageRequest(0, 3));
		for (Book book : findAll) {
			System.out.println(book);
		}
	}
	
	/**
	 * 查询是否存在
	 */
	@Test
	public void exists() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydlsRepository repository = context.getBean(BookQuerydlsRepository.class);
		// 可以返回Page对象
		//Page<Book> page = repository.findAll(QBook.book.price.between(50,60),new PageRequest(0, 3));
		boolean exists = repository.exists(QBook.book.price.between(800, 888));
		System.out.println(exists);
	}
	
	/**
	 * 查询部分字段
	 */
	@Test
	public void selectByPart(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydslService service = context.getBean(BookQuerydslService.class);
		service.query();
	}
	
	/**
	 * 聚合函数查询groupby
	 */
	@Test
	public void group(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQuerydslService service = context.getBean(BookQuerydslService.class);
		service.group();
	}
	
}
