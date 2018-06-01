package com.qbb.util;

import com.qbb.context.AppContext;
import com.qbb.jobs.pub.service.SeqService;

public class SeqUtil {

	public static String seqGet(String sqnocd, String sqnodt) {
		SeqService seqService = AppContext.getBean(SeqService.class);
		return seqService.seqGet(sqnocd, sqnodt);
	}

}
