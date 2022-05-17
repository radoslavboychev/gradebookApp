package com.iktpreobuka.egradebook.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.dto.inbound.CreateTeacherSubjectDTO;
import com.iktpreobuka.egradebook.dto.outbound.GETTeacherSubjectDTO;
import com.iktpreobuka.egradebook.entities.AssignmentEntity;
import com.iktpreobuka.egradebook.entities.StudentGroupEntity;
import com.iktpreobuka.egradebook.entities.StudentGroupTakingASubjectEntity;
import com.iktpreobuka.egradebook.entities.SubjectEntity;
import com.iktpreobuka.egradebook.entities.TeacherSubjectEntity;
import com.iktpreobuka.egradebook.entities.userEntities.TeacherEntity;
import com.iktpreobuka.egradebook.entities.userEntities.UserEntity;
import com.iktpreobuka.egradebook.repositories.AssignmentRepository;
import com.iktpreobuka.egradebook.repositories.StudentGroupRepository;
import com.iktpreobuka.egradebook.repositories.StudentGroupTakingASubjectRepository;
import com.iktpreobuka.egradebook.repositories.SubjectRepository;
import com.iktpreobuka.egradebook.repositories.TeacherSubjectRepository;
import com.iktpreobuka.egradebook.repositories.UserRepository;
import com.iktpreobuka.egradebook.security.Views;
import com.iktpreobuka.egradebook.services.subject.SubjectService;
import com.iktpreobuka.egradebook.services.teacherSubject.TeacherSubjectService;
import com.iktpreobuka.egradebook.services.utils.enums.ERole;
import com.iktpreobuka.egradebook.services.utils.enums.ESubjectName;
import com.iktpreobuka.egradebook.services.utils.enums.RESTError;

