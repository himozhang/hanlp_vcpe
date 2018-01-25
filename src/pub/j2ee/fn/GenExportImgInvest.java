package pub.j2ee.fn;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;

import pub.j2ee.io.PoiUtils;
import pub.j2ee.nlp.util.StringTools;

public class GenExportImgInvest {

	public static void main(String[] args) {
//		List<Object[]> list = PoiUtils.xlsxObject("E:\\111.xlsx", 0, 2);
//		for(Object[] objs : list){
//			String name = (String)objs[0];
//			String path = (String)objs[1];
//			String ext = path.substring(path.lastIndexOf("."));
//			System.out.printf("cp /zdb/zdbupload/pedata-app/%s /var/www/app/export_img/%s%s\n",path,URLEncoder.encode(StringTools.replaceExceptFileName(name.trim())),ext);
//		}
		
		/*
		File root = new File("E:/export_img/export_img");
		for(File file : root.listFiles()){
			//System.out.println(file.getAbsolutePath());
			//System.out.println(file.getName());
			String path = file.getPath();
			String tp = path.substring(0,path.lastIndexOf("\\"));
			try{
				FileUtils.copyFile(file, new File(tp+"\\a\\"+URLDecoder.decode(file.getName())));
			}catch(Exception err){
			}
		}*/
	}

}
