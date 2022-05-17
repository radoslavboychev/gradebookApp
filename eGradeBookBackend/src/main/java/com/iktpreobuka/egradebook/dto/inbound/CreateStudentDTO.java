package com.iktpreobuka.egradebook.dto.inbound;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateStudentDTO {

	@NotNull(message = "First name must be provided.")
	@NotBlank(message = "First name can't be blank.")
	private String name;

	@NotNull(message = "Surname must be provided.")
	private String surname;

	@Email(message = "Please provide a valid email.")
	private String email;

	@NotNull(message = "Username must be provided.")
	@NotBlank(message = "Username mustn't be blank.")
	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.")
	private String username;

	@NotNull(message = "Cannot be null.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$", message = "Password invalid, it must contain at least 5 characters, both numbers and letters must be used.")
	private String password;

	@NotNull(message = "Cannot be null.")
	private String repeatedPassword;

	@NotNull(message = "Cannot be null.")
	@Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]\\d{8}$", message = "Provide a valid Unique ID number.")
	private String jmbg;

	@NotNull(message = "Cannot be null.")
	@Past(message = "Date of birth must be a date in the past.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	@NotNull(message = "Unique number must be provided.")
	@NotBlank(message = "Unique number can't be blank.")
	@Size(min = 7, max = 7, message = "School ID must be exactly {min} characters long.")
	private String studentUniqueNumber;

	public CreateStudentDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getPassword() {
		return password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
