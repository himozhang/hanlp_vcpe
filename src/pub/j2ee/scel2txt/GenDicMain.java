package pub.j2ee.scel2txt;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import pub.j2ee.io.TxtUtils;
import pub.j2ee.util.StringTools;

public class GenDicMain {

	public static void main(String[] args) {
		List<String> dicList = TxtUtils.readList("E:\\hanlp\\三方词库\\电子词汇大全【官方推荐】.txt");
		
		try{
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\hanlp\\三方词库\\electronic_vocabulary.txt"), "UTF-8"));
			for(String dic : dicList){
				wit.append(StringTools.replaceBlank(dic.split(" ")[1])+" nn 1\r\n");
			}
			wit.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}

}
