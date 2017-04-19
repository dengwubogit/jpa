package cc.wubo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import cc.wubo.jpa.entity.NativeQuery_ext_Phone;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone2;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone3;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone4;
import cc.wubo.jpa.entity.User;
@org.springframework.stereotype.Repository
public interface UserNamedNativeQuery_ext_Repository extends Repository<User, Integer>{
	
	@Query(name="native_list",nativeQuery=true)
	public List<NativeQuery_ext_Phone> native_list();
	@Query(name="native_list2",nativeQuery=true)
	public List<NativeQuery_ext_Phone2> native_list2();
	@Query(name="native_list3",nativeQuery=true)
	public List<NativeQuery_ext_Phone3> native_list3();
	@Query(name="native_list4",nativeQuery=true)
	public List<Object[]> native_list4();
	@Query(name="native_list5",nativeQuery=true)
	public List<Object[]> native_list5();
	
}
