package applications.PathCareapplication.widget;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class Pre_Analytical extends AbstractPage {

    private final By pre_Analytical   = By.xpath("//span[text()='Pre-Analytical']");
    private final By specimenRecived = By.xpath("//span[text()='Specimen Receive']");

    private final By workAreaReceive = By.xpath("//span[text()='Work Area Receive']");

    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By subregistation = By.xpath("//span[text()='Registration']");


    private final By logisticsMenubutton = By.xpath("//li//span[text()='Transfer Logistics']");

    private final By transferbutton = By.xpath("//span[text()='Transfers']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By homelink = By.xpath("//md-icon[@title='Home']");


    public Pre_Analytical(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    public void navigatespecimenRecived() {

            click(mainmenu,15);
        if(validateElement_Enabled_Displayed(specimenRecived)){
            click(specimenRecived,10);
        }else{
            click(pre_Analytical);
            click(specimenRecived,10);
        }

            stepPassedWithScreenshot("Navigated to specimen received");

    }



    public void switchtoMainiFrame(){
        super.switchToDefaultContext();

    }

    public void navigateWorkRecived(){
        validateElement_Enabled_Displayed(findbutton,10);
        click(mainmenu,10);
        if(validateElement_Enabled_Displayed(workAreaReceive)){
            click(workAreaReceive,10);
        }else{
            click(pre_Analytical);
            click(workAreaReceive,10);
        }

        stepPassedWithScreenshot("Navigated to Work Received");
    }

    public void navigateTransfer(){
        validateElement_Enabled_Displayed(findbutton,10);
        click(mainmenu,10);
        if(validateElement_Enabled_Displayed(transferbutton)){
            click(transferbutton,10);
        }else{
            click(pre_Analytical);
            click(transferbutton,10);
        }


        stepPassedWithScreenshot("Navigated to Transfers");
    }



    public void navigateRegistration(){
        click(mainmenu,10);
        if(validateElement_Enabled_Displayed(subregistation)){
            click(subregistation,10);
        }else{
            click(pre_Analytical);
            click(subregistation,10);
        }
        stepPassedWithScreenshot("Patient Episode Search Menu appears");

    }


    public void navigateLogistics(){
        click(mainmenu);
        if(validateElement_Enabled_Displayed(logisticsMenubutton)){
            click(logisticsMenubutton,10);
        }else{
            click(pre_Analytical);
            click(logisticsMenubutton,10);
        }
        stepPassedWithScreenshot("Logistics page appears");

    }

    public void navigateMenu(){
        switchtoMainiFrame();
        click(mainmenu,15);

    }
    public void navigatehome(){
        switchtoMainiFrame();
        click(homelink);
    }



    @Override
    public boolean waitForDisplayed() {
        return validateElement_Displayed(mainmenu,5);
    }
}
