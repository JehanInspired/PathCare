import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;

import applications.PathCareapplication.models.TP100;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static applications.PathCareapplication.tool.ExcelExtractorList.getAccessProfile;
import static reporting.ExtentReport.get_reportDir;

public class MolecularTestCase extends RomanBase {

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
        roman().Dispose();
    }



    @Test
    public void TP_102() throws Exception {
        Faker faker = new Faker();
        String[] testCollection = new String[]{"PRPBF", "PHIV1QNT,PMEBF", "PHIV1QNT,PHIV1QL,PRPBF","PRPBF,PHIV1QL","PHIV1QNT,PRPBF"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labEpisode = pathCare.pathCareScratch.mutiplePatientWithDifferentTestset(faker, testCollection,false);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();

        //Specimen Received
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labEpisode);

    }

    @Test
    public void TP_106() throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"PRPBF", "PHIV1QNT,PMEBF", "PHIV1QNT,PHIV1QL,PRPBF","PRPBF,PHIV1QL","PHIV1QNT,PRPBF"};
        String[] departments = new String[]{"Molecular"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatientWithDifferentTestset(faker, testcollection,false);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();

        //Specimen Received
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);


        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL Chem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataSpecimentMultiple(departments,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);



    }

    @Test

    public void TP_100() throws Exception{

        Faker faker = new Faker();
        String[] testcollection = new String[]{"PRPBF", "PHIV1QNT,PMEBF", "PHIV1QNT,PHIV1QL,PRPBF","PRPBF,PHIV1QL","PHIV1QNT,PRPBF"};
        String[] departments = new String[]{"Molecular"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        TP100 tp100 = new TP100();
        tp100.extractTestData("src/main/resources/TP-100.xlsx");
        pathCare.interSystemloginPage.login(model.username,model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatientWithDifferentTestset(faker, testcollection,false);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();

        //Specimen Received
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);

        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL Chem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataSpecimentMultiple(departments,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Change role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();

        //Generate Test set
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        ArrayList<String[]> intruments = new ArrayList<>(Arrays.asList(new String[]{"PCP BioFire FilmArray Torch 1", "BioFire FilmArray"}, new String[]{"PCP GeneXpert C", "Xpert"},new String[]{"PCP BioFire FilmArray Torch 1", "BioFire FilmArray"}));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 1-PRPBF")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(0)).get(0),intruments.get(0));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 2-PHIV1QNT")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(1)).get(0),intruments.get(1));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 2-PMEBF")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(1)).get(2),intruments.get(0));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 3-PHIV1QNT")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(2)).get(0),intruments.get(1));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 3-PHIV1QL")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(2)).get(2),intruments.get(1));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 3-PRPBF")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(2)).get(3),intruments.get(0));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 4-PRPBF")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(3)).get(0),intruments.get(0));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 4-PHIV1QL")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(3)).get(3),intruments.get(1));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 5-PHIV1QL")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(4)).get(0),intruments.get(1));
        pathCare.pathCareLabIntrumentResultGeneratorpage.multipleTestItemListGroup(pathCare.pathCareLabIntrumentResultGeneratorpage.testitemToHashMap(tp100.value.get("Patient 5-PRPBF")),pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.get(labespides.get(4)).get(2),intruments.get(0));


        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();



        //lab Queues
        pathCare.labQueues.findresultonlistsearch(labespides.get(0),true,6,true);
        pathCare.resultEntry.onlyapplyandvalidate(true);
        pathCare.labQueues.switchToDefaultContext();
        pathCare.labQueues.navigatetoHomepage();

        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Virologist");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(1),true,3,true); //Patient 2.1
        pathCare.resultEntry.authorise();


        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.labQueues.findresultonlistsearch(labespides.get(1),true,6,true); //Patient 2.2
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Virologist");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(2),true,3,true); //Patient 3
        pathCare.resultEntry.authorise();

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(2),true,6,true); //Patient 3.1
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(2),true,6,true); //Patient 3.2
        pathCare.resultEntry.onlyapplyandvalidate(true);

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(3),true,6,true); //Patient 4.1
        pathCare.resultEntry.onlyapplyandvalidate(true);


        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Virologist");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(3),true,3,true); //Patient 4.2
        pathCare.resultEntry.authorise();

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Virologist");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(4),true,3,true); //Patient 5.1
        pathCare.resultEntry.authorise();

        pathCare.labQueues.switchToDefaultContext();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PathCare PCP Molecular Scientist C2");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labespides.get(4),true,6,true); //Patient 5.2
        pathCare.resultEntry.onlyapplyandvalidate(true);


    }
    @Test
    public void TP_1032() throws Exception{
        Faker faker = new Faker();
        String[] testcollection = new String[]{"PRPBF", "PHIV1QNT,PMEBF", "PHIV1QNT,PHIV1QL,PRPBF","PRPBF,PHIV1QL","PHIV1QNT,PRPBF"};
        String[] departments = new String[]{"Molecular"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);
        var accessProfile = getAccessProfile("PC Depot Admin and Data Capture PCP");
        pathCare.interSystemloginPage.setLocation(accessProfile);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatientWithDifferentTestset(faker, testcollection,false);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL");
        pathCare.interSystemloginPage.userselection();

        //Specimen Received
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);
pathCare.analytical.navigateWorkSheetRes();

        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant RL Chem");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataSpecimentMultiple(departments,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);



    }
}
