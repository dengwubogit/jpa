package cc.wubo.jpa.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import cc.wubo.jpa.entity.Book;

public interface BookQueryByExampleRepository extends Repository<Book,Integer>,QueryByExampleExecutor<Book>{
	
	

}
 