package com.example.uploadDemo;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadExcelDemo {
	public static void main(String[] args) {
		ReadExcelDemo excelDemo = new ReadExcelDemo();
		MultipartFile file = null;
		excelDemo.readExcel(file);
	}

	private String readExcel(MultipartFile file) {
		StringBuilder stringBuffer = new StringBuilder();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String celValue = null;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						celValue = cell.getStringCellValue();
						break;
					}
				}
				stringBuffer.append(celValue + ",");
			}
			System.out.println(stringBuffer.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
}