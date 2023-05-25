package applications.PathCareapplication.tool;

import Roman.Roman;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.AbstractPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class AbstractExtension extends AbstractPage {

    private final By loadingBar = By.xpath("//div[@class='bar']");

    private final By bodydom = By.xpath("//body[@ng-app='tcApp']");

    public final By mainframe = By.xpath("//iframe[@id='TRAK_main']");

    @Override
    public String getText(By by) {
        return super.getText(by);
    }

    public void awaitElement(By by,int timeout){
        await("wait for "+by.toString()+" element").atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(5L,TimeUnit.SECONDS)
                .until(()-> findOne(by, timeout).isEnabled());
    }

    public void awaitElement(WebElement element,int timeout){
        await("wait for "+element.toString()+" element").atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(5L,TimeUnit.SECONDS)
                .until(element::isEnabled);
    }

    public void javascriptClick(WebElement element){
        JavascriptExecutor jExecutor = (JavascriptExecutor) this._driver;
        jExecutor.executeScript("arguments[0].click();", element);
    }

    public void awaitEitherElement(By by,By bySecond,int timeout){
        await("wait for either" + by.toString() + "or" + bySecond.toString() + "element").atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(5L,TimeUnit.SECONDS)
                .until(() -> findOne(by, timeout).isEnabled() || (findOne(bySecond, timeout).isEnabled()));
    }

    public WebElement awaitClickableElement(By by, int timeout,int pollEverySecond){
        WebDriverWait wait = new WebDriverWait(_driver,timeout);
        return wait.ignoring(NoSuchElementException.class,TimeoutException.class).pollingEvery(Duration.ofSeconds(pollEverySecond)).until(ExpectedConditions.elementToBeClickable(by)) ;

    }

    public WebElement awaitClickableElement(WebElement element, int timeout,int pollEverySecond){
        WebDriverWait wait = new WebDriverWait(_driver,timeout);
        return wait.ignoring(NoSuchElementException.class,TimeoutException.class).pollingEvery(Duration.ofSeconds(pollEverySecond)).until(ExpectedConditions.elementToBeClickable(element)) ;

    }



    public void loadingBarChecker(){
        int counter = 0;
        int max = 30;
        if(validateElement_Displayed(loadingBar,5)) {
            while ( counter < max) {
                if(!validateElement_Displayed(loadingBar, 1)) {
                    break;
                }
                counter++;
                System.out.println("Page still loading");

            }
        }

    }

    protected void tableExtaction(By xpathTableName,String colunmHead,String tableHeader,String colunmFind,String value,int timeout){
        awaitElement(xpathTableName,timeout);
        WebElement table = findOne(xpathTableName,timeout);
        // get table headers
        // get the text
        // trim - no space
        // collect to a list
        List<String> columnNames = new ArrayList<>();
        for (WebElement element : table.findElements(By.tagName("th"))) {
            String text = element.getText();
            String trim = text.trim();
            columnNames.add(trim);
        }


        //get all rows
        boolean first = true;
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            if (first) {
                first = false;
                continue;
            }

            List<WebElement> tds = tr.findElements(By.tagName("td"));
                if (tds.get(columnNames.indexOf(colunmHead)).getText().equals(colunmFind)) {
                    WebElement td = tds.get(columnNames.indexOf(tableHeader));
                    if (!value.isBlank()) {

                        WebElement input = td.findElement(By.tagName("input"));
                        awaitElement(input, timeout);
                        input.clear();
                        awaitElement(input, timeout);
                        input.sendKeys(value.trim());
                        awaitElement(input, timeout);
                        if(validateElement_Enabled_Displayed(By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='"+value+"']"),timeout)){
                            click(By.xpath("//tr[contains(@id,'LookupRow')]//td[text()='"+value+"']"),timeout);
                        }else if(validateElement_Enabled_Displayed(By.xpath("//tr[contains(@id,'LookupRow')]"),timeout)){
                            click(By.xpath("//tr[contains(@id,'LookupRow')]"),timeout);
                        }
                        input.sendKeys(Keys.TAB);

                        stepInfo("Entered " + value + " under " + tableHeader +" "+colunmFind);
                    } else {
                        WebElement input = td.findElement(By.tagName("a"));
                        awaitElement(input, timeout);
                        awaitClickableElement(input, timeout, 3).click();
                        stepInfo("Clicked " + value + " under " + tableHeader);
                    }

                }

        }
    }

    protected void tableExtactionIndex(By xpathTableName,String tableHeader,String colunmFind,int timeout){
        WebElement table = findOne(xpathTableName,timeout);
        // get table headers
        // get the text
        // trim - no space
        // collect to a list
        List<String> columnNames = new ArrayList<>();
        for (WebElement element : table.findElements(By.tagName("th"))) {
            String text = element.getText();
            String trim = text.trim();
            columnNames.add(trim);
        }


        //get all rows
        boolean first = true;
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            if (first) {
                first = false;
                continue;
            }

            List<WebElement> tds = tr.findElements(By.tagName("td"));
            for(WebElement element:tds) {
                if (element.getText() != null) {
                    if(element.getText().contentEquals(colunmFind)) {
                            WebElement td = tds.get(columnNames.indexOf(tableHeader));
                            WebElement input = td.findElement(By.tagName("a"));
                            awaitElement(input, timeout);
                            awaitClickableElement(input, timeout, 3).click();
                    }

                }
            }
        }
    }

    public void sendKeysAndTab(By element,String text){
        sendKeys(element,text);
        super._driver.findElement(element).sendKeys(Keys.TAB);
    }
    public void sendKeysAndTab(By element,String text,int timeOut){
        sendKeys(element,text);
        super._driver.findElement(element).sendKeys(Keys.TAB);
    }
    public void clickAndTab(By element){
        click(element);
        super._driver.findElement(element).sendKeys(Keys.TAB);
    }
    public void switchToMainFrame() {
        super.switchToFrame(mainframe);
    }

    public AbstractExtension(Roman roman) {
        super(roman);
    }


}
