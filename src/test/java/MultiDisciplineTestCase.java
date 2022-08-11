import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.DepartmentsModel;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assert.assertEquals("Specimen already received:",pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide.concat("-1"),testcollection.length));

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
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        Assert.assertNotEquals("",labespide);

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
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assert.assertEquals("Specimen already received:",pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide.concat("-1"),testcollection.length));

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
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        Assert.assertNotEquals("",labespide);

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
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenReception(labespide,testcollection.length);
        pathCare.pathCareLabSpecimenReception.switchtoMainiFrame();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();

        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.specimenNumbers),true);
        Assert.assertTrue("Able to view Available option: Reset & Checkout",pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdata( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.specimenNumbers),false));


    }
}
