package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class PathCareDashboardPage extends AbstractPage {

  private By mainmenu = By.xpath("//md-icon[@title='Main Menu']");
  private By subselectionPreAnalyical = By.xpath("//span[text()='Pre-Analytical']");


    public PathCareDashboardPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

   /* public void preAnalytical()
    {
        click(mainmenu);
        stepPassedWithScreenshot("Menu options are presented to the user");
        click(subselectionPreAnalyical);

    }*/







    public boolean waitForDisplayed() {
        return  validateElement_Enabled_Displayed(mainmenu,15);
    }


    public void choice(String stage, String Selection){
        switch (stage) {
            case "Pre-Analytical":

                if (Selection.contentEquals("Registration")){

                }
        }
    }

}
