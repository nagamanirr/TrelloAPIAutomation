import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {


    RequestSpecification requestSpecification;
    String boardId;

    String boardName="TrelloAPIAutomationUsingRestAssured";
    String tokenNumber="7ff269a1fc4ace2a4a6176a2e26cc7a4b9f8654f00139375a6599aa8cc28f811";
    String keyNumber="0697ace29da135af1009cc535346c753";

@BeforeSuite
    public void setUp() {

    RestAssured.baseURI = "https://api.trello.com/";


  boardId =  createBoard();

}

public String createBoard()
    {

        Response response;

    requestSpecification=given()

            .queryParam("key",keyNumber)
            .queryParam("token",tokenNumber)
            .queryParam("name",boardName)
            .log().all()
            .contentType(ContentType.JSON)
            ;
        response=  requestSpecification.when().post("1/boards");
        //System.out.println("Response="+response.toString());
        /*response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath(); */




    Map<String ,?> ResponseMap= response.jsonPath().getMap("$");
    System.out.println("Response Map="+ResponseMap);

    boardId= ResponseMap.get("id").toString();



    System.out.println("Board id is    "+ResponseMap.get("id"));
    System.out.println("Board Name is    "+ResponseMap.get("name"));


    Assert.assertEquals(ResponseMap.get("name"),boardName,"Board Not created");


        return boardId;
    }

@AfterSuite
    public void tearDown()
{

   deleteBoard();




}

public void deleteBoard()
{
    RestAssured.baseURI = "https://api.trello.com/";
    System.out.println("Board Id is " + boardId );

    requestSpecification=given()
            .queryParam("token",tokenNumber)
            .queryParam("key",keyNumber)
            .log().all()
            .contentType(ContentType.JSON).pathParam("boardID",boardId);


    Response response=  requestSpecification.when().delete("1/boards/{boardID}");
    Assert.assertEquals(response.statusCode(),200,"DId not delete the board");


}







}
