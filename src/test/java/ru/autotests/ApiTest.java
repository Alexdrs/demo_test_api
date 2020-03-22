package ru.autotests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Минимальный набор позитивных кейсов\\Minimum set of positive cases")
@Feature("Проверка запросов\\checking requests")
public class ApiTest extends BaseSteps{
    @TmsLink("http://tfs.infotecs.ru:8080/tfs/infotecstc1/PRG_SIES/R610%20SIES%20MC/_workitems?id=493996&_a=edit")
    @Test(description = "Применить сетевые настройки Основной сценарий \\ To apply the network settings the Main scenario")
    public void testGet() {
        checkGetResponse();
    }

    @Test()
    public void testPOST() {
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


    @Test(dataProvider = "api")
    public void test(String method) {
        if (method.equals("post")) {
            Date date = new Date();
              LOG.info("Начало " + method + " "+ new Timestamp(date.getTime()));
            testPOST();
            date=new Date();
              LOG.info("Конец " + method + " "+ new Timestamp(date.getTime()));
            return;
        }
        if (method.equals("get")) {
            Date date = new Date();
              LOG.info("Начало " + method + " " +new Timestamp(date.getTime()));
            testGet();
            date=new Date();
              LOG.info("Конец " + method + " "+ new Timestamp(date.getTime()));
            return;
        }
    }

    @DataProvider(name = "api", parallel = true)
    public Object[] provide() {
        return new Object[]{
                "post", "get"
        };
    }

}
