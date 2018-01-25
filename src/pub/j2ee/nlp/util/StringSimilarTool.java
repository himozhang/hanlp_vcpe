package pub.j2ee.nlp.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

/**
 * 字符串相似比较工具
 * @author pengyuzhang
 * 2017年8月7日
 */
public class StringSimilarTool {

	/**
	 * 相似度比较函数
	 * @param source
	 * @param target
	 * @return 相似度 		越大相似度越高 0~1
	 */
	public static Double similar(String source,String target){
		List<Term> sList = NLPTokenizer.segment(source);
		Set<String> sSet = DelStopWordTool.removeRepeatWord(sList);
		
		List<Term> tList = NLPTokenizer.segment(target);
		Set<String> tSet = DelStopWordTool.removeRepeatWord(tList);
		
		// 分词交集
		Set<String> andSet = new HashSet<String>();
		andSet.addAll(sSet);
		andSet.retainAll(tSet);
		
		Double sDegree = Double.valueOf(andSet.size())/sSet.size();		// 原字符串相似度
		Double tDegree = Double.valueOf(andSet.size())/tSet.size();		// 新字符串相似度
	
		return (sDegree+tDegree)/2;
	}
	
	/**
	 * 相似度比较函数
	 * @param source
	 * @param target
	 * @return 相似度 		越大相似度越高 0~1
	 */
	public static Double similarEvent(String source,String target){
		CustomDictionary.insert("投资", "v 1024");
		CustomDictionary.insert("股权", "n 1024");
		CustomDictionary.insert("收购", "v 1024");
		List<Term> sList = HanLP.segment(source);
		Set<String> sSet = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWord(sList));
		sSet.remove("投资");
		sSet.remove("股权");
		sSet.remove("收购");
		
		List<Term> tList = HanLP.segment(target);
		Set<String> tSet = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWord(tList));
		tSet.remove("投资");
		tSet.remove("股权");
		tSet.remove("收购");
		
		// 分词交集
		Set<String> andSet = new HashSet<String>();
		andSet.addAll(sSet);
		andSet.retainAll(tSet);
		
		Double sDegree = Double.valueOf(andSet.size())/sSet.size();		// 原字符串相似度
		Double tDegree = Double.valueOf(andSet.size())/tSet.size();		// 新字符串相似度
	
		return (sDegree+tDegree)/2;
	}
	
	/**
	 * 相似度比较函数
	 * @param source
	 * @param target
	 * @return 相似度 		越大相似度越高 0~1
	 */
	public static Double similarEvent(List<Term> sList,List<Term> tList){
		Set<String> sSet = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWord(sList));
		sSet.remove("投资");
		sSet.remove("股权");
		sSet.remove("收购");
		Set<String> tSet = DelStopWordTool.removeRepeatWord(DelStopWordTool.removeStopWord(tList));
		tSet.remove("投资");
		tSet.remove("股权");
		tSet.remove("收购");
		// 分词交集
		Set<String> andSet = new HashSet<String>();
		andSet.addAll(sSet);
		andSet.retainAll(tSet);
		
		Double sDegree = Double.valueOf(andSet.size())/sSet.size();		// 原字符串相似度
		Double tDegree = Double.valueOf(andSet.size())/tSet.size();		// 新字符串相似度
	
		return (sDegree+tDegree)/2;
	}
	
	public static void main(String[] args){
		System.out.println(similarEvent("江苏恒丰波纹管有限公司","江苏恒丰波纹管重庆有限公司"));
	}
}
