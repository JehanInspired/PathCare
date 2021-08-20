package applications.bankingapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import selenium.AbstractPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDashboardPage extends AbstractPage {

    private By DepositBtn = By.xpath("//button[contains(text(),'Deposit')]");

    private By DepositInput = By.xpath("//label[text()='Amount to be Deposited :']/following-sibling::input");
    private By Deposit = By.xpath("//button[text()='Deposit']");
    private By DepositSuccess = By.xpath("//span[text()='Deposit Successful']");
    private By AccountDetails = By.xpath("//div[contains(text(),'Account Number :')]");
    private By LogoutButton = By.xpath("//button[text()='Logout']");
    private By Withdrawbtn = By.xpath("//button[contains(text(),'Withdrawl')]");
    private By WithdrawInput = By.xpath("//label[text()='Amount to be Withdrawn :']/following-sibling::input");
    private By Withdraw = By.xpath("//button[text()='Withdraw']");
    private By TransactionSuccess = By.xpath("//span[text()='Transaction successful']");
    private By AccountSelector = By.id("accountSelect");


    private By TransactionBtn = By.xpath("//button[contains(text(),'Transactions')]");

    public AccountDashboardPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    public void logout()
    {
        click(LogoutButton);
        stepPassedWithScreenshot("Logout complete");
    }

    public void openTransactions()
    {
        click(TransactionBtn);
    }

    public void depositAndValidate(String account, String amount)
    {
        
        setSelectedItem(AccountSelector,account);

        stepPassedWithScreenshot("Account Page Loaded");
        click(DepositBtn);

      

        sendKeys(DepositInput, amount);
        click(Deposit);
        validateElement_Enabled_Displayed(DepositSuccess,15);
        stepPassedWithScreenshot("Deposit success");     

    }

  



    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(AccountDetails,15);
    }

}
