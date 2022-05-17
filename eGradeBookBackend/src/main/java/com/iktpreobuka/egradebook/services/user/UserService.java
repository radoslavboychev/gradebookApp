package com.iktpreobuka.egradebook.services.user;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.egradebook.dto.inbound.CreateParentDTO;
import com.iktpreobuka.egradebook.dto.inbound.CreateStudentDTO;
import com.iktpreobuka.egradebook.dto.inbound.CreateTeacherDTO;
import com.iktpreobuka.egradebook.dto.inbound.UpdateUserDTO;
import com.iktpreobuka.egradebook.dto.outbound.GetChildrenDTO;
import com.iktpreobuka.egradebook.dto.outbound.GetParentsDTO;
import com.iktpreobuka.egradebook.dto.outbound.GetUserDTO;
import com.iktpreobuka.egradebook.entities.userEntities.ParentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentParentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;
import com.iktpreobuka.egradebook.entities.userEntities.UserEntity;

public interface UserService {

	public Boolean amIAdmin();

	public Boolean amIHeadmaster();

	public Boolean amIHomeroom();

	public Boolean amIParent();

	public Boolean amIStudent();

	public Boolean amITeacher();

	public Boolean areWeRelated(ParentEntity parent, StudentEntity student);

	public ResponseEntity<?> createdParentDTOtranslation(ParentEntity parent);

	public ResponseEntity<?> createdStudentDTOtranslation(StudentEntity student);

	public ResponseEntity<?> createdTeacherDTOtranslation(TeacherEntity teacher);

	public String createJWTToken(UserEntity user);

	public ParentEntity createParentDTOtranslation(CreateParentDTO parent);

	public StudentEntity createStudentDTOtranslation(CreateStudentDTO student);

	public TeacherEntity createTeacherDTOtranslation(CreateTeacherDTO teacher);

	public ResponseEntity<?> deletedUserDTOtranslation(UserEntity user);

	public String encodePassword(String passwordToEncode);

	public GetChildrenDTO foundChildrenDTOtranslation(StudentParentEntity student);

	public GetParentsDTO foundParentsDTOtranslation(StudentParentEntity parent);

	public GetUserDTO foundUserDTOtranslation(UserEntity user);

	public Boolean isRoleInEnum(String role);

	public ResponseEntity<?> updatedUserDTOtranslation(UserEntity user);

	public TeacherEntity updateTeacherRole(TeacherEntity teacher, String role, Double bonus);

	public UserEntity updateUserDTOtranslation(UpdateUserDTO updatedUser, UserEntity ogUser);

	public String whoAmI();

}
