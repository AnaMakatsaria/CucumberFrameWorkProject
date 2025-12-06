package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class ExcelReader {
    //we need to store the data in List of maps
    public static void main(String[] args) throws IOException {


        List<Map<String, String>> excelData = new ArrayList<>();


        //1) location of the file
        String path = "C:\\Users\\akhar\\IdeaProjects\\CucumberFrameworkProject\\src\\test\\resources\\testData\\ProjectB24.xlsx";
        //2navigate to the file
        FileInputStream fis = new FileInputStream(path);
        //Properties class want work to read the Excel file. we need XSSFWorkbook
        XSSFWorkbook excel = new XSSFWorkbook(fis);
        //since we can have multiple sheets in Excel file we need to follow this structure
        Sheet sheet = excel.getSheet("Sheet1");
        //we can extract the data from row [0], to use as keys;
        Row headerRow = sheet.getRow(0);
        //to read the data we can use loop
        //we have built-in method to get physical numbers of rows that actually contains data:
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            // to access the new data from the row at each iteration(values to the map)
            Row row = sheet.getRow(i);
            //store the data inside the map
            Map<String, String> rowMap = new LinkedHashMap<>();
            //and to get physical numbers of cells containing data we can use method:
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                //to get the cell:
                Cell cell = row.getCell(j);
                //storing headerRow into a String to use as keys to the maps:
                String key = headerRow.getCell(j).toString();
                //getting values in form of a String to pass in maps
                String value = row.getCell(j).toString();
                rowMap.put(key, value);

            }
            excelData.add(rowMap);

        }

    }
}