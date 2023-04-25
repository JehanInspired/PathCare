package applications.PathCareapplication.models;

import com.poiji.annotation.*;

import java.util.Arrays;
import java.util.List;


public class WorkAreaReceiveEntity {

    @ExcelCell(0)
    String pk ;

    @ExcelCell(1)
    @ExcelCellName("User Profile")
    String userprofile ;

    @ExcelCell(2)
    @ExcelCellName("specimen number")
    String specimeNumber;

    @ExcelCell(3)
    @ExcelCellName("Department")
    String department ;

    @ExcelCell(4)
    @ExcelCellName("Work Area")
    String workArea ;

    @ExcelCell(5)
    @ExcelCellName("Test Set")
    List<String> testSet ;

    public WorkAreaReceiveEntity(){

    }

    public WorkAreaReceiveEntity(String pk, String userprofile, String specimeNumber, String department, String workArea,String testSet){

            this.pk =pk;
            this.userprofile =userprofile;
            this.specimeNumber = specimeNumber;
            this.department = department;
            this.workArea = workArea;
            this.testSet = Arrays.stream(testSet.split(",")).toList();
        }


    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getSpecimeNumber() {
        return specimeNumber;
    }

    public void setSpecimeNumber(String specimeNumber) {
        this.specimeNumber = specimeNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public List<String> getTestSet() {
        return testSet;
    }

    public void setTestSet(List<String> testSet) {
        this.testSet = testSet;
    }

    public String toString(){
        return getPk()+","+getUserprofile()+","+getSpecimeNumber()+","+getDepartment()+","+getWorkArea()+","+getTestSet();
    }
}
