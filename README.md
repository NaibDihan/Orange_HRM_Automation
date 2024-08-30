# Project Title: OrangeHRM Automation Project
## Project Summary: This project automates the management of employee records in the OrangeHRM system using Selenium WebDriver and TestNG. The automation covers logging in as an admin, creating a new employee, verifying the creation, and performing basic operations as the newly created employee.

## Features
- Admin Login: Automates login as an admin user.
- Employee Creation: Creates a new employee with a randomly generated, strong password.
- Employee Verification: Searches and verifies the new employee by ID and name.
- Employee Login: Logs in with the newly created employee's credentials.
- Profile Update: Updates gender and blood type for the employee.
- Logout: Logs out the user after completing all actions.

## Test Cases:
[Test Cases for OrangeHRM Automation.xlsx](https://github.com/user-attachments/files/16821457/Test.Cases.for.OrangeHRM.Automation.xlsx)

## Prerequisites
- JDK LTS Version
- NODEJS LTS Version
- Gradle
- Allure ( If want to generate Allure report)

## Preferred IDE
- Intellij

## How to run?
### Execute the following steps using JMeter:
- ``` git clone <repo_url> ```
- ``` Open the Termnal ```
- Write command ``` gradle clean test ```
### Gradle test report is available in the following location:
- build/reports/tests/test

### To generate Allure report write the following commands:
- ``` allure generate allure-results --clean ```
-  ``` allure serve allure-results ```

##Output
### Gradle Report


