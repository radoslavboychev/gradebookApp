package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.StudentGroupTakingASubjectEntity;
import com.iktpreobuka.egradebook.entities.SubjectEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;

public interface StudentGroupTakingASubjectRepository extends CrudRepository<StudentGroupTakingASubjectEntity, Long> {

	@Query(value = "SELECT s FROM StudentGroupTakingASubjectEntity s LEFT JOIN FETCH s.teacherSubject t WHERE t.subject = :subject")
	List<StudentGroupTakingASubjectEntity> findAllBySubject(@Param(value = "subject") SubjectEntity subject);

	List<StudentGroupTakingASubjectEntity> findAllByTeacherSubject(TeacherSubjectEntity teacherSubject);

	Optional<StudentGroupTakingASubjectEntity> findByStudentGroupAndTeacherSubject(StudentGroupEntity studentGroup,
			TeacherSubjectEntity teacherSubject);

}
