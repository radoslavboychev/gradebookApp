package com.iktpreobuka.egradebook.services.studentGroup;

import java.util.List;

import com.iktpreobuka.egradebook.dto.outbound.GETStudentGroupsDTO;
import com.iktpreobuka.egradebook.entities.StudentGroupEntity;

public interface StudentGroupService {

	public GETStudentGroupsDTO GETStudentGroupDTOtranslation(StudentGroupEntity studentGroup);

	public List<GETStudentGroupsDTO> GETStudentGroupsDTOtranslation(List<StudentGroupEntity> studentGroups);

}
