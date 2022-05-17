package com.iktpreobuka.egradebook.services.teacherSubject;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.egradebook.dto.inbound.CreateTeacherSubjectDTO;
import com.iktpreobuka.egradebook.dto.outbound.GETTeacherSubjectDTO;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;

public interface TeacherSubjectService {

	public ResponseEntity<?> createdTeacherSubjectDTOtranslation(TeacherSubjectEntity teacherSubject);

	public TeacherSubjectEntity createTeacherSubjectDTOtranslation(CreateTeacherSubjectDTO teacherSubject);

	public GETTeacherSubjectDTO GETTeacherSubjectDTOtranslation(TeacherSubjectEntity teacherSubject);

	public List<GETTeacherSubjectDTO> GETTeacherSubjectsDTOtranslation(List<TeacherSubjectEntity> teacherSubjects);

}