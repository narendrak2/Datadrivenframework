package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import base.Testbase;

public class Excelreader extends Testbase{
	
	
	public String path;
	public static FileInputStream fis=null;
	public static FileOutputStream fileout=null;
	public XSSFWorkbook  workbook=null;
	public XSSFSheet sheet=null;
	public XSSFRow row=null;
	public XSSFCell cell=null;
	
	
	public Excelreader(String path) {
		
		this.path=path;
		
		 try { 
			 fis = new FileInputStream(new File("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/excel/Attendanceapril3232.xlsx")); 

	         // Create Workbook instance holding reference to .xlsx file 
			 XSSFWorkbook  workbook= new XSSFWorkbook(fis); 

	         // Get first/desired sheet from the workbook 
	         XSSFSheet sheet = workbook.getSheetAt(0); 
		      fis.close();
		 }catch(Exception e) {
			 
			 e.getMessage();
		 }
		 }
			    public String getCellData(String sheetName,int colNum,int rowNum)
			    {
			        try
			        {
			            sheet = workbook.getSheet(sheetName);
			            row = sheet.getRow(rowNum);
			            cell = row.getCell(colNum);
			            if(cell.getCellTypeEnum() == CellType.STRING)
			                return cell.getStringCellValue();
			            else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
			            {
			                String cellValue  = String.valueOf(cell.getNumericCellValue());
			                if (HSSFDateUtil.isCellDateFormatted(cell))
			                {
			                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
			                    Date date = cell.getDateCellValue();
			                    cellValue = df.format(date);
			                }
			                return cellValue;
			            }else if(cell.getCellTypeEnum() == CellType.BLANK)
			                return "";
			            else
			                return String.valueOf(cell.getBooleanCellValue());
			        }
			        catch(Exception e)
			        {
			            e.printStackTrace();
			            return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
			        }
			    }


}