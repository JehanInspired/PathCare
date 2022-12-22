package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelCellRange;
import com.poiji.annotation.ExcelRow;

public class EditTestSet {

    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    String pk ="";

    @ExcelCell(1)
    String testcode ="";

    public String getTestcode() {
        return testcode;
    }

    public String getPk() {
        return pk;
    }
    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    @ExcelCellRange
    public TestSetSpecialHandling testSetSpecialHandling;

    @ExcelCellRange
    public AdditionalTestSetQuestions additionalTestSetQuestions;

    public static class TestSetSpecialHandling {
        @ExcelCellName("Hide For Collection")
        private String hideforCollection;
        @ExcelCellName("Hide For Receive")
        private String hideforReceive;
        @ExcelCellName("Hide For Processing")
        private String hideforProcessing;
        @ExcelCellName("Discard")
        private String discard;

        public String getHideforCollection() {
            return hideforCollection;
        }

        public void setHideforCollection(String hideforCollection) {
            this.hideforCollection = hideforCollection;
        }

        public String getHideforReceive() {
            return hideforReceive;
        }

        public void setHideforReceive(String hideforReceive) {
            this.hideforReceive = hideforReceive;
        }

        public String getHideforProcessing() {
            return hideforProcessing;
        }

        public void setHideforProcessing(String hideforProcessing) {
            this.hideforProcessing = hideforProcessing;
        }

        public String getDiscard() {
            return discard;
        }

        public void setDiscard(String discard) {
            this.discard = discard;
        }
    }

    public static class AdditionalTestSetQuestions {
        @ExcelCellName("Specimen Container")
        private String specimenContainer;
        @ExcelCellName("Number of FNA Slides")
        private String numberOfFNASlides;

        @ExcelCellName("Macroscopic Description")
        private String macropscopicDescription;

        @ExcelCellName("After Request")
        private String afterRequest;

        @ExcelCellName("After Requested By")
        private String afterRequestBy;

        @ExcelCellName("Payment Status")
        private String paymentStatus;

        @ExcelCellName("Patient on replacement Rx?")
        private String patientOnReplacementRX;


        @ExcelCellName("Is Patient on Oxygen?")
        private String PatientOnOxygen;


        @ExcelCellName("Time Bloodgas taken (HHMM):")
        private String timeBloodgastaken;

        @ExcelCellName("Enter % of Oxygen given or NS")
        private String enterOfOxygenGiven;



        public String getPatientOnOxygen() {
            return PatientOnOxygen;
        }

        public void setPatientOnOxygen(String patientOnOxygen) {
            PatientOnOxygen = patientOnOxygen;
        }
        public String getTimeBloodgastaken() {
            return timeBloodgastaken;
        }

        public void setTimeBloodgastaken(String timeBloodgastaken) {
            this.timeBloodgastaken = timeBloodgastaken;
        }

        public String getEnterOfOxygenGiven() {
            return enterOfOxygenGiven;
        }

        public void setEnterOfOxygenGiven(String enterOfOxygenGiven) {
            this.enterOfOxygenGiven = enterOfOxygenGiven;
        }


        public String getSpecimenContainer() {
            return specimenContainer;
        }

        public void setSpecimenContainer(String specimenContainer) {
            this.specimenContainer = specimenContainer;
        }

        public String getNumberOfFNASlides() {
            return numberOfFNASlides;
        }

        public void setNumberOfFNASlides(String numberOfFNASlides) {
            this.numberOfFNASlides = numberOfFNASlides;
        }

        public String getMacropscopicDescription() {
            return macropscopicDescription;
        }

        public void setMacropscopicDescription(String macropscopicDescription) {
            this.macropscopicDescription = macropscopicDescription;
        }

        public String getAfterRequest() {
            return afterRequest;
        }

        public void setAfterRequest(String afterRequest) {
            this.afterRequest = afterRequest;
        }

        public String getAfterRequestBy() {
            return afterRequestBy;
        }

        public void setAfterRequestBy(String afterRequestBy) {
            this.afterRequestBy = afterRequestBy;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getPatientOnReplacementRX() {
            return patientOnReplacementRX;
        }

        public void setPatientOnReplacementRX(String patientOnReplacementRX) {
            this.patientOnReplacementRX = patientOnReplacementRX;
        }


    }

    public EditTestSet() {
    }
}
