package applications.PathCareapplication.models;

import java.util.ArrayList;
import java.util.HashMap;

public class LabespideData {

     String lapepideNumber ="";
     String testSet = "";
    ArrayList<String> specimenNumbers = new ArrayList<>();
    ArrayList<String> testGeneratorItem = new ArrayList<>();

    public LabespideData(String lapepideNumber){
        this.lapepideNumber =lapepideNumber;
    }

    public void setTestGeneratorItem(ArrayList<String> testGeneratorItem) {
        this.testGeneratorItem = testGeneratorItem;
    }

    public ArrayList<String> getTestGeneratorItem() {
        return testGeneratorItem;
    }

    public String getLapepideNumber(){
        return lapepideNumber;
    }

    public String gettestSet(){
        return testSet;
    }

    public ArrayList<String> getSpecimenNumbers(){
        return specimenNumbers;
    }

    public void setSpecimenNumbers(ArrayList<String> specimenNumbers) {
        this.specimenNumbers = specimenNumbers;
    }
    public void setTestset(String testSet) {
        this.testSet = testSet;
    }

    public void reader(){

    }


}
