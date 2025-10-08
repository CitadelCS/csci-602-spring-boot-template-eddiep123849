package edu.citadel.main;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import java.io.IOException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

/*
CloseableHttpClient httpClient = HttpClientConfig.createHttpClient();
HttpGet getRequest = new HttpGet("http://localhost:8080/users");

try (CloseableHttpResponse response = httpClient.execute(getRequest)) {

    int statusCode = response.getCode();
    if (statusCode == HttpStatus.SC_OK) {

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(responseBody, new TypeReference<List<User>>() {});
        users.forEach(System.out::println);
    } else {
        System.err.println("Failed to fetch users. HTTP Status: " + statusCode);
    }
} catch (Exception e) {
    e.printStackTrace();
}
 */

public class StepDefs extends SpringIntegrationTest {
    // I use this in my "FINITO" method. It makes it easier to see the separate tests.
    private static int numTest = 0;

    // This is an instance variable so I can keep track of an HTTP message
    // through different Cucumber method calls.
    private CloseableHttpResponse objectResponse;
    // I only use the below variable in one test. I suppose it's kind of a waste.
    String userID;

    public StepDefs(){
        // I'm not sure if the super constructor does anything.
        super();
        // Change standard output to purple
        System.out.println("\u001b[35m");
        System.out.println("Beginning of test " + ++numTest);
        // Reset the standard output style
        System.out.println("\u001B[0m");
    }

    // The below methods are what I used to test some cucmber functionality.
    // I also added an extra RestController to make this basic testing easier.
    // It only returns a string.

    @When("^the client calls /version$")
    public void itllworkcommaright(){
        System.out.println("We are now inside the \"When\" response method!");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("http://localhost:5001/version");

        try {
            objectResponse = httpClient.execute(getRequest);
            System.out.println("Holy shmoly, it freaking works!");
            System.out.println("Here's the code: " + objectResponse.getStatusLine());

        }
        catch (Exception e){
            System.out.println("Crap, something went wrong");
            System.out.println(e);
        }
    }

