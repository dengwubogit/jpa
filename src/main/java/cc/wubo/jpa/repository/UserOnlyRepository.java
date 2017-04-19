package cc.wubo.jpa.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.wubo.jpa.entity.User;
@org.springframework.stereotype.Repository
public interface UserOnlyRepository extends Repository<User, Integer>{
	public User findByUsername(String username);
	public User findById(Integer id);
	public List<User> findByStatus(Integer status);
	public User findByUsernameAndPhone(String username, String phone);
	public List<User> findByUsernameOrPhone(String username, String phone);
	public List<User> findByPhoneInOrderByUsername(List<String> phones);
	public List<User> findByPhoneInOrderByUsernameDesc(List<String> phones);
	public List<User> findByPhoneIn(List<String> phones);
	public List<User> findByIdBetween(Integer left,Integer right);
	public List<User> findByIdLessThan(Integer id);
	public List<User> findByIdLessThanEqual(Integer id);
	
	public List<User> findByPhoneIsNotNull();
	//上下都是等效的
	//=============以下是查询的另外三种方法============
	public List<User> readByPhoneIsNotNull();
	public List<User> queryByPhoneIsNotNull();
	public List<User> getByPhoneIsNotNull();
	//查询前三条
	public List<User> queryTop3ByPhoneIsNotNull();
	public List<User> findFirst3ByPhoneIsNotNull();
	public List<User> findFirst5ByPhoneIsNotNullOrderByIdDesc();
	
	
	

}
