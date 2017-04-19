package cc.wubo.jpa.entity;

public class NativeQuery_ext_Phone3 {
	private String phone;
	private String username;
	
	
	public NativeQuery_ext_Phone3(String phone, String username) {
		this.phone = phone;
		this.username = username;
	}
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
		return "NativeQuery_ext_Phone3 [phone=" + phone + ", username="
				+ username + "]";
	}
	
	
	
}
