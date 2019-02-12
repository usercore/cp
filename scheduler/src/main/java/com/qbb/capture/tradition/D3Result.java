package com.qbb.capture.tradition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qbb.capture.enums.LotteryTypeEnum;
import com.qbb.capture.model.AwardInfo;
import com.qbb.util.calculator.finance.math.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class D3Result {

	private static LotteryTypeEnum typeEnum = LotteryTypeEnum.D3;
    /**
     * 根据期号获取双色球开奖号码
     * 格式：一等奖/中奖注数/一等奖奖金#二等奖/二等奖中奖注数/二等奖奖金
     *
     * @param issueNo
     * @return
     */
    public static AwardInfo zhcw(String issueNo) {
        try {
            String htmlId = remand(issueNo);
            System.out.println("获取3D第（"+issueNo+"）期开奖htmlId=" + htmlId);
            if (StringUtils.isEmpty(htmlId)) {
                return null;
            }
            return parsingAward(htmlId, issueNo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("中彩网获取双色球开奖号码异常！");
        }
        return null;
    }

    public static AwardInfo parsingAward(String htmlId, String issueNo) throws IOException {
    	AwardInfo info = new AwardInfo(typeEnum);
        String url = "http://www.zhcw.com/3d/kjgg/" + htmlId + ".shtml";
        Document doc = Jsoup.parse(new URL(url), 10000);
        StringBuffer sb = new StringBuffer();

        Elements balls = doc.select(".blueball_bigst");
        List<String> awards = new ArrayList<String>();
        for (Element item : balls) {
            awards.add(item.text());
        }
        
        String awardNum = getD3AwardNum(awards);
        System.out.println(issueNo + " -> " + awardNum);
        info.setAwardNum(awardNum);
        if(!info.isCheck()){
        	return null;
        }
        String singleRecords = "", singleMoney = "";
        String group3Records = "", group3Money = "";
        String group6Records = "", group6Money = "";
        Elements trs = doc.select(".result_tab tr");
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            if(tds == null || tds.size() == 0){
            	continue;
            }
            if(tds.get(0).text().equals("单 选")){
                singleRecords = replaceNonnumeric(tds.get(1).text());
                singleMoney = replaceNonnumeric(tds.get(2).text());
            }
            if(tds.get(0).text().equals("组选3")){
                group3Records = replaceNonnumeric(tds.get(1).text());
                group3Money = replaceNonnumeric(tds.get(2).text());
            }
            if(tds.get(0).text().equals("组选6")){
                group6Records = replaceNonnumeric(tds.get(1).text());
                group6Money = replaceNonnumeric(tds.get(2).text());
            }
        }

        sb.append("单选").append("/").append(singleRecords).append("/").append(singleMoney).append("#");
        sb.append("组选3").append("/").append(group3Records).append("/").append(group3Money).append("#");
        sb.append("组选6").append("/").append(group6Records).append("/").append(group6Money);

        System.out.println(sb.toString());
        
        info.setLotteryType(typeEnum.getCode());
        info.setIssueNo(issueNo);
        info.setPrizeDetail(sb.toString());
        
        return info;
    }

    private static String replaceNonnumeric(String text) {
        return text.replaceAll("\\D+", "");
    }

    private static String getD3AwardNum(List<String> list) {
        return list.get(0) + "/" + list.get(1) + "/" + list.get(2);
    }

    public static String remand(String issueNo) throws IOException {
        try {
            String url = "http://app.zhcw.com/wwwroot/zhcw/jsp/kjggServ.jsp?catalogId=14757&issueNo=" + issueNo;
            Document doc = Jsoup.parse(new URL(url), 10000);
            JSONObject jsonObject = JSON.parseObject(doc.text());
            System.out.println("获取开奖页面html返回结果" + jsonObject.toString());
            if (!jsonObject.isEmpty() && jsonObject.getInteger("code") == 1) {
                return jsonObject.getString("id");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 500.wan
     * @param issue
     * @return
     */
    public static AwardInfo trade(String issueNo)
	{
		AwardInfo info = new AwardInfo(typeEnum);
		try {
			StringBuilder sb = new StringBuilder();
			String url = "http://kaijiang.500.com/shtml/sd/20" + issueNo + ".shtml";
			Document doc = Jsoup.parse(new URL(url), 10000);
			Element tableNode = doc.getElementsByClass("kj_tablelist02").get(0);//开奖号码及奖池
			Element tableNode2 = doc.getElementsByClass("kj_tablelist02").get(1);//注数奖金
			Elements liNodes = tableNode.getElementsByTag("tr").get(2).getElementsByTag("li");//开奖号码
			if (liNodes != null && liNodes.size() == 3)
			{
				StringBuilder numSb = new StringBuilder();
				for (int i  = 0; i < liNodes.size(); i++)
				{
					String tmp = liNodes.get(i).text();
					if (!tmp.matches("\\d"))
					{
						break;
					}
					else
					{
						numSb.append(tmp);
						if (i != liNodes.size()-1)
						{
							numSb.append("/");
						}
					}
				}
				String awardNum = numSb.toString();
				info.setAwardNum(awardNum);
				if(!info.isCheck()){
					return null;
				}
			}
			String grade1 = tableNode2.getElementsByTag("tr").get(2).getElementsByTag("td").get(1).text();
			grade1 = grade1.replace(",", "");
			String bets1 = tableNode2.getElementsByTag("tr").get(2).getElementsByTag("td").get(2).text();
			bets1 = bets1.replace(",", "");
			
			String type = tableNode.getElementsByTag("tr").get(2).getElementsByTag("td").get(3).getElementsByTag("font").text();
			String grade2 = "0";
			String bets2 = "0";
			String grade3 = "0";
			String bets3 = "0";
			if (!"豹子".equals(type))
			{
				String tmp1 = tableNode2.getElementsByTag("tr").get(3).getElementsByTag("td").get(1).text();
				tmp1 = tmp1.replace(",", "");
				String tmp2 = tableNode2.getElementsByTag("tr").get(3).getElementsByTag("td").get(2).text();
				tmp2 = tmp2.replace(",", "");
				if ("组三".equals(type))
				{
					grade2 = tmp1;
					bets2 = tmp2;
				}
				else
				{
					grade3 = tmp1;
					bets3 = tmp2;
				}
			}
			
			sb.append("单选").append("/").append(grade1).append("/").append(bets1).append("#");
			sb.append("组选3").append("/").append(grade2).append("/").append(bets2).append("#");
			sb.append("组选6").append("/").append(grade3).append("/").append(bets3);

			info.setLotteryType(typeEnum.getCode());
	        info.setIssueNo(issueNo);
	        info.setPrizeDetail(sb.toString());			
			System.out.println("500.com获取3D，开奖信息=====>>" + info.toString());
			
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static AwardInfo getAwardInfo(String issueNo){
		AwardInfo zhcwInfo = zhcw(issueNo);
		AwardInfo tradeInfo = trade(issueNo);
		if(zhcwInfo != null && tradeInfo != null && zhcwInfo.equals(tradeInfo)){
			return zhcwInfo;
		}
		return null;
	}
    
    public static void main(String[] args) {
    	AwardInfo info = D3Result.getAwardInfo("18063");
    	System.out.println("最终开奖号码" + (info == null ? "" : info.toString()));
    }

}
