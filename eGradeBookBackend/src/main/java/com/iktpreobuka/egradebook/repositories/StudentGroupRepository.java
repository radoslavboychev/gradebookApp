package com.iktpreobuka.egradebook.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.egradebook.entities.StudentGroupEntity;

public interface StudentGroupRepository extends CrudRepository<StudentGroupEntity, Long> {

	Optional<StudentGroupEntity> findByIdAndDeleted(Long id, Integer deleted);

	Optional<StudentGroupEntity> findByYearAndYearIndex(String year, Integer yearIndex);

}
