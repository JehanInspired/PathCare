package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class TransferEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Transfer_PK")
    String pk ;

    @ExcelCellName("Patient_FK")
    String userprofile ;

    @ExcelCellName("Status")
    String status;

    @ExcelCellName("TestSet")
    String testSet;

    @ExcelCellName("Department")
    String department;

    @ExcelCellName("From Lab Site")
    String from_lab_site;

    @ExcelCellName("To Lab Site")
    String to_lab_site;

    @ExcelCellName("Referral Lab")
    String referral_lab;

    @ExcelCellName("Date Received From")
    String date_received_from;

    @ExcelCellName("Date Received To")
    String date_received_to;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFrom_lab_site() {
        return from_lab_site;
    }

    public void setFrom_lab_site(String from_lab_site) {
        this.from_lab_site = from_lab_site;
    }

    public String getTo_lab_site() {
        return to_lab_site;
    }

    public void setTo_lab_site(String to_lab_site) {
        this.to_lab_site = to_lab_site;
    }

    public String getReferral_lab() {
        return referral_lab;
    }

    public void setReferral_lab(String referral_lab) {
        this.referral_lab = referral_lab;
    }

    public String getDate_received_from() {
        return date_received_from;
    }

    public void setDate_received_from(String date_received_from) {
        this.date_received_from = date_received_from;
    }

    public String getDate_received_to() {
        return date_received_to;
    }

    public void setDate_received_to(String date_received_to) {
        this.date_received_to = date_received_to;
    }
}
