package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.*;
import applications.PathCareapplication.tool.AbstractExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathCareLabIntrumentResultGeneratorPage extends AbstractExtension {

    private final By intrumentTextBox = By.xpath("//input[@id='LBCInstrument']");
    private final By testGroup = By.xpath("//input[@id='LBCInstrumentTestGroup']");
    private final By speciemenNumber = By.xpath("//input[@id='SpecimenNumber']");
    private final By resultsTB = By.xpath("//input[@id='Resultz1']");

    private final By generateButton = By.xpath("//input[@id='generate1']");
    private final By selecttickbox = By.xpath("//input[@id='Selectz1']");
    private final By iframeMain = By.xpath("//iframe[@id='TRAK_main']");

    private final By nextpage = By.xpath("//a[contains(@id,'Next')]");

    private final By textarea = By.xpath("//textarea");

    private final int timeout = 20;

    private String locationNew = "";
    private Boolean firstTime = true;

    public PathCareLabIntrumentResultGeneratorPage(Roman roman) {
        super(roman);
    }


    public void intrumentTestGroup(String nameOfIntrument, String testgroupvalue, String materialNumber,String results) throws InterruptedException {

        switchToDefaultContext();
        switchToFrame(iframeMain);

        findOne(intrumentTextBox,nameOfIntrument);
        Thread.sleep(3000);
        findOne(testGroup,testgroupvalue);
        Thread.sleep(3000);
        findOne(speciemenNumber,materialNumber);
        Thread.sleep(3000);
        findOne(resultsTB,results);
        Thread.sleep(3000);
        click(selecttickbox);

        scrollToElement(generateButton,5);
        click(generateButton,10);
        scrollToElement(textarea);
        if(!getText(textarea,5).isBlank() || !getText(textarea).isEmpty()){
            stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
        }



    }

    public void testItemListGroup(HashMap<String ,String> testitems, String nameOfIntrument, String testgroupvalue, String materialNumber) throws InterruptedException {

        switchToDefaultContext();
        switchToFrame(iframeMain);

        findOne(intrumentTextBox,nameOfIntrument);
        if(!getText(intrumentTextBox).isBlank()){
            stepPassedWithScreenshot("Entered instrument test item "+nameOfIntrument);
        }
        Thread.sleep(3000);
        findOne(testGroup,testgroupvalue);
        if(!getText(testGroup).isBlank()){
            stepPassedWithScreenshot("Entered test group test item "+testgroupvalue);
        }
        Thread.sleep(3000);
        findOne(speciemenNumber,materialNumber);
        if(!getText(speciemenNumber).isBlank()){
            stepPassedWithScreenshot("Entered material number test item "+materialNumber);
        }
        Thread.sleep(3000);

        for(String value:testitems.keySet()){
            By testitem = By.xpath("//label[contains(text(),'"+value+"')]");

            if(!validateElement_Displayed(testitem)){
                while(!validateElement_Displayed(testitem)){
                    click(nextpage,10);
                }
            }
        }

        if(getText(intrumentTextBox).isBlank()) {
            findOne(intrumentTextBox, nameOfIntrument);
            Thread.sleep(3000);
        }
        if(getText(testGroup).isBlank()) {
            findOne(testGroup, testgroupvalue);
            Thread.sleep(3000);
        }
        if(getText(speciemenNumber).isBlank()) {
            findOne(speciemenNumber, materialNumber);
            Thread.sleep(3000);
        }

        for(String value:testitems.keySet()){
            By testiteminputboxes = By.xpath("//td[preceding-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            find(testiteminputboxes).get(1).sendKeys(testitems.get(value));
            stepPassedWithScreenshot("Entered Results test item "+value);
            By testitemtickbox = By.xpath("//td[following-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            click(testitemtickbox);
            stepPassedWithScreenshot("Ticked test item "+value);

        }
        scrollToElement(generateButton,5);
        click(generateButton,10);
        scrollToElement(textarea);
        if(!getText(textarea,5).isBlank() || !getText(textarea).isEmpty()){
            stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
        }


    }

    public void multipleTestItemListGroup(HashMap<String ,String> testitems, String materialNumber, String[]nameOfIntrument) throws InterruptedException {


        switchToDefaultContext();
        switchToFrame(iframeMain);


            findOne(intrumentTextBox,nameOfIntrument[0]);
            if(!getText(intrumentTextBox).isBlank()){
                stepPassedWithScreenshot("Entered instrument test item "+nameOfIntrument[0]);
            }
            Thread.sleep(3000);
            findOne(testGroup,nameOfIntrument[1]);
            if(!getText(testGroup).isBlank()){
                stepPassedWithScreenshot("Entered test group test item "+nameOfIntrument[1]);
            }
            Thread.sleep(3000);
            findOne(speciemenNumber,materialNumber);
            if(!getText(speciemenNumber).isBlank()){
                stepPassedWithScreenshot("Entered material number test item "+materialNumber);
            }



        Thread.sleep(3000);

        /*for(int x=0; x<=testitems.size()-1;x++){
            By testitem = By.xpath("//label[contains(text(),'"+testitems.get(x)+"')]");

            if(!validateElement_Displayed(testitem)){
                while(!validateElement_Displayed(testitem)){
                    click(nextpage,10);
                }
            }
            x=x+2;
        }*/

           /* findOne(intrumentTextBox,nameOfIntrument[0]);
            if(!getText(intrumentTextBox).isBlank()){
                stepPassedWithScreenshot("Entered instrument test item "+nameOfIntrument[0]);
            }
            Thread.sleep(3000);
            findOne(testGroup,nameOfIntrument[1]);
            if(!getText(testGroup).isBlank()){
                stepPassedWithScreenshot("Entered test group test item "+nameOfIntrument[1]);
            }
            Thread.sleep(3000);
            findOne(speciemenNumber,materialNumber);
            if(!getText(speciemenNumber).isBlank()){
                stepPassedWithScreenshot("Entered material number test item "+materialNumber);
            }*/


        for(String value:testitems.keySet()){
            By testiteminputboxes = By.xpath("//td[preceding-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            find(testiteminputboxes).get(1).sendKeys(testitems.get(value));
            stepPassedWithScreenshot("Entered Results test item "+value);
            By testitemtickbox = By.xpath("//td[following-sibling::td[contains(.,'" + value + "')]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
            click(testitemtickbox);
            stepPassedWithScreenshot("Ticked test item "+value);

        }

        scrollToElement(generateButton,5);
        click(generateButton,10);
        scrollToElement(textarea);
        if(!getText(textarea,5).isBlank() || !getText(textarea).isEmpty()){
            stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
        }
        _driver.navigate().refresh();
    }

    public HashMap<String,String> testitemToHashMap(ArrayList<String> values){
        HashMap<String , String> items = new HashMap<>();
        int y =0;
        String key="";
        for(String value:values){
            ++y;
            if(y==1){
             key  = value;
            }else{
                items.put(key,value);
                y=0;
            }

        }
        return items;
    }

//Just need to loop each Result from each sheet 
    public void multipleEspisode(
                                 List<ResultsGenerator_Sysmexca620Geo> resultsGenerator_sysmexca620GeoList,
                                 List<ResultsGenerator_RocheSysmexXGE> resultsGenerator_rocheSysmexXGEList,
                                 List<ResultsGenerator_AAGeorge> resultsGenerator_aaGeorgeList,
                                 List<ResultsGenerator_Aquios1> resultsGenerator_aquios1s,
                                 List<ResultsGenerator_PCPBioFireFilm> pcpBioFireFilmList,
                                 List<ResultsGenerator_SysmexCS2500> SysmexCS2500,
                                 List<ResultsGenerator_RocheSysmexXN1> rocheSysmexXN1,
                                 List<ResultsGenerator_AbbottAlinityc> abbottAlinityc,
                                 ArrayList<SpecimenReceiveEntity> specimenReceiveEntityArrayList,
                                 LabQueues labQueues,
                                 InterSystemLoginPage interSystemLoginPage) throws InterruptedException {


        List<Object> data = new ArrayList<>();
        data.add(pcpBioFireFilmList);
        data.add(SysmexCS2500);
        data.add(rocheSysmexXN1);
        data.add(abbottAlinityc);
        data.add(resultsGenerator_aaGeorgeList);
        data.add(resultsGenerator_aquios1s);
        data.add(resultsGenerator_rocheSysmexXGEList);
        data.add(resultsGenerator_sysmexca620GeoList);

        for(int x=0;x<data.size()-1;x++){

            if(x==0){
                for (ResultsGenerator_PCPBioFireFilm resultsGenerator_pcpBioFireFilm : ((List<ResultsGenerator_PCPBioFireFilm>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_pcpBioFireFilm.getUser_Profile(),interSystemLoginPage,labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_pcpBioFireFilm.getSpecimenReceive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_pcpBioFireFilm.getInstrument(), resultsGenerator_pcpBioFireFilm.getTestGroup(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_pcpBioFireFilm.getValues());
                            acceptdataResult();
                        }
                    }
                }
            } else if (x==1) {
                for (ResultsGenerator_SysmexCS2500 resultsGenerator_sysmexCS2500 : ((List<ResultsGenerator_SysmexCS2500>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_sysmexCS2500.getUser_Profile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_sysmexCS2500.getSpecimenReceive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_sysmexCS2500.getInstrument(), resultsGenerator_sysmexCS2500.getTestGroup(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_sysmexCS2500.getValues());
                            acceptdataResult();
                        }
                    }
                }

            } else if (x==2) {
                for (ResultsGenerator_RocheSysmexXN1 resultsGenerator_rocheSysmexXN1 : ((List<ResultsGenerator_RocheSysmexXN1>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {

                        changeLocation(resultsGenerator_rocheSysmexXN1.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_rocheSysmexXN1.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_rocheSysmexXN1.getInstrument(), resultsGenerator_rocheSysmexXN1.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_rocheSysmexXN1.getValues());
                            acceptdataResult();

                        }
                    }
                }

            } else if (x==3) {
                for (ResultsGenerator_AbbottAlinityc resultsGenerator_abbottAlinityc : ((List<ResultsGenerator_AbbottAlinityc>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_abbottAlinityc.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_abbottAlinityc.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_abbottAlinityc.getInstrument(), resultsGenerator_abbottAlinityc.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_abbottAlinityc.getValues());
                            acceptdataResult();

                        }
                    }
                }

            } else if(x==4){
                for (ResultsGenerator_AAGeorge resultsGenerator_aaGeorge : ((List<ResultsGenerator_AAGeorge>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_aaGeorge.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_aaGeorge.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_aaGeorge.getInstrument(), resultsGenerator_aaGeorge.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_aaGeorge.getValues());
                            acceptdataResult();

                        }
                    }
                }
            } else if(x==5){
                for (ResultsGenerator_Aquios1 resultsGenerator_aquios1 : ((List<ResultsGenerator_Aquios1>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_aquios1.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_aquios1.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_aquios1.getInstrument(), resultsGenerator_aquios1.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_aquios1.getValues());
                            acceptdataResult();

                        }
                    }
                }
            }else if(x==6){
                for (ResultsGenerator_RocheSysmexXGE resultsGenerator_rocheSysmexXGE : ((List<ResultsGenerator_RocheSysmexXGE>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_rocheSysmexXGE.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_rocheSysmexXGE.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_rocheSysmexXGE.getInstrument(), resultsGenerator_rocheSysmexXGE.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_rocheSysmexXGE.getValues());
                            acceptdataResult();

                        }
                    }
                }
            }else if(x==7){
                for (ResultsGenerator_Sysmexca620Geo resultsGenerator_sysmexca620Geo : ((List<ResultsGenerator_Sysmexca620Geo>) data.get(x))) {
                    for(SpecimenReceiveEntity specimenReceivesEntity : specimenReceiveEntityArrayList) {
                        changeLocation(resultsGenerator_sysmexca620Geo.getUserprofile(), interSystemLoginPage, labQueues);
                        switchToDefaultContext();
                        switchToFrame(iframeMain);

                        if (resultsGenerator_sysmexca620Geo.getSpecimen_receive_FK().contentEquals(specimenReceivesEntity.getPk())) {
                            intrumenSpecimenfield(resultsGenerator_sysmexca620Geo.getInstrument(), resultsGenerator_sysmexca620Geo.getTest_Group(), specimenReceivesEntity.getSpecimenNumber());
                            testItemCodeResult(resultsGenerator_sysmexca620Geo.getValues());
                            acceptdataResult();

                        }
                    }
                }
            }

        }

        }
    void changeLocation(String location,InterSystemLoginPage interSystemloginPage, LabQueues labQueues){

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
            labQueues.navigatetoToolBox();
            labQueues.navigateTestSet();
        }
    }

    void acceptdataResult() throws InterruptedException {
        Thread.sleep(3000);
        scrollToElement(generateButton,5);
        click(generateButton,10);
        scrollToElement(textarea);
        if(!getText(textarea,5).isBlank() || !getText(textarea).isEmpty()){
            stepPassedWithScreenshot("Able to view outputs "+getText(textarea,5));
        }
        _driver.navigate().refresh();
    }

    void intrumenSpecimenfield(String intrument,String testGroupField,String materialNumber) throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(iframeMain);

        findOne(intrumentTextBox,intrument);
        if(!getAttribute(intrumentTextBox,"value").isBlank()){
            stepPassedWithScreenshot("Entered instrument test item "+intrument);
        }
        Thread.sleep(3000);
        findOne(testGroup,testGroupField);
        if(!getAttribute(testGroup,"value").isBlank()){
            stepPassedWithScreenshot("Entered test group test item "+testGroupField);
        }
        Thread.sleep(3000);
        findOne(speciemenNumber,materialNumber);
        if(!getAttribute(speciemenNumber,"value").isBlank()){
            stepPassedWithScreenshot("Entered material number test item "+materialNumber);
        }
    }


    void testItemCodeResult(HashMap<String, String> testitems){

    for(String value:testitems.keySet()) {
        By testiteminputboxes = By.xpath("//td[preceding-sibling::td[label[text()='"+value+"']]]//input[not(@disabled) and(not(contains(@type,'hidden')))]");
        find(testiteminputboxes,timeout).get(1).sendKeys(testitems.get(value));
        stepPassedWithScreenshot("Entered "+value+" Results test item " +testitems.get(value));
        By testitemtickbox = By.xpath("//parent::td[label[text()='"+value+"']]//parent::tr//td//input[contains(@type,'checkbox')]");
        click(testitemtickbox,timeout);
        stepPassedWithScreenshot("Ticked test item " + value);
    }

    }



    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
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
