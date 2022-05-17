package com.iktpreobuka.egradebook.services.assignments;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.egradebook.dto.inbound.CreateAssignmentDTO;
import com.iktpreobuka.egradebook.dto.outbound.GETAssignmentDTO;
import com.iktpreobuka.egradebook.entities.AssignmentEntity;

public interface AssignmentService {

	public AssignmentEntity createAssignmentDTOtranslation(CreateAssignmentDTO assignment);

	public ResponseEntity<?> createdAssignmentDTOtranslation(AssignmentEntity assignment);

	public GETAssignmentDTO getAssignmentDTOTranslation(AssignmentEntity assignement);

	public List<GETAssignmentDTO> getAssignmentsDTOTranslation(List<AssignmentEntity> assignemnts);

	public ResponseEntity<?> getAssignmentsPaginated(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);

	public ResponseEntity<?> getAssignmentsPaginatedForHomeroom(Long id, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder);

	public ResponseEntity<?> getAssignmentsPaginatedForStudent(Long id, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder);

	public ResponseEntity<?> getAssignmentsPaginatedForTeacher(Long id, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder);

	public ResponseEntity<?> sendEmailForGradedAssignemnt(AssignmentEntity assignment);

}
