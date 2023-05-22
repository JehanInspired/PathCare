package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;

public class WorksheetControlPage extends AbstractExtension {


    private final By worksheetType = By.xpath("//input[@id='LBWSWorksheetDefDR']");
    private final By dateCreatedFrom = By.xpath("//input[@id='DateFrom']");
    private final By dateCreatedTo = By.xpath("//input[@id='DateTo']");
    private final By findButton = By.xpath("//button[@id='find1' and @value='Find']");
    private final By createQC_OnlyWorksheet = By.xpath("//button[@id='CreateQCOnlyWorksheet']");
    private final By printLink = By.xpath("//a[@id='LBWSPrintz1']");
    private final By editLink = By.xpath("//a[@id='LBWSEditz1']");
    private final By closeLink = By.xpath("//a[@id='LBWSClosez1']");
    private int timeout = 15;

    public WorksheetControlPage(Roman roman) {
        super(roman);
    }

    public void enterWorksheetType(String desc){
        sendKeys(worksheetType,desc);
    }
    public void enterCreatedDate(String fromDate, String toDate){
        sendKeys(dateCreatedFrom,fromDate);
        sendKeys(dateCreatedTo,toDate);
    }
    public  void clickFindButton(){
        click(findButton);
    }
    public  void clickCreateQC_onlyWorksheetButton(){
        click(createQC_OnlyWorksheet,timeout);
    }
    public  void clickEditLink(){
        click(editLink,timeout);
    }
    public  void clickPrintLink(){
        click(printLink,timeout);
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
