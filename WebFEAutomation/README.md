# WebFEAutomation

WebFEAutomation is maven project to test pet store [api](https://www.demoblaze.com/index.html) developed into Eclipse tool. The tests have been developed for select, add, delete and purchase laptops functionalities.

## Prerequisites

Make sure you have installed all of the following prerequistes on your development machine:

* Git - [Download & Install Git.](https://git-scm.com/downloads) OSX and Linux machines typically have this already installed.
* Java 8 or higher - [Download & Install Java.](https://www.java.com/es/download/)
* Maven - [Download & Install Maven.](https://maven.apache.org/download.cgi)
* Eclipse - [Download & Install Eclipse](https://www.eclipse.org/downloads/packages/release/kepler/sr1/eclipse-ide-java-developers)
* Cucumber-Eclipse plugin - [Download & Install Cucumber-Eclipse plugin](https://www.javatpoint.com/install-cucumber-eclipse-plugin)
* Selenium - [Download & Install Selenium](https://www.selenium.dev/downloads/)

## Downloading WebFEAutomation

The recommended way to get RestApiAutomation is to use git to directly clone the WebFEAutomation repository:

```bash
$ git clone https://github.com/iaramburuf6/qa-challenge.git webFEAutomation
```

This will clone the latest version of the RestApiAutomation repository to a **webFEAutomation** folder.

## Quick Install

Once you've downloaded the WebFEAutomation and installed all the prerequisites, you can launch test via:

* You can run into **webFEAutomation** folder the following command into command line:
```bash
$ mvn clean install
```
* You can [add](https://www.codejava.net/ides/eclipse/import-existing-projects-into-eclipse-workspace) the project to Eclipse tool.
  Once you've imported the project, you can run PetCRUDTest.java class as junit test.
  
## Reports

Once the project tests are launched, the tests create several reports using a [cucumber-reporting-plugin.](https://gitlab.com/monochromata-de/cucumber-reporting-plugin) The reports are created on **/target/cucumber-reports/cucumber-html-reports/** project's folder. The main report is called **overview-features.html**.
  
## Future Improvements
  
In the future, there will be the following improvements:
  
* Make project multi-environment
* Increase the number of checks and logs
* Improve cucumber reports
* ...

## License

[MIT](https://github.com/iaramburuf6/qa-challenge/edit/develop/RestApiAutomation/License)
