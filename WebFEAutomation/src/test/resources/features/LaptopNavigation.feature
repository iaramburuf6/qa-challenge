#Author: Iñaki Aramburu

@Laptop
Feature: Buy laptop

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
      
    @Purchase
    Scenario: Purchase laptops
    Given user access to "Cart" page
    Then user check cart data
    Then user click on place order
    And user fill "name" field as "John"
    And user fill "country" field as "Irland"
    And user fill "city" field as "Dublin"
    And user fill "card" field as "34 423 2323"
    And user fill "month" field as "January"
    And user fill "year" field as "2022"
    When user click on purchase
    Then user check amount equals to expected
    And user click on ok
    