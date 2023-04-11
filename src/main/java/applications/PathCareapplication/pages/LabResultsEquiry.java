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



    private By  episodeNumber;
    private int timeout = 20;



    public LabResultsEquiry(Roman roman) {
        super(roman);
    }

    public void LabResultsEntry(String labespide){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todaydate = LocalDate.now();
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
    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
        return false;
    }


}
