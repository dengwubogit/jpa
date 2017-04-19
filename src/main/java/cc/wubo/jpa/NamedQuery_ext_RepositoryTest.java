package cc.wubo.jpa;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.wubo.jpa.entity.NativeQuery_ext_Phone;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone2;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone3;
import cc.wubo.jpa.entity.NativeQuery_ext_Phone4;
import cc.wubo.jpa.repository.UserNamedNativeQuery_ext_Repository;

@EnableTransactionManagement

public class NamedQuery_ext_RepositoryTest {

	@Test
	public void native_list() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserNamedNativeQuery_ext_Repository repository = context.getBean(UserNamedNativeQuery_ext_Repository.class);
		List<NativeQuery_ext_Phone> list = repository.native_list();
		for (NativeQuery_ext_Phone nativeQuery_ext_Phone : list) {
			System.out.println(nativeQuery_ext_Phone );
			
		}
		context.close();
	}
	@Test
	public void native_list2() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserNamedNativeQuery_ext_Repository repository = context.getBean(UserNamedNativeQuery_ext_Repository.class);
		List<NativeQuery_ext_Phone2> list = repository.native_list2();
		for (NativeQuery_ext_Phone2 nativeQuery_ext_Phone : list) {
			System.out.println(nativeQuery_ext_Phone );
			
		}
		context.close();
	}
	@Test
	public void native_list3() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserNamedNativeQuery_ext_Repository repository = context.getBean(UserNamedNativeQuery_ext_Repository.class);
		List<NativeQuery_ext_Phone3> list = repository.native_list3();
		for (NativeQuery_ext_Phone3 nativeQuery_ext_Phone : list) {
			System.out.println(nativeQuery_ext_Phone );
			
		}
		context.close();
	}
	@Test
	public void native_list4() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserNamedNativeQuery_ext_Repository repository = context.getBean(UserNamedNativeQuery_ext_Repository.class);
		List<Object[]> list = repository.native_list4();
		for (Object[] nativeQuery_ext_Phone : list) {
			System.out.println(nativeQuery_ext_Phone[0]+":"+nativeQuery_ext_Phone[1] );
			
		}
		context.close();
	}
	@Test
	public void native_list5() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		UserNamedNativeQuery_ext_Repository repository = context.getBean(UserNamedNativeQuery_ext_Repository.class);
		List<Object[]> list = repository.native_list5();
		for (Object[] nativeQuery_ext_Phone : list) {
			System.out.println(nativeQuery_ext_Phone[0]+":"+nativeQuery_ext_Phone[1] );
			
		}
		context.close();
	}
	
}
