package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Procedures extends AbstractExtension {
    private final By labEpisodeTextBox = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By worklist = By.xpath("//button[text()='Work List']");

    private final By allCheckboxticked = By.xpath("//md-checkbox[@id='LBProtocolProcedure_List_0-misc-selectAll']");
    private final By bulkCompleteButton =  By.xpath("//button[text()='Bulk Complete']");

    private final By spcimennumberTexts = By.xpath("//span[contains(@ng-bind,'LBPT_OriginMaterialACN')]");
    private final By dateFrom = By.xpath("//input[contains(@ng-model,'DateFrom')]");

    private final By procudure = By.xpath("//input[@name='Procedure']");

    private final By clearButton = By.xpath("//button[text()='Clear']");

    private final By saveSearches = By.xpath("//a[text()='Saved Searches']");
    private int timeout = 15;

    public Procedures(Roman roman) {
        super(roman);
    }

    public void searchLabEpisode(String labEpisode, ArrayList<String> specimenNumber,String procedureSavedSearch) throws InterruptedException {
        savedSearches(procedureSavedSearch);
        sendKeys(labEpisodeTextBox,labEpisode);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now();
        sendKeys(dateFrom,dtf.format(todaydate));
        sendKeys(labEpisodeTextBox,labEpisode,true,false,timeout);
        sendKeys(dateFrom,dtf.format(todaydate));
        javascriptClick(findOne(findButton));
        javascriptClick(findOne(By.xpath("//div[@class='componentBlock flex'][2]")));
       if(validateElement_Displayed(spcimennumberTexts, timeout)) {
            stepInfo("Able to view the specimen");
        }

    }

    public void searchProcedure(String labEpisode, ArrayList<String> specimenNumber,String procedureSavedSearch) throws InterruptedException {
        sendKeys(labEpisodeTextBox,labEpisode);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now();
        sendKeys(dateFrom,dtf.format(todaydate));
        sendKeys(labEpisodeTextBox,labEpisode,true,false,timeout);
        sendKeys(dateFrom,dtf.format(todaydate));
        javascriptClick(findOne(findButton));
        javascriptClick(findOne(By.xpath("//div[@class='componentBlock flex'][2]")));
        if(validateElement_Displayed(spcimennumberTexts, timeout)) {
            stepInfo("Able to view the specimen");
        }

    }

    public void savedSearches(String saveSearchesText){
        _driver.navigate().refresh();
        awaitElement(saveSearches,timeout);
        click(saveSearches,timeout);
        click(By.xpath("//a[contains(text(),'"+saveSearchesText+"')]"),timeout);
        loadingBarChecker();
    }

    public void search(String specimennumber) throws InterruptedException {
        By proccesslink =By.xpath("//parent::span[span[text()='%s']]//parent::span//parent::div//parent::td//parent::tr//a".replace("%s",specimennumber));
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
                x++;
                if(x>=10){
                    break;
                }

            }
        }
        Thread.sleep(10000);
        scrollToElement(proccesslink);
        getObjectFromJavaScript("arguments[0].scrollIntoView();", findOne(proccesslink));
        javascriptClick(findOne(proccesslink,timeout));
        Thread.sleep(5000);
        _driver.navigate().refresh();


    }

    public void multipleSearch(ArrayList<String> lapespside){
        String multliLapEpisode ="";
        for(String lapEpisode:lapespside){
        multliLapEpisode = multliLapEpisode.trim().replace(" ",",");
        findOne(labEpisodeTextBox,lapEpisode);
        click(findButton,timeout);
        stepInfoWithScreenshot("Click Find Button");
        awaitElement(allCheckboxticked,timeout);
        click(allCheckboxticked,timeout);
        stepInfo("Ticked All checkbox");
        stepInfoWithScreenshot("Preparing to click Bulk Complete");
        awaitElement(bulkCompleteButton,timeout);
        click(bulkCompleteButton,timeout);
        }
        _driver.navigate().refresh();

    }

    public void backProtocolProcedureSearch(){
        click(By.xpath("//a[@ng-click='navBack();']"),timeout);
    }

  public void searchSelectionMultiple(String[] procedures){

        awaitElement(clearButton,timeout);
        click(clearButton);
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      for(String procedure :procedures) {
          findOne(procudure, procedure);
          if (validateElement_Displayed(By.xpath("//span[contains(text(),'%s')]".replace("%s", procedure)), timeout)) {
              stepPassed("Entered Procedure " + procedure);
          }
      }
        if(validateElement_Displayed(By.xpath("//input[@name='DateFrom']"),timeout)){
            sendKeys(By.xpath("//input[@name='DateFrom']"),dateTimeFormatter.format(LocalDate.now().minusDays(10)));
        }
        click(findButton);
        stepInfoWithScreenshot("Clicked work list button");
    }


    public void findOne(By by,String input) {
            super.findOne(by,timeout).clear();
            super.findOne(by,timeout).sendKeys(input);
            super.findOne(by,timeout).sendKeys(Keys.TAB);
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
