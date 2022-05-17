package com.iktpreobuka.egradebook.dto.outbound;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "New Assignment")
@JsonPropertyOrder({ "type", "description", "teacher", "subject", "year", "studyYear", "semester", "dateCreated" })
public class CreatedAssignmentDTO {

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Assignment type")
	private String type;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Description")
	private String description;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Semester")
	private Integer semester;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Date of creation")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateCreated;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Teacher issuing")
	private String teacher;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Subject")
	private String subject;

	public CreatedAssignmentDTO() {
		super();
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public Integer getSemester() {
		return semester;
	}

	public String getSubject() {
		return subject;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getType() {
		return type;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CreatedAssignmentDTO [type=" + type + ", description=" + description + ", semester=" + semester
				+ ", dateCreated=" + dateCreated + ", teacher=" + teacher + ", subject=" + subject + "]";
	}

}
