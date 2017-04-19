package cc.wubo.jpa.repository;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import cc.wubo.jpa.entity.User;
/**
 * 三种排序方法
 * 1.在方法名称上加OrderBy
 * 2.在JPQL语句里面加order by
 * 3.使用Sort参数，更加灵活，可以传入多种排序方式
 * @author dengwubo
 *
 */
@org.springframework.stereotype.Repository
public interface UserRepository_sort extends Repository<User, Integer>{
//======================排序=============================
	public List<User> queryByStatusOrderByUsernameDesc(Integer status);
	
	@Query("select u from User u where u.status=?1 order by u.username")
	public List<User> orderByUsername(Integer status);
	
	@Query("select u from User u where u.status=?1 order by u.username")
	public List<User> queryByStatus(Integer status,Sort sort);
	@Query("select u from User u")
	public List<User> queryAll(Sort sort);
	
//=======================分页==========================
	/**
	 * 分页（返回值是Page）主要注意如下2点：
	 * 1、参数必须有Pageable
	 * 2、参数Pageable不能和Sort同时使用，如果想要排序分页，则使用Pageable
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<User> findByStatus(Integer status,Pageable pageable);
	
	@Query("select u from User u")
	Page<User> findAll(Pageable pageable);
	/**
	 *Page,Slice 都可以用来分页
	 *Page内部会执行2条sql语句，一个是查询总记录数，一个是查询分页
	 *Slice内部只会执行一条sql语句，就是查询分页 
	 */
	@Query("select u from User u")
	Slice<User> getAll(Pageable pageable);
	
	// 异步: 方法上需要使用@Async 全局需要使用@EnableAsync
	@Async
	Future<User> queryById(Integer id);
	
	/*
	 * 方法的返回值都有哪些
	 * Entity
	 * List
	 * Set
	 * Collection
	 * Iterable
	 * array
	 * Page
	 * Slice
	 */
	
}
