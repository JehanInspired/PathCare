package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.*;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;



public class PathCareLabWorkSheetResEntry extends AbstractExtension {


    private final By inputWorkSheetDefinition = By.xpath("//input[@id='LBCWorksheetDef']");
    private final By inputWorkSheet = By.xpath("//input[@id='LBWorksheet']");
    private final By mainmenu = By.xpath("//a//md-icon[@title='Main Menu']");
    private final By apply  = By.xpath("//input[@value='Apply']");
    private final By showfinal = By.xpath("//input[@id='ShowFinal']");
    private final By openWorksheets = By.xpath("//input[@id='ShowOpen']");
    private final By findButton = By.xpath("//input[@id='find1']");
    private final By worksheetLookUp = By.xpath("//img[contains(@id,'LBWorksheet')]");

    private final By openWorksheet = By.xpath("//tr[contains(@lookupallvalues,'^Open^^')]");
    private final By applyButton = By.xpath("//input[@value='Apply']");
    private final By validateButton = By.xpath("//input[@id='validate1']");
    private final By workSheetLookUpSelectionOpen = By.xpath("//tr[contains(@id,'LookupRow') and contains(@lookupallvalues,'Open')]");
    private final By validityDateText = By.xpath("//input[@name='ValidityDate']");
    private final By validityTime = By.xpath("//input[@name='ValidityTime']");

    private final By tableName = By.xpath("//table[@class='tblListSelect']");
    private final By closebutton = By.xpath("//input[@id='close1']");
    private final By attachmentsLink = By.xpath("(//a[@id='Attachments'])[2]");
    private final By chooseFile = By.xpath("//input[@id='File']");
    private final By acceptFile = By.xpath("//input[@id='accept1']");
    private final By uploadFileTestSet = By.xpath("//input[@id='TestSetz1']");
    private final By episodeLink = By.xpath("//a[@id='MedVaLEditz1']");
    private final By testSetAttachments = By.xpath("//div[@id='slideContainer']");
    private final By worksheetResEntry = By.xpath("//span[@id='tc_Breadcrumbs-menuCaption' and text()='Worksheet Res. Entry']");
    private String locationNew = "";

    private Boolean firstTime = true;
    private int timeout = 20;

