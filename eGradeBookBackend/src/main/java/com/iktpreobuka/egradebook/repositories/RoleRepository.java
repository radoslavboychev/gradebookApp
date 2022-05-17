package com.iktpreobuka.egradebook.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.egradebook.entities.RoleEntity;
import com.iktpreobuka.egradebook.enums.ERole;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByName(ERole rolename);

}
