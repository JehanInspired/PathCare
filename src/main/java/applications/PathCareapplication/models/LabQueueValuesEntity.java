package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;

public class LabQueueValuesEntity {

    @ExcelCellName("QueuesValueKey")
    String QueuesValueKey ="";

    @ExcelCellName("LabQueuesFK")
    String labQueuesFK ="";

    @ExcelCellName("Apply")
    String apply ="";

    @ExcelCellName("Validate")
    String validate ="";

    @ExcelCellName("Authorise")
    String authorise ="";

    @ExcelCellName("Phone")
    String phone ="";

    @ExcelCellName("Reflex Testing")
    String reflexTesting = "";

    @ExcelCellName("View Queue")
    String viewQueue = "";


    public LabQueueValuesEntity() {
    }

    public String getQueuesValueKey() {
        return QueuesValueKey;
    }

    public void setQueuesValueKey(String queuesValueKey) {
        QueuesValueKey = queuesValueKey;
    }

    public String getLabQueuesFK() {
        return labQueuesFK;
    }

    public void setLabQueuesFK(String labQueuesFK) {
        this.labQueuesFK = labQueuesFK;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReflexTesting() {
        return reflexTesting;
    }

    public void setReflexTesting(String reflexTesting) {
        this.reflexTesting = reflexTesting;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getAuthorise() {
        return authorise;
    }

    public void setAuthorise(String authorise) {
        this.authorise = authorise;
    }

    public String getViewQueue() {
        return viewQueue;
    }

    public void setViewQueue(String viewQueue) {
        this.viewQueue = viewQueue;
    }
}
