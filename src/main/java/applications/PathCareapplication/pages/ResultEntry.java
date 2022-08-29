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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultEntry extends AbstractPage {

    private final By specimenNumber = By.xpath("//input[@name='SpecimenNumber']");
    private final By labEpisode = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");

    private final By resultEntryiFrame = By.xpath("//iframe[@id='TRAK_main']");
    private final By searchTestresultEntry = By.xpath("//img[contains(@id,'LBTSIValue')]");

    private final By applyTestResult = By.xpath("//input[@id='apply1']");
    private  By descriptionSearch = By.xpath("//td[contains(text(),'%s')]");

    private By validate = By.xpath("//input[@id='validate1']");
    private By reportpreview = By.xpath("//a[@id='ReportPreview']");
    private  By backtotestSetList = By.xpath("//a[@class='breadcrumbBack']");
    private  By reportCollectionDate = By.xpath("//input[@name='DateFrom']");

    private  By testSetComments = By.xpath("//a[@id='LBTSCommentsLink']");

    private  By inputTestresults = By.xpath("//input[contains(@id,'Value')]");

    private By testSetCommentsTextboxiFrame = By.xpath("//iframe[@id='TRAK_info']");

    private By testSetCommentsTextbox = By.xpath("//body[@id='CKEditorContentLBTSComments']");
    private By testCommentswindowiframe = By.xpath("//iframe[@title]");

    private  By acceptCommentbutton = By.xpath("//input[@id='accept1']");

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

    private void reportPreview(){
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult,10);
        stepPassedWithScreenshot("Successfully clicked Apply button");
        if(validateElement_Displayed(validate)){
            click(validate);
            stepPassedWithScreenshot("Successfully clicked validate button");
            String currentWindow = super._driver.getCurrentUrl();
            click(reportpreview,10);
            validateElement_Displayed(reportpreview,10);
            switchToWindowHandle(currentWindow);

        }
    }

    public void singleTestsetComment(String singleLabespido, String testresult,String comment,String dir){
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
        click(testSetComments);
        switchToDefaultContext();
        switchToFrame (testSetCommentsTextboxiFrame);
        switchToFrame(testCommentswindowiframe);
        _driver.findElement(testSetCommentsTextbox).sendKeys(comment);
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
                    click(backtotestSetList);
                    click(backtotestSetList);
                    results = false;
                }else{

                    LabResultsEntry(singleLabespido);
                    testSetListSingle(singleLabespido);
                    labEntryTestSpecialHandlinglist(labresults2.split(","));
                    click(backtotestSetList);
                    click(backtotestSetList);
            }
        }

        }

    public int fileChecker(String dir){
        File file = new File(dir);
        int x = 0;

     long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15L, TimeUnit.SECONDS);
        while(System.nanoTime() < endTime) {
                for (String files : file.list()) {
                    if (files.contains(".pdf")) {
                        x++;
                    }else if(x==numberfiles){
                        return x;
                    }
                }
        }
        return x;

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
        String dir = this.dir;
        WebDriverWait wait = new WebDriverWait(super._driver,30L);
        wait.pollingEvery(Duration.ofSeconds(10L));
        return wait.until(EventFiringWebDriver::new).getWindowHandles().size() == 2 && fileChecker(dir)==numberfiles;

    }
}
