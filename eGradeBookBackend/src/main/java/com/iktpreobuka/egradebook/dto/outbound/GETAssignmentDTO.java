package com.iktpreobuka.egradebook.dto.outbound;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Assignment")
@JsonPropertyOrder({ "id", "subject", "type", "description", "teacher", "assignedTo", "studentGroup", "semester",
		"dateCreated", "dateAssigned", "dueDate", "dateCompleted", "gradeRecieved", "overridenGrade", "deleted" })
public class GETAssignmentDTO {

	@JsonView(Views.Admin.class)
	private Long id;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Assignment type")
	private String type;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Description")
	private String description;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Semester")
	private Integer semester;

	@JsonView(Views.Teacher.class)
	@JsonProperty(value = "Date of creation")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateCreated;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Due date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dueDate;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Date assigned")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateAssigned;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Date of completion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateCompleted;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Assigned to")
	private String assignedTo;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Student group")
	private String studentGroup;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Teacher issuing")
	private String teacher;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Subject")
	private String subject;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Grade recieved")
	private Integer gradeRecieved;

	@JsonView(Views.Headmaster.class)
	@JsonProperty(value = "Overriden grade")
	private Integer overridenGrade;

	@JsonView(Views.Admin.class)
	private Integer deleted;

	public GETAssignmentDTO() {
		super();
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public LocalDate getDateAssigned() {
		return dateAssigned;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public Integer getGradeRecieved() {
		return gradeRecieved;
	}

	public Long getId() {
		return id;
	}

	public Integer getOverridenGrade() {
		return overridenGrade;
	}

	public Integer getSemester() {
		return semester;
	}

	public String getStudentGroup() {
		return studentGroup;
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

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setDateAssigned(LocalDate dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public void setDateCompleted(LocalDate dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setGradeRecieved(Integer gradeRecieved) {
		this.gradeRecieved = gradeRecieved;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOverridenGrade(Integer overridenGrade) {
		this.overridenGrade = overridenGrade;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
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
		return "GETAssignmentDTO [id=" + id + ", type=" + type + ", description=" + description + ", semester="
				+ semester + ", dateCreated=" + dateCreated + ", dueDate=" + dueDate + ", dateAssigned=" + dateAssigned
				+ ", dateCompleted=" + dateCompleted + ", assignedTo=" + assignedTo + ", studentGroup=" + studentGroup
				+ ", teacher=" + teacher + ", subject=" + subject + ", gradeRecieved=" + gradeRecieved
				+ ", overridenGrade=" + overridenGrade + ", deleted=" + deleted + ", getType()=" + getType()
				+ ", getDescription()=" + getDescription() + ", getSemester()=" + getSemester() + ", getDateCreated()="
				+ getDateCreated() + ", getTeacher()=" + getTeacher() + ", getSubject()=" + getSubject() + ", getId()="
				+ getId() + ", getDueDate()=" + getDueDate() + ", getDateAssigned()=" + getDateAssigned()
				+ ", getDateCompleted()=" + getDateCompleted() + ", getAssignedTo()=" + getAssignedTo()
				+ ", getStudentGroup()=" + getStudentGroup() + ", getGradeRecieved()=" + getGradeRecieved()
				+ ", getOverridenGrade()=" + getOverridenGrade() + ", getDeleted()=" + getDeleted() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
