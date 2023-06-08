package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class TestSetCodeEntity {

    @ExcelRow
    private int rowIndex;

    //@ExcelCell(0)
    @ExcelCellName("PK_TestCode")
    public String PK_testCode ;

    public String getPK_testCode() {
        return PK_testCode;
    }

    public void setPK_testCode(String PK_testCode) {
        this.PK_testCode = PK_testCode;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ExcelCell(1)
    private String testSet ;


    @ExcelCellName("CODE")
    private String code;


}
