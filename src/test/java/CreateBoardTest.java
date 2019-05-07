import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CreateBoardTest extends BaseTest  {
   String boardName="TrelloAPIAutomationUsingRestAssured";
@Test

public String createBoard()
{
       // String boardName="TrelloAPIAutomationUsingRestAssured";
    RequestSpecification requestSpecification=given().queryParam("key","0697ace29da135af1009cc535346c753")
            .queryParam("token","7ff269a1fc4ace2a4a6176a2e26cc7a4b9f8654f00139375a6599aa8cc28f811")
            .queryParam("name","TrelloAPIAutomationUsingRestAssured")
            .log().all()
            .contentType(ContentType.JSON);



  Response response=  requestSpecification.when().post("https://api.trello.com/1/boards");

Assert.assertEquals(response.statusCode(),200);
    System.out.println("Assert from assert statment pass");
    //then woks similar to Assert
    response.then().statusCode(200)
            .contentType(ContentType.JSON)
            .extract()
            .body()
            .jsonPath();
  System.out.println("Print 2 "+ response.prettyPrint());

   Map<String ,?> ResponseMap= response.jsonPath().getMap("$");
    System.out.println(ResponseMap);


    System.out.println("id is    "+ResponseMap.get("id"));
   System.out.println("Name is    "+ResponseMap.get("name"));

  Assert.assertEquals(ResponseMap.get("name"),boardName,"Board Not created");


    return null;
}






}
