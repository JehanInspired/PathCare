package applications.exampleapplication.pages;

import Roman.Roman;
import applications.exampleapplication.models.ExampleModel;
import applications.exampleapplication.widgets.ExampleWidget;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class ExamplePage extends AbstractPage {

    private ExampleWidget exampleWidget;
    private By Navigator = By.id("Navigator");

    public ExamplePage(Roman roman) {
        super(roman);
        exampleWidget = new ExampleWidget(roman);
    }

    public String get_uri() {
        return "SomeURL";
    }

    public void someAction(ExampleModel model)
    {
        navigateTo();
        waitForDisplayed();
        click(Navigator);
        exampleWidget.useWidget(model);
    }

    public boolean waitForDisplayed() {
        return validateElement_Displayed(Navigator);
    }
}
