import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetDefaultListTest extends BaseTest {


@Test
    public void getDefaultList()
{
    //this code is moved to Base test

   //RestAssured.baseURI = "https://api.trello.com/";
/*
    RequestSpecification requestSpecification=given().queryParam("key","0697ace29da135af1009cc535346c753")
            .queryParam("token","7ff269a1fc4ace2a4a6176a2e26cc7a4b9f8654f00139375a6599aa8cc28f811")
            .queryParam("name","TrelloAPIAutomationUsingRestAssured").pathParam("BoardId",boardId)
            .log().all()
            .contentType(ContentType.JSON); */


    Response response=  requestSpecification.when().pathParam("BoardId",boardId).get("1/boards/{BoardId}/lists");

    List< Map<String ,?> > ResponseList= response.jsonPath().getList("$");


    List<String> actualListsInTheBoard=new ArrayList<>();
    for(int i=0;i<ResponseList.size();i++)
    {
        actualListsInTheBoard.add(ResponseList.get(i).get("name").toString());
    }
    System.out.println("list1"+actualListsInTheBoard);
    //System.out.println(expectedListsInTheBoard);
List<String> expectedListsInTheBoard=new ArrayList<>();

    expectedListsInTheBoard.add("To Do");
    expectedListsInTheBoard.add("Doing");
    expectedListsInTheBoard.add("Done");
    System.out.println("list2"+expectedListsInTheBoard);


    System.out.println("equal or not"+ResponseList.equals(expectedListsInTheBoard));






      Assert.assertTrue(actualListsInTheBoard.equals(expectedListsInTheBoard),"ListNotCreated");


    System.out.println("Response code is *******"+response.getStatusCode());

    response.then().statusCode(200);








//    Assert.assertEquals(ResponseList.get(0).get("name"),"To Do","Todo List Not Created");
//    Assert.assertEquals(ResponseList.get(1).get("name"),"Doing","Doing List Not Created");
//    Assert.assertEquals(ResponseList.get(2).get("name"),"Done","Done List Not Created");
//    System.out.println("Status code is     "+response.statusCode());



}














}
