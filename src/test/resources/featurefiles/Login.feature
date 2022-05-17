@login-feature
Feature: Login Feature for Modulr Application

  Background: Modulr customers need to be able to login securely to our customer portal in order to takevarious
  actions against their Modulr accounts such as create payments, create and managerules etc.
  To ensure this is done in a secure fashion customer must be able to provide a username and
  password. The password must meet a standard that ensures it is not easily found out and we must
  manage incorrect passwords correctly so the customer is aware but the error must not give away too
  much detail for example telling the user specifically the password is incorrector the username is
  incorrect as this could aid users with malicious intent.

  @valid-id-password
  Scenario Outline: Modulr application login with correct and incorrect user details (One test bound to fail for test purpose)
    Given As a Modulr customer, I try to login into the Modulr Customer Portal with User ID as "<userName>" and Password as "<password>"
    Then I should be able to manage my Modulr accounts
    Examples:
      | userName  | password      |
      | Rahul.K92 | Modulr@Re@003 |
      | Rahul.K92 | Modulr@1234   |

  @invalid-id-password
  Scenario Outline: Modulr application login with incorrect user details for verifying different error messages
    Given As a Modulr customer, I try to login into the Modulr Customer Portal with User ID as "<userName>" and Password as "<password>"
    Then I should not be allowed to login and I should get a message saying "<message>"
    Examples:
      | userName  | password      | message                                                                                                                                                                                         |
      | Rahul.K92 | Modulr@1234   | The username or password is incorrect.\nMultiple incorrect sign-ins could result in your access being locked. If this does happen, you'll receive an email explaining how to reset your access. |
      | Rahul.K   | Modulr@Re@003 | The username or password is incorrect.\nMultiple incorrect sign-ins could result in your access being locked. If this does happen, you'll receive an email explaining how to reset your access. |
      | Rahul.K   | Modulr@Re@1234 | The username or password is incorrect.\nMultiple incorrect sign-ins could result in your access being locked. If this does happen, you'll receive an email explaining how to reset your access. |
      | Rahul.K92 |               | This field is required                                                                                                                                                                          |
      |           | Modulr@123456 | This field is required                                                                                                                                                                          |
      |           |               | This field is required                                                                                                                                                                          |

  @forgot-password
  Scenario Outline: Modulr application reset forgot password
    Given As a Modulr customer, I click on Forgotten Password link
    And enter the user name as "<userName>"
    Examples:
      | userName  |
      | Rahul.K92 |


