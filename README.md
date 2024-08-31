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
- Allure ( If you want to generate an Allure report )

## Preferred IDE
- Intellij

## How to run?
### Execute the following steps :
- ``` git clone <repo_url> ```
- ``` Open the Termnal ```
- Write command ``` gradle clean test ```
### Gradle test report is available in the following location:
- build/reports/tests/test

### To generate Allure report write the following commands:
- ``` allure generate allure-results --clean ```
-  ``` allure serve allure-results ```

## Output
### Gradle Report
![Build report](https://github.com/user-attachments/assets/07931eb9-593d-4437-a648-0c6b34ba4c79)

### Allure Report 
#### Overview Section
![Allure Report Overview](https://github.com/user-attachments/assets/bd3edb49-e24b-40e4-89ef-3ddd55123718)

#### Behaviour Section
![Allure Report Behaviour](https://github.com/user-attachments/assets/0965bedd-b390-468d-9265-0b5b0f7ef767)

## Video presentation
- Drive Link: https://drive.google.com/drive/folders/1rfdNTjcQx6xHunko3_lQ8avTq_gNqvYP?usp=sharing




