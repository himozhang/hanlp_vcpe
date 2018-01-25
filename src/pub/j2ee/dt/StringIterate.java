package pub.j2ee.dt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.io.PoiUtils;
import pub.j2ee.nlp.util.StringTools;

public class StringIterate {

	private static Map<String,String> dt_map = new HashMap<String,String>();
	
	/**
	 * 初试化决策map（清科标签）
	 */
	public static void initDTMap(){
		List<Object[]> list = PoiUtils.xlsxObject("E:/ep/李欣自定义标签划分.xlsx", 1, 3);
		String tag1 = "";
		for(Object obj : list){
			Object[] objs = (Object[])obj;
			if(objs.length==3&&(StringUtils.isNotBlank(objs[0].toString())||StringUtils.isNotBlank(objs[1].toString())||StringUtils.isNotBlank(objs[2].toString()))){
				if(StringUtils.isNotBlank(objs[0].toString())){
					tag1 = objs[0].toString().trim();
					dt_map.put(tag1.toLowerCase(), tag1);
				}
				String tag2 = "";
				if(StringUtils.isNotBlank(objs[1].toString())){
					tag2 = objs[1].toString().trim();
					dt_map.put(tag2.toLowerCase(), tag2+","+tag1);
				}
				if(StringUtils.isNotBlank(objs[2].toString())){
					String tag3 = objs[2].toString().trim();
					if(tag3.contains("、")){
						String[] temp = tag3.split("、");
						for(String ts : temp){
							if(StringUtils.isNotBlank(ts)){
								dt_map.put(ts.trim().toLowerCase(), tag2+","+tag1);
							}
						}
					}else{
						dt_map.put(tag3.toLowerCase(), tag1+","+tag2);
					}
				}
			}
		}
		
		// 打印决策map
		/*for(Map.Entry<String, String> me : dt_map.entrySet()){
			System.out.println(me.getKey()+"->"+me.getValue());
		}*/
	}
	
	/**
	 * 初试化决策map（清科行业标签）
	 */
	public static void initDTIndustryMap(){
		List<Object[]> list = PoiUtils.xlsxObject("E:/ep/李欣行业清科标签划分标准.xlsx", 1, 4);
		String tag1 = "";
		String tag2 = "";
		for(Object obj : list){
			Object[] objs = (Object[])obj;
			if(objs.length==4&&(StringUtils.isNotBlank(objs[0].toString())||StringUtils.isNotBlank(objs[1].toString())||StringUtils.isNotBlank(objs[2].toString())||StringUtils.isNotBlank(objs[3].toString()))){
				if(StringUtils.isNotBlank(objs[0].toString())){
					tag1 = objs[0].toString().trim();
					dt_map.put(tag1.toLowerCase(), tag1);
				}
				if(StringUtils.isNotBlank(objs[1].toString())){
					tag2 = objs[1].toString().trim();
					dt_map.put(tag2.toLowerCase(), tag2+","+tag1);
				}
				String tag3 = "";
				if(StringUtils.isNotBlank(objs[2].toString())){
					tag3 = objs[2].toString().trim();
					dt_map.put(tag3.toLowerCase(),tag3+","+tag2+","+tag1);
				}
				if(StringUtils.isNotBlank(objs[3].toString())){
					String tag4 = objs[3].toString().trim();
					if(tag4.contains("、")){
						String[] temp = tag4.split("、");
						for(String ts : temp){
							if(StringUtils.isNotBlank(ts)){
								dt_map.put(ts.trim().toLowerCase(), tag3+","+tag2+","+tag1);
							}
						}
					}else{
						dt_map.put(tag4.toLowerCase(), tag3+","+tag2+","+tag1);
					}
				}
			}
		}
		
		// 打印决策map
		/*for(Map.Entry<String, String> me : dt_map.entrySet()){
			System.out.println(me.getKey()+"->"+me.getValue());
		}*/
	}
	
	/**
	 * 打标签
	 * @param content
	 * @return
	 */
	public static Set<String> makeTag(String content){
		Set<String> set = new HashSet<String>();
		for(int i=0,j=content.length();i<j;i++){
			if(i+4<j){
				String tag4 = dt_map.get(content.substring(i, i+4).toLowerCase());
				if(tag4!=null){
					if(tag4.contains(",")){
						String[] temp = tag4.split(",");
						for(String s : temp){
							set.add(s);
						}
					}else{
						set.add(tag4);
					}
				}
			}
			if(i+3<j){
				String tag3 = dt_map.get(content.substring(i, i+3).toLowerCase());
				if(tag3!=null){
					if(tag3.contains(",")){
						String[] temp = tag3.split(",");
						for(String s : temp){
							set.add(s);
						}
					}else{
						set.add(tag3);
					}
				}
			}
			if(i+2<j){
				String tag2 = dt_map.get(content.substring(i, i+2).toLowerCase());
				if(tag2!=null){
					if(tag2.contains(",")){
						String[] temp = tag2.split(",");
						for(String s : temp){
							set.add(s);
						}
					}else{
						set.add(tag2);
					}
				}
			}
		}
		return set;
	}
	
	
	
	public static void main(String[] args) {
		//initDTMap();	// 清科标签
		initDTIndustryMap();	// 行业标签维度
		/**
		 * 打标签*/
		List<Object[]> list = PoiUtils.xlsxObject("E:/ep/待打标签企业.xlsx", 1, 2);
		for(Object obj : list){
			Object[] objs = (Object[])obj;
			if(objs[1]!=null){
				Set<String> tags = makeTag(StringTools.textToDesc(objs[1].toString()));
				if(tags.size()>0){
					System.out.printf("%s\t%s\t%s\r\n",objs[0],Arrays.toString(tags.toArray()),StringTools.textToDesc(objs[1].toString()));
				}
			}
		}
		
	}

}
