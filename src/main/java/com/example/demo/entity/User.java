package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@ApiModel(description = "This is User Entity, which stores the Authentication and Authorization Data")
public class User {
	
	
	@Id
	private Integer uid;
	@Column
	private String name;
	@Column
	private String username;
	@Column
	private String password;
	@Column(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles_tab",joinColumns = @JoinColumn(name="uid"))
	private Set<String> roles;
	public User(Integer uid, String name, String username, String password, Set<String> roles) {
		super();
		this.uid = uid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public User() {
		super();
	}
	
	
}
