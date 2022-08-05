package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

public class PathCareLabSpecimenReception extends AbstractPage {


    private By specimenNumberText = By.xpath("//input[@name='SpecimenNumber']");
    private By SpecimenNumberUpdateButton = By.xpath("//input[@name='update']");

    private By textEpisode = By.xpath("//label[@name='LabEpisodeNumber']");




    public String entryLabspecimenReceptionNoepisode(String labspecnumber){

        //javaScriptExecutor("document.getElementByName=('SpecimenNumber').value ="+labspecnumber+"-1");

        sendKeys(specimenNumberText,labspecnumber,true,true);
        click(specimenNumberText);
        click(SpecimenNumberUpdateButton);
        return getText(textEpisode);


    }




    public void findOne(By by,String input) {
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }

    public PathCareLabSpecimenReception(Roman roman) {
        super(roman);
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
