package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String filepath;
	
	public ExcelUtility(String path){
		this.filepath=path;
		}
	
	public int getRowCount(String sheetName) throws IOException {
		fis=new FileInputStream(filepath);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum(); //index of last row
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException{
		fis=new FileInputStream(filepath);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName); 
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum(); // count of cells in the row
		workbook.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fis=new FileInputStream(filepath);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlfile=new File(filepath);
		if(!xlfile.exists()) {
			workbook=new XSSFWorkbook();
			fos=new FileOutputStream(filepath);
			workbook.write(fos);
		}
		
		fis=new FileInputStream(filepath);
		workbook=new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
		}
		
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null) {
			sheet.createRow(rowNum);
		}
		
		row=sheet.getRow(rowNum);
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		
		fos=new FileOutputStream(filepath);
		workbook.write(fos);
		
		workbook.close();
		fis.close();
		fos.close();
		}
		
	}
	
	


   