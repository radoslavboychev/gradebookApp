package com.iktpreobuka.egradebook.services.assignments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.egradebook.dto.EmailObjectDTO;
import com.iktpreobuka.egradebook.dto.inbound.CreateAssignmentDTO;
import com.iktpreobuka.egradebook.dto.outbound.CreatedAssignmentDTO;
import com.iktpreobuka.egradebook.dto.outbound.GETAssignmentDTO;
import com.iktpreobuka.egradebook.entities.AssignmentEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentEntity;
import com.iktpreobuka.egradebook.entities.userEntities.StudentParentEntity;
import com.iktpreobuka.egradebook.enums.EAssignmentType;
import com.iktpreobuka.egradebook.repositories.AssignmentRepository;
import com.iktpreobuka.egradebook.repositories.StudentGroupRepository;
import com.iktpreobuka.egradebook.repositories.StudentParentRepository;
import com.iktpreobuka.egradebook.repositories.StudentRepository;
import com.iktpreobuka.egradebook.repositories.TeacherRepository;
import com.iktpreobuka.egradebook.repositories.TeacherSubjectRepository;
import com.iktpreobuka.egradebook.services.email.EmailService;
import com.iktpreobuka.egradebook.services.user.UserService;
import com.iktpreobuka.egradebook.services.utils.enums.ESubjectName;
import com.iktpreobuka.egradebook.services.utils.enums.RESTError;

@Service
public class AssignmentServiceImp implements AssignmentService {

	@Autowired
	private TeacherSubjectRepository teacherSubjectRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentParentRepository studentParentRepo;

	@Autowired
	private AssignmentRepository assignmentRepo;

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private StudentGroupRepository studentGroupRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public AssignmentEntity createAssignmentDTOtranslation(CreateAssignmentDTO assignment) {

		// check to find if teacher teaches a subject, this allows only for a logged
		// teacher to post assignments for subject he/she teaches
		logger.info("##ASIGNMENT SERVICE## Accessed service for DTO to Entity translation.");

		Optional<TeacherSubjectEntity> teacherSubject = teacherSubjectRepo
				.findBySubjectAndTeacher(ESubjectName.valueOf(assignment.getSubject()), userService.whoAmI());

		AssignmentEntity newAssignment = new AssignmentEntity();

		logger.info("##ASIGNMENT SERVICE## Translation started.");
		newAssignment.setDateCreated(LocalDate.now());
		newAssignment.setType(EAssignmentType.valueOf(assignment.getType()));
		newAssignment.setDeleted(0);
		if (assignment.getDescription() != null) {
			newAssignment.setDescription(assignment.getDescription());
		}
		if (assignment.getSemester() != null) {
			newAssignment.setSemester(assignment.getSemester());
		}
		if (teacherSubject.isEmpty()) {
			newAssignment.setTeacherIssuing(null);
		} else {
			newAssignment.setTeacherIssuing(teacherSubject.get());
		}

		logger.info("##ASIGNMENT SERVICE## Translation complete, return to controller.");

		return newAssignment;
	}

	@Override
	public ResponseEntity<?> createdAssignmentDTOtranslation(AssignmentEntity assignment) {

		logger.info("##ASIGNMENT SERVICE## Accessed service for Entity to DTO translation.");
		CreatedAssignmentDTO newAssignmentDTO = new CreatedAssignmentDTO();

		logger.info("##ASIGNMENT SERVICE## Translation started.");
		if (assignment.getDateCreated() != null) {
			newAssignmentDTO.setDateCreated(assignment.getDateCreated());
		}
		if (assignment.getDescription() != null) {
			newAssignmentDTO.setDescription(assignment.getDescription());
		}
		if (assignment.getSemester() != null) {
			newAssignmentDTO.setSemester(assignment.getSemester());
		}
		if (assignment.getTeacherIssuing() != null) {
			newAssignmentDTO.setSubject(assignment.getTeacherIssuing().getSubject().getName().toString());
		}
		if (assignment.getTeacherIssuing() != null) {
			newAssignmentDTO.setTeacher(assignment.getTeacherIssuing().getTeacher().getUsername());
		}
		if (assignment.getType() != null) {
			newAssignmentDTO.setType(assignment.getType().toString());
		}
		logger.info("##ASIGNMENT SERVICE## Translation complete, return to controller. " + newAssignmentDTO.toString());

		return new ResponseEntity<>(newAssignmentDTO, HttpStatus.OK);
	}

