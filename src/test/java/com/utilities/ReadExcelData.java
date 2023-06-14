package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public String[][] getTestData(String SheetName) throws IOException {

		File ExcelFile = new File("./testData/ExcelTestData.xlsx");
		FileInputStream input = new FileInputStream(ExcelFile);

		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		int numberOfColumns = sheet.getRow(0).getLastCellNum();

		String[][] Data = new String[numberOfRows - 1][numberOfColumns];
		for (int i = 0; i < numberOfRows - 1; i++) {

			for (int j = 0; j < numberOfColumns; j++) {

				DataFormatter df = new DataFormatter();
				Data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
			}
		}

		workbook.close();
		input.close();

		return Data;
	}
}
