package com.qbb.jobs.tradition.service.impl;

import com.qbb.jobs.tradition.dao.TraditionAwardDao;
import com.qbb.jobs.tradition.dao.TraditionLostDao;
import com.qbb.jobs.tradition.model.TraditionAward;
import com.qbb.jobs.tradition.model.TraditionLost;
import com.qbb.jobs.tradition.service.IGanSuK3LostService;
import com.qbb.util.StringUtil;
import com.qbb.util.calculator.DictUtils;
import com.qbb.util.calculator.K3CalculateUtils;
import com.qbb.util.calculator.finance.math.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 处理甘肃快三遗漏数据
 */
@Service("ganSuK3LostService")
public class IGanSuK3LostServiceImpl implements IGanSuK3LostService {
    private Logger log = Logger.getLogger(this.getClass());

    //需要的统计期数类型 10期 20期 50期 100期 500期
    private final static List<String> baseIssueList = Arrays.asList("0", "10", "20", "50", "100", "500");
    //彩种 201 双色球 101 大乐透 202 3D 102 排3 301:甘肃快三
    private final static String lottery_type = "301";
    //需要的遗漏类型
    private final static List<String> lostTypeList =
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    @Autowired
    TraditionAwardDao traditionAwardDao;
    @Autowired
    TraditionLostDao traditionLostDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void doCalculateLost() {
        TraditionLost traditionLost = traditionLostDao.getLastAwardLostInfo();
        if (traditionLost != null) {
            log.info("遗漏数据暂不需更新,当前已开奖最新期号为：" + traditionLost.getIssueNo());
            return;
        }
        //循环处理基于不同期数的数据
        for (String count_type : baseIssueList) {
            //循环处理遗漏类型
            for (String lost_type : lostTypeList) {
                //检查是否已存在该期遗漏数据
                if (checkExistLost(lottery_type, count_type, lost_type)) {
                    continue;
                }
                //获取所需要的期数数据
                List<TraditionAward> traditionAwardList = traditionAwardDao.getNewestAwardListByNum(lottery_type, Integer.parseInt(count_type));
                //更新遗漏数据
                updateLost(traditionAwardList, lottery_type, count_type, lost_type);
            }
        }
    }

    /**
     * 判断 遗漏表 某一种类型是否为最新记录 已是最新返回true
     *
     * @param count_type
     * @param lost_type
     * @return
     */
    public boolean checkExistLost(String lottery_type, String count_type, String lost_type) {
        //1、查询开奖记录表最新一期记录
        TraditionAward traditionAward = traditionAwardDao.getLastTraditionAward();
        //2、查询遗漏表 某一统计期数类型 的记录
        TraditionLost traditionLost = traditionLostDao.getLostInfoByParam(lottery_type, count_type, lost_type);
        //3、判断期号是否相等，相等则为最新遗漏记录，不相等则需要进行遗漏统计
        if (traditionLost != null && traditionAward.getIssueNo().equals(traditionLost.getIssueNo())) {
            log.info("该类型遗漏数据已是最新数据，期号：" + traditionLost.getIssueNo());
            return true;
        }
        return false;
    }

