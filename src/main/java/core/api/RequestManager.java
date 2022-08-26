package core.api;

import core.utils.Constants;
import core.utils.EnvReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
/**
 * Setup request and implement method to return response.
 */
public class RequestManager {
    private String baseUrl;
    private String endPoint;

    public RequestManager() {
        this.baseUrl = EnvReader.getInstance().getValue(Constants.API_BASE_URL_JSON_PATH);
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Response get() {
        Response response = when().get(baseUrl + endPoint);
        response.print();
        return response;
    }
}
