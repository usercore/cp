package com.bc.gateway.handle.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.common.json.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.game.k3.domain.K3Num;
import com.bc.game.k3.service.IK3Service;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.util.exception.BusinessException;

@Service("k3Filter")
public class K3Filter extends AbsCommProtocol {

	@Autowired
	IK3Service k3Service ;
	
	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		System.out.println("快三过滤");
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
		
		String dan = paraMap.get("dan").toString();
		String tuo = paraMap.get("tuo").toString();
		String otherFilter = paraMap.get("otherFilter").toString();
		
		String[] dans = {};
		String[] tuos = {};
		
		if(!dan.equals("")){
			dans = dan.split(",");
		}
		
		if(!tuo.equals("")){
			tuos = tuo.split(",");
		}
		//计算胆拖组合出来的投注号码
		List<K3Num> numList = k3Service.K3CalDanTuoNum(Arrays.asList(dans), Arrays.asList(tuos));
		
		//根据条件过滤号码
		numList = k3Service.filterNum(numList,otherFilter);
		
		List<Map<String,String>> resultNumList = new ArrayList<Map<String,String>>(); 
		
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		
		//计算所选号码形态
		for(K3Num k3Num : numList){
			
			Map<String,String> numMap = new HashMap<String,String>();
			
			String numType = k3Service.calculateType(k3Num);
			
			String manner = transitionType(numType);
			
            assembleCanChoiceResult(map,"0",numType);
            
            assembleCanChoiceResult(map,"1",k3Num.getSum() + "");
            
            assembleCanChoiceResult(map,"2",k3Service.calculateSpacing(k3Num));
            
            assembleCanChoiceResult(map,"3",k3Service.calculateOddEven(k3Num));
            
            assembleCanChoiceResult(map,"4",k3Service.calculate012(k3Num));
            
            assembleCanChoiceResult(map,"5",k3Service.calculateBigMidSmall(k3Num));
            
            numMap.put("awardNum", k3Num.getOne() + "" + k3Num.getTwo() + k3Num.getThree());
            numMap.put("manner", manner);
            resultNumList.add(numMap);
            
		}
		resultMap.put("record1", map);
		resultMap.put("record2", resultNumList);
		resultMap.put("erorcd", "000000");
		resultMap.put("errmsg", "");

		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void assembleCanChoiceResult(Map<String,Set<String>> map,String key,String value){
		if(map.containsKey(key)){
			map.get(key).add(value);
		}else{
			Set<String> set = new HashSet<String>();
			set.add(value);
			map.put(key, set);
		}
	}
	
	private String transitionType(String type){
		String result = "";
		if(type.equals("三同号")){
			result = "13";
		}else if(type.equals("二同号")){
			result = "15";
		}else if(type.equals("三连号") || type.equals("三不同")){
			result = "16";
		}
		return result;
	}
}
