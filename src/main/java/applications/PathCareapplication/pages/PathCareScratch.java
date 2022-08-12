package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import selenium.AbstractPage;

public class PathCareScratch extends AbstractPage {

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
    private final By labEspiodeNum = By.xpath("//div[contains(text(),'Lab Episode Number:')]");

    private final By testsetrequimenttext = By.xpath("//span[text()='At least one test set is required']");

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
            click(testCode,10);
            validateElement_Enabled_Displayed(testSetLink,10);
            stepPassedWithScreenshot("The correct Test Set is appears under Tests : "+testset);
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
        click(iconDoctorSearch,10);
        click(firstrowdoctorsearch,10);


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
