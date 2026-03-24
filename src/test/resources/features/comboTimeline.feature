Feature: Combo timeline page

  Background:
   Given I open combo timeline page
   Then the combo timeline page is opened

  Scenario: Verify text on employee tooltips
   When I hide Revenue series
   Then Revenue series should be hidden
   When I collect employee tooltips
   Then employee tooltip texts should match expected values