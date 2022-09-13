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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PathCareProcessingPage extends AbstractPage {

    private final By iframeProcessing = By.xpath("//iframe[@id='TRAK_main']");

    private final By iframeInfoObservation = By.xpath("//iframe[@id='TRAK_info']");

    private final By reportpreview = By.xpath("//a[@id='ReportPreview']");
    private final By iframeInfoObservationNote = By.xpath("//iframe[contains(@title,'Rich Text Editor, LBPTONote')]");

    private final By observationCommentsTextbox = By.xpath("//body[contains(@id,'CKEditorContentLBPTONote')]");

    private final By completeButton = By.xpath("//input[contains(@id,'complete1')]");
    private final By inputScantextfield = By.xpath("//input[@id='ScanField']");
    private By specimenNumber = By.xpath("//strong[contains(text(),'%s')]");
    private final By linkedTestSetItem = By.xpath("//input[@id='LBPTOTestSetItemDR']");
    private final By notficationApply = By.xpath("//div[@class='notification_message']");
    private By completequery = By.xpath("//div[contains(@data-displayvalue,'%s')]");

    private final By addObervation = By.xpath("//a[contains(@id,'AddObservation')]");
    private final By obervationSelection = By.xpath("//input[contains(@id,'LBPTOObservationDR')]");

    private final By pinserttestreults = By.xpath("//img[contains(@id,'Value')]");
    private final By applyTestResult = By.xpath("//input[@id='apply1']");
    private final By updateTestResult = By.xpath("//input[@id='update1']");
    private final By updateObservation = By.xpath("//input[@id='update1']");
    private final By testSetCommentsTextbox = By.xpath("//body[@id='CKEditorContentLBTSComments']");
    private final  By testSetComments = By.xpath("//a[@id='LBTSCommentsLink']");

    private final By acceptbuttonComment = By.xpath("//input[@id='accept1']");

    private final By testCommentswindowiframe = By.xpath("//iframe[@title]");

    private final By pendingbutton = By.xpath("//input[@id='pending1']");

    private final By titlespecimenNumber = By.xpath("LBPTMMaterialNumber");
    private final By orgramismText = By.xpath("//input[@id='LBTSI_Valuez1']");
    private  By div = By.xpath("//div");

    private By organismText = By.xpath("//div[contains(@id,'LBTSIValue')]/p");
    private By organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");
    private By manglass = By.xpath("//img[contains(@id,'lt8560iLBTSI_PathogenGrowthQualifier_DRz1')]");
    private By antibioticslink = By.xpath("//a[@id='LBTSI_AntibioticPanelLinkz1']");

    private By listAntibiotic = By.xpath("//label[contains(@id,'LBTSIANT_Antibiotic_DRz')]");

    private By listResult = By.xpath("//input[contains(@id,'LBTSIANT_Result_DRz')]");

    private By etestinputfield = By.xpath("//input[contains(@id,'LBTSIANT_Result_ETestz6')]");
    private By closelookup = By.xpath("//span[@id='OverlayCloseLookupOverlayDiv']");
    private By antibioticsAcceptButton = By.xpath("//input[@id='Accept']");

    private By validateButton = By.xpath("//input[@id='validate1']");
    public String speciemenR = "";
    public String dir ="";

    public int numberfiles =1;


    public void lookupSingle(String speciemenReceive,String[] testresults) throws InterruptedException {
       int x =0;
        searchSpecimenReceive(speciemenReceive);

        for(WebElement element:find(pinserttestreults)){
            element.click();
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",testresults[x++]));
            if(x==3) {
                click(closelookup);
                break;
            }
            if(!validateElement_Enabled_Displayed(organismTextfield, 10)){
                element.click();
                click(organismTextfield);
            }else{
                click(organismTextfield);
            }
            click(div);
        }

        stepPassedWithScreenshot("Successfully Entered Results " + Arrays.toString(testresults));
        Thread.sleep(5000);
        click(applyTestResult);
    }

    public void searchSpecimenReceive(String specimenReceive){
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        findOne(inputScantextfield,specimenReceive);
    }

    public void antibiotics(String[][] antibios,String mic){
        int x= 0;
        Boolean resultfound = false;
        if(validateElement_Displayed(antibioticslink)){
            click(antibioticslink);
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        for(WebElement element:find(listAntibiotic)){
            for(String[] antibio:antibios){
                for(String value:antibio)
             if(element.getText().contains(value)){
               resultfound =true;
             }else if(resultfound){
                 find(listResult).get(x).sendKeys(value);
                 click(closelookup);
                 resultfound = false;
             }

            }
            x++;
        }
        sendKeys(etestinputfield,mic,10);
        stepPassedWithScreenshot("successfully entered Antibiotics "+Arrays.toString(antibios));

        click(antibioticsAcceptButton);

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
    }

    public void specimenNumPending(String specimenReceive,String observation,Boolean addnotes,String notesvalue,Boolean addLinkedTest,String linkedTest){

        specimenNumber = By.xpath("//strong[contains(text(),'%s')]".replace("%s",specimenReceive.concat(".1")));
        click(specimenNumber,10);
        stepPassedWithScreenshot("Successfully selected  "+specimenReceive.concat(".1"));
        click(addObervation,10);

        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if(!observation.isEmpty()) {
            findOne(obervationSelection, observation);
        }

        if(addnotes){
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            switchToFrame(iframeInfoObservationNote);
            if(validateElement_Displayed(observationCommentsTextbox,10)) {
                _driver.findElement(observationCommentsTextbox).sendKeys(notesvalue);
                stepPassedWithScreenshot("Successfully added notes Observation "+notesvalue);
            }


        }
        if(addLinkedTest){
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            findOne(linkedTestSetItem,linkedTest);


        }

        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        stepPassedWithScreenshot("Successfully entered Observation "+observation);
        click(updateObservation);

    }

    public void specimenCompleteWithoutApplyorUpdate(){
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(completeButton);
    }

    public void SingleProcessingTestSet(String organism,String organisms,String comments, String[] testresults) throws InterruptedException {
      int x= 0;

        for(WebElement element:find(organismText)){
            element.clear();
        }

        for(WebElement element:find(pinserttestreults)){
            element.click();
            Thread.sleep(4000);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",testresults[x++]));
            if(x==3) {
                click(closelookup);
                break;

            }
            if(!validateElement_Enabled_Displayed(organismTextfield, 10)){
                element.click();
            }
            Thread.sleep(4000);
            click(organismTextfield);
        }

        if(!organisms.isEmpty()) {
            click(orgramismText);
            sendKeys(orgramismText, organisms);
            super.findOne(orgramismText).sendKeys(Keys.TAB);
        }

        if (!organism.isEmpty()){
            click(manglass);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organism));
            click(organismTextfield);
            Thread.sleep(3000);
            click(applyTestResult);
            Thread.sleep(3000);
        }
            
        stepPassedWithScreenshot("Successfully Entered Results " + organism +" "+organisms);
        
        //Testing Comment
        if(!comments.isEmpty()) {
            testcomment(comments);
        }else{
            testcomment("");
        }
        Thread.sleep(5000);


        click(applyTestResult);
        

        click(updateTestResult);
        stepPassedWithScreenshot("Successfully updated Test Result");
    }

    public void SingleProcessingTestSetWithReport(String organism,String organisms,String comments, String[] testresults) throws InterruptedException {
        int x= 0;
        validateElement_Displayed(notficationApply,10);
        for(WebElement element:find(organismText)){
            element.clear();
        }


        for(WebElement element:find(pinserttestreults)){
            element.click();
            Thread.sleep(4000);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s",testresults[x++]));
            if(x==3) {
                click(closelookup);
                break;

            }
            if(!validateElement_Enabled_Displayed(organismTextfield, 10)){
                click(orgramismText);
                element.click();
                Thread.sleep(4000);
            }

            if(!validateElement_Enabled_Displayed(organismTextfield, 10)){
                click(applyTestResult);
                element.click();
                Thread.sleep(4000);
            }
            click(organismTextfield);


        }

        if(!organisms.isEmpty()) {
            click(orgramismText);
            sendKeys(orgramismText, organisms);
            super.findOne(orgramismText).sendKeys(Keys.TAB);
        }

        if (!organism.isEmpty()){
            click(manglass);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organism));
            click(organismTextfield);
            Thread.sleep(3000);
            click(applyTestResult);
            Thread.sleep(3000);
        }

        stepPassedWithScreenshot("Successfully Entered Results " + organism +" "+organisms);

        //Testing Comment
        if(!comments.isEmpty()) {
            testcomment(comments);
        }else{
            testcomment("");
        }
        Thread.sleep(5000);

        reportPreview();
        stepPassedWithScreenshot("Successfully updated Test Result");
    }


    public void testcomment(String comments){

        click(testSetComments,10);
        stepPassedWithScreenshot("Successfully reached Test set Comment");
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        switchToFrame(testCommentswindowiframe);
        if(validateElement_Displayed(testSetCommentsTextbox,10)) {
            if(comments.isEmpty()){
                _driver.findElement(testSetCommentsTextbox).clear();
            }else {
                _driver.findElement(testSetCommentsTextbox).sendKeys(comments);
            }
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        click(acceptbuttonComment);

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        scrollToElement(testSetComments);
        stepPassedWithScreenshot("Successfully Entered Comment");

    }



    public void specimenComplete(String specimenReceive){

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(completeButton);

        completequery = By.xpath("//strong[contains(text(),'%s')]".replace("%s",specimenReceive.concat(".1")));

        if(validateElement_Displayed(completequery)){
            stepPassedWithScreenshot("Successfully Completed "+specimenReceive.concat(".1"));
        }

        click(applyTestResult);

        if(validateElement_Displayed(notficationApply,10)){
            stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }

        click(updateTestResult);

        if(validateElement_Displayed(notficationApply,10)){
            stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }

        speciemenR=specimenReceive.concat(".1");

    }

    private void reportPreview(){
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult,10);
        if(validateElement_Displayed(notficationApply,10)){
            stepPassedWithScreenshot("Successfully received  "+getText(notficationApply));
        }else{
            Assert.fail("Unable to receive notification results for Test set");
        }
        if(validateElement_Displayed(validateButton)){
            click(validateButton);
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



    public void inprogress(){
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(pendingbutton);
        stepPassedWithScreenshot("Successfully clicked pending button");
       /* completequery = By.xpath("//strong[contains(text(),'%s')]".replace("%s",speciemenR.concat(".1")));
        click(completequery);*/

    }

    public int fileChecker(String dir){

        File file = new File(dir);
        int x = 0;

        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15, TimeUnit.SECONDS);
        while(System.nanoTime() < endTime) {
            for (String files : Objects.requireNonNull(file.list())) {
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




    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }


    public PathCareProcessingPage(Roman roman) {
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
        return wait.until(EventFiringWebDriver::new).getWindowHandles().size() == 2 && fileChecker(this.dir) == this.numberfiles;

    }
}
