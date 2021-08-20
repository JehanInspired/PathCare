import Roman.RomanBase;
import applications.exampleapplication.ExampleApplication;
import applications.exampleapplication.models.ExampleModel;
import org.junit.Test;

public class ExampleTest extends RomanBase {

    @Test
    public void firstTest() throws Exception
    {
        //Given
        roman()._driver = roman().selenium.getChromeDriver();
        ExampleApplication exampleApplication = new ExampleApplication(roman());
        ExampleModel model = ExampleModel.getExampleModel();

        exampleApplication.examplePage.someAction(model);
    }
}
