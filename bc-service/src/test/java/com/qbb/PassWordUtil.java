package com.qbb;

import com.alibaba.druid.filter.config.ConfigTools;

public class PassWordUtil {
    public static void main(String[] args) {
        try {
            System.out.println( ConfigTools.encrypt("QAZwsx!@3"));
            System.out.println( ConfigTools.encrypt("root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
