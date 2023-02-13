package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.TestDataModel;
import applications.PathCareapplication.models.WorkAreaReceiveEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import applications.PathCareapplication.widget.Pre_Analytical;
import org.openqa.selenium.*;


import java.util.*;


public class WorkAreaReceptionPage extends AbstractExtension {

    private final By specimenNumberText = By.xpath(" //td/input[@id='SpecimenNumber']");

    private final By workArea = By.xpath("//input[@id='WorkArea']");

    private final By lookuprowselection = By.xpath("//tr[@id='LookupRow0']");

    private final By departmentText = By.xpath("//input[@id='Department']");

    private final By resetbutton = By.xpath("//input[@id='reset']");

    private final By checkout = By.xpath("//input[@id='RemoveFromWorkArea']");
    private final By tobereceivedcontain = By.xpath("//label[@id='SpecimenNumberTBRz1']");

    private final By checkin = By.xpath("//input[@id='update']");
    private final By reset = By.xpath("//input[@id='reset']");
    private final By workAreaSearchbutton = By.xpath("//img[@id='ld8066iWorkArea']");

    private final By workAreaReceptionframe = By.xpath("//iframe[@id='TRAK_main']");

    int timeout = 20;

    public WorkAreaReceptionPage(Roman roman) {
        super(roman);
    }

    public void labworkareaswitch() {
        awaitElement(mainframe,timeout);
        switchToMainFrame();

    }

    public List<TestDataModel> setupdata(String[] departments, String[] testcollections, List<String> specimenNumbers) {
        List<TestDataModel> testDataModelList = new ArrayList<>();

        for (int x = 0; x <= specimenNumbers.size() - 1; x++) {

            testDataModelList.add(new TestDataModel(specimenNumbers.get(x), testcollections[x], "g", departments[x]));
        }
        return testDataModelList;

    }

    public List<TestDataModel> setupdataMultiple(String[] departments, String[] testcollections, Collection<ArrayList<String>> specimenNumbers) {
        List<TestDataModel> testDataModelList = new ArrayList<>();

        //2
        for (ArrayList<String> specimen : specimenNumbers) {
            for (int x = 0; x <= specimen.size() - 1; x++) {

                testDataModelList.add(new TestDataModel(specimen.get(x), testcollections.length <= specimen.size() ? testcollections[0] : testcollections[x], "g", testcollections.length <= specimen.size() ? departments[0] : departments[x]));
            }
        }
        return testDataModelList;

    }

    public List<TestDataModel> setupdataSpecimentMultiple(String[] departments, Collection<ArrayList<String>> specimenNumbers) {
        List<TestDataModel> testDataModelList = new ArrayList<>();


        for (ArrayList<String> specimen : specimenNumbers) {
            for (int x = 0; x <= specimen.size()-1; x++) {
                //if(!"([a-z])|([A-Z])\\w+".matches(specimen.get(x).toString())) {
                    testDataModelList.add(new TestDataModel(specimen.get(x), "g", departments[0]));
               // }
            }
        }
        return testDataModelList;

    }

    public List<TestDataModel> setupdataWorkReceive(List<WorkAreaReceiveEntity> workAreaReceiveEntityList){
        List<TestDataModel> testDataModelList = new ArrayList<>();

        for(WorkAreaReceiveEntity workAreaReceiveEntity : workAreaReceiveEntityList){
            testDataModelList.add(new TestDataModel(workAreaReceiveEntity.getSpecimeNumber(), workAreaReceiveEntity.getTestSet(), workAreaReceiveEntity.getWorkArea(), workAreaReceiveEntity.getDepartment(), workAreaReceiveEntity.getUserprofile()));
        }
        return testDataModelList;
    }

    public boolean departmentWorkArea(List<TestDataModel> data, boolean checking) {

        boolean checkingout = false;
        for (TestDataModel dataModel : data) {

            switchToDefaultContext();
            loadingBarChecker();
            awaitElement(mainframe,timeout);
            switchToMainFrame();

            awaitElement(departmentText,timeout);
            sendKeys(departmentText, dataModel.department, timeout);

            click(lookuprowselection,timeout);
            if (validateElement_Enabled_Displayed(workArea, timeout)) {
                click(workAreaSearchbutton,timeout);
            }
            click(lookuprowselection,timeout);
            findOne(specimenNumberText, (String) dataModel.labespode);


            try {
                acceptAlert();
            } catch (NoAlertPresentException ignored) {
            }
            if (checking && !checkingout) {
                stepPassedWithScreenshot("Successfully updated Lab Specimen under Lab episode: " + dataModel.labespode);
                if(!validateElement_Enabled_Displayed(tobereceivedcontain)) {
                    click(checkin);
                }
            } else {
                if (validateElement_Displayed(resetbutton,timeout) && validateElement_Enabled_Displayed(checkout,timeout)) {
                    stepPassedWithScreenshot("Available option : Reset & Check Out. " + dataModel.labespode);
                    click(reset,timeout);
                    checkingout = true;
                    checking = true;
                }
            }
        }
        return checking;
    }

    public boolean departmentWorkArea(List<TestDataModel> data, boolean checking, String location, InterSystemLoginPage interSystemloginPage, Pre_Analytical pre_analytical ) {

        boolean checkingout = false;
        for (TestDataModel dataModel : data) {
            if (!dataModel.location.contentEquals(location)){
                location=dataModel.location;
                switchToDefaultContext();
                interSystemloginPage.setLocation(location);
                interSystemloginPage.changelocation();
                interSystemloginPage.userselection();
                pre_analytical.navigateWorkRecived();
            }

            switchToDefaultContext();
            loadingBarChecker();
            awaitElement(mainframe,timeout);
            switchToMainFrame();


            sendKeys(departmentText, dataModel.department, timeout);

            click(lookuprowselection,timeout);
            if (validateElement_Enabled_Displayed(workArea, timeout)) {
                click(workAreaSearchbutton,timeout);
            }
            click(lookuprowselection,timeout);
            findOne(specimenNumberText, dataModel.specimennumber);


            try {
                acceptAlert();
            } catch (NoAlertPresentException ignored) {
            }
            if (checking && !checkingout) {
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + dataModel.specimennumber);
                if(!validateElement_Enabled_Displayed(tobereceivedcontain)) {
                    click(checkin);
                }
            } else {
                if (validateElement_Displayed(resetbutton,timeout) && validateElement_Enabled_Displayed(checkout,timeout)) {
                    stepPassedWithScreenshot("Available option : Reset & Check Out. " + dataModel.specimennumber);
                    click(reset,timeout);
                    checkingout = true;
                    checking = true;
                }
            }
        }
        return checking;
    }



    public boolean checkout_reset(String[] departments, String[] testcollections, List<String> specimenNumbers) {

        boolean condition = false;
        super.findOne(workArea).clear();

        for (int x = 0; x <= specimenNumbers.size() - 1; x++) {
            departmentWorkArea(setupdata(departments, testcollections, specimenNumbers), false);
        }
        return condition;

    }


    public void findOne(By by, String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys(Keys.TAB);
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
