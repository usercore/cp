package com.qbb.capture.tradition;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class Row3Result {

    public static String lottery(String issueNo)
    {
        StringBuilder sb = new StringBuilder();
        try {
            String url = "http://www.lottery.gov.cn/historykj/history.jspx?page=false&_ltype=pls&termNum=0&startTerm="+issueNo+"&endTerm="+issueNo;
            Document doc = Jsoup.parse(new URL(url), 10000);
            Element table = doc.select("result table").first();
            Elements trs = table.select("tbody tr");

            String awardNum = "";
            for (int i = 0; i < trs.size(); i++) {
                Elements tds = trs.get(i).select("td");
                String _issue = tds.get(0).text();
                if(_issue.equals(_issue)){
                    awardNum = tds.get(1).text().replaceAll(" ", "/");
                    String singleRecords = tds.get(2).text().replace("\\D+", "");
                    String singleMoney = tds.get(3).text().replace("[^\\d\\.]", "");

                    String group3Records = tds.get(4).text().replace("\\D+", "");
                    String group3Money = tds.get(5).text().replace("[^\\d\\.]", "");

                    String group6Records = tds.get(6).text().replace("\\D+", "");
                    String group6Money = tds.get(7).text().replace("[^\\d\\.]", "");

                    sb.append("单选").append("/").append(singleRecords).append("/").append(singleMoney).append("#");
                    sb.append("组选3").append("/").append(group3Records).append("/").append(group3Money).append("#");
                    sb.append("组选6").append("/").append(group6Records).append("/").append(group6Money);

                    break;
                }
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        lottery("");
    }

}
