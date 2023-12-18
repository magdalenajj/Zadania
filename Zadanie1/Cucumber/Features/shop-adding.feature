Feature: Adding new address

  Scenario Outline: User can add new address
    Given An open browser with main page of the shop
    When User enter an email and password
    And User goes to addresses section
    And User selects "Create new address" option
    And <alias> alias is entered in alias input field,<address> address is entered in address input field,<city> city is entered in city input field,<zipCode> zipCode is entered in zip code input field,<phone> phone is entered in phone input field
    Then New address is added,user can see message "Address successfully added!"

    And close browser
    Examples:

      | alias  | address       | city         |zipCode  | phone      | expectedAlias | expectedAddress | expectedCity | expectedZipCode |  expectedPhone |
      | "Magda"| "Miodowa"    | "Warszawa"   | "11-222"| "258634967"| "Magda"       | "Miodowa"        | "Warszawa"   | "11-222"        | "258634967"   |
