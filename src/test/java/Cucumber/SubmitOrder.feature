Feature: Purchase the order from Ecommerce Website
  Background:
    Given I landed on Ecommerce page

    @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username "<name>" and password "<pwd>"
    When I add the product <product> to Cart
    And Checkout <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page.
    Examples:
      |name                      |pwd             | product
      |fasihanaaz@gmail.com      |Fasiha123       |ADIDAS ORIGINAL


