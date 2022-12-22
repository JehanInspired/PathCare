package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import java.util.List;

public class PatientModel {
    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    String pk ="";
    @ExcelCell(1)
    @ExcelCellName("userprofile_FK")
    String userprofile ="";
    @ExcelCell(2)
    @ExcelCellName("Surname")
    String surname = "";
    @ExcelCell(3)
    @ExcelCellName("Given Name")
    String givenName = "";
    @ExcelCell(4)
    @ExcelCellName("Sex")
    String sex = "";
    @ExcelCell(5)
    @ExcelCellName("DOB")
    String dateOfBirth = "";
    @ExcelCell(6)
    @ExcelCellName("Referring Doctor")
    String referringDoctor = "";
    @ExcelCell(7)
    @ExcelCellName("Patient Location")
    String patientLocation = "";

    @ExcelCell(8)
    @ExcelCellName("Collection Time")
    String collectionTime = "";

    @ExcelCell(9)
    @ExcelCellName("Received Date")
    String receivedDate = "";

    @ExcelCell(10)
    @ExcelCellName("Received Time")
    String receivedTime = "";

    @ExcelCell(11)
    @ExcelCellName("Test Set/Superset")
    List<String> testSet;

    @ExcelCell(12)
    @ExcelCellName("New Patient")
    String newPatient;

    @ExcelCell(13)
    @ExcelCellName("URN")
    String URN ;

    public List<String> getTestSet() {
        return testSet;
    }
    public String getCollectionTime() {
        return collectionTime;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }


    public void setTestSet(List<String> testSet) {
        this.testSet = testSet;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getUserprofile() {
        return userprofile;
    }



    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getReferringDoctor() {
        return referringDoctor;
    }

    public void setReferringDoctor(String referringDoctor) {
        this.referringDoctor = referringDoctor;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getNewPatient() {
        return newPatient;
    }

    public void setNewPatient(String newPatient) {
        this.newPatient = newPatient;
    }

    public String getURN() {
        return URN;
    }

    public void setURN(String URN) {
        this.URN = URN;
    }

}
