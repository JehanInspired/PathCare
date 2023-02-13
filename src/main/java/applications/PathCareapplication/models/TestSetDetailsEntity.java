package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class TestSetDetailsEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    @ExcelCellName("Patient Key")
    private String patient_Key ;


    @ExcelCellName("Test Set-Superset")
    private String testSetSuperSet;

    @ExcelCellName("Specimen")
    private String specimen;

    @ExcelCellName("Container")
    private String container;

    @ExcelCellName("Anatomical Site")
    private String anatomicalSite;

    @ExcelCellName("Anatomical Site Qualifier")
    private String anatomicalSiteQualifier;

    @ExcelCellName("Lesion")
    private String lesion;

    public String getPatient_Key() {
        return patient_Key;
    }

    public void setPatient_Key(String patient_Key) {
        this.patient_Key = patient_Key;
    }

    public String getTestSetSuperSet() {
        return testSetSuperSet;
    }

    public void setTestSetSuperSet(String testSetSuperSet) {
        this.testSetSuperSet = testSetSuperSet;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getAnatomicalSite() {
        return anatomicalSite;
    }

    public void setAnatomicalSite(String anatomicalSite) {
        this.anatomicalSite = anatomicalSite;
    }

    public String getAnatomicalSiteQualifier() {
        return anatomicalSiteQualifier;
    }

    public void setAnatomicalSiteQualifier(String anatomicalSiteQualifier) {
        this.anatomicalSiteQualifier = anatomicalSiteQualifier;
    }

    public String getLesion() {
        return lesion;
    }

    public void setLesion(String lesion) {
        this.lesion = lesion;
    }
}
