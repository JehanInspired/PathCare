import Roman.RomanBase;
import applications.bankingapplication.BankingApplication;
import applications.exampleapplication.ExampleApplication;
import applications.exampleapplication.models.ExampleModel;
import org.junit.jupiter.api.Test;

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

    @Test
    public void depositTest()
    {
        roman()._driver = roman().selenium.getChromeDriver();
        BankingApplication bankingApp = new BankingApplication(roman());

        bankingApp.loginPage.login("Harry Potter");
        bankingApp.accountDashboardPage.depositAndValidate("1004", "12345");
    }
}
