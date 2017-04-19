package cc.wubo.jpa.entity.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="persons")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer age;
	//@OneToOne //默认会在表里面加字段实现一对一关联关系，加的字段默认是属性名字+_+id,idCard_id
	//fetch=FetchType.EAGER 急加载
	//fetch=FetchType.LAZY	懒加载
	//optional 一个区别是
	//optional=true， 使用的是left join关联（@OneToOne里默认是true）
	//optional=false，使用的是inner join关联
	//注意将optional=false，在双向关联时会影响级联插入数据
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)//,optional=false) //设置级联关系--级联保存
	@JoinColumn(name="card_id") //自定义外键字段的名字
	private IdCard idCard;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public IdCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age
				+  "]";
	}
	
	
}
