import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;

import applications.PathCareapplication.models.AutomationUserModel;
import org.junit.jupiter.api.Test;

public class ExampleTest extends RomanBase {


    @Test
    public void Test_4() throws Exception {
        roman()._driver = roman().selenium.getChromeDriver();
        PathCareApplication pathCare = new PathCareApplication(roman());
        AutomationUserModel model = AutomationUserModel.getExampleModel();
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.userselection();
        pathCare.pathCareDashboardPage.preAnalytical();
        pathCare.pathCareScratch.patientdetails("Milly", "Simeily", "11/12/2002","Male");
        pathCare.pathCareScratch.DoctorSelection();
        pathCare.pathCareScratch.Collectiondetailnew("n-1","urea");




    }
}
