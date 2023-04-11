package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.SpecimenReceiveEntity;
import applications.PathCareapplication.models.TestSetCodeEntity;
import applications.PathCareapplication.models.WorkAreaReceiveEntity;
import applications.PathCareapplication.tool.AbstractExtension;
import applications.PathCareapplication.widget.Pre_Analytical;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PathCareLabSpecimenReception extends AbstractExtension {

    private final By specimenNumberText = By.xpath("//input[@id='SpecimenNumber']");
    private final By specimenNumberUpdateButton = By.xpath("//input[@name='update']");
    private final By aliquotUpdateButton = By.xpath("//button[text()='Update']");

    private final By aliquotConfirmButton = By.xpath("//button[@aria-label='Confirm']");
    private final By aliquotCloseButton = By.xpath("//button[text()='Close']");

    private final By specimenNumberStatus = By.xpath("//label[@name='Status']");

    private final By textEpisode = By.xpath("//label[@name='LabEpisodeNumber']");
    private final By tobereceivedcontain = By.xpath("//label[@id='SpecimenNumberTBRz1']");
    private final By aliquotbutton = By.xpath("//input[@id='UpdateAndProceedToAliquot']");
    private final By aliquoutPencilIcon = By.xpath("//md-icon[@md-svg-icon='system-edit']");

    private final By aliquotSpecimenFieldEdit = By.xpath("//input[@name='LBSPCSpecimenDR']");
    private final By specimenNumberAliquot = By.xpath("//input[@name='LBSPCSpecimenNumber']");
    private final By specimenVolumeAliquot = By.xpath("//input[@name='LBSPCVolumeCollected']");

    private final By specimenNewSourceVolume = By.xpath("//input[@name='SourceNewVolume']");
    private final By aliquoutSpecimenContainerTextField = By.xpath("//input[@name='LBSPCContainerDR']");

    private boolean aliqout = false;

    public ArrayList<String> specimenNumbers = new ArrayList<>();

    private String locationNew = "";

    private Boolean firstTime =false;

    public ArrayList<WorkAreaReceiveEntity> workAreaReceiveEntityArrayList = new ArrayList<>();

    public ArrayList<SpecimenReceiveEntity> specimenReceiveEntityArrayList = new ArrayList<>();
    public int timeout = 20;
    public HashMap<String,ArrayList<String>> mutlipleSpeicmen = new HashMap<>();

    public String entryMultipleLabspecimenSingleReception(String labspecnumber){

        switchToDefaultContext();
        awaitElement(mainframe,timeout);
        switchToMainFrame();
        int numtestset = 1;
            for (int x = 1; x <= numtestset; x++) {
                String value = labspecnumber.concat("-".concat(String.valueOf(x)));
                click(specimenNumberText,timeout);
                awaitElement(specimenNumberText,timeout);
                findOne(specimenNumberText, value);
                int numberSpeicmen = find(By.xpath("//label[contains(text(),'%s')]".replace("%s",labspecnumber+"-"))).size();
                if(numberSpeicmen!=0 && x==1){
                    numtestset=numberSpeicmen;
                }

                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                if (!validateElement_Enabled_Displayed(tobereceivedcontain)) {
                    specimenNumbers.add(value);
                    break;
                }
                specimenNumbers.add(value);
            }

        click(specimenNumberUpdateButton);
        for(String value:specimenNumbers){
        redoentryLabspecimen(value);
        validateElement_Enabled_Displayed(textEpisode,5);
        stepPassedWithScreenshot(value);
        }

        return getText(specimenNumberStatus).replace(" "+specimenNumbers.get(specimenNumbers.size()-1),"");

    }


    public HashMap<String, ArrayList<String>> mutlipleSpeicmen_Patientmultiple(List<String> labspecnumber){

        switchToDefaultContext();
        switchToFrame(mainframe);
        int numtestset = 1;

        for(String labepsiode : labspecnumber) {

            for (int x = 1; x <= numtestset; x++) {
                String lapnumberspecimen = labepsiode.concat("-".concat(String.valueOf(x)));
                awaitElement(specimenNumberText,timeout);
                findOne(specimenNumberText, lapnumberspecimen);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + lapnumberspecimen);
                specimenNumbers.add(lapnumberspecimen);
                int numberSpeicmen = find(By.xpath("//label[contains(text(),'%s')]".replace("%s",labepsiode+"-"))).size();
                if(numberSpeicmen!=0 && x==1){
                    numtestset=numberSpeicmen;
                }

                if(!validateElement_Enabled_Displayed(tobereceivedcontain)) {
                    click(specimenNumberUpdateButton);
                    mutlipleSpeicmen.put(labepsiode, specimenNumbers);
                    break;
                }

                if (!validateElement_Enabled_Displayed(specimenNumberUpdateButton, timeout)) {
                    Assert.fail("Unable to Update Lab Specimen");
                }
               //specimenNumbers.add(getText(By.xpath("//label[@id='TestSetListz']".replace("TestSetListz","TestSetListz"+x)),10));
                mutlipleSpeicmen.put(labepsiode, specimenNumbers);

            }

            specimenNumbers = new ArrayList<>();
        }

        for(String value:specimenNumbers){
            redoentryLabspecimen(value);
            validateElement_Enabled_Displayed(textEpisode, timeout);
            stepPassedWithScreenshot(value);
        }

        return mutlipleSpeicmen;
    }

    public void specimenReceiveCreated(ArrayList<ArrayList<String>> specimen) throws InterruptedException {
        switchToDefaultContext();
        switchToFrame(mainframe);

        for(ArrayList<String> values :specimen) {
            for (String value : values) {
                findOne(specimenNumberText, value);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                }
                Thread.sleep(4000);

                click(specimenNumberUpdateButton,timeout);
            }

    }



    //Data from LabespideData spreadsheet
    public void entryMultipleLabspecimenSingle(Pre_Analytical pre_analytical, InterSystemLoginPage interSystemloginPage, HashMap<String ,ArrayList<String>> labespisodesSpecimen, String location , ArrayList<SpecimenReceiveEntity> specimenReceiveEntities, List<WorkAreaReceiveEntity> workAreaReceiveEntityArrayList, List<TestSetCodeEntity> testSetCodeEntities) throws InterruptedException {

        switchToDefaultContext();
        awaitElement(mainframe,timeout);
        switchToFrame(mainframe);
        int numtestset = 0;
        for (String patientkey : labespisodesSpecimen.keySet()) {


            changeLocation(specimenReceiveEntities.get(numtestset).getUserprofile_FK(),interSystemloginPage,pre_analytical);

            //for (String specimenvalue:labespisodesSpecimen.get(patientkey)) {
                for(int x=0;x<= labespisodesSpecimen.get(patientkey).size()-1;x++)
                {
                switchToDefaultContext();
                loadingBarChecker();
                awaitElement(mainframe,timeout);
                switchToMainFrame();

                String labespisode = labespisodesSpecimen.get(patientkey).get(x);
                String value = labespisodesSpecimen.get(patientkey).get(++x);

                String registertestsetValue = labespisodesSpecimen.get(patientkey).get(++x);
                String anatomicalSiteRegistration = value !=null ? value: null;

                findOne(specimenNumberText, labespisode);
                String labEpisode = getText(By.xpath("//label[@id='LabEpisodeNumber']"),timeout);
                String specimenReceiveTestSetValue= getText(By.xpath("//parent::td[label[text()='"+labespisode+"']]//parent::tr//td//label[contains(@id,'TestSetListz')]"),timeout);
                aliquot(patientkey, specimenReceiveEntities);
                //editSpecimenReceiver(specimenReceiveEntities);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + labespisode);
                specimenWorkRecieveUpdateData(labEpisode,patientkey,anatomicalSiteRegistration,labespisode,registertestsetValue,specimenReceiveTestSetValue, specimenReceiveEntities, workAreaReceiveEntityArrayList, testSetCodeEntities);
                        numtestset++;
                }
                if(!aliqout) {
                    click(specimenNumberUpdateButton);
                }
                aliqout = false;
            }

    }

    void changeLocation(String location,InterSystemLoginPage interSystemloginPage, Pre_Analytical pre_analytical){

        if (!locationNew.contentEquals(location)){
            locationNew = location;
            switchToDefaultContext();
            interSystemloginPage.setLocation(location);
            if(!firstTime) {
                switchToDefaultContext();
                interSystemloginPage.changelocation();

            }else{
                firstTime=false;
            }
            interSystemloginPage.userselection();
            pre_analytical.navigatespecimenRecived();

        }
    }


    public  void specimenWorkRecieveUpdateData(String labEpisode, String patientKey, String anatomicalSiteRegistration, String specimenvalue,String registerSpecimen, String specimenReceiveTestSetValue, ArrayList<SpecimenReceiveEntity> specimenReceiveEntities, List<WorkAreaReceiveEntity> workAreaReceiveEntityArrayList, List<TestSetCodeEntity> testSetCodeEntities){
        String antomicalSiteSpecimen = getAttribute(By.xpath("//parent::td[label[text()='"+specimenvalue+"']]//parent::tr//td//input[not(@type='hidden') and  contains(@id,'AnatomicalSite') and not(contains(@id,'AnatomicalSiteQualifier'))]"),"value",timeout);
        boolean found =false;
        boolean found2 = false;
        List<String> testSet =new ArrayList<>();
        boolean containsTestSet = registerSpecimen.toLowerCase().contains(specimenReceiveTestSetValue.toLowerCase());

        for(WorkAreaReceiveEntity workAreaReceiveEntity : workAreaReceiveEntityArrayList){

            for(SpecimenReceiveEntity specimenReceiveEntity : specimenReceiveEntities) {
                if (!found){


                    if (anatomicalSiteRegistration == null) {

                            if (containsTestSet && specimenReceiveEntity.getPatientKey_FK().contentEquals(patientKey)) {
                                specimenReceiveEntity.setSpecimenNumber(specimenvalue);
                                specimenReceiveEntity.setPatientKey_FK(labEpisode);
                                specimenReceiveEntityArrayList.add(specimenReceiveEntity);
                                testSet = specimenReceiveEntity.getTestSet();
                                found = true;
                                break;
                            }
                        } else if (containsTestSet && anatomicalSiteRegistration.contentEquals(antomicalSiteSpecimen) && specimenReceiveEntity.getPatientKey_FK().contentEquals(patientKey)) {
                            specimenReceiveEntity.setSpecimenNumber(specimenvalue);
                            specimenReceiveEntity.setPatientKey_FK(labEpisode);
                            specimenReceiveEntityArrayList.add(specimenReceiveEntity);
                            testSet = specimenReceiveEntity.getTestSet();
                            found = true;
                            found2= false;
                            break;
                        }
                    }
            }

            if(workAreaReceiveEntity.getPk() !=null) {
                if (!found2) {
                   //
                    if (workAreaReceiveEntity.getTestSet().equals(testSet) && workAreaReceiveEntity.getSpecimeNumber() == null) {
                        workAreaReceiveEntity.setSpecimeNumber(specimenvalue);
                        this.workAreaReceiveEntityArrayList.add(workAreaReceiveEntity);
                        found2=true;
                    }
                }
            }
        }
    }

    public void aliquot(String patientKey,ArrayList<SpecimenReceiveEntity> specimenReceiveEntities) throws InterruptedException {
            for(SpecimenReceiveEntity specimenReceiveEntity : specimenReceiveEntities){
                  if(specimenReceiveEntity.getPatientKey_FK().contentEquals(patientKey)) {
                      if(specimenReceiveEntity.getAliquotEditPK() !=null) {
                          click(aliquotbutton, timeout);
                          click(aliquoutPencilIcon, timeout);

                          if (specimenReceiveEntity.getAliquotspecimen() != null) {
                              setAliquotbuttonSpecimenEditSpecimen(specimenReceiveEntity.getAliquotspecimen());
                          }
                          //specimenNumbersAliquot

                          if (specimenReceiveEntity.getAliquotcontainer() != null) {
                              aliquotecontainerSpeciemen(specimenReceiveEntity.getAliquotcontainer());
                          }
                          if (specimenReceiveEntity.getAliquotVolume() != null) {
                              aliquoteVolumeSpeciemen(specimenReceiveEntity.getAliquotVolume());
                          }
                          if (specimenReceiveEntity.getNewSourceVolume() != null) {
                              aliquoteNewVolumeSpeciemen(specimenReceiveEntity.getAliquotVolume());
                          }
                          if (specimenReceiveEntity.getAliquotTestSet() != null) {
                              aliqoutSelectTickBox(specimenReceiveEntity.getAliquotTestSet());
                          }
                          click(aliquotUpdateButton, timeout);
                          stepPassedWithScreenshot("Updated aliquot for patient " + patientKey);
                          if (validateElement_Displayed(aliquotConfirmButton, timeout)) {
                              click(aliquotConfirmButton, timeout);
                          }
                          click(aliquotCloseButton, timeout);
                          aliqout = true;
                      }

                  }


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


    private void setAliquotbuttonspecimenNumberfield(String specimenNumber) throws InterruptedException {
        findOne(specimenNumberAliquot).sendKeys(specimenNumber);
        By lookupOnList = By.xpath("//span[contains(@id,'LBSpecimenContainer_Msg_Edit_0') and contains(text(),'%s')]".replace("%s", specimenNumber));
        if(findOne(specimenNumberAliquot).getAttribute("value").contentEquals(specimenNumber)){
            super._driver.findElement(specimenNumberAliquot).sendKeys(Keys.TAB);
            stepInfoWithScreenshot("Entered Specimen Number  "+specimenNumber);
            Thread.sleep(5000);
        }else{
            Assert.fail("Unable to find specimen "+specimenNumber);
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
            Thread.sleep(5000);
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
            Thread.sleep(5000);
        }else{
            Assert.fail("Unable to find Container Specimen "+container);
        }
    }


    public void specimenReceiveCreated(ArrayList<ArrayList<String>> specimen,HashMap<String,List<String>> specimenDetail,boolean Non_GynaeSpecimen) throws InterruptedException {
        switchToDefaultContext();
        switchToMainFrame();
        int x=1;

        if(specimen.size()==1){
            mutlipleSpeicmenRecivedOne( x, Non_GynaeSpecimen, specimen,specimenDetail);
        }else{
            mutlipleSpeicmenRecived( x, Non_GynaeSpecimen, specimen,specimenDetail);
        }
        switchToDefaultContext();
    }

    private void mutlipleSpeicmenRecivedOne(int x,boolean Non_GynaeSpecimen,ArrayList<ArrayList<String>> specimen,HashMap<String,List<String>> specimenDetail) throws InterruptedException {
        String num;
        int z=x;
            for (String value : specimen.get(0)) {
                findOne(specimenNumberText, value);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                if(Non_GynaeSpecimen) {
                    num = "1";
                    if (String.valueOf(x).contentEquals("2")) {
                        num = "2";
                    } else if (String.valueOf(x).contentEquals("3")) {
                        num = "2.1";
                    } else if (String.valueOf(x).contentEquals("4")) {
                        num = "3.1";
                    } else if (String.valueOf(x).contentEquals("5")) {
                        num = "3.2";
                    }
                }else{
                    num = "Lab Episode "+z;
                }

                enteringValueSpecimenDetail(specimenDetail,num,value);

                stepInfoWithScreenshot("Successfully Entered Specimen details: " + value);
                z++;

                click(specimenNumberUpdateButton, timeout);
            }
    }

    private void mutlipleSpeicmenRecived(int x, boolean Non_GynaeSpecimen, ArrayList<ArrayList<String>> specimen, HashMap<String,List<String>> specimenDetail) throws InterruptedException {

        for(ArrayList<String> values :specimen) {
                String num;
            for (String value : values) {
                findOne(specimenNumberText, value);
                stepPassedWithScreenshot("Successfully Entered Lab Specimen under Lab episode: " + value);
                if(Non_GynaeSpecimen) {
                    num = "1";
                    if (String.valueOf(x).contentEquals("2")) {
                        num = "2";
                    } else if (String.valueOf(x).contentEquals("3")) {
                        num = "2.1";
                    } else if (String.valueOf(x).contentEquals("4")) {
                        num = "3.1";
                    } else if (String.valueOf(x).contentEquals("5")) {
                        num = "3.2";
                    }
                }else{
                    num = "Lab Episode "+x;
                }

                enteringValueSpecimenDetail(specimenDetail,num,value);

                stepInfoWithScreenshot("Successfully Entered Specimen details: " + value);
                x++;
            }
            click(specimenNumberUpdateButton, timeout);
        }
    }

    public void enteringValueSpecimenDetail (HashMap<String,List<String>> specimenDetail,String num,String value){
        int y = 0;
        String speicmenvalue1 ="";
        for (String speicmenvalue : specimenDetail.get(num)) {
            By elment = By.xpath("//parent::td[label[text()='%s']]//parent::tr//td//input[not(@type='hidden')and @value='' and contains(@id,'AnatomicalSite') or contains(@id,'Lesionz')]".replace("%s",value));
            By lookupGlass = By.xpath("//parent::td[label[text()='%s']]//parent::tr//img[@class='clsLookupIcon' and contains(@name,'AnatomicalSiteQualifier') or contains(@name,'Lesion') or contains(@name,'AnatomicalSite')]".replace("%s",value));
            if (y == 0) {
                if(!speicmenvalue.isBlank()) {
                    find(elment,timeout).get(y).sendKeys(speicmenvalue);
                    if (find(elment,timeout).get(y).getAttribute("value").isBlank()) {
                        find(elment,timeout).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass,timeout).get(y).click();
                    awaitElement(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)),timeout);
                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue.strip())), timeout)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }
                }
                y++;
            } else if (y == 1) {
                speicmenvalue1 =speicmenvalue;
                if(!speicmenvalue.isBlank()) {
                    find(elment).get(y).sendKeys(speicmenvalue);
                    if (find(elment).get(y).getAttribute("value").isBlank()) {
                        find(elment).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass).get(y).click();
                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)), timeout)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }
                }
                y++;
            } else if (y == 2) {
                if(!speicmenvalue.isBlank()) {
                    awaitElement(elment,timeout);
                    find(elment,timeout).get(y).sendKeys(speicmenvalue);
                    if (find(elment,timeout).get(y).getAttribute("value").isBlank()) {
                        find(elment,timeout).get(y).sendKeys(speicmenvalue);
                    }
                    find(lookupGlass,timeout).get(y).click();
                    awaitElement(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)),timeout);
                    if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)), timeout)) {
                        click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue)));
                    }

                    if(!speicmenvalue1.isBlank()) {
                        awaitElement(lookupGlass,timeout);
                        find(lookupGlass).get(y - 1).click();
                        if (validateElement_Enabled_Displayed(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue1)), timeout)) {
                            click(By.xpath("//a[text()='%s']".replace("%s", speicmenvalue1)),timeout);
                        }
                    }
                }
                break;
            }
        }
    }


    public void redoentryLabspecimen(String labspecnumber){
        findOne(specimenNumberText,labspecnumber);
        click(specimenNumberUpdateButton, timeout);
    }

    public void findOne(By by,String input) {
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }

    public PathCareLabSpecimenReception(Roman roman) {
        super(roman);
    }

    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
        WebDriverWait wait = new WebDriverWait(super._driver,40L);
        wait.pollingEvery(Duration.ofSeconds(7L));
        return wait.until(EventFiringWebDriver::new).findElement(specimenNumberText).isEnabled();
    }


}
