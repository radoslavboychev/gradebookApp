package com.iktpreobuka.egradebook.services.utils.enums;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

public class RESTError {

	@JsonView(Views.Student.class)
	private Integer code;

	@JsonView(Views.Student.class)
	private String message;

	public RESTError(Integer code, String message) {

		this.code = code;
		this.message = message;

	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
