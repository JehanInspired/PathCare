package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class PathCareDashboardPage extends AbstractPage {

  private final By mainmenu = By.xpath("//md-icon[@title='Main Menu']");
  private final By resultEntrybuttonmenu = By.xpath("//span[text()='Result Entry']");

  private final By findbutton = By.xpath("//input[@name='find1']");
  private final By body = By.xpath("//body[@ng-app]");

  private final By analyticalbuttonmenu = By.xpath("//span[text()='Analytical']");



    public PathCareDashboardPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    public Boolean sideMenusCheckingResultsAnalystical()
    {
        click(mainmenu);
        if(validateElement_Displayed(analyticalbuttonmenu,10)){
            click(analyticalbuttonmenu,10);
            if(!validateElement_Enabled_Displayed(resultEntrybuttonmenu,5)){
                stepPassedWithScreenshot("Not Able to reach Result entry.");
                click(body);
                return true;
            }
        }

        return false;

    }







    public boolean waitForDisplayed() {
        return  validateElement_Enabled_Displayed(mainmenu,15);
    }





}
