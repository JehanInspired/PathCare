import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;

import applications.PathCareapplication.models.AutomationUserModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class ExampleTest extends RomanBase {


    @Test
    public void Test_4() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"HCD4","BUCE"};
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation(model.location);
        pathCare.interSystemloginPage.userselection();
        //pathCare.pathCareDashboardPage.choice();
        pathCare.pathCareScratch.patientdetails(faker.name().name(), faker.name().lastName(), "11/12/2002","Male");
        pathCare.pathCareScratch.DoctorSelection();
        pathCare.pathCareScratch.collectiondetailnew("n-1",testcollection);



    }
}
