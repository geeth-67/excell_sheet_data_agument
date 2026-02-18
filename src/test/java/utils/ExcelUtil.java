package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    static String filePath = "src/test/resources/testdata/LoginData.xlsx";

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        String cellValue = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            cellValue = cell.getStringCellValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellValue;
    }
}
