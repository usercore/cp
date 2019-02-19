package com.qbb.capture.tradition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbb.util.calculator.finance.math.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DltResult {

    public static String lottery(String issue)
    {
        StringBuilder sb = new StringBuilder();
        try {
            String kj_url = "http://www.lottery.gov.cn/api/lottery_index_kj.jspx";
            String kj_json = Jsoup.parse(new URL(kj_url), 10000).text();

            JSONArray kjArr = JSON.parseArray(kj_json);
            JSONObject dltObj = kjArr.getJSONObject(0).getJSONObject("dlt");
            String term = dltObj.getString("term");
            System.out.println(term);
            if(StringUtils.isEmpty(term) || !term.equals(issue)) return "";

            String drawNews = dltObj.getString("drawNews");
            JSONArray codeArr = dltObj.getJSONArray("numberCode");
            String awardNum = codeArr.getString(0)+"/"+codeArr.getString(1)+"/"+codeArr.getString(2)+"/"+codeArr.getString(3)+"/"+codeArr.getString(4)+"//"
                    +codeArr.getString(5)+"/"+codeArr.getString(6);
            System.out.println(awardNum);

            String url = "http://www.lottery.gov.cn/kjdlt/"+drawNews+".html";
            Document doc = Jsoup.parse(new URL(url), 10000);

            String htmlString = doc.text();

            String saleReg = "本期全国销售金额：(\\S*)元";
            Pattern salePatt = Pattern.compile(saleReg);
            Matcher saleMatc = salePatt.matcher(htmlString);
            String saleTemp = "";
            if(saleMatc.find()){
                saleTemp =  saleMatc.group(1);
                System.out.println(saleTemp);
            }
            if(StringUtils.isEmpty(saleTemp)) return "";
            String sale_amount = saleTemp.replaceAll("[^\\d\\.]", "");

            String poolReg = "(\\S*)元奖金滚入下期奖池";
            Pattern poolPatt = Pattern.compile(poolReg);
            Matcher poolMatc = poolPatt.matcher(htmlString);
            String poolTemp = "";
            if(poolMatc.find()){
                poolTemp =  poolMatc.group(1);
            }
            if(StringUtils.isEmpty(poolTemp)) return "";
            String pool_amount = poolTemp.replaceAll("[^\\d\\.]", "");

            Element table = doc.select("table").first();
            Elements trNodes = table.select("tr");

            // 奖等#奖金
            String grade1="",grade2="",grade3="",grade4="",grade5="",grade6="",  grade10="",grade11="",grade12="",grade13="",grade14="",grade15="";
            String bets1="",bets2="",bets3="",bets4="",bets5="",bets6="",  bets10="",bets11="",bets12="",bets13="",bets14="",bets15="";

            Element trNode1 = trNodes.get(1);
            Elements tdNodes1 = trNode1.select("td") ;
            if(tdNodes1.get(0).text().trim().equals("一等奖")){
                grade1 = tdNodes1.get(2).text().replaceAll("\\D+", "");
                if(grade1.equals("0")){
                    bets1 = "0";
                }else{
                    bets1 = tdNodes1.get(3).text().replaceAll("\\D+", "");
                }
            }

            Element trNode10 = trNodes.get(2);
            Elements tdNodes10 = trNode10.select("td") ;
            if(tdNodes10.get(0).text().trim().equals("追加")){
                grade10 = tdNodes10.get(1).text().replaceAll("\\D+", "");
                if(grade10.equals("0")){
                    bets10 = "0";
                }else{
                    bets10 = tdNodes10.get(2).text().replaceAll("\\D+", "");
                }
            }

            Element trNode2 = trNodes.get(3);
            Elements tdNodes2 = trNode2.select("td");
            if(tdNodes2.get(0).text().trim().equals("二等奖")){
                grade2 = tdNodes2.get(2).text().replaceAll("\\D+", "");
                bets2 = tdNodes2.get(3).text().replaceAll("\\D+", "");
            }

            Element trNode11 = trNodes.get(4);
            Elements tdNodes11 = trNode11.select("td");
            if(tdNodes11.get(0).text().trim().equals("追加")){
                grade11 = tdNodes11.get(1).text().replaceAll("\\D+", "");
                bets11 = tdNodes11.get(2).text().replaceAll("\\D+", "");
            }

            Element trNode3 = trNodes.get(5);
            Elements tdNodes3 = trNode3.select("td");
            if(tdNodes3.get(0).text().trim().equals("三等奖")){
                grade3 = tdNodes3.get(2).text().replaceAll("\\D+", "");
                bets3 = tdNodes3.get(3).text().replaceAll("\\D+", "");
            }

            Element trNode12 = trNodes.get(6);
            Elements tdNodes12 = trNode12.select("td");
            if(tdNodes12.get(0).text().trim().equals("追加")){
                grade12 = tdNodes12.get(1).text().replaceAll("\\D+", "");
                bets12 = tdNodes12.get(2).text().replaceAll("\\D+", "");
            }

            Element trNode4 = trNodes.get(7);
            Elements tdNodes4 = trNode4.select("td");
            if(tdNodes4.get(0).text().trim().equals("四等奖")){
                grade4 = tdNodes4.get(2).text().replaceAll("\\D+", "");
                bets4 = tdNodes4.get(3).text().replaceAll("\\D+", "");
            }

            Element trNode13 = trNodes.get(8);
            Elements tdNodes13 = trNode13.select("td");
            if(tdNodes13.get(0).text().trim().equals("追加")){
                grade13 = tdNodes13.get(1).text().replaceAll("\\D+", "");
                bets13 = tdNodes13.get(2).text().replaceAll("\\D+", "");
            }

            Element trNode5 = trNodes.get(9);
            Elements tdNodes5 = trNode5.select("td");
            if(tdNodes5.get(0).text().trim().equals("五等奖")){
                grade5 = tdNodes5.get(2).text().replaceAll("\\D+", "");
                bets5 = tdNodes5.get(3).text().replaceAll("\\D+", "");
            }

            Element trNode14 = trNodes.get(10);
            Elements tdNodes14 = trNode14.select("td");
            if(tdNodes14.get(0).text().trim().equals("追加")){
                grade14 = tdNodes14.get(1).text().replaceAll("\\D+", "");
                bets14 = tdNodes14.get(2).text().replaceAll("\\D+", "");
            }

            Element trNode6 = trNodes.get(11);
            Elements tdNodes6 = trNode6.select("td");
            if(tdNodes6.get(0).text().trim().equals("六等奖")){
                grade6 = tdNodes6.get(1).text().replaceAll("\\D+", "");
                bets6 = tdNodes6.get(2).text().replaceAll("\\D+", "");
            }

            Element trNode15 = trNodes.get(12);
            Elements tdNodes15 = trNode15.select("td");
            if(tdNodes15.get(0).text().trim().equals("追加")){
                grade15 = tdNodes15.get(1).text().replaceAll("\\D+", "");
                bets15 = tdNodes15.get(2).text().replaceAll("\\D+", "");
            }

            sb.append("一等奖").append("/");
            sb.append(grade1).append("/");
            sb.append(bets1).append("#");

            sb.append("二等奖").append("/");
            sb.append(grade2).append("/");
            sb.append(bets2).append("#");

            sb.append("三等奖").append("/");
            sb.append(grade3).append("/");
            sb.append(bets3).append("#");

            sb.append("四等奖").append("/");
            sb.append(grade4).append("/");
            sb.append(bets4).append("#");

            sb.append("五等奖").append("/");
            sb.append(grade5).append("/");
            sb.append(bets5).append("#");

            sb.append("六等奖").append("/");
            sb.append(grade6).append("/");
            sb.append(bets6).append("#");

            sb.append("一等奖追加").append("/");
            sb.append(grade10).append("/");
            sb.append(bets10).append("#");

            sb.append("二等奖追加").append("/");
            sb.append(grade11).append("/");
            sb.append(bets11).append("#");

            sb.append("三等奖追加").append("/");
            sb.append(grade12).append("/");
            sb.append(bets12).append("#");

            sb.append("四等奖追加").append("/");
            sb.append(grade13).append("/");
            sb.append(bets13).append("#");

            sb.append("五等奖追加").append("/");
            sb.append(grade14).append("/");
            sb.append(bets14).append("#");

            sb.append("六等奖追加").append("/");
            sb.append(grade15).append("/");
            sb.append(bets15);

            System.out.println(sb.toString());

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