    /**
     * 更新遗漏数据 与前一方法必须同一事物
     *
     * @param traditionAwardList
     * @param count_type
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateLost(List<TraditionAward> traditionAwardList, String lottery_type, String count_type, String lost_type) {
        // 0 和值 1和尾 2跨度  3和值012路 4和尾012路 5跨度012路 6一码 7三同号 8两同号 9 两码 10 背靠背
        // 当前遗漏
        Map<String, Integer> lostMap = new HashMap<String, Integer>();
        // 最大遗漏
        Map<String, Integer> maxLostMap = new HashMap<String, Integer>();
        // 上一次遗漏
        Map<String, Integer> lastLostMap = new HashMap<String, Integer>();
        // 平均遗漏
        Map<String, Integer> avgLostMap = new HashMap<String, Integer>();
        // 遗漏数据不做重新计算，只更新历史数据
        initLostMap(lottery_type, count_type, lost_type, lostMap, maxLostMap, lastLostMap, avgLostMap);

        for (TraditionAward traditionAward : traditionAwardList) {
            String[] awardStrs = traditionAward.getAwardNum().split("/");

            List<String> dictList = null;
            String lostValue = "";
            String compare_type = "1";

            if ("0".equals(lost_type)) {//0和值
                lostValue = K3CalculateUtils.getSum(awardStrs) + "";
                dictList = DictUtils.sumList;
            } else if ("1".equals(lost_type)) {//1和尾
                lostValue = K3CalculateUtils.getSumTail(awardStrs) + "";
                dictList = DictUtils.sumTailList;
            } else if ("2".equals(lost_type)) {//2跨度
                lostValue = K3CalculateUtils.getSpan(awardStrs) + "";
                dictList = DictUtils.spanList;
            } else if ("3".equals(lost_type)) {//3和值012路
                lostValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSum(awardStrs)) + "";
                dictList = DictUtils.mod3List;
            } else if ("4".equals(lost_type)) {//4和尾012路
                lostValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSumTail(awardStrs)) + "";
                dictList = DictUtils.mod3List;
            } else if ("5".equals(lost_type)) {//5跨度012路
                lostValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSpan(awardStrs)) + "";
                dictList = DictUtils.mod3List;
            } else if ("6".equals(lost_type)) {//6一码
                dictList = DictUtils.oneList;
                compare_type = "2";
            } else if ("7".equals(lost_type)) {//7三同号
                dictList = DictUtils.threeSameList;
            } else if ("8".equals(lost_type)) {//8两同号
                compare_type = "2";
                dictList = DictUtils.twoSameList;
            } else if ("9".equals(lost_type)) {//9两码
                dictList = DictUtils.twoNumList;
                compare_type = "3";
            } else if ("10".equals(lost_type)) {//10背靠背
                dictList = DictUtils.backToBackList;
            }
            if (StringUtils.isEmpty(lostValue)) {//比较号码组合形态
                Arrays.sort(awardStrs);
                String awardNum = awardStrs[0] + awardStrs[1] + awardStrs[2];
                lostValue = awardNum;
            }
            //计算遗漏数据
            doDictList(compare_type, lostValue, dictList, lostMap, maxLostMap, lastLostMap, avgLostMap);
        }
        // 最后一期
        TraditionAward traditionAward = traditionAwardList.get(traditionAwardList.size() - 1);
        String issueNo = traditionAward.getIssueNo();
        // 遗漏类型 0 和值 1和尾 2跨度  3和值012路 4和尾012路 5跨度012路 6一码 7三同号 8两同号 9 两码 10 背靠背
        for (String key : lostMap.keySet()) {
            int lostValue = lostMap.get(key) == null ? 0 : lostMap.get(key);
            int maxLost = maxLostMap.get(key) == null ? 0 : maxLostMap.get(key);
            int lastLost = lastLostMap.get(key) == null ? 0 : lastLostMap.get(key);
            int avgLost = avgLostMap.get(key) == null ? 0 : avgLostMap.get(key);
            insertIntoLost(issueNo, key, count_type, lottery_type, lost_type, lostValue, maxLost, lastLost, avgLost);
        }
    }

    /**
     * 初始化遗漏数据
     *
     * @param lottery_type
     * @param count_type
     * @param lost_type
     * @param lostMap
     * @param maxLostMap
     * @param lastLostMap
     * @param avgLostMap
     */
    private void initLostMap(String lottery_type, String count_type, String lost_type, Map<String, Integer> lostMap, Map<String, Integer> maxLostMap, Map<String, Integer> lastLostMap, Map<String, Integer> avgLostMap) {
        if (!"0".equals(count_type)) {
            return;
        }
        TraditionLost queryLost = new TraditionLost();
        queryLost.setLotteryType(lottery_type);
        queryLost.setCountType(count_type);
        queryLost.setLostType(lost_type);
        //当统计类型为所有历史数据时
        List<TraditionLost> traditionLostList = traditionLostDao.getLostInfoList(queryLost);
        if (null != traditionLostList) {
            for (TraditionLost traditionLost : traditionLostList) {
                String num = traditionLost.getNum();
                lostMap.put(num, traditionLost.getNowLostCount());
                maxLostMap.put(num, traditionLost.getMaxLostCount());
                lastLostMap.put(num, traditionLost.getLastLostCount());
                avgLostMap.put(num, traditionLost.getAvgLostCount());
            }
        }
    }

