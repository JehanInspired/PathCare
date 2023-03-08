package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.By;


public class InterSystemLoginPage extends AbstractExtension {
    private final By usernametext = By.xpath("//input[@name='USERNAME']");
    private final By passwordtext = By.xpath("//input[@name='PASSWORD']");
    private final By loginBtn = By.xpath("//button[@type='submit']");
    private final By nextPage = By.xpath("//a[text()='Next >']");
    private final By changeUser = By.xpath("//a[@title='Open Profile Panel']");
    private final By locationchange = By.xpath("//a[@title='Change your logon location']");
    private final By logoffbutton = By.xpath("//button[@aria-label='Logout']");
    private final By footer = By.xpath("//div[@class='footer']");

    private final int  timeout =20;
    private final int polling = 3;

    private String location = "";

    //Need to change



    public InterSystemLoginPage(Roman roman) {
        super(roman);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
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
        stepPassedWithScreenshot("User is directed to User Profile screen");
    }
    public void logoff(){
        click(changeUser);
        click(logoffbutton);
        _driver.manage().deleteAllCookies();
        _driver.navigate().refresh();

    }

    public void changelocation(){
        click(changeUser,timeout);
        click(locationchange,timeout);
    }

    public void userselection(){
        By accessProfile = By.xpath("//span[contains(text(),'"+location+"')]");
        int counter = 0;
        //Checking first page for element
        awaitElement(footer,timeout);
        scrollToElement(footer,timeout);

            while (!validateElement_Enabled_Displayed(accessProfile, timeout)) {
                scrollToElement(footer, timeout);
                scrollToElement(nextPage, timeout);
                click(nextPage, timeout);
                if(counter>=30){
                    Assert.fail("Unable to view/click "+accessProfile+" ");
                }
                counter++;
            }


            if(validateElement_Enabled_Displayed(accessProfile,timeout)){
                scrollToElement(footer,timeout);
                awaitClickableElement(accessProfile,timeout,polling).click();
            }else{
                awaitElement(accessProfile,timeout);
                scrollToElement(accessProfile,timeout);
                awaitClickableElement(accessProfile, timeout,polling).click();
            }


        stepPassedWithScreenshot("Lab queues screen "+location);
    }

    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(loginBtn,timeout);
    }

}
