package pub.j2ee.nlp.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.j2ee.io.PoiUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

/**
 * 神经元词汇打分
 * @author pengyuzhang
 * 2017年8月11日
 */
public class NNScore {

	private static Map<String,Integer> scoreMap = new HashMap<String,Integer>();
	
	static{
		scoreMap.put("私募通", 10);
		scoreMap.put("投资", 5);
		scoreMap.put("融资", 5);
		scoreMap.put("获得", 2);
		scoreMap.put("获", 2);
		scoreMap.put("轮", 3);
		scoreMap.put("千万", 1);
		scoreMap.put("万元", 1);
		scoreMap.put("美元", 1);
		scoreMap.put("亿元", 1);
		scoreMap.put("将", 1);
		
		scoreMap.put("贬值", -5);
		scoreMap.put("租赁", -5);
		scoreMap.put("新政", -5);
		scoreMap.put("收录", -5);
		scoreMap.put("政策", -5);
		scoreMap.put("速递", -5);
		scoreMap.put("解读", -5);
		scoreMap.put("精华", -5);
		scoreMap.put("报告", -5);
		scoreMap.put("速报", -5);
		scoreMap.put("大事件", -5);
		scoreMap.put("周", -5);
		scoreMap.put("篇", -2);
		scoreMap.put("创投", -2);
		scoreMap.put("投资资", -2);
		scoreMap.put("项目", -2);
		scoreMap.put("汇", -2);
	}
	
	public static Integer getScore(List<Term> list){
		Integer total = 0;
		for(Term t : list){
			Integer n = scoreMap.get(t.word);
			if(n!=null){
				total = total + n;
			}
		}
		return total;
	}
	
	public static void main(String[] args){
		List<String> list = PoiUtils.xlsx("E:\\hanlp\\20170814t_news.xlsx");
		CustomDictionary.insert("私募通", "nz 1024");
		for(String s : list.subList(0, 10)){
			List<Term> termList1 = HanLP.segment(s);
			if(getScore(termList1)>5){
				System.out.println(s);
			}
		}
	}
}
