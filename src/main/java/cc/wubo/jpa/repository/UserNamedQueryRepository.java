package cc.wubo.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import cc.wubo.jpa.entity.Phone;
import cc.wubo.jpa.entity.User;
@org.springframework.stereotype.Repository
public interface UserNamedQueryRepository extends Repository<User, Integer>{
	@Query(name="byId")
	public User byId();
	@Query(name="byList")
	public List<User> byList();
	@Query(name="byUsername")
	public User byUsername(String username);
	
	//===分页查询===
	//countQuery的用法
	@Query(value="select u from User u where u.status=?1",countQuery="select count(u.id) from User u where u.status=?1")
	public Page<User> byPage(Integer status,Pageable pageable);
	//countName的用法
	@Query(name="pageList",countName="pageCount")
	public Page<User> byPage2(Integer status,Pageable pageable);
	//===分页查询===
	
	
	//nativeQuery=true 支持sql语句查询，nativeQuery=false 支持jbql语句查询
	@Query(value="select * from users where id = ?1",nativeQuery=true)
	public User byId(Integer id);
	@Query(name="bySql",nativeQuery=true)
	public List<User> bySql(Integer status);
	/**
	 * 将查询的内容映射到另一个对象中
	 * 1.被映射的对象必须被@Entity标注
	 * 2.被映射的对象必须有主键：@Id标注。hibernate的规范
	 * @param status
	 * @return
	 */
	@Query(name="bySqlPhone",nativeQuery=true)
	public List<Phone> bySqlPhone(Integer status);
	
	//sql语句排序,自定义sql语句不支持排序
	/*@Query(name="bySort",nativeQuery=true)
	public List<User> bySort(Sort sort);*/
	//自定义sql语句支持分页
	@Query(name="list",countName="count",nativeQuery=true)
	public List<User> pageList(Pageable pageable);
	
}
