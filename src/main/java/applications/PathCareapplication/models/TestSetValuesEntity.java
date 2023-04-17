package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;
import java.util.Map;

public class TestSetValuesEntity {

    @ExcelCellName("PK")
    private String pk;
    @ExcelCellName("TestValueKey")
    private String testValueKey;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;

    @ExcelCellName("Apply")
    private String apply;

    @ExcelCellName("Validate")
    private String validate;

    @ExcelCellName("Pool")
    private String pool;

    @ExcelCellName("Pool Specimen")
    private String poolSpecimen;

    @ExcelCellName("Reflex Testing")
    private String reflex;

    @ExcelUnknownCells
    private Map<String,String>  testvalues = new HashMap<>();

    public Map<String, String> getTestvalues() {
        return testvalues;
    }


    public String getTestValueKey() {
        return testValueKey;
    }

    public void setTestValueKey(String testValueKey) {
        this.testValueKey = testValueKey;
    }

    public String getUserprofile_FK() {
        return userprofile_FK;
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
    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getReflex() {
        return reflex;
    }

    public void setReflex(String reflex) {
        this.reflex = reflex;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getPoolSpecimen() {
        return poolSpecimen;
    }

    public void setPoolSpecimen(String poolSpecimen) {
        this.poolSpecimen = poolSpecimen;
    }


}