@RestController
@RequestMapping(path = "/api/v1/teacherSubject")
public class TeacherSubjectController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private TeacherSubjectRepository teacherSubjectRepo;

	@Autowired
	private StudentGroupRepository studentGroupRepository;

	@Autowired
	private TeacherSubjectService teacherSubjectService;

	@Autowired
	private StudentGroupTakingASubjectRepository studentGroupTakingASubjectRepo;

	@Autowired
	private AssignmentRepository assignmentRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**********************************************************************************************
	 * POST endpoint for administrator looking to assign subject to teacher. --
	 * postman code 007 --
	 *
	 * @param subject
	 * @param username
	 * @return if ok teacher will be assigned to teach a subject
	 **********************************************************************************************/
	@Secured({ "ROLE_ADMIN", "ROLE_HEADMASTER" })
	@JsonView(Views.Headmaster.class)
	@RequestMapping(method = RequestMethod.POST, path = "/assign")
	public ResponseEntity<?> assignSubjectToTeacher(@RequestBody CreateTeacherSubjectDTO teacherSubject) {

		logger.info("**POST TEACHER SUBJECT COMBINATION** Access to the endpoint successful.");

		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if subject name is valid.");
		if (!subjectService.isSubjectInEnum(teacherSubject.getSubject())) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** Subject name not valid.");
			return new ResponseEntity<>(
					new RESTError(2000, "Subject name not allowed, check ESubjectName for details."),
					HttpStatus.BAD_REQUEST);
		}

		Optional<SubjectEntity> ogSubject = subjectRepo.findByNameAndYearOfSchooling(
				ESubjectName.valueOf(teacherSubject.getSubject()), teacherSubject.getYearOfSchooling());
		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if subject is in the database.");
		if (ogSubject.isEmpty()) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** Subject not in database.");
			return new ResponseEntity<>(new RESTError(5030,
					"Subject not found in database, please provide a valid subject. Check if subject and schooling year combination is correct"),
					HttpStatus.NOT_FOUND);
		}

		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if subject is deleted.");
		if (ogSubject.get().getDeleted() == 1) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** Subject is deleted.");
			return new ResponseEntity<>(
					new RESTError(5031, "Not an active subject, please contact the administrator to reinstate."),
					HttpStatus.BAD_REQUEST);
		}

		Optional<UserEntity> ogUser = userRepo.findByUsername(teacherSubject.getUsername());
		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find user in database.");
		if (ogUser.isEmpty()) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** User not in database.");
			return new ResponseEntity<>(
					new RESTError(1030, "Username not found in database, please provide a valid username."),
					HttpStatus.NOT_FOUND);
		}

		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if user is deleted.");
		if (ogUser.get().getDeleted() == 1) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** User deleted.");
			return new ResponseEntity<>(
					new RESTError(1031, "Not an active user, please contact the administrator to reinstate."),
					HttpStatus.BAD_REQUEST);
		}

		// check if user is a teacher
		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if user is a teacher.");
		if (!ogUser.get().getRole().getName().equals(ERole.ROLE_ADMIN)
				&& !ogUser.get().getRole().getName().equals(ERole.ROLE_HEADMASTER)
				&& !ogUser.get().getRole().getName().equals(ERole.ROLE_HOMEROOM)
				&& !ogUser.get().getRole().getName().equals(ERole.ROLE_TEACHER)) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** User not a teacher.");
			return new ResponseEntity<>(
					new RESTError(1060, "Not a teaching user, unable to assign a subject."), HttpStatus.BAD_REQUEST);
		}
		TeacherEntity ogTeacher = (TeacherEntity) ogUser.get();

		logger.info(
				"**POST TEACHER SUBJECT COMBINATION** Attempt to find if teacher can handle required hours to teach the subject.");
		// check if teacher can handle new subject with hours he can take
		if (ogTeacher.getWeeklyHourCapacity() < teacherSubject.getWeeklyHoursAlloted()
				&& ogTeacher.getWeeklyHourCapacity() - teacherSubject.getWeeklyHoursAlloted() < 0) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** Not enough available hours to teach.");
			return new ResponseEntity<>(new RESTError(1039,
					"Teacher not able to handle needed hours, look for other teachers or decrease alloted hours."),
					HttpStatus.BAD_REQUEST);
		}
		logger.info("**POST TEACHER SUBJECT COMBINATION** Invoke service to translate from DTO to Entity.");
		TeacherSubjectEntity newTeacherSubject = teacherSubjectService
				.createTeacherSubjectDTOtranslation(teacherSubject);

		logger.info("**POST TEACHER SUBJECT COMBINATION** Attempt to find if same combination exists in database.");
		if (teacherSubjectRepo.findByTeacherAndSubject(ogTeacher, ogSubject.get()).isPresent()) {
			logger.warn("**POST TEACHER SUBJECT COMBINATION** Same combination exists in database.");
			return new ResponseEntity<>(
					new RESTError(1539, "Combination teacher - subject already existing in database."),
					HttpStatus.BAD_REQUEST);
		}

		// reduce hours capacity for the teacher and update db
		logger.info(
				"**POST TEACHER SUBJECT COMBINATION** Attempt to reduce teacher hours capacity and save to database.");
		ogTeacher.setWeeklyHourCapacity(ogTeacher.getWeeklyHourCapacity() - teacherSubject.getWeeklyHoursAlloted());
		userRepo.save(ogTeacher);

		teacherSubjectRepo.save(newTeacherSubject);
		// do magic and use service to translate to and fro DTO

		logger.info("**POST TEACHER SUBJECT COMBINATION** Invoke service to translate from Entity to DTO.");
		return teacherSubjectService.createdTeacherSubjectDTOtranslation(newTeacherSubject);
	}

	/******************************************************************************************************************
	 * PUT endpoint for administrator or headmaster looking to link a student group
	 * to a subject -- postman code 017 --
	 *
	 * @param student group
	 * @param subject taught by a teacher
	 * @return if ok, student group linked to a subject
	 ******************************************************************************************************************/
	@Secured({ "ROLE_ADMIN", "ROLE_HEADMASTER" })
	@JsonView(Views.Headmaster.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/assignSubjectToStudentGroup")
	public ResponseEntity<?> assignToStudentGroup(@RequestParam Long studentGroupID, @RequestParam Long subjectTaughtID,
			@RequestParam Integer weeklyHours) {

		logger.info("**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Access to the endpoint successful.");

		Optional<StudentGroupEntity> studentGroup = studentGroupRepository.findByIdAndDeleted(studentGroupID, 0);

		logger.info(
				"**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Attempt to find if student group is in database.");
		if (studentGroup.isEmpty()) {
			logger.warn(
					"**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Student group not in database or deleted.");
			return new ResponseEntity<>(
					new RESTError(5040, "Active student group not found in database, please provide a valid id."),
					HttpStatus.NOT_FOUND);
		}

		Optional<TeacherSubjectEntity> teacherSubject = teacherSubjectRepo.findByIdAndDeleted(subjectTaughtID, 0);
		logger.info(
				"**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Attempt to find if teacher-subject combination is in database.");
		if (teacherSubject.isEmpty()) {
			logger.warn(
					"**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Teacher-subject combination not in database or deleted.");
			return new ResponseEntity<>(
					new RESTError(5050, "Active subject taught not found in database, please provide a valid id."),
					HttpStatus.NOT_FOUND);
		}

		// connect the student group and teacher-subject combo

		StudentGroupTakingASubjectEntity newStudentGroupTakingASubject = new StudentGroupTakingASubjectEntity();

		logger.info(
				"**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Attempt to connect student group and teacher-subject combination and assign hours.");
		newStudentGroupTakingASubject.setDeleted(0);
		newStudentGroupTakingASubject.setStudentGroup(studentGroup.get());
		newStudentGroupTakingASubject.setTeacherSubject(teacherSubject.get());
		if (weeklyHours != null) {
			newStudentGroupTakingASubject.setWeeklyHours(weeklyHours);
		}

		logger.info("**PUT TEACHER SUBJECT COMBINATION TO STUDENT GROUP** Save to database.");
		studentGroupTakingASubjectRepo.save(newStudentGroupTakingASubject);

		return new ResponseEntity<>("Subject " + teacherSubject.get().getSubject().getName().toString()
				+ " taught by " + teacherSubject.get().getTeacher().getName() + " asigned to student group "
				+ studentGroup.get().getYear() + "-" + studentGroup.get().getYearIndex() + ".", HttpStatus.OK);
	}

	/********************************************************************************************************************
	 * PUT/DELETE endpoint for administrator looking to soft delete a teacher
	 * subject combination. -- postman code 040 --
	 *
	 * @param teacherSubject id
	 * @return if ok set deleted to 1
	 ********************************************************************************************************************/
	@Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/deleteTeacherSubject/{teacherSubjectID}")
	public ResponseEntity<?> deleteTeacherSubject(@PathVariable Long teacherSubjectID) {

		logger.info("**DELETE TEACHER SUBJECT COMBINATION** Access to the endpoint successful.");

		logger.info(
				"**DELETE TEACHER SUBJECT COMBINATION** Attempt to find an active teacher subject combination in database.");
		// initial check for existance in db
		Optional<TeacherSubjectEntity> ogTeacherSubject = teacherSubjectRepo.findById(teacherSubjectID);
		if (ogTeacherSubject.isEmpty() || ogTeacherSubject.get().getDeleted() == 1) {
			logger.warn(
					"**DELETE TEACHER SUBJECT COMBINATION** Teacher subject combination not in database or deleted.");
			return new ResponseEntity<>(new RESTError(7530,
					"Teacher subject combination not found in database or is deleted, please provide a valid id."),
					HttpStatus.NOT_FOUND);
		}
		logger.info("**DELETE TEACHER SUBJECT COMBINATION** Attempt successful.");

		logger.info(
				"**DELETE TEACHER SUBJECT COMBINATION** Attempt to find if there are active assignments linked to this teacher-subject combination.");
		// do not allow if there are active assignemnts linked to this combinaton
		List<AssignmentEntity> assignments = assignmentRepo.findByTeacherIssuing(ogTeacherSubject.get());
		if (!assignments.isEmpty()) {
			for (AssignmentEntity assignmentEntity : assignments) {
				if (assignmentEntity.getDateCompleted() == null || assignmentEntity.getDeleted() == 1) {
					logger.warn(
							"**DELETE TEACHER SUBJECT COMBINATION** Teacher subject combination linked with an active assignments.");
					return new ResponseEntity<>(new RESTError(7542,
							"Teacher subject combination linked with an active assignment. Make sure assignemnts are completed or deleted before attempting again."),
							HttpStatus.NOT_FOUND);
				}
				logger.info(
						"**DELETE TEACHER SUBJECT COMBINATION** No active assignments linked to this teacher-subject combination.");
			}
		}
		logger.info("**DELETE TEACHER SUBJECT COMBINATION** Attempt successful.");

		logger.info(
				"**DELETE TEACHER SUBJECT COMBINATION** Attempt to find if there are student groups taking classes from teacher-subject combination.");
		// unlink student groups and teacher subject
		List<StudentGroupTakingASubjectEntity> studentGroupTakingASubjectEntities = studentGroupTakingASubjectRepo
				.findAllByTeacherSubject(ogTeacherSubject.get());
		if (!studentGroupTakingASubjectEntities.isEmpty()) {
			for (StudentGroupTakingASubjectEntity studentGroupTakingASubjectEntity : studentGroupTakingASubjectEntities) {
				studentGroupTakingASubjectEntity.setDeleted(1);
			}
			studentGroupTakingASubjectRepo.saveAll(studentGroupTakingASubjectEntities);
			logger.info(
					"**DELETE TEACHER SUBJECT COMBINATION** Attempt successful, all related enteties have been deleted.");
		}

		// set to deleted and save
		logger.info("**DELETE TEACHER SUBJECT COMBINATION** Attempt on editing deleted field and saving to db.");
		ogTeacherSubject.get().setDeleted(1);
		teacherSubjectRepo.save(ogTeacherSubject.get());
		logger.info("**DELETE TEACHER SUBJECT COMBINATION** Attempt successful.");

		return new ResponseEntity<>(
				"Teacher - subject relationship with id " + teacherSubjectID
						+ " deleted, student groups taking the subject and completed assignments unlinked.",
				HttpStatus.OK);

	}

	/**********************************************************************************************************
	 * GET endpoint for administrator looking to fetch all teacher-subject
	 * combinations. -- postman code 063 --
	 *
	 * @param
	 * @return if ok list of all teacehr-subject combinations in database
	 **********************************************************************************************************/
	@Secured({ "ROLE_ADMIN", "ROLE_HEADMASTER" })
	@JsonView(Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<?> getAllTeacherSubjects() {

		logger.info("**GET ALL TEACHER-SUBJECT COMBINATIONS** Access to the endpoint successful.");

		logger.info("**GET ALL TEACHER-SUBJECT COMBINATIONS** Attempt to find teacher-subject combinations.");
		if (teacherSubjectRepo.findAll() == null) {
			logger.warn("**GET ALL TEACHER-SUBJECT COMBINATIONS** No assignments in database.");
			return new ResponseEntity<>(
					new RESTError(6532, "No teacher-subject combinations found in database."), HttpStatus.NOT_FOUND);
		}
		logger.info(
				"**GET ALL TEACHER-SUBJECT COMBINATIONS** Attempt successful, teacher-subject combinations are present.");

		// fetch teacher-subject combinations and present to admin/headmaster
		logger.info(
				"**GET ALL TEACHER-SUBJECT COMBINATIONS** Attempt to invoke service to translate teacher-subject combinations to DTOSs.");
		List<GETTeacherSubjectDTO> ogTeacherSubjectsDTO = teacherSubjectService
				.GETTeacherSubjectsDTOtranslation((List<TeacherSubjectEntity>) teacherSubjectRepo.findAll());
		logger.info("**GET ALL TEACHER-SUBJECT COMBINATIONS** Attempt successful, list retrieved. Exiting controller");

		return new ResponseEntity<>(ogTeacherSubjectsDTO, HttpStatus.OK);
	}

	/*************************************************************************************************************
	 * GET endpoint for administrator looking to fetch a teacher-subject combination
	 * by ID. -- postman code 064 --
	 *
	 * @param teacherSubject id
	 * @return if ok teacher-subject with given id
	 *************************************************************************************************************/
	@Secured({ "ROLE_ADMIN", "ROLE_HEADMASTER" })
	@JsonView(Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/{teacherSubjectID}")
	public ResponseEntity<?> getTeacherSubjectByID(@PathVariable Long teacherSubjectID) {

		logger.info("**GET TEACHER-SUBJECT BY ID** Access to the endpoint successful.");

		logger.info("**GET TEACHER-SUBJECT BY ID** Attempt to find a teacher-subject combination in database.");
		Optional<TeacherSubjectEntity> ogTeacherSubject = teacherSubjectRepo.findById(teacherSubjectID);
		if (ogTeacherSubject.isEmpty()) {
			logger.warn("**GET TEACHER-SUBJECT BY ID** No teacher-subject combination with given id in database.");
			return new ResponseEntity<>(
					new RESTError(6535, "No teacher-subject combination with given id in database."),
					HttpStatus.NOT_FOUND);
		}
		logger.info("**GET TEACHER-SUBJECT BY ID** teacher-subject combination found.");

		logger.info("**GET TEACHER-SUBJECT BY ID** All done, output to DTO.");
		return new ResponseEntity<>(
				teacherSubjectService.GETTeacherSubjectDTOtranslation(ogTeacherSubject.get()), HttpStatus.OK);
	}

	/***************************************************************************************************
	 * PUT endpoint for administrator looking to restore a deleted student group. --
	 * postman code 041 --
	 *
	 * @param teacherSubject id
	 * @return if ok set deleted to 0
	 ***************************************************************************************************/
	@Secured("ROLE_ADMIN")
	@JsonView(Views.Admin.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/admin/restoreTeacherSubject/{teacherSubjectID}")
	public ResponseEntity<?> restoreTeacherSubject(@PathVariable Long teacherSubjectID) {

		logger.info("**RESTORE TEACHER SUBJECT COMBINATION** Access to the endpoint successful.");

		logger.info(
				"**RESTORE TEACHER SUBJECT COMBINATION** Attempt to find a deleted teacher-subject combination in database.");
		// initial check for existance in db
		Optional<TeacherSubjectEntity> ogTeacherSubject = teacherSubjectRepo.findById(teacherSubjectID);
		if (ogTeacherSubject.isEmpty() || ogTeacherSubject.get().getDeleted() == 0) {
			logger.warn(
					"**RESTORE TEACHER SUBJECT COMBINATION** Teacher-subject combination not in database or active.");
			return new ResponseEntity<>(new RESTError(7730,
					"Teacher subject combination not found in database or is active, please provide a valid id."),
					HttpStatus.NOT_FOUND);
		}
		logger.info("**RESTORE TEACHER SUBJECT COMBINATION** Attempt successful.");

		// set to active and save
		logger.info("**RESTORE TEACHER SUBJECT COMBINATION** Attempt on editing deleted field and saving to db.");
		ogTeacherSubject.get().setDeleted(0);
		teacherSubjectRepo.save(ogTeacherSubject.get());
		logger.info("**RESTORE TEACHER SUBJECT COMBINATION** Attempt successful.");

		return new ResponseEntity<>("Teacher - subject relationship with id " + teacherSubjectID + " restored.",
				HttpStatus.OK);
	}

}
