package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.egradebook.entities.SubjectEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;
import com.iktpreobuka.egradebook.services.utils.enums.ESubjectName;

public interface TeacherSubjectRepository extends CrudRepository<TeacherSubjectEntity, Long> {

	List<TeacherSubjectEntity> findAllBySubject(SubjectEntity subject);

	List<TeacherSubjectEntity> findAllByTeacher(TeacherEntity teacher);

	Optional<TeacherSubjectEntity> findByIdAndDeleted(Long id, Integer deleted);

	@Query(value = "SELECT a FROM TeacherSubjectEntity a " + "LEFT JOIN FETCH a.teacher b "
			+ "LEFT JOIN FETCH a.subject c " + "WHERE b.username = :username and c.name = :subject")
	Optional<TeacherSubjectEntity> findBySubjectAndTeacher(@Param("subject") ESubjectName subject,
			@Param("username") String username);

	Optional<TeacherSubjectEntity> findByTeacherAndSubject(TeacherEntity teacher, SubjectEntity subject);

}
