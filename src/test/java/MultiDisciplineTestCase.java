import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.TestSetResults;
import com.github.javafaker.Faker;
import org.junit.Assert;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

public class MultiDisciplineTestCase extends RomanBase {

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

  /* @AfterEach
    public void cleanUp(){
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();
        roman()._driver.close();
    }*/



    @Test
    public void TP_7() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespide = pathCare.pathCareScratch.mutiplePatient(faker, testcollection, 1);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide.get(0), testcollection.length));

    }

    @Test
    public void TP_4() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        Assertions.assertNotEquals("", labespide);

    }

    @Test
    public void TP_18() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BUCE"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide.concat("-1"), testcollection.length));

    }

    @Test
    public void TP_111() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BUCE"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        Assertions.assertNotEquals("", labespide);

    }

    @Test
    public void TP_11() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HFBC", "BUCE"};
        String[] dapartments = new String[]{"Haematology", "Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();

        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata(dapartments, testcollection, pathCare.pathCareLabSpecimenReception.specimenNumbers), true);
        Assertions.assertTrue(pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata(dapartments, testcollection, pathCare.pathCareLabSpecimenReception.specimenNumbers), false), "Able to view Available option: Reset & Checkout");


    }

    @Test
    public void TP_23() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        Assertions.assertTrue(pathCare.pathCareDashboardPage.sideMenusCheckingResultsAnalystical());

    }




    @Test
    public void TP_110() throws Exception {
        Faker faker = new Faker();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        Assertions.assertTrue(pathCare.pathCareScratch.updatewithoutTestCollection("n-1"));

    }


    @Test
    public void TP_13() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BAA1", "IIGAM", "IIGGSUB", "HLUPUS", "HVWILL", "HPC", "HFPSAG", "HATIII"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pathCareLabTransferList.tranferlistLabepisodewithoutframe(labespide);
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersTransfer(labespide, pathCare.pathCareLabSpecimenReception.specimenNumbers));


    }


    @Test
    public void TP_14() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BAA1", "IIGAM", "IIGGSUB", "HLUPUS", "HVWILL", "HPC", "HFPSAG", "HATIII"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersTransfer(labespide, pathCare.pathCareLabSpecimenReception.specimenNumbers));

    }

    @Test
    public void TP_16() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BAA1", "IIGAM", "IIGGSUB", "HLUPUS", "HVWILL", "HPC", "HFPSAG", "HATIII"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002", "Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersDelivery(labespide, pathCare.pathCareLabSpecimenReception.specimenNumbers));

    }


    @Test
    public void TP_17() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateLogistics();
        Assertions.assertTrue(pathCare.transferLogistics.dropOffShipmentInvalid("12082022/2100/4310/03/01"));


    }

    @Test
    public void TP_35() throws Exception {

        Faker faker = new Faker();
        String[] testcollection = new String[]{"HFBC", "BUCE"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(),  new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)), faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length));

    }

    @Test
    public void TP_25() throws Exception {

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"HCOLDAGG"};
        String[] dapartments = new String[]{"Haematology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker, testcollection, 2);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple(dapartments, testcollection, pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()), true);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Haem 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();

        TestSetResults coldAgg = TestSetResults.getExampleModel("ColdAgglutinins");
        pathCare.resultEntry.mutlipleLabEntryTestSet(labespides, coldAgg.testresult, coldAgg.testresult2, dir);
        Assertions.assertEquals(labespides.size(), pathCare.resultEntry.fileChecker(dir));


    }

    @Test
    public void TP_24() throws Exception {

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"ECA199R"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();

        TestSetResults ca199 = TestSetResults.getExampleModel("CA199");
        pathCare.resultEntry.singleTestsetComment(labespides.get(labespides.size()-1),ca199.testresult2,"Result has been checked in dilution, 1:50 onboard dilution");
        Assertions.assertEquals(1, pathCare.resultEntry.fileChecker(dir));

    }


    @Test
    public void TP_19() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"HGRPCOOMBS"};
        String[] dapartments = new String[]{"Haematology"};
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,3);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,2);//two specimens
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple(dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        pathCare.pathCareLabTransferList.closePackage();

    }


    @Test
    public void TP_32() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"PBARTON"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespides.get(0), testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        pathCare.pathCareLabTransferList.closePackage();
        //Pick Up
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);
        //Received
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.receiceiveShipment();
        pathCare.pathCareLabTransferList.checknumbersReceived(labespides.get(0), pathCare.pathCareLabSpecimenReception.specimenNumbers);

    }


    @Test
    public void TP_108() throws Exception{
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.querySearchSave("Verification Queue","Biochemistry","", model.location);
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.querysearchLabResults(model.location);
        pathCare.pre_analytical.navigateTransfer();
       Assertions.assertTrue(pathCare.pathCareLabTransferList.labSearches());




    }

    @Test
    public void TP_109() throws Exception{
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.queuesSelectResult("Verification Queue","Biochemistry","", model.location);
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.labResultsSelectResult(model.location);
        pathCare.pre_analytical.navigateTransfer();
        Assert.assertTrue(pathCare.pathCareLabTransferList.queuesSelectResult());




    }


    @Test
    public void TP_29() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"ECA199R"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C1");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Positive pack
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        //pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values());
        pathCare.pathCareLabTransferList.closePackage();

        //transfer pick up
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //Drop Off
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);

        //Results Entry
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();
        TestSetResults ca199 = TestSetResults.getExampleModel("CA199");
        pathCare.resultEntry.singleTestsetCommentWithoutReport(labespides.get(labespides.size()-1),ca199.testresult2,"Result has been checked in dilution, 1:50 onboard dilution");

        //logout
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();

        //login
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("Verification Queue","Endocrinology","");

        //result
        Assertions.assertEquals(labespides.get(0), pathCare.labQueues.findlastresultlist(labespides.get(0),false,1));
    }

    @Test//Being fixed
    public void TP_34() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"MGCPE"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");

        //Registration
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);

        //Specimen received and work area received
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);


        //Phone Queue
        pathCare.pre_analytical.switchtoMainiFrame();
        //pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Help Desk Operator RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.queuesSelectResult("Phone Queue","Microbiology","Authorised","");
        pathCare.labQueues.findlastresultlist("22004434",false,1);







    }

    @Test
    public void TP_127() throws Exception
    {
        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"HCD4"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");

        //Registration
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);

        //specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);


        //Transfer
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        Assert.assertTrue(pathCare.pathCareLabTransferList.checknumbersWaiting(labespides.get(0),labespides));

        //Shipment
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values());
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        Assert.assertTrue(pathCare.pathCareLabTransferList.checknumbersPackage(labespides.get(0),labespides));

    }

    @Test
    public void TP_30() throws Exception {


        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"ECA199R","BUCE"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,1);

        //Specimen Recieve
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides,testcollection.length);

        //Work Recieve
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL Chem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Results Entry
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.singleTestsetCommentWithoutReport(labespides.get(labespides.size()-1),TestSetResults.getExampleModel("CA199").testresult2,"Result has been checked in dilution, 1:50 onboard dilution");
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.resultEntry.backtoTestSetList();
        pathCare.resultEntry.mutipleTestsetCommentWithoutReport(labespides.get(labespides.size()-1), TestSetResults.getExampleModel("BUCE").testresult.split(","),"Test",true);


        //logout
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();

        //login
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Pathologist Chemistry");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("","Endocrinology","Entered");

        //result
        Assertions.assertEquals(labespides.get(0), pathCare.labQueues.findlastresultlist(labespides.get(0),true,1));

    }



}
