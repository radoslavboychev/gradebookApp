package com.iktpreobuka.egradebook.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;

public interface TeacherRepository extends PagingAndSortingRepository<TeacherEntity, Long> {

	Optional<TeacherEntity> findByInChargeOf(StudentGroupEntity studentGroup);

	Optional<TeacherEntity> findByUsername(String username);

}
