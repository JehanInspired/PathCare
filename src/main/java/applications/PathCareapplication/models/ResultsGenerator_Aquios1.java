package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;


public class ResultsGenerator_Aquios1 {
    @ExcelRow
    private int rowIndex;

    @ExcelCellName("LabResult Key")
    String LabResult_Key ;

    @ExcelCellName("User Profile")
    String userprofile ;

    @ExcelCellName("Patient Key")
    String patient_Key ;

    @ExcelCellName("Instrument")
    String instrument ;

    @ExcelCellName("Test Group")
    String Test_Group ;

    @ExcelCellName("specimen receive_FK")
    String specimen_receive_FK ;

    @ExcelUnknownCells
    private HashMap<String, String> stringValueMap = new HashMap<>();

    public String getLabResult_Key() {
        return LabResult_Key;
    }

    public void setLabResult_Key(String labResult_Key) {
        LabResult_Key = labResult_Key;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getPatient_Key() {
        return patient_Key;
    }

    public void setPatient_Key(String patient_Key) {
        this.patient_Key = patient_Key;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getTest_Group() {
        return Test_Group;
    }

    public void setTest_Group(String test_Group) {
        Test_Group = test_Group;
    }

    public String getSpecimen_receive_FK() {
        return specimen_receive_FK;
    }

    public void setSpecimen_receive_FK(String specimen_receive_FK) {
        this.specimen_receive_FK = specimen_receive_FK;
    }


    public HashMap<String,String> getValues(){
        return this.stringValueMap;
    }


}
