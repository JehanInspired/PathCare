import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class MultiDisciplineTestCase extends RomanBase {


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
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:", pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide.concat("-1"),testcollection.length));

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
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        Assertions.assertNotEquals("",labespide);

    }

    @Test
    public void TP_18() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BUCE"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:",pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide.concat("-1"),testcollection.length));

    }

    @Test
    public void TP_111() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BUCE"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        Assertions.assertNotEquals("",labespide);

    }

    @Test
    public void TP_11()throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HFBC","BUCE"};
        String[] dapartments = new String[]{"Haematology","Biochemistry"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();

        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.specimenNumbers),true);
        Assertions.assertTrue(pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.specimenNumbers),false), "Able to view Available option: Reset & Checkout");


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
        pathCare.pathCareScratch.patientdetails(faker.name().name(),faker.name().lastName(),"11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        Assertions.assertTrue(pathCare.pathCareScratch.updatewithoutTestCollection("n-1"));

    }


    @Test
    public void TP_13() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BAA1","IIGAM","IIGGSUB","HLUPUS","HVWILL","HPC","HFPSAG","HATIII"};
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespide);
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.specimenNumbers);
        //need to finish the last part


    }


    @Test
    public void TP_14()throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BAA1","IIGAM","IIGGSUB","HLUPUS","HVWILL","HPC","HFPSAG","HATIII"};
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length);
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
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersTransfer(labespide,pathCare.pathCareLabSpecimenReception.specimenNumbers));

    }

    @Test
    public void TP_16()throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BAA1","IIGAM","IIGGSUB","HLUPUS","HVWILL","HPC","HFPSAG","HATIII"};
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
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
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length);
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
        Assertions.assertTrue(pathCare.pathCareLabTransferList.checknumbersDelivery(labespide,pathCare.pathCareLabSpecimenReception.specimenNumbers));

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
    public void TP_35() throws Exception{

        Faker faker = new Faker();
        String[] testcollection = new String[]{"HFBC","BUCE"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.doctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assertions.assertEquals("Specimen already received:",pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length));

    }
}
