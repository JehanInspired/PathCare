package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.TestDataModel;
import applications.PathCareapplication.models.WorkAreaReceiveEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import applications.PathCareapplication.widget.Pre_Analytical;
import org.junit.Assert;
import org.openqa.selenium.*;


import java.util.*;


public class WorkAreaReceptionPage extends AbstractExtension {

    private final By specimenNumberText = By.xpath(" //td/input[@id='SpecimenNumber']");

    private final By workAreaText = By.xpath("//input[@id='WorkArea']");

    private final By lookuprowselection = By.xpath("//tr[@id='LookupRow0']");

    private final By departmentText = By.xpath("//input[@id='Department']");

    private final By resetbutton = By.xpath("//input[@id='reset']");

    private final By checkout = By.xpath("//input[@id='RemoveFromWorkArea']");
    private final By tobereceivedcontain = By.xpath("//label[@id='SpecimenNumberTBRz1']");

    private final By checkin = By.xpath("//input[@id='update']");
    private final By reset = By.xpath("//input[@id='reset']");
    private final By workAreaSearchbutton = By.xpath("//img[@id='ld8066iWorkArea']");

    private final By workAreaReceptionframe = By.xpath("//iframe[@id='TRAK_main']");
    private final By aliqoutbutton = By.xpath("//input[@name='UpdateAndProceedToAliquot']");
    private final By aliquoutPencilIcon = By.xpath("//md-icon[@md-svg-icon='system-edit']");

    private final By aliquotSpecimenFieldEdit = By.xpath("//input[@name='LBSPCSpecimenDR']");
    private final By specimenNumberAliquot = By.xpath("//input[@name='LBSPCSpecimenNumber']");
    private final By specimenVolumeAliquot = By.xpath("//input[@name='LBSPCVolumeCollected']");

    private final By specimenNewSourceVolume = By.xpath("//input[@name='SourceNewVolume']");
    private final By aliquoutSpecimenContainerTextField = By.xpath("//input[@name='LBSPCContainerDR']");

    private final By aliquotUpdateButton = By.xpath("//button[text()='Update']");

    private final By aliquotConfirmButton = By.xpath("//button[@aria-label='Confirm']");
    private final By aliquotCloseButton = By.xpath("//button[text()='Close']");

