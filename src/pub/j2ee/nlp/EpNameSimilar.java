package pub.j2ee.nlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import pub.j2ee.nlp.util.FNVHash;
import pub.j2ee.util.StringTools;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class EpNameSimilar {
	
	private static Map<String,Byte> wm = new HashMap<String,Byte>();
	static{
		wm.put("ns", Byte.valueOf("1"));
		wm.put("nis", Byte.valueOf("1"));
		wm.put("n", Byte.valueOf("1"));
		wm.put("v", Byte.valueOf("5"));
		wm.put("nz", Byte.valueOf("2"));
	}
	
	public static byte getWeight(String kind){
		Byte w = wm.get(kind);
		if(w!=null){
			return w;
		}else{
			return 5;
		}
	}

	public static byte[] toByteArray(String hashCode,byte weight){
		byte[] t = new byte[64];
		for(int i=0;i<64;i++){
			t[i]=(byte) (weight*-1);
		}
		String str = hashCode;
		for(int i=0,j=str.length();i<j;i++){
			if(str.charAt(i)=='1'){
				t[64-str.length()+i]=weight;
			}
		}
		return t;
	}
	
	public static String getSign(String str){
		byte[] total = new byte[64];
		for(int i=0;i<64;i++){
			total[i]=0;
		}
		List<Term> termList1 = HanLP.segment(str);
		for(Term t : termList1){
			byte[] bt = toByteArray(FNVHash.simHashCode(t.word),getWeight(t.nature.name()));
			for(int i=0;i<64;i++){
				total[i]= (byte)(total[i] + bt[i]);
			}
		}
		StringBuffer buff = new StringBuffer("");
		for(byte b : total){
			if(b>0){
				buff.append("1");
			}else{
				buff.append("0");
			}
		}
		return buff.toString();
	}
	
	public static void main(String[] args){
		long t = 0;
		try{
			FileInputStream fis = new FileInputStream("E:\\企业全称.txt");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader red = new BufferedReader(isr);
			BufferedWriter wit = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\ep_sign.txt"), "UTF-8"));
			String s = null;
			int total = 0;
			while((s=red.readLine())!=null){
				if(StringUtils.isNotBlank(s)){
					if(s.length()>4&&s.length()<25){
						wit.append(StringTools.textToDesc(s.trim().replace(" ", ""))+" ntc 1\r\n");
						total++;
					}
				}
			}
			System.out.println(total);
			wit.close();
			red.close();
		}catch(Exception err){
			err.printStackTrace();
		}
		
		//System.out.println(getSign("北京易特创思科技有限公司"));
		//System.out.println(getSign("北京易特创思科技股份有限公司"));
	}
}
