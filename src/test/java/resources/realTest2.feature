# Today's date is 10/8/2025, and let's write another real test.Ability:


  Feature: Make sure that the server is running ok
    Scenario: We need to check the health of the server
      When someone tries to check the health of the server by going to "http://localhost:5001/health"
      Then they will receive a response

      And the response will have a status code of 201 for okay
      #The below step should fail because the server should return 200
