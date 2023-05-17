
import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.CytologyNon_GynaeSpecimen;

import com.github.javafaker.Faker;
import com.microsoft.schemas.vml.CTImageData;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeDocumentImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static applications.PathCareapplication.tool.ExcelExtractorList.patientData;
import static applications.PathCareapplication.tool.ExcelExtractorList.userProfile;
import static reporting.ExtentReport.get_reportDir;
import static applications.PathCareapplication.tool.ExcelExtractorList.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CytologyTestPack extends RomanBase {

    public ChromeOptions options = new ChromeOptions();
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
            String[] testcollection = new String[]{"ANCYTONG"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",true)) ;
            pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));
            Assert.assertTrue(!(specimenNumbers.isEmpty()));
            value=false;

        }

        @Test
        @Order(2)
        public void TP_144() throws Exception {
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",false);
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSetSingle("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtract(true));
            Assert.assertTrue(!(specimenNumbers.isEmpty()));
        }

        @Test
        @Order(3)
        public void TP_145() throws Exception{
            Faker faker = new Faker();
            String[] testcollection = new String[]{"ANCYTONG"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",false);
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSetSingle("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            pathCare.pathCareScratch.searchPatient(labEpisode.get(2));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtracts(true));
            Assert.assertNotEquals("",specimenNumbers);
        }


        @Test
        @Order(4)
        public void TP_146() throws Exception {

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
            //pathCare.pathCareLabTransferList.checkPackItem(labEpisode,specimenNumbers);

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
        @Order(5)
        public void TP_147() throws Exception {
            CytologyNon_GynaeSpecimen specimenDetails =new CytologyNon_GynaeSpecimen();
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigatespecimenRecived();
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers,specimenDetails.value, true);
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
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");// this is the on --"PC Depot Admin and Data Capture PANORAMA"
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",true)) ;
            pathCare.pathCareScratch.searchPatient(labEpisode.get(0));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtracts(true));


            //2nd patient
            pathCare.analytical.switchToDefaultContext();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",false);
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);// need to fix
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSetSingle("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            pathCare.pathCareScratch.searchPatient(labEpisode.get(1));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtracts(true));

            //3rd patient
            pathCare.analytical.switchToDefaultContext();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testcollection,true,false,1,"Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",false);
            pathCare.pathCareScratch.addMultipleSpecimen("Cytology Non-Gynae","Non-Gynae Specimen","Slide(s)",1,false,true);//need to fix
            pathCare.pathCareScratch.specimenSelect();
            pathCare.pathCareScratch.editTestSetSingle("Number of FNA Slides","4");
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            pathCare.pathCareScratch.searchPatient(labEpisode.get(2));
            specimenNumbers.add(pathCare.pathCareScratch.specimenNumberExtracts(true));


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
           // pathCare.pathCareLabTransferList.checkPackItem(labEpisode,specimenNumbers);

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
            pathCare.analytical.navigateProcedures();
            pathCare.procedures.searchLabEpisode(labEpisode.get(0),specimenNumbers.get(0),"Cyto N1 Non-Gynae Verification");
            pathCare.procedures.search(specimenNumbers.get(0).get(0));

            //Need to do it via procedures
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("2",true);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.interSystemloginPage.switchToDefaultContext();


            //2nd lap Episode
            pathCare.procedures.searchLabEpisode(labEpisode.get(1),specimenNumbers.get(1),"Cyto N1 Non-Gynae Verification");
            pathCare.procedures.search(specimenNumbers.get(1).get(0));//Procedures
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(1).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.interSystemloginPage.switchToDefaultContext();



            //2nd lap Episode --2nd specimen
            pathCare.procedures.searchLabEpisode(labEpisode.get(1),specimenNumbers.get(1),"Cyto N1 Non-Gynae Verification");
            pathCare.procedures.search(specimenNumbers.get(1).get(1));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
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
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.setNumSpecimen(1);
            pathCare.interSystemloginPage.switchToDefaultContext();



            //3rd lap Episode --1st specimen
            pathCare.procedures.searchLabEpisode(labEpisode.get(2),specimenNumbers.get(2),"Cyto N1 Non-Gynae Verification");
            pathCare.procedures.search(specimenNumbers.get(2).get(0));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOptionChanger(true);
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("FNA First Unstained Unfixed Slide",false,"",false,"");
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("",true,"SS - Giemsa Add 1 Cyto",true,"FNA First Unstained Fixed Slide");
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(2).get(0),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.interSystemloginPage.switchToDefaultContext();


            //3rd lap Episode --2nd specimen
            pathCare.procedures.searchLabEpisode(labEpisode.get(2),specimenNumbers.get(2),"Cyto N1 Non-Gynae Verification");
             pathCare.procedures.search(specimenNumbers.get(2).get(1));
            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("4",false);
            pathCare.pathCareProcessingPage.testSetOptionChanger(false);
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("FNA First Unstained Unfixed Slide",false,"",false,"");
            pathCare.pathCareProcessingPage.protocolMaterialWidgt("",true,"SS - Giemsa Add 1 Cyto",true,"FNA First Unstained Fixed Slide");
            pathCare.pathCareProcessingPage.setNumSpecimen(2);
            pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(2).get(1),"",false,"",false,"");
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.pathCareProcessingPage.refreshbrowser();
        }

        @Test
        @Order(7)
        public void TP_168() throws Exception {
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.analytical.navigateProcedures();
            pathCare.procedures.searchSelectionMultiple(new String[]{"PAP Staining","Giemsa Cyto","Rapid Diff"});
           labEpisode = new ArrayList<>(List.of(new String[]{"23002163","23002164", "23002165"}));
            pathCare.procedures.multipleSearch(labEpisode);
            pathCare.labEnquiry.navigatelabEnquiry();
            pathCare.labEnquiry.labResultsEntry(labEpisode.get(0)); //First lab Episode
            pathCare.labEnquiry.testSetListSingle(labEpisode.get(0));
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labEnquiry.backtoTestSetList();
            pathCare.labEnquiry.labResultsEntry(labEpisode.get(1)); //Second lab Episode
            pathCare.labEnquiry.testSetListSingle(labEpisode.get(1));
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.interSystemloginPage.switchToDefaultContext();
            pathCare.labEnquiry.backtoTestSetList();
            pathCare.labEnquiry.labResultsEntry(labEpisode.get(2)); //3rd lab Episode
            pathCare.labEnquiry.testSetListSingle(labEpisode.get(2));
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();


        }

        @Test
        @Order(8)
        public void TP_169() throws Exception {
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username,model.password);
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.labQueues.switchToMainFrame();
            pathCare.labQueues.SearchResultTable("Total","Cytology - Non-Gynae Workload",20);
            pathCare.labQueues.clickEspiodeElement(true,"23002163");//labEpisode.get(0)
            pathCare.pathCareProcessingPage.testSetOption();
            //pathCare.

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
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true));
            value = (labEpisode.size() == 4);
            Assert.assertEquals(4,labEpisode.size());

        }

        @Test
        @Order(2)
        public void TP_97() throws Exception {
            Faker faker = new Faker();
            String[] testcollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),"Female");
            pathCare.pathCareScratch.doctorSelection();
            labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testcollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)","2100"));
            pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),"Female");
            pathCare.pathCareScratch.doctorSelection();
            labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testcollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)","2100"));
            value=labEpisode.size()==2;
            Assert.assertEquals(2,labEpisode.size());

        }

        @Test
        @Order(3)
        public void TP_113() throws Exception {
            //Assume.assumeTrue("TP-97 Failed",value);
            value = false;
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),"Female");
            pathCare.pathCareScratch.doctorSelection();
            labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,true,"Cytology Gynae","Cytology Gynae Specimen","Slide(s)","2100"));
            pathCare.pathCareScratch.setNewPatient(false);
            pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
            pathCare.pathCareScratch.doctorSelection();
            pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-1",testCollection,false,false,false,"Cytology Gynae","Cytology Gynae Specimen","LBC","2100");
            pathCare.pathCareScratch.LinkSelectSpecimen();
            labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
            value=labEpisode.size()==2;
            Assert.assertEquals(2,labEpisode.size());

        }
