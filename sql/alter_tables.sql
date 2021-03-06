-- CATALOG = "SIM_PRACTICE", SCHEMA = "ORGCHART" --
ALTER TABLE DEPARTMENT DROP FOREIGN KEY `FK_DEPT_PDEPT`
;
ALTER TABLE DEPARTMENT DROP FOREIGN KEY `FK_DEPT_EMPID`
;
ALTER TABLE EMPLOYEE DROP FOREIGN KEY `FK_EMP_MGR`
;
ALTER TABLE EMPLOYEE DROP FOREIGN KEY `FK_EMP_DPT`
;
ALTER TABLE EMPLOYEE DROP FOREIGN KEY `FK_EMP_JOB`
;
DROP TABLE JOB_TITLE
;
DROP TABLE DEPARTMENT
;
DROP TABLE EMPLOYEE
;
CREATE TABLE JOB_TITLE
(
-- supposedly the AUTO_INCREMENT is needed for mySQL, though other DBs may not need it
   JOB_TITLE_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT
 , DESCRIPTION VARCHAR(45) NOT NULL
)
;
CREATE TABLE `DEPARTMENT`
(
-- supposedly the AUTO_INCREMENT is needed for mySQL, though other DBs may not need it
   `DEPARTMENT_ID` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT
 , `MANAGER_ID` INT(11) DEFAULT NULL
 , `NAME` VARCHAR(45) NOT NULL
 , `PARENT_DEPARTMENT_ID` INT(11) DEFAULT NULL
 , UNIQUE(`NAME`)
)
;
CREATE TABLE EMPLOYEE
(
-- supposedly the AUTO_INCREMENT is needed for mySQL, though other DBs may not need it
   EMPLOYEE_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT
 , FIRST_NAME VARCHAR(20) NOT NULL
 , LAST_NAME VARCHAR(45) NOT NULL
 , MIDDLE_INITIAL CHAR(1)
 , EMAIL VARCHAR(100)
 , SKYPE_NAME VARCHAR(100)
 , JOB_TITLE_ID INT
 , IS_MANAGER BIT DEFAULT 0 NOT NULL
 , MANAGER_ID INT
 , DEPARTMENT_ID INT
)
;
ALTER TABLE DEPARTMENT ADD CONSTRAINT `FK_DEPT_PDEPT` FOREIGN KEY
(
   `PARENT_DEPARTMENT_ID`
)
REFERENCES `DEPARTMENT` (`DEPARTMENT_ID`)
;
ALTER TABLE DEPARTMENT ADD CONSTRAINT `FK_DEPT_EMPID` FOREIGN KEY
(
   `MANAGER_ID`
)
REFERENCES `EMPLOYEE` (`EMPLOYEE_ID`)
;
ALTER TABLE EMPLOYEE ADD CONSTRAINT `FK_EMP_MGR` FOREIGN KEY
(
   `MANAGER_ID`
)
REFERENCES `EMPLOYEE` (`EMPLOYEE_ID`)
;
ALTER TABLE EMPLOYEE ADD CONSTRAINT `FK_EMP_DPT` FOREIGN KEY
(
   `DEPARTMENT_ID`
)
REFERENCES `DEPARTMENT` (`DEPARTMENT_ID`)
;
ALTER TABLE EMPLOYEE ADD CONSTRAINT `FK_EMP_JOB` FOREIGN KEY
(
   `JOB_TITLE_ID`
)
REFERENCES `JOB_TITLE` (`JOB_TITLE_ID`)
;
INSERT INTO JOB_TITLE
(JOB_TITLE_ID,DESCRIPTION     ) VALUES
(1           ,'VICE PRESIDENT')
;
INSERT INTO EMPLOYEE
(EMPLOYEE_ID,JOB_TITLE_ID,MANAGER_ID,DEPARTMENT_ID,FIRST_NAME,LAST_NAME,EMAIL                         ,SKYPE_NAME            ,IS_MANAGER) VALUES
(1          ,1           ,NULL      ,NULL         ,'COLIN'   ,'CHAPMAN','CCHAPMAN@SYSTEMSINMOTION.COM','COLIN.CHAPMAN.SIM.AA',1         )
;
INSERT INTO DEPARTMENT
(DEPARTMENT_ID,MANAGER_ID,NAME      ,PARENT_DEPARTMENT_ID) VALUES
(1            ,1         ,'DELIVERY',NULL                )
;
UPDATE EMPLOYEE SET DEPARTMENT_ID=1
WHERE EMPLOYEE_ID=1
;