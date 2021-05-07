#Author: Iñaki Aramburu

@Laptop
Feature: Navigation through laptop category

    Background: 
    Given user open the navigator
    Then user access to the desired web page

    @Add
    Scenario Outline: Add laptops on cart
    Given user access to "Home" page
    When user access to "Laptops" category
    Then user select product "<laptop>"
    When user add the product to cart
    Then user close pop-up   
    Examples:
      | laptop       |
      |Sony vaio i5  |
      |Dell i7 8gb   |
      
    @Delete
    Scenario Outline: Delete laptops on cart
    Given user access to "Cart" page
    Then user delete on cart the product "<laptop>"
    Examples:
      | laptop       |
      |Dell i7 8gb   |