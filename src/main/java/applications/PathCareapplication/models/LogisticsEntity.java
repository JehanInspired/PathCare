package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class LogisticsEntity {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("TransferLogistics_PK")
    private String pk ;

    @ExcelCellName("userprofile_FK")
    private String userprofile_fk;

    @ExcelCellName("Pick_Up_Shipment_Container")
    private String pick_up_shipment_container;

    @ExcelCellName("Drop Off Shipment Container")
    private String drop_off_shipment_container;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getUserprofile_fk() {
        return userprofile_fk;
    }

    public void setUserprofile_fk(String userprofile_fk) {
        this.userprofile_fk = userprofile_fk;
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
