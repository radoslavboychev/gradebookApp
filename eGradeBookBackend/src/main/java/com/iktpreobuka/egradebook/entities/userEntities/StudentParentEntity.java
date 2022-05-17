package com.iktpreobuka.egradebook.entities.userEntities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "Students_and_parents")
public class StudentParentEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentID")
	private ParentEntity parent;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "studentID")
	private StudentEntity student;

	private Integer deleted;

	@Version
	private Integer version;

	public StudentParentEntity() {
		super();
	}

	public Integer getDeleted() {
		return deleted;
	}

	public Long getId() {
		return id;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public Integer getVersion() {
		return version;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}