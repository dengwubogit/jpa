package cc.wubo.jpa.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import cc.wubo.jpa.entity.Book;
/**
 * 1: 定义一个 Repository(两种方式任选一个)
 * 2: 继承JpaSpecificationExecutor
 * 调用的时候只要传入Specification实现类即可查询
 */
public interface BookCriteriaQueryRepository extends Repository<Book, Integer>,JpaSpecificationExecutor<Book>{
	
	
	
}
