package com.iktpreobuka.egradebook.dto;

import java.time.LocalDate;

public class EmailObjectDTO {

	private String to;
	private String cc;
	private String subject;
	private String studentName;
	private String studentLastName;
	private String teacherName;
	private String teacherLastName;
	private String gradedSubject;
	private String grade;
	private LocalDate date;
	private String assignment;
	private String description;
	private String overridenGrade;

	public EmailObjectDTO() {
	}

	public String getAssignment() {
		return assignment;
	}

	public String getCc() {
		return cc;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getGrade() {
		return grade;
	}

	public String getGradedSubject() {
		return gradedSubject;
	}

	public String getOverridenGrade() {
		return overridenGrade;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getSubject() {
		return subject;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getTo() {
		return to;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setGradedSubject(String gradedSubject) {
		this.gradedSubject = gradedSubject;
	}

	public void setOverridenGrade(String overridenGrade) {
		this.overridenGrade = overridenGrade;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "EmailObjectDTO [to=" + to + ", cc=" + cc + ", subject=" + subject + ", studentName=" + studentName
				+ ", studentLastName=" + studentLastName + ", teacherName=" + teacherName + ", teacherLastName="
				+ teacherLastName + ", gradedSubject=" + gradedSubject + ", grade=" + grade + ", date=" + date
				+ ", assignment=" + assignment + ", description=" + description + ", overridenGrade=" + overridenGrade
				+ ", getAssignment()=" + getAssignment() + ", getTo()=" + getTo() + ", getCc()=" + getCc()
				+ ", getSubject()=" + getSubject() + ", getStudentName()=" + getStudentName()
				+ ", getStudentLastName()=" + getStudentLastName() + ", getTeacherName()=" + getTeacherName()
				+ ", getTeacherLastName()=" + getTeacherLastName() + ", getGradedSubject()=" + getGradedSubject()
				+ ", getGrade()=" + getGrade() + ", getDate()=" + getDate() + ", getDescription()=" + getDescription()
				+ ", getOverridenGrade()=" + getOverridenGrade() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
