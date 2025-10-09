# That last test didn't turn out how I thought, so here I am writing another

  Feature: can we get the "default" user of id 1?
    Scenario: we want to get user data using a GET request
      When Dr. Ravan hits the endpoint "http://localhost:5001/account/1"
      Then he should get a valid userID back
      # We should get something back in JSON format, so
      # we should expect it to be surrounded by quotes.
      And the userID should be "1"
      Then FINITO