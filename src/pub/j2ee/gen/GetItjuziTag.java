package pub.j2ee.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.j2ee.io.TxtUtils;

public class GetItjuziTag {

	public static void main(String[] args){
		Map<String,Boolean> tt = new HashMap<String,Boolean>();
		List<String> list = TxtUtils.readList("E:\\导出标签.txt");
		for(String tag:list){
			String[] temp = tag.split(",");
			for(String s : temp){
				tt.put(s, true);
			}
		}
		
		for(Map.Entry<String, Boolean> et : tt.entrySet()){
			System.out.println(et.getKey());
		}
	}
	
}
