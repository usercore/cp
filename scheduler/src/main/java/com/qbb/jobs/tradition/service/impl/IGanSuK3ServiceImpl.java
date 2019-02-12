package com.qbb.jobs.tradition.service.impl;

import com.qbb.capture.model.AwardInfo;
import com.qbb.capture.often.GanSuK3Result;
import com.qbb.jobs.tradition.dao.TraditionAwardDao;
import com.qbb.jobs.tradition.dao.TraditionIssueDao;
import com.qbb.jobs.tradition.model.TraditionAward;
import com.qbb.jobs.tradition.model.TraditionIssue;
import com.qbb.jobs.tradition.model.TraditionProp;
import com.qbb.jobs.tradition.service.IGanSuK3Service;
import com.qbb.scheduler.model.JobModel;
import com.qbb.util.DateUtil;
import com.qbb.util.StringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ouk
 * @version 2018/7/20 0:18
 */
@Service("ganSuK3Service")
public class IGanSuK3ServiceImpl implements IGanSuK3Service {
    @Autowired
    TraditionAwardDao traditionAwardDao;
    @Autowired
    TraditionIssueDao traditionIssueDao;

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void dealWithResult(JobModel jobModel) throws Exception {
        //查询数据库中是否存在已开奖数据
        TraditionAward traditionAward = traditionAwardDao.getLastTraditionAward();
        Integer openedIssueNo = 0;
        if (null != traditionAward) {
            openedIssueNo = Integer.parseInt(traditionAward.getIssueNo());
            //查询下一期待开奖数据
            TraditionIssue traditionIssue = traditionIssueDao.getNextIssueToOpen();
            Integer nextOpenIssueNo = Integer.parseInt(traditionIssue.getIssueNo());
            Date awardTime = traditionIssue.getAwardTime();
            // 判断 1、已开奖的数据 是否与 待开奖的期数 为连续期数 2、待开奖期数 的开奖时间 是否 已过
            if (nextOpenIssueNo - openedIssueNo == 1 && new Date().before(awardTime)) {
                log.info("没有期数遗漏，并且当前时间小于开奖时间，下期未开奖，跳过此次扫描，等待开奖。。。");
                return;
            }
        } else {
            log.info("未查询到已开奖数据");
        }

        //1、获取开奖数据
        Map<String, AwardInfo> iclMap = GanSuK3Result.icaile20190125();
        Map<String, AwardInfo> pcMap = GanSuK3Result.dzzst();
        // 对结果进行排序
        ArrayList<Map.Entry<String, AwardInfo>> list = new ArrayList<>(iclMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, AwardInfo>>() {
            @Override
            public int compare(Map.Entry<String, AwardInfo> o1, Map.Entry<String, AwardInfo> o2) {
                return o1.getKey().compareTo(o2.getKey());//顺序
            }
        });
        //循环已开奖数据
        for (Map.Entry<String, AwardInfo> entry : list) {
            Integer issueNo = Integer.parseInt(entry.getValue().getIssueNo());
            //判断本次新查询到的数据是否在数据库中存在
            if (null != traditionAward && openedIssueNo >= issueNo) {
                continue;
            }
            dealWithSingleResult(entry.getValue(), pcMap.get(entry.getKey()));

        }
    }


    /**
     * 处理单期数据 单一事务 异常回滚
     *
     * @param iCaiLeAwardInfo
     * @param paiCaiAwardInfo
     */
    @Transactional
    public void dealWithSingleResult(AwardInfo iCaiLeAwardInfo, AwardInfo paiCaiAwardInfo) {
        if (null == iCaiLeAwardInfo || null == paiCaiAwardInfo) {
            log.info("抓取的数据异常！");
            return;
        }
        //1、对比两个网站的数据是否一致
        String issueNo = iCaiLeAwardInfo.getIssueNo();
        String[] iCaiLeNum = iCaiLeAwardInfo.getAwardNum().split("/");
        String[] paiCaiNum = paiCaiAwardInfo.getAwardNum().split("/");
        if (!StringUtil.compareArrayAfterSort(iCaiLeNum, paiCaiNum)) {
            log.info("抓取的第" + issueNo + "期的数据不一致，iCaiLeNum=" + iCaiLeNum + ",paiCaiNum=" + paiCaiNum);
            return;
        }
        //2、验证是否存在开奖记录
        TraditionAward traditionAward = traditionAwardDao.getTraditionAwardByIssueNo(issueNo);
        if (traditionAward != null) {
            log.info("该期已存在数据，issueNo=" + issueNo);
            return;
        }
        //3、入库开奖数据
        insert(iCaiLeAwardInfo);

    }

