package pub.j2ee.scel2txt;

public class Scel2Txt {

	public static void main(String[] args) {
		//单个scel文件转化    
        FileProcessing scel=new SougouScelFileProcessing();  
        scel.parseFile("E:/hanlp/三方词库/电子词汇大全【官方推荐】.scel", "E:/hanlp/三方词库/电子词汇大全【官方推荐】.txt", false);  
  
        //多个scel文件转化为一个txt (格式：拼音字母 词)  
//        try {  
//            scel.parseFiles("/Users/ST_iOS/Desktop/test/ciku", "/Users/ST_iOS/Desktop/test/ciku/txt/汇总.txt", false);  
//        } catch (IOException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
        //多个scel文件转化为多个txt文件  
//        scel.setTargetDir("/Users/ST_iOS/Desktop/test/ciku/多对多");//转化后文件的存储位置  
//        scel.parseFile("/Users/ST_iOS/Desktop/test/ciku",false);  

	}

}
