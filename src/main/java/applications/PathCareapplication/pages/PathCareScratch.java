package applications.PathCareapplication.pages;

import Roman.Roman;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

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
    private final By patienSearchSelect = By.xpath("//span[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupRow-0-value-0']");
    private final By testSetLink = By.xpath("//span[text()='Test Set Link']");
    private final By collectionTime =By.xpath("//input[@name='LBEPCollectionTime']");
    private final By testSetCollection = By.xpath("//input[@name='TestSetSuperset']");
    private final By backtoLabEpisodeNav = By.xpath("//a[text()='Back to: Lab Episode']");
    private final By labEspiodeNum = By.xpath("//div[contains(text(),'Lab Episode Number:')]");
    private final By specimensearch = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupIcon']");

    private final By applybuttonSpecimenContainer = By.xpath("//button[text()='Accept']");
    private final By specimenLookup = By.xpath("//span[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCSpecimenDR-lookupRow-0-value-1']");

    private final By specimenContainerlookup = By.xpath("//span[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupRow-0-value-1']");
    private final By specimenContainerSearch = By.xpath("//md-icon[@id='LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-lookupIcon']");
    private final By specimenContainer = By.xpath("//div[@id=LBSpecimenContainer_Msg_Edit_0-item-LBSPCContainerDR-matchesWrapper']");
    private final By testsetrequimenttext = By.xpath("//span[text()='At least one test set is required']");
    private final By selectlinkSpecimen = By.xpath("//a[text()='Select']");
    private final By acceptButton = By.xpath("//input[@name='accept1']");

    private final  By updatebuton = By.xpath("//button[@type='submit']");
    private  By testCode = By.xpath("//span[text()='%s']");

    public  String testset ="";

    public String collectiondetailnew(String collectiontime, String[] testsetcollection)  {
        click(iconPatientSearch);
        click(patienSearchSelect);
        findOne(collectionTime,collectiontime);
        for (String testset:testsetcollection) {
            setTestset(testset);
            testCode = By.xpath("//span[text()='%s']".replace("%s",testset));
            sendKeys(testSetCollection,testset);
            super._driver.findElement(testSetCollection).sendKeys(Keys.TAB);
            if(validateElement_Displayed(backtoLabEpisodeNav)){
                click(specimensearch);
                click(specimenLookup);
                click(specimenContainerSearch);
                click(specimenContainerlookup);

                Assert.assertTrue(validateElement_Enabled_Displayed(applybuttonSpecimenContainer));
                click(applybuttonSpecimenContainer);
                if(validateElement_Enabled_Displayed(selectlinkSpecimen)){
                    click(selectlinkSpecimen);
                    switchToFrame(switchiFrame);
                    click(acceptButton);
                    switchToDefaultContext();
                }

            }
            validateElement_Enabled_Displayed(testSetLink,10);
            stepPassedWithScreenshot("The correct Test Set appears under Tests : "+testset);
        }

        click(updatebuton);
        stepPassedWithScreenshot("Label printed successfully " +getText(labEspiodeNum,20).replace("Lab Episode Number: ",""));
        return getText(labEspiodeNum,10).replace("Lab Episode Number: ","");

    }
    public Boolean updatewithoutTestCollection(String collectiontime){
        click(iconPatientSearch);
        click(patienSearchSelect);
        findOne(collectionTime,collectiontime);
        click(updatebuton);
         if(validateElement_Displayed(testsetrequimenttext,5)){

             validateElement_Displayed(findbutton,4);
             stepPassedWithScreenshot("Successfully displayed: Error message appears  \"At least one test set is required\"");
             return true;
         }
         return false;
    }

    public void patientdetails(String name, String surname, String dateBirth, String gender){
        sendKeys(surnametextbox,name);
        sendKeys(givennametextbox,surname);
        click(findbutton);
        stepPassedWithScreenshot("The Patient list screen appears with no list");
        click(newbutton);
        findOne(gendertextbox,gender);
        findOne(DateofBirth,dateBirth);
        click(DateofBirth);
        stepPassedWithScreenshot("User is directed to Lab Episode screen");
        click(saveAndclose);
        stepPassedWithScreenshot("Successfully User is directed to Patient Registration screen" );

    }

    public void doctorSelection(){
        click(iconDoctorSearch,15);
        click(firstrowdoctorsearch,10);

    }


    public List<String> mutiplePatient(Faker faker,String[] testcollection, int numberPatient){
        List<String> labEspideonumber = new ArrayList<>();
        for(int x=0;x<=numberPatient-1;x++){
            patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002",faker.demographic().sex());
            doctorSelection();
            labEspideonumber.add(collectiondetailnew("n-1",testcollection));
        }

        return labEspideonumber;

    }


    public void findOne(By by,String input) {

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
