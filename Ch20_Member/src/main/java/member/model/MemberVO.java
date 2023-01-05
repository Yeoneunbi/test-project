package member.model;

public class MemberVO {
	private String name;
	private String id;
	private String password;
	private String email;
	private String tel;
	private String role;
	
	public MemberVO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", password=" + password + ", email=" + email + ", tel=" + tel
				+ ", role=" + role + "]";
	}
	
	
	
}
