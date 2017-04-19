package cc.wubo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class NativeQuery_ext_Phone2 {
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
		return "NativeQuery_ext_Phone2 [phone=" + phone + ", username="
				+ username + "]";
	}
	
	
	
}
