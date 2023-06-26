import Roman.Roman;
import Roman.RomanBase;
import applications.PathCareapplication.PathCareApplication;
import applications.PathCareapplication.models.*;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static applications.PathCareapplication.tool.ExcelExtractorList.getAccessProfile;
import static reporting.ExtentReport.get_reportDir;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSetDataSheet extends RomanBase {
    public ChromeOptions options = new ChromeOptions();
    public Roman roman = super.roman();
    public PathCareApplication pathCare = null;
    public String dir = " ";
    private final LabespideData dataPatient = new LabespideData();
    static ArrayList<String> labEpisode = new ArrayList<>();
    static String shipmentNumber ="";
    static boolean value = false;
     ArrayList<String> labespisodesSpecimen = new ArrayList<>();
    @BeforeEach
    public void startup() {
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
    @Order(1)
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

             labespisodesSpecimen.add(patient.getPk()+","+ pathCare.pathCareScratch.collectiondetailnewEditSpecimen(patient.getPk(),patient.getCollectionTime(),patient.getPatientLocation(),patient.getTestSet()
                     .toArray(String[]::new),!patient.getReceivedDate().isBlank(),dataPatient.getTestSetDetailsList(),dataPatient.getSpecimensArrayList(),dataPatient.getEditTestArrayList()));
            dataPatient.write(labespisodesSpecimen);

            pathCare.pathCareScratch.writeLabEpisodesIntoFile(pathCare.pathCareScratch.labEpisode);
        }

    }
    public void registerPatients() throws Exception {

       // AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
       // pathCare.interSystemloginPage.login(model.username, model.password);
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

            labespisodesSpecimen.add(patient.getPk()+","+ pathCare.pathCareScratch.collectiondetailnewEditSpecimen(patient.getPk(),patient.getCollectionTime(),patient.getPatientLocation(),patient.getTestSet()
                    .toArray(String[]::new),!patient.getReceivedDate().isBlank(),dataPatient.getTestSetDetailsList(),dataPatient.getSpecimensArrayList(),dataPatient.getEditTestArrayList()));
            dataPatient.write(labespisodesSpecimen);

            pathCare.pathCareScratch.writeLabEpisodesIntoFile(pathCare.pathCareScratch.labEpisode);
        }

    }    @Test
    @Order(3)
    public void Transfer() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = dataPatient.getTransferArrayList().get(0).getUserprofile_FK();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.switchToDefaultContext();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.statChangeWaitingLinkFind(dataPatient.getTransferArrayList().get(0).getFrom_lab_site(),
                dataPatient.getTransferArrayList().get(0).getTo_lab_site());
        pathCare.pathCareLabTransferList.selectlistlabespido(labEpisode);
        pathCare.pathCareLabTransferList.tranferSpecimenIntoShipmentContainer();
        pathCare.pathCareLabTransferList.closePackage();
        shipmentNumber=pathCare.pathCareLabTransferList.shipmentNumber;
        pathCare.pathCareScratch.writeShipmentNumberIntoFile(shipmentNumber);

        pathCare.pathCareLabTransferList.enterPackNumberAndFind(shipmentNumber);
        value= pathCare.pathCareLabTransferList.shipmentPackageIsPacked();
        Assertions.assertTrue(value,"Update to status is not In Packed");
    }
    @Test
    @Order(4)
    public void TransferLogistics() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        shipmentNumber = pathCare.pathCareScratch.getShipmentNumberFromFile();
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = dataPatient.getLogisticsEntityArrayList().get(0).getUserprofile_FK();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.switchToDefaultContext();
        pathCare.pre_analytical.navigateLogistics();

        pathCare.transferLogistics.EnterShipmentNumberInPickUpShipment(shipmentNumber);
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.enterPackNumberAndFind(shipmentNumber);
        value= pathCare.pathCareLabTransferList.shipmentPackageIsInTransit();
        Assertions.assertTrue(value,"Update to status is not In Transit");
    }
    @Test
    @Order(5)
    public void TransferLogisticsDropOff() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        shipmentNumber = pathCare.pathCareScratch.getShipmentNumberFromFile();
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = dataPatient.getLogisticsDropOffEntitiesList().get(0).getUserprofile_FK();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.labQueues.switchToDefaultContext();
        pathCare.pre_analytical.navigateLogistics();
        pathCare.transferLogistics.dropOffShipmentValid(shipmentNumber,dataPatient.getLogisticsEntityArrayList().get(0).getAcknowledged_By());

        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(dataPatient.getLogisticsEntityArrayList().get(0).getUserprofile_FK());
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateTransfer();
        pathCare.pathCareLabTransferList.enterPackNumberAndFind(shipmentNumber);
        value= pathCare.pathCareLabTransferList.shipmentPackageIsInDelivered();
        Assertions.assertTrue(value,"Update to status is not In Delivered");
    }
    @Test
    public void TP_1032_GenericWorksheetControl() throws Exception{

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);

        //registration
        registerPatients();
        labEpisode= pathCare.pathCareScratch.getLabEpisodesFromFile();
        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(dataPatient.getWorkAreaReceives().get(0).getUserprofile_FK());
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.workAreaReceive(dataPatient.getWorkAreaReceives().get(0).getDepartment(),dataPatient.getWorkAreaReceives().get(0).getWorkArea(),labEpisode);

        // worksheet control
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.analytical.navigateWorkSheetControl();
        pathCare.worksheetControlPage.findWorksheetdeatils(dataPatient.getWorksheetControlEntitArrayList().get(0).getWorksheetType(),dataPatient.getWorksheetControlEntitArrayList().get(0).getDateCreatedFrom(), "");
        pathCare.worksheetControlPage.editWorkSheet();
        pathCare.worksheetControlPage.printWorkSheet();
        boolean printed = pathCare.worksheetControlPage.isWorksheetPrinted();
        Assert.assertTrue("Work sheet control failed to be printed",printed);
    }
    @Test
    public void TP_1035_GenericProcedure() throws Exception{
        Faker faker = new Faker();
        String descriptiopn = "Hereditary-Haemochromatosis-" + pathCare.procedures.generateRandon()+"-"+ faker.name().name();
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username,model.password);

        //registration
        registerPatients();
        labEpisode= pathCare.pathCareScratch.getLabEpisodesFromFile();

        //Work Receive
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(dataPatient.getWorkAreaReceives().get(0).getUserprofile_FK());
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.labworkareaswitch();
        pathCare.workAreaReceptionPage.workAreaReceive(dataPatient.getWorkAreaReceives().get(0).getDepartment(),dataPatient.getWorkAreaReceives().get(0).getWorkArea(),labEpisode);

        //Molecular: Completing Procedures
        pathCare.pre_analytical.switchtoMainiFrame();
        pathCare.interSystemloginPage.changelocation();
        pathCare.interSystemloginPage.setLocation(dataPatient.getProcedureEntityArrayList().get(0).getUserprofile_FK());
        pathCare.interSystemloginPage.userselection();
        pathCare.analytical.navigateProcedures();
        pathCare.procedures.searchProtocolProcedure(dataPatient.getProcedureEntityArrayList().get(0).getDepartment(),dataPatient.getProcedureEntityArrayList().get(0).getTestSet());
        pathCare.procedures.saveSearchAndUpdate(descriptiopn);
        pathCare.procedures.savedSearches(descriptiopn);
        pathCare.procedures.multipleSearch(labEpisode);

        //Worksheet Result Entry (WRE): Adding an Attachment
        pathCare.analytical.navigateWorkSheetRes();
        pathCare.pathCareLabWorkSheetResEntry.findWorksheetDefinition(dataPatient.getWre_AttachmentsEntityArrayList().get(0).getWorksheetDefinition());
        pathCare.pathCareLabWorkSheetResEntry.uploadWorksheetDocument("Hereditary Haemochromatosis Datasheet",dataPatient.getWre_AttachmentsEntityArrayList().get(0).getTestSet());
        pathCare.pathCareLabWorkSheetResEntry.ClickApplyButton();
        pathCare.pathCareLabWorkSheetResEntry.ClickEpisodeLinkAndCheckAttachedDocs();
        Assert.assertTrue("Worksheet result entry",pathCare.pathCareLabWorkSheetResEntry.worksheetResEntryDisplayed());


    }
    @Test
    @Order(6)
    public void work_Receive() throws Exception{

        if(pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList.isEmpty()){
            pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList = dataPatient.readWorkReceivList();
        }

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList.get(0).getUserprofile_FK();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateWorkRecived();
        pathCare.workAreaReceptionPage.departmentWorkArea(pathCare.workAreaReceptionPage.setupdataWorkReceive(pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList),true,location,pathCare.pathCareLabSpecimenReception.workAreaReceiveEntityArrayList,pathCare.interSystemloginPage,pathCare.pre_analytical);

    }

    @Test
    @Order(7)
    public void labInstrumentResultGenerator() throws Exception{
        if (dataPatient.readerList().isEmpty()) {
            registerPatient();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            //speciemenReceive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            work_Receive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
        }

        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.pathCareLabIntrumentResultGeneratorpage.
                multipleEpisode(dataPatient.getResultsGenerator_sysmexca620Geos(),
                        dataPatient.getResultsGenerator_rocheSysmexXGES(),
                        dataPatient.getResultsGeneratorAaGeorges(),
                        dataPatient.getResultsGenerator_aquios1s(),
                        dataPatient.getResultsGenerator_pcpBioFireFilmList(),
                        dataPatient.getResultsGenerator_sysmexCS2500s(),
                        dataPatient.getResultsGenerator_rocheSysmexXN1List(),
                        dataPatient.getResultsGenerator_AbbottAlinityc(),
                        dataPatient.getResultsGenerator_Abbott()
                        ,pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList,
                pathCare.labQueues,pathCare.interSystemloginPage);
    }

    @Test
    @Order(8)
    public void labResult() throws Exception{
       if (dataPatient.readerList().isEmpty()) {
            registerPatient();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            //speciemenReceive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            work_Receive();
            pathCare.pre_analytical.switchtoMainiFrame();
            pathCare.interSystemloginPage.logoff();
            labInstrumentResultGenerator();
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
    @Order(9)
    public void workSheet() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.pathCareLabWorkSheetResEntry.workSheetEntry(dataPatient.getWorkSheetResultEntryArrayList(),dataPatient.getWorkSheetResultValuesArrayList(),
                pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList.isEmpty()? dataPatient.readSpecimenReceivList():pathCare.pathCareLabSpecimenReception.specimenReceiveEntityArrayList, pathCare.analytical,pathCare.interSystemloginPage);

    }

    @Test
    @Order(10)
    public void queue() throws Exception {
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.labQueues.labQueueSheet(dataPatient.getLabQueueEntities(),dataPatient.getlabQueueValuesEntity(),
                dataPatient.readerList(), pathCare.resultEntry,pathCare.pathCareProcessingPage,pathCare.interSystemloginPage);
    }

}

