package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.UserEntity;

public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {

	List<StudentEntity> findByBelongsToStudentGroup(StudentGroupEntity studentGroup);

	Optional<UserEntity> findByStudentUniqueNumber(String studentUniqueNumber);

}
