package cc.wubo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cc.wubo.jpa.entity.User;
@org.springframework.stereotype.Repository
public interface UserCustomRepository extends Repository<User, Integer>{
	/**
	 * Jpql语句
	 * @return
	 */
	@Query("select u from User u")
	public List<User> list();
	
	@Query("select u from User u where u.phone=?1")
	public List<User> takeByPhone(String phone);
	
	@Query("select u from User u where u.phone=?1 or username=?2")
	public List<User> takeByPhone(String phone,String username);
	
	/**
	 * 参数绑定
	 * @param username
	 * @param phone
	 * @return
	 */
	@Query("select u from User u where u.phone=:phone or username=:name")
	public List<User> takeByPhoneOrUsername(@Param("name")String username,@Param("phone")String phone);
	@Transactional
	@Query("update User u set u.status=3 where id = 10")
	@Modifying
	public Integer updateUser();
	@Transactional
	@Query("update User u set u.status=?1 where id = ?2")
	@Modifying
	public Integer updateUser(Integer status, String id);
	
	@Transactional
	@Query("delete from User u where u.username=?1")
	@Modifying
	public Integer deleteByUsername(String username);
	
}
