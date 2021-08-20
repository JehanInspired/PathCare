package applications.bankingapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class LoginPage extends AbstractPage {

    private By customerLoginBtn = By.xpath("//button[text()='Customer Login']");
    private By nameSelect = By.id("userSelect");
    private By loginBtn = By.xpath("//button[text()='Login']");


    public LoginPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return "http://www.way2automation.com/angularjs-protractor/banking/#/login";
    }

    public void login(String customerName)
    {
        navigateTo();
        waitForDisplayed();
        click(customerLoginBtn);
        setSelectedItem(nameSelect,customerName);
        click(loginBtn);
    }

    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(customerLoginBtn,15);
    }

}
