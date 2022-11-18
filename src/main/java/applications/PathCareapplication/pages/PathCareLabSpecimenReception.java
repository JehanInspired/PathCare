package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.CytologyNon_GynaeSpecimen;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.AbstractPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PathCareLabSpecimenReception extends AbstractPage {


    private final By specimenNumberText = By.xpath("//input[@id='SpecimenNumber']");
    private final By specimenNumberUpdateButton = By.xpath("//input[@name='update']");

    private final By specimenNumberStatus = By.xpath("//label[@name='Status']");

    private final By specimenframe = By.xpath("//iframe[@id='TRAK_main']");
    private final By textEpisode = By.xpath("//label[@name='LabEpisodeNumber']");
    private final By tobereceivedcontain = By.xpath("//label[@id='SpecimenNumberTBRz1']");

    public ArrayList<String> specimenNumbers = new ArrayList<>();
    public int timer = 15;
    public HashMap<String,ArrayList<String>> mutlipleSpeicmen = new HashMap<>();

    public String entryMultipleLabspecimenSingleReception(String labspecnumber){

        switchToDefaultContext();
        switchToFrame(specimenframe);
        int numtestset = 1;
            for (int x = 1; x <= numtestset; x++) {
                String value = labspecnumber.concat("-".concat(String.valueOf(x)));

                findOne(specimenNumberText, value);
                int numberSpeicmen = find(By.xpath("//label[contains(text(),'%s')]".replace("%s",labspecnumber+"-"))).size();
                if(numberSpeicmen!=0 && x==1){
                    numtestset=numberSpeicmen;
                }

                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                if (!validateElement_Enabled_Displayed(tobereceivedcontain)) {
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


    public HashMap<String, ArrayList<String>> mutlipleSpeicmen_Patientmultiple(List<String> labspecnumber){

        switchToDefaultContext();
        switchToFrame(specimenframe);
        int numtestset = 1;

        for(String labepsiode : labspecnumber) {

            for (int x = 1; x <= numtestset; x++) {
                String lapnumberspecimen = labepsiode.concat("-".concat(String.valueOf(x)));
                findOne(specimenNumberText, lapnumberspecimen);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + lapnumberspecimen);
                specimenNumbers.add(lapnumberspecimen);
                int numberSpeicmen = find(By.xpath("//label[contains(text(),'%s')]".replace("%s",labepsiode+"-"))).size();
                if(numberSpeicmen!=0 && x==1){
                    numtestset=numberSpeicmen;
                }

                if(!validateElement_Enabled_Displayed(tobereceivedcontain)) {
                    click(specimenNumberUpdateButton);
                    mutlipleSpeicmen.put(labepsiode, specimenNumbers);
                    break;
                }

                if (!validateElement_Enabled_Displayed(specimenNumberUpdateButton,timer)) {
                    Assert.fail("Unable to Update Lab Specimen");
                }
               // specimenNumbers.add(getText(By.xpath("//label[@id='TestSetListz']".replace("TestSetListz","TestSetListz"+x)),10));
                mutlipleSpeicmen.put(labepsiode, specimenNumbers);

            }

            specimenNumbers = new ArrayList<>();
        }

        for(String value:specimenNumbers){
            redoentryLabspecimen(value);
            validateElement_Enabled_Displayed(textEpisode,timer);
            stepPassedWithScreenshot(value);
        }

        return mutlipleSpeicmen;
    }

    public void specimenReceiveCreated(ArrayList<ArrayList<String>> specimen) {
        switchToDefaultContext();
        switchToFrame(specimenframe);

        for(ArrayList<String> values :specimen) {
            for (String value : values) {
                findOne(specimenNumberText, value);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                }
                click(specimenNumberUpdateButton);
            }

    }

    public void specimenReceiveCreated(ArrayList<ArrayList<String>> specimen,HashMap<String,List<String>> specimenDetail,boolean Non_GynaeSpecimen)  {
        switchToDefaultContext();
        switchToFrame(specimenframe);
        int x=1;
        String num = "";
        for(ArrayList<String> values :specimen) {

            for (String value : values) {
                findOne(specimenNumberText, value);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                if(Non_GynaeSpecimen) {
                        num = "1";
                    if (String.valueOf(x).contentEquals("2")) {
                        num = "2";
                    } else if (String.valueOf(x).contentEquals("3")) {
                        num = "2.1";
                    } else if (String.valueOf(x).contentEquals("4")) {
                        num = "3.1";
                    } else if (String.valueOf(x).contentEquals("5")) {
                        num = "3.2";
                    }
                }else{
                     num = "Lab Episode "+x;
                }

                enteringValueSpecimenDetail(specimenDetail,num,value);

                stepInfoWithScreenshot("Successfully Entered Specimen details: " + value);
                x++;
                }
            click(specimenNumberUpdateButton,timer);
        }
        switchToDefaultContext();
    }

    public void enteringValueSpecimenDetail (HashMap<String,List<String>> specimenDetail,String num,String value){
        int y = 0;
        String speicmenvalue1 ="";
        for (String speicmenvalue : specimenDetail.get(num)) {
            By elment = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[not(@type='hidden')and @value='' and contains(@id,'AnatomicalSite') or contains(@id,'Lesionz')]".replace("%s",value));
            By lookupGlass = By.xpath("//parent::td[label[text()='%s']]//parent::tr//img[@class='clsLookupIcon' and contains(@name,'AnatomicalSiteQualifier') or contains(@name,'Lesion') or contains(@name,'AnatomicalSite')]".replace("%s",value));
            if (y == 0) {
                if(!speicmenvalue.isBlank()) {
                    find(elment).get(y).sendKeys(speicmenvalue);
                    if (find(elment).get(y).getAttribute("value").isBlank()) {
                        find(elment).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass).get(y).click();
                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)), timer)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }
                }
                y++;
            } else if (y == 1) {
                speicmenvalue1 =speicmenvalue;
                if(!speicmenvalue.isBlank()) {
                    find(elment).get(y).sendKeys(speicmenvalue);
                    if (find(elment).get(y).getAttribute("value").isBlank()) {
                        find(elment).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass).get(y).click();
                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)), timer)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }
                }
                y++;
            } else if (y == 2) {
                if(!speicmenvalue.isBlank()) {
                    find(elment).get(y).sendKeys(speicmenvalue);
                    if (find(elment).get(y).getAttribute("value").isBlank()) {
                        find(elment).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass).get(y).click();

                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)), timer)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }

                    if(!speicmenvalue1.isBlank()) {
                        find(lookupGlass).get(y - 1).click();
                        if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue1)), timer)) {
                            click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue1)));
                        }
                    }
                }
                break;
            }
        }
    }

    public void redoentryLabspecimen(String labspecnumber){
        findOne(specimenNumberText,labspecnumber);
        click(specimenNumberUpdateButton,timer);
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
        WebDriverWait wait = new WebDriverWait(super._driver,40L);
        wait.pollingEvery(Duration.ofSeconds(7L));
        return wait.until(EventFiringWebDriver::new).findElement(specimenNumberText).isEnabled();
    }


}
