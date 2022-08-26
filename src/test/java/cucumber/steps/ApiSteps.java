package cucumber.steps;

import core.api.RequestManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class ApiSteps {
    private RequestManager requestManager;
    private Response response;

    @Given("set endpoint {string}")
    public void setEndpoint(String endPoint) {
        this.requestManager = new RequestManager();
        this.requestManager.setEndPoint(endPoint);
    }

    @And("request GET method")
    public void requestGetMethod() {
        this.response = this.requestManager.get();
    }

    @Then("status code {int}")
    public void statusCode(int expectedStatusCode) {
        int actualStatusCode = this.response.statusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @And("{string} has {int} elements")
    public void hasElements(String jsonPath, int expectedNumberOfElements) {
        List<HashMap<String, String>> elements = this.response.jsonPath().get(jsonPath);
        Assert.assertEquals(expectedNumberOfElements, elements.size());
    }

    @And("schema match {string}")
    public void schemaMatch(String schemaFilePath) {
        this.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\schemas\\" + schemaFilePath)));
    }

    @And("{string} match {string}")
    public void match(String responseProperty, String expectedValue) {
        this.response.then().assertThat().body(responseProperty, equalTo(expectedValue));
    }

    @And("{string} match {int}")
    public void match(String responseProperty, int expectedValue) {
        this.response.then().assertThat().body(responseProperty, equalTo(expectedValue));
    }
}
