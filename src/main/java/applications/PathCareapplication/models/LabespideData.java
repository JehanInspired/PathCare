package applications.PathCareapplication.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabespideData {

    private String lapepideNumber ="";
   private   String testSet = "";


    private String fileLocation ="src/main/resources/TestCaseResources.txt";
    ArrayList<String> specimenNumbers = new ArrayList<>();



    ArrayList<List<String>> MutlispecimenNumbers = new ArrayList<>();
    ArrayList<String> testGeneratorItem = new ArrayList<>();

    public LabespideData(String lapepideNumber){
        this.lapepideNumber =lapepideNumber;
    }
    public LabespideData(){
        
    }

    public String getFileLocation() {
        return fileLocation;
    }



    public void setTestGeneratorItem(ArrayList<String> testGeneratorItem) {
        this.testGeneratorItem = testGeneratorItem;
    }

    public ArrayList<List<String>> getMutlispecimenNumbers() {
        return MutlispecimenNumbers;
    }

    public void setMutlispecimenNumbers(ArrayList<List<String>> mutlispecimenNumbers) {
        MutlispecimenNumbers = mutlispecimenNumbers;
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


    //Create file readfile
    //Read and write
    //Structure the content/data
    public Boolean reader(String nameofTestCase) throws FileNotFoundException {
        String vsalue ="";
        String result = "";
        ArrayList<String> value = new ArrayList<>();
         String regex = "([1-9])+([A-Z])+\\w+..";
         String regex2 ="([1-9])+\\w";
         Pattern pattern = Pattern.compile(regex,Pattern.UNICODE_CHARACTER_CLASS);
        File file = new File(fileLocation);
        Scanner scan = new Scanner(file);
        for(int x=0;x<nameofTestCase.split(",").length-1;x++) {
            String[] va = nameofTestCase.split(",");
            while (scan.hasNextLine()) {
                vsalue = scan.nextLine();
                if (vsalue.contains(va[x])) {
                    if (pattern.compile(regex).matcher(vsalue).find()) {
                        result = vsalue.split("^(TP_1[1-9][1-9](....))+")[1].replace("]", "");
                        break;
                    } else if (pattern.compile(regex2).matcher(vsalue).find()) {
                        result = vsalue.split("^(TP_1[1-9][1-9](....))+")[1].replace("]", "");
                        break;
                    }
                }
            }

            PrintWriter writer = new PrintWriter(file); //destinationfile
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (!line.contains(result)) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
            scan.close();
            writer.close();
        }

        if(!result.isBlank()){
            MutlispecimenNumbers.add( Arrays.stream(result.split(",")).toList());
            return false;
        }else{
            return true;
        }
    }

    public void write(String nameofTestCase,String values) throws IOException {
        File file = new File(fileLocation);
        FileWriter fr = new FileWriter(file ,true );
        BufferedWriter br  = new BufferedWriter(fr);
        br.write(System.getProperty("line.separator")+nameofTestCase+":"+values+System.getProperty("line.separator") );
        br.close();
        fr.close();
    }


}
