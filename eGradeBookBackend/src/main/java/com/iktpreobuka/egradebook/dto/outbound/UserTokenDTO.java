package com.iktpreobuka.egradebook.dto.outbound;

public class UserTokenDTO {

	private String username;

	private String token;

	public UserTokenDTO() {
		super();
	}

	public UserTokenDTO(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
