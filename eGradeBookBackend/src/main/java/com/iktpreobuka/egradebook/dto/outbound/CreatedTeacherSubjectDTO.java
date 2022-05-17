package com.iktpreobuka.egradebook.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "New TeacherStudent")
@JsonPropertyOrder({ "name", "surname", "subject", "yearOfSchooling", "weeklyHoursAlloted" })
public class CreatedTeacherSubjectDTO {

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Weekly hours alloted to teacher")
	private Integer weeklyHoursAlloted;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Name")
	private String name;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Surname")
	private String surname;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Subject")
	private String subject;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Grade - year of schooling")
	private String yearOfSchooling;

	public CreatedTeacherSubjectDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getSubject() {
		return subject;
	}

	public String getSurname() {
		return surname;
	}

	public Integer getWeeklyHoursAlloted() {
		return weeklyHoursAlloted;
	}

	public String getYearOfSchooling() {
		return yearOfSchooling;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setWeeklyHoursAlloted(Integer weeklyHoursAlloted) {
		this.weeklyHoursAlloted = weeklyHoursAlloted;
	}

	public void setYearOfSchooling(String yearOfSchooling) {
		this.yearOfSchooling = yearOfSchooling;
	}

	@Override
	public String toString() {
		return "CreatedTeacherSubjectDTO [weeklyHoursAlloted=" + weeklyHoursAlloted + ", name=" + name + ", surname="
				+ surname + ", subject=" + subject + ", yearOfSchooling=" + yearOfSchooling + "]";
	}

}
