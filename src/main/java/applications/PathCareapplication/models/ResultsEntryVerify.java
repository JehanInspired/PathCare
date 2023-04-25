package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class ResultsEntryVerify {


    @ExcelCellName("Registration_FK")
    private String registration_FK;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Lab Episode")
    private String labEpisode;

    @ExcelCellName("Expected result flag")
    private String expectedResultFlag;


    @ExcelCellName("Expected Queue")
    private String expectedQueue;

    @ExcelCellName("Reference range")
    private String referenceRange;


    public String getRegistration_FK() {
        return registration_FK;
    }

    public void setRegistration_FK(String registration_FK) {
        this.registration_FK = registration_FK;
    }

    public String getLabEpisode() {
        return labEpisode;
    }

    public void setLabEpisode(String labEpisode) {
        this.labEpisode = labEpisode;
    }

    public String getExpectedResultFlag() {
        return expectedResultFlag;
    }

    public void setExpectedResultFlag(String expectedResultFlag) {
        this.expectedResultFlag = expectedResultFlag;
    }

    public String getExpectedQueue() {
        return expectedQueue;
    }

    public void setExpectedQueue(String expectedQueue) {
        this.expectedQueue = expectedQueue;
    }

    public String getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(String referenceRange) {
        this.referenceRange = referenceRange;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
    }

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }
}
