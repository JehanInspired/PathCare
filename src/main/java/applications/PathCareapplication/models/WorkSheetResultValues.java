package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;
import java.util.Map;

public class WorkSheetResultValues {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("WorkSheetResultValuePK")
    private String workSheetResultValuePK;

    @ExcelCellName("WorkSheetResult Key_FK")
    private String workSheetResultKey_FK;

    @ExcelCellName("QC")
    private String qc;

    @ExcelCellName("TestSet")
    private String testSet;

    @ExcelCellName("TestSetValue_FK")
    private String TestSetValue_FK;

    @ExcelUnknownCells
    private Map<String,String> testvalues = new HashMap<>();

    public String getWorkSheetResultValuePK() {
        return workSheetResultValuePK;
    }

    public void setWorkSheetResultValuePK(String workSheetResultValuePK) {
        this.workSheetResultValuePK = workSheetResultValuePK;
    }

    public String getWorkSheetResultKey_FK() {
        return workSheetResultKey_FK;
    }

    public void setWorkSheetResultKey_FK(String workSheetResultKey_FK) {
        this.workSheetResultKey_FK = workSheetResultKey_FK;
    }

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
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

    public Map<String, String> getTestvalues() {
        return testvalues;
    }

    public void setTestvalues(Map<String, String> testvalues) {
        this.testvalues = testvalues;
    }
}
