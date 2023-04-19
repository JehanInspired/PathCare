package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;


public class ResultsGenerator_PCP {

    @ExcelCellName("PK")
    private String resultGeneratorPK;

    @ExcelCellName("User Profile")
    private  String user_Profile ;

    @ExcelCellName("Instrument")
    private  String instrument ;


    @ExcelCellName("Test Group")
    private String testGroup ;

    @ExcelCellName("# of QC Runs")
    private String qcRuns ;

    @ExcelCellName("QC Sample ID / Cup Position")
    private String qcSampleIDCupPosition;

    @ExcelUnknownCells
    private HashMap<String, String> stringValueMap = new HashMap<>();

    public String getResultGeneratorPK() {
        return resultGeneratorPK;
    }

    public void setResultGeneratorPK(String resultGeneratorPK) {
        this.resultGeneratorPK = resultGeneratorPK;
    }

    public String getUser_Profile() {
        return user_Profile;
    }

    public void setUser_Profile(String user_Profile) {
        this.user_Profile = user_Profile;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(String testGroup) {
        this.testGroup = testGroup;
    }

    public String getQcRuns() {
        return qcRuns;
    }

    public void setQcRuns(String qcRuns) {
        this.qcRuns = qcRuns;
    }

    public String getQcSampleIDCupPosition() {
        return qcSampleIDCupPosition;
    }

    public void setQcSampleIDCupPosition(String qcSampleIDCupPosition) {
        this.qcSampleIDCupPosition = qcSampleIDCupPosition;
    }

    public HashMap<String, String> getStringValueMap() {
        return stringValueMap;
    }
}
