package pub.j2ee.nlp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.io.PoiUtils;
import pub.j2ee.nlp.util.DelStopWordTool;
import pub.j2ee.nlp.util.StringTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

/**
 * 企业自动打标签
 * @author pengyuzhang
 * 2017年9月5日
 */
public class ItjuziGenEpTag {
	class Tag{
		private String tagName;
		private Double grade;
		
		public String getTagName() {
			return tagName;
		}
		public Double getGrade() {
			return grade;
		}
		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
		public void setGrade(Double grade) {
			this.grade = grade;
		}
		@Override
		public String toString() {
			return tagName+"("+grade+")";
		}
	}

	/**
	 * 标签统计信息
	 */
	public static void genTagStat(){
		List<Object[]> list = PoiUtils.xlsxObject("E:\\ep\\itjuzi企业信息.xlsx", 1, 3);
		Map<String,Map<String,Integer>> fc = new HashMap<String,Map<String,Integer>>(); 
		for(int i=0,j=list.size(); i<j ; i++){
			Object[] obj = list.get(i);
			StringBuffer buff = new StringBuffer("");
			if(obj[0]!=null){
				buff.append(obj[0].toString());
			}
			if(obj[1]!=null){
				buff.append(StringTools.textToDesc(obj[2].toString()));
			}
			String tag = obj[1].toString();
			List<Term> termList = HanLP.segment(buff.toString());
			Set<String> s = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWordEpTag(termList));
			for(String word : s){
				if(word.length()==1||StringUtils.isNumeric(word)){
					continue;
				}
				Map<String,Integer> valueMap = fc.get(word);
				if(valueMap==null){
					valueMap = new HashMap<String,Integer>();
					fc.put(word, valueMap);
				}
				if(tag.contains(",")){
					String[] tempTags = tag.split(",");
					for(String tt : tempTags){
						Integer value = valueMap.get(tt);
						if(value!=null){
							value++;
							valueMap.put(tt, value);
						}else{
							valueMap.put(tt, 1);
						}
					}
				}else{
					Integer value = valueMap.get(tag);
					if(value!=null){
						value++;
						valueMap.put(tag, value);
					}else{
						valueMap.put(tag, 1);
					}
				}
			}
		}
		for(Map.Entry<String, Map<String,Integer>> w : fc.entrySet()){
			boolean f = false;
			for(Map.Entry<String,Integer> w1 : w.getValue().entrySet()){
				if(w1.getValue()>1){
					f = true;
					break;
				}
			}
			if(f){
				System.out.println(w.getKey()+"\t"+JSONObject.toJSON(w.getValue()));
			}
		}
	}
	
	/**
	 * 加载计算字典
	 */
	public static Map<String,Map<String,Double>> loadDic(){
		Map<String,Map<String,Double>> fc = new HashMap<String,Map<String,Double>>();
		try{
			FileInputStream fis = new FileInputStream("E:\\ep\\itjuzi_ep_tag_dic.txt");   
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			String s = null;
			while((s=red.readLine())!=null){
				String[] temp = s.split("\t");
				if(temp.length>1){
					fc.put(temp[0], JSON.parseObject(temp[1], new TypeReference<Map<String, Double>>(){}));
				}
			}
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
		calculate(fc);
		return fc;
	}
	
	/**
	 * 计算关键词出现对应标签权重值 值范围为 0~1之间
	 * @param fc
	 */
	public static void calculate(Map<String,Map<String,Double>> fc){
		for(Map.Entry<String, Map<String,Double>> word : fc.entrySet()){
			Double total = 0d;
			for(Map.Entry<String, Double> tag : word.getValue().entrySet()){
				total += tag.getValue();
			}
			for(Map.Entry<String, Double> tag : word.getValue().entrySet()){
				tag.setValue(tag.getValue()/total);
			}
		}
	}
	
	/**
	 * 合并标签统计值
	 * @param source
	 * @param target
	 */
	public static void mergeValue(Map<String,Double> source,Map<String,Double> target){
		if(target==null){
			return;
		}
		for(Map.Entry<String, Double> ent : target.entrySet()){
			Double v = source.get(ent.getKey());
			if(v==null){
				source.put(ent.getKey(), ent.getValue());
			}else{
				source.put(ent.getKey(), ent.getValue()+v);
			}
		}
		Double total = 0d;
		for(Map.Entry<String, Double> s : source.entrySet()){
			total+=s.getValue();
		}
		for(Map.Entry<String, Double> s : source.entrySet()){
			s.setValue(s.getValue()/total);
		}
	}
	
	/**
	 * 计算并打印标签
	 * @param target
	 * @param threshold
	 */
	public static List<Tag> printTag(Map<String,Double> target,Integer size){
		List<Tag> tList = new ArrayList<Tag>();
		for(Map.Entry<String, Double> m : target.entrySet()){
			Tag t = new ItjuziGenEpTag().new Tag();
			t.setTagName(m.getKey());
			t.setGrade(m.getValue());
			tList.add(t);
		}
		Collections.sort(tList, new Comparator<Tag>() {
			@Override
			public int compare(Tag o1, Tag o2) {
				Double e = o2.getGrade()-o1.getGrade();
				if(e>0){
					return 1;
				}else if(e<0){
					return -1;
				}else{
					return 0;
				}
			}
		});
		return tList.subList(0, tList.size()<size?tList.size():size);
	}
	
	public static void genTag(){
		Map<String,Map<String,Double>> fc = loadDic();
		List<Object[]> list = PoiUtils.xlsxObject("E:\\ep\\待打标签企业.xlsx", 1, 2);
		for(Object[] obj : list.subList(0, 2000)){
			List<Term> termList =  HanLP.segment(obj[0].toString()+obj[1].toString());
			Set<String> words = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWordEpTag(termList));
			Map<String,Double> total = new HashMap<String,Double>();
			for(String w : words){
				Map<String,Double> v = fc.get(w);
				if(v!=null){
					mergeValue(total,v);
				}
			}
			List<Tag> tagList = printTag(total,5);
			System.out.printf("%s\t%s\t%s\r\n",obj[0],Arrays.toString(tagList.toArray()),StringTools.textToDesc(obj[1].toString()));
		}
		
	}
	
	public static void main(String[] args){
		//genTagStat();
		genTag();
	}
	
	
}
