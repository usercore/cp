package com.qbb.util.calculator;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class K3CalculateUtils {

    /**
     * 号码形态 二同与三不同的情况都包含二不同
     *
     * @return
     */
    public static String getAwardForm(String[] awardStrs) {
        Set<String> awardNumSet = new HashSet<String>(Arrays.asList(awardStrs));
        if (awardNumSet.size() == 1) {
            return "三同";
        } else if (awardNumSet.size() == 2) {
            return "二同";
        } else {
            Arrays.sort(awardStrs);
            if (Integer.parseInt(awardStrs[2]) - Integer.parseInt(awardStrs[0]) == 2) {
                return "三连";
            } else {
                return "三不同";
            }
        }
    }

    /**
     * 和值 号码形态 全大、全小、两小一大、两大一小
     *
     * @return
     */
    public static String getSumAwardForm(String[] awardStrs) {
        return "";
    }


    /**
     * 和值
     *
     * @return
     */
    public static int getSum(String[] awardStrs) {
        int sum = 0;
        for (int i = 0; i < awardStrs.length; i++) {
            sum += Integer.parseInt(awardStrs[i]);
        }
        return sum;
    }

    /**
     * 和值形态 单 双
     *
     * @return
     */
    public static String getSumOddOrEven(String[] awardStrs) {
        int sumValue = getSum(awardStrs);
        if (sumValue % 2 == 0) {
            return "双";
        } else {
            return "单";
        }
    }

    /**
     * 和值形态 大 小
     *
     * @return
     */
    public static String getSumScale(String[] awardStrs) {
        int sumValue = getSum(awardStrs);
        if (sumValue >= 3 && sumValue <= 10) {
            return "小";
        } else if (sumValue >= 11 && sumValue <= 18) {
            return "大";
        }
        return "";
    }

    /**
     * 和尾
     *
     * @return
     */
    public static int getSumTail(String[] awardStrs) {
        int sumValue = getSum(awardStrs);
        return sumValue % 10;
    }

    /**
     * 012路
     *
     * @return
     */
    public static int getNumModByThree(int value) {
        return value % 3;
    }

    /**
     * 跨度
     *
     * @return
     */
    public static int getSpan(String[] awardStrs) {
        Arrays.sort(awardStrs);
        return Integer.parseInt(awardStrs[2]) - Integer.parseInt(awardStrs[0]);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"6", "5", "6"};
        System.out.println(getSumTail(strs));
        System.out.println(getNumModByThree(getSumTail(strs)));
        System.out.println(getAwardForm(strs));
        System.out.println(getSpan(strs));
    }


}
