package applications.PathCareapplication.pages;

import Roman.Roman;
import applications.PathCareapplication.models.TestDataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class WorkAreaReceptionPage extends AbstractPage {

    private final By specimenNumberText = By.xpath(" //td/input[@id='SpecimenNumber']");

    private final By workArea = By.xpath("//input[@id='WorkArea']");

    private final   By lookuprowselection = By.xpath("//tr[@id='LookupRow0']");

    private final By departmentText = By.xpath("//input[@id='Department']");

    private final By resetbutton =  By.xpath("//input[@id='reset']");

    private final By checkout  = By.xpath("//input[@id='RemoveFromWorkArea']");

    private final By checkin = By.xpath("//input[@id='update']");
    private final By reset = By.xpath("//input[@id='reset']");

    private  final By workAreaReceptionframe = By.xpath("//iframe[@id='TRAK_main']");


    public WorkAreaReceptionPage(Roman roman) {
        super(roman);
    }

    public void labworkareaswitch(){
        switchToFrame(workAreaReceptionframe);
    }

    public List <TestDataModel> setupdata(String[] departments,String[] testcollections,String[] specimenNumbers){
        List<TestDataModel> testDataModelList = new ArrayList<>();

        for(int x =0;x<=specimenNumbers.length-1;x++){

            testDataModelList.add( new TestDataModel(specimenNumbers[x],testcollections[x],"g",departments[x]));
        }
        return testDataModelList;

        }
//Data get test data from test cases
    /*public void departmentWorkArea(List<NameOfdepartmentItem> department, String[] testset){
        HashMap<String, String> collectsDepartment = new HashMap<>();
         for(String test: testset) {
             char results = test.charAt(0);
             for (NameOfdepartmentItem list : department) {
                 if (list.getAcronym().contentEquals(results)){
                    collectsDepartment.put(list.getName(),list.getAcronym());
                 }

             }
         }*/
        /*for () {
            sendKeys(departmentText, )
        }*/
    public boolean departmentWorkArea(List<TestDataModel> data, boolean checking){

        Boolean checkingout =false;
        for(TestDataModel dataModel: data){

                    sendKeys(departmentText, dataModel.department);

                    click(lookuprowselection);
                    if (validateElement_Enabled_Displayed(workArea, 10)) {
                        sendKeys(workArea, dataModel.workArea);
                    }

                    click(lookuprowselection);
                    findOne(specimenNumberText, dataModel.labespode);
                    if(checking && !checkingout) {
                        stepPassedWithScreenshot("Successfully updated Lab Specimen under Lab episode: " + dataModel.labespode);
                        click(checkin);
                    }else{
                        if(validateElement_Displayed(resetbutton) && validateElement_Enabled_Displayed(checkout)){
                            stepPassedWithScreenshot("Available option : Reset & Check Out. " + dataModel.labespode);
                            click(reset);
                            checkingout =true;
                            checking=true;
                        }
                    }


                }


        return checking;
    }





     public boolean checkout_reset(String[] departments,String[] testcollections,String[] specimenNumbers){

        boolean condition = false;
        super.findOne(workArea).clear();

         for( int x=0;x<=specimenNumbers.length-1;x++) {
             departmentWorkArea(setupdata(departments,testcollections,specimenNumbers),false);



         }

           return condition;

     }





    public void findOne(By by,String input) {
        super.findOne(by).click();
        super.findOne(by).clear();
        super.findOne(by).sendKeys(input);
        super.findOne(by).sendKeys( Keys.TAB);
    }




    @Override
    protected String get_uri() {
        return null;
    }

    @Override
    public boolean waitForDisplayed() {
        return false;
    }
}
