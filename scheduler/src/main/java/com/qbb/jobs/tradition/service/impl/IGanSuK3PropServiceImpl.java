package com.qbb.jobs.tradition.service.impl;

import com.qbb.jobs.tradition.dao.TraditionAwardDao;
import com.qbb.jobs.tradition.dao.TraditionPropDao;
import com.qbb.jobs.tradition.model.TraditionAward;
import com.qbb.jobs.tradition.model.TraditionProp;
import com.qbb.jobs.tradition.service.IGanSuK3PropService;
import com.qbb.util.calculator.K3CalculateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 定时任务 记录开奖数据的属性
 */
@Service("ganSuK3PropService")
public class IGanSuK3PropServiceImpl implements IGanSuK3PropService {
    private Logger log = Logger.getLogger(this.getClass());

    private final static List<String> k3propTypeList =
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8");
    @Autowired
    TraditionPropDao traditionPropDao;
    @Autowired
    TraditionAwardDao traditionAwardDao;

    @Override
    public void doCalculateProp() {
        //查询所有没有属性记录的彩票开奖记录
        List<TraditionAward> traditionAwardList = traditionAwardDao.getAwardPropNotRecorded();
        log.info("本次需要计算彩票属性的期数为："+traditionAwardList.size());
        //循环需要记录的开奖数据
        for (TraditionAward traditionAward : traditionAwardList) {
            doCalculate(traditionAward);
        }

    }

    @Transactional
    protected void doCalculate(TraditionAward traditionAward) {
        //循环属性类型
        for (String propType : k3propTypeList) {
            String[] awardStrs = traditionAward.getAwardNum().split("/");
            //记录彩票属性
            TraditionProp traditionProp = new TraditionProp();
            traditionProp.setIssueNo(traditionAward.getIssueNo());
            traditionProp.setLotteryType(traditionAward.getLotteryType());
            traditionProp.setPropType(propType);
            String propValue = "";
            if ("0".equals(propType)) {//0 和值
                propValue = K3CalculateUtils.getSum(awardStrs) + "";
            } else if ("1".equals(propType)) {// 1和尾
                propValue = K3CalculateUtils.getSumTail(awardStrs) + "";
            } else if ("2".equals(propType)) {// 2跨度
                propValue = K3CalculateUtils.getSpan(awardStrs) + "";
            } else if ("3".equals(propType)) {// 3和值012路
                propValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSum(awardStrs)) + "";
            } else if ("4".equals(propType)) {// 4和尾012路
                propValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSumTail(awardStrs)) + "";
            } else if ("5".equals(propType)) {// 5跨度012路
                propValue = K3CalculateUtils.getNumModByThree(K3CalculateUtils.getSpan(awardStrs)) + "";
            } else if ("6".equals(propType)) {// 6和值形态-大小
                propValue = K3CalculateUtils.getSumScale(awardStrs) + "";
            } else if ("7".equals(propType)) {// 7和值形态-单双
                propValue = K3CalculateUtils.getSumOddOrEven(awardStrs) + "";
            } else if ("8".equals(propType)) {// 8号码形态 二同与三不同的情况都包含二不同 故二不同情况不做记录
                propValue = K3CalculateUtils.getAwardForm(awardStrs) + "";
            }
            traditionProp.setPropValue(propValue);
            traditionProp.setCreateTime(new Date());
            traditionPropDao.insert(traditionProp);
        }
    }
}
