package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class WorksheetControlEntity {

    @ExcelRow
    private int rowIndex;
    @ExcelCellName("Worksheet Number")
    String worksheetNumber ;
    @ExcelCellName("Worksheet Type")
    String worksheetType ;
    @ExcelCellName("User profile")
    String userprofile_FK;
    @ExcelCellName("Created From")
    String dateCreatedFrom;
    @ExcelCellName("Status")
    String status;
    @ExcelCellName("Type")
    String testType;
    @ExcelCellName("Date Created")
    String dateCreatedTo;
    @ExcelCellName("Time Created")
    String timeCreated;
    @ExcelCellName("Print")
    String printWorksheet;
    @ExcelCellName("Close")
    String close;
    @ExcelCellName("Edit")
    String edit;
    @ExcelCellName("Date Printed")
    String date_printed;
    @ExcelCellName("Time Printed")
    String time_printed;
    @ExcelCellName("Number of Episodes")
    String number_Of_Episodes;
    @ExcelCellName("Date Modified")
    String date_modified;
    @ExcelCellName("Time Modified")
    String time_modified;
    @ExcelCellName("Confidential")
    String confidential;
    @ExcelCellName("Lab Site")
    String lab_Site;
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getWorksheetNumber() {
        return worksheetNumber;
    }

    public void setWorksheetNumber(String worksheetNumber) {
        this.worksheetNumber = worksheetNumber;
    }

    public String getWorksheetType() {
        return worksheetType;
    }

    public void setWorksheetType(String worksheetType) {
        this.worksheetType = worksheetType;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
    }

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }
    public String getDateCreatedFrom() {
        return dateCreatedFrom;
    }

    public void setDateCreatedFrom(String dateCreatedFrom) {
        this.dateCreatedFrom = dateCreatedFrom;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDateCreatedTo() {
        return dateCreatedTo;
    }

    public void setDateCreatedTo(String dateCreatedTo) {
        this.dateCreatedTo = dateCreatedTo;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getPrintWorksheet() {
        return printWorksheet;
    }

    public void setPrintWorksheet(String printWorksheet) {
        this.printWorksheet = printWorksheet;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getDate_printed() {
        return date_printed;
    }

    public void setDate_printed(String date_printed) {
        this.date_printed = date_printed;
    }

    public String getTime_printed() {
        return time_printed;
    }

    public void setTime_printed(String time_printed) {
        this.time_printed = time_printed;
    }

    public String getNumber_Of_Episodes() {
        return number_Of_Episodes;
    }

    public void setNumber_Of_Episodes(String number_Of_Episodes) {
        this.number_Of_Episodes = number_Of_Episodes;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getTime_modified() {
        return time_modified;
    }

    public void setTime_modified(String time_modified) {
        this.time_modified = time_modified;
    }

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
    }

    public String getLab_Site() {
        return lab_Site;
    }

    public void setLab_Site(String lab_Site) {
        this.lab_Site = lab_Site;
    }


}
