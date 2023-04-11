package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

public class LabEnquiry extends AbstractExtension {

    private final By mainMenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By labResultEnquiry = By.xpath("//li//span[text()='Lab Result Enquiry']");
    private final By labEnquiry = By.xpath("//li//span[text()='Lab Enquiry']");
    private final By workSheetREsEntry = By.xpath("//li//span[text()='Worksheet Res. Entry']");
    private  final By analytical = By.xpath("//li//span[text()='Analytical']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By proceduresButton = By.xpath("//span[text()='Procedures']");
    private int timeout = 15;

    public LabEnquiry(Roman roman) {
        super(roman);
    }

    public void navigatelabEnquiry(){
        click(mainMenu);
        if(validateElement_Enabled_Displayed(labEnquiry)){
            click(labResultEnquiry,timeout);
            stepInfo("Clicked Lab Result Enquiry");
        }else{
            click(labEnquiry);
            click(labResultEnquiry,timeout);
            stepInfo("Clicked Lab Result Enquiry");
        }
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
