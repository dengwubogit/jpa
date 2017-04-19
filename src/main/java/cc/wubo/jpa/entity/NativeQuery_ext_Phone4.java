package cc.wubo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NativeQuery_ext_Phone4 {
	
	
	public NativeQuery_ext_Phone4(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	public NativeQuery_ext_Phone4() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private Integer id;
	private String username;
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
	@Override
	public String toString() {
		return "NativeQuery_ext_Phone4 [id=" + id + ", username=" + username
				+ "]";
	}
	
	
	
	
}
