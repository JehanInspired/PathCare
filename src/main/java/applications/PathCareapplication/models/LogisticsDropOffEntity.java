package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class LogisticsDropOffEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("TransferLogistics_PK")
    private String pk ;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Pick_Up_Shipment_Container")
    private String pick_up_shipment_container;

    @ExcelCellName("Drop Off Shipment Container")
    private String drop_off_shipment_container;
    @ExcelCellName("Acknowledged By")
    private String acknowledged_By;

    public String getAcknowledged_By() {
        return acknowledged_By;
    }

    public void setAcknowledged_By(String acknowledged_By) {
        this.acknowledged_By = acknowledged_By;
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

    public String getPick_up_shipment_container() {
        return pick_up_shipment_container;
    }

    public void setPick_up_shipment_container(String pick_up_shipment_container) {
        this.pick_up_shipment_container = pick_up_shipment_container;
    }

    public String getDrop_off_shipment_container() {
        return drop_off_shipment_container;
    }

    public void setDrop_off_shipment_container(String drop_off_shipment_container) {
        this.drop_off_shipment_container = drop_off_shipment_container;
    }
}