	@Override
	public GETAssignmentDTO getAssignmentDTOTranslation(AssignmentEntity assignement) {
		logger.info("##ASIGNMENT SERVICE## Accessed service for DTO to Entity translation.");

		GETAssignmentDTO assignmentDTO = new GETAssignmentDTO();

		if (assignement.getAssignedTo() instanceof StudentEntity) {
			assignmentDTO.setAssignedTo(
					assignement.getAssignedTo().getName() + " " + assignement.getAssignedTo().getSurname());
			assignmentDTO.setStudentGroup(assignement.getAssignedTo().getBelongsToStudentGroup().getYear() + "-"
					+ assignement.getAssignedTo().getBelongsToStudentGroup().getYearIndex());
		}
		if (assignement.getDateAssigned() != null) {
			assignmentDTO.setDateAssigned(assignement.getDateAssigned());
		}
		if (assignement.getDateCompleted() != null) {
			assignmentDTO.setDateCompleted(assignement.getDateCompleted());
		}
		if (assignement.getDateCreated() != null) {
			assignmentDTO.setDateCreated(assignement.getDateCreated());
		}
		if (assignement.getDeleted() != null) {
			assignmentDTO.setDeleted(assignement.getDeleted());
		}
		if (assignement.getDescription() != null) {
			assignmentDTO.setDescription(assignement.getDescription());
		}
		if (assignement.getDueDate() != null) {
			assignmentDTO.setDueDate(assignement.getDueDate());
		}
		if (assignement.getGradeRecieved() != null) {
			assignmentDTO.setGradeRecieved(assignement.getGradeRecieved());
		}
		if (assignement.getId() != null) {
			assignmentDTO.setId(assignement.getId());
		}
		if (assignement.getOverridenGrade() != null) {
			assignmentDTO.setOverridenGrade(assignement.getOverridenGrade());
		}
		if (assignement.getSemester() != null) {
			assignmentDTO.setSemester(assignement.getSemester());
		}
		if (assignement.getTeacherIssuing().getSubject().getName() != null
				&& assignement.getTeacherIssuing().getTeacher().getName() != null
				&& assignement.getTeacherIssuing().getTeacher().getSurname() != null) {
			assignmentDTO.setSubject(assignement.getTeacherIssuing().getSubject().getName().toString());
			assignmentDTO.setTeacher(assignement.getTeacherIssuing().getTeacher().getName() + " "
					+ assignement.getTeacherIssuing().getTeacher().getSurname());
		}
		if (assignement.getType() != null) {
			assignmentDTO.setType(assignement.getType().name().toString());
		}
		logger.info("##ASIGNMENT SERVICE## Translation done, DTOs populated, returning to controller.");
		return assignmentDTO;
	}

	@Override
	public List<GETAssignmentDTO> getAssignmentsDTOTranslation(List<AssignmentEntity> assignments) {

		logger.info("##ASIGNMENT SERVICE## Accessed service for DTO to Entity translation.");

		List<GETAssignmentDTO> assignmentsDTO = new ArrayList<>();

		for (AssignmentEntity assignmentEntity : assignments) {
			logger.info("##ASIGNMENT SERVICE## Entering a loop for transleting each assignment to DTO.");

			GETAssignmentDTO assignmentDTO = new GETAssignmentDTO();

			if (assignmentEntity.getAssignedTo() instanceof StudentEntity) {
				assignmentDTO.setAssignedTo(assignmentEntity.getAssignedTo().getName() + " "
						+ assignmentEntity.getAssignedTo().getSurname());
				assignmentDTO.setStudentGroup(assignmentEntity.getAssignedTo().getBelongsToStudentGroup().getYear()
						+ "-" + assignmentEntity.getAssignedTo().getBelongsToStudentGroup().getYearIndex());
			}
			if (assignmentEntity.getDateAssigned() != null) {
				assignmentDTO.setDateAssigned(assignmentEntity.getDateAssigned());
			}
			if (assignmentEntity.getDateCompleted() != null) {
				assignmentDTO.setDateCompleted(assignmentEntity.getDateCompleted());
			}
			if (assignmentEntity.getDateCreated() != null) {
				assignmentDTO.setDateCreated(assignmentEntity.getDateCreated());
			}
			if (assignmentEntity.getDeleted() != null) {
				assignmentDTO.setDeleted(assignmentEntity.getDeleted());
			}
			if (assignmentEntity.getDescription() != null) {
				assignmentDTO.setDescription(assignmentEntity.getDescription());
			}
			if (assignmentEntity.getDueDate() != null) {
				assignmentDTO.setDueDate(assignmentEntity.getDueDate());
			}
			if (assignmentEntity.getGradeRecieved() != null) {
				assignmentDTO.setGradeRecieved(assignmentEntity.getGradeRecieved());
			}
			if (assignmentEntity.getId() != null) {
				assignmentDTO.setId(assignmentEntity.getId());
			}
			if (assignmentEntity.getOverridenGrade() != null) {
				assignmentDTO.setOverridenGrade(assignmentEntity.getOverridenGrade());
			}
			if (assignmentEntity.getSemester() != null) {
				assignmentDTO.setSemester(assignmentEntity.getSemester());
			}
			if (assignmentEntity.getTeacherIssuing().getSubject().getName() != null
					&& assignmentEntity.getTeacherIssuing().getTeacher().getName() != null
					&& assignmentEntity.getTeacherIssuing().getTeacher().getSurname() != null) {
				assignmentDTO.setSubject(assignmentEntity.getTeacherIssuing().getSubject().getName().toString());
				assignmentDTO.setTeacher(assignmentEntity.getTeacherIssuing().getTeacher().getName() + " "
						+ assignmentEntity.getTeacherIssuing().getTeacher().getSurname());
			}
			if (assignmentEntity.getType() != null) {
				assignmentDTO.setType(assignmentEntity.getType().name().toString());
			}
			assignmentsDTO.add(assignmentDTO);
		}

		logger.info("##ASIGNMENT SERVICE## Translation done, DTOs populated, returning to controller.");

		return assignmentsDTO;
	}

