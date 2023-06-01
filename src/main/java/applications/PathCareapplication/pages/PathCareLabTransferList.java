package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PathCareLabTransferList extends AbstractExtension {


  private final By analyticalbuttonmenu = By.xpath("//span[text()='Analytical']");
  private final By specimennumbertext = By.xpath("//input[@id='SpecimenNumber']");

  private final By selectallspecimen = By.xpath("//input[@id='SelectAll']");

  private final By labEpisode = By.xpath("//input[@id='LBEpisodeNo']");

  private final By findbutton = By.xpath("//input[@id='find1']");
  private final By switchiFrame = By.xpath("//iframe[@name='TRAK_main']");

  private final By shipmentbutton = By.xpath("//input[@id='CreateShipment']");
  private  final By addshipmentcontainerbutton = By.xpath("//a[@id='AddShipmentContainer']");
  private final By shipmentnumbertext = By.xpath("//label[@name='LBSHCNumberz1']");

  private final By status = By.xpath("//label[@id='LBTRStatusz1']");
  private final By packSpecimenNumber = By.xpath("//input[@id='SpecimenNumber']");
  private final By closedbutton = By.xpath("//a[@id='Closez1']");

  private final By receiveButton = By.xpath("//input[@id='BulkReceive']");

  private final By closeShipment =  By.xpath("//a[@id='Closez1']");
  private  final By closepacksession = By.xpath("//input[@id='close1']");
  private By listTransfer = By.xpath("//label[text()='%s']");
  private final  By testSetlist = By.xpath("//select[@name='TestSetList']");
  private final By testSetTextBox = By.xpath("//input[@name='TestSet']");

  private  final By fromLabSite = By.name("FromSite");

  private final By toLabsite = By.name("ToSite");

  private final By saveSearchResult = By.id("SaveSearch");

  private final By descriptionSaveSearch = By.id("SRCHDesc");
  private final By updatebutton = By.id("update1");
  private final By savedSearches = By.id("SavedSearches");
  private final By descriptiontextbox = By.xpath("//input[@id='Description']");
  private  By getDescriptionSaveSearchfromlist ;
  private  final By iframeSaveSearch = By.name("TRAK_info");
    private final By searchResultTitle = By.xpath("//label[@id='SRCHDesc']");
    private final By nextpageTransferlist =By.xpath("//img[@id='NextPageImage_LBTransfer_List']");
    private final By dateSentFrom = By.xpath("//input[@id='FromSentDate']");
    private final By dateSentTo = By.xpath("//input[@id='DateSentTo']");
    private final By tLBTransferShipmentList= By.xpath("//table[@id='tLBTransfer_Shipment_List']/tbody");
    private final By firstTransferMaterialList= By.xpath("//label[@id='TransferMaterialListz1']");
    private final By packNumberText= By.xpath("//input[@id='PackNumber']");
    private final By transitStatus = By.xpath("//label[@id='LBTRStatusz1']");
    private String description= "";
    private String url = "";
    private int timeout = 20;

    public String shipmentNumber = "";
  public void tranferlistLabepisode(String text){
      switchToFrame(switchiFrame);
      sendKeys(labEpisode,text,timeout);
      if(validateElement_Enabled_Displayed(findbutton,timeout)){
      click(findbutton,timeout);
      stepPassedWithScreenshot("Successfully Entered lab Episode");
      }
  }
    public void tranferStatusFilter(String status) throws InterruptedException {
        switchToFrame(switchiFrame);
        findOne(By.xpath("//input[@id='Status']"), status);
        if(validateElement_Enabled_Displayed(findbutton,timeout)){
            click(findbutton,timeout);
            stepPassedWithScreenshot("Successfully Entered lab Episode");
        }
    }
    public void enterPackNumberAndFind(String packNumber) throws InterruptedException {
        switchToFrame(switchiFrame);
        findOne(packNumberText, packNumber);
        if(validateElement_Enabled_Displayed(findbutton,timeout)){
            click(findbutton,timeout);
            stepPassedWithScreenshot("Successfully Entered packed number");
        }
    }
    public  boolean shipmentPackageIsInTransit(){
      return validateElement_Displayed(transitStatus,timeout);
    }
    public void tranferSpecimenIntoShipmentContainer(){
        click(shipmentbutton);
        click(addshipmentcontainerbutton,timeout);
        while(validateElement_Displayed(tLBTransferShipmentList,timeout)){
              String specimenNumber=  getText(firstTransferMaterialList);
              String extractedSpecimenNumber = StringUtils.substringBefore(specimenNumber, ":");
              sendKeys(packSpecimenNumber,extractedSpecimenNumber ,timeout);
              super._driver.findElement(packSpecimenNumber).sendKeys(Keys.TAB);
        }

        if(validateElement_Displayed(shipmentnumbertext,timeout)){
            shipmentNumber = getText(shipmentnumbertext,timeout);
            stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
        } else{
            stepInfoWithScreenshot("Unable to receive a shipment container number");
            Assert.fail("Unable to to receive a shipment container number");
        }
         click(closeShipment,timeout);
         acceptAlert();
         stepPassedWithScreenshot("Successfully clicked closed shipment");
    }
    public void tranferlistLabepisodewithoutframe(String text){
        sendKeys(labEpisode,text,timeout);
        if(validateElement_Enabled_Displayed(findbutton,timeout)){
            click(findbutton,timeout);
            stepPassedWithScreenshot("Successfully Entered lab Episode");
        }
    }


    public Boolean checknumbersTransfer(String labepisode, List<String> specimennumbers){
      boolean checker = false;
      listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));

      if(getAllElementText(listTransfer).size()==specimennumbers.size()) {
          stepPassedWithScreenshot("Able to view package In Transit "+labepisode);
          checker =true;
      }
        return checker;
  }
    public void checkPackItem(ArrayList<String> mulitplelabepisode,ArrayList<ArrayList<String>> specimenRecieve) throws InterruptedException {
      switchToFrame(switchiFrame);
      findOne(By.xpath("//input[@id='Status']"), "PKD");
      int y=0;
      for (String labEpisode : mulitplelabepisode) {
          findOne(this.labEpisode, labEpisode);
          clickFindButton();
              By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'Selectz')]".replace("%s", labEpisode));
              if (!(getAllElementText(labEpisodefield).size() == specimenRecieve.get(y).size())) {
                  Assert.fail("Unable to find " + labEpisode);
              }
              stepInfoWithScreenshot("Able to find specimen under lab " + labEpisode);
          y++;
      }
  }
    public void checkWaitItem(ArrayList<String> mulitplelabepisode,ArrayList<ArrayList<String>> specimenRecieve) throws InterruptedException {
        switchToFrame(switchiFrame);
        findOne(By.xpath("//input[@id='Status']"), "W");
        int y=0;
        for (String labEpisode : mulitplelabepisode) {
            findOne(this.labEpisode, labEpisode);
            clickFindButton();
            By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'Selectz')]".replace("%s", labEpisode));
            if (!(getAllElementText(labEpisodefield).size() == specimenRecieve.get(y).size())) {
                Assert.fail("Unable to find " + labEpisode);
            }
            stepInfoWithScreenshot("Able to find specimen under lab " + labEpisode);
            y++;
        }
    }

    public Boolean checknumbersTransferlapepisode(String labepisode, ArrayList<String> specimennumbers) throws InterruptedException {
        boolean checker = false;
        switchToFrame(switchiFrame);
        findOne(By.xpath("//input[@id='Status']"),"T");
            findOne(this.labEpisode, labepisode);
            clickFindButton();
            listTransfer = By.xpath("//label[text()='%s']".replace("%s", labepisode));
                    if (getAllElementText(listTransfer).size() == specimennumbers.size()) {
                        stepPassedWithScreenshot("Able to view package In Transit " + labEpisode);
                        checker = true;
                    }

        return checker;
    }

    public Boolean checknumbersTransferMultiple(ArrayList<String> mulitplelabepisode, ArrayList<ArrayList<String>> specimennumbers) throws InterruptedException {
        boolean checker = false;
        switchToFrame(switchiFrame);
        int y =0;
        findOne(By.xpath("//input[@id='Status']"),"T");
        for(String labEpisode:mulitplelabepisode) {
            findOne(this.labEpisode, labEpisode);
            clickFindButton();
            listTransfer = By.xpath("//label[text()='%s']".replace("%s", labEpisode));
            if (getAllElementText(listTransfer,timeout).size() == specimennumbers.get(y).size()) {
                stepPassedWithScreenshot("Able to view package In Transit " + labEpisode);
                checker = true;
            }
            y++;
        }
        return checker;
    }
    public Boolean checknumbersDelivery(String labepisode, List<String> specimennumbers){
        boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size()) {
            stepPassedWithScreenshot("Able to view package In Transit "+labepisode);
            checker = true;
        }
        return checker;
    }

    public Boolean checknumbersWaiting(String labepisode, List<String> specimennumbers){
        boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size() && getAllElementText(status).contains("Waiting")) {
            stepPassedWithScreenshot("Able to view Waiting "+labepisode);
            checker =true;
        }
        return checker ;
    }

    public Boolean checknumbersPackage(String labepisode, List<String> specimennumbers){
      boolean checker =false;
      switchToDefaultContext();
        switchToFrame(switchiFrame);
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size()) {
            stepPassedWithScreenshot("Able to view Packaged "+labepisode);
            checker = true;
        }else{
            stepInfoWithScreenshot("Unable to find Packed lap episode Excepted "
                    +getAllElementText(listTransfer).size()+" but received "+specimennumbers.size());
        }
        return checker ;
    }

    public Boolean checknumbersReceived(String labepisode, List<String> specimennumbers){
        boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size() && getAllElementText(status).contains("Received")) {
            stepPassedWithScreenshot("Able to view Received "+labepisode);
            checker =true;
        }
        return checker ;
    }
    public void receiceiveShipment(){
        click(selectallspecimen);
      click(receiveButton);

    }

  public void createShipment(ArrayList<String> specimennumbers,boolean selectAll) throws InterruptedException {
      if(selectAll){click(selectallspecimen);}
      click(shipmentbutton);
      click(addshipmentcontainerbutton,timeout);
      if(validateElement_Displayed(shipmentnumbertext,timeout)){
          shipmentNumber = getText(shipmentnumbertext,timeout);
          stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
      } else{
          stepInfoWithScreenshot("Unable to to receive a shipment container number");
          Assert.fail("Unable to to receive a shipment container number");
      }

      for(String value:specimennumbers) {
          findOne(packSpecimenNumber,value);
      }

      awaitElement(closeShipment,timeout);
      click(closeShipment,timeout);
      acceptAlert();
      loadingBarChecker();
      stepPassedWithScreenshot("Successfully clicked closed shipment");

  }
    public void createShipmentContainer(ArrayList<String> specimennumbers,boolean selectAll) throws InterruptedException {
        if(selectAll){click(selectallspecimen);}
        click(shipmentbutton);
        click(addshipmentcontainerbutton,timeout);
        if(validateElement_Displayed(shipmentnumbertext,timeout)){
            shipmentNumber = getText(shipmentnumbertext,timeout);
            stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
        } else{
            stepInfoWithScreenshot("Unable to to receive a shipment container number");
            Assert.fail("Unable to to receive a shipment container number");
        }

        for(String value:specimennumbers) {
            findOne(packSpecimenNumber,value);
        }

        awaitElement(closeShipment,timeout);
        click(closeShipment,timeout);
        stepPassedWithScreenshot("Successfully clicked closed shipment");

    }
    public void createShipment(Collection<ArrayList<String>> specimenNumbers, boolean selectAll) throws InterruptedException {
        if(selectAll){click(selectallspecimen);}
        click(shipmentbutton);
        click(addshipmentcontainerbutton,10);
        if(validateElement_Displayed(shipmentnumbertext,10)){
            shipmentNumber = getText(shipmentnumbertext,10);

            stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
        }

        for(ArrayList<String> values:specimenNumbers) {
            for(String value:values) {
                findOne(packSpecimenNumber, value);
                Thread.sleep(3000);
            }
        }

        try{
            click(closeShipment,timeout);
            acceptAlert();
        } catch (UnhandledAlertException ignored) {
            click(closeShipment,timeout);
            acceptAlert();

        }

        stepPassedWithScreenshot("Successfully clicked closed shipment");

    }

  public boolean closePackage(){
    switchToDefaultContext();
    switchToFrame(switchiFrame);
    if(validateElement_Enabled_Displayed(closepacksession,10)){
          click(closepacksession, 10);
            acceptAlert();
            return true;
      }else{
        Assert.fail("Unable to click close pack session button");
    }
    return false;
  }

  public boolean testSetfield(String name){
      switchToFrame(switchiFrame);
      sendKeys(testSetTextBox,name);
      super.findOne(testSetTextBox).sendKeys( Keys.TAB);
      for(String value :getAllElementText(testSetlist)){
          if(value.contentEquals(name)){
              return true;
          }
      }
      return false;
  }

  public void clickFindButton(){
      click(findbutton,timeout);
  }
  public void statChangeWaitingLinkFind(String fromSite, String toSite) throws InterruptedException {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      switchToDefaultContext();
      switchToFrame(switchiFrame);
      /*findOne(dateSentFrom,dateTimeFormatter.format(LocalDate.now()));
      findOne(dateSentTo,dateTimeFormatter.format(LocalDate.now()));*/
      findOne(fromLabSite,fromSite);
      findOne(toLabsite,toSite);
      findOne(By.xpath("//input[@id='Status']"), "W");
      clickFindButton();

  }

  public void selectlistlabespido(ArrayList<String> labEpisode){

      for(String value:labEpisode){
          int y=0;
         By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'Selectz')]".replace("%s",value));
         while(!validateElement_Enabled_Displayed(labEpisodefield,10)){
             if(validateElement_Displayed(nextpageTransferlist)){
                 click(nextpageTransferlist);
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
    public void selectListLabEspisode(ArrayList<String> labEpisode){

        for(String value:labEpisode){
            int y=0;
            By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'Selectz')]".replace("%s",value));
            while(!validateElement_Enabled_Displayed(labEpisodefield,10)){
                if(validateElement_Displayed(nextpageTransferlist)){
                    click(nextpageTransferlist);
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
  //Save Searches
    public Boolean labSearches() throws InterruptedException {

       description ="From PathCare Park Reference Lab to George Laboratory Lab Transfer "+ new Random().nextDouble();
      switchToFrame(switchiFrame);

      findOne(fromLabSite,"PathCare Park Reference Lab");
      findOne(toLabsite,"George Laboratory");
      click(findbutton);
      stepPassedWithScreenshot("Able to view Search results ");
      if(validateElement_Enabled_Displayed(saveSearchResult,10)){
          click(saveSearchResult,5);

      }

      switchToDefaultContext();
      switchToFrame(iframeSaveSearch);
      findOne(descriptionSaveSearch,description);
      stepPassedWithScreenshot("Successfully entered "+description);
      click(updatebutton);
      switchToDefaultContext();
      switchToFrame(switchiFrame);
      click(savedSearches);

        switchToDefaultContext();
        switchToFrame(iframeSaveSearch);
        getDescriptionSaveSearchfromlist = By.xpath("//a[contains(text(),'%s')]".replace("%s",description));
        sendKeys(descriptiontextbox,description,10);
        click(findbutton,timeout);
        if(getText(getDescriptionSaveSearchfromlist).contains(description)){
            stepPassedWithScreenshot("Able to view Saved Search Results");
            return true;
        }

    return false;
    }
    public boolean queuesSelectResult() throws InterruptedException {

      if(labSearches()){

        click(getDescriptionSaveSearchfromlist);
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        if(getText(searchResultTitle,10).contentEquals(description)){
            stepPassedWithScreenshot("Able to view Save Search of "+description);
            return true;
        }
      }
      return false;
    }

    public void findOne(By by,String input) throws InterruptedException {
        try {
         //super.findOne(by,timeout).click();
        super.findOne(by,timeout).clear();
        super.findOne(by,timeout).sendKeys(input);
        Thread.sleep(1000);
        super.findOne(by,timeout).sendKeys(Keys.TAB);
        } catch (UnhandledAlertException ignored) {
            switchToDefaultContext();
            switchToMainFrame();
            super.findOne(by,timeout).clear();
            super.findOne(by,timeout).sendKeys(input);
            super.findOne(by,timeout).sendKeys(Keys.TAB);

        }
    }




    public PathCareLabTransferList(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }


    public boolean waitForDisplayed() {
        return  false;
    }



}
