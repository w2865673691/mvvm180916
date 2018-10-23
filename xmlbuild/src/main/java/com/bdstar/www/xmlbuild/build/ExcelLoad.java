package com.bdstar.www.xmlbuild.build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelLoad {

    public static void main(String[] args) throws SQLException {
        String excelName = "E:\\CarManual\\GE11目录树20180906.xls";
        File file = new File(excelName);
        readExcel(file);
    }

    public static void readExcel(File file) {
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = Workbook.getWorkbook(is);
            int sheetSize = wb.getNumberOfSheets();
            System.out.println("sheetSize-->"+sheetSize);
            for (int index = 0; index < sheetSize; index++) {
                Sheet sheet = wb.getSheet(index);
                System.out.println("getRows-->"+sheet.getName());
                System.out.println("getRows-->"+sheet.getRows());
                System.out.println("getColumns-->"+sheet.getColumns());
                // sheet.getRows()返回该页的总行数
                // sheet.getColumns()返回该页的总列数
//                for (int i = 0; i < sheet.getRows(); i++) {
//                    for (int j = 0; j < sheet.getColumns(); j++) {
//                        String cellinfo = sheet.getCell(j, i).getContents();
//                        System.out.println("-->"+cellinfo);
//                    }
//                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
