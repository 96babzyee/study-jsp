package com.jsp.dto;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String email;
	private String marketing;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMarketing() {
		return marketing;
	}

	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", marketing="
				+ marketing + "]";
	}
	
}
