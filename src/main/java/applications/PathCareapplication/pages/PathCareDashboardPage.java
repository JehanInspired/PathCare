package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class PathCareDashboardPage extends AbstractPage {

  private By mainmenu = By.xpath("//md-icon[@title='Main Menu']");
  private By subselectionPreAnalyical = By.xpath("//span[text()='Pre-Analytical']");
  private By subregistation = By.xpath("//span[text()='Registration']");

    public PathCareDashboardPage(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    public void preAnalytical()
    {
        click(mainmenu);
        stepPassedWithScreenshot("Menu options are presented to the user");
        click(subselectionPreAnalyical);
        click(subregistation);
        stepPassedWithScreenshot("Patient Episode Search Menu appears");
    }





    public boolean waitForDisplayed() {
        return  false;
    }


    public void choice(String stage, String Selection){
        switch (stage) {
            case "Pre-Analytical":

                if (Selection.contentEquals("Registration")){

                }
        }
    }

}
