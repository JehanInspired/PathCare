package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;


public class ResultsGenerator_PCPBioFireFilm {

    @ExcelCellName("LabResult Key")
    String labResult_Key ;

    @ExcelCellName("User Profile")
    String user_Profile ;

    @ExcelCellName("Patient Key")
    String patient_Key ;

    @ExcelCellName("Instrument")
    String instrument ;


    @ExcelCellName("Test Group")
    String testGroup ;

    @ExcelCellName("specimen receive_FK")
    String specimenReceive_FK ;

    @ExcelUnknownCells
    private HashMap<String, String> stringValueMap = new HashMap<>();

    public HashMap<String,String> getValues(){
        return stringValueMap;
    }

    public String getLabResult_Key() {
        return labResult_Key;
    }

    public void setLabResult_Key(String labResult_Key) {
        this.labResult_Key = labResult_Key;
    }

    public String getUser_Profile() {
        return user_Profile;
    }

    public void setUser_Profile(String user_Profile) {
        this.user_Profile = user_Profile;
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

    public String getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(String testGroup) {
        this.testGroup = testGroup;
    }

    public String getSpecimenReceive_FK() {
        return specimenReceive_FK;
    }

    public void setSpecimenReceive_FK(String specimenReceive_FK) {
        this.specimenReceive_FK = specimenReceive_FK;
    }

}
