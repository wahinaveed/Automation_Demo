package com.grip.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.NewFieldTypeMunger;

public class ExcelUtils {
	public XSSFWorkbook wbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileOutputStream fos;
	public DataFormatter fromatter = new DataFormatter();

	public void enterWorkbook(String sheetName) throws IOException {
		String excelFilePath = new File(".").getCanonicalPath() + AccessData.TESTDATA.get("DataSheetPath");
		fos = new FileOutputStream(excelFilePath);
		System.out.println("DataSheetPath - " + excelFilePath);
		wbook = new XSSFWorkbook(excelFilePath);
		sheet = wbook.getSheet(sheetName);
	}

	public void fetchData(String sheetName) {
		try {
			enterWorkbook(sheetName);
			sheet = wbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			long cellNum = sheet.getRow(1).getLastCellNum();

			for (int j = 0; j < rowNum; j++) {
				row = sheet.getRow(j);
				for (int i = 0; i < cellNum; i++) {
					cell = row.getCell(i);
					System.out.println(cell.getStringCellValue());
				}
			}
			wbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getRowNumber(String TCName, String colHeaderValue) {
		int rowNumber = 0;
		int lastRowNum = sheet.getLastRowNum();
		try {
			for (int i = 0; i < lastRowNum; i++) {
				row = sheet.getRow(i);
				if (row.getCell(0).getStringCellValue().equalsIgnoreCase(TCName)) {
					rowNumber = i;
					break;
				}
			}
			if (rowNumber == 0)
				throw new Exception("Class entry is missing");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowNumber;
	}

	public int getColumnNumber(String colHeaderValue) {
		int columnNumber = 0;
		int isValid = 0;
		row = sheet.getRow(0);
		int lastCelNum = row.getLastCellNum();
		try {

			for (int i = 0; i < lastCelNum; i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colHeaderValue)) {
					columnNumber = i;
					isValid = 1;
					break;
				}
			}
			if (isValid == 0)
				throw new Exception("Enter valid column name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnNumber;
	}

	public String getCellValue(String sheetName, String TCName, String colHeaderValue) {
		String cellValue = null;
		try {
			enterWorkbook(sheetName);
			int rowNumber = getRowNumber(TCName, colHeaderValue);
			int columnNumber = getColumnNumber(colHeaderValue);
			cell = sheet.getRow(rowNumber).getCell(columnNumber);
			cellValue = cell.getStringCellValue();
			wbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	public void setValue(String sheetName) throws IOException {
		enterWorkbook(sheetName);
		row = sheet.createRow(3);
		cell = row.createCell(1);
		cell.setCellValue("Apple");
		wbook.write(fos);
	}

}
