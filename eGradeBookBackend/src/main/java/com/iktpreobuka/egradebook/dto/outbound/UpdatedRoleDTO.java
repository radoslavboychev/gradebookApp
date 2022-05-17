package com.iktpreobuka.egradebook.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Updated Role")
@JsonPropertyOrder({ "username", "role" })
public class UpdatedRoleDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Username")
	private String username;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Role")
	private String role;

	public UpdatedRoleDTO() {
		super();
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
