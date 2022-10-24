package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class InterSystemloginPage extends AbstractPage {

    private final By usernametext = By.xpath("//input[@name='USERNAME']");
    private final By passwordtext = By.xpath("//input[@name='PASSWORD']");
    private final By loginBtn = By.xpath("//button[@type='submit']");
    private final By nextPage = By.xpath("//a[text()='Next >']");
    private final By changeUser = By.xpath("//a[@title='Open Profile Panel']");
    private final By locationchange = By.xpath("//a[@title='Change your logon location']");
    private final By logoffbutton = By.xpath("//button[@aria-label='Logout']");
    private final By footer = By.xpath("//div[@class='footer']");


    private String location = "";

    //Need to change
    private  By accessProfile;


    public InterSystemloginPage(Roman roman) {
        super(roman);
    }

    public void setLocation(String location) {
        this.location = location;
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

    }

    public void changelocation(){
        click(changeUser);
        click(locationchange);
    }

    public void userselection(){
        accessProfile = By.xpath("//span[contains(text(),'%s')]".replace("%s",location));
        scrollToElement(nextPage,10);
        scrollToElement(footer,10);
            while (!validateElement_Enabled_Displayed(accessProfile, 10)) {
                scrollToElement(footer, 10);
                scrollToElement(nextPage, 10);
                click(nextPage, 10);
            }
            scrollToElement(footer, 10);
            scrollToElement(accessProfile, 10);
            click(accessProfile, 10);

        stepPassedWithScreenshot("Lab queues screen "+location);
    }

    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(loginBtn,15);
    }

}
