package pub.j2ee.tag;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

import pub.j2ee.io.TxtUtils;
import pub.j2ee.nlp.util.DelStopWordTool;

public class WordFrequencyUtils {
	public static void main(String[] args){
		List<String> list = TxtUtils.readList("E:\\自动打标签\\项目介绍.txt");
		Map<String,Integer> fMap = new HashMap<String,Integer>();
		long idx = 0;
		Set<String> ts = new HashSet<String>();
		for(String s : list){
			List<Term> termList = HanLP.segment(s);
			for(Term t : termList){
				ts.add(t.word);
			}
			for(String tt : ts){
				Integer i = fMap.get(tt);
				if(i==null){
					fMap.put(tt, 1);
				}else{
					fMap.put(tt, ++i);
				}
			}
			ts.clear();
			idx++;
			if(idx%10000==0){
				System.out.println(idx);
			}
		}
		System.out.println("load finish");
		idx = 0;
		try{
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\project_frequency.txt"), "UTF-8"));
			for(Map.Entry<String,Integer> em : fMap.entrySet()){
				wit.write(em.getKey()+"\t"+em.getValue()+"\r\n");
				idx++;
				if(idx%10000==0){
					System.out.println(idx);
				}
			}
			wit.close();
		}catch(Exception err){
			err.printStackTrace();
		}
	}
}
