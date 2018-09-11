package com.greatmindsolutions.wsdltoexcel.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.greatmindsolutions.wsdltoexcel.modal.OperationEntity;

public class ExcelWriterService {
	
	 private static String[] columns = {"FileName", "Service"};
	 
	 public static void writeExcel(List<OperationEntity> entities, String fileName, String sheetName) throws IOException, InvalidFormatException {
		 Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	        // Create a Sheet
	        Sheet sheet = workbook.createSheet(sheetName);

	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());

	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }

	        // Create Other rows and cells with employees data
	        int rowNum = 1;
	        for(OperationEntity employee: entities) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0)
	                    .setCellValue(employee.getFileName());

	            row.createCell(1)
	                    .setCellValue(employee.getOperation());
	        }

			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the output to a file
	        FileOutputStream fileOut = new FileOutputStream(fileName);
	        workbook.write(fileOut);
	        fileOut.close();

	        // Closing the workbook
	        workbook.close();
	    }

}
