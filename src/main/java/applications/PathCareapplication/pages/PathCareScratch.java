package applications.PathCareapplication.pages;

import Roman.Roman;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

public class PathCareScratch extends AbstractPage {

    private final By surnametextbox = By.xpath("//input[@name='PAPERName']");
    private final By givennametextbox = By.xpath("//input[@name='PAPERName2']");
    private final By findbutton = By.xpath("//button[text()='Find']");
    private final By newbutton = By.xpath("//a[text()='New']");
    private final By gendertextbox = By.xpath("//input[@name='CTSEXDesc']");
    //need to change
    private final By selectGender = By.xpath("//span[text()='Indeterminate']");
    //dd/mm/yyyy
    private final By DateofBirth = By.xpath("//input[@name='PAPERDob']");
    private final By updateButton = By.xpath("//button[text()='Update']");
    private By iconDoctorSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPReferringDoctorDR-lookupIcon']");

    private By firstrowdoctorsearch  = By.xpath("//a[@id='PACRefDoctor_CustomFind_0-row-0-item-FullDoctorName-link']");
    private By iconPatientSearch = By.xpath("//md-icon[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupIcon']");
    private By patienSearchSelect = By.xpath("//span[@id='LBEpisode_Edit_0-item-LBEPPatientLocationDR-lookupRow-0-value-0']");

    private By collectionTime =By.xpath("//input[@name='LBEPCollectionTime']");
    private By testSetCollection = By.xpath("//input[@name='TestSetSuperset']");
    private By labEspiodeNum = By.xpath("//div[contains(text(),'Lab Episode Number:')]");

    private  By updatebuton = By.xpath("//button[@type='submit']");

    public String collectiondetailnew(String collectiontime, String testsetcollection){
        click(iconPatientSearch);
        click(patienSearchSelect);
        findOne(collectionTime,collectiontime);
        findOne(testSetCollection,testsetcollection);
        stepPassedWithScreenshot("The correct Test Set is appears under Tests");
        click(updatebuton);
        stepPassedWithScreenshot("Label printed successfully");
        return getText(labEspiodeNum,5).replace("Lab Episode Number: ","");

    }

    public void patientdetails(String name, String surname, String dateofBirth, String gender){
        sendKeys(surnametextbox,name);
        sendKeys(givennametextbox,surname);
        click(findbutton);
        stepPassedWithScreenshot("The Patient list screen appears with no list");
        click(newbutton);
        findOne(gendertextbox,gender);
        findOne(DateofBirth,dateofBirth);
        click(DateofBirth);
        stepPassedWithScreenshot("User is directed to Lab Episode screen");
        click(updateButton);
        stepPassedWithScreenshot("Successfully User is directed to Patient Registration screen" );

    }

    public void DoctorSelection(){
        click(iconDoctorSearch);
        click(firstrowdoctorsearch);


    }


    public void findOne(By by,String input) {
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.ENTER);
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
        return false;
    }
}
