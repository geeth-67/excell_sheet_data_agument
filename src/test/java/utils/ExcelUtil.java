package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    static String filePath = "src/test/resources/testdata/LoginData.xlsx";


    // ========================
    // READ METHOD
    // ========================
    public static String getCellData(String sheetName, int rowNum, int colNum) {

        String cellValue = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            cellValue = cell.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellValue;
    }



    // ========================
    // WRITE METHOD
    // ========================
    public static void setCellData(String sheetName, int rowNum, int colNum, String value) {

        try {

            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet(sheetName);

            // create row if not exists
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            // create cell if not exists
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            // set value
            cell.setCellValue(value);

            fis.close();

            // write back to file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);

            fos.close();
            workbook.close();

            System.out.println("Data written successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}