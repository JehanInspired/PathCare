import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.TestSetResults;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

public class MultiDisciplineTestCase extends RomanBase {


    @AfterEach
    public void cleanUp(){

        roman()._driver.close();

    }

    @Test
    public void TP_7() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        Assertions.assertTrue(pathCare.pathCareDashboardPage.sideMenusCheckingResultsAnalystical());

    }

    @Test
    public void TP_110() throws Exception {
        Faker faker = new Faker();
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.default_directory", "C:\\");
        options.setExperimentalOption("prefs", chromeOptionsMap);

        roman()._driver = roman().selenium.getChromeDriver(options);
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.default_directory", "C:\\");
        options.setExperimentalOption("prefs", chromeOptionsMap);

        roman()._driver = roman().selenium.getChromeDriver(options);
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.default_directory", "C:\\");
        options.setExperimentalOption("prefs", chromeOptionsMap);
        roman()._driver = roman().selenium.getChromeDriver(options);
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingleReception(labespide, testcollection.length));

    }

    @Test
    public void TP_25() throws Exception {

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"HCOLDAGG"};
        String[] dapartments = new String[]{"Haematology"};
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.open_pdf_in_system_reader", false);
        chromeOptionsMap.put("download.prompt_for_download", false);
        chromeOptionsMap.put("profile.default_content_settings.popups ", 0);
        chromeOptionsMap.put("download.extensions_to_open ", "applications/pdf");
        chromeOptionsMap.put("download.default_directory", dir);
        options.setExperimentalOption("prefs", chromeOptionsMap);
        roman()._driver = roman().selenium.getChromeDriver(options);

        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker, testcollection, 2);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Med Lab Professional RL Haem 1");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides, testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple(dapartments, testcollection, pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()), true);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
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
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.open_pdf_in_system_reader",false);
        chromeOptionsMap.put("download.prompt_for_download",false);
        chromeOptionsMap.put("profile.default_content_settings.popups ",0);
        chromeOptionsMap.put("download.extensions_to_open ","applications/pdf");
        chromeOptionsMap.put("download.default_directory", dir);
        options.setExperimentalOption("prefs", chromeOptionsMap);
        roman()._driver = roman().selenium.getChromeDriver(options);

        PathCareApplication pathCare = new PathCareApplication(roman());
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
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
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
        pathCare.resultEntry.singleTestsetComment(labespides.get(labespides.size()-1),ca199.testresult2,"Result has been checked in dilution, 1:50 onboard dilution",dir);
        Assertions.assertEquals(1, pathCare.resultEntry.fileChecker(dir));

    }


    @Test
    public void TP_32() throws Exception{
        //isssue on item TP_32
        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"PBARTON"};
        String[] dapartments = new String[]{"Molecular"};
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        chromeOptionsMap.put("download.open_pdf_in_system_reader",false);
        chromeOptionsMap.put("download.prompt_for_download",false);
        chromeOptionsMap.put("profile.default_content_settings.popups ",0);
        chromeOptionsMap.put("download.extensions_to_open ","applications/pdf");
        chromeOptionsMap.put("download.default_directory", dir);
        options.setExperimentalOption("prefs", chromeOptionsMap);
        roman()._driver = roman().selenium.getChromeDriver(options);
        PathCareApplication pathCare = new PathCareApplication(roman());
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
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(labespides.get(0));
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.receiceiveShipment();
        pathCare.pathCareLabTransferList.checknumbersReceived(labespides.get(0), pathCare.pathCareLabSpecimenReception.specimenNumbers);

    }
}
