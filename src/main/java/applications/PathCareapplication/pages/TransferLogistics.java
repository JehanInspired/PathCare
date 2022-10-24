package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class TransferLogistics extends AbstractPage {

    private final By dropShipmentContainerNumbertext = By.xpath("//input[@name='DropOffNumber']");
    private final By pickUpShipment = By.xpath("//input[@name='PickUpNumber']");
    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By updatebutton = By.xpath("//button[text()='Update']");
    private final By statusDevilvered = By.xpath("//span[text()='Delivered']");

    private final By statusMessage = By.xpath("//span[@id='LBShipmentContainer_CourierLogistics_Edit_0-item-StatusMsg']");






    public TransferLogistics(Roman roman) {
        super(roman);
    }




    public boolean dropOffShipmentInvalid(String shipContainerNumber){

        findOne(dropShipmentContainerNumbertext,shipContainerNumber);
        if(validateElement_Displayed(statusMessage) && getText(statusMessage).contentEquals("The shipment container can only be dropped off at its destination lab site")){
            stepPassedWithScreenshot("Able to view An error message: The Shipment container can only be dropped off at its destination lab site.");
            validateElement_Displayed(statusMessage,10);
            return true;
        }
        return false;
    }

    public boolean dropOffShipmentValid(String shipContainerNumber){

        findOne(dropShipmentContainerNumbertext,shipContainerNumber);

        if(validateElement_Displayed(updatebutton,10)){
            stepPassedWithScreenshot("Successfully Delivered Drop Off "+ shipContainerNumber);
            click(updatebutton);
            return true;
        }

        return false;
    }

    public boolean pickUpShipmentValid(String shipContainerNumber){

        findOne(pickUpShipment,shipContainerNumber);
            if(validateElement_Displayed(updatebutton,10)){
                stepPassedWithScreenshot("Successfully Pick Up "+ shipContainerNumber);
                click(updatebutton);
                return true;
            }

        return false;
    }


    public void findOne(By by,String input) {

        validateElement_Enabled_Displayed(by,10);
        super.sendKeys(by,input,5);
        super.findOne(by).click();

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
