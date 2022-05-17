package com.iktpreobuka.egradebook.dto.outbound;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Teacher-subject")
@JsonPropertyOrder({ "id", "subject", "teacher", "weeklyHoursAlloted", "studentGroupsTakingASubject", "deleted" })
public class GETTeacherSubjectDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "id")
	private Long id;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Weekly hours alloted")
	private Integer weeklyHoursAlloted;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Teacher")
	private String teacher;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Subject")
	private String subject;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Student groups taking")
	private Set<String> studentGroupsTakingASubject = new HashSet<>();

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Deleted")
	private Integer deleted;

	public GETTeacherSubjectDTO() {
		super();
	}

	public Integer getDeleted() {
		return deleted;
	}

	public Long getId() {
		return id;
	}

	public Set<String> getStudentGroupsTakingASubject() {
		return studentGroupsTakingASubject;
	}

	public String getSubject() {
		return subject;
	}

	public String getTeacher() {
		return teacher;
	}

	public Integer getWeeklyHoursAlloted() {
		return weeklyHoursAlloted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStudentGroupsTakingASubject(Set<String> studentGroupsTakingASubject) {
		this.studentGroupsTakingASubject = studentGroupsTakingASubject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setWeeklyHoursAlloted(Integer weeklyHoursAlloted) {
		this.weeklyHoursAlloted = weeklyHoursAlloted;
	}

	@Override
	public String toString() {
		return "GETTeacherSubjectDTO [id=" + id + ", weeklyHoursAlloted=" + weeklyHoursAlloted + ", teacher=" + teacher
				+ ", subject=" + subject + ", studentGroupsTakingASubject=" + studentGroupsTakingASubject + ", deleted="
				+ deleted + "]";
	}

}
