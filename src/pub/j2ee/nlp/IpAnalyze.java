package pub.j2ee.nlp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import pub.j2ee.io.TxtUtils;

/**
 * ip分析
 * @author pengyuzhang
 * 2018年1月25日
 */
public class IpAnalyze {

	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:\\nginx\\iptable_禁用ip\\res.ip");
		Map<String,Integer> cMap = new HashMap<String,Integer>();
		for(String s : list){
			String[] temp = s.split("\t");
			if(temp.length==2){
				/*List<Term> tList = HanLP.segment(temp[1]);
				String headone = tList.get(0).word;
				String area = "";
				if(headone.contains("省")){
					area = headone.split("省")[0];
				}else if(headone.contains("市")){
					area = headone.split("市")[0];
				}else{
					area = headone;
				}
				Integer i = cMap.get(area);
				if(i==null){
					cMap.put(area, 1);
				}else{
					cMap.put(area, ++i);
				}*/
				
				String [] idc = temp[1].split(" ");
				if(idc.length==2){
					Integer i = cMap.get(idc[1]);
					if(i==null){
						cMap.put(idc[1], 1);
					}else{
						cMap.put(idc[1], ++i);
					}
				}
			}
		}
		
		int total = 0;
		for(Map.Entry<String , Integer> et : cMap.entrySet()){
			total+=et.getValue();
			//System.out.printf("{name: '%s', value: %s},\r\n",et.getKey(),et.getValue());
			System.out.printf("%s\t%s\r\n",et.getKey(),et.getValue());
		}
		System.out.println(total);
	}
}
