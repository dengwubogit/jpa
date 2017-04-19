package cc.wubo.jpa.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.Repository;

import cc.wubo.jpa.entity.Book;

public interface BookQuerydlsRepository extends Repository<Book,Integer>,QueryDslPredicateExecutor<Book>{
	
	

}
 