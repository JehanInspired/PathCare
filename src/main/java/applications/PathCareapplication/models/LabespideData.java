package applications.PathCareapplication.models;

import applications.PathCareapplication.tool.ExcelExtractorList;
import java.io.*;
import java.util.*;

import static applications.PathCareapplication.tool.ExcelExtractorList.*;


public  class LabespideData  {


    private List<PatientModel> patientModelList = new ArrayList<>();

    private List<WorkAreaReceiveEntity> workAreaReceiveEntities = new ArrayList<>();

    //Result Generator
    private List<ResultsGenerator_AbbottAlinityc> resultsGenerator_AbbottAlinityc = new ArrayList<>();

    private List<ResultsGenerator_RocheSysmexXN1> resultsGenerator_rocheSysmexXN1List = new ArrayList<>();

    private List <ResultsGenerator_SysmexCS2500> resultsGenerator_sysmexCS2500s = new ArrayList<>();

    private List<ResultsGenerator_PCPBioFireFilm> resultsGenerator_pcpBioFireFilmList = new ArrayList<>();

    private List<ResultsGenerator_Aquios1> resultsGenerator_aquios1s = new ArrayList<>();

    public List<ResultsGenerator_PCP> getResultsGeneratorPcps() {
        return resultsGeneratorPcps;
    }

    private List<ResultsGenerator_PCP> resultsGeneratorPcps = new ArrayList<>();


    private List<ResultsGenerator_RocheSysmexXGE> resultsGenerator_rocheSysmexXGES = new ArrayList<>();

    private List<ResultsGenerator_AAGeorge> resultsGeneratorAaGeorges = new ArrayList<>();
    private List<ResultsGenerator_Abbott> resultsGenerator_Abbott = new ArrayList<>();


    private List<ResultsGenerator_Sysmexca620Geo> resultsGenerator_sysmexca620Geos = new ArrayList<>();

    private ArrayList<TestSetDetailsEntity> testSetDetailsEntityList = new ArrayList<>();

    private ArrayList<SpecimensEntity> specimensEntityArrayList = new ArrayList<>();


    private ArrayList<EditTestSetEntity> editTestArrayList = new ArrayList<>();

    private ArrayList<SpecimenReceiveEntity> specimenReceiveEntityArrayList = new ArrayList<>();
    private ArrayList<TransferEntity> transferEntityArrayList = new ArrayList<>();

    public ArrayList<WorksheetControlEntity> getWorksheetControlEntitArrayList() {
        return worksheetControlEntitArrayList;
    }

    public void setWorksheetControlEntitArrayList(ArrayList<WorksheetControlEntity> worksheetControlEntitArrayList) {
        this.worksheetControlEntitArrayList = worksheetControlEntitArrayList;
    }

    public ArrayList<ProcedureEntity> getProcedureEntityArrayList() {
        return procedureEntityArrayList;
    }

    public void setProcedureEntityArrayList(ArrayList<ProcedureEntity> procedureEntityArrayList) {
        this.procedureEntityArrayList = procedureEntityArrayList;
    }

    public ArrayList<WRE_AttachmentsEntity> getWre_AttachmentsEntityArrayList() {
        return wre_AttachmentsEntityArrayList;
    }

    public void setWre_AttachmentsEntityArrayList(ArrayList<WRE_AttachmentsEntity> wre_AttachmentsEntityArrayList) {
        this.wre_AttachmentsEntityArrayList = wre_AttachmentsEntityArrayList;
    }

    private ArrayList<WorksheetControlEntity> worksheetControlEntitArrayList = new ArrayList<>();
    private ArrayList<ProcedureEntity> procedureEntityArrayList = new ArrayList<>();
    private ArrayList<WRE_AttachmentsEntity> wre_AttachmentsEntityArrayList = new ArrayList<>();

    public ArrayList<LogisticsDropOffEntity> getLogisticsDropOffEntitiesList() {
        return logisticsDropOffEntitiesList;
    }

    public void setLogisticsDropOffEntitiesList(ArrayList<LogisticsDropOffEntity> logisticsDropOffEntitiesList) {
        this.logisticsDropOffEntitiesList = logisticsDropOffEntitiesList;
    }

    private ArrayList<LogisticsDropOffEntity> logisticsDropOffEntitiesList = new ArrayList<>();

