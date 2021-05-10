#Author: Iñaki Aramburu

@CRUD
Feature: Test API CRUD

  Background: 
    Given user load api data

  @Pet
  Scenario: Get available pets
    When user get "available" pets
    Then user check response status code equal to "200"

  @Pet
  Scenario: Create, update and delete new pet
    Given user set new pet
    When user create new pet
    Then user check response status code equal to "200"
    When user update the created pet
    Then user check response status code equal to "200"
    When user delete the created pet
    Then user check response status code equal to "200"
