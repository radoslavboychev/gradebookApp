package com.iktpreobuka.egradebook.entities.userEntities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.entities.AssignmentEntity;
import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "Students")
@PrimaryKeyJoinColumn(name = "StudentID")
public class StudentEntity extends UserEntity {

	@Column(nullable = false)
	private String studentUniqueNumber;

	@JsonView(Views.Student.class)
	@JsonBackReference(value = "ref2")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "belongsToStudentGroup")
	private StudentGroupEntity belongsToStudentGroup;

	@JsonView(Views.Student.class)
	// @JsonManagedReference(value = "ref3")
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<StudentParentEntity> parents = new HashSet<>();

	@OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<AssignmentEntity> givenAssigmnents = new HashSet<>();

	public StudentEntity() {
		super();
	}

	public StudentGroupEntity getBelongsToStudentGroup() {
		return belongsToStudentGroup;
	}

	public Set<AssignmentEntity> getGivenAssigmnents() {
		return givenAssigmnents;
	}

	public Set<StudentParentEntity> getParents() {
		return parents;
	}

	public String getStudentUniqueNumber() {
		return studentUniqueNumber;
	}

	public void setBelongsToStudentGroup(StudentGroupEntity belongsToStudentGroup) {
		this.belongsToStudentGroup = belongsToStudentGroup;
	}

	public void setGivenAssigmnents(Set<AssignmentEntity> givenAssigmnents) {
		this.givenAssigmnents = givenAssigmnents;
	}

	public void setParents(Set<StudentParentEntity> parents) {
		this.parents = parents;
	}

	public void setStudentUniqueNumber(String studentUniqueNumber) {
		this.studentUniqueNumber = studentUniqueNumber;
	}

}
