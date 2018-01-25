package pub.j2ee.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.io.TxtUtils;

public class ItjuziTagGen {

	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:\\itjuzi标签\\IT桔子标签导出.txt");
		Map<String,Integer> st = new HashMap<String,Integer>();
		for(String tags : list){
			if(StringUtils.isNotBlank(tags)){
				String[] tArr = tags.split(",");
				for(String s : tArr){
					if(StringUtils.isNotBlank(s)){
						Integer c = st.get(s.trim());
						if(c==null){
							st.put(s.trim(), 1);
						}else{
							st.put(s.trim(), c+1);
						}
					}
				}
			}
		}
		for(Map.Entry<String, Integer> ent : st.entrySet()){
			System.out.printf("%s\t%s\r\n",ent.getKey(),ent.getValue());
		}
	}
	
}
