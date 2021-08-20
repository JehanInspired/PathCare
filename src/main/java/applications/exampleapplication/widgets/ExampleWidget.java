package applications.exampleapplication.widgets;

import Roman.Roman;
import applications.exampleapplication.models.ExampleModel;
import org.junit.Assert;
import org.openqa.selenium.By;
import selenium.AbstractPage;
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

    public void useWidget(ExampleModel model)
    {
        click(FieldToClick);
        sendKeys(OtherField,model.Name);
        stepPassedWithScreenshot("Hooray");
        Assert.assertTrue("Validation Element not found",validateElement_Displayed(ValidationElement));

    }


    public boolean waitForDisplayed() {
        return validateElement_Displayed(FieldToClick);
    }
}
