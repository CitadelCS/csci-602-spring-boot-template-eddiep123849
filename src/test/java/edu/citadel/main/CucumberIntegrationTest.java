package edu.citadel.main;

import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.*;
import io.cucumber.junit.*;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/resources")
public class CucumberIntegrationTest {

}
