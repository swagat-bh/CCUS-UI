@CCUS_APP_API_TEST
Feature: Create alert_rule_api of project CCUS 

  Scenario: Verify that Create alert_rule_api is succesful with status code 201
    Given the alert api file "CCUS_Create_Alert_Rule_SuccessRequest:apiJSON"
    When the user calls "post:alert_rule_api:apiCALL" in alert API
    Then alert should be created with response status code 201 and alert Id

  Scenario: Verify error response with error code 400 when alert_rule_api is triggered with no tenant id
    Given the alert api file "CCUS_Create_Alert_Rule_BadRequest:apiJSON"
    When the user calls "post:alert_rule_api:apiCALL" in alert API
    Then alert api response should contain status code 400 with required description for tenant id

   Scenario: Verify error reponse with error code 400 when alert_rule_api is triggered with missing mandatory field 
    Given the alert api file "CCUS_Create_Alert_Rule_BadRequest:apiJSON"
    When the user calls "post:alert_rule_api:apiCALL" in alert API
    Then alert api response should contain status code 400 with required description for required fields

	