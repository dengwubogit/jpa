package cc.wubo.jpa;

import java.util.concurrent.ExecutionException;

import javax.persistence.criteria.Expression;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.ExampleMatcher.PropertyValueTransformer;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.EnableAsync;

import cc.wubo.jpa.entity.Book;
import cc.wubo.jpa.repository.BookQueryByExampleRepository;

/**
 * 原理
 * 根据所有不为null的属性作为条件去查询
 * 注意：
 * 1:Entity 里面不能有基本数据类型，因为基本数据类型有默认值
 * 2:不支持or，仅支持简单的查询条件
 * @author dengwubo
 *
 */
public class QueryByExampleTest {


	/**
	 * 注意QueryByExample在查询时会绑定一个pojo对象，根据所有不为null的属性作为条件去查询，拼接到where字段后
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void findOne() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setId(1);
		Book findOne = repository.findOne(Example.of(book));
		System.out.println(findOne);
	}
	@Test
	public void findAll() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setType(2);
		book.setAuthor("zhangsan");
		Iterable<Book> findAll = repository.findAll(Example.of(book));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	
	
	/**
	 * 匹配模式
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void matcher() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setAuthor("李");
		//设置匹配方式，前提时查询对象只设置了一个String属性
		//ExampleMatcher me = ExampleMatcher.matching().withStringMatcher(StringMatcher.STARTING);
		//设置哪一个属性，使用哪一种匹配方式
		ExampleMatcher me = ExampleMatcher.matching().withMatcher("author", GenericPropertyMatcher.of(StringMatcher.STARTING));
		//me.withMatcher("author", GenericPropertyMatcher.of(StringMatcher.STARTING));
		Iterable<Book> findAll = repository.findAll(Example.of(book, me));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	/**
	 * 名字里面含有三 并且  title里含有java的
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void matcher2() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setAuthor("三");
		book.setTitle("java");
		//多条件模糊查询，指定单个属性的匹配模式
		ExampleMatcher me = ExampleMatcher.matching().
				withMatcher("author", GenericPropertyMatcher.of(StringMatcher.CONTAINING)).
				withMatcher("title", GenericPropertyMatcher.of(StringMatcher.CONTAINING));
		Iterable<Book> findAll = repository.findAll(Example.of(book, me));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	
	/**
	 * ExampleMatcher.matching(). api的使用
	 */
	@Test
	public void matcher3() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setAuthor("张三");
		book.setTitle("javap");
		/*
		//忽略指定属性的大小写进行查询
		ExampleMatcher me = ExampleMatcher.matching().withIgnoreCase("title");
		//忽略所有已经设置的属性的大小写进行查询
		ExampleMatcher me2 = ExampleMatcher.matching().withIgnoreCase();*/
		// withIgnorePaths 查询时忽略某个属性,也就是忽略某个条件
		ExampleMatcher me3 = ExampleMatcher.matching().withIgnorePaths("title");
		Iterable<Book> findAll = repository.findAll(Example.of(book,me3));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
				
	}
	/**
	 *  INCLUDE查询所有属性为空的条件
	 *  IGNORE 忽略所有属性为空的条件
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void withNullHandler() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		
		ExampleMatcher me3 = ExampleMatcher.matching().withNullHandler(NullHandler.IGNORE);
		Iterable<Book> findAll = repository.findAll(Example.of(book,me3));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	/**
	 *  转换原始属性为我们自己更改后的属性
	 *  对某个属性的值进行转换，最终使用转换后的值进行查询
	 */
	@Test
	public void withTransformer() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		book.setAuthor("三");
		ExampleMatcher me3 = ExampleMatcher.matching().withTransformer("author", new  PropertyValueTransformer() {

			@Override
			public Object convert(Object source) {
				System.out.println(source);
				return "张三";
			}
			
		});
		Iterable<Book> findAll = repository.findAll(Example.of(book,me3));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	
	@Test
	public void sort() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		
		Iterable<Book> findAll = repository.findAll(Example.of(book),new Sort(new Order(Direction.DESC,"price")));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	@Test
	public void page() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		
		Iterable<Book> findAll = repository.findAll(Example.of(book),new PageRequest(0, 3, new Sort(new Order(Direction.DESC,"price"))));
		for (Book book2 : findAll) {
			System.out.println(book2);
		}
		
	}
	@Test
	public void count() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		
		long count = repository.count(Example.of(book));
		
	}
	
	@Test
	public void exists() throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookQueryByExampleRepository repository = context.getBean(BookQueryByExampleRepository.class);
		Book book = new Book();
		
		boolean flag = repository.exists(Example.of(book));
		
	}
	
	
	
}
