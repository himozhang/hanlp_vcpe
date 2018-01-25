package pub.j2ee.tag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pub.j2ee.util.StringTools;

public class GenDicUtils {

	public static void genProjectTitle(){
		try{
			FileInputStream fis = new FileInputStream("E:\\自动打标签\\项目标题.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\自动打标签\\project_title.txt"), "UTF-8"));
			String s = null;
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					if(s.contains("/")){
						String[] temp = s.split("/");
						if(StringUtils.isNotBlank(temp[0])){
							wit.append(StringTools.replaceBlank(temp[0].trim())+" nz 1\r\n");
						}
						if(temp.length==2&&StringUtils.isNotBlank(temp[1])){
							wit.append(StringTools.replaceBlank(temp[1].trim())+" ntc 1\r\n");
						}
					}else{
						wit.append(StringTools.replaceBlank(s.trim())+" nz 1\r\n");
					}
				}
			}
			wit.close();
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public static void genProjectTag(){
		try{
			FileInputStream fis = new FileInputStream("E:\\自动打标签\\项目标签.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\自动打标签\\project_tag.txt"), "UTF-8"));
			String s = null;
			Set<String> tagSet = new HashSet<String>();
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					String[] temp = s.split(",");
					for(String t : temp){
						tagSet.add(t);
					}
				}
			}
			for(String tt : tagSet){
				if(StringUtils.isNotBlank(tt)){
					wit.append(StringTools.replaceBlank(tt.trim())+" nn 1\r\n");
				}
			}
			wit.close();
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public static void gen36krTag(){
		try{
			FileInputStream fis = new FileInputStream("E:\\自动打标签\\36kr标签.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\自动打标签\\project_36kr_tag.txt"), "UTF-8"));
			String s = null;
			Set<String> tagSet = new HashSet<String>();
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					JSONArray jarr = JSONArray.parseArray(s);
					for(Object obj : jarr){
						JSONObject o = (JSONObject)obj;
						tagSet.add(o.getString("name"));
					}
				}
			}
			for(String tt : tagSet){
				if(StringUtils.isNotBlank(tt)){
					wit.append(StringTools.replaceBlank(tt.trim())+" nn 1\r\n");
				}
			}
			wit.close();
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public static void genProjectCompanyName(){
		try{
			FileInputStream fis = new FileInputStream("E:\\自动打标签\\项目公司.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\自动打标签\\project_companyname.txt"), "UTF-8"));
			String s = null;
			Set<String> tagSet = new HashSet<String>();
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					tagSet.add(s);
				}
			}
			for(String tt : tagSet){
				if(StringUtils.isNotBlank(tt)){
					wit.append(StringTools.replaceBlank(tt.trim())+" ntc 1\r\n");
				}
			}
			wit.close();
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		genProjectTitle();
//		genProjectTag();
//		genProjectCompanyName();
		gen36krTag();
	}
}
