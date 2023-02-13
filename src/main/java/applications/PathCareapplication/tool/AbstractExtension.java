package applications.PathCareapplication.tool;

import Roman.Roman;
import org.openqa.selenium.*;
import selenium.AbstractPage;

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

    public void awaitEitherElement(By by,By bySecond,int timeout){
        await("wait for either" + by.toString() + "or" + bySecond.toString() + "element").atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(5L,TimeUnit.SECONDS)
                .until(() -> findOne(by, timeout).isEnabled() || (findOne(bySecond, timeout).isEnabled()));
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


    public void switchToMainFrame() {
        super.switchToFrame(mainframe);
    }

    public AbstractExtension(Roman roman) {
        super(roman);
    }


}
