package com.jsonserver.api.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="http://localhost/";
        RestAssured.port=3000;
        RestAssured.basePath="";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
