package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LabResultsEquiry extends AbstractExtension {

    private final By specimenNumber = By.xpath("//input[@name='SpecimenNumber']");
    private final By labEpisode = By.xpath("//input[@name='LBEpisodeNo']");
    private final By findButton = By.xpath("//button[text()='Find']");
    private final  By reportCollectionDate = By.xpath("//input[@name='DateFrom']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By labEnquiryMenuButton= By.xpath("//span[text()='Lab Enquiry']");
    private final By labResultEnquiry   = By.xpath("//li//span[text()='Lab Result Enquiry']");
    private final By TestSetOption   = By.xpath("//a[text()='Test Set Options']");
    private final By specimenNumberfound   = By.xpath("//label[@name='LBSPCSpecimenNumberz1']");
    private final By specimenDerscription   = By.xpath("//label[@name='LBCSPDescz1']");
   // private final By TestSetOption   = By.xpath("//a[text()='Test Set Options']");
    //private final By TestSetOption   = By.xpath("//a[text()='Test Set Options']");
    private By  episodeNumber;
    private int timeout = 20;


    public LabResultsEquiry(Roman roman) {
        super(roman);
    }
    public void navigateLabEnquiry(){
        awaitElement(mainmenu,timeout);
        click(mainmenu);
        if(validateElement_Enabled_Displayed(labEnquiryMenuButton)){
            click(labEnquiryMenuButton,timeout);
        }else{
            click(labEnquiryMenuButton);
            click(labResultEnquiry,timeout);
        }
        stepPassedWithScreenshot("lab result enquiry page appears");

    }
    public void LabResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now().minusDays(10);
        sendKeys(labEpisode,labespide,true,true,timeout);
        sendKeys(reportCollectionDate, dtf.format(todaydate));
        click(findButton,timeout);
    }

    public void testSetListSingle(String labespide) {
        episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));
        awaitElement(episodeNumber,timeout);
        stepPassedWithScreenshot("Able to view testset " + getText(episodeNumber,timeout));
        click(episodeNumber,timeout);
    }
    public boolean checkIfProtocolAdded(){

        return false;
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
