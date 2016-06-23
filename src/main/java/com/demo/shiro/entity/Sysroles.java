package com.demo.shiro.entity;

 
public class Sysroles   implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//;
	private String id;
	//;
	private String role;
	//;
	private String description;
	//;
	private Integer available;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
 
	//;
}