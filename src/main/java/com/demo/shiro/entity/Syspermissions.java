package com.demo.shiro.entity;
 
public class Syspermissions  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//;
	private String id;
	//;
	private String permission;
	//;
	private String description;
	private Integer available;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
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
	 
}