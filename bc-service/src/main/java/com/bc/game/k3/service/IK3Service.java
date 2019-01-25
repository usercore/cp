package com.bc.game.k3.service;

import java.util.List;
import com.bc.game.k3.domain.K3Num;

public interface IK3Service {

	/**
	 * 根据条件过滤号码
	 * @param filterList
	 * @param jsonList
	 * @return
	 */
	List<K3Num> filterNum(List<K3Num> filterList, String jsonList);

	/**
	 * 计算形态
	 * 
	 * @param k3Num
	 * @return
	 */
	String calculateType(K3Num k3Num);

	/**
	 * 计算跨度
	 * 
	 * @param
	 * @return
	 */
	String calculateSpacing(K3Num k3Num);

	/**
	 * 计算奇偶
	 * 
	 * @param
	 * @return
	 */
	String calculateOddEven(K3Num k3Num);

	/**
	 * 计算012路 
	 * @param k3Num
	 * @return
	 */
	String calculate012(K3Num k3Num);

	/**
	 * 计算大中小
	 * @param
	 * @return
	 */
	String calculateBigMidSmall(K3Num k3Num);

	/**
	 * 胆拖计算选择号码
	 * @param dan
	 * @param tuo
	 * @return
	 */
	List<K3Num> K3CalDanTuoNum(List<String> dan, List<String> tuo);

}