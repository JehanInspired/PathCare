package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;

public class LabQueueEntity {

    @ExcelCellName("LabQueuesKey")
    String labQueuesKey ="";

    @ExcelCellName("User ProfileFK")
    String userProfile ="";

    @ExcelCellName("Patient Key")
    String patientKey ="";

    @ExcelCellName("SpecimenNum")
    String specimenNum ="";

    @ExcelCellName("Queue Type")
    String queueType = "";

    @ExcelCellName("Department")
    String department = "";

    @ExcelCellName("Test Set Status")
    String testSetStatus = "";

    @ExcelCellName("Patient Location")
    String patientLocation = "";

    @ExcelCellName("User Lab Site")
    String userLabSite = "";

    @ExcelCellName("Test Set")
    String testSet = "";

    @ExcelCellName("Test Set Lab Site")
    String testSetLabSite = "";

    @ExcelCellName("SearchOptionQueue")
    String searchOptionQueue = "";

    @ExcelCellName("SearchOption")
    String searchOption = "";

    @ExcelCellName("SelectSingleOrEpisode")
    String selectSingleOrEpisode = "";

    public LabQueueEntity() {
    }

    public String getLabQueuesKey() {
        return labQueuesKey;
    }

    public void setLabQueuesKey(String labQueuesKey) {
        this.labQueuesKey = labQueuesKey;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getPatientKey() {
        return patientKey;
    }

    public void setPatientKey(String patientKey) {
        this.patientKey = patientKey;
    }

    public String getSpecimenNum() {
        return specimenNum;
    }

    public void setSpecimenNum(String specimenNum) {
        this.specimenNum = specimenNum;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTestSetStatus() {
        return testSetStatus;
    }

    public void setTestSetStatus(String testSetStatus) {
        this.testSetStatus = testSetStatus;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public String getUserLabSite() {
        return userLabSite;
    }

    public void setUserLabSite(String userLabSite) {
        this.userLabSite = userLabSite;
    }

    public String getTestSet() {
        return testSet;
    }

    public void setTestSet(String testSet) {
        this.testSet = testSet;
    }

    public String getTestSetLabSite() {
        return testSetLabSite;
    }

    public void setTestSetLabSite(String testSetLabSite) {
        this.testSetLabSite = testSetLabSite;
    }

    public String getSearchOptionQueue() {
        return searchOptionQueue;
    }

    public void setSearchOptionQueue(String searchOptionQueue) {
        this.searchOptionQueue = searchOptionQueue;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getSelectSingleOrEpisode() {
        return selectSingleOrEpisode;
    }

    public void setSelectSingleOrEpisode(String selectSingleOrEpisode) {
        this.selectSingleOrEpisode = selectSingleOrEpisode;
    }


}
