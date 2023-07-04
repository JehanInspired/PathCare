package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Procedures extends AbstractExtension {
    private final By labEpisodeTextBox = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By workList = By.xpath("//button[text()='Work List']");

    private final By allCheckboxticked = By.xpath("//*[@id='LBProtocolProcedure_List_0-misc-selectAll']");
    private final By bulkCompleteButton =  By.xpath("//button[text()='Bulk Complete']");

    private final By spcimennumberTexts = By.xpath("//span[contains(@ng-bind,'LBPT_OriginMaterialACN')]");
    private final By dateFrom = By.xpath("//input[contains(@ng-model,'DateFrom')]");

    private final By procudure = By.xpath("//input[@name='Procedure']");

    private final By clearButton = By.xpath("//button[text()='Clear']");

    private final By saveSearches = By.xpath("//a[text()='Saved Searches']");
    private final By testSetText = By.xpath("//input[@name='TestSet']");
    private final By departmentText = By.xpath("//input[@name='Department']");
    private final By savedSearchDescription = By.xpath("//input[@id='CTSearch_Msg_Edit_0-item-SRCHDesc']");
    private final By updateSavedSearch = By.xpath("//button[@id='CTSearch_Msg_Edit_0-button-update1']");
    private final By procedureRecord = By.xpath("//span[@id='LBProtocolProcedure_List_0-row-0-item-LBCPR_Desc']");
    private final By saveSearch = By.xpath("//button[@id='LBProtocolProcedure_List_0-button-SaveSearch']");
    private final By tLBProtocolProcedure_List = By.xpath("(//md-checkbox[@title='Select All'])[1]");
    private final By nextpageProcedureList =By.xpath("//img[@id='NextPageImage_LBTransfer_List']");
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
    public void searchProtocolProcedure(String department, String testSet) throws InterruptedException {
        sendKeysAndTab(testSetText,testSet);
        sendKeysAndTab(departmentText,department);
        click(workList,timeout);
    }
    public void saveSearchAndUpdate(String description) throws InterruptedException {
        Thread.sleep(2000);
        javascriptClick(findOne(saveSearch,timeout));
        Thread.sleep(2000);
        sendKeysAndTab(savedSearchDescription,description,timeout);
        Thread.sleep(1000);
        awaitClickableElement(updateSavedSearch,timeout,10);
        click(updateSavedSearch,timeout);
    }
    public void selectAllAndBulkComplete() throws InterruptedException{
           //click(tLBProtocolProcedure_List,timeout);
           while(validateElement_Displayed(procedureRecord,timeout)){
               click(allCheckboxticked, timeout);
               click(bulkCompleteButton);
           }
    }
    public void clickBulkComplete(){
        click(bulkCompleteButton);
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

    public void savedSearches(String saveSearchesText) throws InterruptedException {
        Thread.sleep(2000);
        awaitElement(saveSearches,timeout);
        javascriptClick(_driver.findElement(saveSearches));
        Thread.sleep(2000);
        _driver.navigate().refresh();
        Thread.sleep(3000);
        javascriptClick(_driver.findElement(By.xpath("//a[contains(text(),'"+saveSearchesText+"')]")));
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

    public void multipleSearch(ArrayList<String> lapespside) throws InterruptedException {
        String multliLapEpisode ="";
        for(String lapEpisode:lapespside){
        multliLapEpisode = multliLapEpisode.trim().replace(" ",",");
        findOne(labEpisodeTextBox,lapEpisode);
        javascriptClick(_driver.findElement(findButton));
        stepInfoWithScreenshot("Click Find Button");
        awaitElement(allCheckboxticked,timeout);
        click(allCheckboxticked,timeout);
        stepInfo("Ticked All checkbox");
        stepInfoWithScreenshot("Preparing to click Bulk Complete");
        awaitElement(bulkCompleteButton,timeout);
        click(bulkCompleteButton,timeout);
        }
        Thread.sleep(2000);
        _driver.navigate().refresh();

    }

    public void backProtocolProcedureSearch(){
        click(By.xpath("//a[@ng-click='navBack();']"),timeout);
    }

  public void searchSelectionMultiple(String[] procedures) throws InterruptedException {

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
    public void selectlistlabespidoFromProcedureList(ArrayList<String> labEpisode){

        for(String value:labEpisode){
            int y=0;
            By labEpisodefield = By.xpath("//span[contains(@id,'LBProtocolProcedure_List')and text()='%s']".replace("%s",value +"-1"));
            while(!validateElement_Enabled_Displayed(labEpisodefield,10)){
                if(validateElement_Displayed(nextpageProcedureList)){
                    click(nextpageProcedureList);
                }else {
                    Assert.fail("Unable to find " + labEpisode.toArray().toString());
                }
            }

            for(WebElement element: find(labEpisodefield)){
                //element.click();
                javascriptClick(element);
                stepInfoWithScreenshot("Ticked Lab Episode "+value+" "+y++);
            }
        }
    }

    public void findOne(By by,String input) throws InterruptedException {
            Thread.sleep(2000);
            super.findOne(by,timeout).clear();
            Thread.sleep(1000);
            super.findOne(by,timeout).sendKeys("");
            Thread.sleep(1000);
            super.findOne(by,timeout).sendKeys(input);
            super.findOne(by,timeout).sendKeys(Keys.TAB);
    }
    public  String generateRandon()
    {
        String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String unique_no = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        return unique_no;
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
