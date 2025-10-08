# Let's finish the third test file. You already know today's date.

# I just realized that the POST request persists through different
# "runnings" of the server. What have I done?
  Feature: Test something a little more complex
    Scenario: We want to see if we can send a POST message to add a new user and then access that new user
      When Eddie tries to send a POST request to "http://localhost:5001/account"
      Then he should receive a response from the server to confirm his message was received
      And that response should contain the user ID of the newly created account
      Then Eddie will send a GET request to "http://localhost:5001/account/" with the right account number
      And he will receive a valid response back