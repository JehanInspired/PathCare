package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class SpecimenReceive {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("PK")
    private String pk;

    @ExcelCellName("PatientKey")
    private String patientKey;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Container")
    private String container;

    @ExcelCellName("Specimen Number")
    private String specimenNumber;

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

    @ExcelCellName("Aliquot Specimen Number")
    private String aliquotSpecimenNumber;

    @ExcelCellName("AliquotSpecimen")
    private String aliquotspecimen;

    @ExcelCellName("AliquotContainer")
    private String aliquotcontainer;

    @ExcelCellName("Aliquot Volume")
    private String aliquotVolume;

    @ExcelCellName("New Source Volume")
    private String newSourceVolume;

    @ExcelCellName("AliquotTest Set")
    private String aliquotTestSet;

    public String getPatientKey() {
        return patientKey;
    }

    public void setPatientKey(String patientKey) {
        this.patientKey = patientKey;
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
}
