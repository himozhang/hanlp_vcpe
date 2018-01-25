package pub.j2ee.formula;

public class HammingDistance {
	public static void main(String[] args) {  
        String str1 = "0010100001101010101111010000110010110110001001011100011111010110";  
        String str2 = "1010001001101011101111010100110010010110000000111001011011010000";  
        HammingDistance hd = new HammingDistance();  
        int distance = hd.getDistance(str1, str2);  
        System.out.println("distance is " + distance);  
        long start = System.nanoTime();
        int weight = hd.getWeight(999999999);  
        System.out.println(System.nanoTime() - start);
        System.out.println("weight is " + weight);  
        
        int i = 0b1001;	// 2进制
        int j = 01001;	// 8进制
        int k = 0x1001;	// 16进制
        System.out.println(k);
    }  

	/**
	 * calculate Hamming Distance between two strings
	 * 
	 * @author
	 * @param str1 the 1st string
	 * @param str2 the 2nd string
	 * @return Hamming Distance between str1 and str2
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

	/**
	 * calculate Hamming weight for binary number
	 * 
	 * @author
	 * @param i
	 *            the binary number
	 * @return Hamming weight of the binary number
	 */
	public static int getWeight(int i) {
		int n;
		for (n = 0; i > 0; n++) {
			i &= (i - 1);
		}
		return n;
	}
}
