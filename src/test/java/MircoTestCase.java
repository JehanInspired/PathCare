import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.AutomationUserModel;
import applications.PathCareapplication.models.SuperSetTesCSF;
import applications.PathCareapplication.models.SuperSetTestCSFTestItem;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.*;

import static reporting.ExtentReport.get_reportDir;

public class MircoTestCase extends RomanBase {

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
    public void TP_98() throws Exception {

        Faker faker = new Faker();
        String[] testcollection = new String[]{"MCCSF","MCCAG","MGSTREPP"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture JEFFREY");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,true,1,true );
        Assertions.assertFalse(labespides.isEmpty());


    }

    @Test
    public void TP_52() throws Exception{
        Faker faker = new Faker();
        SuperSetTesCSF ca199 = new SuperSetTesCSF();
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
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);
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
       pathCare.resultEntry.mutlipleSuperSetTestSet(labespides.get(0),ca199.value);

    }

    @Test
    public void TP_50() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        String[] testcollection = new String[]{"MISS"};
        String[] dapartments = new String[]{"Microbiology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture JEFFREY");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1,true );

        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant Jby");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);
        pathCare.pre_analytical.switchtoMainiFrame();


        //Positive pack
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant Jby");
        pathCare.interSystemloginPage.userselection();
        //pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.tranferlistLabepisode(labespides.get(0));
        pathCare.pathCareLabTransferList.createShipment(pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values(),true );
        pathCare.pathCareLabTransferList.closePackage();

        //transfer pick up
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.pickUpShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //Drop Off
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(pathCare.pathCareLabTransferList.shipmentNumber);

        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);

        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        /*logout
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.logoff();*/

        //login
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Processing
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSingle(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),new String[]{"Preliminary","Growth present."," "});
        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),"Vitek ID",false,"",false,"");
        pathCare.pathCareProcessingPage.specimenComplete(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));
        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),"Observation",true,"small translucent colonies",true,"Organism");
        pathCare.pathCareProcessingPage.specimenComplete(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));

        //Home page
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();

        //Tool box on menu
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();

        pathCare.pathCareLabIntrumentResultGeneratorpage.intrumentTestGroup("Vitek Compact Analyser Geo","Culture",mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),"SALSP");
        pathCare.pre_analytical.switchtoMainiFrame();

        pathCare.labQueues.navigatetoHomepage();
        pathCare.analytical.navigateProcessing();

        pathCare.pathCareProcessingPage.searchSpecimenReceive(pathCare.pathCareProcessingPage.speciemenR.concat(".1"));
        pathCare.pathCareProcessingPage.specimenNumPending(pathCare.pathCareProcessingPage.speciemenR,"Manual Serological Abridged ID",false,"",true,"Organism");

        pathCare.pathCareProcessingPage.inprogress();
        //pathCare.pathCareProcessingPage.lookupSingle(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),new String[]{"Preliminary","Growth present."," "});
        pathCare.pathCareProcessingPage.specimenNumPending(pathCare.pathCareProcessingPage.speciemenR,"Manual Sensitivity",false,"",true,"Organism");
        pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();

        pathCare.pathCareProcessingPage.SingleProcessingTestSet("Scanty growth of","Salmonella group C",true,"Sensitivity to follow",new String[]{"Prelim-Unverified","Growth present."," "},true,true);

        //change User
        //login
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Pathologist Microbiology");
        pathCare.interSystemloginPage.userselection();

        //pathCare.labQueues.SelectSecondrow();
        Assertions.assertEquals(labespides.get(0), pathCare.labQueues.findlastresultlist(labespides.get(0),true,2,false));
        pathCare.singleProcess.SingleProcessingTestSet("","",new String[]{"Refer to Lab","Growth present."," "});

        //login
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Processing
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0).concat(".1"));
        pathCare.pathCareProcessingPage.antibioticsPanel("");
        pathCare.pathCareProcessingPage.antibiotics(new String[][]{{"Ampicillin-amoxicillin","Susceptible"},{"Ceftriaxone","Susceptible"},{"Sulfamethoxazole-trimethoprim","Susceptible"}},"0.016");
        pathCare.pathCareProcessingPage.SingleProcessingTestSet("","",true,"",new String[]{"Prelim-Unverified","Growth present."," "},true,true);

        //login
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Pathologist Microbiology");
        pathCare.interSystemloginPage.userselection();

        pathCare.labQueues.findlastresultlist(labespides.get(0),true,2,true);
        pathCare.pathCareProcessingPage.dir=dir;
        pathCare.pathCareProcessingPage.SingleProcessingTestSetWithReport("","","",new String[]{"Final","Growth present."," "});


    }

    @Test
    public void TP_56() throws Exception{

        Faker faker = new Faker();
        String[] testcollection = new String[]{"MGCPE"};
        String[] dapartments = new String[]{"Microbiology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labespides = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,false,1,true );


        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labespides);


        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataMultiple( dapartments,testcollection,pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen.values()),true);

        //Change User Role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Processing
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));
        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),"CPE Enzyme POSITIVE, no organism id required",false,"",false,"");
        pathCare.pathCareProcessingPage.specimenCompleteWithoutApplyorUpdate();
        pathCare.pathCareProcessingPage.numberfiles =1;
        pathCare.pathCareProcessingPage.dir=get_reportDir();
        pathCare.pathCareProcessingPage.SingleProcessingTestSetWithReport("","","",new String[]{"Final","Positive: Carbapenamse-producing Enterobacterales (CPE) isolated."," "});
    }


    @Test
    public void TP_53() throws Exception{
        Faker faker = new Faker();
        SuperSetTesCSF superSetTesCSF = new SuperSetTesCSF();
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
        Assertions.assertTrue(pathCare.resultEntry.checkvaluesTestResults(new SuperSetTestCSFTestItem().value,"CSF Biochemistry"));

        //Changing role*/
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Navigate to Processing
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0).concat(".1"),new String[]{"Preliminary","No growth after 24 hours incubation. Further results to follow."," "},true,false);

        //Change role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Day 2 Reading
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0).concat(".1"),new String[]{"Preliminary","No growth after 48 hours incubation. Further results to follow."," "},false,true);


        //Day 3 Reading
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();

        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0).concat(".1"),new String[]{"Preliminary","Growth present."," "},false,true);

        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0),"Vitek ID",false,"",false,"");
        pathCare.pathCareProcessingPage.specimenComplete(mutlipleSpeicmen_patientmultiple.get(labespides.get(0)).get(0));

        pathCare.pathCareProcessingPage.searchSpecimenReceive(pathCare.pathCareProcessingPage.speciemenR);
        pathCare.pathCareProcessingPage.specimenNumPending(pathCare.pathCareProcessingPage.speciemenR,"Observation",false,"",false,"organism");
        pathCare.pathCareProcessingPage.specimenComplete(pathCare.pathCareProcessingPage.speciemenR);

    }

    @Test
    public void TP_197() throws Exception{

        Faker faker = new Faker();
        String dir = get_reportDir();
        SuperSetTesCSF superSetTesCSF = new SuperSetTesCSF();
        String[] testcollection = new String[]{"MCCSF","MCCAG","MGSTREPP"};

        String[] dapartments = new String[]{"Microbiology"};
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.interSystemloginPage.setLocation("PC Depot Admin and Data Capture GEORGE");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        List<String> labEpisode = pathCare.pathCareScratch.mutiplePatient(faker,testcollection,false,true,1,true );

        //Specimen Receive
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Lab Assistant George");
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        HashMap<String, ArrayList<String>> mutlipleSpeicmen_patientmultiple = pathCare.pathCareLabSpecimenReception.mutlipleSpeicmen_Patientmultiple(labEpisode);
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
        pathCare.resultEntry.mutlipleSuperSetTestSet(labEpisode.get(0),superSetTesCSF.value);

        //Change User role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Generate Test set
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        pathCare.pathCareLabIntrumentResultGeneratorpage.testItemListGroup(new SuperSetTestCSFTestItem().value,"Abbott Alinity ci George","Alinity Tests",mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0));

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.LabResultsEntry(labEpisode.get(0));
        Assertions.assertTrue(pathCare.resultEntry.checkvaluesTestResults(new SuperSetTestCSFTestItem().value,"CSF Biochemistry"));

        //Changing role*/
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Navigate to Processing
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"),new String[]{"Preliminary","No growth after 24 hours incubation. Further results to follow."," "},true,false);

        //Change role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Day 2 Reading
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"),new String[]{"Preliminary","No growth after 48 hours incubation. Further results to follow."," "},false,true);


        //Day 3 Reading
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();

        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.lookupSinglewithoutOrgnimfield(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"),new String[]{"Preliminary","Growth present."," "},false,true);

        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0),"Vitek ID",false,"",false,"");
        pathCare.pathCareProcessingPage.specimenComplete(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0));

        pathCare.pathCareProcessingPage.searchSpecimenReceive(pathCare.pathCareProcessingPage.speciemenR);
        pathCare.pathCareProcessingPage.specimenNumPending(pathCare.pathCareProcessingPage.speciemenR,"Observation",false,"",false,"organism");
        pathCare.pathCareProcessingPage.specimenComplete(pathCare.pathCareProcessingPage.speciemenR);

        //Change role
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();

        //Generate Test set
        pathCare.labQueues.navigatetoToolBox();
        pathCare.labQueues.navigateTestSet();
        pathCare.pathCareLabIntrumentResultGeneratorpage.intrumentTestGroup("Vitek Compact Analyser Geo","Culture",mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0),"STRPN");

        //Processing
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();

        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(pathCare.pathCareProcessingPage.speciemenR);
        pathCare.pathCareProcessingPage.specimenNumPending(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"),"Manual Sensitivity",false,"",true,"Organism");
        pathCare.pathCareProcessingPage.specimenComplete(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"));
        pathCare.pathCareProcessingPage.searchSpecimenReceive(pathCare.pathCareProcessingPage.speciemenR);
        pathCare.pathCareProcessingPage.SingleProcessingTestSet("Scanty growth of","",false,"",new String[0],false,true);
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"));
        pathCare.pathCareProcessingPage.SingleProcessingTestSet("","",false,"",new String[]{"Prelim-Unverified","Growth present."," "},true,false);

        //Changing Roles
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Pathologist Microbiology");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.findresultonlistsearch(labEpisode.get(0),true,2,true);
        pathCare.pathCareProcessingPage.SingleProcessingTestSet("","",false,"Observation noted during processing",new String[]{"Refer to Lab","Growth present."," "},true,false);

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();


        //Day 4
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"));
        pathCare.pathCareProcessingPage.antibioticsPanel("CSF");
        pathCare.pathCareProcessingPage.antibiotics(new String[][]{{"Ampicillin","Susceptible"},{"Meropenem","Susceptible"},{"Vancomycin","Susceptible"},{"Chloramphenicol","Susceptible"},{"Rifampicin","Susceptible"},{"Penicillin","Susceptible"},{"Ceftriaxone","Susceptible"}},"");
        pathCare.pathCareProcessingPage.SingleProcessingTestSet("","",false,"",new String[]{"Pathologist review","Growth present."," "},true,false);

        pathCare.labQueues.switchToDefaultContext();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.analytical.navigateResultEntry();
        pathCare.resultEntry.LabResultsEntry(labEpisode.get(0));
        pathCare.resultEntry.singleLabResultsTestSetNameEntry("Bacterial Antigens: Streptococcus pneumoniae");
        pathCare.resultEntry.labEntryTestFieldMutatable(new String[]{"Negative","Negative"},"");
        pathCare.resultEntry.applyResultOnly();


        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC Pathologist Microbiology");
        pathCare.interSystemloginPage.userselection();

        pathCare.labQueues.findresultonlistsearch(labEpisode.get(0),true,4,true);

        pathCare.pathCareProcessingPage.SingleProcessingTestSet("","",false,"",new String[]{"Final","Growth present."," "},true,true);
        pathCare.pathCareProcessingPage.applyandvalidateNoReport();

        //Receiving Report
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation("PC MLP George C3");
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.navigatetoHomepage();
        pathCare.analytical.navigateProcessing();
        pathCare.pathCareProcessingPage.searchSpecimenReceive(mutlipleSpeicmen_patientmultiple.get(labEpisode.get(0)).get(0).concat(".1"));
        pathCare.pathCareProcessingPage.dir=dir;
        pathCare.pathCareProcessingPage.receiveReport();
    }


}
