package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

import java.util.List;

public class VetPatientEntity {
    @ExcelRow
    private int rowIndex;
    @ExcelCellName("PK")
    String pk;
    @ExcelCellName("userprofile_FK")
    String userprofile ;
    @ExcelCellName("Subject Name")
    String Subject_name ;
    @ExcelCellName("Alternate ID Number")
    String idNumber ;
    @ExcelCellName("Sex")
    String sex ;
    @ExcelCellName("Species")
    String Species;
    @ExcelCellName("Title")
    String title;
    @ExcelCellName("Surname")
    String surname;
    @ExcelCellName("Given Name")
    String givenName;
    @ExcelCellName("Referring Veterinarian")
    String referringVeterinarian ;

    @ExcelCellName("Secondary Veterinarian")
    String secondaryVeterinarian;
    @ExcelCellName("Requesting Locations")
    String requestedLocation;
    @ExcelCellName("Collection Date")
    String collectionDate;
    @ExcelCellName("Collection Time")
    String collectionTime;
    @ExcelCellName("Received Date")
    String receivedDate;
    @ExcelCellName("Received Time")
    String receivedTime;
    @ExcelCellName("Test Set/Superset")
    List<String> testSet;
    @ExcelCellName("New Patient")
    String newPatient;
    @ExcelCellName("URN")
    String URN ;

/*    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }*/

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

    public String getSubject_name() {
        return Subject_name;
    }

    public void setSubject_name(String subject_name) {
        Subject_name = subject_name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getReferringVeterinarian() {
        return referringVeterinarian;
    }

    public void setReferringVeterinarian(String referringVeterinarian) {
        this.referringVeterinarian = referringVeterinarian;
    }

    public String getSecondaryVeterinarian() {
        return secondaryVeterinarian;
    }

    public void setSecondaryVeterinarian(String secondaryVeterinarian) {
        this.secondaryVeterinarian = secondaryVeterinarian;
    }

    public String getRequestedLocation() {
        return requestedLocation;
    }

    public void setRequestedLocation(String requestedLocation) {
        this.requestedLocation = requestedLocation;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
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

    public List<String> getTestSet() {
        return testSet;
    }

    public void setTestSet(List<String> testSet) {
        this.testSet = testSet;
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
