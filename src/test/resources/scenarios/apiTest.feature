@api
Feature: TC2 - API Test
  Performa an API Automated Test using this endpoint and validate the response code, response body (Apply what you think is worth it)
  http://dummy.restapiexample.com/api/v1/employees

  Scenario: Get /employees
    Given set endpoint "/employees"
    And request GET method
    Then status code 200
    And "status" match "success"
    And "message" match "Successfully! All records has been fetched."
    And "data" has 24 elements
    And schema match "employeesSchema.json"

  Scenario: Get /employee/id
    Given set endpoint "/employee/1"
    And request GET method
    Then status code 200
    And "status" match "success"
    And "message" match "Successfully! Record has been fetched."
    And "data.employee_name" match "Tiger Nixon"
    And "data.employee_salary" match 320800
    And "data.employee_age" match 61
    And schema match "singleEmployeeSchema.json"
