package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.AbstractPage;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ResultEntry extends AbstractPage {

    private final By specimenNumber = By.xpath("//input[@name='SpecimenNumber']");
    private final By labEpisode = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By resultEntryiFrame = By.xpath("//iframe[@id='TRAK_main']");
    private final By searchTestresultEntry = By.xpath("//img[contains(@id,'LBTSIValue')]");

    private final By applyTestResult = By.xpath("//input[@id='apply1']");
    private  By descriptionSearch = By.xpath("//td[contains(text(),'%s')]");

    private final By validate = By.xpath("//input[@id='validate1']");
    private final By reportpreview = By.xpath("//a[@id='ReportPreview']");
    private final  By backtotestSetList = By.xpath("//a[@class='breadcrumbBack']");
    private final  By reportCollectionDate = By.xpath("//input[@name='DateFrom']");

    private final  By testSetComments = By.xpath("//a[@id='LBTSCommentsLink']");

    private final By inputTestresults = By.xpath("//input[contains(@id,'Value')]");

    private final By testSetCommentsTextboxiFrame = By.xpath("//iframe[@id='TRAK_info']");

    private final By testSetCommentsTextbox = By.xpath("//body[@id='CKEditorContentLBTSComments']");
    private final By testCommentswindowiframe = By.xpath("//iframe[@title]");

    private final  By acceptCommentbutton = By.xpath("//input[@id='accept1']");
    private final  By worklistbutton = By.xpath("//button[text()='Work List']");
    private final By saveSearchlink = By.xpath("//a[text()='Save Search']");
    private final By descriptionSaveSearch = By.xpath("//input[@name='SRCHDesc']");
    private final By lookupiconbutton = By.xpath("//md-icon[@id='LBTestSet_Find_0-item-TestSetSelect-lookupIcon']");
    private final By Firstlistrow  = By.xpath("//tr[@id='LBTestSet_Find_0-item-TestSetSelect-lookupRow-1']");
    private final By updatebutton = By.xpath("//button[text()='Update']");

    private final By notficationApply = By.xpath("//div[@class='notification_message']");

    private final By savedSearches = By.xpath("//a[text()='Saved Searches']");

    private By savedResults = By.xpath("//a[text()='%s']");

    private By  episodeNumber;

    public String dir;
    public int numberfiles;


    public void testSetListSingle(String labespide) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));
        if (validateElement_Displayed(episodeNumber)) {
            stepPassedWithScreenshot("Able to view testset " + getText(episodeNumber));
            click(episodeNumber);

        }
    }
    public void LabResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now();
        Date newdate = new Date();
            sendKeys(labEpisode,labespide);
            sendKeys(reportCollectionDate, dtf.format(todaydate));
            click(findButton,10);
        }
    public void labEntryTestSpecialHandling(String testresult){
        this.numberfiles=1;
        switchToFrame(resultEntryiFrame);
        descriptionSearch  = By.xpath("//td[contains(text(),'%s')]".replace("%s",testresult));
        for(WebElement element:find(searchTestresultEntry,5)){
            element.click();
            click(descriptionSearch);
        }

        reportPreview();


    }


    public void labEntryTestSpecialHandlinglist(String[] testresult){
        numberfiles = 2;
        switchToFrame(resultEntryiFrame);
        int x =0;

        for(WebElement element:find(searchTestresultEntry,5)){
            element.click();
            descriptionSearch  = By.xpath("//td[contains(text(),'%s')]".replace("%s",testresult[x]));
            click(descriptionSearch);
            x++;
        }
        stepPassedWithScreenshot("Successfully Entered the Results");
        reportPreview();


    }
    public void singleTestsetCommentWithoutReport(String singleLabespido, String testresult,String comment){
        numberfiles =1;
        LabResultsEntry(singleLabespido);
        testSetListSingle(singleLabespido);
        comments(comment);
        switchToFrame(resultEntryiFrame);
        sendKeys(inputTestresults,testresult);
        _driver.findElement(inputTestresults).sendKeys(Keys.TAB);
       applyResultOnly();

    }

    private void applyResultOnly(){
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult,10);
        if(validateElement_Displayed(notficationApply,10)){
        stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }

    }

    private void reportPreview(){
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult,10);
        if(validateElement_Displayed(notficationApply,10)){
            stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }else{
            Assert.fail("Unable to receive notification results for Test set");
        }
        if(validateElement_Displayed(validate)){
            click(validate);
            stepPassedWithScreenshot("Successfully clicked validate button");
            validateElement_Displayed(reportpreview,10);
            click(reportpreview,10);
            Set<String> currentWindow =  super._driver.getWindowHandles();

            if(waitForDisplayed()){
                _driver.switchTo().window(switchToWindowHandleFirst(currentWindow,false)).close();
                _driver.switchTo().window( switchToWindowHandleFirst(currentWindow,true));

            }


        }
    }

    public void singleTestsetComment(String singleLabespido, String testresult,String comment){
        numberfiles =1;
        LabResultsEntry(singleLabespido);
        testSetListSingle(singleLabespido);
        comments(comment);
        switchToFrame(resultEntryiFrame);
        sendKeys(inputTestresults,testresult);
        _driver.findElement(inputTestresults).sendKeys(Keys.TAB);
        reportPreview();

    }

    public void comments(String comment){
        switchToFrame(resultEntryiFrame);
        click(testSetComments,10);
        switchToDefaultContext();
        switchToFrame (testSetCommentsTextboxiFrame);
        switchToFrame(testCommentswindowiframe);
        if(validateElement_Displayed(testSetCommentsTextbox,10)) {
            _driver.findElement(testSetCommentsTextbox).sendKeys(comment);
        }
        stepPassedWithScreenshot("Successfully entered comments "+comment);
        switchToDefaultContext();
        switchToFrame(testSetCommentsTextboxiFrame);
        click(acceptCommentbutton);
        switchToDefaultContext();
    }

    public void mutlipleLabEntryTestSet(List<String> labespide, String labresults, String labresults2,String dir)  {
       boolean results = true;
       this.dir =dir;

            for (String singleLabespido : labespide) {
                if(results) {
                    LabResultsEntry(singleLabespido);
                    testSetListSingle(singleLabespido);
                    labEntryTestSpecialHandling(labresults);
                    click(backtotestSetList,10);
                    click(backtotestSetList,10);
                    results = false;
                }else{

                    LabResultsEntry(singleLabespido);
                    testSetListSingle(singleLabespido);
                    labEntryTestSpecialHandlinglist(labresults2.split(","));
                    click(backtotestSetList,10);
                    click(backtotestSetList,10);
            }
        }

        }

     public boolean querysearchLabResults(String department){

         String desc = "Reference lab "+department+(new Random().nextDouble() - new Random().nextDouble());;
         click(lookupiconbutton);
         click(Firstlistrow);

            click(worklistbutton);

            click(saveSearchlink);
           // switchToFrame(s);
            findOne(descriptionSaveSearch,desc);
            click(updatebutton);
            click(backtotestSetList);
            if(validateElement_Displayed(savedSearches)){stepPassedWithScreenshot("Successfully reached Lab Results Entry ");}
            click(savedSearches);
            savedResults = By.xpath("//a[text()='%s']".replace("%s",desc));
            if(getText(savedResults).contentEquals(desc)){
                stepPassedWithScreenshot("Successfully received Saved Search on "+desc);
                return true;
            }
        return false;
     }

    public void labResultsSelectResult(String department){
        if(querysearchLabResults(department)){
            click(savedResults);


        }
    }





    public int fileChecker(String dir){
        File file = new File(dir);
        int x = 0;

     long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15, TimeUnit.SECONDS);
        while(System.nanoTime() < endTime) {
                for (String files : file.list()) {
                    if (files.contains(".pdf")) {
                        x++;
                        stepInfo("Checking pdf reporting files");
                    }else if(x==numberfiles){
                        stepPassed("Successfully received files ");
                        return x;
                    }
                }
        }
        return x;

    }

    public void findOne(By by, String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }




    public ResultEntry(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {

        WebDriverWait wait = new WebDriverWait(super._driver,30L);
        wait.pollingEvery(Duration.ofSeconds(10L));
        return wait.until(EventFiringWebDriver::new).getWindowHandles().size() == 2;

    }

    public String switchToWindowHandleFirst(Set<String> windows, boolean firstorsecond){
        int counter =0;
        Iterator var = windows.iterator();
        while(var.hasNext()){
          String value = (String) var.next();
            if(firstorsecond){
                return value;
            }else if(counter==1){
                return value;
            }
        counter++;
        }
        return null;

    }
}
