package pub.j2ee.nlp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hankcs.hanlp.seg.common.Term;

/**
 * 去停用词工具类
 * @author pengyuzhang
 * 2017年8月7日
 */
public class DelStopWordTool {
	private static Map<String,Boolean> STOP_WORD_MAP = new HashMap<String,Boolean>();
	private static Map<String,Boolean> STOP_WORD_MAP_EPTAG = new HashMap<String,Boolean>();
	
	/**
	 * 词性
	 * @param nature	是否停用词 false 是停用词，false 不是停用词
	 * @return
	 */
	public static Boolean isStopWord(String nature){
	    Boolean rs = STOP_WORD_MAP.get(nature);
	    if(rs==null){
	    	return false;
	    }else{
	    	return rs;
	    }
	}
	
	/**
	 * 词性
	 * @param nature	是否停用词 false 是停用词，false 不是停用词
	 * @return
	 */
	public static Boolean isStopWordEpTag(String nature){
	    Boolean rs = STOP_WORD_MAP_EPTAG.get(nature);
	    if(rs==null){
	    	return false;
	    }else{
	    	return rs;
	    }
	}
	
	/**
	 * 去除已知列表中的停用词
	 * @param list
	 * @return
	 */
	public static List<Term> removeStopWord(List<Term> list){
		List<Term> rsList = new ArrayList<Term>();
		for(Term t : list){
			if(!isStopWord(t.nature.name())){
				rsList.add(t);
			}
		}
		return rsList;
	}
	
	/**
	 * 去除已知列表中的停用词
	 * @param list
	 * @return
	 */
	public static List<Term> removeStopWordEpTag(List<Term> list){
		List<Term> rsList = new ArrayList<Term>();
		for(Term t : list){
			if(!isStopWordEpTag(t.nature.name())){
				rsList.add(t);
			}
		}
		return rsList;
	}
	
	/**
	 * 去除重复词
	 * @param list
	 * @return
	 */
	public static Set<String> removeRepeatWord(List<Term> list){
		Set<String> rsList = new HashSet<String>();
		for(Term t : list){
			rsList.add(t.word.toLowerCase());
		}
		return rsList;
	}
	
