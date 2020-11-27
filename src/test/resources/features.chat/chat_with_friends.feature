Feature: Chat with friends

  Scenario: Sandra messages Sergey
    Given Sergey is a non-registered user
    And Sandra is a non-registered user
    When she messages 'Sergey' with the text 'hello'
    Then Sergey sees a message from 'Sally' with the text 'hello'
