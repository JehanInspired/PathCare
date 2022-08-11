package applications.PathCareapplication.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.poi.xssf.usermodel.XSSFWorkbookType.XLSX;

public class TestDataModel {
    public String labespode;
    public String Stage;
    public String code;
    public String site;
    public String securityGroup;
    public String[] AccessProfile;
    public String testset;
    public String department;
    public String workArea;

    public TestDataModel(String labespodespecimen, String testset, String workArea,String department)
    {
        this.labespode = labespodespecimen;
        this.testset = testset;
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

    public static TestDataModel getExampleModel(String name) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+name+".json"), TestDataModel.class);
    }
}
