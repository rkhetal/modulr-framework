# modulr-framework
This repository is specifically created for demonstrating the assignment or the requirement given by Blenheim Chalcot

### Selenium Framework with Cucumber

BDD framework for automation using Selenium Cucumber and TestNg

The framework has following features 

1. Maven based framework
2. Log4j enabled for logging
3. Report Generation (Extent Report) 
5. Utility Classes for Reading the Property Files & Element Retry
6. Centralized Configuration (Using Properties file)
7. POM
8. Hook for Browser Setup

### Add the Feature file 

Add the feature file under `test\resources\featurefiles`

```java
@login-feature
Feature: Login Feature for Modulr Application

  @valid-id-password
  Scenario Outline: Modulr application login with correct and incorrect user details (One test bound to fail for test purpose)
    Given As a Modulr customer, I try to login into the Modulr Customer Portal with User ID as "<userName>" and Password as "<password>"
    Then I should be able to manage my Modulr accounts
    Examples:
      | userName  | password      |
      | abc       | xyz           |
      | xyz       | abc           |
```

### Run the Tests

We need to run the test via TestNG by passing the test tag that we need to run as a part of VM arguments.
eg. -DtagName=@login-feature


