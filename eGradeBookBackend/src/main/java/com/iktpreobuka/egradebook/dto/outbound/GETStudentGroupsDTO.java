package com.iktpreobuka.egradebook.dto.outbound;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Student group")
@JsonPropertyOrder({ "id", "designation", "homeroomTeacher", "students", "subjectsTaken", "deleted" })
public class GETStudentGroupsDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "id")
	private Long id;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Designation")
	private String designation;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Students")
	private Set<String> students = new HashSet<>();

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Homeroom teacher")
	private String homeroomTeacher;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Subjects taken")
	private Set<String> subjectsTaken = new HashSet<>();

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "deleted")
	private Integer deleted;

	public GETStudentGroupsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDeleted() {
		return deleted;
	}

	public String getDesignation() {
		return designation;
	}

	public String getHomeroomTeacher() {
		return homeroomTeacher;
	}

	public Long getId() {
		return id;
	}

	public Set<String> getStudents() {
		return students;
	}

	public Set<String> getSubjectsTaken() {
		return subjectsTaken;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setHomeroomTeacher(String homeroomTeacher) {
		this.homeroomTeacher = homeroomTeacher;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStudents(Set<String> students) {
		this.students = students;
	}

	public void setSubjectsTaken(Set<String> subjectsTaken) {
		this.subjectsTaken = subjectsTaken;
	}

	@Override
	public String toString() {
		return "GETStudentGroupsDTO [id=" + id + ", designation=" + designation + ", students=" + students
				+ ", homeroomTeacher=" + homeroomTeacher + ", subjectsTaken=" + subjectsTaken + ", deleted=" + deleted
				+ "]";
	}

}