/*
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
            //All Specimen LabEpisode
            specimenNumbers=pathCare.pathCareScratch.searchMutliplePatient(labEpisode);//
            Assert.assertEquals(10,labEpisode.size());

        }

        @Test
        @Order(5)
        public void TP_397() throws Exception{
           // Assume.assumeTrue("TP-112",value);
            value = false;
            //Search Users
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
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
            pathCare.transferLogistics.pickUpShipmentValid(shipmentNumber);
            pathCare.pre_analytical.navigateTransfer();
            value=pathCare.pathCareLabTransferList.checknumbersTransferMultiple(labEpisode,specimenNumbers);
            Assert.assertTrue(value);

        }


        @Test
        @Order(8)
        public void TP_211() throws Exception{
            Assume.assumeTrue("TP-207 issue ",value);
            value =false;
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            //Drop off
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateLogistics();
             pathCare.transferLogistics.dropOffShipmentValid(shipmentNumber);
//            labData.write("TP_211()",labEpisode.toString()+","+specimenNumbers.toString());
        }

        @Test
        @Order(9)
        public void TP_114() throws Exception{
//            if(labEpisode.isEmpty()){
//               if (labData.reader("TP_211()")){
//                specimenNumbers.add(labData.getSpecimenNumbers());
//                labEpisode=labData.getLapEpsiode();
//                shipmentNumber=labData.getShipment();
//               };
//            }
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
            pathCare.pathCareScratch.specimenContainerList("LBC Ectocervical Specimen","C: LBC Primary","");
            pathCare.analytical.navigateResultEntry();
            pathCare.resultEntry.mutlipleTestResult(labEpisode);
           // labData.write("TP_114()",labEpisode.toString()+","+specimenNumbers.toString());

        }

      /*  @Test
        void TP_117() throws Exception {

//            if(labEpisode.isEmpty()){
//                if (labData.reader("TP_114()")){
//                    specimenNumbers.add(labData.getSpecimenNumbers());
//                    labEpisode=labData.getLapEpsiode();
//                }
//            }
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("Pathcare Lab Assistant Histo N1");
            pathCare.interSystemloginPage.userselection();
            pathCare.analytical.navigateProcedures();
            pathCare.procedures.searchSelection("Gynae Specimen Verification");
            //5th lap episode
//            try {
//                if(specimenNumbers.get(4).get(0).isEmpty() || specimenNumbers.get(4).get(0) == null) {
//                    pathCare.procedures.search(specimenNumbers.get(0).get(4));
//                }else {
                    pathCare.procedures.search(specimenNumbers.get(4).get(0));
//                }
//            }catch(IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(4));
//            }

            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("",false);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
           // try {
            //    if (specimenNumbers.get(4).get(0).isEmpty() || specimenNumbers.get(4).get(0) == null) {
                   // pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(4), "", false, "", false, "");
               // } else {
                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(4).get(0), "", false, "", false, "");
//                }
//            }catch(IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(4));
//            }
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.backtopreviouspage();
            pathCare.procedures.searchSelection("Gynae Specimen Verification");
            //Lab 6
//            try {
//                if (specimenNumbers.get(5).get(0).isEmpty() || specimenNumbers.get(5).get(0) == null) {
//                    pathCare.procedures.search(specimenNumbers.get(0).get(5));
//                } else {
                    pathCare.procedures.search(specimenNumbers.get(5).get(0));
//                }
//            }catch(IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(4));
//            }

            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("",false);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
//            try {
//                if (specimenNumbers.get(5).get(0).isEmpty() || specimenNumbers.get(5).get(0) == null) {
//                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(5), "", false, "", false, "");
//                } else {
                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(5).get(0), "", false, "", false, "");
//                }
//            }catch(NullPointerException e){
//                pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(5), "", false, "", false, "");
//
//            }

            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.backtopreviouspage();
            pathCare.procedures.searchSelection("Gynae Specimen Verification");

            //Lab 7
//            try {
//                if (specimenNumbers.get(6).get(0).isEmpty() || specimenNumbers.get(6).get(0) == null) {
//                    pathCare.procedures.search(specimenNumbers.get(0).get(6));
//                } else {
                    pathCare.procedures.search(specimenNumbers.get(6).get(0));
//                }
//            }catch (IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(6));
//            }

            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("",false);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
//            try {
//                if (specimenNumbers.get(6).get(0).isEmpty() || specimenNumbers.get(6).get(0) == null) {
//                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(6), "", false, "", false, "");
//                } else {
                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(6).get(0), "", false, "", false, "");
//                }
//            }catch (IndexOutOfBoundsException e){
//                pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(6), "", false, "", false, "");
//
//            }
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.backtopreviouspage();
            pathCare.procedures.searchSelection("Gynae Specimen Verification");
            //Lab 8
//            try {
//                if (specimenNumbers.get(7).get(0).isEmpty() || specimenNumbers.get(7).get(0) == null) {
//                    pathCare.procedures.search(specimenNumbers.get(0).get(7));
//                } else {
                    pathCare.procedures.search(specimenNumbers.get(7).get(0));
//                }
//            }catch (IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(7));
//            }

            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("",false);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
//            try {
//                if (specimenNumbers.get(7).get(0).isEmpty() || specimenNumbers.get(7).get(0) == null) {
//                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(7), "", false, "", false, "");
//                } else {
                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(7).get(0), "", false, "", false, "");
//                }
//            }catch (IndexOutOfBoundsException e){
//                pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(7), "", false, "", false, "");
//            }
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.backtopreviouspage();
            pathCare.procedures.searchSelection("Gynae Specimen Verification");
            //Lab 9
//            try {
//                if (specimenNumbers.get(8).get(0).isEmpty() || specimenNumbers.get(8).get(0) == null) {
//                    pathCare.procedures.search(specimenNumbers.get(0).get(8));
//                } else {
                    pathCare.procedures.search(specimenNumbers.get(8).get(0));
//                }
//            }catch (IndexOutOfBoundsException e){
//                pathCare.procedures.search(specimenNumbers.get(0).get(8));
//            }

            pathCare.pathCareProcessingPage.clickRequestLink();
            pathCare.pathCareProcessingPage.backbuttonbrowser();
            pathCare.pathCareProcessingPage.clickEpisodeEventLink();
            pathCare.pathCareProcessingPage.testSpeciemenQuestionaire("",false);
            pathCare.pathCareProcessingPage.testSetProctocol();
            pathCare.pathCareProcessingPage.testSetOption();
            pathCare.pathCareProcessingPage.proctocolProcdueQuestion();
//            try {
//                if (specimenNumbers.get(8).get(0).isEmpty() || specimenNumbers.get(8).get(0) == null) {
//                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(8), "", false, "", false, "");
//                } else {
                    pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(8).get(0), "", false, "", false, "");
//                }
//            }catch(IndexOutOfBoundsException e){
//                pathCare.pathCareProcessingPage.specimenNumPending(specimenNumbers.get(0).get(8), "", false, "", false, "");
//            }
            pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
            pathCare.pathCareProcessingPage.clickUpdateButton();
            pathCare.pathCareProcessingPage.backtopreviouspage();
            pathCare.labQueues.switchToDefaultContext();
            pathCare.labQueues.navigatetoHomepage();
            Assert.assertTrue("",pathCare.labQueues.searchEachEpisode(labEpisode));

        }

*/


    }

   /* @Nested
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

    @Nested
    class CytologyGynae{
        @Test
        void TP_123() throws Exception {
            Faker faker  = new Faker();
            String[]  testCollection = new String[]{"AGCYTO"};
            AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
            pathCare.interSystemloginPage.login(model.username, model.password);
            pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
            pathCare.interSystemloginPage.userselection();
            pathCare.pre_analytical.navigateRegistration();
            pathCare.pathCareScratch.setClickBackboneLabEpisodeSelect(false);
            labEpisode.addAll(pathCare.pathCareScratch.mutiplePatientEditSpecimen(faker,faker.demographic().sex().contentEquals("Male")? "Female":"Female",testCollection,false,false,4,"Cytology Gynae","Cytology Gynae Specimen","LBC Container",true));
            specimenNumbers = pathCare.pathCareScratch.searchMutliplePatient(labEpisode);
            pathCare.pre_analytical.navigatespecimenRecived();
            pathCare.pathCareLabSpecimenReception.specimenReceiveCreated(specimenNumbers);
            pathCare.pre_analytical.navigateTransfer();
            pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
            pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
            pathCare.pathCareLabTransferList.createShipment(specimenNumbers,false );




        }
    }
    */
   @Test
   @Order(10)
   public void TP_1123() throws Exception {

       value = false;
       Faker faker  = new Faker();
       String[]  testCollection = new String[]{"AHHIST"};
       String[]  receiveDateCollection = new String[]{"N","T"};
       AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
       pathCare.interSystemloginPage.login(model.username, model.password);
       var accessProfile = getAccessProfile("PC Depot Admin and Data Capture PANORAMA");
       pathCare.interSystemloginPage.setLocation(accessProfile);
       pathCare.interSystemloginPage.userselection();
       pathCare.pre_analytical.navigateRegistration();
       pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),"Female");
       pathCare.pathCareScratch.doctorSelection();
       labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-5",receiveDateCollection,testCollection,true,false,true,"Histology","Tissue Histo","Specimen Container","4119"));
       pathCare.pathCareScratch.setNewPatient(false);
       pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
       pathCare.pathCareScratch.doctorSelection();
       labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-5",receiveDateCollection,testCollection,true,false,true,"Histology","Tissue Histo","Specimen Container","4119"));
       pathCare.pathCareScratch.setNewPatient(false);
       pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
       pathCare.pathCareScratch.doctorSelection();
       labEpisode.add(pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-5",receiveDateCollection,testCollection,true,false,true,"Histology","Tissue Histo","Specimen Container","4119"));
       pathCare.pathCareScratch.setNewPatient(false);
       pathCare.pathCareScratch.patientdetails(pathCare.pathCareScratch.getName(),pathCare.pathCareScratch.getSurname(),pathCare.pathCareScratch.getDateOfBirth(),pathCare.pathCareScratch.getGender());
       pathCare.pathCareScratch.doctorSelection();
       pathCare.pathCareScratch.collectiondetailnewEditSpecimen("n-5",receiveDateCollection,testCollection,true,false,false,"Histology","Tissue Histo","Specimen Container","4119");
       pathCare.pathCareScratch.LinkSelectSpecimen();
       labEpisode.add(pathCare.pathCareScratch.updateClientDetails());
       value=labEpisode.size()==4;
       pathCare.pathCareScratch.writeLabEpisodesIntoFile(labEpisode);
       Assert.assertEquals(4,labEpisode.size());
   }

    @Test
    @Order(11)
    public void TP_1126() throws Exception {

        value = false;
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        labEpisode= pathCare.pathCareScratch.getLabEpisodesFromFile();

        pathCare.interSystemloginPage.setLocation("Lab Assistant PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateTransfer();

        pathCare.pathCareLabTransferList.testSetfield("Histology");
        pathCare.pathCareLabTransferList.statChangeWaitingLinkFind("Panorama Laboratory","PCP  - Histology Laboratory");
        pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
        pathCare.pathCareLabTransferList.tranferSpecimenIntoShipmentContainer();
        pathCare.pathCareLabTransferList.closePackage();
        shipmentNumber=pathCare.pathCareLabTransferList.shipmentNumber;
        pathCare.pre_analytical.navigateLogistics();
        value= pathCare.transferLogistics.EnterShipmentNumberInPickUpShipment(shipmentNumber);
        Assertions.assertTrue(value,"Update to status is not In Transit");
    }

}
