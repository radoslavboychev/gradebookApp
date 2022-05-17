package com.iktpreobuka.egradebook.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "StudentGroup")
public class StudentGroupEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String year;

	@Column(nullable = false)
	private Integer yearIndex;

	@JsonManagedReference(value = "ref2")
	@OneToMany(mappedBy = "belongsToStudentGroup", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<StudentEntity> students = new HashSet<>();

	@OneToOne(mappedBy = "inChargeOf", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private TeacherEntity homeroomTeacher;

	@OneToMany(mappedBy = "studentGroup", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<StudentGroupTakingASubjectEntity> subjectsTaken = new HashSet<>();

	private Integer deleted;

	@Version
	private Integer version;

	public StudentGroupEntity() {
		super();
	}

	public Integer getDeleted() {
		return deleted;
	}

	public TeacherEntity getHomeroomTeacher() {
		return homeroomTeacher;
	}

	public Long getId() {
		return id;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public Set<StudentGroupTakingASubjectEntity> getSubjectsTaken() {
		return subjectsTaken;
	}

	public Integer getVersion() {
		return version;
	}

	public String getYear() {
		return year;
	}

	public Integer getYearIndex() {
		return yearIndex;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setHomeroomTeacher(TeacherEntity homeroomTeacher) {
		this.homeroomTeacher = homeroomTeacher;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	public void setSubjectsTaken(Set<StudentGroupTakingASubjectEntity> subjectsTaken) {
		this.subjectsTaken = subjectsTaken;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setYearIndex(Integer yearIndex) {
		this.yearIndex = yearIndex;
	}

}
