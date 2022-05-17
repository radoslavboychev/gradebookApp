package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.egradebook.entities.userEntities.ParentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentParentEntity;

public interface StudentParentRepository extends CrudRepository<StudentParentEntity, Long> {

	List<StudentParentEntity> findByParent(ParentEntity parent);

	List<StudentParentEntity> findByStudent(StudentEntity student);

	Optional<StudentParentEntity> findByStudentAndParentAndDeleted(StudentEntity student, ParentEntity parent,
			Integer deleted);

}
