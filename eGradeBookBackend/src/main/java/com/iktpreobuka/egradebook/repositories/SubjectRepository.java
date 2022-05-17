package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.egradebook.entities.SubjectEntity;
import com.iktpreobuka.egradebook.services.utils.enums.ESubjectName;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {

	List<SubjectEntity> findAllByName(ESubjectName subjectName);

	Optional<SubjectEntity> findByName(ESubjectName subjectName);

	Optional<SubjectEntity> findByNameAndYearOfSchooling(ESubjectName subjectName, String yearOfSchooling);

}
