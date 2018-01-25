package pub.j2ee.nlp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.j2ee.io.PoiUtils;
import pub.j2ee.nlp.util.StringSimilarTool;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

/**
 * 时间去重
 * @author pengyuzhang
 * 2017年8月25日
 */
public class EventUnRepeat {
	
	public static String getValue(Object obj){
		if(obj==null){
			return ";";
		}else{
			return obj.toString()+";";
		}
	}

	public static void testRepeat(List<Object[]> listAll){
		long startTime = System.currentTimeMillis();
		
		System.out.println("待去数量："+listAll.size());
		
		// 设置自定义关键词
		CustomDictionary.insert("投资", "v 1024");
		CustomDictionary.insert("股权", "n 1024");
		CustomDictionary.insert("收购", "v 1024");
		
		// 初试化二维标记数组
		Double[][] sFlag = new Double[listAll.size()][];
		for(int i=0,j=listAll.size();i<j;i++){
			sFlag[i] = new Double[listAll.size()];
		}
		// 两两相似比较
		Map<String,List<Term>> cache = new HashMap<String,List<Term>>();
		for(int i=0,j=listAll.size();i<j;i++){
			StringBuffer sTr0 = new StringBuffer();
			Object[] temp0 = listAll.get(i);
			sTr0.append(getValue(temp0[1])).append(";").append(getValue(temp0[2])).append(getValue(temp0[3])).append(getValue(temp0[5])).append(getValue(temp0[6]));
			List<Term> sList = HanLP.segment(sTr0.toString());
			for(int m=i+1;m<j;m++){
				StringBuffer tTr1 = new StringBuffer();
				Object[] temp1 = listAll.get(m);
				tTr1.append(getValue(temp1[1])).append(";").append(getValue(temp1[2])).append(getValue(temp1[3])).append(getValue(temp1[5])).append(getValue(temp1[6]));
				List<Term> tList = cache.get(tTr1.toString());
				if(tList==null){
					tList = HanLP.segment(tTr1.toString());
					cache.put(tTr1.toString(), tList);
				}
				Double similar = StringSimilarTool.similarEvent(sList, tList);
				sFlag[i][m] = similar;
			}
		}
		// 结果集
		byte[] rs = new byte[listAll.size()];
		for(int i=0,j=rs.length;i<j;i++){
			rs[i] = 1;
		}
		for(int i=0,j=rs.length;i<j;i++){
			Double[] row = sFlag[i];
			for(int m=i+1;m<j;m++){
				if(row[m]>0.9){
					rs[m]=0;
					rs[i]=0;
				}
			}
		}
		// 首页新闻
		for(int i=0,j=rs.length;i<j;i++){
			if(rs[i]==0){
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\r\n",listAll.get(i));
			}
		}
		System.out.println("共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
		System.out.println("### AppTopNewsJob end ###");
		
	}
	
	public static void main(String[] args) {
		List<Object[]> list = PoiUtils.xlsxObject("E:/ep/2015年投资事件.xlsx", 1, 7);
		//System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s",list.get(0));
//		for(Object o : list){
//		}
		testRepeat(list.subList(8000, list.size()-1));
	}
}
