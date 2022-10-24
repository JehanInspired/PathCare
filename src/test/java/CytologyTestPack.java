
import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.CytologyNon_GynaeSpecimen;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

public class CytologyTestPack extends RomanBase {

    public ChromeOptions options = new ChromeOptions();
    public Roman roman = super.roman();
    public PathCareApplication pathCare = null;
    public String dir = " ";

    @BeforeEach
    public void startup(){
        dir = get_reportDir();
        options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.open_pdf_in_system_reader",false);
        chromeOptionsMap.put("download.prompt_for_download",false);
        chromeOptionsMap.put("profile.default_content_settings.popups ",0);
        chromeOptionsMap.put("download.extensions_to_open ","applications/pdf");
        chromeOptionsMap.put("download.default_directory", dir);
        options.setExperimentalOption("prefs", chromeOptionsMap);
        roman._driver = roman().selenium.getChromeDriver(options);
        pathCare = new PathCareApplication(roman);

    }

    @AfterEach
    public void cleanUp(){
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();
        roman.Dispose();

    }


    @Test
    public void TP_143() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true );
        pathCare.pathCareScratch.searchPatient(labespides.get(0));
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract(true));

    }

    @Test
    public void TP_144() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        String labEpisode =  pathCare.pathCareScratch.updateClientDetails();
        pathCare.pathCareScratch.searchPatient(labEpisode);
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract(true));
    }

    @Test
    public void TP_145() throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        String labespides =  pathCare.pathCareScratch.updateClientDetails();
        pathCare.pathCareScratch.searchPatient(labespides);
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract(true));
    }

    @Test
    public void TP_146() throws Exception {
        Faker faker = new Faker();
        ArrayList<String> labEpisode = new ArrayList<>();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        //1st patient
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        labEpisode.add(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true ).get(0));
        pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //2nd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //3rd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(2));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //Change user Profile
        pathCare.pathCareScratch.loadingBarChecker();
        pathCare.analytical.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.pathCareScratch.loadingBarChecker();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();

        //Transfer
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.testSetfield("Cytology Non-Gynae");
        pathCare.pathCareLabTransferList.clickFindButton();
        pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
        pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pathCareLabTransferList.checkPackItem(labEpisode,specimenNumbers);

        //transfer pick up
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //transfer In transit
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.checknumbersTransferMultiple(labEpisode,specimenNumbers);
    }

    @Test
    public void TP_147() throws Exception {
        Faker faker = new Faker();
        ArrayList<String> labEpisode = new ArrayList<>();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[] testcollection = new String[]{"ANCYTONG"};
        CytologyNon_GynaeSpecimen specimenDetails =new CytologyNon_GynaeSpecimen();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);

        //1st patient
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        labEpisode.add(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true ).get(0));
        pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //2nd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //3rd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(2));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //Change user Profile
        pathCare.pathCareScratch.loadingBarChecker();
        pathCare.analytical.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.pathCareScratch.loadingBarChecker();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();

        //Transfer
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.testSetfield("Cytology Non-Gynae");
        pathCare.pathCareLabTransferList.clickFindButton();
        pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
        pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pathCareLabTransferList.checkPackItem(labEpisode,specimenNumbers);

        //transfer pick up
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //transfer In transit
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.checknumbersTransferMultiple(labEpisode,specimenNumbers);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers,specimenDetails.value);
    }


}
