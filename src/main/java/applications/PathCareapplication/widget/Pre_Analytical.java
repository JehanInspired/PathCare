package applications.PathCareapplication.widget;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

public class Pre_Analytical extends AbstractExtension {

    private final By pre_Analytical   = By.xpath("//span[text()='Pre-Analytical']");
    private final By specimenRecived = By.xpath("//span[text()='Specimen Receive']");

    private final By workAreaReceive = By.xpath("//span[text()='Work Area Receive']");

    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By subregistation = By.xpath("//span[text()='Registration']");



    private final By logisticsMenubutton = By.xpath("//li//span[text()='Transfer Logistics']");

    private final By transferbutton = By.xpath("//span[text()='Transfers']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By homelink = By.xpath("//md-icon[@title='Home']");

    private final int timeout = 30;


    public Pre_Analytical(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    public void navigatespecimenRecived() {

        awaitElement(mainmenu,timeout);
        click(mainmenu,timeout);
        if(validateElement_Enabled_Displayed(specimenRecived)){
            click(specimenRecived,timeout);
        }else{
            click(pre_Analytical);
            click(specimenRecived,timeout);
        }

            stepPassedWithScreenshot("Navigated to specimen received");

    }



    public void switchtoMainiFrame(){
        super.switchToDefaultContext();

    }

    public void navigateWorkRecived(){
        awaitElement(mainmenu,timeout);
        click(mainmenu,timeout);
        if(validateElement_Enabled_Displayed(workAreaReceive)){
            click(workAreaReceive,timeout);
        }else{
            click(pre_Analytical);
            click(workAreaReceive,timeout);
        }

        stepPassedWithScreenshot("Navigated to Work Received");
    }

    public void navigateTransfer(){
        awaitElement(mainmenu,timeout);
        click(mainmenu,timeout);
        if(validateElement_Enabled_Displayed(transferbutton)){
            click(transferbutton,timeout);
        }else{
            click(pre_Analytical);
            click(transferbutton,timeout);
        }
        stepPassedWithScreenshot("Navigated to Transfers");
    }



    public void navigateRegistration(){
        awaitElement(mainmenu,timeout);
        click(mainmenu,timeout);
        if(validateElement_Enabled_Displayed(subregistation)){
            click(subregistation,timeout);
        }else{
            click(pre_Analytical);
            click(subregistation,timeout);
        }
        stepPassedWithScreenshot("Patient Episode Search Menu appears");

    }


    public void navigateLogistics(){
        awaitElement(mainmenu,timeout);
        click(mainmenu);
        if(validateElement_Enabled_Displayed(logisticsMenubutton)){
            click(logisticsMenubutton,timeout);
        }else{
            click(pre_Analytical);
            click(logisticsMenubutton,timeout);
        }
        stepPassedWithScreenshot("Logistics page appears");

    }



    public void navigateMenu(){
        switchtoMainiFrame();
        click(mainmenu,timeout);
    }

    public void navigatehome(){
        switchtoMainiFrame();
        awaitElement(homelink,timeout);
        click(homelink);
    }



    @Override
    public boolean waitForDisplayed() {
        return validateElement_Displayed(mainmenu,5);
    }
}
