package com.qbb.util;

import java.util.*;

/**
 * @author ouk
 * @version 2018/7/20 13:57
 */
public class StringUtil {
    /**
     * 比较排序后的String数组
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean compareArrayAfterSort(String[] array1, String[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        return compareArray(array1, array2);
    }

    /**
     * 比较String数组
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean compareArray(String[] array1, String[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 使用Integer类的parseInt和String类的substring方法(直接转换法)
     *
     * @param str
     * @return
     */
    public static int[] strToInt(String[] str) {
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        return arr;
    }

    /**
     * 获取数组的和值
     *
     * @param arr
     * @return
     */
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    /**
     * 获取数组的最大值
     *
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        return max;
    }

    /**
     * 获取数组的最小值
     *
     * @param arr
     * @return
     */
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] < min) {
                min = arr[x];
            }
        }
        return min;
    }
}
