package cc.wubo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class NativeQuery_ext_Phone {
	@Id
	private String phone;
	private String name;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "NativeQuery_ext_Phone [phone=" + phone + ", name=" + name + "]";
	}
	
	
}
