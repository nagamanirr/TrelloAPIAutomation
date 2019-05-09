import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

public class BoardOperations extends BaseTest {


    String listID;
    String defaultListName = "MytrelloDefaultlistName";
    String defaultCardName = "MytrelloDefaultCardName";
    String cardId;


    @Test
    public void canCreateList() {
        Response response = requestSpecification.when()
                .queryParam("name", defaultListName)
                .pathParam("ParamID", boardId).post("1/Boards/{ParamID}/lists");
        Map<String, ?> responseMap = response.jsonPath().getMap("$");
        //response.then().statusCode(200);
        listID = responseMap.get("id").toString();
        System.out.println("list id id " + listID);


    }

    @Test(dependsOnMethods = {"canCreateList"})

    public void canCreateCard() {
        System.out.println("list id is ******" + listID);


        Response responseCardRequest = requestSpecification.when()
                .queryParam("name", defaultCardName)
                .pathParam("ParamID", listID)
                .post("1/lists/{ParamID}/cards");
        Map<String, ?> responseMap = responseCardRequest.jsonPath().getMap("$");
        //responseCardRequest.then().statusCode(200);
        System.out.println("Card id is    " + responseMap.get("id").toString());
        cardId = responseMap.get("id").toString();
        responseCardRequest.then().body(matchesJsonSchemaInClasspath("package.json"));

        /*responseCardRequest
                .then()
                .body("name",equalTo(defaultCardName)); */


    }

    @Test(dependsOnMethods = {"canCreateCard"})

    public void deleteCard() {

        System.out.println("Card id is ******" + cardId);
        Response responseCardDelete = requestSpecification.when()
                .queryParam("name", defaultCardName)
                .pathParam("ParamID", cardId)
                .delete("1/cards/{ParamID}");
        //responseCardDelete.then().statusCode(200);

    }


}
