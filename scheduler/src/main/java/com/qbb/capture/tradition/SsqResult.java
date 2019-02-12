package com.qbb.capture.tradition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qbb.capture.enums.LotteryTypeEnum;
import com.qbb.capture.model.AwardInfo;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SsqResult {

	private static LotteryTypeEnum typeEnum = LotteryTypeEnum.SSQ;
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
    	
        String url = "http://www.zhcw.com/ssq/kjgg/" + htmlId + ".shtml";
        Document doc = Jsoup.parse(new URL(url), 10000);
        Element currentScript = doc.getElementById("currentScript");
        String con = currentScript.text();
        JSONObject kj = JSON.parseArray(con).getJSONObject(0);
        StringBuffer sb = new StringBuffer();

        String kj_z_num = kj.getString("KJ_Z_NUM");
        String kj_t_num = kj.getString("KJ_T_NUM");
        String awardNum = getSsqAwardNum(kj_z_num, kj_t_num);
        System.out.println("zhcw获取双色球第" + issueNo + "开奖号码 -> " + awardNum);
        info.setAwardNum(awardNum);
        if(!info.isCheck()){
        	return null;
        }

        String one_z = kj.getString("ONE_Z");
        String one_j = kj.getString("ONE_J");
        String two_z = kj.getString("TWO_Z");
        String two_j = kj.getString("TWO_J");
        String three_z = kj.getString("THREE_Z");
        String three_j = kj.getString("THREE_J");
        String four_z = kj.getString("FOUR_Z");
        String four_j = kj.getString("FOUR_J");
        String five_z = kj.getString("FIVE_Z");
        String five_j = kj.getString("FIVE_J");
        String six_z = kj.getString("SIX_Z");
        String six_j = kj.getString("SIX_J");

        
        sb.append("一等奖").append("/").append(one_z).append("/").append(one_j).append("#");
        sb.append("二等奖").append("/").append(two_z).append("/").append(two_j).append("#");
        sb.append("三等奖").append("/").append(three_z).append("/").append(three_j).append("#");
        sb.append("四等奖").append("/").append(four_z).append("/").append(four_j).append("#");
        sb.append("五等奖").append("/").append(five_z).append("/").append(five_j).append("#");
        sb.append("六等奖").append("/").append(six_z).append("/").append(six_j);
        System.out.println("zhcw获取双色球第" + issueNo + "开奖详情 -> " + sb.toString());
        
        info.setLotteryType(typeEnum.getCode());
        info.setIssueNo(issueNo);
        info.setPrizeDetail(sb.toString());
        return info;
    }

    private static String getSsqAwardNum(String kj_z_num, String kj_t_num) {
		StringBuffer sb = new StringBuffer("");
		List<String> znumList = Arrays.asList(kj_z_num.split(" "));
		Collections.sort(znumList);
		sb.append(StringUtils.join(znumList.toArray(), "/"));
		sb.append("//");
		sb.append(kj_t_num);
    	return sb.toString();
	}

	public static String remand(String issueNo) throws IOException {
        try {
            String url = "http://app.zhcw.com/wwwroot/zhcw/jsp/kjggServ.jsp?catalogId=14609&issueNo=" + issueNo;
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
	 * 500wan
	 * @param issue
	 * @return
	 */
	public static AwardInfo trade(String issueNo)
	{
		try {
			String url = "http://kaijiang.500.com/shtml/ssq/" + issueNo + ".shtml";
			Document doc = Jsoup.parse(new URL(url), 10000);
			Elements tableNodes = doc.select(".kj_tablelist02");
			if (tableNodes!=null && tableNodes.size()==2)
			{
				Element tableNode = tableNodes.get(0);//开奖号码及奖池
				Elements liNodes = tableNode.select("tr").get(2).select("li");
				StringBuilder numSb = new StringBuilder();
				if (liNodes!=null && liNodes.size()==7)
				{
					for (int i=0; i<liNodes.size(); i++)
					{
						numSb.append(liNodes.get(i).text());
						if (i!=liNodes.size()-1)
							numSb.append("/");
						if (i==5)
							numSb.append("/");
					}
				}
				String awardNum = numSb.toString();
				System.out.println("500.com获取彩种("+ typeEnum.getName() + ")开奖号码 -> " + awardNum);
				AwardInfo info = new AwardInfo(typeEnum);
				info.setAwardNum(awardNum);
				if(!info.isCheck()){
					return null;
				}
				StringBuilder sb = new StringBuilder();
				Elements trNodes = doc.select(".kj_tablelist02").get(1).select("tr");//注数奖金
				sb.append("一等奖").append("/");
				sb.append(trNodes.get(2).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(2).select("td").get(2).text().replaceAll("\\D+", "")).append("#");
				sb.append("二等奖").append("/");
				sb.append(trNodes.get(3).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(3).select("td").get(2).text().replaceAll("\\D+", "")).append("#");
				sb.append("三等奖").append("/");
				sb.append(trNodes.get(4).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(4).select("td").get(2).text().replaceAll("\\D+", "")).append("#");
				sb.append("四等奖").append("/");
				sb.append(trNodes.get(5).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(5).select("td").get(2).text().replaceAll("\\D+", "")).append("#");
				sb.append("五等奖").append("/");
				sb.append(trNodes.get(6).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(6).select("td").get(2).text().replaceAll("\\D+", "")).append("#");
				sb.append("六等奖").append("/");
				sb.append(trNodes.get(7).select("td").get(1).text().replaceAll("\\D+", "")).append("/");
				sb.append(trNodes.get(7).select("td").get(2).text().replaceAll("\\D+", ""));
				
				info.setLotteryType(typeEnum.getCode());
		        info.setIssueNo(issueNo);
		        info.setPrizeDetail(sb.toString());
		        System.out.println("500.com获取双色球开奖详情 -> " + sb.toString());
				return info;
			}
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
    	AwardInfo info = SsqResult.getAwardInfo("18063");
    	System.out.println("最终开奖号码" + (info == null ? "" : info.toString()));
    }

}