    public  void  findWorksheetDefinition(String worksheetDef){
       sendKeysAndTab(inputWorkSheetDefinition,worksheetDef,timeout);
       clickAndTab(worksheetLookUp);
    }
    public  void  ClickApplyButton(){
        click(apply);
    }
    public  void  ClickEpisodeLinkAndCheckAttachedDocs(){
        click(episodeLink);
        if(_driver.findElement(testSetAttachments).isDisplayed()){
            _driver.navigate().back();
        }
    }
    public boolean worksheetResEntryDisplayed(){
       return  _driver.findElement(worksheetResEntry).isDisplayed();
    }
    public  void  uploadWorksheetDocument(String path,String testSet){
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+path+".xlsx");
        switchToFrame(By.id("TRAK_info"));
        sendKeys(chooseFile,file.toString());
        sendKeys(uploadFileTestSet,testSet,timeout);
        click(acceptFile,timeout);
    }
    public void workSheetEntry(List<WorkSheetResultEntry> workSheet,List<WorkSheetResultValues> sheetResultValues,
                               List<SpecimenReceiveEntity> specimenReceiveEntities,Analytical analytical,
                               InterSystemLoginPage interSystemLoginPage) throws InterruptedException {

       for(WorkSheetResultEntry workSheetSingle:workSheet) {

            //Change location
            changeLocation(workSheetSingle.getUserprofile_FK(),interSystemLoginPage,analytical);
            //need to fill in
            changeWorksheetDeFinition(workSheetSingle);

            for(SpecimenReceiveEntity specimenReceiveEntity:specimenReceiveEntities){
                if(workSheetSingle.getSpecimenReceive_FK().contentEquals(specimenReceiveEntity.getPk())) {
                    workSheetSingle.setSpecimenReceive_FK(specimenReceiveEntity.getSpecimenNumber());


                   for (WorkSheetResultValues workSheetResultValues : sheetResultValues) {
                        if (workSheetResultValues.getWorkSheetResultKey_FK() != null) {

                            if (workSheetResultValues.getWorkSheetResultKey_FK().contentEquals(workSheetSingle.getWorkSheetResult_Key())) {
                                for (String qcKey : workSheetResultValues.getTestvalues().keySet()) {
                                    if(qcKey != null || !qcKey.isBlank() && (workSheetResultValues.getTestvalues().get(qcKey) != null || !workSheetResultValues.getTestvalues().get(qcKey).isBlank())) {

                                       // stepInfo("Entering Field "+qcKey);
                                        tableExtaction(tableName, "Specimen No", qcKey.trim(), workSheetSingle.getSpecimenReceive_FK(), workSheetResultValues.getTestvalues().get(qcKey) == null ? "" : workSheetResultValues.getTestvalues().get(qcKey).trim(), timeout);
                                    }
                                }
                                 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate todaydate = LocalDate.now();
                                findOne(validityDateText, dtf.format(todaydate));
                                findOne(validityTime, "n");
                                click(applyButton, timeout);

                            }

                        }
                    }
                }
            }

            for(WorkSheetResultValues workSheetValues:sheetResultValues){
                    if(workSheetValues.getWorkSheetResultKey_FK()==null){
                        if(workSheetValues.getQc() != null || !workSheetValues.getQc().isBlank()) {
                            for (String qcKey : workSheetValues.getTestvalues().keySet()) {
                                if(qcKey != null || !qcKey.isBlank()) {
                                    tableExtaction(tableName, "Specimen No", qcKey.trim(), workSheetValues.getQc().trim(), workSheetValues.getTestvalues().get(qcKey) == null ? "" : workSheetValues.getTestvalues().get(qcKey).trim(), timeout);

                                }
                            }

                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate todaydate = LocalDate.now();
                            findOne(validityDateText, dtf.format(todaydate));
                            findOne(validityTime, "n");
                            click(applyButton, timeout);
                        }
                    }
            }

        }

        try {
            for(WorkSheetResultEntry workSheetSingle:workSheet){
                changeLocation(workSheetSingle.getUserprofile_FK(),interSystemLoginPage,analytical);
                changeWorksheetDeFinition(workSheetSingle);
                switchToDefaultContext();
                switchToMainFrame();
                stepInfo("Preparing to tick Specimen " + workSheetSingle.getSpecimenReceive_FK());
                        click(By.xpath("//parent::td[label[text()='" + workSheetSingle.getSpecimenReceive_FK() + "']]//parent::tr//td//input[contains(@id,'Selectz')]"), timeout);
                        stepInfo("Ticked Specimen " + workSheetSingle.getSpecimenReceive_FK());
                awaitElement(validateButton,timeout);
                click(validateButton,timeout);
                click(closebutton,timeout);
            }


        } catch (NoAlertPresentException ignored) {
            acceptAlert();
        }

    }
    void changeWorksheetDeFinition(WorkSheetResultEntry workSheetSingle) throws InterruptedException {
        switchToMainFrame();
        awaitClickableElement(inputWorkSheetDefinition, timeout,2).click();
        findOne(inputWorkSheetDefinition,workSheetSingle.getWorksheetDefinition());
        if(workSheetSingle.getShowOpenWorksheet().toLowerCase().contains("yes")){
            click(openWorksheets);
        }
        if(workSheetSingle.getShowOpenWorksheet().toLowerCase().contains("yes")){
            click(showfinal);
        }
        click(findButton,timeout);
        click(worksheetLookUp,timeout);
        Thread.sleep(5000);
        if (!validateElement_Enabled_Displayed(workSheetLookUpSelectionOpen, timeout)) {
            click(worksheetLookUp, timeout);
        }
        awaitElement(workSheetLookUpSelectionOpen,timeout);
        click(workSheetLookUpSelectionOpen, timeout);
    }


    void changeLocation(String location,InterSystemLoginPage interSystemloginPage, Analytical analytical ){

        if (!locationNew.contentEquals(location)){
            locationNew =location;
            switchToDefaultContext();
            interSystemloginPage.setLocation(location);
            if(!firstTime) {
                switchToDefaultContext();
                interSystemloginPage.changelocation();

            }else{
                firstTime=false;
            }
            interSystemloginPage.userselection();
            analytical.navigateWorkSheetRes();
        }
    }




    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by,timeout).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }

    public PathCareLabWorkSheetResEntry(Roman roman) {
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
