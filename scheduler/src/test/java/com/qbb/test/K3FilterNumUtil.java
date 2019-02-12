package com.qbb.test;

import com.qbb.test.util.JsonCastUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class K3FilterNumUtil {

    public static List<Map<String,Integer>> numList = new ArrayList<>();
    public static void initNumList(){
        if(numList == null || numList.size() != 6*6*6 ){
            for (int i=1 ;i<=6;i++){
                for (int j=1 ;j<=6;j++){
                    for (int k=1 ;k<=6;k++){
                        Map<String,Integer> numMap = new HashMap<>();
                        System.out.print(i+""+j+""+k);
                        System.out.print("  类型=" + calculateType(i,j,k));
                        System.out.print("  和值=" + calculateSum(i,j,k));
                        System.out.print("  跨度=" + calculateSpacing(i,j,k));
                        System.out.print("  奇偶=" + calculateOddEven(i,j,k));
                        System.out.print("  012=" + calculate012(i,j,k));
                        System.out.print("  大中小=" + calculateBigMidSmall(i,j,k));
                        System.out.println();
                        numMap.put("one",i);
                        numMap.put("two",j);
                        numMap.put("three",k);
                        numList.add(numMap);
                    }
                }
            }
        }

    }
    public static void main(String args[]){
        initNumList();
        Long time1 = Calendar.getInstance().getTime().getTime();

        filterNum("[{\"filterName\": \"两偶一奇,两奇一偶\", \"filterType\": \"3\"},{\"filterName\": \"15\", \"filterType\": \"1\"}]");
        System.out.println(Calendar.getInstance().getTime().getTime() - time1);
    }
    public static List<Map<String,Integer>> filterNum(String jsonList){
        Long time1 = Calendar.getInstance().getTime().getTime();

        List<Map<String, Object>> list = JsonCastUtil.parseJSON2List(jsonList);

        System.out.println(Calendar.getInstance().getTime().getTime() - time1);

        List<Map<String,Integer>> filterList = numList;
        int j = 0;
        for (Map<String, Object> map : list){
            Set filterNameSet = new HashSet();
            String[] filterNames = map.get("filterName").toString().split(",");
            CollectionUtils.addAll(filterNameSet, filterNames);
            filterList = getTypeList(filterList ,filterNameSet,map.get("filterType").toString());
            for (int i=0;i<filterList.size();i++){
                System.out.println(filterList.get(i).get("one") + "" + filterList.get(i).get("two") + "" + filterList.get(i).get("three"));
            }
            System.out.println("第"+ ++j + "次过滤结束");
        }

        return filterList;
    }
    public static List<Map<String,Integer>> getTypeList(List<Map<String,Integer>> numList,Set filterName ,String filterType){
        List<Map<String,Integer>> resultList = new ArrayList<>();
        for(int i = 0 ;i<numList.size();i++){
            String calFilterName = "";
            int one = numList.get(i).get("one");
            int two = numList.get(i).get("two");
            int three = numList.get(i).get("three");
            if(filterType.equals("0")){
                calFilterName = calculateType(one,two,three);
            }else if(filterType.equals("1")){
                calFilterName = calculateSum(one,two,three);
            }else if(filterType.equals("2")){
                calFilterName = calculateSpacing(one,two,three);
            }else if(filterType.equals("3")){
                calFilterName = calculateOddEven(one,two,three);
            }else if(filterType.equals("4")){
                calFilterName = calculate012(one,two,three);
            }else if(filterType.equals("5")){
                calFilterName = calculateBigMidSmall(one,two,three);
            }

            if(filterName.contains(calFilterName)){
                resultList.add(numList.get(i));
            }
        }

        return resultList;
    }

    public static List<Integer> sortList(int one,int two ,int three){
        List<Integer> list = new ArrayList();
        list.add(one);
        list.add(two);
        list.add(three);
        Collections.sort(list);
        return  list;
    }
    public static String calculateType(int one,int two ,int three){
        List<Integer> list = sortList(one, two, three);
        if(list.get(0) - list.get(2) == 0){
            return "三同号";
        } else if(list.get(2) - list.get(1) == 0 || list.get(1) - list.get(0) == 0){
            return "二同号";
        }else{
            if(three - two == 1 && two - one == 1){
                return "三连号";
            }
            return "三不同";
        }
    }

    /**
     * 计算和值
     * @param one
     * @param two
     * @param three
     * @return
     */
    private static  String calculateSum(int one,int two ,int three){
        return one + two + three + "";
    }
    /**
     * 计算跨度
     * @param one
     * @param two
     * @param three
     * @return
     */
    public static  String calculateSpacing(int one,int two ,int three){
        List<Integer> list = sortList(one, two, three);
        return (list.get(2) - list.get(0)) + "";
    }

    public static String  calculateOddEven(int one,int two ,int three){
        String result = "";
        int odd = 0;
        if(one % 2 == 1){
            ++ odd;
        }

        if(two % 2 == 1){
            ++odd;
        }

        if(three % 2 == 1){
            ++odd;
        }

        switch(odd){
            case 0:
                result = "全偶";
                break;
            case 1:
                result = "两偶一奇";
                break;
            case 2:
                result = "两奇一偶";
                break;
            case 3:
                result = "全奇";
                break;
        }
        return result;
    }

    public static String  calculate012(int one,int two ,int three){
        StringBuilder sb =  new StringBuilder();
        List<Integer> list = new ArrayList<>();
        list.add(one % 3);
        list.add(two % 3);
        list.add(three % 3);
        Collections.sort(list);
        for (Integer i : list){
            sb.append(i);
        }
        return sb.toString();
    }

    /**
     * 计算大中小
     * @param one
     * @param two
     * @param three
     * @return
     */
    public static String  calculateBigMidSmall(int one,int two ,int three){
        StringBuilder sb =  new StringBuilder();
        List<Integer> list = sortList(one, two, three);

        for (Integer i : list){
            if(i <=2 ){
                sb.append("小") ;
            }else if(i <=4 ){
                sb.append("中") ;
            }else{
                sb.append("大") ;
            }
        }
        return sb.toString();
    }
}
