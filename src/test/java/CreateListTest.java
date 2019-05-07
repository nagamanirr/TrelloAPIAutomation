import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CreateListTest extends BaseTest {

private String Name;
private String listId;

    public CreateListTest(String name) {
        Name = name;

    }

    public CreateListTest() {
    }



    @Test

    public void create()

{
    CreateListTest createListTest=new CreateListTest("Create youtube videos");
    Response response=requestSpecification.when()
            .queryParam("name",createListTest.Name)
            .pathParam("BoardId",boardId).post("1/Boards/{BoardId}/lists");
    Map<String ,?> ResponseMap= response.jsonPath().getMap("$");

   response.then().statusCode(200);
   listId=ResponseMap.get("id").toString();
   createListTest.setListId(listId);
    CreateCardtest createCardtest = new CreateCardtest(createListTest);
    createCardtest.createCard(createListTest);

}

    public String getName() {
        return Name;
    }

    public String getListId() {
        return listId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

}
