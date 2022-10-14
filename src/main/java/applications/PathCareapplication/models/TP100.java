package applications.PathCareapplication.models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TP100 {

    public HashMap<String, ArrayList<String>> value = new HashMap<>();

    public TP100() {

    }

        public void extractTestData(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        Workbook tp_100 = new XSSFWorkbook(file);

            for (Iterator<Sheet> it = tp_100.sheetIterator(); it.hasNext(); ) {
                Sheet sheet = it.next();
                ArrayList<String> datavalue = new ArrayList<>();
                String key = sheet.getSheetName();
                for (int x = 0; x < sheet.getPhysicalNumberOfRows(); x++) {

                    for (Cell cell : sheet.getRow(x)) {

                        if(!(0==x))
                        {
                            if(cell.getCellType().equals(CellType.NUMERIC)) {
                                datavalue.add(String.valueOf(cell.getNumericCellValue()));
                            }else if (cell.getCellType().equals(CellType.STRING)){
                                datavalue.add(cell.getStringCellValue().trim());
                            }

                        }

                    }

                    if(!(x==0)) {
                        value.put(key, datavalue);
                    }
                }
            }

    }
}
