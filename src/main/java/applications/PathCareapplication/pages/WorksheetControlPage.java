package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.security.Key;

public class WorksheetControlPage extends AbstractExtension {


    private final By worksheetType = By.xpath("//input[@id='LBWSWorksheetDefDR']");
    //private final By worksheetType = By.xpath("//img[@id='ld5977iLBWSWorksheetDefDR']");
    private final By worksheetTypeOption = By.xpath("//td[text()='Hereditary Haemochromatosis #1']");
    private final By dateCreatedFrom = By.xpath("//input[@id='DateFrom']");
    private final By dateCreatedTo = By.xpath("//input[@id='DateTo']");
    private final By findButton = By.xpath("//input[@id='find1' and @value='Find']");
    private final By createQC_OnlyWorksheet = By.xpath("//button[@id='CreateQCOnlyWorksheet']");
    private final By printLink = By.xpath("//a[@id='LBWSPrintz1']");
    private final By editLink = By.xpath("//a[@id='LBWSEditz1']");
    private final By closeLink = By.xpath("//a[@id='LBWSClosez1']");
    private final By workSheetDescription= By.xpath("//label[@id='LBCWSDescz1']");
    private final By workSheetEntryList= By.xpath("//form[@id='fLBWorksheetEntry_List']");
    private final By closeWorkSheetEntryList= By.xpath("//span[@id='InfoPaneClose']");
    private int timeout = 30;

    public WorksheetControlPage(Roman roman) {
        super(roman);
    }

    public void enterWorksheetType(String type){
       switchToFrame(By.id("TRAK_main"));
        sendKeys(worksheetType,type,timeout);
        click(worksheetTypeOption);
    }
    public void enterCreatedDate(String fromDate, String toDate) throws InterruptedException {
        sendKeysAndTab(dateCreatedFrom,fromDate);
        sendKeys(dateCreatedTo,toDate);
    }
    public  void clickFindButton(){
        click(findButton);
    }
    public  void findWorksheetdeatils(String desc,String fromDate, String toDate ) throws InterruptedException {
        enterWorksheetType(desc);
        enterCreatedDate( fromDate, toDate);
        clickFindButton();
    }
    public  void  editWorkSheet(){

        if(_driver.findElement(workSheetDescription).isDisplayed()){
            click(editLink,timeout);
            switchToDefaultContext();
            switchToFrame(By.name("TRAK_info"));
            click(closeWorkSheetEntryList);
            switchToDefaultContext();
            switchToFrame(By.name("TRAK_main"));
        }
    }
    public  void  printWorkSheet(){
        if(_driver.findElement(workSheetDescription).isDisplayed()){
            click(printLink);
        }
    }
    //The entry for the worksheet just printed is removed from the Worksheet Control list.
    public Boolean isWorksheetPrinted(){
        return (!validateElement_Displayed(workSheetDescription,timeout));
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
