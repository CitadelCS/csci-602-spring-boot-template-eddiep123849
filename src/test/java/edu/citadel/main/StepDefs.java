package edu.citadel.main;

import io.cucumber.java.en.Then;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;





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
import io.cucumber.java.en.When;

public class StepDefs extends SpringIntegrationTest {
    // I use this in my "endIt" method. It makes it easier to see the separate tests.
    private static int numTest = 0;

    public StepDefs(){
        // I'm not sure if the super constructor does anything.
        super();
        // Change standard output to purple
        System.out.println("\u001b[35m");
        System.out.println("Beginning of test " + ++numTest);
        // Reset the standard output style
        System.out.println("\u001B[0m");
    }

    @When("^the client calls /version$")
    public void itllworkcommaright(){
        System.out.println("We are now inside the \"When\" response method!");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("http://localhost:5001/info");

        try (CloseableHttpResponse response = httpClient.execute(getRequest)){

            System.out.println("Holy shmoly, it freaking works!");
            System.out.println("Here's the code: " + response.getStatusLine());

        }
        catch (Exception e){
            System.out.println("Crap, something went wrong");
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

    @Then("FINITO")
    public void endIt(){
        // Change standard output to purple
        System.out.println("\u001b[35m");
        System.out.println("End of test " + numTest);
        // Reset the standard output style
        System.out.println("\u001B[0m");
    }


}