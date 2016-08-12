# _OrgChart Java_

_This is an organization chart api for the training project._

## Building

_Gradle is the main tool for build & dependency management.
You will be able to run gradle commands via the gradle wrapper in the root of this project, e.g. `./gradlew tasks`_

- _`./gradlew tasks` - Displays the tasks runnable from root project 'org-chart-java' (some of the displayed tasks may belong to subprojects)._
- _`./gradlew clean` - Deletes the build directory._
- _`./gradlew assemble` - Assembles the outputs of this project._
- _`./gradlew build` - Assembles and tests this project._

## Metrics

_The [Jacoco](https://docs.gradle.org/current/userguide/jacoco_plugin.html) and 
[Pitest](https://plugins.gradle.org/plugin/info.solidsoft.pitest) plugins are all installed to help with code metrics. The following commands will produce reports which can be viewed by opening up each generated 
html file that by default are found in project\_dir/build/reports._

- _`./gradlew test` - Runs all of the tests in the project._
- _`./gradlew jacocoTestReport` - Generates the jacoco code coverage report._
- _`./gradlew pitest` - Generates the pitest report._

## Data Migration

_This project uses [Liquibase](http://www.liquibase.org/) for data migration. There is a subsequent gradle plugin for liquibase._
_It is assumed that you have a schema called bulletin with username/password of orgchart/orgchart that has root privileges._

_This schema and user can be created using the SQL statements below:_

```
CREATE SCHEMA orgchart_api;
CREATE USER 'orgchart'@'localhost' IDENTIFIED BY 'orgchart';
GRANT ALL PRIVILEGES ON *.* TO 'orgchart'@'localhost' WITH GRANT OPTION;
```

_The following gradle tasks should be used when migrating data:_

- _`./gradlew dropAll` - Drops all database objects owned by the user. Note that functions, procedures and packages are not dropped (Liquibase limitation)._
- _`./gradlew update` - Updates the database to the current version._

_For recreating the DB schema and data from scratch `dropAll update` command can be used._

## Deploying to Tomcat (First-time setup)

_Apache Tomcat is currently used as our application server._
_Create new directory "Nexient" in the Tomcat root directory_
_Copy orgchart-api.properties and security.properties from {orgchart}\orgchart-config\environment\dev into the newly created Nexient directory_
_Update 'common.loader' property in '[tomcat-home]/conf' to add '${catalina.base}/Nexient'_
_'common.loader=some/other/paths,${catalina.base}/Nexient'_

_For Intellij:_
_Create a new local tomcat server configuration._
_Under Deployment Tab -> add new artifact -> select appropriate project's .war file ('api#orgchart.war')_

_Bash Command Line:_
_`Start Tomcat`_

- _`./gradlew war` - Builds the war file to be deployed to Tomcat._
- _`./gradlew tomcatRun` - Starts an instance of Tomcat and deploys the built war file to it._
- _`./gradlew tomcatRunWar` - Performs the above two steps in one command._
- _`./gradlew tomcatStop` - Shuts down Tomcat._

View it locally on Swagger here:

http://localhost:8080/api/orgchart/swagger-ui.html