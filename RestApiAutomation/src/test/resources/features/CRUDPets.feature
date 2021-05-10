#Author: Iñaki Aramburu

@CRUD
Feature: Test API CRUD

  Background: 
  Given user load api data

  Scenario: Get, Create, update and delete pets
    When user get "available" pets
    When user create new pet
    When user update the created pet 
    When user delete the created pet
    
