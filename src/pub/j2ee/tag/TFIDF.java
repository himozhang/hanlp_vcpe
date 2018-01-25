package pub.j2ee.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.io.TxtUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

public class TFIDF {
	private static Map<String,Integer> tagMap = new HashMap<String,Integer>();			// 标签底库
	private static Map<String,Integer> tfMap = new HashMap<String,Integer>();			// 词频
	private static Map<String,Integer> idfMap = new HashMap<String,Integer>();			// 逆向文件频率
	private static Integer fileCount = 70476;											// 文档总数
	private static Integer avgFrequency = 0;											// 平均词频
	static{
		List<String> list = TxtUtils.readList("E:\\hanlp\\data\\dictionary\\custom\\project_frequency.txt");
		int total = 0;
		int sum = 0;
		for(String s : list){
			String[] temp = s.split("[\t]");
			tfMap.put(temp[0],Integer.valueOf(temp[1]));
			total++;
			sum += Integer.valueOf(temp[1]);
		}
		avgFrequency = sum / total;
		
		List<String> itjuziTaglist = TxtUtils.readList("E:\\hanlp\\data\\dictionary\\custom\\project_tag.txt");
		for(String tag : itjuziTaglist){
			String[] temp = tag.split(" ");
			if(StringUtils.isNotBlank(temp[0])){
				tagMap.put(temp[0],1);
			}
		}
		List<String> krTaglist = TxtUtils.readList("E:\\hanlp\\data\\dictionary\\custom\\\\project_36kr_tag.txt");
		for(String tag : krTaglist){
			String[] temp = tag.split(" ");
			if(StringUtils.isNotBlank(temp[0])){
				tagMap.put(temp[0],1);
			}
		}
		
		// 非标签筛选
		tagMap.put("基金", null);
		tagMap.put("私募基金", null);
		tagMap.put("投资", null);
		tagMap.put("有限合伙", null);
		tagMap.put("有限", null);
		tagMap.put("合伙", null);
		tagMap.put("机构", null);
		tagMap.put("投资机构", null);
		tagMap.put("投资人", null);
		tagMap.put("研究", null);
		tagMap.put("论文", null);
	}
	
	public static Float getDF(String word){
		Integer df = tfMap.get(word);
		if(df==null){
			return 1f;
		}else{
			return df.floatValue();
		}
	}
	
	public static Float getTF(Integer total,Integer time){
		return Float.valueOf(time)/total; 
	}
	
	public static Double getIDF(String word){
		return Math.log(fileCount.doubleValue()/getDF(word));
	}
	
	private static Double o = 0.0001;
	private static Integer u = 3000;
	public static Double nddf(Double x){
		Double p1 = Math.pow(Math.E, -1*Math.pow(x-u, 2)/2*Math.pow(o, 2));
		Double p2 = Math.sqrt(2*Math.PI)*o;
		return p1/p2;
	}
	
	public static List<String> getTags(String str){
		List<Term> termList = HanLP.segment(str);
		System.out.println(termList);
		Map<String,Integer> tM = new LinkedHashMap<String,Integer>();
		for(Term t : termList){
			Integer v = tM.get(t.word);
			if(v==null){
				tM.put(t.word, 1);
			}else{
				tM.put(t.word, ++v);
			}
		}
		Map<String,Double> rs = new HashMap<String,Double>();
		for(Term w : termList){
			Double tfnddf = getTF(termList.size(), tM.get(w.word))*nddf(getDF(w.word).doubleValue());
			if(w.nature.name().equals("nn")||w.nature.name().equals("vn"))
			rs.put(w.word, tfnddf.doubleValue());
		}
		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(rs.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        Double t = o1.getValue() - o2.getValue();
		        if(t>0){
		        	return -1;
		        }else if(t<0){
		        	return 1;
		        }else{
		        	return 0;
		        }
		    }
		});
		List<String> tagList = new ArrayList<String>();
		for(Map.Entry<String, Double> tem : infoIds){
			/*if(tagMap.get(tem.getKey())!=null){	//  是候选标签
				//System.out.println(tem.getKey()+":"+tem.getValue());
				tagList.add(tem.getKey());
			}*/
			tagList.add(tem.getKey());
		}
		return tagList;
	}

	public static void main(String[] args){
		List<String> tags = getTags("第二次世界大战结束以后，世界进入了冷战时期。美国率先研发出了毁灭性的武器—核武器，苏联为形成战略制衡，也开始大力发展核武器，于是世界上大国之间开始用核武器来威慑无核国家。现在，世界上大国之间都有了战略性的核武器，都形成了战略核威慑。大家都知道爱因斯坦是一个伟大的科学家，他虽然是犹太人，但是却帮助美国研制出了原子弹，并且当他看到原子弹的威力之后，他又成了第一个反对原子弹的人，他的一生是传奇的一生，他还提出了著名的广义相对论和狭义相对论。曾经有人问他一个问题，如果发生第三次世界大战的话，那么人类的武器会进步到什么程度，而他当时的回答是，他并不知道第三次人类世界大战人类会用什么武器，但是如果发生第四次世界大战的话，那么人们一定是用石头和棍棒战斗的，而很多人都非常的好奇，为什么人类不用各种高精尖的武器反而用石头呢？而他的解释是，如果第三次世界大战的话，那么各国肯定会把自己最厉害的武器拿出来，到时候世界就差不多和毁灭一样了，如果再爆发第四次世界大战的话，因为第三次已经造成了难以挽回的伤害，所以根本就没有什么先进武器了，所以第四次只能用棍棒。第三次世界大战一旦开战必定是全球毁灭性的，所以第四次世界大战就是原始社会的打架而已！");
		for(String s : tags){
			System.out.print(s+'、');
		}
	}
}
