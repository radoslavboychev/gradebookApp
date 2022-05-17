package com.iktpreobuka.egradebook.dto.outbound;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Student")
@JsonPropertyOrder({ "name", "surname", "username", "role", "phoneNumber", "children" })
public class GetParentsDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Name")
	private String name;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Surname")
	private String surname;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Username")
	private String username;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Role")
	private String role;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Phone number")
	private String phoneNumber;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Children")
	private Set<String> children = new HashSet<>();

	public GetParentsDTO() {
		super();
	}

	public Set<String> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public void setChildren(Set<String> children) {
		this.children = children;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
