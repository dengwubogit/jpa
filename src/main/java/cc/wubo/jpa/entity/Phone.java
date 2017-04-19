package cc.wubo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Phone {
	@Id
	private String phone;
	private String username;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Phone [phone=" + phone + ", username=" + username + "]";
	}
	
}
