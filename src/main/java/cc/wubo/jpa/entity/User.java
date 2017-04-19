package cc.wubo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

@Entity
@Table(name="users")
//定义命名查询
//@NamedQuery(name="byId",query="select u from User u where u.id=3")
@NamedQueries({
	@NamedQuery(name="byId",query="select u from User u where u.id=3"),
	@NamedQuery(name="byList",query="select u from User u"),
	@NamedQuery(name="byUsername",query="select u from User u where u.username=?1"),
	@NamedQuery(name="pageList",query="select u from User u where u.status=?1"),
	@NamedQuery(name="pageCount",query="select count(u.id) from User u where u.status=?1")
})
//本地命名查询
@NamedNativeQueries({
	@NamedNativeQuery(name="bySql",query="select * from users where status = ?1",resultClass=User.class),
	@NamedNativeQuery(name="bySqlPhone",query="select phone,username from users where status = ?1",resultClass=Phone.class),
	//本地命名查询语句不支持排序
	//@NamedNativeQuery(name="bySort",query="select * from users",resultClass=User.class),
	//本地命名查询语句支持分页
	@NamedNativeQuery(name="list",query="select * from users",resultClass=User.class),
	@NamedNativeQuery(name="count",query="select count(*) from users",resultClass=User.class),
	
	//本地命名查询的扩展
	@NamedNativeQuery(name="native_list",query="select phone,username name from users",resultSetMapping="result"),
	@NamedNativeQuery(name="native_list2",query="select phone,username name from users",resultSetMapping="result2"),
	@NamedNativeQuery(name="native_list3",query="select phone,username name from users",resultSetMapping="result3"),
	@NamedNativeQuery(name="native_list4",query="select id,status,username name from users",resultSetMapping="result4"),
	@NamedNativeQuery(name="native_list5",query="select id,status,username name from users",resultSetMapping="result5")
	

})
//本地命名查询扩展之@SqlResultSetMappings,描述值与对象的映射关系 
@SqlResultSetMappings({
	/*@SqlResultSetMapping 中可以指定多个要映射的实体（@EntityResult）,
	 * EntityResult中有entityClass(需要映射的类)和fields(需要映射的字段)两个属性

	*/
	@SqlResultSetMapping(name = "result",entities= {@EntityResult(entityClass=NativeQuery_ext_Phone.class,
			fields= {@FieldResult(name="id",column="id"),@FieldResult(name="name",column="name")})}),
			
	@SqlResultSetMapping(name = "result2",entities= {@EntityResult(entityClass=NativeQuery_ext_Phone2.class,
	fields= {@FieldResult(name="id",column="id"),@FieldResult(name="username",column="name")})}),
	
	//classes,@ConstructorResult 方式 此时的name时sql查询显示的字段
	@SqlResultSetMapping(name = "result3",classes= {@ConstructorResult(targetClass=NativeQuery_ext_Phone3.class, 
	columns = { @ColumnResult(name="phone",type=String.class),@ColumnResult(name="name",type=String.class) })}),
	
	@SqlResultSetMapping(name = "result4",entities= {@EntityResult(entityClass=NativeQuery_ext_Phone4.class,
	fields= {@FieldResult(name="id",column="id"),@FieldResult(name="username",column="name")})},
	columns= {@ColumnResult(name="status",type=Integer.class)}),
	
	@SqlResultSetMapping(name = "result5",classes= {@ConstructorResult(targetClass=NativeQuery_ext_Phone4.class, 
			columns = { @ColumnResult(name="id",type=Integer.class),@ColumnResult(name="name",type=String.class)})},
	columns= {@ColumnResult(name="status",type=Integer.class)})
	
})
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String username;
	@Column
	private String phone;
	@Column
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", phone=" + phone
				+ ", status=" + status + "]";
	}
	
	
	

}
