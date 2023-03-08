package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class ResultsEntry {
    @ExcelRow
    private int rowIndex;

    @ExcelCellName("ResultEntryPK")
    private String resultEntryPK;

    @ExcelCellName("Patient_FK")
    private String patient_FK;


    @ExcelCellName("Specimen receive _FK")
    private String SpecimenReceive_FK;

    @ExcelCellName("TestSet")
    private String testSet;

    @ExcelCellName("TestSetValue_FK")
    private String TestSetValue_FK;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getResultEntryPK() {
        return resultEntryPK;
    }

    public void setResultEntryPK(String resultEntryPK) {
        this.resultEntryPK = resultEntryPK;
    }

    public String getPatient_FK() {
        return patient_FK;
    }

    public void setPatient_FK(String patient_FK) {
        this.patient_FK = patient_FK;
    }

    public String getSpecimenReceive_FK() {
        return SpecimenReceive_FK;
    }

    public void setSpecimenReceive_FK(String specimenReceive_FK) {
        SpecimenReceive_FK = specimenReceive_FK;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String getTestSetValue_FK() {
        return TestSetValue_FK;
    }

    public void setTestSetValue_FK(String testSetValue_FK) {
        TestSetValue_FK = testSetValue_FK;
    }
}
