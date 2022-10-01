package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class FileProviderServiceImpl {

  public String excelToJson(final String filePath) throws IOException {
    StringJoiner csv = new StringJoiner(",");   //StringeJoiner object
    try {

      FileInputStream excelFile = new FileInputStream(filePath);
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      for (Row currentRow : datatypeSheet) {

        for (Cell currentCell : currentRow) {

          //getCellTypeEnum shown as deprecated for version 3.15
          //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
          if (currentCell.getCellType() == CellType.STRING) {
            System.out.print(currentCell.getStringCellValue() + "--");
            String tmp = currentCell.getStringCellValue();
            csv.add(tmp);
          } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
            System.out.print(currentCell.getNumericCellValue() + "--");
            String tmp = String.valueOf(currentCell.getNumericCellValue());
            csv.add(tmp);
          }
        }
        csv.add("\n");
        System.out.println();

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String response = csv.toString().replace("\n,", "\n");
    response = response.replace(",\n", "\n");
    return response;
  }
}
