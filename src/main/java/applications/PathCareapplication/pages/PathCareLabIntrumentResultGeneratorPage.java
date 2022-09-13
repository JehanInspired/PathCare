package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

public class PathCareLabIntrumentResultGeneratorPage extends AbstractPage {

    private final By intrumentTextBox = By.xpath("//input[@id='LBCInstrument']");
    private final By testGroup = By.xpath("//input[@id='LBCInstrumentTestGroup']");
    private final By speciemenNumber = By.xpath("//input[@id='SpecimenNumber']");
    private final By resultsTB = By.xpath("//input[@id='Resultz1']");

    private final By generateButton = By.xpath("//input[@id='generate1']");
    private final By selecttickbox = By.xpath("//input[@id='Selectz1']");
    private final By iframeMain = By.xpath("//iframe[@id='TRAK_main']");

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
           stepPassedWithScreenshot("Able to view outputs");
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
