package com.qbb.util.client;


import com.qbb.util.Constanct;

import java.util.Map;

public class QbbServiceRPC {

	public static final String URL = Constanct.SERVICE_URL;

	public static Map<String, Object> getData(Map<String, Object> inMap,
			String actina) throws Exception {
		Map<String, Object> returnMap = QbbServiceClient.doPost(URL, actina,
				inMap);
		return returnMap;
	}

}