	@Override
	public ResponseEntity<?> getAssignmentsPaginated(Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {

		logger.info("##ASIGNMENT SERVICE## Accessed service admin and headmaster assignments pagination.");

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		if (sortOrder.matches("^a.*$")) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		}

		Page<AssignmentEntity> pagedResult = assignmentRepo.findAll(paging);

		Page<GETAssignmentDTO> pagedDTOs = pagedResult.map(this::getAssignmentDTOTranslation);

		logger.info("##ASIGNMENT SERVICE## All done, returning to controller.");

		if (pagedDTOs.hasContent()) {
			return new ResponseEntity<>(pagedDTOs.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RESTError(10000, "No more pages."), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> getAssignmentsPaginatedForHomeroom(Long id, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder) {
		logger.info("##ASIGNMENT SERVICE## Accessed service homeroom assignments pagination.");

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		if (sortOrder.matches("^a.*$")) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		}

		Page<AssignmentEntity> pagedResult = assignmentRepo.findAllByStudentGroup(studentGroupRepo.findById(id).get(),
				paging);
		Page<GETAssignmentDTO> pagedDTOs = pagedResult.map(this::getAssignmentDTOTranslation);

		logger.info("##ASIGNMENT SERVICE## All done, returning to controller.");

		if (pagedDTOs.hasContent()) {
			return new ResponseEntity<>(pagedDTOs.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RESTError(10000, "No more pages."), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> getAssignmentsPaginatedForStudent(Long id, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {

		logger.info("##ASIGNMENT SERVICE## Accessed service student assignments pagination.");

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		if (sortOrder.matches("^a.*$")) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		}

		Page<AssignmentEntity> pagedResult = assignmentRepo.findAllByAssignedTo(studentRepo.findById(id).get(), paging);
		Page<GETAssignmentDTO> pagedDTOs = pagedResult.map(this::getAssignmentDTOTranslation);

		logger.info("##ASIGNMENT SERVICE## All done, returning to controller.");

		if (pagedDTOs.hasContent()) {
			return new ResponseEntity<>(pagedDTOs.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RESTError(10000, "No more pages."), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> getAssignmentsPaginatedForTeacher(Long id, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		logger.info("##ASIGNMENT SERVICE## Accessed service teacher assignments pagination.");

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		if (sortOrder.matches("^a.*$")) {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		}

		Page<AssignmentEntity> pagedResult = assignmentRepo.findAllByTeacherIssuing(teacherRepo.findById(id).get(),
				paging);
		Page<GETAssignmentDTO> pagedDTOs = pagedResult.map(this::getAssignmentDTOTranslation);

		logger.info("##ASIGNMENT SERVICE## All done, returning to controller.");

		if (pagedDTOs.hasContent()) {
			return new ResponseEntity<>(pagedDTOs.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RESTError(10000, "No more pages."), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> sendEmailForGradedAssignemnt(AssignmentEntity assignment) {

		// Send email
		EmailObjectDTO object = new EmailObjectDTO();

		logger.info("##ASIGNMENT SERVICE## Email service accessed, preparing list of parents.");
		List<StudentParentEntity> parents = studentParentRepo.findByStudent(assignment.getAssignedTo());
		List<String> parentEmails = new ArrayList<>();

		for (StudentParentEntity parent : parents) {
			parentEmails.add(parent.getParent().getEmail());
		}

		if (parentEmails.isEmpty()) {
			return new ResponseEntity<>(new RESTError(8002, "No parent associated with this student :(."),
					HttpStatus.BAD_REQUEST);
		}

		if (parentEmails.size() == 1) {
			object.setTo(parentEmails.get(0));
		}

		if (parentEmails.size() > 1) {
			object.setTo(parentEmails.get(0));
			object.setCc(parentEmails.get(1));
		}

		object.setSubject("New grade posted for " + assignment.getAssignedTo().getName() + ".");
		object.setStudentName(assignment.getAssignedTo().getName());
		object.setStudentLastName(assignment.getAssignedTo().getSurname());
		object.setTeacherName(assignment.getTeacherIssuing().getTeacher().getName());
		object.setTeacherLastName(assignment.getTeacherIssuing().getTeacher().getSurname());
		object.setDate(assignment.getDateCompleted());
		object.setGrade(assignment.getGradeRecieved().toString());
		object.setGradedSubject(assignment.getTeacherIssuing().getSubject().getName().toString());
		object.setAssignment(assignment.getType().toString());
		object.setDescription(assignment.getDescription());
		if (assignment.getOverridenGrade() != null) {
			object.setOverridenGrade(assignment.getOverridenGrade().toString());
		}
		logger.info("##ASIGNMENT SERVICE## Trying to send out emails.");
		try {
			emailService.sendTemplateMessage(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
