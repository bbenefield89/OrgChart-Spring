package com.nexient.orgchart.model;

import java.util.Random;

/**
 * Created by mrangel on 7/21/2016.
 */
public class Models {
    public static final String DEPARTMENT_NAME = "Department";
    public static final Integer DEPT_ID = 22;
    public static final String EMAIL = "email";
    public static final Integer EMPLOYEE_ID = 5;
    public static final String FIRST_NAME = "first name";
    public static final String JOB_TITLE = "Job Title";
    public static final Integer JOB_TITLE_ID = 5;
    public static final String JOB_TITLE_NAME = "JobTitle";
    public static final String LAST_NAME = "last name";
    public static final Integer MANAGER_ID = 1;
    public static final String SKYPE_NAME = "skype name";
    public static final Character MIDDLE_INITIAL='M';

    private static Random random = new Random();

//    public static Department department() {
//        Department department = new Department();
//        department.setName(departmentName());
//        department.setId(DEPT_ID);
//
//        return department;
//    }
//
//    public static Department department(Integer id) {
//        Department department = department();
//        department.setIsActive(true);
//        department.setId(id);
//        return department;
//    }
//
//    public static Department department(Department parent) {
//        Department department = department();
//        department.setIsActive(true);
//        department.setParentDepartment(parent);
//        return department;
//    }
//
//    private static String departmentName() {
//        return DEPARTMENT_NAME + random.nextInt();
//    }
//
//    public static Employee employee(Department dept) {
//        Employee emp = new Employee();
//        emp.setFirstName(FIRST_NAME);
//        emp.setMiddleInitial(MIDDLE_INITIAL);
//        emp.setLastName(LAST_NAME);
//        emp.setEmail(EMAIL + random.nextInt());
//        emp.setSkypeName(SKYPE_NAME + random.nextInt());
//        emp.setJobTitle(jobTitle());
//        emp.setIsManager(false);
//        return emp;
//    }
//
//    public static Employee employee() {
//        Employee emp = new Employee();
//        emp.setDepartment(department());
//        emp.setFirstName(FIRST_NAME);
//        emp.setMiddleInitial(MIDDLE_INITIAL);
//        emp.setLastName(LAST_NAME);
//        emp.setEmail(EMAIL + random.nextInt());
//        emp.setJobTitle(jobTitle());
//        emp.setSkypeName(SKYPE_NAME + random.nextInt());
//        emp.setIsManager(false);
//        return emp;
//    }
//
//    public static JobTitle jobTitle() {
//        JobTitle jobTitle = new JobTitle();
//        jobTitle.setId(JOB_TITLE_ID);
//        jobTitle.setName(JOB_TITLE_NAME + random.nextInt());
//        return jobTitle;
//    }
//
//    public static Employee manager() {
//        Employee mgr = new Employee();
//        mgr.setFirstName(FIRST_NAME);
//        mgr.setMiddleInitial(MIDDLE_INITIAL);
//        mgr.setLastName(LAST_NAME);
//        mgr.setEmail(EMAIL + random.nextInt());
//        mgr.setSkypeName(SKYPE_NAME + random.nextInt());
//        mgr.setIsManager(true);
//        return mgr;
//    }
//
//
//    public static Employee employee(Integer employeeId) {
//        Employee emp = new Employee();
//        emp.setId(EMPLOYEE_ID);
//        return emp;
//    }

}
