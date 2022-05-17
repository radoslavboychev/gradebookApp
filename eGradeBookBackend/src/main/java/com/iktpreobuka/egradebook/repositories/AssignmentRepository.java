package com.iktpreobuka.egradebook.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.egradebook.entities.AssignmentEntity;
import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;

public interface AssignmentRepository extends PagingAndSortingRepository<AssignmentEntity, Long> {

	Page<AssignmentEntity> findAllByAssignedTo(StudentEntity student, Pageable pageable);

	@Query(value = "SELECT a FROM AssignmentEntity a JOIN a.assignedTo b WHERE b.belongsToStudentGroup =:homeroomGroup")
	Page<AssignmentEntity> findAllByStudentGroup(@Param("homeroomGroup") StudentGroupEntity homeroomGroup,
			Pageable pageable);

	@Query(value = "SELECT a FROM AssignmentEntity a JOIN a.teacherIssuing b WHERE b.teacher =:teacher")
	Page<AssignmentEntity> findAllByTeacherIssuing(@Param("teacher") TeacherEntity teacher, Pageable pageable);

	List<AssignmentEntity> findByAssignedTo(StudentEntity student);

	List<AssignmentEntity> findByTeacherIssuing(TeacherSubjectEntity teacherIssuing);
}
