package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

import java.util.HashMap;

public class PathCareLabIntrumentResultGeneratorPage extends AbstractPage {

    private final By intrumentTextBox = By.xpath("//input[@id='LBCInstrument']");
    private final By testGroup = By.xpath("//input[@id='LBCInstrumentTestGroup']");
    private final By speciemenNumber = By.xpath("//input[@id='SpecimenNumber']");
    private final By resultsTB = By.xpath("//input[@id='Resultz1']");

    private final By generateButton = By.xpath("//input[@id='generate1']");
    private final By selecttickbox = By.xpath("//input[@id='Selectz1']");
    private final By iframeMain = By.xpath("//iframe[@id='TRAK_main']");

    private final By nextpage = By.xpath("//a[contains(@id,'Next')]");

    private final By textarea = By.xpath("//textarea");





    public void intrumentTestGroup(String nameOfIntrument, String testgroupvalue, String materialNumber,String results) throws InterruptedException {

        switchToDefaultContext();
        switchToFrame(iframeMain);

        findOne(intrumentTextBox,nameOfIntrument);
        Thread.sleep(3000);
        findOne(testGroup,testgroupvalue);
        Thread.sleep(3000);
        findOne(speciemenNumber,materialNumber);
        Thread.sleep(3000);
        findOne(resultsTB,results);
        Thread.sleep(3000);
        click(selecttickbox);
        click(generateButton,10);

       if(!getText(textarea).contains("Test Item:")){
           stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
       }


    }

    public void testitemListGroup(HashMap<String ,String> testitems,String nameOfIntrument, String testgroupvalue, String materialNumber) throws InterruptedException {

        switchToDefaultContext();
        switchToFrame(iframeMain);

        findOne(intrumentTextBox,nameOfIntrument);
        if(!getText(intrumentTextBox).isBlank()){
            stepPassedWithScreenshot("Entered instrument test item "+nameOfIntrument);
        }
        Thread.sleep(3000);
        findOne(testGroup,testgroupvalue);
        if(!getText(testGroup).isBlank()){
            stepPassedWithScreenshot("Entered test group test item "+testgroupvalue);
        }
        Thread.sleep(3000);
        findOne(speciemenNumber,materialNumber);
        if(!getText(speciemenNumber).isBlank()){
            stepPassedWithScreenshot("Entered material number test item "+materialNumber);
        }
        Thread.sleep(3000);

        for(String value:testitems.keySet()){
            By testitem = By.xpath("//label[contains(text(),'"+value+"')]");

            if(!validateElement_Displayed(testitem)){
                while(!validateElement_Displayed(testitem)){
                    click(nextpage);
                }
            }

        }

        if(getText(intrumentTextBox).isBlank()) {
            findOne(intrumentTextBox, nameOfIntrument);
            Thread.sleep(3000);
        }
        if(getText(testGroup).isBlank()) {
            findOne(testGroup, testgroupvalue);
            Thread.sleep(3000);
        }
        if(getText(speciemenNumber).isBlank()) {
            findOne(speciemenNumber, materialNumber);
            Thread.sleep(3000);
        }

        for(String value:testitems.keySet()){

            By testiteminputboxes = By.xpath("//td[preceding-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            find(testiteminputboxes).get(1).sendKeys(testitems.get(value));
            stepPassedWithScreenshot("Entered Results test item "+value);
            By testitemtickbox = By.xpath("//td[following-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            click(testitemtickbox);
            stepPassedWithScreenshot("Ticked test item "+value);

        }

        click(generateButton,10);

        if(!getText(textarea).contains("Test Item:")){
            stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
        }


    }





    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }


    public PathCareLabIntrumentResultGeneratorPage(Roman roman) {
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
