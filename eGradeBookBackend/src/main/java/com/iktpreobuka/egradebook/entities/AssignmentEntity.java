package com.iktpreobuka.egradebook.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.enums.EAssignmentType;
import com.iktpreobuka.egradebook.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class AssignmentEntity {

	@Id
	@GeneratedValue
	@JsonView(Views.Admin.class)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Admin.class)
	private EAssignmentType type;

	@JsonView(Views.Admin.class)
	private String description;

	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private Integer semester;

	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private LocalDate dateCreated;

	@JsonView(Views.Admin.class)
	private LocalDate dateAssigned;

	@JsonView(Views.Admin.class)
	private LocalDate dateCompleted;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonView(Views.Admin.class)
	private LocalDate dueDate;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherIssuing")
	@JsonView(Views.Admin.class)
	private TeacherSubjectEntity teacherIssuing;

	@JsonView(Views.Admin.class)
	private Integer gradeRecieved;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "assignedTo")
	@JsonView(Views.Admin.class)
	private StudentEntity assignedTo;

	@JsonView(Views.Admin.class)
	private Integer overridenGrade;

	@JsonView(Views.Admin.class)
	private Integer deleted;

	@Version
	@JsonView(Views.Admin.class)
	private Integer version;

	public AssignmentEntity() {
		super();
	}

	public StudentEntity getAssignedTo() {
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

	public TeacherSubjectEntity getTeacherIssuing() {
		return teacherIssuing;
	}

	public EAssignmentType getType() {
		return type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setAssignedTo(StudentEntity assignedTo) {
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

	public void setTeacherIssuing(TeacherSubjectEntity teacherIssuing) {
		this.teacherIssuing = teacherIssuing;
	}

	public void setType(EAssignmentType type) {
		this.type = type;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
