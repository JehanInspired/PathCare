package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.EditTestSetEntity;
import applications.PathCareapplication.models.SpecimensEntity;
import applications.PathCareapplication.models.TestSetDetailsEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathCareScratch extends AbstractExtension {

    private final By switchiFrame = By.xpath("//iframe[@name='TRAK_main']");
    private final By surnametextbox = By.xpath("//input[@name='PAPERName']");
    private final By givennametextbox = By.xpath("//input[@name='PAPERName2']");
    private final By findbutton = By.xpath("//button[text()='Find']");
    private final By newbutton = By.xpath("//a[text()='New']");
    private final By newbutton2 = By.xpath("//button[text()='New']");
    private final By gendertextbox = By.xpath("//input[@name='CTSEXDesc']");
    //dd/mm/yyyy
    private final By DateofBirth = By.xpath("//input[@name='PAPERDob']");
    private final By saveAndclose = By.xpath("//button[@type='submit']");
    private final By iconDoctorSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPReferringDoctorDR-lookupIcon']");
    private final By nameofDoctor = By.xpath("//input[@name='LBEPReferringDoctorDR']");

    private final By firstrowdoctorsearch  = By.xpath("//a[@id='PACRefDoctor_CustomFind_0-row-0-item-FullDoctorName-link']");
    private final By iconPatientSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupIcon']");
    private final By patientSearchSelect = By.xpath("//input[@name= 'LBEPPatientLocationDR' ]");
    private final By testSetLink = By.xpath("//span[text()='Test Set Link']");
    private final By collectionTimeTextbox =By.xpath("//input[@name='LBEPCollectionTime']");
    private final By collectionDateTextbox =By.xpath("//input[@name='LBEPCollectionDate']");
    private final By testSetCollection = By.xpath("//input[@name='TestSetSuperset']");
    private final By backtoLabEpisodeNav = By.xpath("//a[text()='Back to: Lab Episode']");
    private final By specimenContainereditSpan = By.xpath("//*[@id='LBSpecimenContainer_Msg_Edit_0-header-caption']");
    private final By backtoPatient = By.xpath("//a[contains(@ng-bind,'getBackToCaption()')]");
    private final By supersetItemSelection = By.xpath("//span[contains(text(),'Superset Item Selection')]");
    private final By labEspiodeNum = By.xpath("//div[contains(text(),'Lab Episode Number')]");
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

    private final By registrationNo = By.xpath("//input[@name='RegistrationNo']");
    private final By lapEpisodeTitle = By.xpath("//span[text()='Lab Episode']");

    private final By labEpisodeNumber =  By.xpath("//input[@name='LBEPNumber']");

    private final By findButton = By.xpath("//button[text()='Find']");

    private final By reportTitle = By.xpath("//span[text()='Reports']");
    private final By specimenNumbers = By.xpath("//input[ contains(@name,'LBSPC_SpecimenNumberz')]");
    private final By specimenAnatomicalSiteWithTestSet = By.xpath("//*[ contains(@aria-label,'Anatomical Site Lookup')  or contains(@ng-model,'LBSPC_SpecimenNumber') or contains(@ng-bind,'TestSetList') ]");
    private final By specimenNumberUnedit = By.xpath("//span[contains(@ng-bind,'LBCAS_Desc') or contains(@ng-bind,'LBSPC_SpecimenNumber')]");
    private final By specimenText = By.xpath("//input[contains(@ng-model,'LBSPC_SpecimenNumber')]");
    private final By specimenNumberTextUnedit = By.xpath("//span[contains(@ng-bind,'LBSPC_SpecimenNumber')]");

    private final By addSpecimenButton = By.xpath("//a[contains(@ng-class,'AddS')]");

    private final By advanceSearch = By.xpath("//span[text()='Advanced Search']");
    private final By advanceSearchDownArrow = By.xpath("//md-icon[@md-svg-icon='system-chevron-down' and not(contains(@id,'PreAnalysis'))]");
    private final By specimenContainerEdit = By.xpath("//input[@name='LBSPCSpecimenDR']");

    private final By nextpagelookup = By.xpath("//a[@aria-label='Lookup Next Page']");

    private final By editPencil = By.xpath("//md-icon[contains(@id,'item-Edit-image') and not(contains(@id,'LBEpisode_Reporting_List'))]");

    private final By specimenTestSetFilterMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-TestSet-lookupIcon']");

    private final By specimenContainerMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupIcon']");
    private final By specimenMaginfilyGlass = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupIcon']");

    private final By testSetTextfield = By.xpath("//div[@name='TestSet']/p");
    private final By acceptButtonEdit = By.xpath("//button[text()='Accept']");
    private final By receivedDateTextBox = By.xpath("//input[@name='LBEPReceivedDate']");
    private final By receivedTimeTextBox = By.xpath("//input[@name='LBEPReceivedTime']");
    private final By clickTestSetLink = By.xpath("//md-icon[contains(@id,'LBTSLink') and not(@ng-class)]");

    private final By firstpatientEpisodeSearch = By.xpath("//a[contains(@id,'PAPerson') and  not(@aria-label='New') and not(@title)]");

    private final By editQuestionHideCollection =By.xpath("//md-icon[contains(@id,'LBTSSHHideForCollection') and not(@class)]");
    private final By editQuestionHideProcessing = By.xpath("//md-icon[contains(@id,'LBTSSHHideForProcessing') and not(@class)]");
    private final By getEditQuestionHideReceive = By.xpath("//md-icon[contains(@id,'LBTSSHHideForReceive') and not(@class)]");
    private final By addtionalQuestionSpecimenContainer = By.xpath("//input[contains(@name,'QA_ComboBoxz1') ]");
    private final By addtionalQuestionSlides = By.xpath("//input[contains(@name,'QA_Integerz2') ]");
    private final By addtionalQuestionApprox = By.xpath("//input[contains(@name,'QA_Integerz3') ]");
    private final By addtionalQuestionmarcodesciption = By.xpath("//input[contains(@name,'QA_ComboBoxz4') ]");
    private final By addtionalQuestionmEnterOfOxygen = By.xpath("//input[contains(@name,'QA_TextBoxz6')]");
    private final By addtionalQuestionmTimeBloodgasTaken = By.xpath("//input[contains(@name,'QA_Timez5')]");
    private final By iframehidden = By.xpath("//iframe[@name='TRAK_hidden']");
    private final By continuelink = By.xpath("//a[text()='Continue']");
    private final By volumeCollectedTextBox = By.xpath("//input[contains(@name,'VolumeCollected')]");

    private  By testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]");
    private  By  nextpageURN = By.xpath("//a[text()='Next >']");
    //private  By  labEspiodeNum = By.xpath("//*[@id='tc_Toast-misc-message']");
    private  By  uniqueRegistrationNum = By.xpath("//input[@name='RegistrationNo']");
    public  String testset ="";
    private boolean newPatient = true;

    private int timeout = 40;

    private boolean clickBackboneLabEpisodeSelect = true;

    private String name;
    private String dateOfBirth;
    private String surname;
    private String gender;
    public  ArrayList<String> labEpisodesNumber = new ArrayList<String>();
    static  String shipmentNumber ="";
    public ArrayList<String> labEpisode = new ArrayList<>();
    public String collectionDetailWithMultipleTestSet(String collectiontime, String[] testsetcollection, Boolean specimenSelect,String petientLocation) {
        //click(iconPatientSearch);
        sendKeys(patientSearchSelect,petientLocation);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTimeTextbox,collectiontime);
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

    public String collectiondetailnew(String collectiontime, String[] testsetcollection,Boolean receiveDate, Boolean specimenSelect, Boolean updateClient,String petientLocation) throws InterruptedException {
       //click(iconPatientSearch);
        String labepsiode ="";
        sendKeys(patientSearchSelect,petientLocation);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTimeTextbox,collectiontime);
        if(receiveDate) {
            addReceivedDate(collectiontime);
        }
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
        if(validateElement_Enabled_Displayed(updatebuton,timeout)) {
            awaitElement(updatebuton,timeout);
            click(updatebuton, timeout);
            stepInfo("Successfully clicked Update button "+updatebuton);

        } else {
            Assert.fail("Unable to receive Lap Episode Number");
        }
            String lab_episode = getText(labEspiodeNum, timeout).replace("Lab Episode Number: ", "");
            stepPassedWithScreenshot("Label printed successfully " + lab_episode);
            labEpisode.add(lab_episode);
        return lab_episode;
    }

    public void specimenSelect(){
        if(clickBackboneLabEpisodeSelect){click(backtoLabEpisodeNav);}

        scrollToElement(linkSelectSpecimen,timeout);
        click(linkSelectSpecimen,timeout);
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

   /* public void editTestSet(String testset ,String value)  {

        loadingBarChecker();
        if(validateElement_Enabled_Displayed(editPencil,10)){
            loadingBarChecker();
            scrollToElement(editPencil);
            click(editPencil,10);
        }else{
            Assert.fail("Unable to click Edit Pencil");
        }
        testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]".replace("%s",name));
        sendKeys(testdieditfield,value);
        stepPassedWithScreenshot("Added "+name+" "+value);
        click(acceptButtonEdit,10);
        loadingBarChecker();

    }*/

    public void editTestList(String testset, String pk,ArrayList<EditTestSetEntity> editTestSetEntityArrayList){

                for(EditTestSetEntity editTestSetEntity : editTestSetEntityArrayList){
                    if(editTestSetEntity.getPk()!=null){
                        if(pk.contentEquals(pk)){
                            if(editTestSetEntity.getTestcode().contentEquals(testset)){
                               // editTestSetEntity = By.xpath("//parent::span[ contains(@ng-bind,'LBTS_TestSet_DR') and text()='"+testsetname+"']//ancestor::tr//a[contains(@id,'LBEpisode_TestSet_List_"+Integer.parseInt(pk)-1+"-row-"+Integer.parseInt(pk)-1+"-item-Edit-link')]");
                                if(validateElement_Enabled_Displayed(editPencil,timeout)){
                                    loadingBarChecker();
                                    scrollToElement(editPencil);
                                    //Need to fix by aliging the testset with the pencil icon
                                    find(editPencil,timeout).get(0).click();
                                }else{
                                    Assert.fail("Unable to click Edit Pencil");
                                }
                                specialHandling(editTestSetEntity.testSetSpecialHandling);
                                additionalQuestion(editTestSetEntity.additionalTestSetQuestions);
                                if(validateElement_Enabled_Displayed(acceptButtonEdit,timeout)) {
                                    click(acceptButtonEdit, timeout);
                                    stepInfoWithScreenshot("Accepting edit test set information");
                                }else{
                                    Assert.fail("Unable to Submit Edit Pencil");
                                }
                            }

                        }
                    }
                }
    }
    public void specialHandling( EditTestSetEntity.TestSetSpecialHandling testSetSpecialHandling){
        if(testSetSpecialHandling != null) {
            if (testSetSpecialHandling.getHideforCollection() != null) {
                click(editQuestionHideCollection);
                stepInfoWithScreenshot("Clicked tick-box Question Hide Collection");
            }

            if (testSetSpecialHandling.getHideforProcessing() != null) {
                click(editQuestionHideProcessing);
                stepInfoWithScreenshot("Clicked tick-box Question Hide Processing");
            }

            if (testSetSpecialHandling.getHideforReceive() != null) {
                click(getEditQuestionHideReceive);
                stepInfoWithScreenshot("Clicked tick-box Question Hide Receive");
            }
        }


    }

    public void additionalQuestion(EditTestSetEntity.AdditionalTestSetQuestions additionalTestSetQuestions){
        if(additionalTestSetQuestions != null) {
            if (additionalTestSetQuestions.getSpecimenContainer() != null) {
                sendKeys(addtionalQuestionSpecimenContainer, additionalTestSetQuestions.getSpecimenContainer());
                super._driver.findElement(addtionalQuestionSlides).sendKeys(Keys.TAB);
                stepInfo("Entered Specimen Container" + additionalTestSetQuestions.getSpecimenContainer());
            }

            if (additionalTestSetQuestions.getNumberOfFNASlides() != null) {
                sendKeys(addtionalQuestionSlides, additionalTestSetQuestions.getNumberOfFNASlides());
                super._driver.findElement(addtionalQuestionSlides).sendKeys(Keys.TAB);
                stepInfo("Entered FNA Slides " + additionalTestSetQuestions.getNumberOfFNASlides());
            }

            if (additionalTestSetQuestions.getMacropscopicDescription() != null) {
                sendKeys(addtionalQuestionmarcodesciption, additionalTestSetQuestions.getMacropscopicDescription());
                super._driver.findElement(addtionalQuestionmarcodesciption).sendKeys(Keys.TAB);
                stepInfo("Entered Macroscopic Description " + additionalTestSetQuestions.getMacropscopicDescription());
            }

            if (additionalTestSetQuestions.getPatientOnReplacementRX() != null) {
                sendKeys(addtionalQuestionmarcodesciption, additionalTestSetQuestions.getPatientOnReplacementRX());
                super._driver.findElement(addtionalQuestionmarcodesciption).sendKeys(Keys.TAB);
                stepInfo("Entered Patient on replacement Rx value " + additionalTestSetQuestions.getPatientOnReplacementRX());
            }

            if (additionalTestSetQuestions.getPatientOnOxygen() != null) {
                sendKeys(addtionalQuestionmarcodesciption, additionalTestSetQuestions.getPatientOnOxygen());
                super._driver.findElement(addtionalQuestionmarcodesciption).sendKeys(Keys.TAB);
                stepInfo("Entered Is patient on Oxygen value " + additionalTestSetQuestions.getPatientOnOxygen());
            }


            if (additionalTestSetQuestions.getTimeBloodgastaken() != null) {
                sendKeys(addtionalQuestionmTimeBloodgasTaken, additionalTestSetQuestions.getTimeBloodgastaken());
                super._driver.findElement(addtionalQuestionmTimeBloodgasTaken).sendKeys(Keys.TAB);
                stepInfo("Entered Time Bloodgas taken (HHMM) value " + additionalTestSetQuestions.getTimeBloodgastaken());
            }

            if (additionalTestSetQuestions.getEnterOfOxygenGiven() != null) {
                sendKeys(addtionalQuestionmEnterOfOxygen, additionalTestSetQuestions.getEnterOfOxygenGiven());
                super._driver.findElement(addtionalQuestionmEnterOfOxygen).sendKeys(Keys.TAB);
                stepInfo("Entered % Oxygen given or NS Description value " + additionalTestSetQuestions.getEnterOfOxygenGiven());
            }
        }


    }


    public void editTestSetSingle(String name , String value)  {
        loadingBarChecker();
       if(validateElement_Enabled_Displayed(editPencil,timeout)){
           loadingBarChecker();
           scrollToElement(editPencil,timeout);
           click(editPencil,timeout);
        }else{
           Assert.fail("Unable to click Edit Pencil");
       }
       testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]".replace("%s",name));
       sendKeys(testdieditfield,value,timeout);
       stepPassedWithScreenshot("Added "+name+" "+value);
       click(acceptButtonEdit,timeout);
       loadingBarChecker();

    }

    public Boolean updatewithoutTestCollection(String collectiontime, String patientLocation){
        sendKeys(patientSearchSelect,patientLocation);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTimeTextbox,collectiontime);
        click(updatebuton);
         if(validateElement_Displayed(testsetrequimenttext,timeout)){
             validateElement_Displayed(findbutton,timeout);
             stepPassedWithScreenshot("Successfully displayed: Error message appears  \"At least one test set is required\"");
             return true;
         }
         return false;
    }

    public void setClientdetails(String name, String surname, String dateOfBirth,String gender){

        setName(name.isBlank()? new Faker().name().firstName():name);
        setSurname(surname.isBlank()? new Faker().name().lastName():surname);
        setDateOfBirth(dateOfBirth.isEmpty()?  new SimpleDateFormat("dd/MM/yyyy").format(new Faker().date().birthday(11,55)):dateOfBirth);
        setGender(gender.isBlank() ? new Faker().demographic().sex():gender);
    }

    public void createdSamePatient() {
        loadingBarChecker();
        awaitElement(firstpatientEpisodeSearch,timeout);
        click(firstpatientEpisodeSearch,timeout);
        awaitElement(continuelink,timeout);
       if (validateElement_Displayed(continuelink,timeout)) {
            click(continuelink,timeout);
            click(newbutton2,timeout);
        }else{
            Assert.fail("Unable to create patient details");
        }
        loadingBarChecker();
        stepInfoWithScreenshot("Able to create new Patient");
    }

    public void patientdetails(String name, String surname, String dateOfBirth, String gender) {
        setClientdetails(name,surname,dateOfBirth,gender);
        sendKeys(surnametextbox,this.surname,timeout);
        sendKeys(givennametextbox,this.name,timeout);
        sendKeys(gendertextbox,this.gender,timeout);
        click(findbutton);
        loadingBarChecker();
        if(!newPatient){
            awaitElement(firstpatientEpisodeSearch,timeout);
            click(firstpatientEpisodeSearch,timeout);
            loadingBarChecker();
            awaitElement(continuelink,timeout);
            click(continuelink);

            awaitElement(newbutton2,timeout);
             click(newbutton2,timeout);
        }else if(validateElement_Enabled_Displayed(newbutton,timeout)) {
            stepPassedWithScreenshot("The Patient list screen appears with no list");
            click(newbutton,timeout);
        }

        if(newPatient) {
            findEnterTab(gendertextbox, this.gender);
            findEnterTab(DateofBirth, this.dateOfBirth);
            click(DateofBirth,timeout);
            stepPassedWithScreenshot("User is directed to Lab Episode screen");
        }
        if(validateElement_Enabled_Displayed(saveAndclose,timeout)) {
            click(saveAndclose,timeout);
            loadingBarChecker();
            if(validateElement_Displayed(continuelink, timeout)){
                click(continuelink,timeout);
            } else if(validateElement_Displayed(newbutton2,timeout)){
                click(newbutton2,timeout);
            }

        }else if(!validateElement_Displayed(updatebuton,timeout)){
            Assert.fail("Unable to click save close button");
        }
        stepPassedWithScreenshot("Successfully User is directed to Patient Registration screen" );

    }

    public void doctorSelection()  {

        click(iconDoctorSearch,timeout);
        loadingBarChecker();
        awaitElement(firstrowdoctorsearch,timeout);
        click(firstrowdoctorsearch,timeout );
        stepInfoWithScreenshot("Selected Doctor on list");

    }
    public void doctorSelection(String doctor)  {
        if(doctor.isBlank()) {
            doctorSelection();
        }else{
            awaitElement(nameofDoctor, timeout);
            sendKeys(nameofDoctor, doctor);
            super._driver.findElement(nameofDoctor).sendKeys(Keys.TAB);
        }
    }


    public List<String> mutiplePatient(Faker faker, String[] testcollection, boolean receiveDate,boolean specimenSelect, int numberPatient, Boolean updateClient) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
            doctorSelection();
            labEspideonumber.add(collectiondetailnew("n-1",testcollection,receiveDate,specimenSelect,updateClient,"2100"));
        }

        return labEspideonumber;
    }

    public List<String> mutiplePatientEditSpecimen(Faker faker,String gender, String[] testcollection, boolean receiveDate,boolean specimenSelect, int numberPatient,String testSetFilter,String specimen,String container, Boolean updateClient) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),gender);
            doctorSelection();
            labEspideonumber.add(collectiondetailnewEditSpecimen("n-1",testcollection,receiveDate,specimenSelect,updateClient,testSetFilter,specimen,container,"4119"));
        }
        return labEspideonumber;
    }

    public String collectiondetailnewEditSpecimen(String patientKey, String collectiontime, String patienLocation , String[] testsetcollection, Boolean receiveDate, ArrayList<TestSetDetailsEntity> testSetlist, ArrayList<SpecimensEntity> specimensEntityList, ArrayList<EditTestSetEntity> editTestSetEntityArrayList) {

        sendKeys(patientSearchSelect,patienLocation.isBlank() ?"2100":patienLocation);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTimeTextbox,collectiontime.isBlank()? "n-1":collectiontime);
        super._driver.findElement(collectionTimeTextbox).sendKeys(Keys.TAB);

        if(receiveDate) {
            addReceivedDate(collectiontime);
        }
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            stepPassedWithScreenshot("Entered test set "+testset);
            if (validateElement_Displayed(supersetItemSelection)) {
                stepInfoWithScreenshot("Reach to Superset Item Selection");
                click(buttonSupersetItemSelectionAccept,timeout);
                loadingBarChecker();
            }
            testSetDetails(testSetlist,testset,patientKey);
            specimens(specimensEntityList,testset,patientKey);
            editTestList(testset,patientKey, editTestSetEntityArrayList);
            //dftTestset();


            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }

        return updateClientDetails();
    }

    private void testSetDetails(ArrayList<TestSetDetailsEntity> testSetlist, String testset, String patientkey){
        boolean checker= true;
        for(TestSetDetailsEntity testSetDetail:testSetlist){
            if(testset.contentEquals(testSetDetail.getTestSetSuperSet()) && testSetDetail.getPatient_Key().contentEquals(patientkey)) {
                specimenEditSpecimen(testSetDetail.getSpecimen());
                containerSpeciemen(testSetDetail.getContainer());
                //Need to add Volume collected
                //Need to add Volume Currently
                stepInfo("Entered specimen"+testSetDetail.getSpecimen()+ "and Container "+testSetDetail.getContainer());
                awaitElement(applybuttonSpecimenContainer,timeout);
                click(applybuttonSpecimenContainer,timeout);
               checker= false;
             }
        }

    if(checker) {
        for (TestSetDetailsEntity testSetDetail : testSetlist) {

                if (validateElement_Displayed(backtoLabEpisodeNav)) {
                    click(backtoLabEpisodeNav, timeout);
                    stepInfoWithScreenshot("Clicked back to Lab Episode");
                }
        }
    }

        }
   /*private  void dftTestset(String testSetName,String dynamicTestSet,ArrayList<DFT> dynamicFunctionTest ){
     //Need to build loop using list from spreadsheet
      for(DFT dft:dynamicFunctionTest) {

          if(dft.Patient_FK() != null) {

              By testsetdftbox = By.xpath("//parent::span[ contains(@ng-bind,'LBTS_TestSet_DR') and text()='" + testSetName + "']//ancestor::tr//a[contains(@ng-class,'DFTToggleLink')]");
              By dynamicfunctionTestlink = By.xpath("//a[text()='" + dynamicTestSet + "']");
              By div = By.xpath("//div[@id='tc_Home-misc-alert']");
              click(testsetdftbox, timeout);
              switchToFrame(iframehidden);
              click(dynamicfunctionTestlink, timeout);
              click(div);
              switchToDefaultContext();
          }
       }

   }*/

    private void specimens(ArrayList<SpecimensEntity> specimensEntityArrayList, String testset, String patientKey){
        for(SpecimensEntity specimensEntity : specimensEntityArrayList) {
            if (specimensEntity.getTestCode() != null) {
                if (specimensEntity.getTestCode().contentEquals(testset) && specimensEntity.getPatient_Key().contentEquals(patientKey)) {
                    click(selectlinkSpecimen,timeout);
                    switchToFrame(switchiFrame);
                    //need to click the tick box  and not un-tick  box
                    WebElement element= findOne(By.xpath("//parent::td[label[contains(text(),'" + specimensEntity.getSpecimens().trim() + "')]]//parent::tr//td//input[@type='checkbox']"));
                    if(!element.isSelected()) {
                        click(By.xpath("//parent::td[label[contains(text(),'" + specimensEntity.getSpecimens().trim() + "')]]//parent::tr//td//input[@type='checkbox']"));
                    }
                    click(acceptButton,timeout);
                    switchToDefaultContext();
                }
            }
        }
        }

    public String collectiondetailnewEditSpecimen(String collectiontime, String[] testsetcollection,Boolean receiveDate, Boolean specimenSelect, Boolean updateClient,String testSetFilter,String specimen,String container, String petientLocation) {
        //click(iconPatientSearch);
        String labepsiode ="";
        sendKeys(patientSearchSelect,petientLocation,timeout);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        loadingBarChecker();
        findEnterTab(collectionTimeTextbox,collectiontime);
        if(receiveDate) {
            addReceivedDate(collectiontime);
        }
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            if(validateElement_Displayed(supersetItemSelection,timeout)){
                stepInfoWithScreenshot("Reach to Superset Item Selection");
                click(buttonSupersetItemSelectionAccept,timeout);
            } else if (validateElement_Displayed(specimenContainereditSpan,timeout) && !specimenSelect){
                testSetFilter(testSetFilter);
                specimenEditSpecimen(specimen);
                containerSpeciemen(container);

                Assert.assertTrue("Unable to click apply button on Specimen Container",validateElement_Enabled_Displayed(applybuttonSpecimenContainer,timeout));
                click(applybuttonSpecimenContainer,timeout);
                loadingBarChecker();
               /* if(validateElement_Enabled_Displayed(selectlinkSpecimen)){
                    click(selectlinkSpecimen);
                    switchToFrame(switchiFrame);
                    click(acceptButton);
                    switchToDefaultContext();
                }*/
            }else if(specimenSelect){
                specimenSelect();
            }
            //validateElement_Enabled_Displayed(testSetLink,timeout);
            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }

        if(updateClient) {
            labepsiode = updateClientDetails();
        }
        return labepsiode;
    }
    public String collectiondetailnewEditSpecimen(String collectiontime,String[] recieveDate, String[] testsetcollection,Boolean receiveDate, Boolean specimenSelect, Boolean updateClient,String testSetFilter,String specimen,String container, String petientLocation) {

        String labepsiode ="";
        sendKeys(patientSearchSelect,petientLocation,timeout);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        loadingBarChecker();
        findEnterTab(collectionTimeTextbox,collectiontime);
        if(receiveDate) {
            addReceivedDate(recieveDate[0],recieveDate[1]);
        }
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            if(validateElement_Displayed(supersetItemSelection,timeout)){
                stepInfoWithScreenshot("Reach to Superset Item Selection");
                click(buttonSupersetItemSelectionAccept,timeout);
            } else if (validateElement_Displayed(specimenContainereditSpan,timeout) && !specimenSelect){
                testSetFilter(testSetFilter);
                specimenEditSpecimen(specimen);
                containerSpeciemen(container);

                Assert.assertTrue("Unable to click apply button on Specimen Container",validateElement_Enabled_Displayed(applybuttonSpecimenContainer,timeout));
                click(applybuttonSpecimenContainer,timeout);
                loadingBarChecker();
               /* if(validateElement_Enabled_Displayed(selectlinkSpecimen)){
                    click(selectlinkSpecimen);
                    switchToFrame(switchiFrame);
                    click(acceptButton);
                    switchToDefaultContext();
                }*/
            }else if(specimenSelect){
                specimenSelect();
            }
            //validateElement_Enabled_Displayed(testSetLink,timeout);
            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }

        if(updateClient) {
            labepsiode = updateClientDetails();
        }
        return labepsiode;
    }

    public void LinkSelectSpecimen(){
        loadingBarChecker();
        click(clickTestSetLink,timeout);
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        By linkedLabEpisode =By.xpath("//label[@id='LBEP_Numberz1']");
        for(WebElement element:find(tickboxSpecimens)){
            if(!element.isSelected()){
                element.click();
                stepPassedWithScreenshot("Selected specimen "+getText(linkedLabEpisode));
            }
        }
        click(acceptSpecimens);
        stepPassedWithScreenshot("Accepted Linked Test Set List "+testset);
        switchToDefaultContext();
        if(validateElement_Displayed(clickTestSetLink)) {
            scrollToElement(clickTestSetLink);
        }

    }


    public void addReceivedDate(String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        findEnterTab(receivedDateTextBox, dateTimeFormatter.format(LocalDate.now()));
        findEnterTab(receivedTimeTextBox,time);

    }
    public void addReceivedDate(String time,String receiveDate){
        findEnterTab(receivedDateTextBox, receiveDate);
        findEnterTab(receivedTimeTextBox,time);

    }
    public void addCollectionDate(String collectionDate,String time){
        findEnterTab(collectionDateTextbox, collectionDate);
        findEnterTab(collectionTimeTextbox,time);
    }
    public void searchPatient(String lapespido) throws InterruptedException {
                Thread.sleep(5000);
                sendKeys(labEpisodeNumber, lapespido,timeout);
                click(findButton,timeout);
                stepInfoWithScreenshot("Search for Patient "+ lapespido);
                if(validateElement_Displayed(continuelink,timeout)){
                        click(continuelink,timeout);
                }
                Thread.sleep(5000);
                By editTestsetlink = By.xpath("//parent::tr/td/div/span//span[text()='%s']//parent::span//parent::span//parent::span//parent::div//parent::td//parent::tr//td//a[not(contains(@id,'CreateCopy')) ]".replace("%s",lapespido));
                while(!validateElement_Displayed(editTestsetlink,timeout)){
                    scrollToElement(nextpageURN,timeout);
                    click(nextpageURN,timeout);
                    if(!validateElement_Displayed(nextpageURN,timeout)){
                        break;
                    }
                }

                if(validateElement_Displayed(editTestsetlink,timeout)){
                    click(editTestsetlink,timeout);
                }else{
                    click(editTestsetlink,timeout);
                }
    }
    public void searchPatientURN(String urn) throws InterruptedException {
        findOne(registrationNo).clear();
        sendKeys(registrationNo, urn);
        stepInfoWithScreenshot("Entered Unique Registration Number "+ urn);

        click(findButton);

    }

    public void specimenContainerList(String anatomical,String lesion,String anatomicalSite) throws InterruptedException {
        int y = 0;
        for(String element:specimenNumberExtract(true)){
           click( By.xpath("//*[@id='LBEpisode_SpecimenContainer_List_"+y+"-row-"+y+"-item-Edit-image']"));

            if(!lesion.isBlank()) {
                sendKeys(By.xpath("//input[@name='LBSPCLesionDR']"), lesion);
                click(By.xpath("//input[@name='LBSPCLesionDR']"));
                By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0-item-LBSPCLesionDR-lookupRow-0-value-0') and contains(text(),'')]");
                Thread.sleep(3000);
                if(validateElement_Displayed(lookupOnList)){
                    click(lookupOnList);
                }else{
                    super._driver.findElement(By.xpath("//input[@name='LBSPCLesionDR']")).sendKeys(Keys.TAB);
                }

                stepInfoWithScreenshot("Entered Lesion "+lesion);
            }

               if(!anatomical.isBlank()){
                   sendKeys(By.xpath("//input[@name='LBSPCAnatomicalSiteDR']"),anatomical);
                   By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0-item-LBSPCAnatomicalSiteDR-lookupRow-0-value-0') and contains(text(),'')]");
                   Thread.sleep(3000);
                   if(validateElement_Displayed(lookupOnList)){
                       click(lookupOnList);
                   }else{

                   }
                   stepInfoWithScreenshot("Entered anatomical "+anatomical);

               }

               if(!anatomicalSite.isBlank()) {
                   sendKeys(By.xpath("//input[@name='LBSPCAnatomicalSiteQualifierDR']"), anatomicalSite);
                   super._driver.findElement(By.xpath("//input[@name='LBSPCAnatomicalSiteQualifierDR']")).sendKeys(Keys.TAB);
                    Thread.sleep(3000);
                   stepInfoWithScreenshot("Entered anatomical Site "+anatomicalSite);
               }


               click(applybuttonSpecimenContainer);

               stepInfoWithScreenshot("Clicked applyed button");

            y++;
           }
        Thread.sleep(3000);
        click(updatebuton, timeout);

    }

    public ArrayList<ArrayList<String>> searchMutliplePatient(List<String> lapsiode) throws InterruptedException {
        ArrayList<ArrayList<String>> speciemenlist = new ArrayList<>();
        for(String labnumber: lapsiode) {
            searchPatient(labnumber);
            speciemenlist.add(specimenNumberExtract(true));
            if (validateElement_Enabled_Displayed(backtoPatient,timeout)){
                click(backtoPatient,timeout);
                if(validateElement_Enabled_Displayed(backtoPatient,timeout)){
                    click(backtoPatient,timeout);
                    click(backtoPatient,timeout);
                }

                }
        }
        return speciemenlist;
    }

    public ArrayList<ArrayList<String>> searchMutliplePatients(List<String> lapsiode) throws InterruptedException {
        ArrayList<ArrayList<String>> speciemenlist = new ArrayList<>();
        for(String labnumber: lapsiode) {
            searchPatient(labnumber);
            speciemenlist.add(specimenNumberExtracts(true));
            if (validateElement_Enabled_Displayed(backtoPatient,timeout)){
                click(backtoPatient,timeout);
                if(validateElement_Enabled_Displayed(backtoPatient,timeout)){
                    click(backtoPatient,timeout);
                    click(backtoPatient,timeout);
                }

            }
        }
        return speciemenlist;
    }

    public HashMap<String ,ArrayList<String>> searchMutliplePatientKeys(List<String> lapsiode) throws InterruptedException {
        HashMap<String,ArrayList<String>> speciemenlist = new HashMap<>();
        for(String labnumber: lapsiode) {
            searchPatient(labnumber.split(",")[1]);
            speciemenlist.put(labnumber.split(",")[0],specimenNumberExtract(true));
            if (validateElement_Enabled_Displayed(backtoPatient,timeout)){
                click(backtoPatient,timeout);
                click(backtoPatient,timeout);
                click(backtoPatient,timeout);
            }
        }
        return speciemenlist;
    }

    public List<String> addMultipleSpecimen(String testSetFilter,String specimen,String container,int numberSpecimen,boolean checkspecimen,boolean addSpecimen){
        if(addSpecimen) {
            for (int x = 0; x <= numberSpecimen - 1; x++) {
                if (validateElement_Enabled_Displayed(addSpecimenButton, timeout)) {
                    scrollToElement(addSpecimenButton);
                    click(addSpecimenButton);
                    testSetFilter(testSetFilter);
                    specimenEditSpecimen(specimen);
                    containerSpeciemen(container);
                    click(applybuttonSpecimenContainer);
                }
            }
        }else{
            testSetFilter(testSetFilter);
            specimenEditSpecimen(specimen);
            containerSpeciemen(container);
            click(applybuttonSpecimenContainer);

        }
        return  specimenNumberExtract(checkspecimen);
    }


    public void specimenEditSpecimen(String specimen) {
        findOne(specimenContainerEdit).sendKeys(specimen);
        By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", specimen));
        if(findOne(specimenContainerEdit).getAttribute("value").contentEquals(specimen)){
            super._driver.findElement(specimenContainerEdit).sendKeys(Keys.TAB);
            stepInfoWithScreenshot("Entered container "+specimen);
            loadingBarChecker();
        }else{
            Assert.fail("Unable to find specimen "+specimen);
        }
    }

    public void testSetFilter(String testSetFilter) {
        if(!testSetFilter.isBlank()) {
            JavascriptExecutor jExecutor = (JavascriptExecutor) this._driver;
            jExecutor.executeScript("arguments[0].textContent = arguments[1];", findOne(testSetTextfield), testSetFilter);

            if (findOne(testSetTextfield).getText().contains(testSetFilter)) {
                stepInfoWithScreenshot("Entered test Set Filter " + testSetFilter);
            } else {
                Assert.fail("Unable to find Test Set Filter " + testSetFilter);
            }
        }
    }

    public void containerSpeciemen(String container){

         findOne(specimenContainerTextField).sendKeys(container);
        By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", container));
        if(findOne(specimenContainerTextField).getAttribute("value").contentEquals(container)){
            super._driver.findElement(specimenContainerTextField).sendKeys(Keys.TAB);
            stepInfoWithScreenshot("Entered container "+container);
            loadingBarChecker();
        }else{
            Assert.fail("Unable to find Container Specimen "+container);
        }
    }

    public void volumeCollected(String volume){
        findOne(volumeCollectedTextBox,timeout).sendKeys(volume);
        if(!getAttribute(volumeCollectedTextBox,"value",timeout).isBlank()){
            stepInfo("Entered Volume Collected "+getAttribute(volumeCollectedTextBox,"value",timeout));
        }

    }

    public ArrayList<String> specimenNumberExtracts(boolean checkspecimen){
        ArrayList<String> values =  new ArrayList<>();
        if(checkspecimen) {
            if (validateElement_Displayed(specimenText,timeout) ) {
                scrollToElement(specimenText,timeout);
                for (WebElement element : find(specimenText,timeout)) {
                        values.add(element.getAttribute("value"));

                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            } else if(validateElement_Displayed(specimenNumberTextUnedit,timeout)) {
                scrollToElement(specimenNumberTextUnedit,timeout);
                for (WebElement element : find(specimenNumberTextUnedit,timeout)) {
                    values.add(element.getText());
                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            }else{
                Assert.fail("Unable to Find specimen Number");
            }
        }
        return null;
    }

    public ArrayList<String> specimenNumberExtract(boolean checkspecimen){
        ArrayList<String> values =  new ArrayList<>();
        if(checkspecimen) {
            if (validateElement_Displayed(specimenAnatomicalSiteWithTestSet,timeout)) {
                scrollToElement(reportTitle,timeout);
                for (WebElement element : find(specimenAnatomicalSiteWithTestSet,timeout)) {
                    if(element.getText().isBlank()) {
                        values.add(element.getAttribute("value").trim());
                    }else{
                        values.add(element.getText().trim());
                    }

                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            } else if(validateElement_Displayed(specimenNumberUnedit,timeout)) {
                scrollToElement(specimenNumberUnedit,timeout);
                for (WebElement element : find(specimenNumberUnedit,timeout)) {
                    values.add(element.getText());
                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            }else{
                Assert.fail("Unable to Find specimen Number");
            }
        }
        return null;
    }
    public ArrayList<String> specimenNumbersExtract(boolean checkspecimen){
        ArrayList<String> values =  new ArrayList<>();
        if(checkspecimen) {
            if (validateElement_Displayed(specimenAnatomicalSiteWithTestSet,timeout)) {
                scrollToElement(reportTitle,timeout);
                var ted = find(specimenNumbers,timeout).size();
                for (WebElement element : find(specimenNumbers,timeout)) {
                    if(element.getText().isBlank()) {
                        values.add(element.getAttribute("value").trim());
                    }else{
                        values.add(element.getText().trim());
                    }

                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            } else if(validateElement_Displayed(specimenNumberUnedit,timeout)) {
                scrollToElement(specimenNumberUnedit,timeout);
                for (WebElement element : find(specimenNumberUnedit,timeout)) {
                    values.add(element.getText());
                }
                stepInfoWithScreenshot("Able to receive Specimen Number " + values);
                return values;
            }else{
                Assert.fail("Unable to Find specimen Number");
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> specimenNumberList(ArrayList<String> listLapEpisode) throws InterruptedException {
        ArrayList<ArrayList<String>> specimenArrayList = new ArrayList<>();
        for(String labEpisode:listLapEpisode){
            searchPatient(labEpisode);
            specimenArrayList.add(specimenNumberExtract(true));
        }
        return specimenArrayList;
    }

    public List<String> mutiplePatientWithDifferentTestset(Faker faker,String[] testcollection,Boolean specimenSelect) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=testcollection.length-1;x++){
                patientdetails(faker.name().name(), faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11, 55)), faker.demographic().sex());
                doctorSelection();
                labEspideonumber.add(collectionDetailWithMultipleTestSet("n-1", testcollection[x].split(","), specimenSelect,"2100"));
        }

        return labEspideonumber;

    }

    public void findEnterTab(By by, String input) {
        awaitElement(by,timeout);
        sendKeys(by,input,timeout);
        click(by,timeout);

    }
    public static void writeLabEpisodesIntoFile(ArrayList<String> labEpisodes) {
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\LabEpisodes.text");
            for (String labEpisodeNumber : labEpisodes)
            {
                myWriter.write(labEpisodeNumber + "\n");
                System.out.println(labEpisodeNumber);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeShipmentNumberIntoFile(String shipmentNum) {
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\shipmentNumbers.text");

                myWriter.write(shipmentNum );
                System.out.println(shipmentNum);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeShipmentNumbersIntoFile(ArrayList<String> shipmentNum) {
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\shipmentNumbers.text");
            for (String shipmentNumber : shipmentNum)
            {
                myWriter.write(shipmentNumber + "\n");
                System.out.println(shipmentNumber);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public  ArrayList<String> getLabEpisodesFromFile() {
        try {
            File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\LabEpisodes.text");
            FileReader fr = new FileReader(file);
            BufferedReader BR = new BufferedReader(fr);
            String Content = "";

            //Loop to read all lines one by one from file and print It.
            while((Content = BR.readLine())!= null) {
                labEpisodesNumber.add(Content);
                System.out.println(Content);
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  labEpisodesNumber;
    }
    public  String getShipmentNumberFromFile() {
        try {
            File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\shipmentNumbers.text");
            FileReader fr = new FileReader(file);
            BufferedReader BR = new BufferedReader(fr);
            shipmentNumber = BR.readLine();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  shipmentNumber;
    }
    public  void registerPatient(){

        //nextpageURN
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setClickBackboneLabEpisodeSelect(boolean clickBackboneLabEpisodeSelect){ this.clickBackboneLabEpisodeSelect =clickBackboneLabEpisodeSelect;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setNewPatient(boolean newPatient) {
        this.newPatient = newPatient;
    }
}
