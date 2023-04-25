package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.ResultsEntry;
import applications.PathCareapplication.models.ResultsEntryVerify;
import applications.PathCareapplication.models.SpecimenReceiveEntity;
import applications.PathCareapplication.models.TestSetValuesEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ResultEntry extends AbstractExtension {

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
    private final   By testSetOptionButtonDropDown = By.xpath("//a[text()='Test Set Options']");
    private final By MultipleSpecimenContainer = By.xpath("//a[contains(@id,'LBProtocolz')]");

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

    private final By notfication = By.xpath("//div[@class='notification_message']");

    private final By savedSearches = By.xpath("//a[text()='Saved Searches']");

    private By savedResults = By.xpath("//a[text()='%s']");

    private By testsetlistTitle = By.xpath("//span[text()='%s']");

    private final  By footer = By.xpath("//div[@id='tc_Footer']");
    private final  By nextpage = By.xpath("//a[text()='Next >']");
    private final By requiredfield = By.xpath("//span[@class='clsColourTab clsResultRequired']");
    private final By organismText = By.xpath("//div[contains(@id,'LBTSIValue')]/p");
    private  By labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");

    private final By receivedTestList = By.xpath("//span[text()='Received']");
    private final By pinserttestreult = By.xpath("//img[contains(@id,'Value')]");
    private final By closelookup = By.xpath("//span[@id='OverlayCloseLookupOverlayDiv']");
    private final By iframeProcessing = By.xpath("//iframe[@id='TRAK_main']");

    private final By espiodeNumberLink = By.xpath("//a[@id='LBEPNumber']");

    private final By requiredfieldblackbox = By.xpath("//span[contains(@title,'Required')]");

    private final By viewQueues = By.xpath("//a[@id='ViewQueuesLink']");
    private final By queueText = By.xpath("//label[@id='LBQ_Descz1']");

    private final By resultflag = By.xpath("//label[contains(@id,'AbnormalFlag')]");
    private final By refeRange = By.xpath("//label[contains(@id,'LBTSIRange')]");

    private By selectSingletest= By.xpath("");

    private String locationNew = "";

    private Boolean firstTime =true;

    private ArrayList<ResultsEntry> resultsEntryArrayList = new ArrayList<>();

    private By  episodeNumber;
   private  String desc = "";

    public String dir;

    int timeout =20;
    public int numberfiles;


    public void testSetListSingle(String lab_episode) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", lab_episode));
        awaitElement(episodeNumber,timeout);
        stepPassedWithScreenshot("Able to view Test Set " + getText(episodeNumber,timeout));
        click(episodeNumber,timeout);


    }


    public void testSetListMultiple(String labespide) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));

        if (validateElement_Displayed(episodeNumber) && validateElement_Enabled_Displayed(receivedTestList)) {
            stepPassedWithScreenshot("Able to view Test Set " + getText(episodeNumber));
            click(receivedTestList);

        }
    }
    public void multipleTestResult(ArrayList<String> multiple_labEpisode){
        for(String lab_Episode:multiple_labEpisode) {
            LabResultsEntry(lab_Episode);
            episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", lab_Episode));

            if (validateElement_Displayed(episodeNumber)) {
                stepPassedWithScreenshot("Able to view Test Set " + getText(episodeNumber));
                click(episodeNumber);
                switchToFrame(iframeProcessing);
                testSetOption();
                switchToDefaultContext();
                //click back twice
                click(By.xpath("//a[@ng-click='navBack();']"));
                click(By.xpath("//a[@ng-click='navBack();']"));
            }
        }
    }
    private void backToResultEntrySeacrh(){
        switchToDefaultContext();
        click(By.xpath("//a[@ng-click='navBack();']"));
        click(By.xpath("//a[@ng-click='navBack();']"));
    }

    void changeLocation(String location,InterSystemLoginPage interSystemloginPage, Analytical analytical,String specimenRecieve){

        if (!locationNew.contentEquals(location)){
            locationNew = location;
            switchToDefaultContext();
            interSystemloginPage.setLocation(location);
            if(!firstTime) {
                switchToDefaultContext();
                interSystemloginPage.changelocation();

            }

            interSystemloginPage.userselection();
            analytical.navigateResultEntry();
            if(!firstTime) {
                awaitElement(specimenNumber,timeout);
                sendKeys(specimenNumber,specimenRecieve,true,true,timeout);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate todaydate = LocalDate.now().minusDays(3);
                sendKeys(reportCollectionDate, dtf.format(todaydate));
                awaitElement(findButton, timeout);
                click(findButton, timeout);
                testSetListSingle(specimenRecieve);
            }
            firstTime=false;

        }
    }
    public void structureLabResultEntry(ArrayList<ResultsEntry> resultEntries, ArrayList<SpecimenReceiveEntity> specimenReceiveEntities, ArrayList<TestSetValuesEntity> testSetValueEntities, InterSystemLoginPage interSystemloginPage, Analytical analytical) throws IllegalAccessException {
        changeLocation(testSetValueEntities.get(0).getUserprofile_FK(),interSystemloginPage,analytical,"");
        String testSetValue ="";
        for(ResultsEntry resultsEntry:resultEntries){

            for(SpecimenReceiveEntity specimenReceiveEntity : specimenReceiveEntities){
                if(specimenReceiveEntity.getPk().contains(resultsEntry.getSpecimenReceive_FK())){
                    resultsEntry.setSpecimenReceive_FK(specimenReceiveEntity.getSpecimenNumber());
                    resultsEntry.setPatient_FK(specimenReceiveEntity.getPatientKey_FK());

                    resultsEntryArrayList.add(resultsEntry);
                }
            }

        }
        searchSpecimen(resultEntries, testSetValueEntities,interSystemloginPage, analytical);
    }

    public void searchSpecimen(ArrayList<ResultsEntry> data, ArrayList<TestSetValuesEntity> testSetValueEntities, InterSystemLoginPage interSystemloginPage, Analytical analytical) throws IllegalAccessException {

        for(ResultsEntry resultsEntry:data)
        {
            specimenNumberEntry(resultsEntry.getTestSetValue_FK(),resultsEntry.getTestSet(),resultsEntry.getSpecimenReceive_FK(), testSetValueEntities, interSystemloginPage,  analytical);

        }

    }
    public void mutlipleVerifyLabEpisode(ArrayList<ResultsEntryVerify> data, List<String> Lab_episodes, InterSystemLoginPage interSystemloginPage, Analytical analytical){
        for(String Lab_episode:Lab_episodes){
            for(ResultsEntryVerify resultsEntryVerifySingle:data) {
                if (resultsEntryVerifySingle.getRegistration_FK().contentEquals(Lab_episode.split(",")[0])) {
                    changeLocation(resultsEntryVerifySingle.getUserprofile_FK(),interSystemloginPage,analytical,Lab_episode.split(",")[1]);
                    resultverify(Lab_episode.split(",")[1],resultsEntryVerifySingle.getReferenceRange(),resultsEntryVerifySingle.getExpectedQueue(),resultsEntryVerifySingle.getExpectedResultFlag());
                }
            }

        }
    }
    public void resultverify(String labEpisode,String ranges,String queueValue,String resultFlagValue){
        //Search Specimen
        LabResultsEntry(labEpisode);
        testSetListSingle(labEpisode);
        //Verify results
        if(referenceRange(ranges)){
            stepPassedWithScreenshot("Able to view ranges "+ranges);
        }
        if(expectedqueue(queueValue)){
            stepPassed("Able to view queue "+queueValue);
        }
        if(expectedResultflagSingle(resultFlagValue)){
            stepPassed("Able to view result flag "+resultFlagValue);
        }
        switchToDefaultContext();
        backtoTestSetList();
    }

    private Boolean referenceRange(String ranges){
        switchToDefaultContext();
        switchToMainFrame();
        return getText(refeRange,timeout).contentEquals(ranges);
    }
    private Boolean expectedqueue(String queueValue){
        click(testSetOptionButtonDropDown,timeout);
        click(viewQueues,timeout);
        switchToDefaultContext();
        switchToFrame(testSetCommentsTextboxiFrame);
       return getText(queueText,timeout).contentEquals(queueValue);
    }

    private Boolean expectedResultflagSingle(String resultflagValue){
        switchToDefaultContext();
        switchToMainFrame();
       return getText(resultflag,timeout).contentEquals(resultflagValue!=null?resultflagValue: "  " );
    }

    private void specimenNumberEntry(String testSetValue_Fk,String testSet,String specimenRecieve, ArrayList<TestSetValuesEntity> testSetValueEntities, InterSystemLoginPage interSystemloginPage, Analytical analytical) throws IllegalAccessException {

        awaitElement(specimenNumber,timeout);
        sendKeys(specimenNumber,specimenRecieve,true,true,timeout);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now().minusDays(3);
        sendKeys(reportCollectionDate, dtf.format(todaydate));
        awaitElement(findButton,timeout);
        click(findButton,timeout);
        testSetListSingle(specimenRecieve);
        enterdataResults(testSetValue_Fk,specimenRecieve ,testSetValueEntities, interSystemloginPage,  analytical);
        backtoTestSetList();
    }

    private void enterdataResults(String testvalueKey,String specimenReciveNumber, ArrayList<TestSetValuesEntity> testSetValueslistEntity, InterSystemLoginPage interSystemloginPage, Analytical analytical) throws IllegalAccessException {
        switchToDefaultContext();
        switchToMainFrame();
        for(TestSetValuesEntity testSetValue : testSetValueslistEntity){
            if(testSetValue.getTestValueKey().contentEquals(testvalueKey)) {
                if (testSetValue.getUserprofile_FK() != null){
                    changeLocation(testSetValue.getUserprofile_FK(), interSystemloginPage, analytical,specimenReciveNumber);
                    switchToDefaultContext();
                    switchToMainFrame();
                }
                reflexLabEpisode(testSetValue.getReflex());
                for(String value :testSetValue.getTestvalues().keySet()){
                    By resultValue = By.xpath("//a[contains(text(),'"+value.trim()+"')]//parent::td//parent::tr//parent::tr//img[contains(@id, 'Value')]");
                    Boolean found =false;
                    if(validateElement_Enabled_Displayed(resultValue,timeout)) {
                        click(resultValue);
                        found = true;

                    }
                            labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",testSetValue.getTestvalues().get(value)));
                            if(validateElement_Enabled_Displayed(labresultTextfield,timeout) && found){
                                awaitElement(labresultTextfield,timeout);
                                awaitClickableElement(labresultTextfield,timeout,6).click();
                            }else{
                                findOne(By.xpath("//a[text()='"+value.trim()+"']//parent::td//parent::tr//parent::tr//input"),timeout).clear();
                                awaitElement(By.xpath("//a[text()='"+value.trim()+"']//parent::td//parent::tr//parent::tr//input"),timeout);
                                sendKeys(By.xpath("//a[text()='"+value.trim()+"']//parent::td//parent::tr//parent::tr//input"),testSetValue.getTestvalues().get(value),timeout);
                            }

                            if(validateElement_Displayed(closelookup,timeout)){
                                click(closelookup,timeout);
                            }
                            //click(By.xpath(/));
                            stepInfoWithScreenshot("Entered "+value.trim()+" result "+testSetValue.getTestvalues().get(value));

                }

                if(testSetValue.getValidate() !=null){
                    switchToDefaultContext();
                    switchToMainFrame();
                    if(testSetValue.getValidate().equalsIgnoreCase("yes")) {
                        onlyapplyandvalidate(true);
                    }

                }

                if(testSetValue.getApply() !=null){
                    switchToDefaultContext();
                    switchToMainFrame();
                    if(testSetValue.getApply().toLowerCase().contentEquals("yes")) {
                        applyResultOnly();
                    }
                }


            }


        }
        switchToDefaultContext();
    }

    public void reflexLabEpisode(String reflexYesNo){
        if(reflexYesNo != null){
            if(reflexYesNo.equalsIgnoreCase("yes")){
                interactEspiodeNumber();
            }

        }
    }



    public void testSetOption()  {

        click(testSetOptionButtonDropDown, timeout);
        stepInfoWithScreenshot("Able to view specimen container ");

    }
    public void LabResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now().minusDays(5);
            sendKeys(labEpisode,labespide,true,true,timeout);
            sendKeys(reportCollectionDate, dtf.format(todaydate));
            click(findButton,timeout);
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
        switchToMainFrame();
        int x =0;

        for(WebElement element:find(searchTestresultEntry ,5)){
            if(element.isEnabled() && element.isDisplayed()) {
                element.click();
                labresultTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",testresult[x]));
                click(labresultTextfield);
                Thread.sleep(5000);
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
        if(validateElement_Enabled_Displayed(authoriseButton,timeout)) {
            stepInfoWithScreenshot("Able to view Results Entry");
            awaitElement(authoriseButton,timeout);
            click(authoriseButton);
            if(validateElement_Displayed(notfication,timeout)){
                stepPassedWithScreenshot("Successfully received  "+getText(notfication));
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


    public Boolean checkvaluesTestResults(HashMap<String,String> testlistResult,String testSet){
        boolean results =false;
        click(By.xpath("//span[text()='%s']".replace("%s", testSet)));
        stepPassedWithScreenshot("Successfully clicked "+testSet);
        switchToFrame(resultEntryiFrame);
        for(String testResult:testlistResult.keySet()){
          By testSetElement = By.xpath("//td[preceding-sibling::td[contains(.,'"+testResult+"')]]//input[contains(@id,'LBTSIValue') and(not(contains(@type,'hidden')))]");
         String value = findOne(testSetElement).getAttribute("value") ;
          if(value.isBlank()||value.isEmpty() || Math.abs(Double.parseDouble(value)) !=Math.abs(Double.valueOf(testlistResult.get(testResult)))){
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
                     if(find(requiredfieldblackbox,timeout).size() == count){
                         while(find(requiredfieldblackbox,timeout).size() == count){
                             find(inputTestresults,timeout).get(counter).sendKeys(testresult);
                             find(inputTestresults,timeout).get(counter).sendKeys(Keys.TAB);
                             Thread.sleep(5000);
                         }
                     }
                     count--;
                 }
                 counter++;
             }

             switchToDefaultContext();
             switchToFrame(resultEntryiFrame);
             click(applyTestResult,timeout);
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
        if(validateElement_Displayed(notfication,10)){
        stepPassedWithScreenshot("Successfully received  "+getText(notfication));
        }

    }

  public void interactEspiodeNumber(){
        click(espiodeNumberLink);
    }
    public void  onlyapplyandvalidate(boolean onlyValidate){

      if(!onlyValidate) {
          if (validateElement_Displayed(applyTestResult)) {
              Assert.assertTrue(validateElement_Displayed(applyTestResult));
              click(applyTestResult, 10);

              if (validateElement_Displayed(notfication, 10)) {
                  stepPassedWithScreenshot("Successfully received " + getText(notfication));

              } else {
                  Assert.fail("Unable to receive notification results for Test set");
              }
          }
      }
        awaitElement(validate,timeout);
        if(validateElement_Displayed(validate,timeout)) {
            stepInfoWithScreenshot("Able to view Results Entry");
            click(validate);
            try {
                acceptAlert();
            } catch (NoAlertPresentException ignored) {
            }
            stepPassedWithScreenshot("Successfully received "+getText(notfication,timeout));
            stepPassedWithScreenshot("Successfully clicked validate button");
        }else{
            Assert.fail("Unable to receive notification to validate");
        }
    }

    public void reportPreview() throws InterruptedException {
        Assert.assertTrue(validateElement_Displayed(applyTestResult,timeout));
        click(applyTestResult,timeout);
        if(validateElement_Displayed(notfication,timeout)){
            stepPassedWithScreenshot("Successfully received  "+getText(notfication,timeout));
        }else{
            Assert.fail("Unable to receive notification results for Test set");
        }
        awaitElement(validate,timeout);
        if(validateElement_Displayed(validate,timeout)){
            click(validate,timeout);
            stepPassedWithScreenshot("Successfully clicked validate button");
            awaitElement(reportpreview,timeout);
            if(validateElement_Enabled_Displayed(reportpreview,timeout)) {
                click(reportpreview, timeout);
                Thread.sleep(5000);
            }else{
                Assert.fail("Unable to click report preview");
            }


            if(waitForDisplayed()){
                loadingBarChecker();
                Thread.sleep(10000);
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
        click(backtotestSetList,timeout);
        click(backtotestSetList,timeout);
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
            if(getText(testsetlistTitle,15).contains(desc)){
                stepPassedWithScreenshot("Successfully Recieved Test Set List Title "+desc);
                return true;
            }

        }
        return false;
    }





    public int fileChecker(String dir, String testname){
        File file = new File(dir);
        int x = 0;

     long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(timeout, TimeUnit.SECONDS);
        while(System.nanoTime() < endTime) {
                for (File files : Objects.requireNonNull(file.listFiles())) {
                    if (files.getName().contains(".pdf") && files.getName().contains("document")) {
                        x++;
                        files.renameTo(new File(dir+testname+x+".pdf"));
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

        WebDriverWait wait = new WebDriverWait(super._driver,35L);
        return wait.pollingEvery(Duration.ofSeconds(10L)).withMessage("Waiting for 2nd Window")
                .until(EventFiringWebDriver::new)
                .getWindowHandles().size() == 2;

    }

   public String urlString(){
        WebElement pdfviewer = findOne(By.xpath("//pdf-viewer[@id='viewer']"));
       JavascriptExecutor jExecutor = (JavascriptExecutor) this._driver;
       WebElement shadowRoot = (WebElement)(jExecutor.executeScript("return arguments[0].shadowRoot", pdfviewer));
      return shadowRoot.findElement(By.xpath("//embed[@original-url]")).getAttribute("original-url");
    }

    public String switchToWindowHandleFirst(Set<String> windows, boolean firstorsecond){
        int counter =0;
        Iterator<String> var = windows.iterator();
        while(var.hasNext()){
          String value = var.next();
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
