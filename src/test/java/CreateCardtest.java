import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateCardtest extends BaseTest {

    CreateListTest createListTest;

    public CreateCardtest(CreateListTest createListTest) {
        this.createListTest = createListTest;
    }

    @Test
    public void createCard(CreateListTest createListTest)
{
     String listId = createListTest.getListId();




}





}
