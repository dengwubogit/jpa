package cc.wubo.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import cc.wubo.jpa.entity.Book;
import cc.wubo.jpa.entity.QBook;


@Service
public class BookQuerydslService {
	@Autowired
	private EntityManager em;

	public void query(){
		JPAQuery<?> query = new JPAQuery<Book>(em);
		//查询title与price两个字段,条件是type=2 并且按价格降序分组排序
		JPAQuery<Tuple> resultSet = query.select(QBook.book.title,QBook.book.price).
				from(QBook.book).
				where(QBook.book.type.eq(2)).
				orderBy(QBook.book.price.desc());
		List<Tuple> fetch = resultSet.fetch();
		//Tuple 其实是一个被封装了的数组,提供了get等方法可以获取返回的数据
		for (Tuple tuple : fetch) {
			System.out.println(tuple.get(0, String.class)+"...."+tuple.get(1, Double.class));
		}
	}
	/**
	 * 使用聚合函数查询
	 */
	public void group(){
		JPAQuery<?> query = new JPAQuery<Book>(em);
		//查询title与price两个字段,条件是type=2 并且按价格降序分组排序
		JPAQuery<Tuple> resultSet = query.select(QBook.book.type,QBook.book.price.max(),QBook.book.price.sum()).
				from(QBook.book).
				where(QBook.book.type.between(2, 7)).
				groupBy(QBook.book.type);
		List<Tuple> fetch = resultSet.fetch();
		//Tuple 其实是一个被封装了的数组,提供了get等方法可以获取返回的数据
		for (Tuple tuple : fetch) {
			System.out.println(tuple.get(0, Integer.class)+"...."+tuple.get(1, Double.class)+"...."+tuple.get(2, Double.class));
		}
	}
	
	
	
}
