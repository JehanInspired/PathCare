package applications.PathCareapplication.models;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class UserProfile {

    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    private String PK;

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    public String getAccessProfile() {
        return AccessProfile;
    }

    public void setAccessProfile(String accessProfile) {
        AccessProfile = accessProfile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ExcelCellName("Access Profile")
    private String AccessProfile;

    @ExcelCellName("Location")
    private String location;
}
