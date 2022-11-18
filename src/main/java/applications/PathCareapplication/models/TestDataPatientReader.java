package applications.PathCareapplication.models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TestDataPatientReader {

    public HashMap<String, List<String>> value = new HashMap<>();

    public TestDataPatientReader() {

    }

        public void extractTestData(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        Workbook extractSheet = new XSSFWorkbook(file);
            ArrayList<String> datavalue = new ArrayList<>();

            for (Iterator<Sheet> it = extractSheet.sheetIterator(); it.hasNext(); ) {
                Sheet sheet = it.next();
                datavalue = new ArrayList<>();
                String key = sheet.getSheetName();
                for (int x = 0; x < sheet.getPhysicalNumberOfRows(); x++) {

                    for (Cell cell : sheet.getRow(x)) {

                        if(!(0==x))
                        {
                            if(cell.getCellType().equals(CellType.NUMERIC)) {
                                datavalue.add(String.valueOf(cell.getNumericCellValue()));
                            }else if (cell.getCellType().equals(CellType.STRING)){
                                datavalue.add(cell.getStringCellValue().trim());
                            }else if(cell.getCellType().equals(CellType.BLANK)){
                                datavalue.add(cell.getStringCellValue().trim());
                            }

                        }

                    }

                }
              value.put(key,datavalue);

            }

    }
}
