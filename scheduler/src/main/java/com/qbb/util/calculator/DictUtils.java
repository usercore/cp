package com.qbb.util.calculator;

import javax.enterprise.inject.New;
import java.util.*;

/**
 * 典型值
 */
public class DictUtils {

    /**
     * 一码
     */
    public final static List<String> oneList =
            Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6"});
    /**
     * 两码 开奖号码组合 不包含零跨度
     */
    public final static List<String> twoNumList =
            Arrays.asList(new String[]{
                    "12", "13", "14", "15", "16",
                    "23", "24", "25", "26",
                    "34", "35", "36",
                    "45", "46",
                    "56"});
    /**
     * 和值
     */
    public final static List<String> sumList =
            Arrays.asList(new String[]{"3", "4", "5", "6", "7", "8", "9",
                    "10", "11", "12", "13", "14", "15", "16", "17", "18"});
    /**
     * 和尾
     */
    public final static List<String> sumTailList =
            Arrays.asList(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
    /**
     * 跨度
     */
    public final static List<String> spanList =
            Arrays.asList(new String[]{"0", "1", "2", "3", "4", "5"});
    /**
     * 012路
     */
    public final static List<String> mod3List =
            Arrays.asList(new String[]{"0", "1", "2"});
    /**
     * 两同号 开奖号码组合
     */
    public final static List<String> twoSameList =
            Arrays.asList(new String[]{"11", "22", "33", "44", "55", "66"});
    /**
     * 三同号 开奖号码组合
     */
    public final static List<String> threeSameList =
            Arrays.asList(new String[]{"111", "222", "333", "444", "555", "666"});
    /**
     * 三连开奖号码组合
     */
    public final static List<String> tripleList =
            Arrays.asList(new String[]{"123", "234", "345", "456"});
    /**
     * 背靠背开奖号码组合
     */
    public final static List<String> backToBackList =
            Arrays.asList(new String[]{
                    "112", "122", "113", "133", "114", "144", "115", "155", "116", "166",
                    "223", "233", "224", "244", "225", "255", "226", "266",
                    "334", "344", "335", "355", "336", "366",
                    "445", "455", "446", "466",
                    "556", "566"});

    /**
     * 所有开奖号码组合
     */
    public final static List<String> allNumList =
            Arrays.asList(new String[]{
                    "111", "112", "113", "114", "115", "116",
                    "122", "123", "124", "125", "126",
                    "133", "134", "135", "136",
                    "144", "145", "146",
                    "155", "156",
                    "166",
                    "222", "223", "224", "225", "226", "233", "234", "235", "236",
                    "244", "245", "246", "255", "256",
                    "266",
                    "333", "334", "335", "336",
                    "344", "345", "346",
                    "355", "356",
                    "366",
                    "444", "445", "446",
                    "455", "456",
                    "466",
                    "555", "556",
                    "566",
                    "666"});


}
