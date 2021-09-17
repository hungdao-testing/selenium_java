## Using Selenium framework to exercise the registration and on-boarding flow of the Loan application.

### A. Business description:

- A credit company requests to have a software to help them decide how to reject or
  approve a money loan request from their customers. This system will help to keep track of
  all the loan information and help to faster proceed all the requests.

### B. Framework information:

#### 1. Description
The testing program consists of essential parts below:
- Builder tool: Maven
- Unit framework tool: TestNG
- Web automation-framework: Selenium(v3)
- Programming language: JAVA (v11)
- REST service is handled by Unirest library
- Using Lombok library to construct the data structure and test data generation.

It designs on following patterns:

- Test scripts are designed on Page Object model, and the element is found on PageFactory pattern.


#### 2. What have not done yet ?

- The Reporter(would use Allure) are under construction .

### C. Scenarios to be covered:

#### 1. Description
There are pages in Singapore that need to go through:
1. Registration
2. Mobile Verification (OTP)
3. Business Role
4. Personal Details
5. Email Verification (OTP)
6. Business Details (Business UEN number: ^([0-9]{8,9}[a-zA-Z]{1})$ )
7. Identity Verification (It is checked, you can ignore it).
8. Onboarding NPS


### D. Installation:

- Before installing the framework, please make sure your computer already have:

  - Java >= 8 and Maven tool.
    
- How to install:

  - At the root folder of the project, type command `mvn clean install -DskipTests`
    
### E. How to run the test
 ** Locale: Singapore(code: sg), Indonesia(code: id)
 ** Environment: test, stag

- Hit command : `mvn clean test - Dlocale={locale_code} -Denv={env_name}`
