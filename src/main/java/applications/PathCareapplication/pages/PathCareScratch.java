package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.EditTestSet;
import applications.PathCareapplication.models.LabespideData;
import applications.PathCareapplication.models.Specimens;
import applications.PathCareapplication.models.TestSetDetails;
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
import java.util.HashMap;
import java.util.List;

public class PathCareScratch extends AbstractPage {

    private final By switchiFrame = By.xpath("//iframe[@name='TRAK_main']");
    private final By surnametextbox = By.xpath("//input[@name='PAPERName']");
    private final By givennametextbox = By.xpath("//input[@name='PAPERName2']");
    private final By findbutton = By.xpath("//button[text()='Find']");
    private final By newbutton = By.xpath("//a[text()='New']");
    private final By newbutton2 = By.xpath("//button[text()='New']");
    private final By gendertextbox = By.xpath("//input[@name='CTSEXDesc']");
    //dd/mm/yyyy
    private final By DateofBirth = By.xpath("//input[@name='PAPERDob']");
    private final By saveAndclose = By.xpath("//button[text()='Save & Close']");
    private final By iconDoctorSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPReferringDoctorDR-lookupIcon']");
    private final By nameofDoctor = By.xpath("//input[@name='LBEPReferringDoctorDR']");

    private final By firstrowdoctorsearch  = By.xpath("//a[@id='PACRefDoctor_CustomFind_0-row-0-item-FullDoctorName-link']");
    private final By iconPatientSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupIcon']");
    private final By patientSearchSelect = By.xpath("//input[@name= 'LBEPPatientLocationDR' ]");
    private final By testSetLink = By.xpath("//span[text()='Test Set Link']");
    private final By collectionTime =By.xpath("//input[@name='LBEPCollectionTime']");
    private final By testSetCollection = By.xpath("//input[@name='TestSetSuperset']");
    private final By backtoLabEpisodeNav = By.xpath("//a[text()='Back to: Lab Episode']");
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

    private final By specimenN = By.xpath("//input[contains(@ng-model,'LBSPC_SpecimenNumber')]");

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

    private final By loadingBar = By.xpath("//div[@class='bar']");

    private final By bodydom = By.xpath("//body[@ng-app='tcApp']");

    private final By editQuestionHideCollection =By.xpath("//md-icon[contains(@id,'LBTSSHHideForCollection') and not(@class)]");
    private final By editQuestionHideProcessing = By.xpath("//md-icon[contains(@id,'LBTSSHHideForProcessing') and not(@class)]");
    private final By getEditQuestionHideReceive = By.xpath("//md-icon[contains(@id,'LBTSSHHideForReceive') and not(@class)]");
    private final By addtionalQuestionSpecimenContainer = By.xpath("//input[contains(@name,'QA_ComboBoxz1') ]");
    private final By addtionalQuestionSlides = By.xpath("//input[contains(@name,'QA_Integerz2') ]");
    private final By addtionalQuestionApprox = By.xpath("//input[contains(@name,'QA_Integerz3') ]");
    private final By addtionalQuestionmarcodesciption = By.xpath("//input[contains(@name,'QA_ComboBoxz4') ]");
    private final By addtionalQuestionmEnterOfOxygen = By.xpath("//input[contains(@name,'QA_TextBoxz6')]");
    private final By addtionalQuestionmTimeBloodgasTaken = By.xpath("//input[contains(@name,'QA_Timez5')]");

    private final By continuelink = By.xpath("//a[text()='Continue']");

    private  By testdieditfield = By.xpath("//a[text()='%s']//following::md-input-container[@class='tcNumeric']//input[contains(@name,'QA')]");

    public  String testset ="";
    private boolean newPatient = true;

    private int timeout = 20;

    private boolean clickBackboneLabEpisodeSelect = true;

    private String name;
    private String dateOfBirth;
    private String surname;
    private String gender;

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

