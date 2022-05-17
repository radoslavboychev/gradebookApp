package com.iktpreobuka.egradebook.entities.userEntities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;
import com.iktpreobuka.egradebook.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "Teachers")
@PrimaryKeyJoinColumn(name = "TeacherID")
public class TeacherEntity extends UserEntity {

	@JsonView(Views.Headmaster.class)
	@Column(nullable = false)
	private LocalDate startOfEmployment;

	@JsonView(Views.Headmaster.class)
	@Column(nullable = false)
	private Double salary;

	@JsonView(Views.Teacher.class)
	@Column(nullable = false)
	private Integer isHomeroomTeacher;

	@JsonView(Views.Teacher.class)
	@Column(nullable = false)
	private Integer isHeadmaster;

	@JsonView(Views.Teacher.class)
	@Column(nullable = false)
	private Integer isAdministrator;

	@JsonView(Views.Headmaster.class)
	private Double salaryHomeroomBonus;

	@JsonView(Views.Headmaster.class)
	private Double salaryHeadmasterBonus;

	@JsonView(Views.Headmaster.class)
	private Double salaryAdminBonus;

	@JsonView(Views.Headmaster.class)
	@Column(nullable = false)
	private Integer weeklyHourCapacity;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "inChargeOf")
	private StudentGroupEntity inChargeOf;

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<TeacherSubjectEntity> subjectsTeaching = new HashSet<>();

	public TeacherEntity() {
		super();
	}

	public StudentGroupEntity getInChargeOf() {
		return inChargeOf;
	}

	public Integer getIsAdministrator() {
		return isAdministrator;
	}

	public Integer getIsHeadmaster() {
		return isHeadmaster;
	}

	public Integer getIsHomeroomTeacher() {
		return isHomeroomTeacher;
	}

	public Double getSalary() {
		return salary;
	}

	public Double getSalaryAdminBonus() {
		return salaryAdminBonus;
	}

	public Double getSalaryHeadmasterBonus() {
		return salaryHeadmasterBonus;
	}

	public Double getSalaryHomeroomBonus() {
		return salaryHomeroomBonus;
	}

	public LocalDate getStartOfEmployment() {
		return startOfEmployment;
	}

	public Set<TeacherSubjectEntity> getSubjectsTeaching() {
		return subjectsTeaching;
	}

	public Integer getWeeklyHourCapacity() {
		return weeklyHourCapacity;
	}

	public void setInChargeOf(StudentGroupEntity inChargeOf) {
		this.inChargeOf = inChargeOf;
	}

	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public void setIsHeadmaster(Integer isHeadmaster) {
		this.isHeadmaster = isHeadmaster;
	}

	public void setIsHomeroomTeacher(Integer isHomeroomTeacher) {
		this.isHomeroomTeacher = isHomeroomTeacher;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setSalaryAdminBonus(Double salaryAdminBonus) {
		this.salaryAdminBonus = salaryAdminBonus;
	}

	public void setSalaryHeadmasterBonus(Double salaryHeadmasterBonus) {
		this.salaryHeadmasterBonus = salaryHeadmasterBonus;
	}

	public void setSalaryHomeroomBonus(Double salaryHomeroomBonus) {
		this.salaryHomeroomBonus = salaryHomeroomBonus;
	}

	public void setStartOfEmployment(LocalDate startOfEmployment) {
		this.startOfEmployment = startOfEmployment;
	}

	public void setSubjectsTeaching(Set<TeacherSubjectEntity> subjectsTeaching) {
		this.subjectsTeaching = subjectsTeaching;
	}

	public void setWeeklyHourCapacity(Integer weeklyHourCapacity) {
		this.weeklyHourCapacity = weeklyHourCapacity;
	}

}
