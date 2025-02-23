package org.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
 *  Class that provides methods for extracting data from Excel tables.
 */
public class ExcelDriver {
  public ExcelDriver(String excelFilePath, String sheetName) {
  }

  /*
   * Static method that retrieves testing data from multiple data Excel table and returns it
   * as Map collection of key-value pairs. Column Number indicates number of data set. Note, please, that returned values
   * are String. We should take care of value's type by himself when will use
   * data values in the test.
   */

  public static Map getMultipleData(String dataFileName, String sheetName, int columnNumber) throws IOException {
    System.out.println("dataFile = " + dataFileName); // вказуємо шлях до тестових даних
    System.out.println("sheetName = " + sheetName);
    System.out.println("columnNumber = " + columnNumber);
    Map<String, String> testData = new HashMap<>(); // мапа де ми будемо зберігати отримані тестові дані
    // Create stream for reading from file перед зчитуванням файла треба зробити конкшн, відкрити, зчитати, закрити
    InputStream input = new FileInputStream(dataFileName); //встановлюємо конкшн до нашого файлу
    // Get Excel WorkBook from input stream
    HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(input)); // просимо РОІ бібліотеку розібрати файл на структуру Екселя
    // Get Excel sheet from WorkBook
    HSSFSheet sheet = wb.getSheet(sheetName); //дістати з воркбука відповідний аркуш

    // Get number of data values
    int dataSize = sheet.getPhysicalNumberOfRows() - 1; // кількість фізично заповнених рядочків
    // Look over the table and put key-value pairs into the Map collection
    for (int k = 1; k < (dataSize + 1); k++) { // проходимо по кожному рядочку і зчитаємо дані
      HSSFCell keyCell = sheet.getRow(k).getCell(0); // звертаємось до К рядочку отримуємо значення і зберігаємо у Кей селл
      HSSFCell valueCell = sheet.getRow(k).getCell(columnNumber);
      valueCell.setCellType(HSSFCell.CELL_TYPE_STRING); //зчитуємо стрінгу з екселя
      testData.put(keyCell.getStringCellValue(), valueCell.getStringCellValue()); // записуємо ключ значення
    }

    input.close();
    return testData;
  }


  /*
   * Static method that retrieves testing data from Excel table and returns it
   * as Map collection of key-value pairs. Note, please, that returned values
   * are String. We should take care of value's type by himself when will use
   * data values in the test.
   */
  public static Map<String, String> getData(String dataFileName, String sheetName) throws IOException {
    return getMultipleData(dataFileName, sheetName, 1);
  }


  /*
   * Static method that retrieves testing data from Excel table and returns it
   * as Map collection of key-value pairs. Note, please, that returned values
   * are String. We should take care of value's type by himself when will use
   * data values in the test.
   */
  public static Map getDataRow(String dataFileName, String sheetName) throws IOException {
    Map<String, String> testData = new HashMap<>();
    // Create stream for reading from file
    InputStream input = new FileInputStream(dataFileName);
    // Get Excel WorkBook from input stream
    HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(input));
    // Get Excel sheet from WorkBook
    HSSFSheet sheet = wb.getSheet(sheetName);

    // Get number of data values
    int dataSize = sheet.getRow(2).getPhysicalNumberOfCells();
    // Look over the table and put key-value pairs into the Map collection
    for (int k = 0; k < (dataSize); k++) {
      HSSFCell keyCell = sheet.getRow(2).getCell(k);
      HSSFCell valueCell = sheet.getRow(3).getCell(k);
      valueCell.setCellType(HSSFCell.CELL_TYPE_STRING);
      testData.put(keyCell.getStringCellValue(), valueCell.getStringCellValue());
    }

    input.close();
    return testData;
  }
}