    public ArrayList<LogisticsEntity> getLogisticsEntityArrayList() {
        return logisticsEntityArrayList;
    }

    public void setLogisticsEntityArrayList(ArrayList<LogisticsEntity> logisticsEntityArrayList) {
        this.logisticsEntityArrayList = logisticsEntityArrayList;
    }

    private ArrayList<LogisticsEntity> logisticsEntityArrayList = new ArrayList<>();
    private List<List> ResultGene = new ArrayList<>();


    private String fileLocation ="src/main/resources/TestCaseResources.txt";
    private List<TestSetCodeEntity> testSetCodeEntity = new ArrayList<>();

    private String shipment = "";

    ArrayList<String> lapEpsiode = new ArrayList<>();

    private ArrayList<ResultsEntry> resultsEntries = new ArrayList<>();

    private ArrayList<ResultsEntryVerify> resultsEntryVerifies = new ArrayList<>();

    private ArrayList<TestSetValuesEntity> testSetValuesEntityList = new ArrayList<>();

    //LabQueue
    private  ArrayList<LabQueueEntity> labQueueEntities = new ArrayList<>();

    private ArrayList<LabQueueValuesEntity> labQueueValuesEntity = new ArrayList<>();

    //WorkSheet
    private ArrayList<WorkSheetResultEntry> workSheetResultEntryArrayList = new ArrayList<>();


    private ArrayList<WorkSheetResultValues> workSheetResultValuesArrayList = new ArrayList<>();

    public ArrayList<TestSetValuesEntity> getTestSetValuesList() {
        return testSetValuesEntityList;
    }

    public void setTestSetValuesList(ArrayList<TestSetValuesEntity> testSetValuesEntityList) {
        this.testSetValuesEntityList = testSetValuesEntityList;
    }

    public ArrayList<SpecimenReceiveEntity> getSpecimenReceiveArrayList() {
        return specimenReceiveEntityArrayList;
    }

    public void setSpecimenReceiveArrayList(ArrayList<SpecimenReceiveEntity> specimenReceiveEntityArrayList) {
        this.specimenReceiveEntityArrayList = specimenReceiveEntityArrayList;
    }

    public List<TestSetCodeEntity> setTestCodeList(List<TestSetCodeEntity> testSetCodeEntities) {
        return this.testSetCodeEntity = testSetCodeEntities;
    }

    public List<TestSetCodeEntity> getTestCodeList() {
        return testSetCodeEntity;
    }

    public ArrayList<EditTestSetEntity> getEditTestArrayList() {
        return editTestArrayList;
    }

    public void setEditTestArrayList(ArrayList<EditTestSetEntity> editTestArrayList) {
        this.editTestArrayList = editTestArrayList;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }



    public LabespideData(){
        
    }

    public List<PatientModel> getPatientModelList() {
        return patientModelList;
    }


    public void setPatientModelList(List<PatientModel> patientModelList) {
        this.patientModelList = patientModelList;
    }

    public void setWorkReceive(List<WorkAreaReceiveEntity> workAreaReceiveEntityList){
        this.workAreaReceiveEntities = workAreaReceiveEntityList;
    }
    //Work Sheets
    public ArrayList<WorkSheetResultEntry> getWorkSheetResultEntryArrayList() {
        return workSheetResultEntryArrayList;
    }

    public void setWorkSheetResultEntryArrayList(ArrayList<WorkSheetResultEntry> workSheetResultEntryArrayList) {
        this.workSheetResultEntryArrayList = workSheetResultEntryArrayList;
    }

    public ArrayList<WorkSheetResultValues> getWorkSheetResultValuesArrayList() {
        return workSheetResultValuesArrayList;
    }

    public void setWorkSheetResultValuesArrayList(ArrayList<WorkSheetResultValues> workSheetResultValuesArrayList) {
        this.workSheetResultValuesArrayList = workSheetResultValuesArrayList;
    }
    //End WorkSheet


    //Being  Result Generator

    public void setResultsGeneratorPcps(List<ResultsGenerator_PCP> resultsGeneratorPcps){
        this.resultsGeneratorPcps = resultsGeneratorPcps;
    }


