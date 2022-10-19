package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.LabespideData;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import selenium.AbstractPage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PathCareScratch extends AbstractPage {

    private final By switchiFrame = By.xpath("//iframe[@name='TRAK_main']");
    private final By surnametextbox = By.xpath("//input[@name='PAPERName']");
    private final By givennametextbox = By.xpath("//input[@name='PAPERName2']");
    private final By findbutton = By.xpath("//button[text()='Find']");
    private final By newbutton = By.xpath("//a[text()='New']");
    private final By gendertextbox = By.xpath("//input[@name='CTSEXDesc']");
    //dd/mm/yyyy
    private final By DateofBirth = By.xpath("//input[@name='PAPERDob']");
    private final By saveAndclose = By.xpath("//button[text()='Save & Close']");
    private final By iconDoctorSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPReferringDoctorDR-lookupIcon']");

    private final By firstrowdoctorsearch  = By.xpath("//a[@id='PACRefDoctor_CustomFind_0-row-0-item-FullDoctorName-link']");
    private final By iconPatientSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupIcon']");
    private final By patientSearchSelect = By.xpath("//input[@name= 'LBEPPatientLocationDR' ]");
    private final By testSetLink = By.xpath("//span[text()='Test Set Link']");
    private final By collectionTime =By.xpath("//input[@name='LBEPCollectionTime']");
    private final By testSetCollection = By.xpath("//input[@name='TestSetSuperset']");
    private final By backtoLabEpisodeNav = By.xpath("//a[text()='Back to: Lab Episode']");
    private final By supersetItemSelection = By.xpath("//span[contains(text(),'Superset Item Selection')]");
    private final By labEspiodeNum = By.xpath("//div[contains(text(),'Lab Episode Number:')]");
    private final By specimensearch = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupIcon']");

    private final By applybuttonSpecimenContainer = By.xpath("//button[text()='Accept']");
    private final By buttonSupersetItemSelectionAccept = By.xpath("//button[contains(text(),'Accept')]");
    private final By specimenLookup = By.xpath("//span[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupRow-0-value-1']");

    private final By specimenContainerlookup = By.xpath("//span[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupRow-0-value-1']");
    private final By specimenContainerSearch = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupIcon']");
    private final By specimenContainer = By.xpath("//div[@id=LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-matchesWrapper']");
    private final By testsetrequimenttext = By.xpath("//span[text()='At least one test set is required']");
    private final By selectlinkSpecimen = By.xpath("//a[text()='Select']");
    private final By acceptButton = By.xpath("//input[@name='accept1']");
    private final By specimenContainerTextField = By.xpath("//input[@name='LBSPCContainerDR']");

    private final  By updatebuton = By.xpath("//button[@type='submit']");
    private  By testCode = By.xpath("//span[text()='%s']");
    private final By linkSelectSpecimen =By.xpath("//a[text()='Select']");
    private final By tickboxSpecimens = By.xpath("//input[contains(@id,'selectz')]");

    private final By acceptSpecimens = By.xpath("//input[contains(@id,'accept')]");
    private final By labEpiodeTextField = By.xpath("//input[@name='LBEPNumber']");

    private final By findButton = By.xpath("//button[text()='Find']");

    private final By specimenN = By.xpath("//input[contains(@ng-model,'LBSPC_SpecimenNumber')]");

    private final By addSpecimenButton = By.xpath("//a[contains(@ng-class,'AddS')]");

    private final By advanceSearch = By.xpath("//span[text()='Advanced Search']");
    private final By specimenContainerEdit = By.xpath("//input[@name='LBSPCSpecimenDR']");

    private final By nextpagelookup = By.xpath("//a[@aria-label='Lookup Next Page']");

    private final By editPencil = By.xpath("//md-icon[contains(@id,'LBEpisode_TestSet_List_0-row-0-item-Edit-image')]");

    private final By specimenTestSetFilterMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-TestSet-lookupIcon']");

    private final By specimenContainerMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupIcon']");
    private final By specimenMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupIcon']");

    private final By testSetTextfield = By.xpath("//div[@name='TestSet']/p");
    private final By acceptButtonEdit = By.xpath("//button[text()='Accept']");
    private final By receivedDateTextBox = By.xpath("//input[@name='LBEPReceivedDate']");
    private final By receivedTimeTextBox = By.xpath("//input[@name='LBEPReceivedTime']");

    private final By loadingBar = By.xpath("//div[@class='bar']");

    private final By bodydom = By.xpath("//body[@ng-app='tcApp']");

    private  By testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]");

    public  String testset ="";

    public String collectionDetailWithMultipleTestSet(String collectiontime, String[] testsetcollection, Boolean specimenSelect) {
        //click(iconPatientSearch);
        sendKeys(patientSearchSelect,"2100");
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
           for (String set : testsetcollection) {
               testCode = By.xpath("//span[text()='%s']".replace("%s", set));
               sendKeys(testSetCollection, set);
               super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
               if (validateElement_Displayed(supersetItemSelection)) {
                   stepInfoWithScreenshot("Reach to Superset Item Selection");
                   click(buttonSupersetItemSelectionAccept);
               } else if (validateElement_Displayed(backtoLabEpisodeNav) && !specimenSelect) {
                   click(specimensearch);
                   click(specimenLookup);
                   click(specimenContainerSearch);
                   click(specimenContainerlookup);

                   Assert.assertTrue(validateElement_Enabled_Displayed(applybuttonSpecimenContainer));
                   click(applybuttonSpecimenContainer);
                   if (validateElement_Enabled_Displayed(selectlinkSpecimen)) {
                       click(selectlinkSpecimen);
                       switchToFrame(switchiFrame);
                       click(acceptButton);
                       switchToDefaultContext();
                   }
               } else if (specimenSelect) {
                   click(backtoLabEpisodeNav);
                   click(linkSelectSpecimen, 10);
                   switchToDefaultContext();
                   switchToFrame(switchiFrame);
                   for (WebElement element : find(tickboxSpecimens)) {
                       element.click();
                       stepPassedWithScreenshot("Selected specimen " + set);
                   }
                   click(acceptSpecimens);
                   stepPassedWithScreenshot("Accepted specimen " + set);
                   switchToDefaultContext();
               }
               validateElement_Enabled_Displayed(testSetLink, 10);
               stepPassedWithScreenshot("The correct Test Set appears under Tests : " + set);
           }

               click(updatebuton);
               stepPassedWithScreenshot("Label printed successfully " +getText(labEspiodeNum,20).replace("Lab Episode Number: ",""));

        return getText(labEspiodeNum,10).replace("Lab Episode Number: ","");
    }

    public String collectiondetailnew(String collectiontime, String[] testsetcollection, Boolean specimenSelect, Boolean updateClient) throws InterruptedException {
       //click(iconPatientSearch);
        String labepsiode ="";
        sendKeys(patientSearchSelect,"2100");
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
        addReceivedDate(collectiontime);
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            if(validateElement_Displayed(supersetItemSelection)){
                stepInfoWithScreenshot("Reach to Superset Item Selection");
                click(buttonSupersetItemSelectionAccept);
            } else if (validateElement_Displayed(backtoLabEpisodeNav) && !specimenSelect){
                click(specimensearch);
                click(specimenLookup);
                click(specimenContainerSearch);
                click(specimenContainerlookup);

                Assert.assertTrue("Unable to click apply button on Specimen Container",validateElement_Enabled_Displayed(applybuttonSpecimenContainer));
                click(applybuttonSpecimenContainer);
                loadingBarChecker();
                if(validateElement_Enabled_Displayed(selectlinkSpecimen)){
                    click(selectlinkSpecimen);
                    switchToFrame(switchiFrame);
                    click(acceptButton);
                    switchToDefaultContext();
                }
            }else if(specimenSelect){
                specimenSelect();
            }
            validateElement_Enabled_Displayed(testSetLink,10);
            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }

        if(updateClient) {
           labepsiode = updateClientDetails();
        }
        return labepsiode;

    }

    public String updateClientDetails(){
        if(validateElement_Enabled_Displayed(updatebuton,10)) {
            loadingBarChecker();
            click(updatebuton, 15);
        } else {
            Assert.fail("Unable to receive Lap Episode Number");
        }
            String labepsiode = getText(labEspiodeNum, 20).replace("Lab Episode Number: ", "");
            stepPassedWithScreenshot("Label printed successfully " + labepsiode);
            LabespideData labespideData = new LabespideData(labepsiode);
            labespideData.setTestset(testset);

        return labepsiode;
    }
    public void loadingBarChecker(){
        int x =5;
        while(validateElement_Displayed(loadingBar,5)|| !validateElement_Displayed(bodydom,5)){
            x--;
            stepInfo("Page still loading");
            if(x==0){
                break;
            }
        }
    }
    public void specimenSelect(){
       // if(validateElement_Displayed(backtoLabEpisodeNav)){click(backtoLabEpisodeNav);}
        loadingBarChecker();
        scrollToElement(linkSelectSpecimen);
        click(linkSelectSpecimen,10);
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        for(WebElement element:find(tickboxSpecimens)){
            if(!element.isSelected()){
            element.click();
            stepPassedWithScreenshot("Selected specimen "+testset);
            }

        }
        click(acceptSpecimens);
        stepPassedWithScreenshot("Accepted specimen "+testset);
        switchToDefaultContext();
    }

    public void editTestSet(String name ,String value)  {
       if(validateElement_Enabled_Displayed(editPencil,10)){
           loadingBarChecker();
           scrollToElement(editPencil);
           click(editPencil,10);
        }else{
           Assert.fail("Unable to click Edit Pencil");
       }
       testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]".replace("%s",name));
       sendKeys(testdieditfield,value);
       click(acceptButtonEdit,10);
       loadingBarChecker();

    }

    public Boolean updatewithoutTestCollection(String collectiontime){
        sendKeys(patientSearchSelect,"2100");
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
        click(updatebuton);
         if(validateElement_Displayed(testsetrequimenttext,5)){
             validateElement_Displayed(findbutton,4);
             stepPassedWithScreenshot("Successfully displayed: Error message appears  \"At least one test set is required\"");
             return true;
         }
         return false;
    }

    public void patientdetails(String name, String surname, String dateBirth, String gender){
        validateElement_Enabled_Displayed(surnametextbox,15);
        sendKeys(surnametextbox,name,10);
        validateElement_Enabled_Displayed(givennametextbox,15);
        sendKeys(givennametextbox,surname,10);
        validateElement_Enabled_Displayed(findButton,15);
        click(findbutton);
        if(validateElement_Enabled_Displayed(newbutton,15)) {
            stepPassedWithScreenshot("The Patient list screen appears with no list");
        }
        click(newbutton);
        findEnterTab(gendertextbox,gender);
        findEnterTab(DateofBirth,dateBirth);
        click(DateofBirth);
        stepPassedWithScreenshot("User is directed to Lab Episode screen");
        if(validateElement_Enabled_Displayed(saveAndclose,10)) {
            click(saveAndclose);
            loadingBarChecker();
        }else{
            Assert.fail("Unable to click save close button");
        }
        stepPassedWithScreenshot("Successfully User is directed to Patient Registration screen" );

    }

    public void doctorSelection(){
        click(iconDoctorSearch,15);
        click(firstrowdoctorsearch,10);

    }


    public List<String> mutiplePatient(Faker faker, String[] testcollection, Boolean specimenSelect, int numberPatient, Boolean updateClient) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
            doctorSelection();
            labEspideonumber.add(collectiondetailnew("n-1",testcollection,specimenSelect,updateClient));
        }

        return labEspideonumber;
    }

    public void addReceivedDate(String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        findEnterTab(receivedDateTextBox, dateTimeFormatter.format(LocalDate.now()));
        findEnterTab(receivedTimeTextBox,time);

    }

    public void searchPatient(String lapespido){

            if(validateElement_Displayed(advanceSearch,10)){
                click(advanceSearch);
                sendKeys(labEpiodeTextField,lapespido);
                click(findButton);
                stepInfoWithScreenshot("Search for Patient "+ lapespido);
            }else{
                Assert.fail("Unable to find Advance Search");
            }

    }

    public List<String> addMultipleSpecimen(String testSetFilter,String specimen,String container,int numberSpecimen,boolean checkspecimen){
        for(int x=0;x<=numberSpecimen-1;x++) {
            if (validateElement_Enabled_Displayed(addSpecimenButton, 10)) {
                scrollToElement(addSpecimenButton);
                click(addSpecimenButton);
                testSetFilter(testSetFilter);
                specimenEditSpecimen(specimen);
                containerSpeciemen(container);
                click(applybuttonSpecimenContainer);
            }
        }
        return  specimenNumberExtract(checkspecimen);
    }


    public void specimenEditSpecimen(String specimen) {
        findOne(specimenContainerEdit).sendKeys(specimen);
        By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and (text()='%s')]".replace("%s", specimen));
        while (!validateElement_Enabled_Displayed(lookupOnList)) {
            if (validateElement_Enabled_Displayed(nextpagelookup, 5)) {
                click(nextpagelookup);
            } else {
                break;
            }
        }
        if(findOne(specimenContainerEdit).getAttribute("value").contentEquals(specimen)){
            click(lookupOnList);
            stepInfoWithScreenshot("Entered Specimen "+specimen);
        }else{
            Assert.fail("Unable to find specimen "+specimen);
        }
    }

    public void testSetFilter(String testSetFilter) {
        JavascriptExecutor jExecutor = (JavascriptExecutor) this._driver;
        jExecutor.executeScript("arguments[0].textContent = arguments[1];", findOne(testSetTextfield), testSetFilter);

        if(findOne(testSetTextfield).getText().contains(testSetFilter)){
            stepInfoWithScreenshot("Entered test Set Filter "+testSetFilter);
        }else{
            Assert.fail("Unable to find Test Set Filter "+testSetFilter);
        }
    }

    public void containerSpeciemen(String container){

         findOne(specimenContainerTextField).sendKeys(container);
        By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and (text()='%s')]".replace("%s", container));
        if(findOne(specimenContainerTextField).getAttribute("value").contentEquals(container)){
            click(lookupOnList);
            stepInfo("Entered container "+container);
        }else{
            Assert.fail("Unable to find Container Specimen "+container);
        }
    }

    public ArrayList<String> specimenNumberExtract(boolean checkspecimen){
        ArrayList<String> values =  new ArrayList<>();
        if(checkspecimen) {
            if (validateElement_Displayed(specimenN)) {
                scrollToElement(specimenN);
                for (WebElement element : find(specimenN)) {
                    values.add(element.getAttribute("value"));
                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            } else {
                Assert.fail("Unable to Find specimen Number");
            }
        }
        return null;
    }

    public List<String> mutiplePatientWithDifferentTestset(Faker faker,String[] testcollection,Boolean specimenSelect) {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=testcollection.length-1;x++){
                patientdetails(faker.name().name(), faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11, 55)), faker.demographic().sex());
                doctorSelection();
                labEspideonumber.add(collectionDetailWithMultipleTestSet("n-1", testcollection[x].split(","), specimenSelect));
        }

        return labEspideonumber;

    }

    public void findEnterTab(By by, String input) {

        validateElement_Enabled_Displayed(by,10);
        super.sendKeys(by,input,5);
        super.findOne(by).click();

    }
    private void setTestset(String testset) {
        this.testset = testset;
    }

    public PathCareScratch(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
        return validateElement_Enabled_Displayed(iconDoctorSearch,15);
    }
}
