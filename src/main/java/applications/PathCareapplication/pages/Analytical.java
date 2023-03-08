package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

public class Analytical extends AbstractExtension {

    private final By mainMenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By processing = By.xpath("//span[text()='Processing']");
    private final By resultEntry = By.xpath("//li//span[text()='Result Entry']");

    private final By workSheetREsEntry = By.xpath("//li//span[text()='Worksheet Res. Entry']");
    private  final By analytical = By.xpath("//li//span[text()='Analytical']");

    private int timeout = 15;

    public Analytical(Roman roman) {
        super(roman);
    }

    public void navigateResultEntry(){
        click(mainMenu);
        if(validateElement_Enabled_Displayed(resultEntry)){
            click(resultEntry,timeout);
        }else{
            click(analytical);
            click(resultEntry,timeout);
        }
    }

    public void navigateProcessing() {

        click(mainMenu,timeout);
        if(validateElement_Enabled_Displayed(processing)){
            click(processing,timeout);
        }else{
            click(analytical);
            click(processing,10);
        }

        stepPassedWithScreenshot("Navigated to specimen received");
    }

    public void navigateWorkSheetRes(){
        awaitElement(mainMenu,timeout);
        click(mainMenu,timeout);
        if(!validateElement_Enabled_Displayed(workSheetREsEntry)){
            click(analytical);
            click(workSheetREsEntry,timeout);
        }else{
            click(workSheetREsEntry,timeout);
        }
        stepPassedWithScreenshot("clicked Worksheet Res. Entry");

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
