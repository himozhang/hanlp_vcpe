package pub.j2ee.tag;

import java.util.List;

import pub.j2ee.io.PoiUtils;

public class GenTagStaticSql {

	public static void main(String[]args){
		List <String> list = PoiUtils.xlsx("E:\\标签.xlsx",0,0);
		for(String s : list){
			System.out.printf("SELECT '%s' AS 'tagName',count(p.company_id) AS 'caseCount' FROM zdb_spider.project_2017 p WHERE MATCH(tag)AGAINST('%s*'in boolean mode) <#if startTime??> AND p.create_time BETWEEN :startTime AND :endTime </#if>\r\n",s,s);
			//System.out.printf("SELECT '%s',count(p.company_id) AS c FROM zdb_spider.project_2017 p WHERE p.tag like '%%%s%%'\r\n",s,s);
			System.out.println("UNION ALL");
		}
	}
}