    public String collectiondetailnew(String collectiontime, String[] testsetcollection,Boolean receiveDate, Boolean specimenSelect, Boolean updateClient) throws InterruptedException {
       //click(iconPatientSearch);
        String labepsiode ="";
        sendKeys(patientSearchSelect,"2100");
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
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
        if(validateElement_Enabled_Displayed(updatebuton,10)) {
            loadingBarChecker();
            click(updatebuton, 15);
            stepInfo("Successfully clicked Update button "+updatebuton);

        } else {
            Assert.fail("Unable to receive Lap Episode Number");
        }
            String lab_episode = getText(labEspiodeNum, timeout).replace("Lab Episode Number: ", "");
            stepPassedWithScreenshot("Label printed successfully " + lab_episode);
            LabespideData labespideData = new LabespideData(lab_episode);
            labespideData.setTestset(testset);

        return lab_episode;
    }
    public void loadingBarChecker(){
        int x = 5;
        while(validateElement_Displayed(loadingBar,timeout)|| !validateElement_Displayed(bodydom,timeout)){
            x--;
            stepInfo("Page still loading");
            if(x==0){
                break;
            }
        }
    }
    public void specimenSelect(){
        if(clickBackboneLabEpisodeSelect){click(backtoLabEpisodeNav);}
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

    public void editTestList(String testset, String pk,ArrayList<EditTestSet> editTestSetArrayList){

                for(EditTestSet editTestSet:editTestSetArrayList){
                    if(editTestSet.getPk()!=null){
                        if(pk.contentEquals(pk)){
                            if(editTestSet.getTestcode().contentEquals(testset)){
                                if(validateElement_Enabled_Displayed(editPencil,timeout)){
                                    loadingBarChecker();
                                    scrollToElement(editPencil);
                                    //Need to fix by aliging the testset with the pencil icon
                                    find(editPencil,timeout).get(0).click();
                                }else{
                                    Assert.fail("Unable to click Edit Pencil");
                                }
                                specialHandling(editTestSet.testSetSpecialHandling);
                                additionalQuestion(editTestSet.additionalTestSetQuestions);
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
    public void specialHandling( EditTestSet.TestSetSpecialHandling testSetSpecialHandling){
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

    public void additionalQuestion(EditTestSet.AdditionalTestSetQuestions additionalTestSetQuestions){
        if(!additionalTestSetQuestions.equals(null)) {
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

    public void setClientdetails(String name, String surname, String dateOfBirth,String gender){
        setName(name);
        setSurname(surname);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
    }

    public void uncreatedSamePatient() throws InterruptedException {
        loadingBarChecker();
        Thread.sleep(4000);
        click(firstpatientEpisodeSearch,timeout);
        if(validateElement_Displayed(newbutton2)){
            click(newbutton2,timeout);
        }else if (validateElement_Displayed(continuelink))
        {
            click(continuelink);
        }
        stepInfo("Able to create new Patient");
    }

    public void patientdetails(String name, String surname, String dateOfBirth, String gender) throws InterruptedException {
        sendKeys(surnametextbox,name,timeout);
        sendKeys(givennametextbox,surname,timeout);
        sendKeys(gendertextbox,gender,timeout);
        setClientdetails(name,surname,dateOfBirth,gender);
        click(findbutton);
        if(!newPatient){
            loadingBarChecker();
            Thread.sleep(4000);
            click(firstpatientEpisodeSearch,timeout);
            loadingBarChecker();
            if(validateElement_Displayed(continuelink,timeout)){
                click(continuelink);
            }
            if(validateElement_Displayed(newbutton2,timeout)){
                click(newbutton2,timeout);
            }
        }else if(validateElement_Enabled_Displayed(newbutton,timeout)) {
            stepPassedWithScreenshot("The Patient list screen appears with no list");
            click(newbutton,timeout);
        }

        if(newPatient) {
            findEnterTab(gendertextbox, gender);
            findEnterTab(DateofBirth, dateOfBirth);
            click(DateofBirth);
            stepPassedWithScreenshot("User is directed to Lab Episode screen");
        }
        if(validateElement_Enabled_Displayed(saveAndclose,timeout)) {
            click(saveAndclose);
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

    public void doctorSelection() throws InterruptedException {

        click(iconDoctorSearch,timeout);
        loadingBarChecker();
        click(firstrowdoctorsearch,timeout );
        Thread.sleep(4000);
        stepInfoWithScreenshot("Selected Doctor on list");

    }
    public void doctorSelection(String doctor){
        sendKeys(nameofDoctor,doctor);
        super._driver.findElement(nameofDoctor).sendKeys(Keys.TAB);

    }


    public List<String> mutiplePatient(Faker faker, String[] testcollection, boolean receiveDate,boolean specimenSelect, int numberPatient, Boolean updateClient) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
            doctorSelection();
            labEspideonumber.add(collectiondetailnew("n-1",testcollection,receiveDate,specimenSelect,updateClient));
        }

        return labEspideonumber;
    }

    public List<String> mutiplePatientEditSpecimen(Faker faker,String gender, String[] testcollection, boolean receiveDate,boolean specimenSelect, int numberPatient,String testSetFilter,String specimen,String container, Boolean updateClient) throws InterruptedException {
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),gender);
            doctorSelection();
            labEspideonumber.add(collectiondetailnewEditSpecimen("n-1",testcollection,receiveDate,specimenSelect,updateClient,testSetFilter,specimen,container));
        }
        return labEspideonumber;
    }

    public String collectiondetailnewEditSpecimen(String patientKey, String collectiontime, String patienLocation , String[] testsetcollection, Boolean receiveDate, ArrayList<TestSetDetails> testSetlist, ArrayList<Specimens> specimensList, ArrayList<EditTestSet> editTestSetArrayList) {

        sendKeys(patientSearchSelect,patienLocation);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
        super._driver.findElement(collectionTime).sendKeys(Keys.TAB);
        if(receiveDate) {
            addReceivedDate(collectiontime);
        }
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            if (validateElement_Displayed(supersetItemSelection)) {
                stepInfoWithScreenshot("Reach to Superset Item Selection");
                click(buttonSupersetItemSelectionAccept);
                loadingBarChecker();
            }
            testSetDetails(testSetlist,testset,patientKey);
            specimens(specimensList,testset,patientKey);
            editTestList(testset,patientKey,editTestSetArrayList);


            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }


        return updateClientDetails();
    }

    private void testSetDetails(ArrayList<TestSetDetails> testSetlist, String testset,String patientkey){
        boolean checker= true;
        for(TestSetDetails testSetDetail:testSetlist){
            if(testSetDetail.getEnter().toLowerCase().contentEquals("yes") && testset.contentEquals(testSetDetail.getTestSetSuperSet()) && testSetDetail.getPatient_Key().contentEquals(patientkey)) {
                specimenEditSpecimen(testSetDetail.getSpecimen());
                containerSpeciemen(testSetDetail.getContainer());
                click(applybuttonSpecimenContainer);
               checker= false;
             }
        }

    if(checker) {
        for (TestSetDetails testSetDetail : testSetlist) {
            if (testSetDetail.getEnter().toLowerCase().contentEquals("no")) {
                if (validateElement_Displayed(backtoLabEpisodeNav)) {
                    click(backtoLabEpisodeNav, timeout);
                    stepInfoWithScreenshot("Clicked back to Lab Episode");
                }
            }
        }
    }

        }
    private void specimens(ArrayList<Specimens> specimensArrayList,String testset,String patientKey){
        for(Specimens specimens:specimensArrayList) {
            if (specimens.getTestCode() != null) {
                if (specimens.getTestCode().contentEquals(testset) && specimens.getPatient_Key().contentEquals(patientKey)) {
                    click(selectlinkSpecimen);
                    switchToFrame(switchiFrame);
                    click(By.xpath("//parent::td[label[contains(text(),'" + specimens.getSpecimens().trim() + "')]]//parent::tr//td//input[@type='checkbox']"));
                    click(acceptButton);
                    switchToDefaultContext();
                }
            }
        }
        }

    public String collectiondetailnewEditSpecimen(String collectiontime, String[] testsetcollection,Boolean receiveDate, Boolean specimenSelect, Boolean updateClient,String testSetFilter,String specimen,String container) {
        //click(iconPatientSearch);
        String labepsiode ="";
        sendKeys(patientSearchSelect,"2100",timeout);
        super._driver.findElement(patientSearchSelect).sendKeys(Keys.TAB);
        findEnterTab(collectionTime,collectiontime);
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
                testSetFilter(testSetFilter);
                specimenEditSpecimen(specimen);
                containerSpeciemen(container);

                Assert.assertTrue("Unable to click apply button on Specimen Container",validateElement_Enabled_Displayed(applybuttonSpecimenContainer));
                click(applybuttonSpecimenContainer);
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

    public void searchPatient(String lapespido) throws InterruptedException {
                Thread.sleep(3000);
                sendKeys(labEpisodeNumber, lapespido,timeout);
                click(findButton,timeout);
                stepInfoWithScreenshot("Search for Patient "+ lapespido);
                if(validateElement_Displayed(continuelink,timeout)){
                        click(continuelink,timeout);
                }
                Thread.sleep(5000);
                By editTestsetlink = By.xpath("//parent::tr/td/div/span//span[text()='%s']//parent::span//parent::span//parent::span//parent::div//parent::td//parent::tr//td//a[not(contains(@id,'CreateCopy')) ]".replace("%s",lapespido));
                if(validateElement_Displayed(editTestsetlink,timeout)){
                    click(editTestsetlink,timeout);
                }else{
                    click(editTestsetlink,timeout);
                }
    }
    public void searchPatientURN(String urn) throws InterruptedException {
        findOne(registrationNo).clear();
        sendKeys(registrationNo, urn);
        click(findButton);
        stepInfoWithScreenshot("Entered Unique Registration Number "+ urn);
        Thread.sleep(3000);
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

    public HashMap<String ,ArrayList<String>> searchMutliplePatientKeys(List<String> lapsiode) throws InterruptedException {
        HashMap<String,ArrayList<String>> speciemenlist = new HashMap<>();
        for(String labnumber: lapsiode) {
            searchPatient(labnumber.split(",")[1]);
            speciemenlist.put(labnumber.split(",")[0],specimenNumberExtract(true));
            if (validateElement_Enabled_Displayed(backtoPatient,timeout)){
                click(backtoPatient,timeout);
            }
        }
        return speciemenlist;
    }

    public List<String> addMultipleSpecimen(String testSetFilter,String specimen,String container,int numberSpecimen,boolean checkspecimen,boolean addSpecimen){
        if(addSpecimen) {
            for (int x = 0; x <= numberSpecimen - 1; x++) {
                if (validateElement_Enabled_Displayed(addSpecimenButton, 10)) {
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
                labEspideonumber.add(collectionDetailWithMultipleTestSet("n-1", testcollection[x].split(","), specimenSelect));
        }

        return labEspideonumber;

    }



    public void findEnterTab(By by, String input) {
        validateElement_Enabled_Displayed(by,timeout);
        super.sendKeys(by,input,timeout);
        super.findOne(by,timeout).click();

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