    @Then("they will receive the response {string}")
    public void checkResponse(String string){
        try {
            if (objectResponse == null) {
                System.out.println("The HTTP request never went through");
            } else {
                System.out.println("The HTTP response went through");
                System.out.println("Here it is. I'm reading it by the byte:");
                int data = objectResponse.getEntity().getContent().read();
                String response = "";
                while (data != -1){
                    //System.out.print((char) data);
                    response = response + ((char) data);
                    data = objectResponse.getEntity().getContent().read();
                }
                if (response.equalsIgnoreCase(string))
                    System.out.println("It passed");
                else
                    System.out.println("It didn't pass");

            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Then("\"Hello!\" will be printed to the console")
    public void ohBoy(){
        System.out.println("Seems like the cucumber file was read");
    }

    @When("^the client goes to /version... yeah, that's all I got.$")
    public void itFrickinWorksWhen(){
        for (int i = 0; i < 30; i++)
            System.out.println("IT'S WORKING!!!!");
    }

    @Then("1+1 will equal 2")
    public void itFrickinWorksThen(){
        System.out.println();
        System.out.println("1 + 1 does indeed equal 2");
        //return (1 + 1 == 2);
    }

    @Then("the client will be happy")
    public void theClientWillBeHappy() {
        System.out.println(":)");
    }

    //The below method is used to give a "graceful" ending in the console of good tests.

    @Then("FINITO")
    public void FINITO(){
        // Change standard output to purple
        System.out.println("\u001b[35m");
        System.out.println("Successful end of test " + numTest);
        // Reset the standard output style
        System.out.println("\u001B[0m");
    }

    //The below methods are used in tests that test the actual endpoints of our Spring application.
    // I've tried to order them according to how they appear in the "realTest" files,
    // so the first method for the first line in "realTest1" is what starts it and
    // the last line in "realTest3" is the last method.

    @Given("Dr. Ravan is looking at his web browser")
    public void drRavanIsLooking(){
        System.out.println("Dr. Ravan is looking at his web browser");
    }

    @Then("he will try to test the {string} endpoint")
    public void heWillTryToTestInfo(String endpoint) throws IOException{
        System.out.println("He'll try and test Eddie's endpoint");
        HttpGet getRequest = new HttpGet("http://localhost:5001/" + endpoint);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = httpClient.execute(getRequest);

        this.objectResponse = response;
    }

    @Then("he will receive a response with a status code of {int}")
    public void receiveResponse(int responseCode) throws IOException{
        int statusCode = objectResponse.getStatusLine().getStatusCode();

        if (statusCode == responseCode)
            // Everything went well.
            System.out.println("Eddie's server works. He'll get an A+");
        else {
            // This shouldn't happen.
            System.out.println("It seems that Eddie failed this assignment");
            throw new IOException("Expected a status code of \"" + responseCode
                    + "\", but received \"" + statusCode + "\" instead.");
        }
    }

    @When("someone tries to check the health of the server by going to {string}")
    public void tryToCheckTheHealth(String url) throws IOException{
        HttpGet getRequest = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        objectResponse = httpClient.execute(getRequest);
    }

    // Two different steps require the same code, so I used
    // two annotations for this method. I think this method may
    // be unnecessary, but hey, it does something.
    @And("he will receive a valid response back")
    @Then("they will receive a response")
    public void checkForResponse() throws IOException{
        if (objectResponse == null)
            throw new IOException("No response was received");
        else
            ; // We received a response, so everything is fine

    }

    // The below method is only used for "realTest2" and should fail.
    @And("the response will have a status code of {int} for okay")
    public void checkStatusCode(int responseCode) throws IOException{
        int statusCode = objectResponse.getStatusLine().getStatusCode();

        if (statusCode != responseCode){
            throw new IOException("Expected a status code of \"" + responseCode
                    + "\", but received \"" + statusCode + "\" instead."
                    + " However, this was supposed to go wrong.");
        }
        else {
            // Everything went well
            System.out.println("This should not have happened, but it did somehow. Huh.");
        }
    }

    @When("Dr. Ravan hits the endpoint {string}")
    public void sendGet(String endpoint) throws IOException{
        HttpGet getRequest = new HttpGet(endpoint);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        objectResponse = httpClient.execute(getRequest);
    }

    @Then ("he should get a valid userID back")
    public void checkForUserID() throws IOException{
        JSONParser parser = new JSONParser(objectResponse.getEntity().getContent());
        String currentToken = parser.getNextToken().toString();
        boolean foundUserID = false;

        userID = "";
        while ( !(currentToken.equals("")) ){
            System.out.println("Current token: " + currentToken);
            if (currentToken.equals( "\"user_id\"" )){
                // This is probably a valid response, so everything's good.
                foundUserID = true;
                // We need to skip over the the colon to get to the actual userID.
                parser.getNextToken();
                userID = parser.getNextToken().toString();

                break;
            }
            currentToken = parser.getNextToken().toString();
        }

        if (!foundUserID)
            // We didn't get the desired response
            throw new IOException("There was no userID in the repsonse");
        else
            ; // We're good!
    }

    @And("the userID should be {string}")
    public void checkUserID(String expectedUserID) throws IOException{
        JSONParser parser = new JSONParser(objectResponse.getEntity().getContent());

        if ( !(userID.equals(expectedUserID)) ){
            // We didn't get back the expected userID in the server's HTTP response
            throw new IOException("Expected \"" + expectedUserID
                + "\", but got \"" + userID + "\" instead.");
        }
        // We received the userID we expected to receive!

        System.out.println("We expected to receive \"" + expectedUserID
                + "\" and actually received \"" + userID + "\". Test passed.");
    }







































    // You can just ignore the below. I wrote a lot of tests to play
    // around with cucumber and the apache HTTP api, and this code is
    // for the ones I wrote but have no relevance to our actual endpoints.

    /*
    @When("the client does an info call")
    public void theClientDoesAInfoCall() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("http://localhost:5001/info");

        try (CloseableHttpResponse response = httpClient.execute(getRequest)){
            System.out.println(response.getEntity().getContentType());
            JSONParser parser = new JSONParser(response.getEntity().getContent());
            for (int i = 0; i < 20; ++i) {
                String hi = parser.getNextToken().toString();
                System.out.println(hi);
                if (hi.equals(""))
                    System.out.println("Yeah, it's empty");

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Then("they will get a response")
    public void theyWillGetAResponse() {
        System.out.println("Work in progress");
    }

    @When("Eddie tries to send a POST request to {string}")
    public void sendPOST(String endpoint) throws IOException{
        HttpPost postRequest = new HttpPost(endpoint);
        // Set the header of the message
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Content-type", "application/json");
        // Set the JSON body of the message
        postRequest.setEntity(new StringEntity(
                "{" +
                        "  \"username\": \"Dr. Ravan Bruh\",\n" +
                        "  \"password\": \"SpotifyIsCool Bruh\",\n" +
                        "  \"email\": \"ravanj1@citadel.edu Bruh\"\n" +
                        "}"
        ));
        CloseableHttpClient httpClient = HttpClients.createDefault();

        objectResponse = httpClient.execute(postRequest);
    }

    @Then("he should receive a response from the server to confirm his message was received")
    public void receiveResponse() throws IOException{
        if (objectResponse == null)
            throw new IOException("We didn't receive a response from the server");
        else
            ; // Everything is fine here.
    }

    @And("that response should contain the user ID of the newly created account")
    public void checkForUserID() throws IOException {
        JSONParser parser = new JSONParser(objectResponse.getEntity().getContent());
        String parserToken = parser.getNextToken().toString();
        while( !(parserToken.equals("")) ){
            System.out.println(parserToken);
            if (parserToken.equals("\"user_id\"")){
                // Skip the colon
                parser.getNextToken();
                // Store the userId.
                parserToken = parser.getNextToken().toString();
                System.out.println(parserToken);
                userId = Integer.parseInt(parserToken);
                // Is it really that bad to use a return like this? C'mon!
                return;
            }
            else
                // We need to keep looking.
                parserToken = parser.getNextToken().toString();
        }

        // If we make it here, then we never found a "\"user_id\"" element
        // in the response, so something probably went wrong.

        throw new IOException("There's no \"user_id\" element in the response");

    }

    @Then("Eddie will send a GET request to {string} with the right account number")
    public void getAccount(String endpoint) throws IOException {
        // We need to add the userId to the end of the url
        endpoint += userId;
        HttpGet getRequest = new HttpGet(endpoint);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        objectResponse = httpClient.execute(getRequest);
    }
    */

}