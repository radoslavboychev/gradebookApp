package com.iktpreobuka.egradebook.dto.outbound;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "New Teacher")
@JsonPropertyOrder({ "name", "surname", "username", "email", "jmbg", "dateOfBirth", "startOfEmployment", "salary",
		"weeklyHourCapacity" })
public class CreatedTeacherDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Name")
	private String name;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Surname")
	private String surname;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "E-mail")
	private String email;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Username")
	private String username;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Unique ID number")
	private String jmbg;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Date of birth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Start of Employment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate startOfEmployment;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Salary")
	private Double salary;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Weekly hour capacity")
	private Integer weeklyHourCapacity;

	public CreatedTeacherDTO() {
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

	public Double getSalary() {
		return salary;
	}

	public LocalDate getStartOfEmployment() {
		return startOfEmployment;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public Integer getWeeklyHourCapacity() {
		return weeklyHourCapacity;
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

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setStartOfEmployment(LocalDate startOfEmployment) {
		this.startOfEmployment = startOfEmployment;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setWeeklyHourCapacity(Integer weeklyHourCapacity) {
		this.weeklyHourCapacity = weeklyHourCapacity;
	}

	@Override
	public String toString() {
		return "CreatedTeacherDTO [name=" + name + ", surname=" + surname + ", email=" + email + ", username="
				+ username + ", jmbg=" + jmbg + ", dateOfBirth=" + dateOfBirth + ", startOfEmployment="
				+ startOfEmployment + ", salary=" + salary + ", weeklyHourCapacity=" + weeklyHourCapacity + "]";
	}

}
