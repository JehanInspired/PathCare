package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.AbstractPage;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class PathCareProcessingPage extends AbstractExtension {

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
    private final By updatePhoneEvent = By.xpath("//input[@id='update1']");
    private final By testSetCommentsTextbox = By.xpath("//body[@id='CKEditorContentLBTSComments']");
    private final By testSetComments = By.xpath("//a[@id='LBTSCommentsLink']");

    private final By acceptbuttonsWidgt = By.xpath("//input[@id='accept1']");

    private final By testCommentswindowiframe = By.xpath("//iframe[@title]");
    private final By pathogentextbox = By.xpath("//input[contains(@id,'DRz1') and (not(contains(@type,'hidden')))]");

    private final By pendingbutton = By.xpath("//input[@id='pending1']");

    private final By titlespecimenNumber = By.xpath("LBPTMMaterialNumber");
    private final By orgramismText = By.xpath("//input[@id='LBTSI_Valuez1']");
    private final By div = By.xpath("//div");

    private final By organismText = By.xpath("//div[contains(@id,'LBTSIValue')]/p");
    private By organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");
    private By LookupTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");

    private By receipientTypeTextfield = By.xpath("//input[@name='LBEPERecipientID']");
    private By manglass = By.xpath("//img[contains(@id,'lt8560iLBTSI_Valuez1')]");
    private By antibioticslink = By.xpath("//a[@id='LBTSI_AntibioticPanelLinkz1']");

    private By listAntibiotic = By.xpath("//label[contains(@id,'LBTSIANT_Antibiotic_DRz')]");

    private By listResult = By.xpath("//input[contains(@id,'LBTSIANT_Result_DRz')]");

    private By redfronts = By.xpath("//font[@color='RED']");

    private By etestinputfield = By.xpath("//input[contains(@id,'LBTSIANT_Result_ETestz6')]");
    private By closelookup = By.xpath("//span[@id='OverlayCloseLookupOverlayDiv']");
    private By antibioticsAcceptButton = By.xpath("//input[@id='Accept']");

    private By validateButton = By.xpath("//input[@id='validate1']");

    private By organimMaginitglass = By.xpath("//img[@id='lt8560iLBTSI_PathogenGrowthQualifier_DRz1']");

    private By requestLink = By.xpath("//a[text()='equest']");

    private By listTestSet = By.xpath("//a[contains(@id,'TabTestSet')]");

    private By phoneEventlink = By.xpath("//a[contains(@id,'PhoneEventLink')]");

    private By titlePhoneEvent = By.xpath("//h1[text()='Phone Event']");
    private By reportingRecipientLookUp = By.xpath("//img[contains(@name,'LBEpisodeRep')]");
    private By phonelist = By.xpath("//select[@name='PhonesList']");
    private By bussinesoption = By.xpath("//option[@value='B']");


    private By phonenumber = By.xpath("//input[@name='LBEPEPhoneNumber']");

    private By textAreaPhoneComments = By.xpath("//textarea[@name='LBEPEComments']");
    private By selectallPhone = By.xpath("//input[@name='SelectAll']");

    private By phonetickbox = By.xpath("//input[contains(@id,'SelectItem')]");

    private By antibioticsPanel = By.xpath("//img[contains(@id,'AntibioticPanel')]");
    private By addAntibioticsPanel = By.xpath("//input[contains(@id,'add') and (not(contains(@type,'hidden')))]");
    private By antibioticsPanelTextbox = By.xpath("//input[contains(@id,'AntibioticPanel') and (not(contains(@type,'hidden'))) ]");
    private By statuscolourProtocolOverview = By.xpath("//span[(@class='clsColourTab clsLBCProtocolAdditional') and not(@disabled)]");

    private final By loadingBar = By.xpath("//div[@class='bar']");

    private final By bodydom = By.xpath("//body[@ng-app='tcApp']");

    private By tableListSearch = By.xpath("//table[@id='tblLookup']");
    private By questionarieButton = By.xpath("//a[@id='TC.LBTSOrderQuestionMissingMandatory']");
    private By testSetProtocolButton = By.xpath("//a[@id='TC.LBTSProtocolsCompleted']");
    private By questionProtocolButton = By.xpath("//a[@id='TC.LBPTPQuestionsEntered']");
    private By iframeInfroCloseButton = By.xpath("//span[@id='InfoPaneClose']");
    private By slideMaterialText = By.xpath("//label[@id='MaterialStatusCountz1.1']");
    private By testSetOptionButtonDropDown = By.xpath("//a[text()='Test Set Options']");
    private By oneSpecimenContainer = By.xpath("//a[@id='LBProtocolz1']");

    private By MultipleSpecimenContainer = By.xpath("//a[contains(@id,'LBProtocolz')]");
    private By secondQuestion = By.xpath("//input[@id='QAz2']");

    private By episodeEventlink = By.xpath("//a[@id='EpisodeEventLink']");
    private By addProtocol = By.xpath("//a[@id='Addz1']");
    private By collectionAddProtocol = By.xpath("//a[contains(@id,'Addz')]");

    private By lookupProtocolMaterial = By.xpath("//img[@id='ld8628iLBPTMMaterialDR']");

    private By protocolObservation = By.xpath("//input[@id='LBPTPOObservationDR']");
    private By backtopreviouspage = By.xpath("//a[@ng-click='navBack();']");
    public String speciemenR = "";
    public String dir = "";
    public int counter = 0;
    public int timeout = 20;

    public int getNum() {
        return num;
    }

    public void setNumSpecimen(int num) {
        this.num = num;
    }

    public int num = 1;

    public int numberfiles = 1;


    public void backtopreviouspage() {
        switchToDefaultContext();
        click(backtopreviouspage);
        _driver.navigate().refresh();
    }

    public void clickRequestLink() throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        Thread.sleep(10000);
        if (validateElement_Enabled_Displayed(requestLink, timeout)) {
            click(requestLink, timeout);
            stepInfoWithScreenshot("Able to click request Link");
        }
    }

    public void backbuttonbrowser() {
        _driver.navigate().back();
    }

    public void clickEpisodeEventLink() throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        if (validateElement_Enabled_Displayed(episodeEventlink)) {
            click(episodeEventlink, timeout);
            Thread.sleep(3000);
            stepInfoWithScreenshot("Able to click Episode Events Link");
        } else {
            Assert.fail("Unable to view Episode Events Link");
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        click(iframeInfroCloseButton, 15);
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
    }

    public void clickUpdateButton() {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        if (validateElement_Enabled_Displayed(questionarieButton, 15)) {
            click(updateTestResult);
            stepInfoWithScreenshot("Clicked Update Button");
        }
    }

    public void testSpeciemenQuestionaire(String value, boolean entervalue) throws InterruptedException {
        Thread.sleep(2000);
        if (validateElement_Enabled_Displayed(questionarieButton, 15)) {
            click(questionarieButton);
            stepInfoWithScreenshot("Able to click Test Set Order Questions");
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if (validateElement_Enabled_Displayed(secondQuestion)) {
            if (entervalue) {
                enterquestionvalue(value);
            } else {
                checkquestionvalue(value);
            }
        } else {
            click(iframeInfroCloseButton, 15);
            switchToDefaultContext();
            switchToFrame(iframeProcessing);
            click(questionarieButton);
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            if (entervalue) {
                enterquestionvalue(value);
            } else {
                checkquestionvalue(value);
            }
        }

        click(iframeInfroCloseButton, 15);
        switchToDefaultContext();
        switchToFrame(iframeProcessing);

    }

    private void enterquestionvalue(String value) {
        sendKeys(secondQuestion, value, 15);
        if (!getAttribute(secondQuestion, "value", 10).isBlank()) {
            stepPassedWithScreenshot("Entered value " + value);
        }
    }

    private void checkquestionvalue(String value) {
        if (getAttribute(secondQuestion, "value", 10).equals(value)) {
            stepPassedWithScreenshot("Able to view " + value);
        }
    }

    public void proctocolProcdueQuestion() throws InterruptedException {
        Thread.sleep(4000);
        if (validateElement_Enabled_Displayed(questionProtocolButton, 15)) {
            click(questionProtocolButton);
            stepInfoWithScreenshot("Able to click Test Set Protocol");
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        stepPassedWithScreenshot("Able to view Procedure Question ");

        click(iframeInfroCloseButton);
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
    }

    public void testSetProctocol() throws InterruptedException {
        Thread.sleep(4000);
        if (validateElement_Enabled_Displayed(testSetProtocolButton, 15)) {
            click(testSetProtocolButton);
            stepInfoWithScreenshot("Able to click Test Set Protocol");
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if (!getText(slideMaterialText, 15).isBlank()) {
            stepPassedWithScreenshot("Able to view Number of slide ");
        }
        click(iframeInfroCloseButton);
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
    }

    public void testSetOption() throws InterruptedException {

        click(testSetOptionButtonDropDown, 10);
        for (int x = 0; x < find(MultipleSpecimenContainer).size(); x++) {
            if (find(MultipleSpecimenContainer).get(x).isDisplayed()) {
                Thread.sleep(3000);
                find(MultipleSpecimenContainer).get(x).click();
                Thread.sleep(4000);
                stepPassedWithScreenshot("Able to view Protocol Overview " + x);
                closeInfroCloseButton();
            }
        }


    }

    public void testSetOptionChanger() {
        click(testSetOptionButtonDropDown, 10);
        if (validateElement_Displayed(oneSpecimenContainer)) {
            click(oneSpecimenContainer);
            stepPassedWithScreenshot("Able to view Protocol Overview ");
        }
    }

    public void protocolMaterialWidgt(String nameMaterial, boolean observation, String observationValue, boolean testOptions, String value) {
        if (!observation) {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            click(addProtocol);
            click(lookupProtocolMaterial);
            if (!(validateElement_Displayed(By.xpath("//td[text()='%s']".replace("%s", nameMaterial))))) {
                click(lookupProtocolMaterial);
                click(By.xpath("//td[text()='%s']".replace("%s", nameMaterial)));
            } else {
                click(By.xpath("//td[text()='%s']".replace("%s", nameMaterial)));
            }

        } else if (validateElement_Displayed(statuscolourProtocolOverview)) {
            find(collectionAddProtocol).get(find(collectionAddProtocol).size() - 1).click();
            sendKeys(protocolObservation, observationValue);
            stepPassedWithScreenshot("Entered " + observationValue);
        } else {
            Assert.fail("Unable to find status green on Protocol ");
        }


        if (validateElement_Enabled_Displayed(acceptbuttonsWidgt, 10)) {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            click(acceptbuttonsWidgt);
        }

        if (testOptions) {

            if (validateElement_Enabled_Displayed(By.xpath("//a[(text()='" + value + "') and not(@disabled)]"), 10)) {
                click(By.xpath("//a[(text()='" + value + "') and not(@disabled)]"));
            }
            if (validateElement_Enabled_Displayed(By.xpath("//input[@id='cancel1' and not(@onclick)]"), 10)) {
                click(By.xpath("//input[@id='cancel1' and not(@onclick)]"));
            }
            //Update
            if (validateElement_Displayed(By.xpath("//*[@id='update1' and not(@class='clsButtonDefault clsButton')]"), 10)) {
                click(By.xpath("//*[@id='update1' and not(@class='clsButtonDefault clsButton')]"));
            } else {
                Assert.fail("Unable to update the Protocol Results");
            }

            switchToDefaultContext();
            switchToFrame(iframeProcessing);

        }
    }


    private void closeInfroCloseButton() {
        try {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            click(iframeInfroCloseButton);
            switchToDefaultContext();
            switchToFrame(iframeProcessing);
        } catch (NoSuchElementException ex) {
            switchToDefaultContext();
            switchToFrame(iframeProcessing);
            click(testSetProtocolButton);
            closeInfroCloseButton();
        }
    }

    public void lookupSinglewithoutOrgnimfield(String speciemenReceive, String[] testresults, boolean validate, boolean apply) throws InterruptedException {
        int x = 0;
        searchSpecimenReceive(speciemenReceive);
        Thread.sleep(4000);
        for (WebElement element : find(organismText)) {
            element.clear();
            while (!element.getText().isBlank() || !element.getText().isEmpty()) {
                element.clear();
            }
        }

        for (WebElement element : find(pinserttestreults)) {
            element.click();
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", testresults[x++]));
            if (x == 3) {
                click(closelookup);
                break;
            }
            Thread.sleep(4000);
            if (!validateElement_Enabled_Displayed(organismTextfield)) {
                element.click();
                click(organismTextfield);
            } else {
                click(organismTextfield);
            }
            click(div);
        }

        if (validate) {
            click(validateButton);
        }

        if (apply) {
            click(applyTestResult);
        }
    }


    public void lookupSingle(String speciemenReceive, String[] testresults) throws InterruptedException {
        int x = 0;
        searchSpecimenReceive(speciemenReceive);

        for (WebElement element : find(pinserttestreults)) {
            click(pathogentextbox);
            element.click();
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", testresults[x++]));
            if (x == 3) {
                click(closelookup);
                break;
            }
            if (!validateElement_Enabled_Displayed(organismTextfield, 10)) {
                element.click();
                click(organismTextfield);
            } else {
                click(organismTextfield);
            }
            click(div);
        }

        stepPassedWithScreenshot("Successfully Entered Results " + Arrays.toString(testresults));
        Thread.sleep(5000);
        click(applyTestResult);
    }

    public void searchSpecimenReceive(String specimenReceive) {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        findOne(inputScantextfield, specimenReceive);
    }

    public void antibioticsPanel(String value) throws InterruptedException {

        if (validateElement_Enabled_Displayed(antibioticslink)) {
            click(antibioticslink);
        } else {
            Thread.sleep(4000);
            click(antibioticslink);
        }

        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if (validateElement_Enabled_Displayed(antibioticsPanel, 10)) {
            click(antibioticsPanel);
        } else {
            Thread.sleep(4000);
            try {
                acceptAlert();
                click(antibioticsPanel);
            } catch (NoAlertPresentException ignore) {
                if (validateElement_Enabled_Displayed(antibioticsPanel, 10)) {
                    click(antibioticsPanel);
                }
            }
        }

        By antibioticsList = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", value));
        if (validateElement_Enabled_Displayed(tableListSearch)) {
            click(antibioticsList);
            click(addAntibioticsPanel);
            stepInfoWithScreenshot("Added " + value + " on Antibiotic list");
        }


            /*try {

                     acceptAlert();
                }
                }catch (NoAlertPresentException ignore){

                }*/
        stepInfo("Entered Antibiotic Panel " + value);
    }

    public void antibiotics(String[][] antibios, String mic) throws InterruptedException {
        int x = 0;
        Thread.sleep(5000);
        Boolean resultfound = false;

        Thread.sleep(4000);
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        for (WebElement element : find(listAntibiotic)) {
            for (String[] antibio : antibios) {
                for (String value : antibio)
                    if (element.getText().contains(value)) {
                        resultfound = true;
                    } else if (resultfound) {
                        find(listResult).get(x).sendKeys(value);
                        click(closelookup);
                        resultfound = false;
                    }

            }
            x++;
        }

        if (!mic.isBlank()) {
            sendKeys(etestinputfield, mic, 10);
        }

        stepPassedWithScreenshot("successfully entered Antibiotics " + Arrays.stream(antibios).toArray().toString());

        click(antibioticsAcceptButton);

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
    }

    public void specimenNumPending(String specimenReceive, String observation, Boolean addnotes, String notesvalue, Boolean addLinkedTest, String linkedTest) {

        specimenNumber = speciemenPendingValue(specimenReceive, num);
        click(specimenNumber, 10);
        stepPassedWithScreenshot("Successfully selected  " + specimenReceive.concat("." + num));
        if (!observation.isBlank()) {
            click(addObervation, 10);
        }

        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if (!observation.isBlank()) {
            findOne(obervationSelection, observation);
        }

        if (addnotes) {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            switchToFrame(iframeInfoObservationNote);
            if (validateElement_Displayed(observationCommentsTextbox, 10)) {
                _driver.findElement(observationCommentsTextbox).sendKeys(notesvalue);
                stepPassedWithScreenshot("Successfully added notes Observation " + notesvalue);
            }
        }

        if (addLinkedTest) {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            findOne(linkedTestSetItem, linkedTest);
        }
        if (!observation.isBlank()) {
            switchToDefaultContext();
            switchToFrame(iframeInfoObservation);
            stepPassedWithScreenshot("Successfully entered Observation " + observation);

            click(updateObservation);
        }


    }

    private By speciemenPendingValue(String value, int num) {
        if (num == 1) {
            return By.xpath("//strong[contains(text(),'%s')]".replace("%s", value.concat("." + num)));
        } else {
            return By.xpath("//a[contains(text(),'Complete All')]");
        }
    }

    public void specimenCompleteWithoutApplyorUpdate() {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(completeButton);
    }


    public void phonequeue() throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        if (validateElement_Enabled_Displayed(redfronts)) {
            stepPassedWithScreenshot("Able to view warning ");
        } else {
            stepInfoWithScreenshot("No warning message");
            Assert.fail("Unable to view the warning message");
        }
        counter = find(listTestSet).size();
        for (WebElement element : find(listTestSet)) {
            if (element.isEnabled()) {
                element.click();
                phoneEvent();
            }
        }
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        //checking phone queues
        for (WebElement element : find(listTestSet)) {
            if (element.isEnabled()) {
                element.click();
                phoneEvent();
            }
        }

    }

    public void phoneEvent() throws InterruptedException {
        Thread.sleep(4000);
        click(phoneEventlink, 5);
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        if (validateElement_Displayed(titlePhoneEvent)) {
            stepInfoWithScreenshot("Able to view");
            if (validateElement_Displayed(phonetickbox)) {
                click(reportingRecipientLookUp);
                LookupTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", "Referring Doctor"));
                click(LookupTextfield);

                stepPassedWithScreenshot("Select Referring Doctor");
                click(bussinesoption);
                stepPassedWithScreenshot("Able to view phone number");
                sendKeys(textAreaPhoneComments, "phoned to doctor");
                stepPassedWithScreenshot("Entered phone doctor");
                click(selectallPhone);
                click(updatePhoneEvent);
            } else {
                stepInfoWithScreenshot("Successfully removed test set in phone queue");
            }

        } else {
            stepInfoWithScreenshot("No warning Phone event widget");
            Assert.fail("Unable to view the Phone Event");
        }


    }


    public void SingleProcessingTestSet(String organism, String organisms, boolean addcomments, String comments, String[] testresults, boolean apply, boolean update) throws InterruptedException {
        int x = 0;
        Thread.sleep(3000);
        switchToDefaultContext();
        switchToFrame(iframeProcessing);

        if (testresults.length != 0) {

            for (WebElement element : find(organismText, 10)) {
                element.clear();
            }

            for (WebElement element : find(pinserttestreults)) {
                click(pathogentextbox);
                element.click();
                Thread.sleep(4000);
                organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", testresults[x++]));
                if (x == 3) {
                    click(closelookup);
                    break;
                }
                while (!validateElement_Enabled_Displayed(organismTextfield, 10)) {
                    _driver.findElement(pathogentextbox).sendKeys(Keys.TAB);
                    element.click();
                }
                Thread.sleep(3000);
                click(organismTextfield);
            }

            if (!organisms.isEmpty()) {
                if (validateElement_Displayed(closelookup)) {
                    click(closelookup);
                }
                if (validateElement_Displayed(manglass)) {
                    sendKeys(orgramismText, organisms);
                    if (validateElement_Displayed(closelookup)) {
                        click(closelookup);
                        // click(manglass);
                    }
//                Thread.sleep(4000);
//                organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organisms));
//                click(organismTextfield);
                    click(applyTestResult);
                    Thread.sleep(3000);
                }

            }
        }

        if (!organism.isEmpty()) {
            Thread.sleep(3000);
            click(pathogentextbox);
            click(organimMaginitglass, 10);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organism));
            click(organismTextfield, 10);
            Thread.sleep(3000);
            click(applyTestResult);

        }

        stepPassedWithScreenshot("Successfully Entered Results " + organism + " " + organisms);

        //Testing Comment
        if (addcomments) {
            testcomment(comments);
        } else if (addcomments && comments.isEmpty()) {
            testcomment("");
        }

        Thread.sleep(3000);

        if (validateElement_Displayed(applyTestResult, 10) && apply) {
            click(applyTestResult, 10);
            stepPassedWithScreenshot("Successfully updated Test Result");
        }

        if (validateElement_Displayed(updateTestResult, 10) && update) {
            click(updateTestResult, 10);
            stepPassedWithScreenshot("Successfully updated Test Result");
        }

    }

    public void SingleProcessingTestSetWithReport(String organism, String organisms, String comments, String[] testresults) throws InterruptedException {
        int x = 0;
        validateElement_Displayed(notficationApply, 10);
        for (WebElement element : find(organismText)) {
            element.clear();
        }

        if (testresults.length != 0) {

            for (WebElement element : find(pinserttestreults)) {
                click(pathogentextbox);
                element.click();
                Thread.sleep(4000);
                organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", testresults[x++]));
                if (x == 3) {
                    click(closelookup);
                    break;
                }
                while (!validateElement_Enabled_Displayed(organismTextfield, 10)) {
                    _driver.findElement(pathogentextbox).sendKeys(Keys.TAB);
                    element.click();
                }
                Thread.sleep(3000);
                click(organismTextfield);
            }

            if (!organisms.isEmpty()) {
                if (validateElement_Displayed(closelookup)) {
                    click(closelookup);
                }
                if (validateElement_Displayed(manglass)) {
                    sendKeys(orgramismText, organisms);
                    if (validateElement_Displayed(closelookup)) {
                        click(closelookup);
                        // click(manglass);
                    }
//                Thread.sleep(4000);
//                organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organisms));
//                click(organismTextfield);
                    click(applyTestResult);
                    Thread.sleep(3000);
                }

            }
        }

        stepInfo("Successfully Entered Results " + organism + " " + organisms);

        //Testing Comment
        if (!comments.isEmpty()) {
            testcomment(comments);
        } else {
            testcomment("");
        }

        loadingBarChecker();
        reportPreview();
        stepPassedWithScreenshot("Successfully updated Test Result");
    }


    public void testcomment(String comments) {

        click(testSetComments, 10);
        stepPassedWithScreenshot("Successfully reached Test set Comment");
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        switchToFrame(testCommentswindowiframe);
        if (validateElement_Displayed(testSetCommentsTextbox, 10)) {
            if (comments.isEmpty()) {
                _driver.findElement(testSetCommentsTextbox).clear();
            } else {
                _driver.findElement(testSetCommentsTextbox).sendKeys(comments);
            }
        }
        switchToDefaultContext();
        switchToFrame(iframeInfoObservation);
        click(acceptbuttonsWidgt);

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        scrollToElement(testSetComments);
        stepPassedWithScreenshot("Successfully Entered Comment");

    }


    public void specimenComplete(String specimenReceive) {

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(completeButton);

        completequery = By.xpath("//strong[contains(text(),'%s')]".replace("%s", specimenReceive.concat(".1")));

        if (validateElement_Displayed(completequery)) {
            stepPassedWithScreenshot("Successfully Completed " + specimenReceive.concat(".1"));
        }

        click(applyTestResult);

        if (validateElement_Displayed(notficationApply, 10)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply));
        }

        click(updateTestResult);

        if (validateElement_Displayed(notficationApply, 10)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply));
        }

        speciemenR = specimenReceive.concat(".1");

    }

    public void specimenComplete(String specimenReceive, String number) {

        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(completeButton);

        completequery = By.xpath("//strong[contains(text(),'%s')]".replace("%s", specimenReceive.concat("." + number)));

        if (validateElement_Displayed(completequery,timeout)) {
            stepPassedWithScreenshot("Successfully Completed " + specimenReceive.concat("." + number));
        }

        click(applyTestResult);

        if (validateElement_Displayed(notficationApply, timeout)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply,timeout));
        }

        click(updateTestResult);

        if (validateElement_Displayed(notficationApply, timeout)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply,timeout));
        }

        speciemenR = specimenReceive.concat(".1");

    }

    public void receiveReport() throws InterruptedException {
        awaitElement(reportpreview,timeout);
        if (validateElement_Enabled_Displayed(reportpreview, timeout)) {
            click(reportpreview, timeout);
            Thread.sleep(5000);
        } else {
            Assert.fail("Unable to click report preview");
        }

        if (waitForDisplayed()) {
            Set<String> currentWindow = super._driver.getWindowHandles();
            _driver.switchTo().window(switchToWindowHandleFirst(currentWindow, true));
            fileChecker(dir, _testName + "-");
        }

    }

    public void applyandvalidateNoReport() {
        Assert.assertTrue(validateElement_Displayed(applyTestResult,timeout));
        click(applyTestResult, timeout);
        if (validateElement_Displayed(notficationApply,timeout)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply,timeout));
        } else {
            Assert.fail("Unable to receive notification results for Test set");
        }

        if (validateElement_Displayed(validateButton,timeout)) {
            click(validateButton,timeout);
            stepPassedWithScreenshot("Successfully clicked validate button");

        }
    }

    private void reportPreview() throws InterruptedException {
        Assert.assertTrue(validateElement_Displayed(applyTestResult));
        click(applyTestResult, timeout);
        if (validateElement_Displayed(notficationApply, timeout)) {
            stepPassedWithScreenshot("Successfully received  " + getText(notficationApply,timeout));
        } else {
            Assert.fail("Unable to receive notification results for Test set");
        }

        if (validateElement_Displayed(validateButton,timeout)) {
            click(validateButton,timeout);
            stepPassedWithScreenshot("Successfully clicked validate button");
            if (validateElement_Enabled_Displayed(reportpreview, timeout)) {
                click(reportpreview, timeout);
                Thread.sleep(5000);
            } else {
                Assert.fail("Unable to click report preview");
            }

            if (waitForDisplayed()) {
                Set<String> currentWindow = super._driver.getWindowHandles();
                _driver.switchTo().window(switchToWindowHandleFirst(currentWindow, true));
                fileChecker(this.dir, _testName + "-");

            }

        }
    }

    public String switchToWindowHandleFirst(Set<String> windows, boolean firstorsecond) {
        int counter = 0;
        Iterator var = windows.iterator();
        while (var.hasNext()) {
            String value = (String) var.next();
            if (firstorsecond) {
                return value;
            } else if (counter == 1) {
                return value;
            }
            counter++;
        }
        return null;

    }


    public void inprogress() {
        switchToDefaultContext();
        switchToFrame(iframeProcessing);
        click(pendingbutton,timeout);
        stepPassedWithScreenshot("Successfully clicked pending button");
       /* completequery = By.xpath("//strong[contains(text(),'%s')]".replace("%s",speciemenR.concat(".1")));
        click(completequery);*/

    }

    public int fileChecker(String dir, String testname) {
        File file = new File(dir);
        int x = 0;

        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15, TimeUnit.SECONDS);
        while (System.nanoTime() < endTime) {
            for (File files : Objects.requireNonNull(file.listFiles())) {
                if (files.getName().contains(".pdf") && files.getName().contains("document")) {
                    x++;
                    files.renameTo(new File(dir + testname + x + ".pdf"));
                    stepInfo("Checking pdf reporting files");
                } else if (x == numberfiles) {
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
        stepInfoWithScreenshot("Entered value " + input);
        super.findOne(by).sendKeys(Keys.TAB);
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

        WebDriverWait wait = new WebDriverWait(super._driver, 30L);
        wait.pollingEvery(Duration.ofSeconds(10L));
        return wait.until(EventFiringWebDriver::new).getWindowHandles().size() == 2 && fileChecker(this.dir, _testName + "_") == this.numberfiles;
    }
}
