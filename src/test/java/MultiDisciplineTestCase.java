import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.*;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

public class MultiDisciplineTestCase extends RomanBase {

  public ChromeOptions options = new ChromeOptions();
  public Roman roman = super.roman();
  public PathCareApplication pathCare = null;
  public String dir = " ";

    @BeforeEach
    public void startup()  {
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
        //roman().Dispose();
    }

    @Test
    public void TP_7() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespide = pathCare.pathCareScratch.mutiplePatient(faker, testcollection,false,false, 1,true );
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide.get(0)));

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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide));

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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection,false,false,true ,"2100");
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide);
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
    @Disabled("Manual Testing")
    public void TP_110() throws Exception {
        Faker faker = new Faker();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        Assertions.assertTrue(pathCare.pathCareScratch.updatewithoutTestCollection("n-1","4119"));

    }


    @Test
    public void TP_13() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4", "BAA1"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers,true);
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pathCareLabTransferList.tranferlistLabepisodewithoutframe(labespide);
        pathCare.pathCareLabTransferList.switchToDefaultContext();
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersPackage(labespide, pathCare.pathCareLabSpecimenReception.specimenNumbers));
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-2", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers,true);
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(), new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday(11,55)),faker.demographic().sex());
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-5", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers,true);
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
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1", testcollection,false,false,true ,"2100");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide));

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
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker, testcollection,false,false, 2,true );
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);
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
        Assertions.assertEquals(labespides.size(), pathCare.resultEntry.fileChecker(dir,"TP_25-"));

    }

    @Test
    @Disabled("Need to resolve Test set comments on Result Entry")
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
        List<String> labEpisode = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1, true);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labEpisode);
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
        pathCare.resultEntry.singleTestsetComment(labEpisode.get(labEpisode.size()-1),ca199.testresult2,"Result has been checked in dilution, 1:50 onboard dilution");
        Assertions.assertEquals(1, pathCare.resultEntry.fileChecker(dir,"TP24-"));

    }

    @Test
    public void TP_32() throws Exception{

        Faker faker = new Faker();
        String[] testcollection = new String[]{"PBARTON"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1, true);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespides.get(0));
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers,true);
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


    @Test //done
    public void TP_29() throws Exception{
        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"ECA199R","BUCE"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1,true );
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);

        //Work Receive
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
        TestSetResults ca199 = TestSetResults.getExampleModel("CA199");
        pathCare.resultEntry.singleTestsetCommentWithoutReport(labespides.get(labespides.size()-1),ca199.testresult2,"Result has been checked in dilution, 1:50 onboard dilution");

        //Test Generator
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        pathCare.pathCareLabIntrumentResultGeneratorpage.testItemListGroup(new BUCUTestItem().value,"Abbott Alinity ci PCP","Alinity Tests",mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));

        //logout
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();

        //login
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("Verification Queue","Endocrinology","","Entered");

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.findlastresultlist(labespides.get(0),true,1,true);
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.searchResults("Failure Queue","Biochemistry","","Entered");
        pathCare.labQueues.findlastresultlist(labespides.get(0),true,1,true);
        pathCare.resultEntry.onlyapplyandvalidate(true);

    }

    @Test
    public void TP_34() throws Exception{
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Help Desk Operator RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("Phone Queue","","","");
        pathCare.labQueues.phoneQueues("Max Failures",false);
        HashMap<String,String> totalNumber = pathCare.labQueues.totalNumber;
        pathCare.pathCareProcessingPage.phonequeue();


        //Alert Failures
        if(!totalNumber.get("Alert Failures").isBlank()){
            pathCare.pre_analytical.navigatehome();
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("Alert Failures",true);
            pathCare.pathCareProcessingPage.phonequeue();

        }

        //Stat
        if(!totalNumber.get("STAT").isBlank()){
            pathCare.pre_analytical.navigatehome();
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("STAT",true);
            pathCare.pathCareProcessingPage.phonequeue();

        }

        //Urgent
        if(!totalNumber.get("Urgent").isBlank()){
            pathCare.pre_analytical.navigatehome();
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("Urgent",true);
            pathCare.pathCareProcessingPage.phonequeue();

        }

        //Routine
        if(!totalNumber.get("Routine").isBlank()){
            pathCare.pre_analytical.navigatehome();
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("Routine",true);
        }

        //Norm
        if(!totalNumber.get("Norm").isBlank()){
            pathCare.pre_analytical.navigatehome();
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("Norm",true);
            pathCare.pathCareProcessingPage.phonequeue();
        }

        //No Priority
        pathCare.pre_analytical.navigatehome();
        if(!totalNumber.get("No Priority").isBlank()){
            pathCare.labQueues.searchResults("Phone Queue","","","");
            pathCare.labQueues.phoneQueues("No Priority",true);
            pathCare.pathCareProcessingPage.phonequeue();
        }

        pathCare.labQueues.finaltotal();
        HashMap<String,String> totalNumber2 = pathCare.labQueues.totalNumber2;
        Assert.assertNotEquals("First total is not the same ",totalNumber.toString(),totalNumber2.toString());

    }

    @Test
    public void TP_127() throws Exception
    {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");

        //Registration
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1, true);

        //specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);


        //Transfer
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersWaiting(labespides.get(0),labespides));

        //Shipment
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values(),true );
        pathCare.pathCareLabTransferList.closePackage();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        Assert.assertTrue(pathCare.pathCareLabTransferList.checknumbersPackage(labespides.get(0),labespides));

    }

    @Test//done
    public void TP_30() throws Exception {

        Faker faker = new Faker();
        String[] testcollection = new String[]{"ECA199R","BUCE"};
        String[] dapartments = new String[]{"Biochemistry"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1,true );

        //Specimen Recieve
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);

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
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 3");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.singleTestsetCommentWithoutReport(labespides.get(labespides.size()-1),TestSetResults.getExampleModel("CA199").testresult2,"Result has been checked in dilution, 1:50 onboard dilution");
        pathCare.pre_analytical.switchtoMainiFrame();


        //Test Generator
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        pathCare.pathCareLabIntrumentResultGeneratorpage.testItemListGroup(new BUCUTestItem().value,"Abbott Alinity ci PCP","Alinity Tests",mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));


        //TP-29 Clinical Queues
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Chem/Endo 3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("Verification Queue","Endocrinology","","Entered");

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.findlastresultlist(labespides.get(0),true,1,true);
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.searchResults("Failure Queue","Biochemistry","","Entered");
        pathCare.labQueues.findlastresultlist(labespides.get(0),true,1,true);
        pathCare.resultEntry.onlyapplyandvalidate(true);

        //Change location
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Pathologist Chemistry");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.searchResults("","Endocrinology","","Entered");

        //result
        pathCare.labQueues.findresultonlistsearch(labespides.get(0),true,3,true);
        pathCare.resultEntry.authorise();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        // 2nd result
        pathCare.labQueues.searchResults("","Biochemistry","","Entered");
        pathCare.labQueues.findresultonlistsearch(labespides.get(0),true,2,true);
        pathCare.resultEntry.authorise();

    }

    @Test
    public void TP_31() throws Exception{

        Faker faker = new Faker();
        String[] testcollection = new String[]{"HMAL"};
        String[] dapartments = new String[]{"Haematology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1,true );

        //Specimen Recieve
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);

        //Work Recieve
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL Haem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple(dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Lab Result
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Haem 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();
        TestSetResults ca199 = TestSetResults.getExampleModel("MalariaScreen");
        pathCare.resultEntry.mutipleTestsetCommentMutatableWithoutReport(labespides.get(labespides.size()-1),ca199.testresult.split(","),"0.2","",true);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Haem 3");
        pathCare.interSystemloginPage.userselection();

        //lab Queue
        pathCare.labQueues.searchResults("Verification Queue","Haematology","","Entered");
       pathCare.labQueues.findlastresultlist(labespides.get(0),true,4,false);

        //Lab Results
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare Pathologist Clinical GEO");
        pathCare.interSystemloginPage.userselection();


        pathCare.labQueues.selecSearch(1);
        pathCare.labQueues.searchResults("","","Malaria screen","");
        pathCare.labQueues.findlastresultlist(labespides.get(0),true,1,false);
        pathCare.resultEntry.authorise();

    }

    @Test
    public void TP_33() throws Exception{
        Faker faker = new Faker();
        String dir = get_reportDir();
        SuperSetTesCSF superSetTesCSF = new SuperSetTesCSF();
        HashMap<String, List<String>> values = superSetTesCSF.value;
        String[] testcollection = new String[]{"MCCSF","MCCAG","MGSTREPP"};

        String[] dapartments = new String[]{"Microbiology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,true,1,true );

        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);
        pathCare.pre_analytical.switchtoMainiFrame();

        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Lab Result
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.mutlipleSuperSetTestSet(labespides.get(0),superSetTesCSF.value);

        //Change User role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Generate Test set
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        pathCare.pathCareLabIntrumentResultGeneratorpage.testItemListGroup(new SuperSetTestCSFTestItem().value,"Abbott Alinity ci George","Alinity Tests",mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.LabResultsEntry(labespides.get(0));
        Assert.assertTrue(pathCare.resultEntry.checkvaluesTestResults(new SuperSetTestCSFTestItem().value,"CSF Biochemistry"));

    }

}
