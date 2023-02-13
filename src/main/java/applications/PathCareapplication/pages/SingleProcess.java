package applications.PathCareapplication.pages;

import Roman.Roman;

import applications.PathCareapplication.tool.AbstractExtension;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.AbstractPage;


import java.time.Duration;


public class SingleProcess extends AbstractExtension {

    private final By iframeProcessing = By.xpath("//iframe[@id='TRAK_main']");


    private final By pinserttestreults = By.xpath("//img[contains(@id,'Value')]");
    private final By applyTestResult = By.xpath("//input[@id='apply1']");
    private final By updateTestResult = By.xpath("//input[@id='update1']");
    private final By orgramismText = By.xpath("//input[@id='LBTSI_Valuez1']");
    private final By div = By.xpath("//div");
    private final By pathogentextbox = By.xpath("//input[contains(@id,'DRz1') and (not(contains(@type,'hidden')))]");
    private final By organismText = By.xpath("//div[contains(@id,'LBTSIValue')]/p");
    private By organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']");
    private final By closelookup = By.xpath("//span[@id='OverlayCloseLookupOverlayDiv']");
    private final By manglass = By.xpath("//img[contains(@id,'lt8560iLBTSI_PathogenGrowthQualifier_DRz1')]");

    public void SingleProcessingTestSet(String organism,String organisms, String[] testresults) throws InterruptedException {
        int x = 0;
        switchToDefaultContext();
        switchToFrame(iframeProcessing);

        for (WebElement element : find(organismText)) {
            element.clear();
        }

        for (WebElement element : find(pinserttestreults)) {
            click(pathogentextbox);
            element.click();
            Thread.sleep(4000);
            organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", testresults[x++]));
            if (x == 3) {
                click(closelookup);
                break;

            }

            int y =0;
            while(!validateElement_Enabled_Displayed(organismTextfield, 10)) {
                _driver.findElement(pathogentextbox).sendKeys(Keys.TAB);
                element.click();
                if(y==6){
                    Assert.fail("Unable to find "+organismTextfield.toString());
                }
                y++;
            }

            click(organismTextfield);

        }

        if(!organisms.isEmpty()) {
            click(orgramismText);
            sendKeys(orgramismText, organisms);
            super.findOne(orgramismText).sendKeys(Keys.TAB);
        }

        if (!organism.isEmpty()){
            click(manglass);
        organismTextfield = By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='%s']".replace("%s", organism));
        click(organismTextfield);
        Thread.sleep(3000);
        click(applyTestResult);
        Thread.sleep(3000);
    }

        stepPassedWithScreenshot("Successfully Entered Results " + organism +" "+organisms);


        click(applyTestResult);

        stepPassedWithScreenshot("Successfully Applied Test Result");

    }



    public SingleProcess(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {

        WebDriverWait wait = new WebDriverWait(super._driver,30L);
        wait.pollingEvery(Duration.ofSeconds(10L));
        return wait.until(EventFiringWebDriver::new).getWindowHandles().size() == 2;

    }

}