    /**
     * @param compare_type 比较类型：1、equals 2、contains 3、contain ignore order
     * @param value        值
     * @param dictList     典型值 集合
     * @param lostMap      当前遗漏
     * @param maxLostMap   最大遗漏
     * @param lastLostMap  上一次遗漏
     */
    public void doDictList(String compare_type, String value, List<String> dictList, Map<String, Integer> lostMap,
                           Map<String, Integer> maxLostMap, Map<String, Integer> lastLostMap, Map<String, Integer> avgLostMap) {
        for (String dictNum : dictList) {
            //设置初始值
            if (null == lostMap.get(dictNum)) {
                lostMap.put(dictNum, 0);
            }
            //上一次遗漏
            lastLostMap.put(dictNum, lostMap.get(dictNum));
            //当前遗漏
            boolean equals_flag = "1".equals(compare_type) && value.equals(dictNum);
            boolean contain_flag = "2".equals(compare_type) && value.contains(dictNum);
            boolean contain_ignore_order_flag = false;
            if ("3".equals(compare_type)) {//适用于两码 不包含两同号
                int contain_num = 0;
                char[] dictNumArrs = dictNum.toCharArray();
                for (int i = 0; i < dictNumArrs.length; i++) {
                    if (value.contains(dictNumArrs[i] + "")) {
                        contain_num += 1;
                    }
                }
                if (contain_num == dictNumArrs.length) {//出现次数等于典型值长度
                    contain_ignore_order_flag = true;
                }
            }
            if (equals_flag || contain_flag || contain_ignore_order_flag) {
                lostMap.put(dictNum, 0);
            } else {
                lostMap.put(dictNum, lostMap.get(dictNum) + 1);
            }

            //最大遗漏
            if (null == maxLostMap.get(dictNum) || lostMap.get(dictNum) > maxLostMap.get(dictNum)) {
                maxLostMap.put(dictNum, lostMap.get(dictNum));
            }

            //平均遗漏 此处记录出现次数
            if (null == avgLostMap.get(dictNum)) { //map中不存在记录给默认值
                avgLostMap.put(dictNum, 0);
            }
            if (0 == lostMap.get(dictNum)) {
                avgLostMap.put(dictNum, avgLostMap.get(dictNum) + 1);
            }
        }
    }

    /**
     * 保存遗漏统计记录
     *
     * @param issue_no
     * @param num
     * @param count_type
     * @param lottery_type
     * @param lost_type
     * @param nowLost
     * @param maxLost
     * @param lastLost
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertIntoLost(String issue_no, String num, String count_type, String lottery_type, String lost_type,
                               Integer nowLost, Integer maxLost, Integer lastLost, Integer avgLost) {
        TraditionLost traditionLost = new TraditionLost();
        traditionLost.setIssueNo(issue_no);
        traditionLost.setNum(num);
        traditionLost.setCountType(count_type);
        traditionLost.setLotteryType(lottery_type);
        traditionLost.setLostType(lost_type);
        traditionLost.setNowLostCount(nowLost);
        traditionLost.setMaxLostCount(maxLost);
        traditionLost.setAvgLostCount(avgLost);
        traditionLost.setLastLostCount(lastLost);
        traditionLost.setCreateTime(new Date());
        traditionLost.setUpdateTime(new Date());
        int i = 0;
        if (null != traditionLostDao.getLostInfo(traditionLost)) {
            i = traditionLostDao.updateTraditionLost(traditionLost);
        } else {
            i = traditionLostDao.insertSelective(traditionLost);
        }
        if (i <= 0) {
            throw new RuntimeException("更新数据异常！");
        }
    }

}
