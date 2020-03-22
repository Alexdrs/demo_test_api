package ru.autotests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Date;

@Epic("Minimum set of positive cases")
@Feature("Checking the requests")
public class ApiTest extends BaseSteps {

    @TmsLink("http://........id=493996&_a=edit")
    @Test(description = "To checking the GET request")
    public void testGet() {
        checkGetResponse();
    }

    @TmsLink("Test 493943")
    @Test(description = "Check the POST request")
    public void testPOST() {
        checkPostRequest();
    }


    @TmsLink("Test 493434")
    @Test(dataProvider = "api", description = "Check the POST and GET with parallel run and dataProvider")
    public void test(String method) {
        if (method.equals("post")) {
            Date date = new Date();
            LOG.info("Начало " + method + " " + new Timestamp(date.getTime()));
            testPOST();
            date = new Date();
            LOG.info("Конец " + method + " " + new Timestamp(date.getTime()));
            return;
        }
        if (method.equals("get")) {
            Date date = new Date();
            LOG.info("Начало " + method + " " + new Timestamp(date.getTime()));
            testGet();
            date = new Date();
            LOG.info("Конец " + method + " " + new Timestamp(date.getTime()));
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
