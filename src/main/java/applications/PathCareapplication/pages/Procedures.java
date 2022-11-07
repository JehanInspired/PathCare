package applications.PathCareapplication.pages;

import Roman.Roman;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.openqa.selenium.By;
import selenium.AbstractPage;

import java.util.ArrayList;

public class Procedures extends AbstractPage {

    private final By labEpisodeTextBox = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By spcimennumberTexts = By.xpath("//span[contains(@ng-bind,'LBPT_OriginMaterialACN')]");

    private final By clearButton = By.xpath("//button[text()='Clear']");

    public Procedures(Roman roman) {
        super(roman);
    }

    public void searchLabEpisode(String labEpisode, ArrayList<String> specimenNumber) throws InterruptedException {
        sendKeys(labEpisodeTextBox,labEpisode);
        click(findButton);
        Thread.sleep(3000);
       if(validateElement_Displayed(spcimennumberTexts,10)) {
            if(find(spcimennumberTexts,15).size()==specimenNumber.size()) {
                stepPassed("Able to view correct number of specimen");
           }else{
                Assert.fail("Unable to view correct speciemen number");
            }
        }

       click(clearButton);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
        return false;
    }
}
