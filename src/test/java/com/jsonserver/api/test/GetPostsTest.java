package com.jsonserver.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostsTest extends BaseTest{
    @Test
    public void getPostsShouldSucceed(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test
    public void getPostDetailsShouldSucceed(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/10")
                .then()
                .statusCode(200)
                .body("id",equalTo(10))
                .body("title",equalTo("South Wayne"))
                .log().body();
    }
}
