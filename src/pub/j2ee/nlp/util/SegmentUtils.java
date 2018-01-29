package pub.j2ee.nlp.util;

import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

/**
 * 分词工具类
 * @author 张鹏宇
 *
 */
public class SegmentUtils {

    /**
     * 断句分词
     * @param content
     * @return
     */
    public static List<List<Term>> sentence(String content){
        List<Term> cList = HanLP.segment(content);
        List<List<Term>> rsList = new ArrayList<List<Term>>();
        List<Term> sent = new ArrayList<Term>();
        for(int i=0,j=cList.size();i<j;i++){
            Term t = cList.get(i);
            String n = t.nature.toString();
            if(n.equals("w")||n.equals("wj")||n.equals("wt")||n.equals("wd")||n.equals("wf")||n.equals("")){   // 句子标识
                if(sent.size()>0)
                    rsList.add(sent);
                sent = new ArrayList<Term>();
            }else{
                sent.add(t);
            }
        }
        if(sent.size()>0)
            rsList.add(sent);
        return rsList;
    }
    
    public static void main(String [] args){
        String content = "海外网1月29日电外交部发言人华春莹29日主持例行记者会，就近期热点进行回应。如下：问：中国政府对VPN的管控对在华企业的经营造成了干扰，引发对竞争力和商业机密泄漏的担忧。你能否谈一谈未来将如何实施这方面的管控？中国监管者是否会遵守WTO规则及其允许外企与中国企业公平竞争的承诺？答：你提到的问题非常具体，而且提问的前提带着跟中国对立的情绪，我不想做具体回应。但我想提醒你注意，你可以回想一下刘鹤主任前几天在达沃斯的致辞，再回想一下一年前习近平主席在达沃斯的重要演讲。你应该看到，中方反对各种保护主义，加强知识产权保护，促进公平竞争，放宽金融业市场准入，主动扩大进口，积极推进“一带一路”建设，用实际行动推动各方一起构建开放型世界经济，开展公平公正贸易，用实际行动推动经济全球化进程。今年是中国改革开放40周年，我们将不断完善社会主义市场经济体制，继续推动全面对外开放，形成全面开放新格局，实行高水平的贸易和投资自由化便利化政策，大幅放宽市场准入。相信这将为世界各国同中国进一步开展和深化合作提供广阔空间，也将为世界各国发展提供新的机遇。希望各方客观和公正看待中国在这方面的努力和进步。";
        List<List<Term>> rs = SegmentUtils.sentence(content);
        for(List<Term> r : rs){
            System.out.println(r);
        }
    }
}
