package com.practice.repo.tests;

import com.practice.repo.BaseTest;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InterviewPractice2 extends BaseTest {

    public static void main(String[] args) {
        int[] arr = new int[5];
        System.out.println(arr.length);
    }

    @Test
    public void restTest() {
        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://petstore.swagger.io/v2/")
                .pathParam("petId", 3567788)
                .header("api_key", "Password10")
                .get("/pet/{petId}");
        response1.then().log().all();

        System.out.println("========================================================================");
        System.out.println();

        RequestSpecification request2 = RestAssured.given().log().all();
        Response response2 = request2
                .baseUri("https://fakerestapi.azurewebsites.net")
                .body(response1.body().asPrettyString())
                .contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .post("/api/v1/Activities");
        response2.then().log().all();
    }

    @Test
    public void restTest1() {
        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://reqres.in/")
                .queryParam("delay", 5)
                .get("/api/users");
        response1.then().assertThat().time(Matchers.equalTo(2L), TimeUnit.SECONDS);
        response1.then().log().all();
    }

    @Test
    public void restTest2() {
        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://reqres.in/")
                .queryParam("page", 2)
                .get("/api/users");
        System.out.println("===================================================================");
        response1.then().log().all();
    }

    @Test
    public void restTest3() {
        RequestSpecification request1 = RestAssured.given().log().all();

        Map<String, String> patchBody = Map.of(
                "first_name", "test.test.test.in",
                "last_name", "test.test.test.in");

        Response response1 = request1
                .baseUri("https://reqres.in/")
                .pathParam("id", 9)
                .body(patchBody)
                .put("/api/users/{id}");
        System.out.println("===================================================================");
        response1.then().assertThat().body("updatedAt", Matchers.startsWith(LocalDate.now().toString()));
        response1.then().log().all();
    }

    @Test
    public void soapTest() {
        RequestSpecification request1 = RestAssured.given().log().all();

        String var = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <Multiply xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>9</intA>\n" +
                "      <intB>5</intB>\n" +
                "    </Multiply>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        Response response1 = request1
                .baseUri("http://www.dneonline.com")
                .header("Content-Type", "text/xml; charset=utf-8")
                .body(var)
                .post("/calculator.asmx");
        System.out.println("===================================================================");
        Assert.assertEquals(String.valueOf(45), response1.xmlPath().get("//MultiplyResult/text()").toString());
        response1.then().log().all();
    }

    @Test
    public void basicAuth() {
        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://httpbin.org/")
                .auth()
                .basic("user", "pass")
                .get("/basic-auth/user/pass");
        System.out.println("===================================================================");
        response1.then().log().all();
    }

    @Test
    public void restTest6() {

        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://your-auth-server.com")
                .auth()
                .basic("username", "password")
                .post("/oauth/token");
        System.out.println("===================================================================");
        String accessToken = response1.jsonPath().get("access_token").toString();

        RequestSpecification request2 = RestAssured.given().log().all();
        Response response2 = request2
                .baseUri("https://example.com")
                .auth()
                .oauth2(accessToken)
                .get("/protected/resource");
        response2.then().log().all();
    }

    @Test
    public void restTest7() {

        RequestSpecification request1 = RestAssured.given().log().all();
        Response response1 = request1
                .baseUri("https://your-auth-server.com")
                .formParam("client_id", "username")
                .formParam("client_secret", "password")
                .formParam("grant_type", "client_credentials")
                .post("/oauth/token");
        System.out.println("===================================================================");
        String accessToken = response1.jsonPath().get("access_token").toString();

        RequestSpecification request2 = RestAssured.given().log().all();
        Response response2 = request2
                .baseUri("https://example.com")
                .auth()
                .oauth2(accessToken)
                .get("/protected/resource");
        response2.then().log().all();
    }
}