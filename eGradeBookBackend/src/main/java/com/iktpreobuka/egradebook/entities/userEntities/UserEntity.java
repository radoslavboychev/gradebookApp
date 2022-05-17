package com.iktpreobuka.egradebook.entities.userEntities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.entities.RoleEntity;
import com.iktpreobuka.egradebook.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

	@Id
	@GeneratedValue
	protected Long id;

	@Column(nullable = false)
	protected String name;

	@Column(nullable = false)
	protected String surname;

	@JsonView(Views.Admin.class)
	protected String email;

	@Column(nullable = false)
	protected String username;

	@Column(nullable = false)
	protected String password;

	@Transient
	@Column(nullable = false)
	protected String repeatedPassword;

	@Column(nullable = false)
	protected String jmbg;

	@Column(nullable = false)
	protected LocalDate dateOfBirth;

	@Column(nullable = false)
	protected Integer deleted;

	@Version
	protected Integer version;

	@JsonBackReference(value = "ref1")
	@JsonView(Views.Admin.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	protected RoleEntity role;

	public UserEntity() {
		super();
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public RoleEntity getRole() {
		return role;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public Integer getVersion() {
		return version;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
