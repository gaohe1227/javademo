package com.demo.shiro.entity;
 
public class Rolespermissions   implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//;
	private String id;
	//;
	private String role_name;
	//;
	private String permission;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

	 
 

}