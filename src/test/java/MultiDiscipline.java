import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MultiDiscipline extends RomanBase {


    @Test
    public void TP_7() throws Exception {
        Faker faker = new Faker();
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.DoctorSelection();
        String labespide = pathCare.pathCareScratch.collectiondetailnew("n-1","urea");
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(model.accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        Assert.assertEquals("<No episode selected>",pathCare.pathCareLabSpecimenReception.entryLabspecimenReceptionNoepisode(labespide));;








    }
}
