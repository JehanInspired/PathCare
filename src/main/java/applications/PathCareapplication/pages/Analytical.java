package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class Analytical extends AbstractPage {

    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By resultEntry = By.xpath("//li//span[text()='Result Entry']");
    private  final By analytical = By.xpath("//li//span[text()='Analytical']");

    public Analytical(Roman roman) {
        super(roman);
    }


    public void navigateResultEntry(){
        click(mainmenu);
        if(validateElement_Enabled_Displayed(resultEntry)){
            click(resultEntry,10);
        }else{
            click(analytical);
            click(resultEntry,10);
        }
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
