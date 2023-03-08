package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;

public class WorkSheetResultEntry {

    @ExcelCellName("WorkSheetResult Key")
    private String workSheetResult_Key;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Worksheet Definition")
    private String worksheetDefinition;

    @ExcelCellName("Worksheet")
    private String worksheet;

    @ExcelCellName("Show final worksheets")
    private String finalworkSheet;

    @ExcelCellName("Show open worksheet")
    private String ShowOpenWorksheet;

    @ExcelCellName("Specimen receive _FK")
    private String specimenReceive_FK;

    public String getWorkSheetResult_Key() {
        return workSheetResult_Key;
    }

    public void setWorkSheetResult_Key(String workSheetResult_Key) {
        this.workSheetResult_Key = workSheetResult_Key;
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
        return ShowOpenWorksheet;
    }

    public void setShowOpenWorksheet(String showOpenWorksheet) {
        ShowOpenWorksheet = showOpenWorksheet;
    }

    public String getSpecimenReceive_FK() {
        return specimenReceive_FK;
    }

    public void setSpecimenReceive_FK(String specimenReceive_FK) {
        this.specimenReceive_FK = specimenReceive_FK;
    }
}
