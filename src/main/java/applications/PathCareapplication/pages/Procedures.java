package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import selenium.AbstractPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Procedures extends AbstractPage {

    private final By labEpisodeTextBox = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By worklist = By.xpath("//button[text()='Work List']");

    private final By spcimennumberTexts = By.xpath("//span[contains(@ng-bind,'LBPT_OriginMaterialACN')]");

    private final By procudure = By.xpath("//input[@name='Procedure']");

    private final By clearButton = By.xpath("//button[text()='Clear']");
    private int timeout = 15;

    public Procedures(Roman roman) {
        super(roman);
    }

    public void searchLabEpisode(String labEpisode, ArrayList<String> specimenNumber) throws InterruptedException {
        sendKeys(labEpisodeTextBox,labEpisode);
        click(findButton);
        Thread.sleep(3000);
       if(validateElement_Displayed(spcimennumberTexts, timeout)) {
            if(find(spcimennumberTexts, timeout).size()==specimenNumber.size()) {
                stepPassed("Able to view correct number of specimen of "+labEpisode);
           }else{
                Assert.fail("Unable to view correct speciemen number of "+labEpisode);
            }
        }

       click(clearButton);
    }

    public void search(String spcimennumber) throws InterruptedException {
        By proccesslink =By.xpath("//parent::span[span[text()='%s']]//parent::span//parent::div//parent::td//parent::tr//a".replace("%s",spcimennumber));
        By nextPage = By.xpath("//a[text()='Next >']");
        By previous = By.xpath("//a[text()='< Previous']");
        int x = 0;
        Thread.sleep(10000);
        if(validateElement_Enabled_Displayed(proccesslink,timeout)){
            stepInfo("Found process link");
        }else{
            while(!validateElement_Enabled_Displayed(proccesslink,timeout)) {
                scrollToElement(proccesslink);
                getObjectFromJavaScript("arguments[0].scrollIntoView();", findOne(proccesslink));
                if(!validateElement_Displayed(nextPage)){
                    click(previous);
                }else{
                    click(nextPage);
                }
                if(x<=10){
                    break;
                }
                x++;
            }
        }
        Thread.sleep(10000);
        scrollToElement(proccesslink);
        getObjectFromJavaScript("arguments[0].scrollIntoView();", findOne(proccesslink));
        Thread.sleep(5000);
        click(proccesslink,timeout);


    }

  public void searchSelection(String procedure){
        if(validateElement_Enabled_Displayed(clearButton,timeout)){
            click(clearButton);
        }
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        sendKeys(procudure,procedure, timeout);
        click(By.xpath("//span[text()='%s']".replace("%s",procedure)), timeout);
        if(validateElement_Displayed(By.xpath("//span[text()='%s']".replace("%s",procedure)), timeout)){
            stepPassed("Entered Procedure "+procedure);
        }
        if(validateElement_Displayed(By.xpath("//input[@name='DateFrom']"),timeout)){
            sendKeys(By.xpath("//input[@name='DateFrom']"),dateTimeFormatter.format(LocalDate.now()));
        }
        click(findButton);
        click(worklist);
        stepInfoWithScreenshot("Clicked work list button");
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
