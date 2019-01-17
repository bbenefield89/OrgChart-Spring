package com.nexient.orgchart.data.entity;

import java.util.Random;

/**
 * Created by kskronek on 5/25/2016.
 */
public class Entities {

	public static final String DEPARTMENT_NAME = "DepartmentEntity";
	public static final Integer DEPT_ID = 22;
	public static final String EMAIL = "email";
	public static final Integer EMPLOYEE_ID = 5;
	public static final String FIRST_NAME = "first name";
	public static final String JOB_TITLE = "Job Title";
	public static final Integer JOB_TITLE_ID = 5;
	public static final String JOB_TITLE_NAME = "JobTitleEntity";
	public static final String LAST_NAME = "last name";
	public static final Integer MANAGER_ID = 1;
	public static final String SKYPE_NAME = "skype name";
	public static final Character MIDDLE_INITIAL='M';

	private static Random random = new Random();

	public static DepartmentEntity department() {
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setIsActive(true);
		departmentEntity.setName(departmentName());
		return departmentEntity;
	}

	public static DepartmentEntity department(Integer id) {
		DepartmentEntity department = department();
		department.setIsActive(true);
		department.setId(id);
		return department;
	}

	public static DepartmentEntity department(DepartmentEntity parent) {
		DepartmentEntity departmentEntity = department();
		departmentEntity.setIsActive(true);
		departmentEntity.setParentDepartment(parent);
		return departmentEntity;
	}

	private static String departmentName() {
		return DEPARTMENT_NAME + random.nextInt();
	}

//	public static EmployeeEntity employee(DepartmentEntity dept) {
//		EmployeeEntity emp = new EmployeeEntity();
//		emp.setIsActive(true);
//		emp.setFirstName(FIRST_NAME);
//		emp.setMiddleInitial(MIDDLE_INITIAL);
//		emp.setLastName(LAST_NAME);
//		emp.setEmail(EMAIL + random.nextInt());
//		emp.setSkypeName(SKYPE_NAME + random.nextInt());
//		emp.setJobTitle(jobTitle());
//		emp.setIsManager(false);
//		return emp;
//	}

	public static EmployeeEntity employee() {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setIsActive(true);
		emp.setFirstName(FIRST_NAME);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		emp.setLastName(LAST_NAME);
		emp.setEmail(EMAIL + random.nextInt());
		emp.setSkypeName(SKYPE_NAME + random.nextInt());
		emp.setIsManager(false);
		return emp;
	}

//	public static EmployeeEntity employee(Integer employeeId) {
//		EmployeeEntity emp = new EmployeeEntity();
//		emp.setIsActive(true);
//		emp.setId(EMPLOYEE_ID);
//		return emp;
//	}

	public static EmployeeEntity manager() {
		EmployeeEntity mgr = new EmployeeEntity();
		mgr.setFirstName(FIRST_NAME);
		mgr.setIsActive(true);
		mgr.setMiddleInitial(MIDDLE_INITIAL);
		mgr.setLastName(LAST_NAME);
		mgr.setEmail(EMAIL + random.nextInt());
		mgr.setSkypeName(SKYPE_NAME + random.nextInt());
		mgr.setIsManager(true);
		return mgr;
	}

//	public static JobTitleEntity jobTitle(Integer jobTitleId) {
//		JobTitleEntity title = new JobTitleEntity();
//		title.setId(JOB_TITLE_ID);
//		title.setIsActive(true);
//		return title;
//	}

	public static JobTitleEntity jobTitle() {
		JobTitleEntity jobTitle = new JobTitleEntity();
		jobTitle.setIsActive(true);
		jobTitle.setName(JOB_TITLE_NAME + random.nextInt());
		return jobTitle;
	}
}
