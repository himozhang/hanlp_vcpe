package pub.j2ee.io;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class PoiUtils {

	/**
	 * 获取cellString值
	 * @param xssfCell
	 * @return
	 */
	private static String getStringValue(XSSFCell xssfCell) {
		if(xssfCell == null){
			return "";
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
			return String.valueOf(xssfCell.getStringCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_FORMULA) {
			return String.valueOf(xssfCell.getCTCell().getV());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			if(XSSFDateUtil.isCellDateFormatted(xssfCell)){
				SimpleDateFormat sdf = null;  
                if (xssfCell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                }else if (xssfCell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyyMMdd");  
                }  
                Date date = DateUtil.getJavaDate(xssfCell.getNumericCellValue());  
                return sdf.format(date);
			}else{
				return String.valueOf(xssfCell.getNumericCellValue());
			}
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	
	public static List<String> xlsx(String filePath){
		List<String> list = new ArrayList<String>();
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			Sheet s = wb.getSheetAt(0);
			int rows = s.getPhysicalNumberOfRows();
			for(int i=2;i<rows;i++){
				Row r = s.getRow(i);
				String b1 = getStringValue((XSSFCell)r.getCell(1));
				list.add(b1);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return list;
	}
	
	public static List<String> xlsx(String filePath,int cellIdx){
		List<String> list = new ArrayList<String>();
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			Sheet s = wb.getSheetAt(0);
			int rows = s.getPhysicalNumberOfRows();
			for(int i=2;i<rows;i++){
				Row r = s.getRow(i);
				String b1 = getStringValue((XSSFCell)r.getCell(cellIdx));
				list.add(b1);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return list;
	}
	public static List<String> xlsx(String filePath,int cellIdx,int startRow){
		List<String> list = new ArrayList<String>();
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			Sheet s = wb.getSheetAt(0);
			int rows = s.getPhysicalNumberOfRows();
			for(int i=startRow;i<rows;i++){
				Row r = s.getRow(i);
				String b1 = getStringValue((XSSFCell)r.getCell(cellIdx));
				list.add(b1);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return list;
	}
	
	public static List<Object[]> xlsxObject(String filePath,int startRow,int column){
		List<Object[]> list = new ArrayList<Object[]>();
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			Sheet s = wb.getSheetAt(0);
			int rows = s.getPhysicalNumberOfRows();
			for(int i=startRow;i<rows;i++){
				Row r = s.getRow(i);
				Object[] data = new Object[column];
				for(int m=0;m<column;m++){
					data[m] = getStringValue((XSSFCell)r.getCell(m));
				}
				list.add(data);
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args){
		Set<String> s1 = new HashSet<String>();
		Set<String> s2 = new HashSet<String>();
		List<Object[]> list = PoiUtils.xlsxObject("E:\\mobile.xlsx", 0, 2);
		for(Object obj : list){
			Object[] temp = (Object[])obj;
			s1.add(temp[0].toString());
			s2.add(temp[1].toString());
		}
		s1.removeAll(s2);
		for(String d : s1){
			System.out.println(d);
		}
	}
}
