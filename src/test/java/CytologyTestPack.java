
import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.CytologyNon_GynaeSpecimen;
import applications.PathCareapplication.models.LabespideData;
import applications.PathCareapplication.models.TestDataPatientReader;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeOptions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CytologyTestPack extends RomanBase {

    public ChromeOptions options = new ChromeOptions();
    public LabespideData labData = new LabespideData();
    public Roman roman = super.roman();
    Faker faker = new Faker();
    static ArrayList<String> labEpisode = new ArrayList<>();
    static String[] testcollection = new String[]{"ANCYTONG"};
    static ArrayList<ArrayList<String>> specimenNumbers = new ArrayList<>();

    static String shipmentNumber ="";
    static
    public PathCareApplication pathCare = null;
    static boolean value = false;
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


    @Nested
    class Non_GynaeSpecimen {

        @Test
        @Order(1)
        public void TP_143() throws Exception {

            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,true )) ;
            pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
            Assert.assertTrue(!(pathCare.pathCareScratch.specimenNumberExtract(true).isEmpty()));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));
            value=false;

        }

        @Test
        @Order(2)
        public void TP_144() throws Exception {
            Assume.assumeTrue("TP-143 Failed ",value);
            value=false;
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false ));
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            labEpisode.remove("");
            pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
            Assert.assertTrue(!(pathCare.pathCareScratch.specimenNumberExtract(true).isEmpty()));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));
            value =true;
        }

        @Test
        @Order(3)
        public void TP_145() throws Exception{
            Faker faker = new Faker();
            String[] testcollection = new String[]{"ANCYTONG"};
            Assume.assumeTrue("TP-144 Failed ",value);
            value=false;
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatient(faker, testcollection,true,false,1,false ));
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            labEpisode.remove("");
            pathCare.pathCareScratch.searchPatient(labEpisode.get(2));
            Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract(true));
            labData.write(roman().testName,pathCare.pathCareScratch.specimenNumberExtract(true).toString());
            value= false;
        }


        @Test
        @Order(4)
        public void TP_146() throws Exception {
            Assume.assumeTrue("TP-145 Failed ",value);
            value=false;
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
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
            value=true;
        }

        @Test
        @Order(5)
        public void TP_147() throws Exception {
            Assume.assumeTrue("TP-146 Failed ",value);
            value=false;
            CytologyNon_GynaeSpecimen specimenDetails =new CytologyNon_GynaeSpecimen();
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);

            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigatespecimenRecived();
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers,specimenDetails.value, true);
            value =true;
        }

        @Test
        @Order(6)
        public void TP_167() throws Exception{
            Faker faker = new Faker();
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
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers,specimenDetails.value,true);

            //Processing
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
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("2",true);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(0),"",false,"",false,"");
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            pathCare.labQueues.findlastresultlist(labEpisode.get(0),false,4,false);
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.pre_analytical.navigateMenu();
            pathCare.analytical.navigateProcessing();

            //2nd lap Episode
            pathCare.pathCareProcessingPage.searchSpecimenReceive(specimenNumbers.get(1).get(0));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonBrowers();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            pathCare.labQueues.findlastresultlist(labEpisode.get(1),false,4,false);
            pathCare.pre_analytical.navigateMenu();
            pathCare.analytical.navigateProcessing();

            //2nd lap Episode --2nd specimen
            pathCare.pathCareProcessingPage.searchSpecimenReceive(specimenNumbers.get(1).get(1));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonBrowers();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(1),"",false,"",false,"");
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(1),"",false,"",false,"");
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            pathCare.labQueues.findlastresultlist(labEpisode.get(1),false,4,false);
            pathCare.pre_analytical.navigateMenu();
            pathCare.analytical.navigateProcessing();


            //3rd lap Episode --1st specimen
            pathCare.pathCareProcessingPage.searchSpecimenReceive(specimenNumbers.get(2).get(0));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonBrowers();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOptionChanger();
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("FNA First Unstained Fixed Slide",false,"",false,"");
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("",true,"SS - Giemsa Add 1 Cyto",true,"FNA First Unstained Fixed Slide");
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(2).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            pathCare.labQueues.findlastresultlist(labEpisode.get(2),false,4,false);
            pathCare.pre_analytical.navigateMenu();
            pathCare.analytical.navigateProcessing();

            //3rd lap Episode --2nd specimen
            pathCare.pathCareProcessingPage.searchSpecimenReceive(specimenNumbers.get(2).get(1));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonBrowers();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOptionChanger();
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("FNA First Unstained Fixed Slide",false,"",false,"");
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("",true,"SS - Giemsa Add 1 Cyto",true,"FNA First Unstained Fixed Slide");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(2).get(1),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            Assert.assertNotEquals("",pathCare.labQueues.findlastresultlist(labEpisode.get(2),false,4,false));

        }

    }


    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LBC_Conventional {
        @Test
        @Order(1)
        public void TP_94() throws Exception{
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true));
            value = (labEpisode.size() == 4);
            Assert.assertEquals(4,labEpisode.size());

        }

        @Test
        @Order(2)
        public void TP_97() throws Exception {
            Assume.assumeTrue("TP-97",value);
            Faker faker = new Faker();
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
            value=labEpisode.size()==6;
            Assert.assertEquals(6,labEpisode.size());

        }

        @Test
        @Order(3)
        public void TP_113() throws Exception {
            Assume.assumeTrue("TP-97 Failed",value);
            value = false;
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
            pathCare.pathCareScratch.doctorSelection();
            labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
            pathCare.pathCareScratch.setNewPatient(false);
            pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
            pathCare.pathCareScratch.doctorSelection();
            pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC");
            pathCare.pathCareScratch.LinkSelectSpecimen();
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            value=labEpisode.size()==8;
            Assert.assertEquals(8,labEpisode.size());


        }

        @Test
        @Order(4)
        public void TP_112() throws Exception{
            Assume.assumeTrue("TP-113",value);
            value = false;
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex().contentEquals("Male")? "Female":"Female");
            pathCare.pathCareScratch.doctorSelection();
            labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)"));
            pathCare.pathCareScratch.setNewPatient(false);
            pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
            pathCare.pathCareScratch.doctorSelection();
            pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)");
            pathCare.pathCareScratch.LinkSelectSpecimen();
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            value=labEpisode.size()==10;
            Assert.assertEquals(10,labEpisode.size());

        }

        @Test
        @Order(5)
        public void TP_397() throws Exception{
            Assume.assumeTrue("TP-112",value);
            //Search Users
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();

            //All Specimen LabEpisode
            specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(labEpisode);

            //Specimen Receive
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.changelocation();
            pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigatespecimenRecived();
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);

        }

        @Test
        @Order(6)
        public void TP_198() throws Exception{

            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
            pathCare.interSystemloginPage.userselection();

            //Transfer
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.labQueues.navigatetoHomepage();
            pathCare.pre_analytical.navigateTransfer();
            pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
            pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
            pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );
            value=pathCare.pathCareLabTransferList.closePackage();
            shipmentNumber=pathCare.pathCareLabTransferList.shipmentNumber;
        }

        @Test
        @Order(7)
        public void TP_207() throws Exception{
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Lab Assistant PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateLogistics();
            pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
            pathCare.pre_analytical.navigateTransfer();
            pathCare.pathCareLabTransferList.checknumbersTransferMultiple(labEpisode,specimenNumbers);


        }

        @Test
        @Order(8)
        public void TP_211() throws Exception{
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            //Drop off
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateLogistics();
             pathCare.transferLogistics.dropOffShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        }

        @Test
        @Order(9)
        public void TP_114() throws Exception{
            TestDataPatientReader testDataPatientReader = new TestDataPatientReader();
            testDataPatientReader.extractTestData("src/main/resources/TP-114_testCase.xlsx");
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigatespecimenRecived();
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers,testDataPatientReader.value,false);
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.searchPatient(labEpisode.get(9));
            pathCare.pathCareScratch.specimenContainerList(specimenNumbers.get(9).get(0),"LBC Ectocervical Specimen","C: LBC Primary","");
            pathCare.analytical.navigateResultEntry();
            pathCare.resultEntry.mutlipleTestResult(labEpisode);

        }

    }

    @Nested
    class ViralStorage{
        @Test
        void tp_229() throws Exception {
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
    }

    @Nested
    class GynaeIssuing{

        @Test
        void TP_230() throws Exception {
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();

        }
    }

}
