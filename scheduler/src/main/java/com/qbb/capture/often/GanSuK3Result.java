package com.qbb.capture.often;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.druid.support.json.JSONUtils;
import com.qbb.capture.enums.LotteryTypeEnum;
import com.qbb.capture.model.AwardInfo;
import com.qbb.jobs.tradition.model.TraditionIssue;
import com.qbb.util.PostUrl;

public class GanSuK3Result {
    private static LotteryTypeEnum typeEnum = LotteryTypeEnum.GANSU_K3;

    private static final String ISSUE_URL = "http://wx.dzzst.com/lottery/periodInfo/gsk3.json";

    /**
     * 爱彩乐 已失效
     *
     * @return 格式：
     */
    public static Map<String, AwardInfo> icaile() {
        Map<String, AwardInfo> infoMap = new HashMap<String, AwardInfo>();

        String url = "http://gsk3kjjg.icaile.com/";
        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Element table = doc.select(".tablelist tbody").first();
            Elements trNodes = table.select("tr");
            for (int i = 0; i < trNodes.size(); i++) {

                Elements tdNodes = trNodes.get(i).select("td");
                if (tdNodes != null && tdNodes.size() > 2) {
                    AwardInfo info = new AwardInfo(typeEnum);

                    String issueNo = tdNodes.get(0).text();
                    String[] awardNumArr = tdNodes.get(1).text().split(" ");
                    StringBuffer awardNumBuff = new StringBuffer("");
                    Integer sumValue = 0;
                    for (int j = 0; j < awardNumArr.length; j++) {
                        String codeNum = awardNumArr[j];
                        awardNumBuff.append(codeNum);
                        if (j < awardNumArr.length - 1) {
                            awardNumBuff.append("/");
                        }
                        sumValue += Integer.parseInt(codeNum);
                    }
                    Arrays.sort(awardNumArr);
                    Integer spanValue = Integer.parseInt(awardNumArr[2]) - Integer.parseInt(awardNumArr[0]);

                    info.setLotteryType(typeEnum.getCode());
                    info.setIssueNo(issueNo);
                    info.setAwardNum(awardNumBuff.toString());
                    info.setSumValue(sumValue);
                    info.setSpanValue(spanValue);
                    System.out.println(info.toString());

                    infoMap.put(issueNo, info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    /**
     * 爱彩乐 新版
     *
     * @return 格式：
     */
    public static Map<String, AwardInfo> icaile20190125() {
        Map<String, AwardInfo> infoMap = new HashMap<String, AwardInfo>();
        // 1000期 https://k3.icaile.com/chart/gs/?top=1000
        // 当天 https://k3.icaile.com/chart/gs/?top=-1
        String url = "https://k3.icaile.com/chart/gs/?top=-1";

        try {
            //创建client实例
            HttpClient client = HttpClients.createDefault();
            //创建httpget实例
            HttpGet httpGet = new HttpGet(url);
            //执行 get请求
            HttpResponse response = client.execute(httpGet);
            //返回获取实体
            HttpEntity entity = response.getEntity();
            //获取网页内容，指定编码
            String web = EntityUtils.toString(entity, "UTF-8");
            //输出网页
            Document doc = Jsoup.parse(web);
            Elements divNodes = doc.select(".bline.round");
            if (divNodes != null && divNodes.size() > 0) {
                for (int i = 0; i < divNodes.size(); i++) {
                    try {
                        Element divNode = divNodes.get(i);
                        String pcode = divNode.select(".rline.period").text();
                        String[] pcodeArr = pcode.split("-");
                        String issueNo = pcodeArr[0] + pcodeArr[1];
                        if (!StringUtils.isEmpty(pcode)) {
                            AwardInfo info = new AwardInfo(typeEnum);

                            Elements codes = divNode.select(".weak.bold.bigfont");
                            List<Integer> numList = new ArrayList<Integer>();
                            Integer sumValue = 0;
                            for (int j = 0; codes.size() == 3 && j < codes.size(); j++) {
                                String codeNum = codes.get(j).text();
                                Integer numInt = Integer.parseInt(codeNum);
                                numList.add(numInt);
                                sumValue += numInt;
                            }
                            String awardNum = StringUtils.join(numList.toArray(), "/");
                            Collections.sort(numList);
                            Integer spanValue = numList.get(2) - numList.get(0);

                            info.setLotteryType(typeEnum.getCode());
                            info.setIssueNo(issueNo);
                            info.setAwardNum(awardNum);
                            info.setSumValue(sumValue);
                            info.setSpanValue(spanValue);

                            System.out.println(info.toString());
                            infoMap.put(issueNo, info);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    /**
     * 上海派彩网
     *
     * @return 格式：
     */
    public static Map<String, AwardInfo> dzzst() {
        Map<String, AwardInfo> infoMap = new HashMap<String, AwardInfo>();

        String url = "http://wx.dzzst.com/m/chart/gsk3/k3-001.html";
        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Elements divNodes = doc.select(".grid-row");
            if (divNodes != null && divNodes.size() > 0) {
                for (int i = 0; i < divNodes.size(); i++) {
                    try {
                        Element divNode = divNodes.get(i);
                        String pcode = divNode.attr("data-pcode");
                        String[] pcodeArr = pcode.split("-");
                        String issueNo = pcodeArr[0].substring(2) + pcodeArr[1];
                        if (!StringUtils.isEmpty(pcode)) {
                            AwardInfo info = new AwardInfo(typeEnum);

                            Elements codes = divNode.select(".column-codes");
                            List<Integer> numList = new ArrayList<Integer>();
                            Integer sumValue = 0;
                            for (int j = 0; codes.size() == 3 && j < codes.size(); j++) {
                                String codeNum = codes.get(j).text();
                                Integer numInt = Integer.parseInt(codeNum);
                                numList.add(numInt);
                                sumValue += numInt;
                            }
                            String awardNum = StringUtils.join(numList.toArray(), "/");
                            Collections.sort(numList);
                            Integer spanValue = numList.get(2) - numList.get(0);

                            info.setLotteryType(typeEnum.getCode());
                            info.setIssueNo(issueNo);
                            info.setAwardNum(awardNum);
                            info.setSumValue(sumValue);
                            info.setSpanValue(spanValue);

                            System.out.println(info.toString());
                            infoMap.put(issueNo, info);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    /**
     * 获取待开奖数据 已弃用
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, TraditionIssue> getCurrentAndNextIssueInfo() {
        Map<String, TraditionIssue> resultMap = new HashMap<String, TraditionIssue>();

        String issueResult = PostUrl.getURLContent(ISSUE_URL);

        System.out.println(issueResult);

        Map<String, HashMap<String, Object>> issueMap = (HashMap<String, HashMap<String, Object>>) JSONUtils.parse(issueResult);

        //Map<String,Object> currentIssueMap = issueMap.get("currPeriod");
        Map<String, Object> nextIssueMap = issueMap.get("nextPeriod");

        TraditionIssue currentIssue = new TraditionIssue();

        String[] pcodeArr = nextIssueMap.get("pcode").toString().split("-");
        String issueNo = pcodeArr[0].substring(2) + pcodeArr[1];

        currentIssue.setIssueNo(issueNo);
        currentIssue.setStatus(2);
        currentIssue.setSellStatus(0);
        currentIssue.setLotteryType("301");
        currentIssue.setStartTime(new Date((Long) nextIssueMap.get("startTime")));
        currentIssue.setEndTime(new Date((Long) nextIssueMap.get("stopTime")));
        currentIssue.setAwardTime(new Date((Long) nextIssueMap.get("openTime")));

        currentIssue.setCreateTime(new Date());
        currentIssue.setUpdateTime(new Date());

        TraditionIssue nextIssue = new TraditionIssue();

        String issueNoStr = (Integer.parseInt(pcodeArr[1]) + 1) + "";

        if (issueNoStr.length() == 1) {
            issueNoStr = "00" + issueNoStr;
        } else if (issueNoStr.length() == 2) {
            issueNoStr = "0" + issueNoStr;
        }

        issueNo = pcodeArr[0].substring(2) + issueNoStr;

        nextIssue.setIssueNo(issueNo);
        nextIssue.setStatus(0);
        nextIssue.setSellStatus(0);
        nextIssue.setLotteryType("301");
        nextIssue.setStartTime(new Date((Long) nextIssueMap.get("startTime") + 10 * 60 * 1000));
        nextIssue.setEndTime(new Date((Long) nextIssueMap.get("stopTime") + 10 * 60 * 1000));
        nextIssue.setAwardTime(new Date((Long) nextIssueMap.get("openTime") + 10 * 60 * 1000));

        nextIssue.setCreateTime(new Date());
        nextIssue.setUpdateTime(new Date());

        resultMap.put("currentIssue", currentIssue);
        resultMap.put("nextIssue", nextIssue);
        return resultMap;
    }

    public static Map<String, TraditionIssue> zhcw() {
        Map<String, TraditionIssue> resultMap = new HashMap<String, TraditionIssue>();
        String url = "http://data.zhcw.com/k3/index.php?act=kstb";
        return resultMap;
    }


    public static void main(String[] args) {
        //icaile();
        //dzzst();
        System.out.println(getCurrentAndNextIssueInfo());
    }

}
