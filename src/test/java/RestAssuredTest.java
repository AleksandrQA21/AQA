import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {

    public static final String BASE_URL = "https://reqres.in/";
    public static final String LIST_USERS_URI = BASE_URL + "api/users?page=2";
    public static final String CREATE_USER = BASE_URL + "api/users";
    public static final String UPDATE_USER = BASE_URL + "api/users/2";
    public static final String SINGLE_USER_NOT_FOUND = BASE_URL + "api/users/157";

    @Test
    public  void getListUsersStatus200() {
        RestAssured.get(LIST_USERS_URI)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getListUserBody(){

        RestAssured
                .when().get(LIST_USERS_URI)
                .then().assertThat().statusCode(200)
                .and().body("page", equalTo(2))
                .and().body("per_page", equalTo(6))
                .and().body("total", equalTo(12))
                .and().assertThat().body("data[1].id", equalTo(8));
    }


    @Test
    public void getEmailList(){
        RestAssured
                .when().get(LIST_USERS_URI)
                .then().body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in"));
    }

    @Test
    public void getSingleUserNotFound(){
        RestAssured
                .when().get(SINGLE_USER_NOT_FOUND)
                .then().statusCode(404)
                .log().all();

    }

    @Test
    public void postCreateUser(){

        JSONObject request = new JSONObject();
        request.put("name", "Alex");
        request.put("Job", "Student");
        System.out.println(request);

        RestAssured
                .given().body(request.toJSONString())
                .when().post(CREATE_USER)
                .then().statusCode(201)
                .log().all();
    }

    @Test
    public void putUpdateUser(){
        JSONObject request = new JSONObject();
        request.put("name", "Alex");
        request.put("Job", "Intern");

        RestAssured
                .given().body(request.toJSONString())
                .when().put(UPDATE_USER)
                .then().statusCode(200)
                .log().all();
    }

    @Test
    public void deleteUser(){

        RestAssured
                .when().delete(UPDATE_USER)
                .then().statusCode(204)
                .log().all();
    }

}