    /**
     * 入库开奖数据
     *
     * @param awardInfo
     */
    @Transactional
    public void insert(AwardInfo awardInfo) {
        //本期期号
        String issueNo = awardInfo.getIssueNo();
        //本期开奖号码数组
        String awardNum = awardInfo.getAwardNum();
        //本期和值
        int sum = awardInfo.getSumValue();
        //本期跨度
        int span_num = awardInfo.getSpanValue();

        System.out.println("期数：" + issueNo + ",开奖号码：" + awardInfo.getAwardNum() + ",和值：" + sum + ",本期跨度：" + span_num);
        //记录开奖数据
        insertIntoAward(issueNo, awardNum, sum, span_num);
        //记录遗漏数据
        //insertIntoLost(awardInfo);
    }

    /**
     * 插入彩票数据
     *
     * @param issueNo  期号
     * @param awardNum 开奖号码 字符串类型
     * @param sum      和值
     * @param span_num 跨度
     */
    @Transactional
    public void insertIntoAward(String issueNo, String awardNum, int sum, int span_num) {
        //上一期数据 开奖号码遗漏(6位)@和值遗漏(16位)@跨度遗漏(6位)
        String lastLost = "0/0/0/0/0/0@0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0@0/0/0/0/0/0";
        TraditionAward lastTraditionAward = traditionAwardDao.getLastTraditionAward();
        if (null != lastTraditionAward && null != lastTraditionAward.getLost()) {
            lastLost = lastTraditionAward.getLost();
        }
        //历史统计数据
        String[] total = lastLost.split("@");
        //历史开奖遗漏
        String[] awardNumLost = total[0].split("/");
        for (int i = 0; i < awardNumLost.length; i++) {
            if (awardNum.indexOf((i + 1) + "") > -1) {
                awardNumLost[i] = "0";
            } else {
                awardNumLost[i] = String.valueOf(Integer.parseInt(awardNumLost[i]) + 1);
            }
        }
        //历史和值遗漏
        String[] awardSumLost = total[1].split("/");
        for (int i = 0; i < awardSumLost.length; i++) {
            if ((i + 3) == sum) {
                awardSumLost[i] = "0";
            } else {
                awardSumLost[i] = String.valueOf(Integer.parseInt(awardSumLost[i]) + 1);
            }
        }
        //历史跨度遗漏
        String[] awardSpanLost = total[2].split("/");
        for (int i = 0; i < awardSpanLost.length; i++) {
            if (i == span_num) {
                awardSpanLost[i] = "0";
            } else {
                awardSpanLost[i] = String.valueOf(Integer.parseInt(awardSpanLost[i]) + 1);
            }
        }
        //拼接更新后的数据
        StringBuilder lost_sb = new StringBuilder();
        lost_sb.append(StringUtils.join(awardNumLost, "/")).append("@")
                .append(StringUtils.join(awardSumLost, "/")).append("@")
                .append(StringUtils.join(awardSpanLost, "/"));
        //新增开奖记录
        TraditionAward traditionAward = new TraditionAward();
        traditionAward.setIssueNo(issueNo);
        traditionAward.setStatus(0);
        traditionAward.setLotteryType("301");
        traditionAward.setAwardNum(awardNum);
        traditionAward.setLost(lost_sb.toString());
        traditionAward.setCreateTime(new Date());
        traditionAward.setUpdateTime(new Date());
        int result = traditionAwardDao.insert(traditionAward);
        if (result <= 0) {
            throw new RuntimeException("新增开奖记录失败");
        }
        //TODO 新增下一期记录
        Map<String, TraditionIssue> issueMap = GanSuK3Result.getCurrentAndNextIssueInfo();
        TraditionIssue currentIssue = issueMap.get("currentIssue");
        TraditionIssue nextIssue = issueMap.get("nextIssue");


        if (traditionIssueDao.selectByIssueAndLotteryType(currentIssue) == null) {
            traditionIssueDao.insert(currentIssue);
        }

        if (traditionIssueDao.selectByIssueAndLotteryType(nextIssue) == null) {
            traditionIssueDao.insert(nextIssue);
        }

        log.info("新增记录成功");
    }
}
