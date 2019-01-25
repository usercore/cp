

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class TestRequest{
	StrSubstitutor s = new StrSubstitutor();

	public static void main(String[] args) {

		HashMap<String, String> param = new HashMap<String, String>();
		// 登录
		// param.put("actina", "userLogin");
		// param.put("userName", "-1");
		// param.put("password", "0");
		// 注册
		
		param.put("actina", "userRegister"); 
		param.put("userPhone","13798576666");
		param.put("password", "123456");
		param.put("nickName", "加油"); 
		param.put("channel", "ADR");
		 
		
		//createFilterScheme();
		//modifyUserLoginPassword();
		//userLogin();
		//calculateAward();
		k3Filter();
	}
	
	public static void k3Filter(){
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("actina", "k3Filter");
		param.put("dan", "1,2");
		param.put("tuo", "3,4,5,6");
		param.put("otherFilter",
				"[{\"filterName\": \"两偶一奇,两奇一偶\", \"filterType\": \"3\"},{\"filterName\": \"6\", \"filterType\": \"1\"}]");
		param.put("channel", "ADR");
		post(param);
	}

	public static void userLogin() {
		// 登录
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("actina", "userLogin");
		param.put("userPhone", "13798576666");
		param.put("password", "1234567");
		post(param);
	}

	public static void createFilterScheme() {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("actina", "createFilterScheme");
		param.put("userId", "1");

		param.put("schemeDetail", "和值#04,05#1@三同号单选#111#1");
		param.put("issueNo", "180915040");
		param.put("lotteryType", "301");
		param.put("investMoney", "20");
		param.put("multiple", "1");
		param.put("continuousCount", "0");
		param.put("channel", "ADR");
		post(param);
	}

	public static void modifyUserLoginPassword() {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("actina", "modifyUserLoginPassword");
		param.put("userId", "513294ec6e07439fbd6022f5752ecc9d");

		param.put("oldPassword", "1234567");
		param.put("newPassword", "1234567");
		param.put("channel", "ADR");
		post(param);
	}

	public static void post(Map<String, String> param) {
		try {
			MyHttpUtilTest.sendPost(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void calculateAward(){
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("actina", "calculateAward");
		param.put("issueNo", "180915040");

		param.put("lotteryType", "301");
		param.put("channel", "ADR");
		post(param);
	}
}
