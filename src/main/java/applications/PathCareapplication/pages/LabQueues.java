package applications.PathCareapplication.pages;

import Roman.Roman;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.AbstractPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LabQueues extends AbstractPage {

    private final By queueType = By.xpath("//select[@id='QueueTypeList']");

    private final By switchiFrame = By.xpath("//iframe[@id='TRAK_main']");

    private final By deparmentlist = By.xpath("//select[@id='DepartmentList']");
    private final By findbutton = By.xpath("//input[@name='find1']");
    private final By testSetLabSite = By.xpath("//input[@id='TestSetLabSite']");
    private final By findbuttonLab = By.xpath("//input[@id='find1']");
    private final By saveSearch = By.xpath("//a[@id='SaveSearch']");
    private final By SearchResults = By.xpath("//table[@id='tLBVerificationQueueGrid_List']");
    private final By descriptionText = By.xpath("//input[@id='SRCHDesc']");
    private final By descriptionTable  = By.xpath("//a[@id='SRCHDescz1']");
    private final By updateLabQueuesSearch = By.xpath("//input[@id='update1']");
    private final By savedSearch = By.name("SavedSearches");
    private final By textboxDespiption =By.id("Description");
    private final By switchiframeEdit = By.id("TRAK_info");
    private final By nextpagequeue = By.xpath("//a[@id='NextPage_LBVerificationQueue_TestSets_FindList']");
    private By clinicalResultEpisode = By.xpath("//label[text()='%s']");
    private  final By totalResults = By.xpath("//a[@id='totalCountz1']");
    private final By tableSearchResult = By.xpath("//table[@name='tLBVerificationQueueGrid_List']");

    private List<String> allSearchelement  =new ArrayList<>();






    public LabQueues(Roman roman) {
        super(roman);
    }



//Query Search Save
    public boolean querySearchSave(String queueTypeSelected,String department,String testsite){

        String desc = testsite+" Reference lab "+department+(new Random().nextDouble() - new Random().nextDouble());
        switchToFrame(switchiFrame);
        setSelectedItem(queueType,queueTypeSelected);
        setSelectedItem(deparmentlist,department);
        sendKeys(testSetLabSite,testsite);
        click(findbuttonLab);
        if(!(getAllElementText(SearchResults).size() == 0)){
            stepPassedWithScreenshot("Able to view query results");
        }else{
            Assert.fail("Unable to receive search result");
        }
        //edit Save Results
        allSearchelement = getAllElementText(tableSearchResult);
        click(saveSearch);
        setSwitchiframeEdit();

       sendKeys(descriptionText,desc);
       click(updateLabQueuesSearch);
       //accessing Saved Searches List
        setSwitchiFrame();
        click(savedSearch);
        setSwitchiframeEdit();
        sendKeys(textboxDespiption,desc);
        click(findbuttonLab);

       if(getText(descriptionTable).contains(desc)){
           switchToDefaultContext();
           stepPassedWithScreenshot("Able to view Description on list "+desc);
           return true;
       }
        switchToDefaultContext();
        return false;
    }

    //Save and Select query result
    public boolean queuesSelectResult(String queueTypeSelected,String department,String testsite){
        if(querySearchSave(queueTypeSelected,department,testsite)){
            setSwitchiframeEdit();
            click(descriptionTable);
            setSwitchiFrame();
            if(getAllElementText(tableSearchResult).size() == allSearchelement.size()){
                stepPassedWithScreenshot("Search Results are equal with saved search" +allSearchelement.size());
                switchToDefaultContext();
              return true;
            }
        }
        Assert.fail("Unable to find Search Result");
        return false;
    }

    public void searchResults(String queueTypeSelected,String department){
        switchToFrame(switchiFrame);
        setSelectedItem(queueType,queueTypeSelected);
        setSelectedItem(deparmentlist,department);
        click(findbuttonLab);

    }

    public String findlastresultlist(String labespide){

        click(totalResults);
        while(validateElement_Enabled_Displayed(nextpagequeue,5)){
            click(nextpagequeue);
        }
        switchToDefaultContext();
        switchToFrame(switchiFrame);
        clinicalResultEpisode = By.xpath("//label[contains(text(),'%s')]".replace("%s",labespide));
        List<String> texts = getAllElementText(clinicalResultEpisode,10);

            if(texts.size()<=1){
                 stepPassedWithScreenshot("Successfully received Episode "+texts.get(0));
                 return texts.get(0);

            }

        return null;

    }

    public void setSwitchiframeEdit(){
        switchToDefaultContext();
        switchToFrame(switchiframeEdit);

    }

    public void setSwitchiFrame(){
        switchToDefaultContext();
        switchToFrame(switchiFrame);

    }


    public void findOne(By by,String input) {

        validateElement_Enabled_Displayed(by,10);
        super.sendKeys(by,input,5);
        super.findOne(by).click();

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
