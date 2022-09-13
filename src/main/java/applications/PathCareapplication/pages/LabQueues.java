package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;

import selenium.AbstractPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LabQueues extends AbstractPage {

    private final By queueType = By.xpath("//select[@id='QueueTypeList']");
    private final By toolbox = By.xpath("//md-icon[@class='mainMenuToolsIcon']");
    private final By testmenu = By.xpath("//span[text()='Test']");

    private final By labInstrumentResultGenerator = By.xpath("//span[text()='Lab Instrument Result Generator']");

    private final By homeicon = By.xpath("//md-icon[@title='Home']");
    private final By testSetStatus = By.xpath("//select[@id='TestSetStatusList']");


    private final By switchiFrame = By.xpath("//iframe[@id='TRAK_main']");

    private final By deparmentlist = By.xpath("//select[@id='DepartmentList']");
    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By testSetLabSite = By.xpath("//input[@id='TestSetLabSite']");
    private final By findbuttonLab = By.xpath("//input[@id='find1']");
    private final By saveSearch = By.xpath("//a[@id='SaveSearch']");
    private final By SearchResults = By.xpath("//table[@id='tLBVerificationQueueGrid_List']");
    private final By descriptionText = By.xpath("//input[@id='SRCHDesc']");
    private final By descriptionTable  = By.xpath("//a[@id='SRCHDescz1']");
    private final By updateLabQueuesSearch = By.xpath("//input[@id='update1']");
    private final By savedSearch = By.name("SavedSearches");
    private final By textboxDespiption =By.id("Description");
    private final By switchiframeEdit = By.id("TRAK_info");
    private final By nextpagequeue = By.xpath("//a[@id='NextPage_LBVerificationQueue_TestSets_FindList']");
    private By clinicalResultEpisode = By.xpath("//label[text()='%s']");
    private  final By totalResults = By.xpath("//a[@id='totalCountz1']");
    private  final By totalResults2 = By.xpath("//a[@id='totalCountz2']");
    private  final By totalResults3 = By.xpath("//a[@id='totalCountz3']");
    private final By tableSearchResult = By.xpath("//table[@name='tLBVerificationQueueGrid_List']");
    private final By searchResultTitle = By.xpath("//label[@id='SRCHDesc']");

    private final By tableLabquery = By.xpath("//table[@id='tLBVerificationQueueGrid_List']");
    private final By testSetEditCollection = By.xpath("//a[contains(text(),'Single')]");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By testSetSingleCollectionSingle = By.xpath("//a[text()='Single']");
    private final By secondrow = By.xpath("//a[text()='Single']");

    private final By allsingle = By.xpath("//a[not(@disabled) and(not(contains(@class,'disabled'))) and(not(contains(.,'display: none'))) and (text()='Single')]");
    private final By lastavailable = By.xpath("//a[@id='nextAvailable']");

    private final By rowcount = By.xpath("//label[@name='rowCount']");

    private List<String> allSearchelement  =new ArrayList<>();
    private String desc ="";



    public LabQueues(Roman roman) {
        super(roman);
    }



//Query Search Save
    public boolean querySearchSave(String queueTypeSelected,String department,String testSetStat,String testsite){

        desc = testsite+" Reference lab "+department+(new Random().nextDouble() - new Random().nextDouble());
        switchToFrame(switchiFrame);
        if(!queueTypeSelected.isEmpty()) {
            setSelectedItem(queueType, queueTypeSelected);
        }
        if(!department.isEmpty()){
            setSelectedItem(deparmentlist,department);
        }

        if(!testSetStat.isEmpty()){
            setSelectedItem(testSetStatus,testSetStat);
        }
        sendKeys(testSetLabSite,testsite);
        click(findbuttonLab);
        if(!(getAllElementText(SearchResults).size() == 0)){
            stepPassedWithScreenshot("Able to view query results");
        }else{
            Assert.fail("Unable to receive search result");
        }
        //edit Save Results
        click(saveSearch);
        setSwitchiframeEdit();

       sendKeys(descriptionText,desc);
       click(updateLabQueuesSearch);
       //accessing Saved Searches List
        setSwitchiFrame();
        click(savedSearch);
        setSwitchiframeEdit();
        sendKeys(textboxDespiption,desc);
        click(findbuttonLab);

       if(getText(descriptionTable).contains(desc)){
           switchToDefaultContext();
           stepPassedWithScreenshot("Able to view Description on list "+desc);
           return true;
       }
        switchToDefaultContext();
        return false;
    }

    //Save and Select query result
    public boolean queuesSelectResult(String queueTypeSelected,String department,String testSetStat,String testsite){
        if(querySearchSave(queueTypeSelected,department,testSetStat,testsite)){
            setSwitchiframeEdit();
            click(descriptionTable);
            setSwitchiFrame();
            if(getText(searchResultTitle).contentEquals(desc)){
                stepPassedWithScreenshot("Successfully viewed Saved search results " +desc);
                switchToDefaultContext();
              return true;
            }
        }
        Assert.fail("Unable to find Search Result");
        return false;
    }

    public void searchResults(String queueTypeSelected,String department,String testSetStat){
        switchToFrame(switchiFrame);

        if(!queueTypeSelected.isEmpty()) {
            setSelectedItem(queueType, queueTypeSelected);
        }
        if(!department.isEmpty()){
            setSelectedItem(deparmentlist,department);
        }

        if(!testSetStat.isEmpty()){
            setSelectedItem(testSetStatus,testSetStat);
        }
        click(findbuttonLab);

    }

    public void SelectSecondrow(){
        switchToFrame(switchiFrame);
        click(secondrow);
    }

    public String findlastresultlist(String labespide, Boolean clicklastElementList,int totalrow){
        switchToFrame(switchiFrame);

        switch (totalrow) {
            case 1 -> click(totalResults);
            case 2 -> click(totalResults2);
            default -> Assert.fail("Unable to find the row");
        }
        while(validateElement_Enabled_Displayed(nextpagequeue,5)){
            click(nextpagequeue);
        }
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[contains(text(),'%s')]".replace("%s",labespide));
        List<String> texts;
        if(validateElement_Displayed(clinicalResultEpisode)) {
            texts = getAllElementText(clinicalResultEpisode, 10);
            stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
            String value = texts.get(0);
            if(clicklastElementList){
                clickLastElement();
            }

            return value;
        }else{
            Assert.fail("Unable to view "+labespide +"on lab queues");
        }


        return null;
    }

    public void clickLastElement(){
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        List<WebElement> webElementList = find(allsingle);
        if(webElementList.get(webElementList.size()-1).isEnabled()){
           webElementList.get(webElementList.size()-1).click();
        }


    }

    public void navigatetoHomepage(){
        click(homeicon);
    }

    public void navigatetoToolBox(){
        click(mainmenu);
        click(toolbox);
    }

    public void navigateTestSet(){

        if(validateElement_Enabled_Displayed(labInstrumentResultGenerator)){
            click(labInstrumentResultGenerator,10);
        }else{
            click(testmenu);
            click(labInstrumentResultGenerator,10);
        }

    }

    public String findlastresultlist(String labespide,String description){

        List<String> value = getAllElementText(tableLabquery);

        click(totalResults);
        while(validateElement_Enabled_Displayed(nextpagequeue,5)){
            click(nextpagequeue);
        }
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[contains(text(),'%s')]".replace("%s",labespide));
        List<String> texts = getAllElementText(clinicalResultEpisode,10);

        if(texts.size()<=1){
            stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
            return texts.get(0);

        }

        return null;

    }


    public void setSwitchiframeEdit(){
        switchToDefaultContext();
        switchToFrame(switchiframeEdit);

    }

    public void setSwitchiFrame(){
        switchToDefaultContext();
        switchToFrame(switchiFrame);

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