    int timeout = 30;

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
            testDataModelList.add(new TestDataModel(workAreaReceiveEntity.getSpecimeNumber(), workAreaReceiveEntity.getTestSet().toString(), workAreaReceiveEntity.getWorkArea(), workAreaReceiveEntity.getDepartment(), workAreaReceiveEntity.getUserprofile()));
        }
        return testDataModelList;
    }
   public void workAreaReceive(String department, String workArea, String specimenNumber) throws InterruptedException {
        sendKeysAndTab(departmentText,department,timeout);
        Thread.sleep(2000);
        sendKeysAndTab(workAreaText,workArea,timeout);
        Thread.sleep(2000);
        sendKeysAndTab(specimenNumberText,specimenNumber + "-1",timeout);
        awaitClickableElement(checkin,timeout,30);
        Thread.sleep(3000);
        click(checkin,timeout);
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
            if (validateElement_Enabled_Displayed(workAreaText, timeout)) {
                click(workAreaSearchbutton,timeout);
            }

            awaitElement(lookuprowselection,timeout);
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

    public boolean departmentWorkArea(List<TestDataModel> data, boolean checking, String location,List<WorkAreaReceiveEntity> workAreaReceiveEntityList, InterSystemLoginPage interSystemloginPage, Pre_Analytical pre_analytical ) {

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
            /*if(dataModel.department !=null ||!dataModel.department.isBlank()) {
                sendKeys(departmentText, dataModel.department, timeout);
                click(lookuprowselection,timeout);

            }*/


            if (validateElement_Enabled_Displayed(workAreaText, timeout)) {
                sendKeys(workAreaText,dataModel.workArea);
                click(workAreaSearchbutton,timeout);
            }

            click(lookuprowselection,timeout);
            findOne(specimenNumberText, dataModel.specimennumber);

          //  aliqout(workAreaReceiveEntityList);

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
        super.findOne(workAreaText).clear();

        for (int x = 0; x <= specimenNumbers.size() - 1; x++) {
            departmentWorkArea(setupdata(departments, testcollections, specimenNumbers), false);
        }
        return condition;

    }
     public void aliqout(List<WorkAreaReceiveEntity> workAreaReceiveEntityList,String patientKey) throws InterruptedException {
        for(WorkAreaReceiveEntity workAreaReceiveEntity : workAreaReceiveEntityList) {
            if (workAreaReceiveEntity.getPk().contentEquals(patientKey)) {
               /* if (workAreaReceiveEntityList.getAliquotEditPK() != null) {
                    click(aliqoutbutton, timeout);
                    click(aliquoutPencilIcon, timeout);

                    if (workAreaReceiveEntityList.getAliquotspecimen() != null) {
                        setAliquotbuttonSpecimenEditSpecimen(specimenReceiveEntity.getAliquotspecimen());
                    }
                    //specimenNumbersAliquot

                    if (workAreaReceiveEntityList.getAliquotcontainer() != null) {
                        aliquotecontainerSpeciemen(specimenReceiveEntity.getAliquotcontainer());
                    }
                    if (workAreaReceiveEntityList.getAliquotVolume() != null) {
                        aliquoteVolumeSpeciemen(specimenReceiveEntity.getAliquotVolume());
                    }
                    if (workAreaReceiveEntityList.getNewSourceVolume() != null) {
                        aliquoteNewVolumeSpeciemen(specimenReceiveEntity.getAliquotVolume());
                    }
                    if (workAreaReceiveEntityList.getAliquotTestSet() != null) {
                        aliqoutSelectTickBox(specimenReceiveEntity.getAliquotTestSet());
                    }
                    click(aliquotUpdateButton, timeout);
                    stepPassedWithScreenshot("Updated aliquot for patient " + patientKey);
                    if (validateElement_Displayed(aliquotConfirmButton, timeout)) {
                        click(aliquotConfirmButton, timeout);
                    }
                    click(aliquotCloseButton, timeout);
                    aliqout = true;
                }*/

            }
        }

    }

    private void setAliquotbuttonSpecimenEditSpecimen(String specimen) throws InterruptedException {
            findOne(aliquotSpecimenFieldEdit).sendKeys(specimen);
            By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", specimen));
            if(findOne(aliquotSpecimenFieldEdit).getAttribute("value").contentEquals(specimen)){
                super._driver.findElement(aliquotSpecimenFieldEdit).sendKeys(Keys.TAB);
                stepInfoWithScreenshot("Entered container "+specimen);
                Thread.sleep(5000);
            }else{
                Assert.fail("Unable to find specimen "+specimen);
            }
        }


        private void aliquotecontainerSpeciemen(String container) throws InterruptedException {

            findOne(aliquoutSpecimenContainerTextField).sendKeys(container);
            By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", container));
            if(findOne(aliquoutSpecimenContainerTextField).getAttribute("value").contentEquals(container)){
                super._driver.findElement(aliquoutSpecimenContainerTextField).sendKeys(Keys.TAB);
                stepInfoWithScreenshot("Entered container "+container);
                Thread.sleep(5000);
            }else{
                Assert.fail("Unable to find Container Specimen "+container);
            }
        }

        private void aliquoteNewVolumeSpeciemen(String container) throws InterruptedException {

            findOne(specimenNewSourceVolume).sendKeys(container);
            By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", container));
            if(findOne(specimenNewSourceVolume).getAttribute("value").contentEquals(container)){
                super._driver.findElement(specimenNewSourceVolume).sendKeys(Keys.TAB);
                stepInfoWithScreenshot("Entered container "+container);
                Thread.sleep(3000);
            }else{
                Assert.fail("Unable to find Container Specimen "+container);
            }
        }

        private void aliquoteVolumeSpeciemen(String container) throws InterruptedException {

            findOne(specimenVolumeAliquot).sendKeys(container);
            By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", container));
            if(findOne(specimenVolumeAliquot).getAttribute("value").contentEquals(container)){
                super._driver.findElement(specimenVolumeAliquot).sendKeys(Keys.TAB);
                stepInfoWithScreenshot("Entered container "+container);
                Thread.sleep(3000);
            }else{
                Assert.fail("Unable to find Container Specimen "+container);
            }
        }

    private void aliqoutSelectTickBox(String testSetName) throws InterruptedException {
        By tickboxselection = By.xpath("//span[contains(text(),'%s')]//ancestor::tr//md-checkbox".replace("%s", testSetName));
        if (validateElement_Displayed(tickboxselection, timeout)) {
            click(tickboxselection, timeout);
            stepInfoWithScreenshot("Ticked Test set selection " + testSetName);
            Thread.sleep(5000);
        } else {
            Assert.fail("Unable to tick Test set selection " + testSetName);
        }
    }

    public void findOne(By by, String input) {
        super.findOne(by).click();
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
