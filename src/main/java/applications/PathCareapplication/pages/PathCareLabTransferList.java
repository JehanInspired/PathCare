package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.*;
import selenium.AbstractPage;

import java.util.*;

public class PathCareLabTransferList extends AbstractPage {


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
    private String description= "";
    private String url = "";

  public String shipmentNumber = "";

  public void tranferlistLabepisode(String text){
      switchToFrame(switchiFrame);
      sendKeys(labEpisode,text,10);
      if(validateElement_Enabled_Displayed(findbutton,10)){
      click(findbutton,10);
      stepPassedWithScreenshot("Successfully Entered lab Episode");
      }
  }

    public void tranferlistLabepisodewithoutframe(String text){
        sendKeys(labEpisode,text,10);
        if(validateElement_Enabled_Displayed(findbutton,10)){
            click(findbutton,10);
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
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size() && getAllElementText(status).contains("Packed")) {
            stepPassedWithScreenshot("Able to view Packaged "+labepisode);
            checker = true;
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
      click(addshipmentcontainerbutton,10);
      if(validateElement_Displayed(shipmentnumbertext,10)){
          shipmentNumber = getText(shipmentnumbertext,10);
          stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
      } else{
          stepInfoWithScreenshot("Unable to to receive a shipment container number");
          Assert.fail("Unable to to receive a shipment container number");
      }

      for(String value:specimennumbers) {
          findOne(packSpecimenNumber,value);
      }
      click(closeShipment,5);
      acceptAlert();
      Thread.sleep(3000);
      stepPassedWithScreenshot("Successfully clicked closed shipment");

  }

    public void createShipment(Collection<ArrayList<String>> specimenNumbers, boolean selectAll){
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
            }
        }
        click(closeShipment,5);
        acceptAlert();
        stepPassedWithScreenshot("Successfully clicked closed shipment");

    }

  public void closePackage(){
    switchToDefaultContext();
    switchToFrame(switchiFrame);
    if(validateElement_Enabled_Displayed(closepacksession,10)){
          click(closepacksession, 10);
      }else{
        Assert.fail("Unable to click close pack session button");
    }
    acceptAlert();
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
      click(findbutton);
  }

  public void selectlistlabespido(ArrayList<String> labEpisode){
      for(String value:labEpisode){
          int y=0;
         By labEpisodefield = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[contains(@id,'Selectz')]".replace("%s",value));

         while(!validateElement_Enabled_Displayed(labEpisodefield,10)){
             if(validateElement_Displayed(nextpageTransferlist)){
                 click(nextpageTransferlist);
             }else{
                 break;
             }

         }
         for(WebElement element: find(labEpisodefield)){

             element.click();
             stepInfoWithScreenshot("Ticked Lab Episode "+value+" "+y++);
         }
      }
  }

  //Save Searches
    public Boolean labSearches(){

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
        if(getText(getDescriptionSaveSearchfromlist).contains(description)){
            stepPassedWithScreenshot("Able to view Saved Search Results");
            return true;
        }

    return false;
    }
    public boolean queuesSelectResult(){

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

    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
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
