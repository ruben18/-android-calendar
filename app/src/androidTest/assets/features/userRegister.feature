Feature:
  As a user
  I want to enter an email, name, username, password and verification password
  and  click on the "Sign up" button
  So that I create a account

  Background:
    Given I see an empty registration form

  Scenario Outline: Password and validation password mismatch
    When i introduced as email <email>
    And i introduced as name <name>
    And i intruducced as username <username>
    And i introduced as password <password>
    And i introduced an invalid verification password <password2>
    And i click on the Sign up button
    Then I see an error message saying 'Password and password validation should match'
    Examples:
      | email | name | username | password | password2 |
      | "ruben@gmail.com"| "Ruben Lopes"| "ruben123"| "Aa123456789"|"Aa987654321"|

  Scenario Outline: Invalid email
    When i introduced an invalid email <email>
    And i introduced as name <name>
    And i intruducced as username <username>
    And i introduced as password <password>
    And i introduced as verification password <password>
    And i click on the Sign up button
    Then I see an error message saying 'Invalid email. The email should be of type email@domain.com'
    Examples:
      | email | name | username | password |
      | "ruben.com"| "Ruben Lopes"| "ruben123"| "Aa123456789"|


  Scenario Outline: Empty field
    When i introduced as name <name>
    And i introduced as password <password>
    And i introduced as verification password <password>
    And i click on the Sign up button
    Then I see an error message saying 'Please fill all the fields' 
    Examples:
      | name  | password |
      | "Ruben Lopes"| "Aa123456789"|


  Scenario Outline: Email already in use
    When i introduced an used email <email>
    And i introduced as name <name>
    And i intruducced as username <username>
    And i introduced as password <password>
    And i introduced as verification password <password>
    And i click on the Sign up button
    Then I see an error message saying 'This email is already in use. Please verify your email field'
    Examples:
      | email | name | username | password |
      | "ruben_18_95@hotmail.com"| "Ruben Lopes"| "ruben123"| "Aa123456789"|


  Scenario Outline: Username already in use
    When i introduced an used email <email>
    And i introduced as name <name>
    And i intruducced as username <username>
    And i introduced as password <password>
    And i introduced as verification password <password>
    And i click on the Sign up button
    Then I see an error message saying 'This username is already in use. Please verify your username field'
    Examples:
      | email | name | username | password |
      | "ruben_18_95@hotmail.com"| "Ruben Lopes"| "ruben18"| "Aa123456789"|

  Scenario: Registration canceled
    When i click on the Login textView
    Then i see the LoginActivity

  Scenario Outline: Succesfull registration
    When i introduced as email <email>
    And i introduced as name <name>
    And i intruducced as username <username>
    And i introduced as password <password>
    And i introduced as verification password <password>
    And i click on the Sign up button
    Then I see the DashboardActivity
    Examples:
      | email | name  | username | password |
      | "ruben@gmail.com"| "Ruben Lopes"| "ruben123"| "Aa123456789"|