    public void setResultGenerator_AbbottAliinityc(List<ResultsGenerator_AbbottAlinityc> resultsGenerator_abbottAlinitycList){
        this.resultsGenerator_AbbottAlinityc = resultsGenerator_abbottAlinitycList;
    }

    public void setResultsGenerator_rocheSysmexXN1List(List<ResultsGenerator_RocheSysmexXN1> resultsGenerator_rocheSysmexXN1List){
        this.resultsGenerator_rocheSysmexXN1List = resultsGenerator_rocheSysmexXN1List;
    }

    public void setResultsGenerator_sysmexCS2500s(List<ResultsGenerator_SysmexCS2500> resultsGenerator_sysmexCS2500List){
        this.resultsGenerator_sysmexCS2500s = resultsGenerator_sysmexCS2500List;
    }

    public void setResultsGenerator_pcpBioFireFilmList(List<ResultsGenerator_PCPBioFireFilm> resultsGenerator_pcpBioFireFilmList){
        this.resultsGenerator_pcpBioFireFilmList = resultsGenerator_pcpBioFireFilmList;
    }

    public List<ResultsGenerator_Aquios1> getResultsGenerator_aquios1s() {
        return resultsGenerator_aquios1s;
    }
    public List<ResultsGenerator_RocheSysmexXGE> getResultsGenerator_rocheSysmexXGES() {
        return resultsGenerator_rocheSysmexXGES;
    }

    public List<ResultsGenerator_AAGeorge> getResultsGeneratorAaGeorges() {
        return resultsGeneratorAaGeorges;
    }

    public List<ResultsGenerator_Sysmexca620Geo> getResultsGenerator_sysmexca620Geos() {
        return resultsGenerator_sysmexca620Geos;
    }

    public void setResultsGenerator_aquios1s(List<ResultsGenerator_Aquios1> resultsGenerator_aquios1s) {
        this.resultsGenerator_aquios1s = resultsGenerator_aquios1s;
    }

    public void setResultsGenerator_rocheSysmexXGES(List<ResultsGenerator_RocheSysmexXGE> resultsGenerator_rocheSysmexXGES) {
        this.resultsGenerator_rocheSysmexXGES = resultsGenerator_rocheSysmexXGES;
    }


    public void setResultsGenerator_sysmexca620Geos(List<ResultsGenerator_Sysmexca620Geo> resultsGenerator_sysmexca620Geos) {
        this.resultsGenerator_sysmexca620Geos = resultsGenerator_sysmexca620Geos;
    }


    public void setResultsGeneratorAaGeorges(List<ResultsGenerator_AAGeorge> resultsGeneratorAaGeorges) {
        this.resultsGeneratorAaGeorges = resultsGeneratorAaGeorges;
    }


    public List<WorkAreaReceiveEntity> getWorkAreaReceives() {
        return workAreaReceiveEntities;
    }

    public List<ResultsGenerator_AbbottAlinityc> getResultsGenerator_AbbottAlinityc() {
        return resultsGenerator_AbbottAlinityc;
    }

    public List<ResultsGenerator_RocheSysmexXN1> getResultsGenerator_rocheSysmexXN1List() {
        return resultsGenerator_rocheSysmexXN1List;
    }

    public List<ResultsGenerator_SysmexCS2500> getResultsGenerator_sysmexCS2500s() {
        return resultsGenerator_sysmexCS2500s;
    }

    public List<ResultsGenerator_PCPBioFireFilm> getResultsGenerator_pcpBioFireFilmList() {
        return resultsGenerator_pcpBioFireFilmList;
    }
    public List<ResultsGenerator_Abbott> getResultsGenerator_Abbott() {
        return resultsGenerator_Abbott;
    }

    public ArrayList<ResultsEntry> getResultEntry(){
        return resultsEntries;
    }

    public void setResultsEntries(ArrayList<ResultsEntry> resultsEntries){
        this.resultsEntries = resultsEntries;
    }

    public List<List> getResultGene() {
        return ResultGene;
    }

    public void setResultGene(List<List> resultGene) {
        this.ResultGene = resultGene;
    }

    //End Results Generator

    //Begin Queue
    public ArrayList<LabQueueEntity> getLabQueueEntities() {
        return labQueueEntities;
    }

