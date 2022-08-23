package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

import java.util.List;

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
          stepPassedWithScreenshot("Able to view package In Transit ");
          checker =true;
      }
        return checker;
  }
    public Boolean checknumbersDelivery(String labepisode, List<String> specimennumbers){
        boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size()) {
            stepPassedWithScreenshot("Able to view package In Transit ");
            checker = true;
        }
        return checker;
    }

    public Boolean checknumbersPackage(String labepisode, List<String> specimennumbers){
      boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size()) {
            stepPassedWithScreenshot("Able to view Packaged ");
            checker =true;
        }
        return checker ;
    }

    public Boolean checknumbersReceived(String labepisode, List<String> specimennumbers){
        boolean checker =false;
        listTransfer = By.xpath("//label[text()='%s']".replace("%s",labepisode));
        if(getAllElementText(listTransfer).size()==specimennumbers.size() && getAllElementText(status).contains("Received")) {
            stepPassedWithScreenshot("Able to view Received ");
            checker =true;
        }
        return checker ;
    }
    public void receiceiveShipment(){
        click(selectallspecimen);
      click(receiveButton);

    }

  public void createShipment(List<String> specimennumbers){
      click(selectallspecimen);
      click(shipmentbutton);
      click(addshipmentcontainerbutton,10);
      if(validateElement_Displayed(shipmentnumbertext,10)){
          shipmentNumber = getText(shipmentnumbertext,10);

          stepPassedWithScreenshot("Able to receive a shipment container number "+ shipmentNumber);
      }

      for(String value:specimennumbers) {
          findOne(packSpecimenNumber,value);
      }
      click(closeShipment,5);
      acceptAlert();
      stepPassedWithScreenshot("Successfully clicked closed shipment");

  }

  public void closePackage(){
    click(closepacksession);
    acceptAlert();
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
