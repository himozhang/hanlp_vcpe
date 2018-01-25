package pub.j2ee.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class TxtUtils {

	/**
	 * 获取文本数据
	 * @param filePath
	 * @return
	 */
	public static List<String> readList(String filePath){
		List<String> dataList = new ArrayList<String>();
		try{
			FileInputStream fis = new FileInputStream(filePath);   
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			String s = null;
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					dataList.add(s);
				}
			}
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
		return dataList;
	}
	
	public static List<String> readList(String filePath,String charset){
		List<String> dataList = new ArrayList<String>();
		try{
			FileInputStream fis = new FileInputStream(filePath);   
			InputStreamReader isr = new InputStreamReader(fis, charset);
			BufferedReader red = new BufferedReader(isr);
			String s = null;
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					dataList.add(s);
				}
			}
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
		return dataList;
	}
	
	public static void main(String[] args){
		File root = new File("E:\\db");
		for(File file : root.listFiles()){
			List<String> list = readList(file.getAbsolutePath(),"GB2312");
			list.remove(0);
			for(String s : list){
				System.out.println(s);
			}
		}
	}
}
