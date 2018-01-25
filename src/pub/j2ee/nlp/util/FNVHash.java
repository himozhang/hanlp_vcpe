package pub.j2ee.nlp.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * FNVhash
 * 
 * @author louxuezheng@hotmail.com
 */
public final class FNVHash {

	public static final int HASH_BITS = 64;
	public static final BigInteger FNV_64_INIT = new BigInteger("14695981039346656037");
	public static final BigInteger FNV_64_PRIME = new BigInteger("1099511628211");
	public static final BigInteger MASK_64 = BigInteger.ONE.shiftLeft(HASH_BITS).subtract(BigInteger.ONE);

	private FNVHash() {
	}

	/**
	 * fnv-1 hash�㷨�����ַ�ת��Ϊ64λhashֵ
	 * 
	 * @param str str
	 * @return
	 */
	public static BigInteger fnv1Hash64(String str) {
		BigInteger hash = FNV_64_INIT;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			hash = hash.multiply(FNV_64_PRIME);
			hash = hash.xor(BigInteger.valueOf(str.charAt(i)));
		}
		hash = hash.and(MASK_64);
		return hash;
	}

	/**
	 * fnv-1a hash�㷨�����ַ�ת��Ϊ64λhashֵ
	 * 
	 * @param str str
	 * @return
	 */
	public static BigInteger fnv1aHash64(String str) {
		BigInteger hash = FNV_64_INIT;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			hash = hash.xor(BigInteger.valueOf(str.charAt(i)));
			hash = hash.multiply(FNV_64_PRIME);
		}
		hash = hash.and(MASK_64);
		return hash;
	}
	
	/**
	 * ���ض����ƴ�hash����
	 * 
	 * @param str1 str1
	 * @param str2 str2
	 * @return
	 */
	public static int getDistance(String str1, String str2) {
		int distance;

		if (str1.length() != str2.length()) {
			distance = -1;
		} else {
			distance = 0;
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) != str2.charAt(i)) {
					distance++;
				}
			}
		}
		return distance;
	}
	
	public static String simHashCode(String str){
		BigInteger bi = fnv1Hash64(str);
		StringBuffer code = new StringBuffer(Long.toBinaryString(bi.longValue()));
		if(code.length()<64){
			int l = 64-code.length();
			for(int i=0;i<l;i++){
				code.insert(0, '0');
			}
		}
		return code.toString();
	}
	
	public static void main(String[] args){
		System.out.println(simHashCode("七奇网"));
		System.out.println(simHashCode("昆明奇旗网络科技有限公司"));
	}
}
