package pub.j2ee.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pub.j2ee.nlp.util.DelStopWordTool;
import pub.j2ee.nlp.util.Nature2NameTool;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class WebSpider {

	public static Document getContent(String url){
		Document doc = null;
		try{
			doc = Jsoup.connect(url)
					.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36 OPR/37.0.2178.43")
					.header("Cookie","xp_ci=3z2f0gEvz62Wz4tdzBdczfM8Ijddj; s_cc=true")
					.get();
		}catch(Exception err){
			err.printStackTrace();
		}
		return doc;
	}
	
	public static void main(String[] args){
		String u = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6899&query=%E5%A4%B1%E4%BF%A1%E8%A2%AB%E6%89%A7%E8%A1%8C%E4%BA%BA&pn=20&rn=10&ie=utf-8&oe=utf-8&format=json&t=1506051848706&cb=jQuery1102006432263895955126_1506049020590&_=1506049020721";
		
		//http://36kr.com/p/5086498.html
		//http://www.lieyunwang.com/archives/347249
		//http://biz.jrj.com.cn/2017/08/04100522844038.shtml
		//http://www.duozhi.com/company/201708046316.shtml
		//http://36kr.com/p/5086537.html
		//http://www.linkshop.com.cn/web/archives/2017/384361.shtml
		/*
		for(String url : getUrls()){
			if(!url.startsWith("https://") && !url.endsWith(".pdf") && !url.endsWith(".PDF")){
//				List<Term> termList1 = HanLP.segment(getContent(url));
//				List<Term> list = DelStopWordTool.removeStopWord(termList1);
//				System.out.println(DelStopWordTool.removeRepeatWord(list));
				try{
					Document doc = getContent(url);
					System.out.print(doc.select("title").text().trim());
					System.out.print("\t");
					System.out.println(doc.select("meta[name=description]").attr("content").trim());
				}catch(Exception err){
				}
			}
		}*/
		
		//Nature2NameTool.StatNature(list);
		//Nature2NameTool.StatWord(list);
	}
	
	public static String [] getUrls(){
		String[] urls = {
			"http://fudaoquan.com/p/12370.html",
			"https://www.pencilnews.cn/p/14723.html",
			"http://www.ymtmt.com/news/detail_3290.html",
			"http://www.neeq.com.cn/disclosure/2016/2016-11-17/1479368411_062291.pdf",
			"http://www.9k9k.com/touzidt/17677.html",
			"http://www.lieyunwang.com/archives/262452",
			"http://www.lieyunwang.com/archives/343923",
			"http://vcbeat.net/34071.html",
			"http://www.iyiou.com/p/51017",
			"http://pe.pedaily.cn/201707/20170726417627.shtml",
			"https://www.pencilnews.cn/p/4825.html",
			"http://pe.pedaily.cn/201707/20170731417903.shtml",
			"https://www.itjuzi.com/investevents/31005",
			"https://www.pencilnews.cn/p/14697.html",
			"https://www.itjuzi.com/company/29361",
			"https://www.pencilnews.cn/p/14762.html",
			"http://www.zhonghua-pe.com/2017/0717/178197.html",
			"http://www.lieyunwang.com/archives/341990",
			"https://www.pencilnews.cn/p/14687.html",
			"http://36kr.com/p/5084972.html",
			"http://www.duozhi.com/company/201707256291.shtml",
			"https://www.pencilnews.cn/p/14685.html",
			"http://pe.pedaily.cn/201707/20170725417562.shtml",
			"http://www.cninfo.com.cn/finalpage/2017-07-26/1203732883.PDF",
			"http://www.lieyunwang.com/archives/342611",
			"http://www.zhonghua-pe.com/2017/0726/179322.html",
			"http://www.zhonghua-pe.com/2017/0726/179322.html",
			"http://pe.pedaily.cn/201707/20170726417597.shtml",
			"http://www.jiemodui.com/N/82227.html",
			"http://36kr.com/p/5085145.html",
			"http://36kr.com/p/5085236.html",
			"https://www.pencilnews.cn/p/14702.html",
			"http://news.trjcn.com/detail_188974.html",
			"https://www.pencilnews.cn/p/14700.html",
			"http://www.lieyunwang.com/archives/341919",
			"http://36kr.com/p/5082585.html",
			"http://www.iyiou.com/p/51017",
			"http://www.yxdown.com/olnews/332258.html",
			"https://www.pencilnews.cn/p/14704.html",
			"https://mp.weixin.qq.com/s?__biz=MzA3NjM4MDM2Mg==&mid=2651720028&idx=2&sn=570f883a56c0b89e56afc09d62a5fb75&chksm=849888f9b3ef01ef7f61d186fc8c865de3f1b79cd2886ad25e9d9d414aedc91de07fc4fb590d&scene=27#wechat_redirect",
			"http://www.sohu.com/a/160148768_114778",
			"https://mp.weixin.qq.com/s?__biz=MjM5MDY1NDk5Ng==&mid=2650360202&idx=1&sn=9952d317f90b69144a2d2c1fcdf805e6",
			"http://www.lieyunwang.com/archives/343384",
			"http://www.lieyunwang.com/archives/343372",
			"http://36kr.com/p/5085324.html",
			"http://36kr.com/p/5085318.html",
			"http://fund.jrj.com.cn/simu/2017/07/27092622804700.shtml",
			"http://www.sohu.com/a/160206031_640805",
			"http://pe.pedaily.cn/201707/20170727417695.shtml",
			"https://www.pencilnews.cn/p/14706.html",
			"http://36kr.com/p/5083553.html",
			"http://www.jiemodui.com/N/82083.html",
			"http://36kr.com/p/5068368.html",
			"http://pe.pedaily.cn/201707/20170727417742.shtml",
			"http://www.donews.com/news/detail/3/2961663.html",
			"http://www.cninfo.com.cn/finalpage/2017-07-26/1203736746.PDF",
			"http://www.sohu.com/a/160504845_487521",
			"https://www.itjuzi.com/investevents/33746",
			"http://36kr.com/p/5085470.html",
			"http://www.lieyunwang.com/archives/343758",
			"http://36kr.com/p/5085502.html",
			"http://36kr.com/p/5085149.html",
			"http://36kr.com/p/5085547.html",
			"http://pe.pedaily.cn/201707/20170728417764.shtml",
			"http://www.neeq.com.cn/disclosure/2017/2017-07-27/1501146925_116645.pdf",
			"http://36kr.com/p/5080650.html",
			"http://pe.pedaily.cn/201707/20170727417725.shtml",
			"http://www.iyiou.com/breaking/8618",
			"http://www.cyzone.cn/a/20170728/313538.html",
			"https://www.pencilnews.cn/p/14721.html",
			"http://36kr.com/p/5074758.html",
			"https://www.pencilnews.cn/p/14728.html",
			"https://www.pencilnews.cn/p/14726.html",
			"https://www.pencilnews.cn/p/14719.html",
			"http://www.lieyunwang.com/archives/343776",
			"http://www.cyzone.cn/a/20170728/313498.html",
			"http://36kr.com/p/5085435.html",
			"http://36kr.com/p/5084143.html",
			"http://www.xtecher.com/Xfeature/view?aid=7767",
			"http://36kr.com/p/5085622.html",
			"http://news2.qimingpian.com/weixin/e5cb5f20088606d4409f6272ef84cd44.html",
			"http://www.lieyunwang.com/archives/344823",
			"http://www.lieyunwang.com/archives/344868",
			"http://36kr.com/p/5085693.html",
			"http://www.3wyu.com/14180.html",
			"http://news2.qimingpian.com/weixin/3acf22ebc7106a9bc7a464e2643dc695.html",
			"http://36kr.com/p/5085787.html",
			"https://www.pencilnews.cn/p/14718.html",
			"https://www.pencilnews.cn/p/14735.html",
			"http://36kr.com/p/5084792.html",
			"https://mp.weixin.qq.com/s/OUunsFaYOWgU9nZIelBZQA",
			"https://www.pencilnews.cn/p/14737.html",
			"http://www.iyiou.com/p/51338",
			"http://36kr.com/p/5084986.html",
			"http://district.ce.cn/newarea/hyzx/201707/28/t20170728_24592299.shtml",
			"https://www.pencilnews.cn/p/14740.html",
			"http://www.lieyunwang.com/archives/344737",
			"http://www.lieyunwang.com/archives/344764",
			"http://www.lieyunwang.com/archives/344753",
			"http://pe.pedaily.cn/201707/20170731417903.shtml",
			"http://www.lieyunwang.com/archives/288838",
			"http://36kr.com/p/5085844.html",
			"http://36kr.com/p/5085844.html",
			"http://www.sohu.com/a/160915479_99897610",
			"http://www.iyiou.com/breaking/8652",
			"http://www.lieyunwang.com/archives/345185",
			"http://www.iyiou.com/breaking/8691",
			"https://www.pencilnews.cn/p/14760.html",
			"https://mp.weixin.qq.com/s/yTNXNRvHMCgmWYBr7xd0zg",
			"http://www.ebrun.com/20170731/240442.shtml",
			"http://www.lieyunwang.com/archives/345448",
			"https://www.pencilnews.cn/p/14758.html",
			"http://www.iyiou.com/p/51377",
			"http://www.iyiou.com/p/51300",
			"https://www.pencilnews.cn/p/14762.html",
			"https://www.pencilnews.cn/p/14731.html",
			"https://www.pencilnews.cn/p/14731.html",
			"http://www.iyiou.com/p/51456",
			"https://www.pencilnews.cn/p/14756.html",
			"http://www.cyzone.cn/a/20170801/313707.html",
			"http://www.cninfo.com.cn/finalpage/2017-07-31/1203742198.PDF",
			"http://www.iyiou.com/p/51493",
			"http://www.lieyunwang.com/archives/345884",
			"http://www.lieyunwang.com/archives/346102",
			"http://pe.pedaily.cn/201708/20170802418039.shtml",
			"http://36kr.com/p/5086258.html",
			"http://www.ebrun.com/20170803/240993.shtml",
			"https://mp.weixin.qq.com/s/0dulDCO-9oZd49cg-iFXKQ",
			"http://www.iyiou.com/p/51609",
			"http://www.lieyunwang.com/archives/346158",
			"http://www.lieyunwang.com/archives/346457",
			"http://36kr.com/p/5086382.html",
			"http://trz.yigoonet.com/article/26941157.html",
			"https://mp.weixin.qq.com/s/54V92QHf9xD3MfTceinjzA",
			"https://www.huxiu.com/article/208193.html",
			"https://mp.weixin.qq.com/s/DLw_q35v-plbIJ4SMtMtSw",
			"https://www.itjuzi.com/investevents/34121",
			"http://www.tuoniao.fm/p/13003.html",
			"http://www.lieyunwang.com/archives/346892",
			"http://36kr.com/p/5086490.html",
			"http://36kr.com/p/5086537.html",
			"http://36kr.com/p/5086498.html",
			"http://36kr.com/p/5086488.html",
			"http://www.duozhi.com/company/201708046316.shtml",
			"http://biz.jrj.com.cn/2017/08/04100522844038.shtml",
			"http://www.lieyunwang.com/archives/347045",
			"http://www.lieyunwang.com/archives/347249",
			"http://pe.pedaily.cn/201708/20170807418217.shtml",
			"http://36kr.com/p/5086970.html",
			"https://mp.weixin.qq.com/s/r3P_CWIsQL0VeRFYNPud4Q",
			"http://36kr.com/p/5086885.html",
			"http://pe.pedaily.cn/201708/20170808418263.shtml",
			"http://www.lieyunwang.com/archives/348213",
			"https://www.chinaventure.com.cn/cmsmodel/news/detail/317948.shtml",
			"http://www.aqniu.com/industry/27303.html",
			"http://36kr.com/p/5087122.html",
			"http://www.linkshop.com.cn/web/archives/2017/384361.shtml",
			"http://36kr.com/p/5087198.html",
			"https://www.itjuzi.com/investevents/34386",
			"http://www.lieyunwang.com/archives/348747",
			"http://www.sohu.com/a/163188337_114778"
		};
		return urls;
	}
}
