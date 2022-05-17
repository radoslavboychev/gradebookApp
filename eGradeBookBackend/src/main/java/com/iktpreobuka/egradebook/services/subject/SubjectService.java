package com.iktpreobuka.egradebook.services.subject;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.egradebook.dto.inbound.CreateSubjectDTO;
import com.iktpreobuka.egradebook.entities.SubjectEntity;

public interface SubjectService {

	public ResponseEntity<?> createdSubjectDTOtranslation(SubjectEntity subject);

	public SubjectEntity createSubjectDTOtranslation(CreateSubjectDTO subject);

	public Boolean isSubjectInEnum(String subject);

	public SubjectEntity updateSubjectDTOtranslation(CreateSubjectDTO subject, Long ogSubjectID);
}