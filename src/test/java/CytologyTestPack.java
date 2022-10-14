
import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static reporting.ExtentReport.get_reportDir;

public class CytologyTestPack extends RomanBase {

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

    @AfterEach
    public void cleanUp(){
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();
        roman._driver.close();
        roman._driver.quit();
    }


    @Test
    public void TP_143() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker, testcollection,false,1,true );
        pathCare.pathCareScratch.searchPatient(labespides);
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract());

    }

    @Test
    public void TP_144() throws Exception {
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("CytologyTestPack Non-Gynae","Non-Gynae Specimen","Slide(s)",1);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        String labespides =  pathCare.pathCareScratch.updateClientDetails();
        pathCare.pathCareScratch.searchPatient(Arrays.asList(labespides));
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract());
    }

    @Test
    public void TP_145() throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"ANCYTONG"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PANORAMA");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        pathCare.pathCareScratch.mutiplePatient(faker, testcollection,false,1,false );
        pathCare.pathCareScratch.addMultipleSpecimen("CytologyTestPack Non-Gynae","Non-Gynae Specimen","Slide(s)",1);
        pathCare.pathCareScratch.specimenSelect();
        pathCare.pathCareScratch.editTestSet("Number of FNA Slides","4");
        String labespides =  pathCare.pathCareScratch.updateClientDetails();
        pathCare.pathCareScratch.searchPatient(Arrays.asList(labespides));
        Assert.assertNotEquals("",pathCare.pathCareScratch.specimenNumberExtract());
    }
}
