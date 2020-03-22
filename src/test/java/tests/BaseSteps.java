package tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

/**
 * @author Alexandr.Drasikov
 */

public class BaseSteps {
    protected final static Logger LOG = Logger.getLogger(BaseSteps.class);

    @Step("Проверка запроса гет")
    public void checkGetResponse(){
        LOG.info("Проверка Get запроса");
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread Get:" + threadId);
        Response response1 =
                given()
                        //.log().all()
                        .when()
                        .get("http://httpbin.org/get")
                        .then()
                        //.log().all()
                        .statusCode(200)
//                        .body("headers.Connection", equalTo("close"))
//                        .body("args.a", equalTo("1"))
                        .extract()
                        .response();
        LOG.info(response1.print());
    }
}
