package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class InterSystemloginPage extends AbstractPage {

    private By usernametext = By.xpath("//input[@name='USERNAME']");
    private By passwordtext = By.xpath("//input[@name='PASSWORD']");
    private By loginBtn = By.xpath("//button[@type='submit']");

    //Need to change
    private  By location = By.xpath("//a[text()='National Log Pool']");


    public InterSystemloginPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return "http://172.17.7.83/trakcare/scratch/web/csp/system.Home.cls#/Component/SSUserLogon";
    }

    public void login(String username,String password)
    {
        navigateTo();
        waitForDisplayed();
        sendKeys(usernametext,username);
        sendKeys(passwordtext,password);
        click(loginBtn);
        stepInfoWithScreenshot("User is directed to User Profile screen");
    }

    public void userselection(){
        waitForDisplayed();
        click(location);
        stepInfoWithScreenshot("Lab queues screen");
    }

    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(loginBtn,15);
    }

}
