package com.iktpreobuka.egradebook.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.entities.userEntities.UserEntity;
import com.iktpreobuka.egradebook.enums.ERole;
import com.iktpreobuka.egradebook.security.Views;

@Entity
@Table(name = "Role")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class RoleEntity {

	@Id
	@GeneratedValue
	@JsonView(Views.Admin.class)
	@Column(name = "role_id") // bitno za app.prop
	private Long id;

	@JsonView(Views.Admin.class)
	@NotNull(message = "Cannot be null.")
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private ERole name;

	@JsonView(Views.Admin.class)
	@JsonManagedReference(value = "ref1")
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<UserEntity> users = new HashSet<>();

	public RoleEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public ERole getName() {
		return name;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

}