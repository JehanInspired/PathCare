package applications.PathCareapplication.widget;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class Pre_Analytical extends AbstractPage {

    private By pre_Analytical   = By.xpath("//span[text()='Pre-Analytical']");
    private By specimenRecived = By.xpath("//span[text()='Specimen Receive']");
    private By subregistation = By.xpath("//span[text()='Registration']");
    private By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");


    public Pre_Analytical(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }


    public void navigatespecimenRecived(){
        click(mainmenu);
        click(pre_Analytical);
        click(specimenRecived);
    }


    public void navigateRegistration(){
        click(mainmenu);
        click(pre_Analytical);
        click(subregistation);
        stepPassedWithScreenshot("Patient Episode Search Menu appears");

    }


    @Override
    public boolean waitForDisplayed() {
        return false;
    }
}
