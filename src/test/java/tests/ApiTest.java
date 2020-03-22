package tests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest extends BaseSteps{
    @Test()
    public void testGet() {
        checkGetResponse();
    }

//    @Test()
//    public void testPOST() {
//        long threadId = Thread.currentThread().getId();
//        System.out.println("Thread Post:" + threadId);
//        Map<String, String> data = new HashMap<>();
//        data.put("orderId", "2");
//        given()
//                .contentType("application/json")
//                .body(data)
//                .when().post("http://httpbin.org/post").then()
//                .statusCode(200)
//                .body("json.orderId", equalTo("2"));
//    }
//
//
//    @Test(dataProvider = "api")
//    public void test(String method) {
//        if (method.equals("post")) {
//            Date date = new Date();
//            System.out.println("Начало " + method + " "+ new Timestamp(date.getTime()));
//            testPOST();
//            date=new Date();
//            System.out.println("Конец " + method + " "+ new Timestamp(date.getTime()));
//            return;
//        }
//        if (method.equals("get")) {
//            Date date = new Date();
//            System.out.println("Начало " + method + " " +new Timestamp(date.getTime()));
//            testGet();
//            date=new Date();
//            System.out.println("Конец " + method + " "+ new Timestamp(date.getTime()));
//            return;
//        }
//    }
//
//    @DataProvider(name = "api", parallel = true)
//    public Object[] provide() {
//        return new Object[]{
//                "post", "get"
//        };
//    }
}
