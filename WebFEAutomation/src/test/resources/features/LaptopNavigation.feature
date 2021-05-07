#Author: Iñaki Aramburu

@Laptop
Feature: Navigation through laptop category

    Background: 
    Given user open the navigator
    Then user access to the desired web page

    @Add
    Scenario Outline: Add products on cart
    Given user access to home page
    When user access to "Laptops" category
    Then user select product "<product>"
    When user add to cart the product
    Then user close pop-up   
    Examples:
      | product      |
      |Sony vaio i5  |
      |Dell i7 8gb   |