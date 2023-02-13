package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class SpecimenReceiveEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("PK")
    private String pk;

    @ExcelCellName("PatientKey_FK")
    private String patientKey_FK;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Specimen Number")
    private String specimenNumber;

    @ExcelCellName("test set")
    private String testSet;

    @ExcelCellName("Container")
    private String container;

    @ExcelCellName("Anatomical Site")
    private String anatomicalSite;

    @ExcelCellName("Anatomical Site Qualifier")
    private String AnatomicalSiteQualifier;

    @ExcelCellName("Lesion")
    private String lesion;

    @ExcelCellName("Volume Received")
    private String volumeReceived;

    @ExcelCellName("Aliquot edit PK")
    private String aliquotEditPK;

    @ExcelCellName("Specimen Number")
    private String aliquotSpecimenNumber;

    @ExcelCellName("Specimen")
    private String aliquotspecimen;

    @ExcelCellName("Container")
    private String aliquotcontainer;

    @ExcelCellName("Aliquot Volume")
    private String aliquotVolume;

    @ExcelCellName("New Source Volume")
    private String newSourceVolume;

    @ExcelCellName("AliquotTest Set")
    private String aliquotTestSet;

    public SpecimenReceiveEntity(){}
    public SpecimenReceiveEntity(String pk, String patientKey_FK, String specimenNumber, String testSet){
        this.pk =pk;
        this.patientKey_FK =patientKey_FK;
        this.specimenNumber = specimenNumber;
        this.testSet = testSet;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPatientKey_FK() {
        return patientKey_FK;
    }

    public void setPatientKey_FK(String patientKey) {
        this.patientKey_FK = patientKey;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
    }

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getSpecimenNumber() {
        return specimenNumber;
    }

    public void setSpecimenNumber(String specimenNumber) {
        this.specimenNumber = specimenNumber;
    }

    public String getAnatomicalSite() {
        return anatomicalSite;
    }

    public void setAnatomicalSite(String anatomicalSite) {
        this.anatomicalSite = anatomicalSite;
    }

    public String getAnatomicalSiteQualifier() {
        return AnatomicalSiteQualifier;
    }

    public void setAnatomicalSiteQualifier(String anatomicalSiteQualifier) {
        AnatomicalSiteQualifier = anatomicalSiteQualifier;
    }

    public String getLesion() {
        return lesion;
    }

    public void setLesion(String lesion) {
        this.lesion = lesion;
    }

    public String getVolumeReceived() {
        return volumeReceived;
    }

    public void setVolumeReceived(String volumeReceived) {
        this.volumeReceived = volumeReceived;
    }

    public String getAliquotEditPK() {
        return aliquotEditPK;
    }

    public void setAliquotEditPK(String aliquotEditPK) {
        this.aliquotEditPK = aliquotEditPK;
    }

    public String getAliquotSpecimenNumber() {
        return aliquotSpecimenNumber;
    }

    public void setAliquotSpecimenNumber(String aliquotSpecimenNumber) {
        this.aliquotSpecimenNumber = aliquotSpecimenNumber;
    }

    public String getAliquotspecimen() {
        return aliquotspecimen;
    }

    public void setAliquotspecimen(String aliquotspecimen) {
        this.aliquotspecimen = aliquotspecimen;
    }

    public String getAliquotcontainer() {
        return aliquotcontainer;
    }

    public void setAliquotcontainer(String aliquotcontainer) {
        this.aliquotcontainer = aliquotcontainer;
    }

    public String getAliquotVolume() {
        return aliquotVolume;
    }

    public void setAliquotVolume(String aliquotVolume) {
        this.aliquotVolume = aliquotVolume;
    }

    public String getNewSourceVolume() {
        return newSourceVolume;
    }

    public void setNewSourceVolume(String newSourceVolume) {
        this.newSourceVolume = newSourceVolume;
    }

    public String getAliquotTestSet() {
        return aliquotTestSet;
    }

    public void setAliquotTestSet(String aliquotTestSet) {
        this.aliquotTestSet = aliquotTestSet;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String ToString(){
        return getPk()+","+getPatientKey_FK()+","+getSpecimenNumber()+","+getTestSet();
    }
}
