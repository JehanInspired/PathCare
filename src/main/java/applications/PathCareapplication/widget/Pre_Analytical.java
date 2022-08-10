package applications.PathCareapplication.widget;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class Pre_Analytical extends AbstractPage {

    private final By pre_Analytical   = By.xpath("//span[text()='Pre-Analytical']");
    private final By specimenRecived = By.xpath("//span[text()='Specimen Receive']");
    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By subregistation = By.xpath("//span[text()='Registration']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");


    public Pre_Analytical(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }


    public void navigatespecimenRecived() {

            validateElement_Enabled_Displayed(findbutton,10);
            click(mainmenu,10);
            click(pre_Analytical);
            click(specimenRecived,10);
            stepPassedWithScreenshot("Navigated to specimen received");

            }




    public void navigateRegistration(){
        click(mainmenu);
        click(pre_Analytical);
        click(subregistation);
        stepPassedWithScreenshot("Patient Episode Search Menu appears");

    }


    @Override
    public boolean waitForDisplayed() {
        return validateElement_Displayed(mainmenu,5);
    }
}