class QCTestCase extends RomanBase{

    public ChromeOptions options = new ChromeOptions();
    public Roman roman = super.roman();
    public PathCareApplication pathCare = null;
    public String dir = " ";
    private final LabespideData dataPatient = new LabespideData();
    @BeforeEach
    public void startup() {
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

    @Test
    public void labResultGeneratorQC() throws Exception{
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.pathCareLabIntrumentResultGeneratorpage.
                multipleEpisodeQC(dataPatient.getResultsGeneratorPcps()
                        ,pathCare.labQueues,pathCare.interSystemloginPage);
    }
}

class ReferenceRangesTestCase extends RomanBase{

    public ChromeOptions options = new ChromeOptions();
    public Roman roman = super.roman();
    public PathCareApplication pathCare = null;
    public String dir = " ";
    private final LabespideData dataPatient = new LabespideData();
    @BeforeEach
    public void startup() {
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

    @Test
    @Order(1)
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
    @Order(2)
    public void labInstrumentResultGenerator() throws Exception{


        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        String location = dataPatient.getPatientModelList().get(0).getUserprofile();
        pathCare.interSystemloginPage.setLocation(location);
        pathCare.interSystemloginPage.userselection();
        pathCare.pre_analytical.navigateRegistration();
        HashMap<String, ArrayList<String>> labespisodesSpecimen = pathCare.pathCareScratch.searchMutliplePatientKeys(dataPatient.readerList());
        pathCare.pathCareLabIntrumentResultGeneratorpage.
                multipleEpisode(dataPatient.getResultsGenerator_AbbottAlinityc(),labespisodesSpecimen
                        ,pathCare.labQueues,pathCare.interSystemloginPage);
        dataPatient.write(labespisodesSpecimen.toString().replace("{","").replace("}",""),"ReferenceRangesResources.txt");

    }

    @Test
    @Order(3)
    public void labResultVerify() throws Exception{
        AutomationUserModel model = AutomationUserModel.getExampleModel("PCLABAssistantGeorge");
        pathCare.interSystemloginPage.login(model.username, model.password);
        pathCare.resultEntry.mutlipleVerifyLabEpisode(dataPatient.getResultsEntryVerifies(), dataPatient.readerList(),pathCare.interSystemloginPage,pathCare.analytical);

    }


}


