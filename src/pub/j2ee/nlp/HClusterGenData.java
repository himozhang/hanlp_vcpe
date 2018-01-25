package pub.j2ee.nlp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.io.TxtUtils;

/**
 * 分层聚类算法产生数据
 * @author pengyuzhang
 * 2017年9月12日
 */
public class HClusterGenData {

	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:/ep/直播数据.txt");
		Set<String> allTags = new HashSet<String>();
		for(String s : list){
			String[] temp = s.split("\t");
			String key = temp[0];
			String tags = temp[1];
			if(tags.contains(",")){
				String[] t = tags.split(",");
				for(String tt:t){
					if(StringUtils.isNotBlank(tt)){
						allTags.add(tt);
					}
				}
			}else{
				allTags.add(key.trim());
			}
		}
		System.out.print("项目名");
		for(String s : allTags){
			System.out.printf("\t%s", s);
		}
		for(String s : list){
			String[] temp = s.split("\t");
			String key = temp[0];
			String tags = temp[1];
			Map<String,Integer> map = new HashMap<String,Integer>();
			for(String tag : allTags){
				map.put(tag, 0);
			}
			if(tags.contains(",")){
				String[] temp1 = tags.split(",");
				for(String ttt : temp1){
					map.put(ttt, 1);
				}
			}else{
				map.put(tags, 1);
			}
			System.out.print(key);
			for(String key1 : allTags){
				System.out.printf("\t%d", map.get(key1));
			}
			System.out.println();
		}
	}
	
}
