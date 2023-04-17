package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LabEnquiry extends AbstractExtension {

    private final By mainMenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By labResultEnquiry = By.xpath("//li//span[text()='Lab Result Enquiry']");

    private final By labEnquiry = By.xpath("//li//span[text()='Lab Enquiry']");
    private final By labEpisode = By.xpath("//input[@name='LBEpisodeNo']");
    private final  By backtotestSetList = By.xpath("//a[@class='breadcrumbBack']");
    private final By findButton = By.xpath("//button[text()='Find']");
    private final By workSheetREsEntry = By.xpath("//li//span[text()='Worksheet Res. Entry']");
    private  final By analytical = By.xpath("//li//span[text()='Analytical']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By proceduresButton = By.xpath("//span[text()='Procedures']");
    private final  By reportCollectionDate = By.xpath("//input[@name='DateFrom']");
    private int timeout = 15;

    public LabEnquiry(Roman roman) {
        super(roman);
    }

    public void navigatelabEnquiry(){
        awaitElement(labResultEnquiry,timeout);
        click(mainMenu);
        if(validateElement_Enabled_Displayed(labResultEnquiry)){
            click(labResultEnquiry,timeout);
            stepInfo("Clicked Lab Result Enquiry");
        }else{
            click(labEnquiry);
            awaitElement(labResultEnquiry,timeout);
            click(labResultEnquiry,timeout);
            stepInfo("Clicked Lab Result Enquiry");
        }
    }

    public void backtoTestSetList() {
        click(backtotestSetList, timeout);
        click(backtotestSetList, timeout);
    }


        public void labResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now().minusDays(10);
        awaitElement(labEpisode,timeout);
        sendKeys(labEpisode,labespide,true,true,timeout);
        sendKeys(reportCollectionDate, dtf.format(todaydate));
        click(findButton,timeout);
    }

    public void testSetListSingle(String labespide) {
        By episodeNumber = By.xpath("//span[contains(text(),'%s')]".replace("%s", labespide));
        awaitElement(episodeNumber,timeout);
        stepPassedWithScreenshot("Able to view testset " + getText(episodeNumber,timeout));
        click(episodeNumber,timeout);


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
