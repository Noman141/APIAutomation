package com.jsonserver.api.test;

import com.jsonserver.api.test.pojo.Post;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class WriteApiTest extends BaseTest{
    @Test
    public void createPostShouldSucceed(){
        String json="{ \"title\": \"json-server2\", \"author\": \"typicode2\" }";
        given()
                .header("Content-Type","application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void createPostWithHasMapShouldSucceed(){
        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", LoremIpsum.getInstance().getTitle(2));
        jsHashMap.put("author", LoremIpsum.getInstance().getName());
        given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void createPostWithHasMapWithAssertShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();
        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", Title);
        jsHashMap.put("author", Author);
        given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());
    }

    @Test
    public void createPostWithJSONShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", Title);
        jsonObject.put("author",Author);

         Post post = given()
                .header("Content-Type","application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue())
                .extract().jsonPath().getObject("", Post.class);
        System.out.println("===========================================================");
        System.out.println(post.getId());
        System.out.println(post.getTitle());
        System.out.println(post.getAuthor());


    }

    @Test
    public void updatePostShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();
        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", Title);
        jsHashMap.put("author", Author);

        int Id = given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");


        Title = LoremIpsum.getInstance().getTitle(2);
        Author = LoremIpsum.getInstance().getName();
        HashMap<String, Object> jsHashMap1 = new HashMap<>();
        jsHashMap1.put("title", Title);
        jsHashMap1.put("author", Author);

        given()
                .header("Content-Type","application/json")
                .body(jsHashMap1)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+Id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());

    }


    @Test
    public void updatePostPatchShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();
        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", Title);
        jsHashMap.put("author", Author);

        int Id = given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");


        Title = LoremIpsum.getInstance().getTitle(2);
        HashMap<String, Object> jsHashMap1 = new HashMap<>();
        jsHashMap1.put("title", Title);


        given()
                .header("Content-Type","application/json")
                .body(jsHashMap1)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/"+Id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());

    }


    @Test
    public void deletePostShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();
        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", Title);
        jsHashMap.put("author", Author);

        int Id = given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");


        given()
                .header("Content-Type","application/json")
                .log().uri()
                .when()
                .delete("/posts/"+Id)
                .then()
                .statusCode(200)
                .log().body();


    }

    //using  Pojo class

    @Test
    public void createPostUsingPojoWithJSONShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();

        given()
                .header("Content-Type","application/json")
                .body(new Post(Title,Author))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());
    }


    @Test
    public void updatePostUsingPojoShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();


        Long Id = given()
                .header("Content-Type","application/json")
                .body(new Post(Title,Author))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue())
                .extract().jsonPath().getObject("", Post.class)
                .getId();

//Now update the json
        Title = LoremIpsum.getInstance().getTitle(2);
        Author = LoremIpsum.getInstance().getName();
        given()
                .header("Content-Type","application/json")
                .body(new Post(Title,Author))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+Id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());

    }

    @Test
    public void updatePostListUsingPojoShouldSucceed(){
        List<Post> postList= given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
//                .log().body()
                .extract().jsonPath().getList("", Post.class);
//        System.out.println("===========================================");
//
//        for(Post post: postList){
//            System.out.println(post.getId());
//            System.out.println(post.getTitle());
//            System.out.println(post.getAuthor());
//            System.out.println("===========================================");
//        }
        Long id = postList.get(0).getId();

        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();
        given()
                .header("Content-Type","application/json")
                .body(new Post(Title,Author))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(Title))
                .body("author",equalTo(Author))
                .body("id", notNullValue());

    }


    @Test
    public void deletePostFormListUsingPojoShouldSucceed(){
        String Title = LoremIpsum.getInstance().getTitle(2);
        String Author = LoremIpsum.getInstance().getName();

        List<Post> postList= given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);
        Long Id = postList.get(0).getId();


        given()
                .header("Content-Type","application/json")
                .log().uri()
                .when()
                .delete("/posts/"+Id)
                .then()
                .statusCode(200)
                .log().body();
    }


}
