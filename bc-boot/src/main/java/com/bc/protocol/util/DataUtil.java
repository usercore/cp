package com.bc.protocol.util;


public class DataUtil {

	public static boolean valideDecimals(String decimals){
		boolean result = false;
		double d = 0;
		if(decimals.toString().indexOf(".") != -1){
			d = Double.parseDouble(decimals.split("\\.")[1]);
			if(d > 0 ){
				result = true;
			}
		}
		return result;
	}
}
