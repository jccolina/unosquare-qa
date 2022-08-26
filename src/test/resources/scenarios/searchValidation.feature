@ui
Feature: Search validation

  Scenario: Search item and add to cart flow
    Given search by keyword on Windows tab
    And select Comprar tab in search results
    When select Juegos on category menu
    Then print items in the first 3 pages
    And verify non-free item's price in category page matches price in item page
    When add non-free item to cart
    Then single item should be on cart
    When delete item from cart
    Then cart displays empty message "Tu carro está vacío."

  Scenario: Dummy test to fail
    Given search by keyword on Windows tab
    When select Juegos on category menu
