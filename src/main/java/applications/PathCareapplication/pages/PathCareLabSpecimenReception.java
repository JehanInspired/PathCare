package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class PathCareLabSpecimenReception extends AbstractPage {


    private final By specimenNumberText = By.xpath(" //td/input[@id='SpecimenNumber']");
    private final By specimenNumberUpdateButton = By.xpath("//input[@name='update']");

    private final By specimenNumberStatus = By.xpath("//label[@name='Status']");

    private final By specimenframe = By.xpath("//iframe[@id='TRAK_main']");
    private final By textEpisode = By.xpath("//label[@name='LabEpisodeNumber']");

    private final By tobereceivedcontain = By.xpath("//label[@id='SpecimenNumberTBRz1']");
    public List<String> specimenNumbers = new ArrayList<>();





    public String entryMultipleLabspecimenReception(String labspecnumber,int number){
        switchToFrame(specimenframe);

        for(int x=1;x<=number;x++) {

            String value = labspecnumber.concat("-".concat(String .valueOf(x)));

            findOne(specimenNumberText,value);
            stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
            if(!validateElement_Enabled_Displayed(tobereceivedcontain)){
                specimenNumbers.add(value);
                break;
            }
            specimenNumbers.add(value);
        }

        click(specimenNumberUpdateButton);
        for(String value:specimenNumbers){
        redoentryLabspecimen(value);
        validateElement_Enabled_Displayed(textEpisode,5);
        stepPassedWithScreenshot(value);
        }
        return getText(specimenNumberStatus).replace(" "+specimenNumbers.get(specimenNumbers.size()-1),"");

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
