
import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.CytologyNon_GynaeSpecimen;
import applications.PathCareapplication.models.TestDataPatientReader;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.SelectModules;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
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
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
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
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        String labespides =  pathCare.pathCareScratch.updateClientDetails();
        pathCare.pathCareScratch.searchPatient(labespides);
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract(true));
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
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        labEpisode.add(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true).get(0));
        pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //2nd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //3rd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
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


    @Test
    public void TP_97() throws Exception {
        Faker faker = new Faker();
        List<String> labEpisode = new ArrayList<>();
        String[] testcollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testcollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testcollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        Assert.assertEquals(2,labEpisode.size());
    }

    @Test
    public void TP_113() throws Exception {
        Faker faker  = new Faker();
        List<String> lapEpisode = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add( pathCare.pathCareScratch.updateClientDetails());
        Assert.assertEquals(2,lapEpisode.size());

    }

    @Test
    public void TP_112() throws Exception{
        Faker faker  = new Faker();
        List<String> lapEpisode = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        Assert.assertEquals(2,lapEpisode.size());

    }

    @Test
    public void TP_94() throws Exception{
        Faker faker  = new Faker();
        List<String> lapEpisode = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        lapEpisode = pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true);
        Assert.assertEquals(4,lapEpisode.size());

    }

    @Test
    public void TP_397() throws Exception{
        Faker faker  = new Faker();
        List<String> lapEpisode = new ArrayList<>();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        lapEpisode = pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true);
        Assert.assertEquals(4,lapEpisode.size());
        //TP-97
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        //TP-112
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        //TP-113
        pathCare.pathCareScratch.setNewPatient(true);
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.setNewPatient(true);

        //All Specimen LabEpisode
        specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(lapEpisode);

        //Specimen Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);

    }

    @Test
    @Disabled("Still in progress")
    public void TP_167() throws Exception{
        Faker faker = new Faker();
        ArrayList<String> labEpisode = new ArrayList<>();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[] testcollection = new String[]{"ANCYTONG"};
        CytologyNon_GynaeSpecimen specimenDetails =new CytologyNon_GynaeSpecimen();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
        pathCare.interSystemloginPage.userselection();

        /*
        //1st patient
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
        labEpisode.add(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true).get(0));
        pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //2nd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
        specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

        //3rd patient
        pathCare.analytical.switchToDefaultContext();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
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

        //Processing
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigatehome();
        pathCare.analytical.navigateProcedures();
        pathCare.procedures.searchLabEpisode(labEpisode.get(0),specimenNumbers.get(0));
        pathCare.procedures.searchLabEpisode(labEpisode.get(1),specimenNumbers.get(1));
        pathCare.procedures.searchLabEpisode(labEpisode.get(2),specimenNumbers.get(2));
        pathCare.pre_analytical.navigateMenu();
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(specimenNumbers.get(0).get(0));
        pathCare.pathCareProcessingPage.clickRequestLink();
        pathCare.pathCareProcessingPage.backbuttonBrowers();
        pathCare.pathCareProcessingPage.clickEpisodeEventLink();
        pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("2");
        pathCare.pathCareProcessingPage.testSetProctocol();
        pathCare.pathCareProcessingPage.testSetOption();
        pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
        pathCare.pathCareProcessingPage.searchSpecimenReceive("22DN00522-1");
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00522-1","",false,"",false,"");

        pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
        pathCare.pathCareProcessingPage.setNumSpecimen(2);
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00522-1","",false,"",false,"");


        pathCare.interSystemloginPage.switchToDefaultContext();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.findlastresultlist("22006967",false,4,false);
        pathCare.interSystemloginPage.switchToDefaultContext();
        pathCare.pre_analytical.navigateMenu();
        pathCare.analytical.navigateProcessing();

        //2nd lap Episode
        pathCare.pathCareProcessingPage.searchSpecimenReceive("22DN00523-1");
        pathCare.pathCareProcessingPage.clickRequestLink();
        pathCare.pathCareProcessingPage.backbuttonBrowers();
        pathCare.pathCareProcessingPage.clickEpisodeEventLink();
        pathCare.pathCareProcessingPage.testSetProctocol();
        pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4");
        pathCare.pathCareProcessingPage.testSetOption();
        pathCare.pathCareProcessingPage.setNumSpecimen(1);
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00523-1","",false,"",false,"");
        pathCare.pathCareProcessingPage.setNumSpecimen(2);
        pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00523-1","",false,"",false,"");
        pathCare.pathCareProcessingPage.setNumSpecimen(1);
        pathCare.interSystemloginPage.switchToDefaultContext();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.findlastresultlist("22006968",false,4,false);*/

        //2nd lap Episode --2nd specimen
        pathCare.pathCareProcessingPage.searchSpecimenReceive("22DN00523-2");
        pathCare.pathCareProcessingPage.clickRequestLink();
        pathCare.pathCareProcessingPage.backbuttonBrowers();
        pathCare.pathCareProcessingPage.clickEpisodeEventLink();
        pathCare.pathCareProcessingPage.testSetProctocol();
        pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4");
        pathCare.pathCareProcessingPage.testSetOption();
        pathCare.pathCareProcessingPage.setNumSpecimen(1);
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00523-2","",false,"",false,"");
        pathCare.pathCareProcessingPage.setNumSpecimen(2);
        pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
        pathCare.pathCareProcessingPage.specimenNumPending("22DN00523-2","",false,"",false,"");
        pathCare.pathCareProcessingPage.setNumSpecimen(1);


        //3rd lap Episode --1st specimen
        pathCare.pathCareProcessingPage.searchSpecimenReceive("22DN00524-1");
        pathCare.pathCareProcessingPage.clickRequestLink();
        pathCare.pathCareProcessingPage.backbuttonBrowers();
        pathCare.pathCareProcessingPage.clickEpisodeEventLink();
        pathCare.pathCareProcessingPage.testSetProctocol();
        pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4");
        pathCare.pathCareProcessingPage.testSetOption();

    }

    @Test
    public void TP_198() throws Exception{
        Faker faker  = new Faker();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> lapEpisode = pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true);
        Assert.assertEquals(4,lapEpisode.size());
        //TP-97
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        //TP-112
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        //TP-113
        pathCare.pathCareScratch.setNewPatient(true);
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.setNewPatient(true);

        //TP_397
        //All Specimen LabEpisode
        specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(lapEpisode);

        //Specimen Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
        pathCare.pathCareLabTransferList.selectlistlabespido((ArrayList<String>) lapEpisode);
        pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
        pathCare.pathCareLabTransferList.closePackage();

    }


    @Test
    public void TP_207() throws Exception{
        Faker faker  = new Faker();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> lapEpisode = pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true);
        Assert.assertEquals(4,lapEpisode.size());
        //TP-97
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        //TP-112
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        //TP-113
        pathCare.pathCareScratch.setNewPatient(true);
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.setNewPatient(true);

        //TP_397
        //All Specimen LabEpisode
        specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(lapEpisode);

        //Specimen Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
        pathCare.pathCareLabTransferList.selectlistlabespido((ArrayList<String>) lapEpisode);
        pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.checknumbersTransferMultiple((ArrayList<String>) lapEpisode,specimenNumbers);

    }

    @Test
    public void TP_211() throws Exception{
        Faker faker  = new Faker();
        ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();
        String[]  testCollection = new String[]{"AGCYTO"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> lapEpisode = pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true);
        Assert.assertEquals(4,lapEpisode.size());
        //TP-97
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        //TP-112
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        //TP-113
        pathCare.pathCareScratch.setNewPatient(true);
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
        pathCare.pathCareScratch.doctorSelection();
        lapEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
        pathCare.pathCareScratch.setNewPatient(false);
        pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
        pathCare.pathCareScratch.doctorSelection();
        pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
        pathCare.pathCareScratch.LinkSelectSpecimen();
        lapEpisode.add(pathCare.pathCareScratch.updateClientDetails());
        pathCare.pathCareScratch.setNewPatient(true);

        //TP_397
        //All Specimen LabEpisode
        specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(lapEpisode);

        //Specimen Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
        pathCare.pathCareLabTransferList.selectlistlabespido((ArrayList<String>) lapEpisode);
        pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.checknumbersTransferMultiple((ArrayList<String>) lapEpisode,specimenNumbers);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
    }


    @Test
    public void TP_Data() throws Exception{
        TestDataPatientReader data = new TestDataPatientReader();
        data.extractTestData("src/main/resources/TP-4.xlsx");
    }
    @Nested
    class Non_GynaeSpecimen{

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
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            labEpisode.add(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true ).get(0));
            pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

            //2nd patient
            pathCare.analytical.switchToDefaultContext();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));

            //3rd patient
            pathCare.analytical.switchToDefaultContext();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false );
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
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

    }

}
