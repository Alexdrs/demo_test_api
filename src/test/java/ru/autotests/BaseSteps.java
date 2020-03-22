package ru.autotests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Alexandr.Drasikov
 */

public class BaseSteps {
    protected final static Logger LOG = Logger.getLogger(BaseSteps.class);

    @Step("Check GET request")
    public void checkGetResponse(){
        LOG.info("Check GET request");
        long threadId = Thread.currentThread().getId();
        LOG.info("Thread Get:" + threadId);
        Response response1 =
                given()
                        .log().all()
                        .when()
                        .get("http://httpbin.org/get")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("headers.Connection", equalTo("close"))
                        .body("args.a", equalTo("1"))
                        .extract()
                        .response();
        LOG.info(response1.print());
    }

    @Step("Check POST request")
    public void checkPostRequest(){
        long threadId = Thread.currentThread().getId();
        LOG.info("Thread Post:" + threadId);
        Map<String, String> data = new HashMap<>();
        data.put("orderId", "2");
        given()
                .contentType("application/json")
                .body(data)
                .when().post("http://httpbin.org/post").then()
                .statusCode(200)
                .body("json.orderId", equalTo("2"));
    }
}
