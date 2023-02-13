package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

public class Analytical extends AbstractExtension {

    private final By mainMenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By processing = By.xpath("//span[text()='Processing']");
    private final By resultEntry = By.xpath("//li//span[text()='Result Entry']");
    private  final By analytical = By.xpath("//li//span[text()='Analytical']");

    public Analytical(Roman roman) {
        super(roman);
    }

    public void navigateResultEntry(){
        click(mainMenu);
        if(validateElement_Enabled_Displayed(resultEntry)){
            click(resultEntry,10);
        }else{
            click(analytical);
            click(resultEntry,10);
        }
    }

    public void navigateProcessing() {

        click(mainMenu,15);
        if(validateElement_Enabled_Displayed(processing)){
            click(processing,10);
        }else{
            click(analytical);
            click(processing,10);
        }

        stepPassedWithScreenshot("Navigated to specimen received");
    }







    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
       return false;
    }
}
