package applications.PathCareapplication.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TestDataModel {
    public Object labespode;
    public String testset;
    public String department;
    public String workArea;

    public TestDataModel(Object labespodespecimen, String testset, String workArea, String department)
    {
        this.labespode = labespodespecimen;
        this.testset = testset;
        this.department = department;
        this.workArea = workArea;
    }

    public TestDataModel(Object labespodespecimen, String workArea, String department)
    {
        this.labespode = labespodespecimen;
        this.department = department;
        this.workArea = workArea;
    }

    /*public void extractTestData(String path) throws IOException {
        FileInputStream file = new FileInputStream(new File("Test Set codes.xlsx"));
        Workbook testSetCode = new XSSFWorkbook(file);
        ArrayList<TestDataModel> testDataModels =  new ArrayList<TestDataModel>();
        Sheet sheet1 = testSetCode.getSheetAt(0);
        for(int x =2;x<sheet1.getPhysicalNumberOfRows();x++){

            for(Cell cell: sheet1.getRow(x)){

                //Registration couter 5++
                if(cell.getColumnIndex()) {
                    testDataModels.add();
                }

            }


        }

    }*/
}
