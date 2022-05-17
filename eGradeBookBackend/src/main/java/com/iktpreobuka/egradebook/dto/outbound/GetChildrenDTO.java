package com.iktpreobuka.egradebook.dto.outbound;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Student")
@JsonPropertyOrder({ "name", "surname", "username", "role", "belongsToStudentGroup", "parents" })
public class GetChildrenDTO {

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
	@JsonProperty(value = "Parents")
	private Map<String, String> parents;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Student group")
	private String StudentGroup;

	public GetChildrenDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getParents() {
		return parents;
	}

	public String getRole() {
		return role;
	}

	public String getStudentGroup() {
		return StudentGroup;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParents(Map<String, String> parents) {
		this.parents = parents;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStudentGroup(String studentGroup) {
		StudentGroup = studentGroup;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "GetChildrenDTO [name=" + name + ", surname=" + surname + ", username=" + username + ", role=" + role
				+ ", parents=" + parents + ", StudentGroup=" + StudentGroup + "]";
	}

}
