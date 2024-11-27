Feature: Revenue Calculator Automation

  Scenario: User navigates to FitPeo homepage and uses the revenue calculator
    Given I am on the FitPeo homepage
    When I navigate to the Revenue Calculator page
    And I scroll down to the slider section
    And I adjust the slider to 820
    Then the text field should show 820
    When I enter 560 into the text field
    Then the slider should adjust to 560
    When I select the CPT codes CPT-99091, CPT-99453, CPT-99454, and CPT-99474 for Total Individual Patient per Month 820
    Then the Total Recurring Reimbursement should be "$110700"