import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetBoardTest extends BaseTest {

    //String passID="ccfda75a7d103362daff879";
    String boardName="VapsiTrainingBoardWithDefaultList";
@Test
    public void getBoard()
{
    RestAssured.baseURI = "https://api.trello.com/";
    RequestSpecification requestSpecification=given().queryParam("key","0697ace29da135af1009cc535346c753")
            .queryParam("token","7ff269a1fc4ace2a4a6176a2e26cc7a4b9f8654f00139375a6599aa8cc28f811")
            .queryParam("name","TrelloAPIAutomationUsingRestAssured").pathParam("boardID","5ccfddb0c386d564afd422e7")
            .log().all()
            .contentType(ContentType.JSON);

    Response response=  requestSpecification.when().get("1/boards/{boardID}");

    Map<String ,?> ResponseMap= response.body().jsonPath().getMap("$");
    System.out.println(response.getBody());
    Assert.assertEquals(ResponseMap.get("name"),boardName,"Board Not created");




}






}