	// 初始化停用词
	static{
		STOP_WORD_MAP.put("bg", false);   //区别语素
		STOP_WORD_MAP.put("mg", false);   //数语素
		STOP_WORD_MAP.put("nl", false);   //名词性惯用语
		STOP_WORD_MAP.put("nx", false);   //字母专名
		STOP_WORD_MAP.put("qg", false);   //量词语素
		STOP_WORD_MAP.put("ud", false);   //助词
		STOP_WORD_MAP.put("uj", false);   //助词
		STOP_WORD_MAP.put("uz", false);   //着
		STOP_WORD_MAP.put("ug", false);   //过
		STOP_WORD_MAP.put("ul", false);   //连词
		STOP_WORD_MAP.put("uv", false);   //连词
		STOP_WORD_MAP.put("yg", false);   //语气语素
		STOP_WORD_MAP.put("zg", false);   //状态词
		STOP_WORD_MAP.put("n", false);   //名词
		STOP_WORD_MAP.put("nr", false);   //人名
		STOP_WORD_MAP.put("nrj", false);   //日语人名
		STOP_WORD_MAP.put("nrf", false);   //音译人名
		STOP_WORD_MAP.put("nr1", false);   //复姓
		STOP_WORD_MAP.put("nr2", false);   //蒙古姓名
		STOP_WORD_MAP.put("ns", false);   //地名
		STOP_WORD_MAP.put("nsf", false);   //音译地名
		STOP_WORD_MAP.put("nt", false);   //机构团体名
		STOP_WORD_MAP.put("ntc", false);   //公司名
		STOP_WORD_MAP.put("ntcf", false);   //工厂
		STOP_WORD_MAP.put("ntcb", false);   //银行
		STOP_WORD_MAP.put("ntch", false);   //酒店宾馆
		STOP_WORD_MAP.put("nto", false);   //政府机构
		STOP_WORD_MAP.put("ntu", false);   //大学
		STOP_WORD_MAP.put("nts", false);   //中小学
		STOP_WORD_MAP.put("nth", false);   //医院
		STOP_WORD_MAP.put("nh", false);   //医药疾病等健康相关名词
		STOP_WORD_MAP.put("nhm", false);   //药品
		STOP_WORD_MAP.put("nhd", false);   //疾病
		STOP_WORD_MAP.put("nn", false);   //工作相关名词
		STOP_WORD_MAP.put("nnt", false);   //职务职称
		STOP_WORD_MAP.put("nnd", false);   //职业
		STOP_WORD_MAP.put("ng", false);   //名词性语素
		STOP_WORD_MAP.put("nf", false);   //食品，比如“薯片”
		STOP_WORD_MAP.put("ni", false);   //机构相关（不是独立机构名）
		STOP_WORD_MAP.put("nit", false);   //教育相关机构
		STOP_WORD_MAP.put("nic", false);   //下属机构
		STOP_WORD_MAP.put("nis", true);   //机构后缀
		STOP_WORD_MAP.put("nm", false);   //物品名
		STOP_WORD_MAP.put("nmc", false);   //化学品名
		STOP_WORD_MAP.put("nb", false);   //生物名
		STOP_WORD_MAP.put("nba", false);   //动物名
		STOP_WORD_MAP.put("nbc", false);   //动物纲目
		STOP_WORD_MAP.put("nbp", false);   //植物名
		STOP_WORD_MAP.put("nz", false);   //其他专名
		STOP_WORD_MAP.put("g", false);   //学术词汇
		STOP_WORD_MAP.put("gm", false);   //数学相关词汇
		STOP_WORD_MAP.put("gp", false);   //物理相关词汇
		STOP_WORD_MAP.put("gc", false);   //化学相关词汇
		STOP_WORD_MAP.put("gb", false);   //生物相关词汇
		STOP_WORD_MAP.put("gbc", false);   //生物类别
		STOP_WORD_MAP.put("gg", false);   //地理地质相关词汇
		STOP_WORD_MAP.put("gi", false);   //计算机相关词汇
		STOP_WORD_MAP.put("j", false);   //简称略语
		STOP_WORD_MAP.put("i", false);   //成语
		STOP_WORD_MAP.put("l", false);   //习用语
		STOP_WORD_MAP.put("t", false);   //时间词
		STOP_WORD_MAP.put("tg", false);   //时间词性语素
		STOP_WORD_MAP.put("s", false);   //处所词
		STOP_WORD_MAP.put("f", false);   //方位词
		STOP_WORD_MAP.put("v", false);   //动词
		STOP_WORD_MAP.put("vd", false);   //副动词
		STOP_WORD_MAP.put("vn", false);   //名动词
		STOP_WORD_MAP.put("vshi", false);   //动词“是”
		STOP_WORD_MAP.put("vyou", false);   //动词“有”
		STOP_WORD_MAP.put("vf", false);   //趋向动词
		STOP_WORD_MAP.put("vx", false);   //形式动词
		STOP_WORD_MAP.put("vi", false);   //不及物动词（内动词）
		STOP_WORD_MAP.put("vl", false);   //动词性惯用语
		STOP_WORD_MAP.put("vg", false);   //动词性语素
		STOP_WORD_MAP.put("a", false);   //形容词
		STOP_WORD_MAP.put("ad", false);   //副形词
		STOP_WORD_MAP.put("an", false);   //名形词
		STOP_WORD_MAP.put("ag", false);   //形容词性语素
		STOP_WORD_MAP.put("al", false);   //形容词性惯用语
		STOP_WORD_MAP.put("b", false);   //区别词
		STOP_WORD_MAP.put("bl", false);   //区别词性惯用语
		STOP_WORD_MAP.put("z", false);   //状态词
		STOP_WORD_MAP.put("r", false);   //代词
		STOP_WORD_MAP.put("rr", false);   //人称代词
		STOP_WORD_MAP.put("rz", false);   //指示代词
		STOP_WORD_MAP.put("rzt", false);   //时间指示代词
		STOP_WORD_MAP.put("rzs", false);   //处所指示代词
		STOP_WORD_MAP.put("rzv", false);   //谓词性指示代词
		STOP_WORD_MAP.put("ry", false);   //疑问代词
		STOP_WORD_MAP.put("ryt", false);   //时间疑问代词
		STOP_WORD_MAP.put("rys", false);   //处所疑问代词
		STOP_WORD_MAP.put("ryv", false);   //谓词性疑问代词
		STOP_WORD_MAP.put("rg", false);   //代词性语素
		STOP_WORD_MAP.put("Rg", false);   //古汉语代词性语素
		STOP_WORD_MAP.put("m", false);   //数词
		STOP_WORD_MAP.put("mq", false);   //数量词
		STOP_WORD_MAP.put("Mg", false);   //甲乙丙丁之类的数词
		STOP_WORD_MAP.put("q", false);   //量词
		STOP_WORD_MAP.put("qv", false);   //动量词
		STOP_WORD_MAP.put("qt", false);   //时量词
		STOP_WORD_MAP.put("d", false);   //副词
		STOP_WORD_MAP.put("dg", false);   //辄,俱,复之类的副词
		STOP_WORD_MAP.put("dl", false);   //连语
		STOP_WORD_MAP.put("p", false);   //介词
		STOP_WORD_MAP.put("pba", false);   //介词“把”
		STOP_WORD_MAP.put("pbei", false);   //介词“被”
		STOP_WORD_MAP.put("c", false);   //连词
		STOP_WORD_MAP.put("cc", false);   //并列连词
		STOP_WORD_MAP.put("u", false);   //助词
		STOP_WORD_MAP.put("uzhe", false);   //着
		STOP_WORD_MAP.put("ule", false);   //了 喽
		STOP_WORD_MAP.put("uguo", false);   //过
		STOP_WORD_MAP.put("ude1", false);   //的 底
		STOP_WORD_MAP.put("ude2", false);   //地
		STOP_WORD_MAP.put("ude3", false);   //得
		STOP_WORD_MAP.put("usuo", false);   //所
		STOP_WORD_MAP.put("udeng", false);   //等 等等 云云
		STOP_WORD_MAP.put("uyy", false);   //一样 一般 似的 般
		STOP_WORD_MAP.put("udh", false);   //的话
		STOP_WORD_MAP.put("uls", false);   //来讲 来说 而言 说来
		STOP_WORD_MAP.put("uzhi", false);   //之
		STOP_WORD_MAP.put("ulian", false);   //连 （“连小学生都会”）
		STOP_WORD_MAP.put("e", false);   //叹词
		STOP_WORD_MAP.put("y", false);   //语气词(delete yg)
		STOP_WORD_MAP.put("o", false);   //拟声词
		STOP_WORD_MAP.put("h", false);   //前缀
		STOP_WORD_MAP.put("k", false);   //后缀
		STOP_WORD_MAP.put("x", false);   //字符串
		STOP_WORD_MAP.put("xx", false);   //非语素字
		STOP_WORD_MAP.put("xu", false);   //网址URL
		STOP_WORD_MAP.put("w", true);   //标点符号
		STOP_WORD_MAP.put("wkz", true);   //左括号，全角：（ 〔  ［  ｛  《 【  〖 〈   半角：( [ { <
		STOP_WORD_MAP.put("wky", true);   //右括号，全角：） 〕  ］ ｝ 》  】 〗 〉 半角： ) ] { >
		STOP_WORD_MAP.put("wyz", true);   //左引号，全角：“ ‘ 『
		STOP_WORD_MAP.put("wyy", true);   //右引号，全角：” ’ 』
		STOP_WORD_MAP.put("wj", true);   //句号，全角：。
		STOP_WORD_MAP.put("ww", true);   //问号，全角：？ 半角：?
		STOP_WORD_MAP.put("wt", true);   //叹号，全角：！ 半角：!
		STOP_WORD_MAP.put("wd", true);   //逗号，全角：， 半角：,
		STOP_WORD_MAP.put("wf", true);   //分号，全角：； 半角： ;
		STOP_WORD_MAP.put("wn", true);   //顿号，全角：、
		STOP_WORD_MAP.put("wm", true);   //冒号，全角：： 半角： :
		STOP_WORD_MAP.put("ws", true);   //省略号，全角：……  …
		STOP_WORD_MAP.put("wp", true);   //破折号，全角：——   －－   ——－   半角：---  ----
		STOP_WORD_MAP.put("wb", true);   //百分号千分号，全角：％ ‰   半角：%
		STOP_WORD_MAP.put("wh", true);   //单位符号，全角：￥ ＄ ￡  °  ℃  半角：$
		STOP_WORD_MAP.put("end", false);   //仅用于终##终，不会出现在分词结果中
		STOP_WORD_MAP.put("begin", false);   //仅用于始##始，不会出现在分词结果中
		
		STOP_WORD_MAP_EPTAG.put("bg", false);   //区别语素
		STOP_WORD_MAP_EPTAG.put("mg", true);   //数语素
		STOP_WORD_MAP_EPTAG.put("nl", false);   //名词性惯用语
		STOP_WORD_MAP_EPTAG.put("nx", false);   //字母专名
		STOP_WORD_MAP_EPTAG.put("qg", false);   //量词语素
		STOP_WORD_MAP_EPTAG.put("ud", true);   //助词
		STOP_WORD_MAP_EPTAG.put("uj", true);   //助词
		STOP_WORD_MAP_EPTAG.put("uz", true);   //着
		STOP_WORD_MAP_EPTAG.put("ug", true);   //过
		STOP_WORD_MAP_EPTAG.put("ul", true);   //连词
		STOP_WORD_MAP_EPTAG.put("uv", true);   //连词
		STOP_WORD_MAP_EPTAG.put("yg", false);   //语气语素
		STOP_WORD_MAP_EPTAG.put("zg", false);   //状态词
		STOP_WORD_MAP_EPTAG.put("n", false);   //名词
		STOP_WORD_MAP_EPTAG.put("nr", true);   //人名
		STOP_WORD_MAP_EPTAG.put("nrj", true);   //日语人名
		STOP_WORD_MAP_EPTAG.put("nrf", true);   //音译人名
		STOP_WORD_MAP_EPTAG.put("nr1", true);   //复姓
		STOP_WORD_MAP_EPTAG.put("nr2", true);   //蒙古姓名
		STOP_WORD_MAP_EPTAG.put("ns", true);   //地名
		STOP_WORD_MAP_EPTAG.put("nsf", true);   //音译地名
		STOP_WORD_MAP_EPTAG.put("nt", true);   //机构团体名
		STOP_WORD_MAP_EPTAG.put("ntc", true);   //公司名
		STOP_WORD_MAP_EPTAG.put("ntcf", true);   //工厂
		STOP_WORD_MAP_EPTAG.put("ntcb", true);   //银行
		STOP_WORD_MAP_EPTAG.put("ntch", true);   //酒店宾馆
		STOP_WORD_MAP_EPTAG.put("nto", true);   //政府机构
		STOP_WORD_MAP_EPTAG.put("ntu", true);   //大学
		STOP_WORD_MAP_EPTAG.put("nts", true);   //中小学
		STOP_WORD_MAP_EPTAG.put("nth", false);   //医院
		STOP_WORD_MAP_EPTAG.put("nh", false);   //医药疾病等健康相关名词
		STOP_WORD_MAP_EPTAG.put("nhm", false);   //药品
		STOP_WORD_MAP_EPTAG.put("nhd", false);   //疾病
		STOP_WORD_MAP_EPTAG.put("nn", false);   //工作相关名词
		STOP_WORD_MAP_EPTAG.put("nnt", false);   //职务职称
		STOP_WORD_MAP_EPTAG.put("nnd", false);   //职业
		STOP_WORD_MAP_EPTAG.put("ng", false);   //名词性语素
		STOP_WORD_MAP_EPTAG.put("nf", false);   //食品，比如“薯片”
		STOP_WORD_MAP_EPTAG.put("ni", false);   //机构相关（不是独立机构名）
		STOP_WORD_MAP_EPTAG.put("nit", false);   //教育相关机构
		STOP_WORD_MAP_EPTAG.put("nic", false);   //下属机构
		STOP_WORD_MAP_EPTAG.put("nis", true);   //机构后缀
		STOP_WORD_MAP_EPTAG.put("nm", false);   //物品名
		STOP_WORD_MAP_EPTAG.put("nmc", false);   //化学品名
		STOP_WORD_MAP_EPTAG.put("nb", false);   //生物名
		STOP_WORD_MAP_EPTAG.put("nba", false);   //动物名
		STOP_WORD_MAP_EPTAG.put("nbc", false);   //动物纲目
		STOP_WORD_MAP_EPTAG.put("nbp", false);   //植物名
		STOP_WORD_MAP_EPTAG.put("nz", true);   //其他专名
		STOP_WORD_MAP_EPTAG.put("g", false);   //学术词汇
		STOP_WORD_MAP_EPTAG.put("gm", false);   //数学相关词汇
		STOP_WORD_MAP_EPTAG.put("gp", false);   //物理相关词汇
		STOP_WORD_MAP_EPTAG.put("gc", false);   //化学相关词汇
		STOP_WORD_MAP_EPTAG.put("gb", false);   //生物相关词汇
		STOP_WORD_MAP_EPTAG.put("gbc", false);   //生物类别
		STOP_WORD_MAP_EPTAG.put("gg", false);   //地理地质相关词汇
		STOP_WORD_MAP_EPTAG.put("gi", false);   //计算机相关词汇
		STOP_WORD_MAP_EPTAG.put("j", false);   //简称略语
		STOP_WORD_MAP_EPTAG.put("i", false);   //成语
		STOP_WORD_MAP_EPTAG.put("l", false);   //习用语
		STOP_WORD_MAP_EPTAG.put("t", true);   //时间词
		STOP_WORD_MAP_EPTAG.put("tg", true);   //时间词性语素
		STOP_WORD_MAP_EPTAG.put("s", true);   //处所词
		STOP_WORD_MAP_EPTAG.put("f", true);   //方位词
		STOP_WORD_MAP_EPTAG.put("v", true);   //动词
		STOP_WORD_MAP_EPTAG.put("vd", false);   //副动词
		STOP_WORD_MAP_EPTAG.put("vn", false);   //名动词
		STOP_WORD_MAP_EPTAG.put("vshi", true);   //动词“是”
		STOP_WORD_MAP_EPTAG.put("vyou", true);   //动词“有”
		STOP_WORD_MAP_EPTAG.put("vf", true);   //趋向动词
		STOP_WORD_MAP_EPTAG.put("vx", true);   //形式动词
		STOP_WORD_MAP_EPTAG.put("vi", true);   //不及物动词（内动词）
		STOP_WORD_MAP_EPTAG.put("vl", true);   //动词性惯用语
		STOP_WORD_MAP_EPTAG.put("vg", true);   //动词性语素
		STOP_WORD_MAP_EPTAG.put("a", true);   //形容词
		STOP_WORD_MAP_EPTAG.put("ad", true);   //副形词
		STOP_WORD_MAP_EPTAG.put("an", true);   //名形词
		STOP_WORD_MAP_EPTAG.put("ag", true);   //形容词性语素
		STOP_WORD_MAP_EPTAG.put("al", true);   //形容词性惯用语
		STOP_WORD_MAP_EPTAG.put("b", true);   //区别词
		STOP_WORD_MAP_EPTAG.put("bl", true);   //区别词性惯用语
		STOP_WORD_MAP_EPTAG.put("z", true);   //状态词
		STOP_WORD_MAP_EPTAG.put("r", false);   //代词
		STOP_WORD_MAP_EPTAG.put("rr", false);   //人称代词
		STOP_WORD_MAP_EPTAG.put("rz", false);   //指示代词
		STOP_WORD_MAP_EPTAG.put("rzt", false);   //时间指示代词
		STOP_WORD_MAP_EPTAG.put("rzs", false);   //处所指示代词
		STOP_WORD_MAP_EPTAG.put("rzv", false);   //谓词性指示代词
		STOP_WORD_MAP_EPTAG.put("ry", false);   //疑问代词
		STOP_WORD_MAP_EPTAG.put("ryt", false);   //时间疑问代词
		STOP_WORD_MAP_EPTAG.put("rys", false);   //处所疑问代词
		STOP_WORD_MAP_EPTAG.put("ryv", false);   //谓词性疑问代词
		STOP_WORD_MAP_EPTAG.put("rg", false);   //代词性语素
		STOP_WORD_MAP_EPTAG.put("Rg", false);   //古汉语代词性语素
		STOP_WORD_MAP_EPTAG.put("m", true);   //数词
		STOP_WORD_MAP_EPTAG.put("mq", true);   //数量词
		STOP_WORD_MAP_EPTAG.put("Mg", true);   //甲乙丙丁之类的数词
		STOP_WORD_MAP_EPTAG.put("q", true);   //量词
		STOP_WORD_MAP_EPTAG.put("qv", true);   //动量词
		STOP_WORD_MAP_EPTAG.put("qt", true);   //时量词
		STOP_WORD_MAP_EPTAG.put("d", true);   //副词
		STOP_WORD_MAP_EPTAG.put("dg", true);   //辄,俱,复之类的副词
		STOP_WORD_MAP_EPTAG.put("dl", true);   //连语
		STOP_WORD_MAP_EPTAG.put("p", true);   //介词
		STOP_WORD_MAP_EPTAG.put("pba", true);   //介词“把”
		STOP_WORD_MAP_EPTAG.put("pbei", true);   //介词“被”
		STOP_WORD_MAP_EPTAG.put("c", false);   //连词
		STOP_WORD_MAP_EPTAG.put("cc", false);   //并列连词
		STOP_WORD_MAP_EPTAG.put("u", false);   //助词
		STOP_WORD_MAP_EPTAG.put("uzhe", false);   //着
		STOP_WORD_MAP_EPTAG.put("ule", false);   //了 喽
		STOP_WORD_MAP_EPTAG.put("uguo", false);   //过
		STOP_WORD_MAP_EPTAG.put("ude1", false);   //的 底
		STOP_WORD_MAP_EPTAG.put("ude2", false);   //地
		STOP_WORD_MAP_EPTAG.put("ude3", false);   //得
		STOP_WORD_MAP_EPTAG.put("usuo", false);   //所
		STOP_WORD_MAP_EPTAG.put("udeng", false);   //等 等等 云云
		STOP_WORD_MAP_EPTAG.put("uyy", false);   //一样 一般 似的 般
		STOP_WORD_MAP_EPTAG.put("udh", false);   //的话
		STOP_WORD_MAP_EPTAG.put("uls", false);   //来讲 来说 而言 说来
		STOP_WORD_MAP_EPTAG.put("uzhi", false);   //之
		STOP_WORD_MAP_EPTAG.put("ulian", false);   //连 （“连小学生都会”）
		STOP_WORD_MAP_EPTAG.put("e", false);   //叹词
		STOP_WORD_MAP_EPTAG.put("y", false);   //语气词(delete yg)
		STOP_WORD_MAP_EPTAG.put("o", false);   //拟声词
		STOP_WORD_MAP_EPTAG.put("h", false);   //前缀
		STOP_WORD_MAP_EPTAG.put("k", false);   //后缀
		STOP_WORD_MAP_EPTAG.put("x", false);   //字符串
		STOP_WORD_MAP_EPTAG.put("xx", false);   //非语素字
		STOP_WORD_MAP_EPTAG.put("xu", false);   //网址URL
		STOP_WORD_MAP_EPTAG.put("w", true);   //标点符号
		STOP_WORD_MAP_EPTAG.put("wkz", true);   //左括号，全角：（ 〔  ［  ｛  《 【  〖 〈   半角：( [ { <
		STOP_WORD_MAP_EPTAG.put("wky", true);   //右括号，全角：） 〕  ］ ｝ 》  】 〗 〉 半角： ) ] { >
		STOP_WORD_MAP_EPTAG.put("wyz", true);   //左引号，全角：“ ‘ 『
		STOP_WORD_MAP_EPTAG.put("wyy", true);   //右引号，全角：” ’ 』
		STOP_WORD_MAP_EPTAG.put("wj", true);   //句号，全角：。
		STOP_WORD_MAP_EPTAG.put("ww", true);   //问号，全角：？ 半角：?
		STOP_WORD_MAP_EPTAG.put("wt", true);   //叹号，全角：！ 半角：!
		STOP_WORD_MAP_EPTAG.put("wd", true);   //逗号，全角：， 半角：,
		STOP_WORD_MAP_EPTAG.put("wf", true);   //分号，全角：； 半角： ;
		STOP_WORD_MAP_EPTAG.put("wn", true);   //顿号，全角：、
		STOP_WORD_MAP_EPTAG.put("wm", true);   //冒号，全角：： 半角： :
		STOP_WORD_MAP_EPTAG.put("ws", true);   //省略号，全角：……  …
		STOP_WORD_MAP_EPTAG.put("wp", true);   //破折号，全角：——   －－   ——－   半角：---  ----
		STOP_WORD_MAP_EPTAG.put("wb", true);   //百分号千分号，全角：％ ‰   半角：%
		STOP_WORD_MAP_EPTAG.put("wh", true);   //单位符号，全角：￥ ＄ ￡  °  ℃  半角：$
		STOP_WORD_MAP_EPTAG.put("end", false);   //仅用于终##终，不会出现在分词结果中
		STOP_WORD_MAP_EPTAG.put("begin", false);   //仅用于始##始，不会出现在分词结果中
	}
}
