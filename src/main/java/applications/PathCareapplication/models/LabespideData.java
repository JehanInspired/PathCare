package applications.PathCareapplication.models;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LabespideData {

   private  String testSet = "";

   private String lapepideNumber ="";

    private List<PatientModel> patientModelList = new ArrayList<>();


    private ArrayList<TestSetDetails> testSetDetailsList = new ArrayList();

    private ArrayList<Specimens> specimensArrayList = new ArrayList<>();


    private ArrayList<EditTestSet> editTestArrayList = new ArrayList<>();

    private ArrayList<SpecimenReceive> specimenReceiveArrayList = new ArrayList<>();


    private String fileLocation ="src/main/resources/TestCaseResources.txt";

    public ArrayList<SpecimenReceive> getSpecimenReceiveArrayList() {
        return specimenReceiveArrayList;
    }

    public void setSpecimenReceiveArrayList(ArrayList<SpecimenReceive> specimenReceiveArrayList) {
        this.specimenReceiveArrayList = specimenReceiveArrayList;
    }

    public ArrayList<EditTestSet> getEditTestArrayList() {
        return editTestArrayList;
    }

    public void setEditTestArrayList(ArrayList<EditTestSet> editTestArrayList) {
        this.editTestArrayList = editTestArrayList;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    private String shipment = "";

    ArrayList<String> lapEpsiode = new ArrayList<>();

    public LabespideData(String lapepideNumber){
        this.lapepideNumber =lapepideNumber;
    }
    public LabespideData(){
        
    }

    public List<PatientModel> getPatientModelList() {
        return patientModelList;
    }


    public void setPatientModelList(List<PatientModel> patientModelList) {
        this.patientModelList = patientModelList;
    }

    public ArrayList<TestSetDetails> getTestSetDetailsList() {
        return testSetDetailsList;
    }
    public ArrayList<String> getLapEpsiode() {
        return lapEpsiode;
    }

    public void setLapEpsiode(ArrayList<String> lapEpsiode) {
        this.lapEpsiode = lapEpsiode;
    }


    public void setTestSetDetailsList(ArrayList<TestSetDetails> testSetDetailsList) {
        this.testSetDetailsList = testSetDetailsList;
    }

    public ArrayList<Specimens> getSpecimensArrayList() {
        return specimensArrayList;
    }

    public void setSpecimensArrayList(ArrayList<Specimens> specimensArrayList) {
        this.specimensArrayList = specimensArrayList;
    }


    public void setTestset(String testSet) {
        this.testSet = testSet;
    }


    //Create file readfile
    //Read and write
    //Structure the content/data
    public ArrayList<String> readerList() throws FileNotFoundException {

        ArrayList<String> value = new ArrayList<>();

        File file = new File(fileLocation);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
           String vsalue = scan.nextLine();
            value.add(vsalue);
        }
        scan.close();
        return value;

    }

    public void removealllabespisode(ArrayList<String> result) throws FileNotFoundException {
        File file = new File(fileLocation);
         Scanner scan = new Scanner(file);
        PrintWriter writer = new PrintWriter(file); //destinationfile
        while (scan.hasNext()) {
            String line = scan.nextLine();
            for(String value:result) {
                if (!line.contains(value)) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
        }
        scan.close();
        writer.close();
    }

    public void write(String values) throws IOException {
        File file = new File(fileLocation);
        FileWriter fr = new FileWriter(file ,true );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write(values+System.getProperty("line.separator"));
        br.close();
        fr.close();
    }

    public void patientInform(){

        ;

        for(SpecimenReceive specimenReceive:specimenReceives())
        {
            for(TestSetCode testSetCode :testSetCode()){
                if(specimenReceive.getAliquotTestSet() != null) {
                    if (specimenReceive.getAliquotTestSet().contentEquals(testSetCode.PK_testCode)) {
                        specimenReceive.setAliquotTestSet(testSetCode.getCode().trim());
                    }
                }
            }
            for (UserProfile userProfile : userProfile()) {
                if (userProfile.getPK().contentEquals(specimenReceive.getUserprofile_FK())) {
                    specimenReceive.setUserprofile_FK(userProfile.getAccessProfile()); ;
                }
            }
            specimenReceiveArrayList.add(specimenReceive);
        }

        for(EditTestSet editTestSet:editTestSets())
        {
            for(TestSetCode testSetCode :testSetCode()){
                if (editTestSet.testcode.contentEquals(testSetCode.PK_testCode)) {
                    editTestSet.setTestcode(testSetCode.getCode().trim());
                }
            }
            editTestArrayList.add(editTestSet);
        }

        for (PatientModel patientModel:patientData()) {
            for (UserProfile userProfile : userProfile()) {
                if (userProfile.getPK().contentEquals(patientModel.userprofile)) {
                    patientModel.userprofile = userProfile.getAccessProfile();
                }
            }
            List<String> tests = new ArrayList<>();
            for(String test :patientModel.testSet ){
                for(TestSetCode testSetCode: testSetCode()) {
                    if (test.contentEquals(testSetCode.PK_testCode)) {
                        tests.add(testSetCode.getCode());
                    }
                }
            }
            patientModel.testSet =tests;

            patientModelList.add(patientModel);
            }

        for(TestSetDetails testSetDetails:testSetDetails()){
            for(TestSetCode testSetCode: testSetCode()){
                if(testSetDetails.getTestSetSuperSet() != null) {
                    if (testSetCode.getPK_testCode().contentEquals(testSetDetails.getTestSetSuperSet())) {
                        testSetDetails.setTestSetSuperSet(testSetCode.getCode());
                    }
                }
            }
            testSetDetailsList.add(testSetDetails);
        }

            for(Specimens specimens:specimenDetails()){
               if(specimens.getTestCode() !=null){
                   for(TestSetCode testSetCode :testSetCode()){
                       if (specimens.getTestCode().contentEquals(testSetCode.PK_testCode)) {
                           specimens.setTestCode(testSetCode.getCode().trim());
                       }
                   }
               }
               specimensArrayList.add(specimens);
            }




            setPatientModelList(patientModelList);

            setTestSetDetailsList(testSetDetailsList);

            setSpecimensArrayList(specimensArrayList);
            setEditTestArrayList(editTestArrayList);

    }


    public List<PatientModel> patientData(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("PatientInfo").addListDelimiter(",")
                .build();
         return Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), PatientModel.class, options);

    }

    private List<TestSetCode> testSetCode(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetCode").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), TestSetCode.class, options);
    }

    private List<UserProfile> userProfile(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("UserProfile").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), UserProfile.class, options);
    }

    private  List<TestSetDetails> testSetDetails(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetDetails").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"),TestSetDetails.class, options);
    }
    private List<Specimens> specimenDetails(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Specimens").addListDelimiter(",")
                .build();
     return  Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), Specimens.class, options);

    }
    private List<EditTestSet> editTestSets(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("EditTestSet").addListDelimiter(",")
                .build();
        return  Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), EditTestSet.class, options);

    }

    private List<SpecimenReceive> specimenReceives(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(3).sheetName("Specimen receive").addListDelimiter(",")
                .build();
        return  Poiji.fromExcel(new File("src/main/resources/Lab Episodes Registrations.xlsx"), SpecimenReceive.class, options);

    }

}
