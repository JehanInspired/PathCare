package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.AbstractPage;

import java.util.*;

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
    private  final By totalResults4 = By.xpath("//a[@id='totalCountz4']");
    private  final By maxfailures1 = By.xpath("//a[@id='MaxTimeFailureCountz1']");
    private  final By maxfailures2 = By.xpath("//a[@id='MaxTimeFailureCountz2']");
    private  final By maxfailures3 = By.xpath("//a[@id='MaxTimeFailureCountz3']");
    private final By tableSearchResult = By.xpath("//table[@name='tLBVerificationQueueGrid_List']");
    private final By searchResultTitle = By.xpath("//label[@id='SRCHDesc']");

    private final By tableLabquery = By.xpath("//table[@id='tLBVerificationQueueGrid_List']");
    private final By testSetEditCollection = By.xpath("//a[contains(text(),'Single')]");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By testSetSingleCollectionSingle = By.xpath("//a[text()='Single']");
    private final By secondrow = By.xpath("//a[text()='Single']");

    private final By allsingle = By.xpath("//a[not(@disabled) and(not(contains(@class,'disabled'))) and(not(contains(.,'display: none'))) and (text()='Single')]");
    private final By allEpisode = By.xpath("//a[not(@disabled) and(not(contains(@class,'disabled'))) and(not(contains(.,'display: none'))) and (text()='Episode')]");
    private final By lastavailable = By.xpath("//a[@id='nextAvailable']");
    private By elementEpisodeSingle  = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Episode']");
    private By elementSingle  = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Episode']");

    private final By tablefield = By.xpath("//tr[contains(@class,'LBVerificationQueueV')]");

    private final By rowcount = By.xpath("//label[@name='rowCount']");

    private final By firstrowQueueResult = By.xpath("//tr[contains(@class,'LBVerificationQueueP RowOdd')]//td[not(contains(@style,'display:none'))and not(contains(@class,'clsRowColourTabLBVerificationQueueP')) and  not(contains(@style,'#'))]");

    private boolean recent =false;

    private List<String> allSearchelement  =new ArrayList<>();
    public HashMap<String,String> totalNumber = new HashMap<>();
    public HashMap<String,String> totalNumber2 = new HashMap<>();

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

    public void searchResults(String queueTypeSelected,String department,String testSetStat) throws InterruptedException {
        switchToDefaultContext();
        Thread.sleep(4000);
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

    public void Selectrow(){
        switchToFrame(switchiFrame);
        click(secondrow);
    }
    private void  selecSearch(int totalrow){
        switch (totalrow) {
            case 1 -> click(totalResults);
            case 2 -> click(totalResults2);
            case 3 -> click(totalResults3);
            case 4 -> click(totalResults4);
            case 5 -> click(maxfailures1);
            case 6 -> click(maxfailures2);
            case 7 -> click(maxfailures3);
            default -> Assert.fail("Unable to find the row");
        }
    }

    public String findlastresultlist(String labespide, boolean clicklastElementList,int totalrow,boolean single){
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        selecSearch(totalrow);

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
                clickEspiodeElement(single,texts.get(0));
            }

            return value;
        }else{
            Assert.fail("Unable to view  "+labespide +"on lab queues");
        }


        return null;
    }

    public void clickAnyElementlist(int totalrow, boolean single){
        selecSearch(totalrow);
        clickLastElementAny();

    }

    public void phoneQueues(String titleHeader,boolean recent){
        List<String>  resultslist  = getAllElementText(firstrowQueueResult);
        boolean found = false;

        structureTotal(resultslist, recent);
      for(WebElement element:find(firstrowQueueResult)){
          if(totalNumber.get(titleHeader).isBlank()){
              stepInfoWithScreenshot(titleHeader+" has no test sets");
              found = false;
              break;
          }
          //Queues
          if((!totalNumber.get(titleHeader).isBlank() && totalNumber.get(titleHeader).contentEquals(element.getText())) && !found || totalNumber2.getOrDefault(titleHeader,"").contentEquals(element.getText()) ){
                if (element.isEnabled()) {
                    element.click();
                    found = true;
                }
            }
      }
      if(found) {
          clickLastElementAny();
      }

    }

    public void finaltotal() {
        List<String> resultlist = getAllElementText(firstrowQueueResult);


        for (int x = 0; x <= resultlist.size() - 1; x++) {

            switch (x) {
                case 0 -> totalNumber2.put("Total Failures", resultlist.get(x));
                case 1 -> totalNumber2.put("Alert Failures", resultlist.get(x));
                case 2 -> totalNumber2.put("Max Failures", resultlist.get(x));
                case 3 -> totalNumber2.put("Total", resultlist.get(x));
                case 4 -> totalNumber2.put("Stat", resultlist.get(x));
                case 5 -> totalNumber2.put("Urgent", resultlist.get(x));
                case 6 -> totalNumber2.put("Routine", resultlist.get(x));
                case 7 -> totalNumber2.put("Norm", resultlist.get(x));
                case 8 -> totalNumber2.put("No Priority", resultlist.get(x));
                default -> Assert.fail("Unable to find the column");
            }


        }
        stepInfoWithScreenshot("Total Updated "+totalNumber2.toString());
    }



    private void structureTotal(List<String> resultlist,Boolean recent){

        if(recent) {
            for (int x = 0; x <= resultlist.size() - 1; x++) {

                switch (x) {
                    case 0 -> totalNumber2.put("Total Failures",resultlist.get(x));
                    case 1 ->  totalNumber2.put("Alert Failures",resultlist.get(x));
                    case 2 ->  totalNumber2.put("Max Failures", resultlist.get(x));
                    case 3 ->  totalNumber2.put("Total", resultlist.get(x));
                    case 4 -> totalNumber2.put("Stat", resultlist.get(x));
                    case 5 -> totalNumber2.put("Urgent", resultlist.get(x));
                    case 6 -> totalNumber2.put("Routine", resultlist.get(x));
                    case 7 -> totalNumber2.put("Norm", resultlist.get(x));
                    case 8 -> totalNumber2.put("No Priority", resultlist.get(x));
                    default -> Assert.fail("Unable to find the column");
                }

            }

            stepInfoWithScreenshot("Total Updated"+totalNumber2.toString());
        }else {

            for (int x = 0; x <= resultlist.size() - 1; x++) {

                switch (x) {
                    case 0 -> totalNumber.put("Total Failures", resultlist.get(x));
                    case 1 -> totalNumber.put("Alert Failures", resultlist.get(x));
                    case 2 -> totalNumber.put("Max Failures", resultlist.get(x));
                    case 3 -> totalNumber.put("Total", resultlist.get(x));
                    case 4 -> totalNumber.put("Stat", resultlist.get(x));
                    case 5 -> totalNumber.put("Urgent", resultlist.get(x));
                    case 6 -> totalNumber.put("Routine", resultlist.get(x));
                    case 7 -> totalNumber.put("Norm", resultlist.get(x));
                    case 8 -> totalNumber.put("No Priority", resultlist.get(x));
                    default -> Assert.fail("Unable to find the column");
                }
            }
            stepInfoWithScreenshot("Total 1st "+totalNumber.toString());
        }



    }

    public void clickLastElementAny(){
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        List<WebElement> webElementList = find(allsingle);
        int number = new Random().nextInt(webElementList.size());
        if(webElementList.get(number).isEnabled()){
            webElementList.get(number).click();
        }


    }


    public void clickEspiodeElement(Boolean single,String episodeNumber){

        switchToDefaultContext();
        switchToFrame(switchiFrame);
        if(single) {
            elementSingle = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Single']".replace("%s",episodeNumber));
            List<WebElement> webElementList = find(elementSingle);
            if (webElementList.get(webElementList.size() - 1).isEnabled()) {
                webElementList.get(webElementList.size() - 1).click();
            }
        } else {
            elementEpisodeSingle = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Episode']".replace("%s",episodeNumber));
            List<WebElement> webElementList = find(elementEpisodeSingle);
            if (webElementList.get(webElementList.size() - 1).isEnabled()) {
                webElementList.get(webElementList.size() - 1).click();
            }

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