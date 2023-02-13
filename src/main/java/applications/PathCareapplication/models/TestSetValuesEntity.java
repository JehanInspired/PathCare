package applications.PathCareapplication.models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import java.util.HashMap;
import java.util.Map;

public class TestSetValuesEntity {

    @ExcelCellName("TestValueKey")
    private String testValueKey;

    @ExcelCellName("userprofile_FK")
    private String userprofile_FK;



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

    public void setUserprofile_FK(String userprofile_FK) {
        this.userprofile_FK = userprofile_FK;
    }
/*
    public void setTestvalues(Map<String, String> testvalues) {
        this.testvalues = testvalues;
    }

    @ExcelCellName("Malaria Thin Film")
    private String Malaria_Thin_Film;


    @ExcelCellName("Malaria Thick Film")
    private String malaria_Thick_Film;

    @ExcelCellName("P.falciparum Antigen")
    private String p_falciparum_Antigen;

    @ExcelCellName("Malaria Antigen Control")
    private String malaria_antigen_control;


    @ExcelCellName("Anti A")
    private String anti_A;

    @ExcelCellName("Anti B")
    private String anti_b;

    @ExcelCellName("Anti AB")
    private String anti_ab;

    @ExcelCellName("A Cells")
    private String a_Cells;

    @ExcelCellName("A2 Cells")
    private String a2_Cells;

    @ExcelCellName("B Cells")
    private String b_Cells;

    @ExcelCellName("RH Reagent 1")
    private String rh_reagent1;

    @ExcelCellName("Rhesus Control Cells")
    private String rhesus_control_cells;





   /*
    public String getMalaria_Thin_Film() {
        return Malaria_Thin_Film;
    }

    public void setMalaria_Thin_Film(String malaria_Thin_Film) {
        Malaria_Thin_Film = malaria_Thin_Film;
    }

    public String getMalaria_Thick_Film() {
        return malaria_Thick_Film;
    }

    public void setMalaria_Thick_Film(String malaria_Thick_Film) {
        this.malaria_Thick_Film = malaria_Thick_Film;
    }

    public String getP_falciparum_Antigen() {
        return p_falciparum_Antigen;
    }

    public void setP_falciparum_Antigen(String p_falciparum_Antigen) {
        this.p_falciparum_Antigen = p_falciparum_Antigen;
    }

    public String getMalaria_antigen_control() {
        return malaria_antigen_control;
    }

    public void setMalaria_antigen_control(String malaria_antigen_control) {
        this.malaria_antigen_control = malaria_antigen_control;
    }

    public String getAnti_A() {
        return anti_A;
    }

    public void setAnti_A(String anti_A) {
        this.anti_A = anti_A;
    }

    public String getAnti_b() {
        return anti_b;
    }

    public void setAnti_b(String anti_b) {
        this.anti_b = anti_b;
    }

    public String getAnti_ab() {
        return anti_ab;
    }

    public void setAnti_ab(String anti_ab) {
        this.anti_ab = anti_ab;
    }

    public String getA_Cells() {
        return a_Cells;
    }

    public void setA_Cells(String a_Cells) {
        this.a_Cells = a_Cells;
    }

    public String getA2_Cells() {
        return a2_Cells;
    }

    public void setA2_Cells(String a2_Cells) {
        this.a2_Cells = a2_Cells;
    }

    public String getB_Cells() {
        return b_Cells;
    }

    public void setB_Cells(String b_Cells) {
        this.b_Cells = b_Cells;
    }

    public String getRh_reagent1() {
        return rh_reagent1;
    }

    public void setRh_reagent1(String rh_reagent1) {
        this.rh_reagent1 = rh_reagent1;
    }

    public String getRhesus_control_cells() {
        return rhesus_control_cells;
    }

    public void setRhesus_control_cells(String rhesus_control_cells) {
        this.rhesus_control_cells = rhesus_control_cells;
    }

    /*public HashMap<String, ArrayList<String>> values() throws IllegalAccessException {
        HashMap<String, ArrayList<String>> testsetvalues = new HashMap<>();
        ArrayList<String> specimensResults = new ArrayList<>();
        String keyTestValue = "";
        for (Field field : TestSetValuesEntity.class.getDeclaredFields()) {

            if (!field.getName().contentEquals("testValueKey") && !field.getName().contentEquals("userprofile_FK")) {
                specimensResults.add((String) field.get(this));
                testsetvalues.put(keyTestValue, specimensResults);
            }else if (!field.getName().isBlank()) {
                keyTestValue = (String) field.get(this);
            }

        }
        return testsetvalues;
    }*/


}
