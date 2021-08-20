package applications.bankingapplication;

import Roman.Roman;
import applications.bankingapplication.pages.AccountDashboardPage;
import applications.bankingapplication.pages.LoginPage;

public class BankingApplication
{

    public AccountDashboardPage accountDashboardPage;
    public LoginPage loginPage;

    public BankingApplication(Roman roman)
    {
        accountDashboardPage = new AccountDashboardPage(roman);
        loginPage = new LoginPage(roman);
    }
}
