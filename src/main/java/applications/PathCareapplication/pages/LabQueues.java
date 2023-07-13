package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.LabQueueEntity;
import applications.PathCareapplication.models.LabQueueValuesEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.*;

public class LabQueues extends AbstractExtension {

    private final By queueType = By.xpath("//input[@id='QueueType']");
    private final By toolbox = By.xpath("//md-icon[@class='mainMenuToolsIcon']");
    private final By testMenu = By.xpath("//span[text()='Test']");
    private final By advanceSearchParameter = By.xpath("//a[text()='Advanced Search Parameters']");
    private final By labInstrumentResultGenerator = By.xpath("//span[text()='Lab Instrument Result Generator']");
    private final By homeicon = By.xpath("//md-icon[@title='Home']");
    private final By inputtestSetStatus = By.xpath("//input[@id='TestSetStatus']");
    private final By inputTestSet = By.xpath("//input[@id='TestSet']");
    private final By switchiFrame = By.xpath("//iframe[@id='TRAK_main']");

    private final By deparmentlist = By.xpath("//input[@id='Department']");
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
    private  final By totalResults5 = By.xpath("//a[@id='totalCountz4']");
    private final By totalResults6 = By.xpath("//a[@id='totalCountz6']");
    private  final By maxfailures1 = By.xpath("//a[@id='MaxTimeFailureCountz1']");
    private  final By maxfailures2 = By.xpath("//a[@id='MaxTimeFailureCountz2']");
    private  final By maxfailures3 = By.xpath("//a[@id='MaxTimeFailureCountz3']");
    private final By searchResultTitle = By.xpath("//label[@id='SRCHDesc']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By secondrow = By.xpath("//a[text()='Single']");



    private final By allsingle = By.xpath("//a[not(@disabled) and(not(contains(@class,'disabled'))) and(not(contains(.,'display: none'))) and (text()='Single')]");
    private final By allEpisode = By.xpath("//a[not(@disabled) and(not(contains(@class,'disabled'))) and(not(contains(.,'display: none'))) and (text()='Episode')]");
    private final By lastavailable = By.xpath("//a[@id='nextAvailable']");
    private By elementEpisodeSingle  = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Episode']");
    private By elementSingle  = By.xpath("//td[following-sibling::td[contains(.,'%s')]]//a[not(@disabled) and text()='Episode']");

    private final By tablefield = By.xpath("//tr[contains(@class,'LBVerificationQueueV')]");

    private final By rowcount = By.xpath("//label[@name='rowCount']");

    private final By firstrowQueueResult = By.xpath("//tr[contains(@class,'LBVerificationQueueP RowEven')]//td[not(contains(@style,'display:none'))and not(contains(@class,'clsRowColourTabLBVerificationQueueP')) and  not(contains(@style,'#'))]");
    private final By tableQueue = By.xpath("//table[@class='tblList']");
    private final By firstSingleTestSet= By.xpath("//*[@id='singleEditz1']");
    private final By testSetProtocolsCompleted_Cancelled = By.xpath("//img[@title='Test Set Protocols Completed/Cancelled']");
    private final By infoPaneClose = By.xpath("//span[@id='InfoPaneClose']");
    private final By viewQueuesLink = By.xpath("//a[@id='ViewQueuesLink']");
    private final By referSelected = By.xpath("//input[@id='TransferSelected']");
    private final By selectz1 = By.xpath("//input[@id='selectz1']");
    private final By selectz2 = By.xpath("///input[@id='selectz2']");
    private final By selectz3 = By.xpath("//input[@id='selectz3']");
    private By testSetOptionButtonDropDown = By.xpath("//a[text()='Test Set Options']");
    private final By homeButton = By.xpath("//md-icon[@id='tc_NavBar-misc-homeButtonIcon']");
    private boolean recent = false;
    private String locationNew = "";

    private List<String> allSearchelement  =new ArrayList<>();
    public HashMap<String,String> totalNumber = new HashMap<>();
    public HashMap<String,String> totalNumber2 = new HashMap<>();
    private Boolean firstTime = true;
    private String desc ="";

    private int timeout = 30;



    public LabQueues(Roman roman) {
        super(roman);
    }



//Query Search Save
    public boolean querySearchSave(String queueTypeSelected,String department,String testSetStat,String testsite){

        desc = testsite+" Reference lab "+department+(new Random().nextDouble() - new Random().nextDouble());
        switchToFrame(switchiFrame);
        if(!queueTypeSelected.isBlank()) {
            sendKeys(queueType, queueTypeSelected, timeout);
            findOne(queueType,timeout).sendKeys(Keys.TAB);
        }
        if(!department.isBlank()){
            sendKeys(deparmentlist,department,timeout);
            findOne(deparmentlist,timeout).sendKeys(Keys.TAB);
        }

        if(!testSetStat.isBlank()){
            sendKeys(inputtestSetStatus,testSetStat,timeout);
            findOne(inputtestSetStatus,timeout).sendKeys(Keys.TAB);
        }
        awaitElement(advanceSearchParameter,timeout);
        click(advanceSearchParameter,timeout);
        awaitElement(testSetLabSite,timeout);
        sendKeys(testSetLabSite,testsite, timeout);
        findOne(testSetLabSite,timeout).sendKeys(Keys.TAB);
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
        sendKeys(textboxDespiption,desc,true,true,10);
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

    public void SearchResultTable(String colunmHeader,String tableHeader,int timeout){
        tableExtactionIndex(tableQueue,colunmHeader,tableHeader,timeout);
    }

    public void searchResults(String queueTypeSelected,String department,String testSet,String testSetStat) throws InterruptedException {
        switchToDefaultContext();
        Thread.sleep(4000);

        switchToFrame(switchiFrame);

        if(!queueTypeSelected.isEmpty()) {
            sendKeys(queueType, queueTypeSelected, timeout);
            findOne(queueType,timeout).sendKeys(Keys.TAB);
        }
        if(!department.isEmpty()){
            sendKeys(deparmentlist,department,timeout);
            findOne(deparmentlist,timeout).sendKeys(Keys.TAB);
        }

        if(!testSet.isEmpty()){
            sendKeys(inputTestSet,testSet,timeout);
            findOne(inputTestSet,timeout).sendKeys(Keys.TAB);
        }


        if(!testSetStat.isEmpty()){
            sendKeys(inputtestSetStatus,testSetStat,timeout);
            findOne(inputtestSetStatus,timeout).sendKeys(Keys.TAB);
        }

        click(findbuttonLab);
    }

    public void Selectrow(){
        switchToFrame(switchiFrame);
        click(secondrow);
    }
    public void  selecSearch(int totalrow) throws InterruptedException {
        switchToDefaultContext();
        awaitElement(switchiFrame,timeout);
        switchToFrame(switchiFrame);
        switch (totalrow) {
            case 1 -> click(totalResults);
            case 2 -> click(totalResults2);
            case 3 -> click(totalResults3);
            case 4 -> click(totalResults4);
            case 5 -> click(totalResults5);
            case 6 -> click(totalResults6);
            case 7 -> click(maxfailures1);
            case 8 -> click(maxfailures2);
            case 9 -> click(maxfailures3);
            default -> Assert.fail("Unable to find the row");
        }
    }

    public String findlastresultlist(String labespide, boolean clicklastElementList,int totalrow,boolean single) throws InterruptedException {
        Thread.sleep(3000);
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        selecSearch(totalrow);

        while(validateElement_Enabled_Displayed(nextpagequeue,5)){
            click(nextpagequeue);
        }
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[not(@disabled) and text()='%s']".replace("%s",labespide));
        List<String> texts;
        if(validateElement_Displayed(clinicalResultEpisode)) {
            texts = getAllElementText(clinicalResultEpisode, timeout);
            stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
            String value = texts.get(0);
            if(clicklastElementList){
                clickEspiodeElement(single,texts.get(0));
            }
            return value;
        }else {
            Assert.fail("Unable to view  " + labespide + " on lab queues");
        }

        return null;
    }

    public String findlastresultlistWithoutSearch(String labespide, boolean clicklastElementList,boolean single) throws InterruptedException {
        Thread.sleep(3000);
        switchToDefaultContext();
        switchToFrame(switchiFrame);


        while(validateElement_Enabled_Displayed(nextpagequeue,5)){
            click(nextpagequeue);
        }
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[not(@disabled) and text()='%s']".replace("%s",labespide));
        List<String> texts;
        if(validateElement_Displayed(clinicalResultEpisode)) {
            texts = getAllElementText(clinicalResultEpisode, timeout);
            stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
            String value = texts.get(0);
            if(clicklastElementList){
                clickEspiodeElement(single,texts.get(0));
            }
            return value;
        }else {
            Assert.fail("Unable to view  " + labespide + " on lab queues");
        }

        return null;
    }

    public boolean searchEachEpisode(ArrayList<String> labepisodeNumber){
        boolean found= false;
        for(String episode:labepisodeNumber){

            By episodeNumber = By.xpath(" //label[not(@disabled) and (contains(.,'s%'))]".replace("s%",episode));
            while(!validateElement_Enabled_Displayed(episodeNumber)){
                switchToDefaultContext();
                switchToFrame(switchiFrame);
                //next page
                if(validateElement_Enabled_Displayed(nextpagequeue, timeout)) {
                    click(nextpagequeue);
                }else{
                    break;
                }
                switchToDefaultContext();
                switchToFrame(switchiFrame);
            }
                if (validateElement_Displayed(episodeNumber, timeout)) {
                    click(episodeNumber);
                    stepInfoWithScreenshot("clicked Lab Episode " + episode);
                }
            found=true;
            }

        return found;
    }

    public String findresultonlistsearch(String labespide, boolean clicklastElementList, int totalrow, boolean single) throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        selecSearch(totalrow);

        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[not(@disabled) and text()='%s']".replace("%s",labespide));
          while(!validateElement_Enabled_Displayed(clinicalResultEpisode)){
              switchToDefaultContext();
              switchToFrame(switchiFrame);
              //next page
              if(validateElement_Enabled_Displayed(nextpagequeue, timeout)) {
                  click(nextpagequeue);
              }else{
                  break;
              }
              switchToDefaultContext();
              switchToFrame(switchiFrame);
            }

        switchToDefaultContext();
        switchToFrame(switchiFrame);

        List<String> texts;
        if(validateElement_Displayed(clinicalResultEpisode)) {
            texts = getAllElementText(clinicalResultEpisode, timeout);
            stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
            String value = texts.get(0);
            if(clicklastElementList){
                clickEspiodeElement(single,texts.get(0));
            }
            return value;
        }else{
            stepInfoWithScreenshot("Unable to view  "+labespide +" on lab queues");
            Assert.fail("Unable to view  "+labespide +" on lab queues");
        }


        return null;
    }

    public void clickAnyElementlist(int totalrow, boolean single) throws InterruptedException {
        selecSearch(totalrow);
        clickFirstElement();

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
              SearchResultTable(titleHeader,"Phone Queue",timeout);
              found = true;
              break;
            }
      }
        if(found) {
            clickFirstElement();
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
                    case 4 -> totalNumber.put("STAT", resultlist.get(x));
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

    public void clickFirstElement(){
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        List<WebElement> webElementList = find(allsingle);
        if(webElementList.get(0).isEnabled()){
            webElementList.get(0).click();
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
    public void selectSingleEpisode(){
        for(int x=1;x<=3;++x) {
            final By firstSingleTestSet=  By.xpath("//a[@id='singleEditz"+"%s']".replace("%s",Integer.toString(x)));
            click(firstSingleTestSet,timeout);
            click(testSetProtocolsCompleted_Cancelled,timeout);
            switchToDefaultContext();switchToFrame(By.name("TRAK_info"));
            click(infoPaneClose,timeout);
            switchToDefaultContext();switchToMainFrame();
            click(testSetOptionButtonDropDown, timeout);
            click(viewQueuesLink,timeout);
            switchToDefaultContext();
            javascriptClick(_driver.findElement(homeButton));
            switchToMainFrame();
            SearchResultTable("Total","Cytology - Non-Gynae Workload",20);
        }
    }
    public void selectlistlabespido(ArrayList<String> labEpisode) throws InterruptedException {

        for(String value:labEpisode){
            By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//a[contains(@id,'singleEditz')]".replace("%s",value));
                if(validateElement_Displayed(labEpisodefield)){
                   // click(labEpisodefield,timeout);
                    javascriptClick(_driver.findElement(labEpisodefield));
                    click(testSetProtocolsCompleted_Cancelled,timeout);
                    switchToDefaultContext();switchToFrame(By.name("TRAK_info"));
                    click(infoPaneClose,timeout);
                    Thread.sleep(2000);
                    switchToDefaultContext();switchToMainFrame();
                    click(testSetOptionButtonDropDown, timeout);
                    Thread.sleep(2000);
                    click(viewQueuesLink,timeout);
                    switchToDefaultContext();
                    javascriptClick(_driver.findElement(homeButton));
                    Thread.sleep(2000);
                    switchToMainFrame();
                    SearchResultTable("Total","Cytology - Non-Gynae Workload",40);
                    Thread.sleep(4000);
                }else {

                }
            }
    }
    public void tickeEpisodeAndClickReferSelected(ArrayList<String> labEpisode) throws InterruptedException {
        for(String value:labEpisode){
            By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'selectz')]".replace("%s",value));
            javascriptClick(_driver.findElement(labEpisodefield));
            Thread.sleep(2000);
        }
        click(referSelected);
    }
    public void labQueueSheet(List<LabQueueEntity> labQueueEntities, List<LabQueueValuesEntity> labQueueValuesEntities, ArrayList<String> labEpsiode,ResultEntry resultEntry,PathCareProcessingPage pathCareProcessingPage, InterSystemLoginPage interSystemLoginPage) throws InterruptedException {
        Boolean found = false;
        for(LabQueueEntity labQueueEntity:labQueueEntities) {
           changeLocation(labQueueEntity.getUserProfile(), interSystemLoginPage);
           searchResults(labQueueEntity.getQueueType(),labQueueEntity.getDepartment(),labQueueEntity.getTestSet(),"");
           SearchResultTable(labQueueEntity.getSearchOption(),labQueueEntity.getSearchOptionQueue(),timeout);
           for(String lapespidoe :labEpsiode){
               if(lapespidoe.split(",")[0].contentEquals(labQueueEntity.getPatientKey())) {
                   findlastresultlistWithoutSearch(lapespidoe.split(",")[1], true, labQueueEntity.getSelectSingleOrEpisode().toLowerCase().contentEquals("single"));
                   for (LabQueueValuesEntity labQueueValuesEntity : labQueueValuesEntities) {
                       if (labQueueValuesEntity.getLabQueuesFK().contentEquals(labQueueEntity.getLabQueuesKey())) {
                           if ((labQueueValuesEntity.getApply() != null) && labQueueValuesEntity.getApply().toLowerCase().contentEquals("yes")) {
                               resultEntry.applyResultOnly();
                               switchToDefaultContext();
                               click(homeicon, timeout);
                               found=true;
                               break;
                           }
                           if ((labQueueValuesEntity.getValidate() != null) && labQueueValuesEntity.getValidate().toLowerCase().contentEquals("yes")) {
                               resultEntry.onlyapplyandvalidate(true);
                               switchToDefaultContext();
                               click(homeicon, timeout);
                               found=true;
                               break;
                           }
                           if ((labQueueValuesEntity.getAuthorise() != null) && labQueueValuesEntity.getAuthorise().toLowerCase().contentEquals("yes")) {
                               resultEntry.authorise();
                               switchToDefaultContext();
                               click(homeicon, timeout);
                               found=true;
                               break;
                           }
                           if ((labQueueValuesEntity.getPhone() != null) && labQueueValuesEntity.getPhone().toLowerCase().contentEquals("yes")) {
                               pathCareProcessingPage.phonequeue();
                               switchToDefaultContext();
                               click(homeicon, timeout);
                               found=true;
                               break;
                           }
                       }
                   }

               }
               if(found){
                   found = false;
                   break;
               }
           }
       }
    }

    void changeLocation(String location,InterSystemLoginPage interSystemloginPage){

        if (!locationNew.contentEquals(location)){
            locationNew =location;
            switchToDefaultContext();
            interSystemloginPage.setLocation(location);
            if(!firstTime) {
                switchToDefaultContext();
                interSystemloginPage.changelocation();

            }else{
                firstTime=false;
            }
            interSystemloginPage.userselection();
        }
    }



    public void navigatetoHomepage(){
        click(homeicon);
    }

    public void navigatetoToolBox(){

        awaitElement(mainmenu, timeout);
        click(mainmenu, timeout);
        if(!validateElement_Displayed(testMenu, timeout)) {
            click(toolbox, timeout);
        }
    }

    public void navigateTestSet(){

        if(validateElement_Enabled_Displayed(labInstrumentResultGenerator, timeout)){
            awaitElement(labInstrumentResultGenerator, timeout);
            click(labInstrumentResultGenerator, timeout);
        }else{
            awaitElement(testMenu, timeout);
            click(testMenu, timeout);
            awaitElement(labInstrumentResultGenerator, timeout);
            click(labInstrumentResultGenerator, timeout);
        }

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
