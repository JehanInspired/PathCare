package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;

public class SpecimensEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    private String patient_Key ;
    @ExcelCell(1)
    private String testCode;

    @ExcelCell(2)
    private String specimens;
    public String getPatient_Key() {
        return patient_Key;
    }

    public void setPatient_Key(String patient_Key) {
        this.patient_Key = patient_Key;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getSpecimens() {
        return specimens;
    }

    public void setSpecimens(String specimens) {
        this.specimens = specimens;
    }
}
