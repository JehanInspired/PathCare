package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private final By lookupicon = By.xpath("//img[contains(@id,'Value')]");
    private final By inputTestresultlast = By.xpath("//input[contains(@id,'0_LBTSIValue')]");
    private final By pinserttestreults = By.xpath("//*[contains(@id,'Value')]/p");
    private final By searchinput = By.xpath("//img[contains(@id,'Value')]");
    private final By inputTestresult = By.xpath("//td/span/input");

    private final By testSetCommentsTextboxiFrame = By.xpath("//iframe[@id='TRAK_info']");

    private final By testSetCommentsTextbox = By.xpath("//body[@id='CKEditorContentLBTSComments']");
    private final By testCommentswindowiframe = By.xpath("//iframe[@title]");

    private final  By acceptCommentbutton = By.xpath("//input[@id='accept1']");
    private final By authoriseButton = By.xpath("//input[@id='authorise1']");
    private final  By worklistbutton = By.xpath("//button[text()='Work List']");
    private final By saveSearchlink = By.xpath("//a[text()='Save Search']");
    private final By descriptionSaveSearch = By.xpath("//input[@name='SRCHDesc']");
    private final By lookupiconbutton = By.xpath("//md-icon[@id='LBTestSet_Find_0-item-TestSetSelect-lookupIcon']");
    private final By Firstlistrow  = By.xpath("//tr[@id='LBTestSet_Find_0-item-TestSetSelect-lookupRow-1']");
    private final By updatebutton = By.xpath("//button[text()='Update']");

    private final By notficationApply = By.xpath("//div[@class='notification_message']");

    private final By savedSearches = By.xpath("//a[text()='Saved Searches']");

    private By savedResults = By.xpath("//a[text()='%s']");

    private By testsetlistTitle = By.xpath("//span[text()='%s']");

    private final  By footer = By.xpath("//div[@id='tc_Footer']");
    private final  By nextpage = By.xpath("//a[text()='Next >']");
    private final By requiredfield = By.xpath("//span[@class='clsColourTab clsResultRequired']");
    private By organismText = By.xpath("//div[contains(@id,'LBTSIValue')]/p");
    private By labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");

    private final By receivedTestList = By.xpath("//span[text()='Received']");
    private final By pinserttestreult = By.xpath("//img[contains(@id,'Value')]");
    private By closelookup = By.xpath("//span[@id='OverlayCloseLookupOverlayDiv']");

    private By espiodeNumberLink = By.xpath("//a[@id='LBEPNumber']");

    private By requiredfieldblackbox = By.xpath("//span[contains(@title,'Required')]");

    private By selectSingletest= By.xpath("");

    private By  episodeNumber;
   private  String desc = "";

    public String dir;
    public int numberfiles;


    public void testSetListSingle(String labespide) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));
        if (validateElement_Displayed(episodeNumber)) {
            stepPassedWithScreenshot("Able to view testset " + getText(episodeNumber));
            click(episodeNumber);

        }
    }
    public void testSetListMultiple(String labespide) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));

        if (validateElement_Displayed(episodeNumber) && validateElement_Enabled_Displayed(receivedTestList)) {
            stepPassedWithScreenshot("Able to view testset " + getText(episodeNumber));
            click(receivedTestList);

        }
    }
    public void LabResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now();
            sendKeys(labEpisode,labespide);
            sendKeys(reportCollectionDate, dtf.format(todaydate));
            click(findButton,10);
        }

    public void labEntryTestSpecialHandling(String testresult) throws InterruptedException {
        this.numberfiles=1;
        switchToFrame(resultEntryiFrame);
        descriptionSearch  = By.xpath("//td[contains(text(),'%s')]".replace("%s",testresult));
        for(WebElement element:find(searchTestresultEntry,5)){
            element.click();
            click(descriptionSearch);
        }

        reportPreview();

    }

    public void labEntryTestFieldMutatable(String[] value,String lasttestvalue)  {
        switchToFrame(resultEntryiFrame);
        int x =0;
        for(WebElement element:find(pinserttestreult)){
            element.click();
            labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",value[x++]));

            if(!validateElement_Enabled_Displayed(labresultTextfield, 10)){
                element.click();
            }
            if(!(x==7)) {
                click(labresultTextfield);
          }else{
                sendKeys(inputTestresultlast,lasttestvalue);
           }
        }

    }


    public void labEntryTestSpecialHandlinglist(String[] testresult,Boolean extrfield) throws InterruptedException {
        numberfiles = 2;
        switchToFrame(resultEntryiFrame);
        int x =0;

        for(WebElement element:find(inputTestresult ,5)){
            if(element.isEnabled() && element.isDisplayed()) {
                Thread.sleep(5000);
                element.sendKeys(testresult[x]);
                        element.sendKeys(Keys.TAB);
                x++;
            }
        }
        if(extrfield) {
            for (WebElement element1 : find(pinserttestreults, 5)) {

                JavascriptExecutor jExecutor = (JavascriptExecutor) this._driver;
                jExecutor.executeScript("arguments[0].textContent = arguments[1];", element1, "Absent");
                element1.click();
                Thread.sleep(3000);
                    element1.click();
                    element1.sendKeys(Keys.TAB);

            }
        }

        stepPassedWithScreenshot("Successfully Entered the Results");
        if(!extrfield) {
            reportPreview();
        }
    }
    public void authorise(){

        switchToDefaultContext();
        switchToFrame(resultEntryiFrame);
        if(validateElement_Enabled_Displayed(authoriseButton,10)) {
            click(authoriseButton);
            if(validateElement_Displayed(notficationApply,10)){
                stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
            }else{
                Assert.fail("Unable to receive a notification");
            }
        }else{
            Assert.fail("Unable to click a authorise Button");
        }

    }
    public void singleLabResultsTestSetNameEntry(String testSetName){
        selectSingletest = By.xpath("//span[text()='%s']".replace("%s",testSetName));
        click(selectSingletest,5);
    }
    public void singleTestsetCommentWithoutReport(String singleLabespido, String testresult,String comment){
        numberfiles =1;
        LabResultsEntry(singleLabespido);
        testSetListSingle(singleLabespido);
        if(!comment.isEmpty()||!comment.contentEquals("")){
            comments(comment);}
        switchToFrame(resultEntryiFrame);
        sendKeys(inputTestresults,testresult);
        _driver.findElement(inputTestresults).sendKeys(Keys.TAB);
        applyResultOnly();
       // reportPreview();

    }
    public void selectTestSetResultlist(String testSet){
        click(By.xpath("//span[text()='%s']".replace("%s", testSet)));
        stepPassedWithScreenshot("Successfully clicked "+testSet);
    }

    public Boolean checkvaluesTestResults(HashMap<String,String> testlistResult,String testSet){
        boolean results =false;
        click(By.xpath("//span[text()='%s']".replace("%s", testSet)));
        stepPassedWithScreenshot("Successfully clicked "+testSet);
        switchToFrame(resultEntryiFrame);
        for(String testResult:testlistResult.keySet()){
          By testSetElement = By.xpath("//td[preceding-sibling::td[contains(.,'"+testResult+"')]]//input[contains(@id,'LBTSIValue') and(not(contains(@type,'hidden')))]");
         String value = findOne(testSetElement).getAttribute("value") ;
          if(value.isBlank()||value.isEmpty() || Math.abs(Double.valueOf(value)) !=Math.abs(Double.valueOf(testlistResult.get(testResult)))){
              stepInfo("checked test set " + testResult + " has test Result " + value +" but excepted is "+testlistResult.get(testResult));
                return false;
            }
          if(Math.abs(Double.valueOf(value))==Math.abs(Double.valueOf(testlistResult.get(testResult)))) {
              stepPassed("Successfully checked test set " + testResult + " has test Result " + value);
              results = true;
          }
        }
    return results;
    }



    public void mutlipleSuperSetTestSet(String singleLabespido,HashMap<String, List<String>> superTestset) throws InterruptedException {
        LabResultsEntry(singleLabespido);
        for(String testSet:superTestset.keySet()){
            int counter =0;

             click(By.xpath("//span[text()='"+testSet+"']"));
            stepPassedWithScreenshot("Successfully clicked "+testSet);
            switchToFrame(resultEntryiFrame);
             for(String testresult:superTestset.get(testSet)) {
                 int count = find(requiredfieldblackbox).size();
                 if(testSet.contains("CSF Microscopy")||testSet.contains("CSF Macroscopy")||testSet.contains("Cryptococcal Antigen LFA")){
                    find(lookupicon).get(counter).click();
                     labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='"+testresult+"']");
                     click(labresultTextfield,10);
                     stepPassedWithScreenshot("Successfully Selected "+testSet +" value "+testresult);
                     if (testSet.contains("CSF Microscopy") && counter == 1) {
                         break;
                        }
                 }
                 boolean b = testSet.contains("CSF Microscopy") || testSet.contains("CSF Macroscopy") || testSet.contains("Cryptococcal Antigen LFA");
                 if(!b) {

                     find(inputTestresults).get(counter).sendKeys(testresult);
                     find(inputTestresults).get(counter).sendKeys(Keys.TAB);
                     Thread.sleep(5000);
                     if(find(requiredfieldblackbox).size() == count){
                         while(find(requiredfieldblackbox).size() == count){
                             find(inputTestresults).get(counter).sendKeys(testresult);
                             find(inputTestresults).get(counter).sendKeys(Keys.TAB);
                             Thread.sleep(5000);
                         }
                     }
                     count--;
                 }
                 counter++;
             }

             switchToDefaultContext();
             switchToFrame(resultEntryiFrame);
             click(applyTestResult);
             stepPassedWithScreenshot("Successfully applied "+testSet);
             Thread.sleep(4000);
             if(validateElement_Displayed(validate)){
                 click(validate,10);
                 stepPassedWithScreenshot("Successfully Validated "+testSet);
             }
            switchToDefaultContext();
            click(backtotestSetList,10);


        }
    }

    public void mutipleTestsetCommentMutatableWithoutReport(String singleLabespido, String[] testresult,String lasttestvalue, String comment,Boolean clickEspodeNumber)  {

        numberfiles =1;
        LabResultsEntry(singleLabespido);
        testSetListMultiple(singleLabespido);
        if(!comment.isEmpty()||!comment.contentEquals("")){
            comments(comment);
        }
        labEntryTestFieldMutatable(testresult,lasttestvalue);
        switchToDefaultContext();
        switchToFrame(resultEntryiFrame);
        applyResultOnly();

        if(clickEspodeNumber){
            interactEspiodeNumber();
            switchToDefaultContext();
            labEntryTestFieldMutatable(testresult,lasttestvalue);
        }

        switchToDefaultContext();
        switchToFrame(resultEntryiFrame);
        applyResultOnly();

    }

    public void mutipleTestsetCommentWithoutReport(String singleLabespido, String[] testresult, String comment, Boolean extrfield,Boolean apply) throws InterruptedException {

        numberfiles =1;
        LabResultsEntry(singleLabespido);
        testSetListMultiple(singleLabespido);
        if(!comment.isEmpty()||!comment.contentEquals("")){
        comments(comment);}
        labEntryTestSpecialHandlinglist(testresult,extrfield);
        switchToDefaultContext();
        switchToFrame(resultEntryiFrame);
        if(apply) {
            applyResultOnly();
        }

    }

    public void applyResultOnly(){
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult,10);
        if(validateElement_Displayed(notficationApply,10)){
        stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }

    }

  private void interactEspiodeNumber(){
        click(espiodeNumberLink);
    }
    public void  onlyapplyandvalidate(){
        if(validateElement_Displayed(applyTestResult)) {
            Assert.assertTrue(validateElement_Displayed(applyTestResult));
            click(applyTestResult, 10);

            if(validateElement_Displayed(notficationApply,10)){
                stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
            }else{
                Assert.fail("Unable to receive notification results for Test set");
            }
        }

        if(validateElement_Displayed(validate,10)) {
            click(validate);
            stepPassedWithScreenshot("Successfully received  "+getText(notficationApply,5));
        }else{
            Assert.fail("Unable to receive notification to validate");
        }
    }

    public void reportPreview() throws InterruptedException {
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
            if(validateElement_Enabled_Displayed(reportpreview,10)) {
                click(reportpreview, 10);
                Thread.sleep(5000);
            }else{
                Assert.fail("Unable to click report preview");
            }


            if(waitForDisplayed()){
                Set<String> currentWindow =  super._driver.getWindowHandles();
                _driver.switchTo().window(switchToWindowHandleFirst(currentWindow,false)).close();
                _driver.switchTo().window( switchToWindowHandleFirst(currentWindow,true));

            }


        }
    }

    public void singleTestsetComment(String singleLabespido, String testresult,String comment) throws InterruptedException {
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

    public void backtoTestSetList(){
        click(backtotestSetList,10);
        click(backtotestSetList,10);
    }

    public void mutlipleLabEntryTestSet(List<String> labespide, String labresults, String labresults2,String dir) throws InterruptedException {
       boolean results = true;
       this.dir =dir;

            for (String singleLabespido : labespide) {
                if(results) {
                    LabResultsEntry(singleLabespido);
                    testSetListSingle(singleLabespido);
                    labEntryTestSpecialHandling(labresults);
                    backtoTestSetList();
                    results = false;
                }else{

                    LabResultsEntry(singleLabespido);
                    testSetListSingle(singleLabespido);
                    labEntryTestSpecialHandlinglist(labresults2.split(","),false);
                    backtoTestSetList();
            }
        }

        }

     public boolean querysearchLabResults(String department){

         desc = "Reference lab "+department+(new Random().nextDouble() - new Random().nextDouble());
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

            scrollToElement(footer);
            savedResults = By.xpath("//a[contains(text(),'%s')]".replace("%s",desc));
            while(!validateElement_Displayed(savedResults)) {

                click(nextpage);
                scrollToElement(footer);
            }

             if (getText(savedResults,10).contains(desc)) {
                 stepPassedWithScreenshot("Successfully received Saved Search on " + desc);
                 return true;
             }
        return false;
     }

    public boolean labResultsSelectResult(String department){
        if(querysearchLabResults(department)){
            click(savedResults);
            testsetlistTitle = By.xpath("//span[contains(text(),'%s')]".replace("%s",desc));
            if(getText(testsetlistTitle).contains(desc)){
                stepPassedWithScreenshot("Successfully Recieved Test Set List Title "+desc);
                return true;
            }

        }
        return false;
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

   /* public boolean waitforDisplayed(By elment, int index){

        if(find(elment).get(index).isDisplayed()){
            return true;
        }

         return false;
    }*/

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
