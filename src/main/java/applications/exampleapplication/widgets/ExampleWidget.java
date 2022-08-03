/*package applications.exampleapplication.widgets;

import Roman.Roman;
import applications.PathCareapplication.models.AutomationUserModel;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import selenium.AbstractWidget;

public class ExampleWidget extends AbstractWidget {

    //Xpaths require a . infront of the // to be relational to the scope in widgets - otherwise they will not be scoped
    private By FieldToClick = By.xpath(".//div[@name='Click Me']");
    //Standard selectors are by default relational
    private By OtherField = By.id("ClickMeToo");
    private By ValidationElement = By.id("ValidateMe");

    public ExampleWidget(Roman roman) {
        super(roman);
    }

    @Override
    protected By _Locator() {
        return By.id("Scope");
    }

    public void useWidget(AutomationUserModel model)
    {
        click(FieldToClick);
        sendKeys(OtherField,model.username);
        stepPassedWithScreenshot("Hooray");
        Assertions.assertTrue(validateElement_Displayed(ValidationElement),"Validation Element not found");
    }


    public boolean waitForDisplayed() {
        return validateElement_Displayed(FieldToClick);
    }
}
*/