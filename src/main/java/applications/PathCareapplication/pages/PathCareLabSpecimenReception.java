package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

import java.util.Arrays;

public class PathCareLabSpecimenReception extends AbstractPage {


    private final By specimenNumberText = By.xpath(" //td/input[@id='SpecimenNumber']");
    private final By specimenNumberUpdateButton = By.xpath("//input[@name='update']");

    private final By specimenNumberStatus = By.xpath("//label[@name='Status']");

    private final By specimenframe = By.xpath("//iframe[@id='TRAK_main']");
    private final By textEpisode = By.xpath("//label[@name='LabEpisodeNumber']");





    public String entryMultipleLabspecimenReception(String labspecnumber,int number){
        switchToFrame(specimenframe);
        String[] values = new String[number];
        for( int x=1;x<=number;x++) {

            String[] split = labspecnumber.split("-");
            String value = split[0].concat("-".concat(String .valueOf(x)));
            findOne(specimenNumberText,value);
            stepPassedWithScreenshot("Successfully updated Lab Specimen under Lab episode: " + value);
            values[x-1]=value;

        }
        click(specimenNumberUpdateButton);
        for(String value:values){
        redoentryLabspecimen(value);
        stepPassedWithScreenshot( value);
        }
        return getText(specimenNumberStatus).replace(" "+values[Arrays.asList(values).size()-1],"");

    }

    public void redoentryLabspecimen(String labspecnumber){
        findOne(specimenNumberText,labspecnumber);
        click(specimenNumberUpdateButton);
    }




    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
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
        return validateElement_Enabled_Displayed(specimenNumberText,15);
    }


}
