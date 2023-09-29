package com.jsonserver.api.test;

import com.jsonserver.api.test.pojo.Post;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

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
        List<Post> postList= given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);
        Post post = postList.get(0);

        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/"+ post.getId())
                .then()
                .statusCode(200)
//                .body("id",equalTo(post.getId()))
                .body("title",equalTo(post.getTitle()))
                .body("author",equalTo(post.getAuthor()))
                .log().body();
    }
}
