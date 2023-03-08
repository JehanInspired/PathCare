import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;

import static reporting.ExtentReport.get_reportDir;

public class TestSetDataSheet extends RomanBase {
    public ChromeOptions options = new ChromeOptions();
    public Roman roman = super.roman();
    public PathCareApplication pathCare = null;
    public String dir = " ";
    private LabespideData dataPatient = new LabespideData();

    @BeforeEach
    public void startup() throws IllegalAccessException {
        dir = get_reportDir();
        options = new ChromeOptions();
        dataPatient.patientInform();
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
    @Disabled
    public void registerPatient() throws Exception {

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        for(PatientModel patient:dataPatient.getPatientModelList()) {

            if(pathCare.interSystemloginPage.getLocation().isEmpty()) {
                pathCare.interSystemloginPage.setLocation(patient.getUserprofile());
                pathCare.interSystemloginPage.userselection();
                pathCare.pre_analytical.navigateRegistration();
            }else if(!pathCare.interSystemloginPage.getLocation().contentEquals(patient.getUserprofile())){
                pathCare.interSystemloginPage.setLocation(patient.getUserprofile());
                pathCare.interSystemloginPage.changelocation();
                pathCare.interSystemloginPage.userselection();
                pathCare.pre_analytical.navigateRegistration();
            }
            if(patient.getURN() != null){
                pathCare.pathCareScratch.searchPatientURN(patient.getURN());
                pathCare.pathCareScratch.createdSamePatient();
            }else{
                pathCare.pathCareScratch.patientdetails(patient.getGivenName(), patient.getSurname(), patient.getDateOfBirth(), patient.getSex());
            }
            pathCare.pathCareScratch.doctorSelection(patient.getReferringDoctor());
            dataPatient.write(patient.getPk()+","+pathCare.pathCareScratch.collectiondetailnewEditSpecimen(patient.getPk(),patient.getCollectionTime(),patient.getPatientLocation(),patient.getTestSet().toArray(String[]::new),!patient.getReceivedDate().isBlank(),dataPatient.getTestSetDetailsList(),dataPatient.getSpecimensArrayList(),dataPatient.getEditTestArrayList()));
        }

    }

    @Test
    public void speciemenReceive() throws Exception {
        if (dataPatient.readerList().isEmpty()) {
            registerPatient();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
        }

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = dataPatient.getPatientModelList().get(0).getUserprofile();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        HashMap<String, ArrayList<String>> labespisodesSpecimen = pathCare.pathCareScratch.searchMutliplePatientKeys(dataPatient.readerList());
        location = dataPatient.getSpecimenReceiveArrayList().get(0).getUserprofile_FK();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigatespecimenRecived();
        pathCare.pathCareLabSpecimenReception.entryMultipleLabspecimenSingle(pathCare.pre_analytical, pathCare.interSystemloginPage, labespisodesSpecimen, location, dataPatient.getSpecimenReceiveArrayList(), dataPatient.getWorkAreaReceives(), dataPatient.getTestCodeList());
        if (!pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList.isEmpty()){
            for (WorkAreaReceiveEntity workAreaReceiveEntity : pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList) {
                dataPatient.write(workAreaReceiveEntity.toString(),"WorkRecieve.txt");
            }
        }
        if (!pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()){
            for (SpecimenReceiveEntity specimenReceiveEntity : pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList) {
                dataPatient.write(specimenReceiveEntity.ToString(),"SpecimenRecieve.txt");
            }
        }


    }



    @Test
    public void work_Receive() throws Exception{

        if(pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList.isEmpty()){
            pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList =dataPatient.readWorkReceivList();
        }

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList.get(0).getUserprofile();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataWorkReceive(pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList),true,location,pathCare.interSystemloginPage,pathCare.pre_analytical);

    }

    @Test
    public void labInstrumentResultGenerator() throws Exception{
        if (dataPatient.readerList().isEmpty()) {
            registerPatient();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            speciemenReceive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            work_Receive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
        }

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.pathCareLabIntrumentResultGeneratorpage.
                multipleEspisode(dataPatient.getResultsGenerator_sysmexca620Geos(),
                        dataPatient.getResultsGenerator_rocheSysmexXGES(),
                        dataPatient.getResultsGeneratorAaGeorges(),
                        dataPatient.getResultsGenerator_aquios1s(),
                        dataPatient.getResultsGenerator_pcpBioFireFilmList(),
                        dataPatient.getResultsGenerator_sysmexCS2500s(),
                        dataPatient.getResultsGenerator_rocheSysmexXN1List(),
                        dataPatient.getResultsGenerator_AbbottAlinityc()
                        ,pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList,
                pathCare.labQueues,pathCare.interSystemloginPage);
    }

    @Test
    public void labResult() throws Exception{
       if (dataPatient.readerList().isEmpty()) {
            registerPatient();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            speciemenReceive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            work_Receive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            labInstrumentResultGenerator();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            workSheet();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();

        }



        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.resultEntry.structureLabResultEntry(dataPatient.getResultEntry(),
                pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList,dataPatient.getTestSetValuesList(),
                pathCare.interSystemloginPage,pathCare.analytical);
    }

    @Test
    public void workSheet() throws Exception {

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.pathCareLabWorkSheetResEntry.workSheetEntry(dataPatient.getWorkSheetResultEntryArrayList(),dataPatient.getWorkSheetResultValuesArrayList(),
                pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList, pathCare.analytical,pathCare.interSystemloginPage);

    }

    @Test
    public void queue() throws Exception {

        labResult();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.labQueues.labQueueSheet(dataPatient.getLabQueueEntities(),
                pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList, pathCare.analytical,pathCare.interSystemloginPage);
    }

}
