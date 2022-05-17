package com.iktpreobuka.egradebook.dto.inbound;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateUserDTO {

	private String name;

	private String surname;

	@Email(message = "Please provide a valid email.")
	private String email;

	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long. Provide a valid username or leave null.")
	private String username;

	@Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]\\d{8}$", message = "Provide a valid Unique ID number or leave null.")
	private String jmbg;

	@Past(message = "Date of birth must be a date in the past.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	public UpdateUserDTO() {
		super();
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
