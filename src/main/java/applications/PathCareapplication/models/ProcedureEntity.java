package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class ProcedureEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("PK")
    String pk ;

    @ExcelCellName("User")
    String userprofile_FK;
    @ExcelCellName("Department")
    String department;
    @ExcelCellName("LabSite")
    String lab_site;
    @ExcelCellName("LabEpisode")
    String labEpisode;
    @ExcelCellName("SpecimenNumber")
    String specimenNumber;
    @ExcelCellName("TestSet")
    String testSet;
    @ExcelCellName("WorkArea")
    String workArea;
    @ExcelCellName("DateFrom")
    String date__from;
    @ExcelCellName("DateTo")
    String date_to;
    @ExcelCellName("Procedure")
    String procedure ;
    @ExcelCellName("Queue")
    String queue ;
    @ExcelCellName("SelectAll")
    String selectAll ;
    @ExcelCellName("BulkComplete")
    String bulkComplete ;
    @ExcelCellName("BulkCancel")
    String bulkCancel ;
    @ExcelCellName("SaveSearch")
    String saveSearch ;
    @ExcelCellName("Worklist")
    String worklist ;
    @ExcelCellName("Find")
    String find ;
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
    }

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLab_site() {
        return lab_site;
    }

    public void setLab_site(String lab_site) {
        this.lab_site = lab_site;
    }

    public String getLabEpisode() {
        return labEpisode;
    }

    public void setLabEpisode(String labEpisode) {
        this.labEpisode = labEpisode;
    }

    public String getSpecimenNumber() {
        return specimenNumber;
    }

    public void setSpecimenNumber(String specimenNumber) {
        this.specimenNumber = specimenNumber;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getDate__from() {
        return date__from;
    }

    public void setDate__from(String date__from) {
        this.date__from = date__from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(String selectAll) {
        this.selectAll = selectAll;
    }

    public String getBulkComplete() {
        return bulkComplete;
    }

    public void setBulkComplete(String bulkComplete) {
        this.bulkComplete = bulkComplete;
    }

    public String getBulkCancel() {
        return bulkCancel;
    }

    public void setBulkCancel(String bulkCancel) {
        this.bulkCancel = bulkCancel;
    }

    public String getSaveSearch() {
        return saveSearch;
    }

    public void setSaveSearch(String saveSearch) {
        this.saveSearch = saveSearch;
    }

    public String getWorklist() {
        return worklist;
    }

    public void setWorklist(String worklist) {
        this.worklist = worklist;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }


}
