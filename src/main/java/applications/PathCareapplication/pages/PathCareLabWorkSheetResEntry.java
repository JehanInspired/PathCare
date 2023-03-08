package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.*;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
    private final By workSheetLookUpSelectionOpen = By.xpath("//tr[contains(@id,'LookupRow') and contains(@lookupallvalues,'Open')]");
    private final By validityDateText = By.xpath("//input[@name='ValidityDate']");
    private final By validityTime = By.xpath("//input[@name='ValidityTime']");

    private final By tableName = By.xpath("//table[@class='tblListSelect']");

    private String locationNew = "";

    private Boolean firstTime = true;
    private int timeout = 20;


    public void workSheetEntry(List<WorkSheetResultEntry> workSheet,List<WorkSheetResultValues> sheetResultValues,
                               List<SpecimenReceiveEntity> specimenReceiveEntities,Analytical analytical,
                               InterSystemLoginPage interSystemLoginPage){
        Boolean found = false;

        for(WorkSheetResultEntry workSheetSingle:workSheet) {

            //Change location
            changeLocation(workSheetSingle.getUserprofile_FK(),interSystemLoginPage,analytical);
            //need to fill in
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
            awaitElement(workSheetLookUpSelectionOpen,timeout);
            awaitClickableElement(workSheetLookUpSelectionOpen,timeout,4).click();
            for(SpecimenReceiveEntity specimenReceiveEntity:specimenReceiveEntities){
                if(workSheetSingle.getSpecimenReceive_FK().contains(specimenReceiveEntity.getPk())) {
                    workSheetSingle.setSpecimenReceive_FK(specimenReceiveEntity.getSpecimenNumber());


                   for (WorkSheetResultValues workSheetResultValues : sheetResultValues) {
                        if (workSheetResultValues.getWorkSheetResultKey_FK() != null) {


                            if (workSheetResultValues.getWorkSheetResultKey_FK().contentEquals(workSheetSingle.getWorkSheetResult_Key())) {
                                found = true;
                                int x = 0;
                                for (String qcKey : workSheetResultValues.getTestvalues().keySet()) {
                                    tableExtaction(tableName,"Specimen No",qcKey,workSheetSingle.getSpecimenReceive_FK() , workSheetResultValues.getTestvalues().get(qcKey) == null ? "" : workSheetResultValues.getTestvalues().get(qcKey),timeout);
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
                        found = true;
                        int x =0;
                        if(workSheetValues.getQc() != null || !workSheetValues.getQc().isBlank()) {
                            for (String qcKey : workSheetValues.getTestvalues().keySet()) {
                                tableExtaction(tableName,"Specimen No",qcKey, workSheetValues.getQc(), workSheetValues.getTestvalues().get(qcKey) == null ? "" : workSheetValues.getTestvalues().get(qcKey),timeout);
                            /*By qcSpecimenField = By.xpath("//parent::td[label[text()='"+workSheetSingle.getSpecimenReceive_FK()+"']]//parent::tr//td/span/input[not(@type='hidden')]");
                            By heading = By.xpath("//th[text()='"+qcKey+"']");
                            if(validateElement_Enabled_Displayed(heading,timeout)){
                                find(qcSpecimenField,timeout).get(++x).sendKeys(workSheetValues.getTestvalues().get(qcKey) == null? "":workSheetValues.getTestvalues().get(qcKey));
                            }*/
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

    private void EnteringValueSpecimenNumber(){

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