    public void setLabQueueEntities(ArrayList<LabQueueEntity> labQueueEntities) {
        this.labQueueEntities = labQueueEntities;
    }

    public ArrayList<LabQueueValuesEntity> getlabQueueValuesEntity() {
        return labQueueValuesEntity;
    }

    public void setlabQueueValuesEntity(ArrayList<LabQueueValuesEntity> labQueueValuesEntity) {
        this.labQueueValuesEntity = labQueueValuesEntity;
    }

    //End Queue


    public ArrayList<TestSetDetailsEntity> getTestSetDetailsList() {
        return testSetDetailsEntityList;
    }
    public ArrayList<String> getLapEpsiode() {
        return lapEpsiode;
    }

    public void setLapEpsiode(ArrayList<String> lapEpsiode) {
        this.lapEpsiode = lapEpsiode;
    }


    public void setTestSetDetailsList(ArrayList<TestSetDetailsEntity> testSetDetailsEntityList) {
        this.testSetDetailsEntityList = testSetDetailsEntityList;
    }

    public ArrayList<SpecimensEntity> getSpecimensArrayList() {
        return specimensEntityArrayList;
    }

    public void setSpecimensArrayList(ArrayList<SpecimensEntity> specimensEntityArrayList) {
        this.specimensEntityArrayList = specimensEntityArrayList;
    }
    public ArrayList<TransferEntity> getTransferArrayList() {
        return transferEntityArrayList;
    }
    public void setTransferArrayList(ArrayList<TransferEntity> transferEntityArrayList) {
        this.transferEntityArrayList = transferEntityArrayList;
    }

