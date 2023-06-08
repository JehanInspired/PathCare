package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;

public class WRE_AttachmentsEntity {

    @ExcelCellName("PK")
    private String pk;

    @ExcelCellName("FK from Procedure PK")
    private String FK_from_Procedure_PK;
    @ExcelCellName("User profile")
    private String userprofile_FK;

    @ExcelCellName("Worksheet Definition")
    private String worksheetDefinition;

    @ExcelCellName("Worksheet")
    private String worksheet;

    @ExcelCellName("Show final worksheets")
    private String finalworkSheet;

    @ExcelCellName("Show open worksheet")
    private String showOpenWorksheet;

    @ExcelCellName("Attachment")
    private String attachment;
    @ExcelCellName("Name")
    private String name;
    @ExcelCellName("Comments")
    private String comments;
    @ExcelCellName("Discard")
    private String discard;
    @ExcelCellName("Reportable")
    private String reportable;
    @ExcelCellName("Type")
    private String type;
    @ExcelCellName("Test Set")
    private String testSet;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getFK_from_Procedure_PK() {
        return FK_from_Procedure_PK;
    }

    public void setFK_from_Procedure_PK(String FK_from_Procedure_PK) {
        this.FK_from_Procedure_PK = FK_from_Procedure_PK;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
    }

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }

    public String getWorksheetDefinition() {
        return worksheetDefinition;
    }

    public void setWorksheetDefinition(String worksheetDefinition) {
        this.worksheetDefinition = worksheetDefinition;
    }

    public String getWorksheet() {
        return worksheet;
    }

    public void setWorksheet(String worksheet) {
        this.worksheet = worksheet;
    }

    public String getFinalworkSheet() {
        return finalworkSheet;
    }

    public void setFinalworkSheet(String finalworkSheet) {
        this.finalworkSheet = finalworkSheet;
    }

    public String getShowOpenWorksheet() {
        return showOpenWorksheet;
    }

    public void setShowOpenWorksheet(String showOpenWorksheet) {
        this.showOpenWorksheet = showOpenWorksheet;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDiscard() {
        return discard;
    }

    public void setDiscard(String discard) {
        this.discard = discard;
    }

    public String getReportable() {
        return reportable;
    }

    public void setReportable(String reportable) {
        this.reportable = reportable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }
}
