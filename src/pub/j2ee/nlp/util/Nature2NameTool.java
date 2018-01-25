package pub.j2ee.nlp.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hankcs.hanlp.seg.common.Term;

/**
 * 词性对照
 * @author pengyuzhang
 * 2017年8月7日
 */
public class Nature2NameTool {
	private static Map<String,String> WORD_MAP_NAME = new HashMap<String,String>();
	
	/**
	 * 获取词性名称对照
	 * @param nature
	 * @return
	 */
	public static String getNatureMapName(String nature){
		return WORD_MAP_NAME.get(nature);
	}
	
	/**
	 * 统计词性分布
	 * @param list
	 */
	public static void StatNature(List<Term> list){
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(Term t : list){
			String type = t.nature.name();
			Integer i = (Integer)map.get(type);
			if(i!=null){
				map.put(type, i+1);
			}else{
				map.put(type, 1);
			}
		}
		System.out.println("######词性分布######");
		for(Entry<String, Integer> ent : map.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue()+":"+Nature2NameTool.getNatureMapName(ent.getKey()));
		}
		System.out.println("########结束#######");
	}
	
	/**
	 * 统计词的分布
	 * @param list
	 */
	public static void StatWord(List<Term> list){
		Map<String,Integer> map = new HashMap<String,Integer>();
		Set<Term> rs = new HashSet<>();
		for(Term t : list){
			String word = t.word;
			Integer i = (Integer)map.get(word);
			if(i!=null){
				map.put(word, i+1);
			}else{
				map.put(word, 1);
				rs.add(t);
			}
		}
		System.out.println("#######词分布######");
		for(Term t : rs){
			System.out.println(t.word+":"+map.get(t.word)+" "+t.nature.name()+":"+getNatureMapName(t.nature.name()));
		}
		System.out.println("########结束#######");
	}
	
	static{
		WORD_MAP_NAME.put("bg", "区别语素");
		WORD_MAP_NAME.put("mg", "数语素");
		WORD_MAP_NAME.put("nl", "名词性惯用语");
		WORD_MAP_NAME.put("nx", "字母专名");
		WORD_MAP_NAME.put("qg", "量词语素");
		WORD_MAP_NAME.put("ud", "助词");
		WORD_MAP_NAME.put("uj", "助词");
		WORD_MAP_NAME.put("uz", "着");
		WORD_MAP_NAME.put("ug", "过");
		WORD_MAP_NAME.put("ul", "连词");
		WORD_MAP_NAME.put("uv", "连词");
		WORD_MAP_NAME.put("yg", "语气语素");
		WORD_MAP_NAME.put("zg", "状态词");
		WORD_MAP_NAME.put("n", "名词");
		WORD_MAP_NAME.put("nr", "人名");
		WORD_MAP_NAME.put("nrj", "日语人名");
		WORD_MAP_NAME.put("nrf", "音译人名");
		WORD_MAP_NAME.put("nr1", "复姓");
		WORD_MAP_NAME.put("nr2", "蒙古姓名");
		WORD_MAP_NAME.put("ns", "地名");
		WORD_MAP_NAME.put("nsf", "音译地名");
		WORD_MAP_NAME.put("nt", "机构团体名");
		WORD_MAP_NAME.put("ntc", "公司名");
		WORD_MAP_NAME.put("ntcf", "工厂");
		WORD_MAP_NAME.put("ntcb", "银行");
		WORD_MAP_NAME.put("ntch", "酒店宾馆");
		WORD_MAP_NAME.put("nto", "政府机构");
		WORD_MAP_NAME.put("ntu", "大学");
		WORD_MAP_NAME.put("nts", "中小学");
		WORD_MAP_NAME.put("nth", "医院");
		WORD_MAP_NAME.put("nh", "医药疾病等健康相关名词");
		WORD_MAP_NAME.put("nhm", "药品");
		WORD_MAP_NAME.put("nhd", "疾病");
		WORD_MAP_NAME.put("nn", "工作相关名词");
		WORD_MAP_NAME.put("nnt", "职务职称");
		WORD_MAP_NAME.put("nnd", "职业");
		WORD_MAP_NAME.put("ng", "名词性语素");
		WORD_MAP_NAME.put("nf", "食品，比如“薯片”");
		WORD_MAP_NAME.put("ni", "机构相关（不是独立机构名）");
		WORD_MAP_NAME.put("nit", "教育相关机构");
		WORD_MAP_NAME.put("nic", "下属机构");
		WORD_MAP_NAME.put("nis", "机构后缀");
		WORD_MAP_NAME.put("nm", "物品名");
		WORD_MAP_NAME.put("nmc", "化学品名");
		WORD_MAP_NAME.put("nb", "生物名");
		WORD_MAP_NAME.put("nba", "动物名");
		WORD_MAP_NAME.put("nbc", "动物纲目");
		WORD_MAP_NAME.put("nbp", "植物名");
		WORD_MAP_NAME.put("nz", "其他专名");
		WORD_MAP_NAME.put("g", "学术词汇");
		WORD_MAP_NAME.put("gm", "数学相关词汇");
		WORD_MAP_NAME.put("gp", "物理相关词汇");
		WORD_MAP_NAME.put("gc", "化学相关词汇");
		WORD_MAP_NAME.put("gb", "生物相关词汇");
		WORD_MAP_NAME.put("gbc", "生物类别");
		WORD_MAP_NAME.put("gg", "地理地质相关词汇");
		WORD_MAP_NAME.put("gi", "计算机相关词汇");
		WORD_MAP_NAME.put("j", "简称略语");
		WORD_MAP_NAME.put("i", "成语");
		WORD_MAP_NAME.put("l", "习用语");
		WORD_MAP_NAME.put("t", "时间词");
		WORD_MAP_NAME.put("tg", "时间词性语素");
		WORD_MAP_NAME.put("s", "处所词");
		WORD_MAP_NAME.put("f", "方位词");
		WORD_MAP_NAME.put("v", "动词");
		WORD_MAP_NAME.put("vd", "副动词");
		WORD_MAP_NAME.put("vn", "名动词");
		WORD_MAP_NAME.put("vshi", "动词“是”");
		WORD_MAP_NAME.put("vyou", "动词“有”");
		WORD_MAP_NAME.put("vf", "趋向动词");
		WORD_MAP_NAME.put("vx", "形式动词");
		WORD_MAP_NAME.put("vi", "不及物动词（内动词）");
		WORD_MAP_NAME.put("vl", "动词性惯用语");
		WORD_MAP_NAME.put("vg", "动词性语素");
		WORD_MAP_NAME.put("a", "形容词");
		WORD_MAP_NAME.put("ad", "副形词");
		WORD_MAP_NAME.put("an", "名形词");
		WORD_MAP_NAME.put("ag", "形容词性语素");
		WORD_MAP_NAME.put("al", "形容词性惯用语");
		WORD_MAP_NAME.put("b", "区别词");
		WORD_MAP_NAME.put("bl", "区别词性惯用语");
		WORD_MAP_NAME.put("z", "状态词");
		WORD_MAP_NAME.put("r", "代词");
		WORD_MAP_NAME.put("rr", "人称代词");
		WORD_MAP_NAME.put("rz", "指示代词");
		WORD_MAP_NAME.put("rzt", "时间指示代词");
		WORD_MAP_NAME.put("rzs", "处所指示代词");
		WORD_MAP_NAME.put("rzv", "谓词性指示代词");
		WORD_MAP_NAME.put("ry", "疑问代词");
		WORD_MAP_NAME.put("ryt", "时间疑问代词");
		WORD_MAP_NAME.put("rys", "处所疑问代词");
		WORD_MAP_NAME.put("ryv", "谓词性疑问代词");
		WORD_MAP_NAME.put("rg", "代词性语素");
		WORD_MAP_NAME.put("Rg", "古汉语代词性语素");
		WORD_MAP_NAME.put("m", "数词");
		WORD_MAP_NAME.put("mq", "数量词");
		WORD_MAP_NAME.put("Mg", "甲乙丙丁之类的数词");
		WORD_MAP_NAME.put("q", "量词");
		WORD_MAP_NAME.put("qv", "动量词");
		WORD_MAP_NAME.put("qt", "时量词");
		WORD_MAP_NAME.put("d", "副词");
		WORD_MAP_NAME.put("dg", "辄,俱,复之类的副词");
		WORD_MAP_NAME.put("dl", "连语");
		WORD_MAP_NAME.put("p", "介词");
		WORD_MAP_NAME.put("pba", "介词“把”");
		WORD_MAP_NAME.put("pbei", "介词“被”");
		WORD_MAP_NAME.put("c", "连词");
		WORD_MAP_NAME.put("cc", "并列连词");
		WORD_MAP_NAME.put("u", "助词");
		WORD_MAP_NAME.put("uzhe", "着");
		WORD_MAP_NAME.put("ule", "了 喽");
		WORD_MAP_NAME.put("uguo", "过");
		WORD_MAP_NAME.put("ude1", "的 底");
		WORD_MAP_NAME.put("ude2", "地");
		WORD_MAP_NAME.put("ude3", "得");
		WORD_MAP_NAME.put("usuo", "所");
		WORD_MAP_NAME.put("udeng", "等 等等 云云");
		WORD_MAP_NAME.put("uyy", "一样 一般 似的 般");
		WORD_MAP_NAME.put("udh", "的话");
		WORD_MAP_NAME.put("uls", "来讲 来说 而言 说来");
		WORD_MAP_NAME.put("uzhi", "之");
		WORD_MAP_NAME.put("ulian", "连 （“连小学生都会”）");
		WORD_MAP_NAME.put("e", "叹词");
		WORD_MAP_NAME.put("y", "语气词(delete yg)");
		WORD_MAP_NAME.put("o", "拟声词");
		WORD_MAP_NAME.put("h", "前缀");
		WORD_MAP_NAME.put("k", "后缀");
		WORD_MAP_NAME.put("x", "字符串");
		WORD_MAP_NAME.put("xx", "非语素字");
		WORD_MAP_NAME.put("xu", "网址URL");
		WORD_MAP_NAME.put("w", "标点符号");
		WORD_MAP_NAME.put("wkz", "左括号，全角：（ 〔  ［  ｛  《 【  〖 〈   半角：( [ { <");
		WORD_MAP_NAME.put("wky", "右括号，全角：） 〕  ］ ｝ 》  】 〗 〉 半角： ) ] { >");
		WORD_MAP_NAME.put("wyz", "左引号，全角：“ ‘ 『");
		WORD_MAP_NAME.put("wyy", "右引号，全角：” ’ 』");
		WORD_MAP_NAME.put("wj", "句号，全角：。");
		WORD_MAP_NAME.put("ww", "问号，全角：？ 半角：?");
		WORD_MAP_NAME.put("wt", "叹号，全角：！ 半角：!");
		WORD_MAP_NAME.put("wd", "逗号，全角：， 半角：,");
		WORD_MAP_NAME.put("wf", "分号，全角：； 半角： ;");
		WORD_MAP_NAME.put("wn", "顿号，全角：、");
		WORD_MAP_NAME.put("wm", "冒号，全角：： 半角： :");
		WORD_MAP_NAME.put("ws", "省略号，全角：……  …");
		WORD_MAP_NAME.put("wp", "破折号，全角：——   －－   ——－   半角：---  ----");
		WORD_MAP_NAME.put("wb", "百分号千分号，全角：％ ‰   半角：%");
		WORD_MAP_NAME.put("wh", "单位符号，全角：￥ ＄ ￡  °  ℃  半角：$");
		WORD_MAP_NAME.put("end", "仅用于终##终，不会出现在分词结果中");
		WORD_MAP_NAME.put("begin", "仅用于始##始，不会出现在分词结果中");
	}
}