    //Read
    //Structure the content/data
    public ArrayList<String> readerList() throws FileNotFoundException {

        ArrayList<String> value = new ArrayList<>();

        File file = new File(fileLocation);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
           String vsalue = scan.nextLine().trim();
           if(!vsalue.isBlank()) {
               value.add(vsalue);
           }
        }
        scan.close();
        return value;

    }

    public ArrayList<WorkAreaReceiveEntity> readWorkReceivList() throws FileNotFoundException {

        ArrayList<WorkAreaReceiveEntity> values = new ArrayList<>();

        File file = new File("src/main/resources/WorkRecieve.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String vsalue = scan.nextLine().trim();
            if(!vsalue.isBlank()) {
            WorkAreaReceiveEntity workAreaReceiveEntity = new WorkAreaReceiveEntity(
                    vsalue.split(",")[0]==null ? "":vsalue.split(",")[0],
                    vsalue.split(",")[1]==null ?"": vsalue.split(",")[1],
                    vsalue.split(",")[2]==null ?"":vsalue.split(",")[2],
                    vsalue.split(",")[3]==null ?"":vsalue.split(",")[3],
                    vsalue.split(",")[4]==null ?"":vsalue.split(",")[4],
                    vsalue.split(",")[5] == null ? "" : vsalue.split(",")[5]);
               values.add(workAreaReceiveEntity);


            }
        }
        scan.close();
        return values;

    }

    public ArrayList<SpecimenReceiveEntity> readSpecimenReceivList() throws FileNotFoundException {

        ArrayList<SpecimenReceiveEntity> values = new ArrayList<>();

        File file = new File("src/main/resources/SpecimenRecieve.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String vsalue = scan.nextLine().trim();
            if(!vsalue.isBlank()) {
                SpecimenReceiveEntity specimenReceiveEntity = new SpecimenReceiveEntity(
                        vsalue.split(",")[0]==null ? "":vsalue.split(",")[0],
                        vsalue.split(",")[1]==null ?"": vsalue.split(",")[1],
                        vsalue.split(",")[2]==null ?"":vsalue.split(",")[2],
                        vsalue.split(",")[3] == null ? "" : vsalue.replace("]","").replace("[","").split(",")[3]);
                values.add(specimenReceiveEntity);


            }
        }
        scan.close();
        return values;

    }

    public ArrayList<SpecimenReceiveEntity> readSpecimenReceivListWithOutTestSet() throws FileNotFoundException {

        ArrayList<SpecimenReceiveEntity> values = new ArrayList<>();

        File file = new File("src/main/resources/ReferenceRangesResources.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String vsalue = scan.nextLine().trim().replace("{","").replace("}","");
            if(!vsalue.isBlank()) {
                SpecimenReceiveEntity specimenReceiveEntity = new SpecimenReceiveEntity(
                        vsalue.split(",")[0]==null ? "":vsalue.split(",")[0],
                        vsalue.split(",")[1]==null ?"": vsalue.split(",")[1],
                        vsalue.split(",")[2]==null ?"":vsalue.split(",")[2]);
                values.add(specimenReceiveEntity);


            }
        }
        scan.close();
        return values;

    }



    public void clear() throws IOException {

        File file = new File(fileLocation);
        FileWriter fr = new FileWriter(file ,true );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write("");
        br.flush();
        br.close();
        fr.close();
    }

    public void write(String values) throws IOException {

        File file = new File(fileLocation);

        FileWriter fr = new FileWriter(file ,true );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write("");
        br.flush();
        br.write(values+System.getProperty("line.separator"));
        br.close();
        fr.close();
    }
    public void write(ArrayList<String> values) throws IOException {

        File file = new File(fileLocation);

        FileWriter fr = new FileWriter(file ,false );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write("");
        br.flush();
        for (String labEpisodeNumber : values)
        {
            br.write(labEpisodeNumber + "\n");
            System.out.println(labEpisodeNumber);
        }
       // br.write(values+System.getProperty("line.separator"));
        br.close();
        fr.close();
    }
    public void write(String values,String fileName) throws IOException {

        File file = new File("src/main/resources/"+fileName);

        FileWriter fr = new FileWriter(file ,true );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write("");
        br.flush();
        br.write(values+System.getProperty("line.separator"));
        br.close();
        fr.close();
    }




    public void patientInform() {

        for(SpecimenReceiveEntity specimenReceiveEntity :specimenReceives())
        {
            for(TestSetCodeEntity testSetCodeEntity :testSetCode()){
                if(specimenReceiveEntity.getAliquotTestSet() != null) {
                    if (specimenReceiveEntity.getAliquotTestSet().contentEquals(testSetCodeEntity.PK_testCode)) {
                        specimenReceiveEntity.setAliquotTestSet(testSetCodeEntity.getTestSet().trim());
                    }
                }
            }

            for (UserProfileEntity userProfileEntity : userProfile()) {
                if (userProfileEntity.getPK().contentEquals(specimenReceiveEntity.getUserprofile_FK())) {
                    specimenReceiveEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                }
            }
            Collections.sort(specimenReceiveEntity.getTestSet());
            specimenReceiveEntityArrayList.add(specimenReceiveEntity);
        }

        for(EditTestSetEntity editTestSetEntity :editTestSets())
        {
            for(TestSetCodeEntity testSetCodeEntity :testSetCode()){
                if (editTestSetEntity.testcode.contentEquals(testSetCodeEntity.PK_testCode)) {
                    editTestSetEntity.setTestcode(testSetCodeEntity.getCode().trim());
                }
            }
            editTestArrayList.add(editTestSetEntity);
        }

        for (PatientModel patientModel:patientData()) {
            for (UserProfileEntity userProfileEntity : userProfile()) {
                if (userProfileEntity.getPK().contentEquals(patientModel.userprofile)) {
                    patientModel.setUserprofile(userProfileEntity.getAccessProfile());
                }
            }
            List<String> tests = new ArrayList<>();
            for(String test :patientModel.testSet ){
                for(TestSetCodeEntity testSetCodeEntity : testSetCode()) {
                    if (test.contentEquals(testSetCodeEntity.PK_testCode)) {
                        tests.add(testSetCodeEntity.getCode());
                    }
                }
            }
            patientModel.testSet =tests;

            patientModelList.add(patientModel);
            }

        for(TestSetDetailsEntity testSetDetailsEntity :testSetDetails()){
            for(TestSetCodeEntity testSetCodeEntity : testSetCode()){
                if(testSetDetailsEntity.getTestSetSuperSet() != null) {
                    if (testSetCodeEntity.getPK_testCode().contentEquals(testSetDetailsEntity.getTestSetSuperSet())) {
                        testSetDetailsEntity.setTestSetSuperSet(testSetCodeEntity.getCode());
                    }
                }
            }
            testSetDetailsEntityList.add(testSetDetailsEntity);
        }

            for(SpecimensEntity specimensEntity :specimenDetails()){
               if(specimensEntity.getTestCode() !=null){
                   for(TestSetCodeEntity testSetCodeEntity :testSetCode()){
                       if (specimensEntity.getTestCode().contentEquals(testSetCodeEntity.PK_testCode)) {
                           specimensEntity.setTestCode(testSetCodeEntity.getCode().trim());
                       }
                   }
               }
               specimensEntityArrayList.add(specimensEntity);
            }

            //Work Area Receive
            for(WorkAreaReceiveEntity workAreaReceiveEntity :workAreaReceives())
            {
                if(workAreaReceiveEntity.getUserprofile_FK() !=null) {
                    for (UserProfileEntity userProfileEntity : userProfile()) {
                        if (workAreaReceiveEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                            workAreaReceiveEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                        }
                    }
                }

                Collections.sort(workAreaReceiveEntity.getTestSet());
                this.workAreaReceiveEntities.add(workAreaReceiveEntity);
            }

        //Result Generator
            for(ResultsGenerator_AbbottAlinityc resultsGenerator_abbottAlinityc: resultsGenerator_abbottAlinitycs()){

                if(resultsGenerator_abbottAlinityc.getUserprofile() !=null){
                    for(UserProfileEntity userProfileEntity :userProfile()){
                        if (resultsGenerator_abbottAlinityc.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                            resultsGenerator_abbottAlinityc.setUserprofile(userProfileEntity.getAccessProfile());
                        }
                    }
                    this.resultsGenerator_AbbottAlinityc.add(resultsGenerator_abbottAlinityc);
                }

            }

        for(ResultsGenerator_RocheSysmexXN1 resultsGenerator_rocheSysmexXN1: resultsGeneratorRocheSysmexXN1s()) {

            if (resultsGenerator_rocheSysmexXN1.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_rocheSysmexXN1.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_rocheSysmexXN1.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_rocheSysmexXN1List.add(resultsGenerator_rocheSysmexXN1);
            }


        }

            for(ResultsGenerator_SysmexCS2500 resultsGenerator_sysmexCS2500: ExcelExtractorList.resultsGenerator_sysmexCS2500s()) {

                if (resultsGenerator_sysmexCS2500.getUser_Profile() != null) {
                    for (UserProfileEntity userProfileEntity : userProfile()) {
                        if (resultsGenerator_sysmexCS2500.getUser_Profile().contentEquals(userProfileEntity.getPK())) {
                            resultsGenerator_sysmexCS2500.setUser_Profile(userProfileEntity.getAccessProfile());
                        }
                    }
                    this.resultsGenerator_sysmexCS2500s.add(resultsGenerator_sysmexCS2500);
                }

            }

        for(ResultsGenerator_PCPBioFireFilm resultsGenerator_pcpBioFireFilm: ExcelExtractorList.resultsGenerator_pcpBioFireFilms()) {

            if (resultsGenerator_pcpBioFireFilm.getUser_Profile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_pcpBioFireFilm.getUser_Profile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_pcpBioFireFilm.setUser_Profile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_pcpBioFireFilmList.add(resultsGenerator_pcpBioFireFilm);
            }

        }

        for(ResultsGenerator_Aquios1 resultsGenerator_Aquios1: ExcelExtractorList.resultsGenerator_Aquios1()) {

            if (resultsGenerator_Aquios1.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_Aquios1.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_Aquios1.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_aquios1s.add(resultsGenerator_Aquios1);
            }

        }

        for(ResultsGenerator_RocheSysmexXGE resultsGenerator_rocheSysmexXGE: ExcelExtractorList.resultsGenerator_RocheSysmexXGE()) {

            if (resultsGenerator_rocheSysmexXGE.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_rocheSysmexXGE.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_rocheSysmexXGE.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_rocheSysmexXGES.add(resultsGenerator_rocheSysmexXGE);
            }

        }

        for(ResultsGenerator_Sysmexca620Geo resultsGenerator_sysmexca620Geo: ExcelExtractorList.resultsGenerator_Sysmexca620Geo()) {

            if (resultsGenerator_sysmexca620Geo.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_sysmexca620Geo.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_sysmexca620Geo.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_sysmexca620Geos.add(resultsGenerator_sysmexca620Geo);
            }

        }

        for(ResultsGenerator_AAGeorge resultsGenerator_aaGeorge: ExcelExtractorList.resultsGenerator_AAGeorge()) {

            if (resultsGenerator_aaGeorge.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_aaGeorge.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_aaGeorge.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGeneratorAaGeorges.add(resultsGenerator_aaGeorge);
            }

        }

        for(ResultsGenerator_Abbott resultsGenerator_abbott : ExcelExtractorList.resultsGenerator_Abbott()) {

            if (resultsGenerator_abbott.getUserprofile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_abbott.getUserprofile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_abbott.setUserprofile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGenerator_Abbott.add(resultsGenerator_abbott);
            }

        }

        for(ResultsGenerator_PCP resultsGenerator_pcp : ExcelExtractorList.resultsGenerator_PCP()) {

            if (resultsGenerator_pcp.getUser_Profile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsGenerator_pcp.getUser_Profile().contentEquals(userProfileEntity.getPK())) {
                        resultsGenerator_pcp.setUser_Profile(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsGeneratorPcps.add(resultsGenerator_pcp);
            }

        }
        //End of Result Generator

        //Lab Queue
        for(LabQueueEntity labQueueEntity:ExcelExtractorList.labQueues()) {

            if (labQueueEntity.getUserProfile() != null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (labQueueEntity.getUserProfile().contentEquals(userProfileEntity.getPK())) {
                        labQueueEntity.setUserProfile(userProfileEntity.getAccessProfile().trim());
                    }
                }
            }

                for(TestSetCodeEntity testSetCodeEntity : testSetCode()) {
                    if (labQueueEntity.testSet.contentEquals(testSetCodeEntity.PK_testCode)) {
                        labQueueEntity.setTestSet(testSetCodeEntity.getCode().trim());
                    }
                }

            this.labQueueEntities.add(labQueueEntity);
        }

        for(LabQueueValuesEntity labQueueValuesEntity:ExcelExtractorList.labQueueValues()) {

            this.labQueueValuesEntity.add(labQueueValuesEntity);
        }
        //End of Lab Queue


        //Lab Result Entry
        for(ResultsEntry resultsEntry:ExcelExtractorList.resultsEntries()) {


                for(TestSetCodeEntity testSetCodeEntity : testSetCode()) {
                    if (resultsEntry.getTestSet().contentEquals(testSetCodeEntity.PK_testCode)) {
                        resultsEntry.setTestSet(testSetCodeEntity.getTestSet());
                    }

            }
                this.resultsEntries.add(resultsEntry);
        }

        for (TestSetValuesEntity testSetValue:ExcelExtractorList.testSetValues()){

            if (testSetValue.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (testSetValue.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        testSetValue.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }

                }
            }

            this.testSetValuesEntityList.add(testSetValue);
        }

        for(ResultsEntryVerify resultsEntryVerifyEntries:ExcelExtractorList.resultsEntryVerifyEntries()) {


            if (resultsEntryVerifyEntries.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (resultsEntryVerifyEntries.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        resultsEntryVerifyEntries.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.resultsEntryVerifies.add(resultsEntryVerifyEntries);
            }


        }
       //Lab Transfer
        for (TransferEntity transferEntity:ExcelExtractorList.labTransfer()){

            if (transferEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (transferEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        transferEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.transferEntityArrayList.add(transferEntity);
            }
        }
        //TransferLogisticsPathCareParkRe Logistics dropOff
        for (LogisticsDropOffEntity logisticsDropOffEntity:ExcelExtractorList.labTransferLogisticsDropOff()){

            if (logisticsDropOffEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (logisticsDropOffEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        logisticsDropOffEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.logisticsDropOffEntitiesList.add(logisticsDropOffEntity);
            }
        }
        //Lab Transfer Logistics
        for (LogisticsEntity logisticsEntity:ExcelExtractorList.labTransferLogistics()){

            if (logisticsEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (logisticsEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        logisticsEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.logisticsEntityArrayList.add(logisticsEntity);
            }
        }
        //Work Sheet
        for (WorkSheetResultEntry workSheetResultEntry:ExcelExtractorList.workSheetResultEntries()){

            if (workSheetResultEntry.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (workSheetResultEntry.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        workSheetResultEntry.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.workSheetResultEntryArrayList.add(workSheetResultEntry);
            }
        }
        //Work Sheet Control
        for (WorksheetControlEntity worksheetControlEntity:ExcelExtractorList.worksheetControlEntities()){

            if (worksheetControlEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (worksheetControlEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        worksheetControlEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.worksheetControlEntitArrayList.add(worksheetControlEntity);
            }
        }
        //Procedure
        for (ProcedureEntity procedureEntity:ExcelExtractorList.procedureEntities()){

            if (procedureEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (procedureEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        procedureEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.procedureEntityArrayList.add(procedureEntity);
            }
        }
        //WRE Attachment
        for (WRE_AttachmentsEntity wre_attachmentsEntity:ExcelExtractorList.wre_attachmentsEntities()){

            if (wre_attachmentsEntity.getUserprofile_FK()!= null) {
                for (UserProfileEntity userProfileEntity : userProfile()) {
                    if (wre_attachmentsEntity.getUserprofile_FK().contentEquals(userProfileEntity.getPK())) {
                        wre_attachmentsEntity.setUserprofile_FK(userProfileEntity.getAccessProfile());
                    }
                }
                this.wre_AttachmentsEntityArrayList.add(wre_attachmentsEntity);
            }
        }
        for (WorkSheetResultValues workSheetResultValues :ExcelExtractorList.workSheetResultValues()){

                this.workSheetResultValuesArrayList.add(workSheetResultValues);

        }


        //WorkSheet End
            setResultsEntryVerifies(resultsEntryVerifies);
            setPatientModelList(patientModelList);
            setTestCodeList(testSetCode());
            setTestSetDetailsList(testSetDetailsEntityList);
            setSpecimensArrayList(specimensEntityArrayList);
            setEditTestArrayList(editTestArrayList);
            setSpecimenReceiveArrayList(specimenReceiveEntityArrayList);
            setWorkReceive(workAreaReceiveEntities);
            setWorkSheetResultEntryArrayList(workSheetResultEntryArrayList);
            setWorkSheetResultValuesArrayList(workSheetResultValuesArrayList);
            setResultGenerator_AbbottAliinityc(resultsGenerator_AbbottAlinityc);
            setResultsGenerator_rocheSysmexXN1List(resultsGenerator_rocheSysmexXN1List);
            setResultsGenerator_pcpBioFireFilmList(resultsGenerator_pcpBioFireFilmList);
            setResultsGenerator_sysmexCS2500s(resultsGenerator_sysmexCS2500s);
            setResultsGenerator_aquios1s(resultsGenerator_aquios1s);
            setResultsGenerator_rocheSysmexXGES(resultsGenerator_rocheSysmexXGES);
            setResultsGenerator_sysmexca620Geos(resultsGenerator_sysmexca620Geos);
            setResultsGeneratorAaGeorges(resultsGeneratorAaGeorges);
            setResultsGeneratorPcps(resultsGeneratorPcps);

            List<List> resultGenerator = new ArrayList<>();
            resultGenerator.add(resultsGenerator_AbbottAlinityc);
            resultGenerator.add(resultsGenerator_sysmexCS2500s);
            resultGenerator.add(resultsGenerator_rocheSysmexXN1List);
            resultGenerator.add(resultsGenerator_pcpBioFireFilmList);
            resultGenerator.add(resultsGeneratorAaGeorges);
            resultGenerator.add(resultsGenerator_aquios1s);
            resultGenerator.add(resultsGenerator_rocheSysmexXGES);
            resultGenerator.add(resultsGenerator_sysmexca620Geos);
            resultGenerator.add(resultsGenerator_Abbott);
            resultGenerator.add(resultsGeneratorPcps);
            setResultGene(resultGenerator);

            setResultsEntries(resultsEntries);
            setTestSetValuesList(testSetValuesEntityList);
            setLabQueueEntities(labQueueEntities);
            setlabQueueValuesEntity(labQueueValuesEntity);

    }


    public ArrayList<ResultsEntryVerify> getResultsEntryVerifies() {
        return resultsEntryVerifies;
    }

    public void setResultsEntryVerifies(ArrayList<ResultsEntryVerify> resultsEntryVerifies) {
        this.resultsEntryVerifies = resultsEntryVerifies;
    }
}

