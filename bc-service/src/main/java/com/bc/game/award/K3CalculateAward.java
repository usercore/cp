package com.bc.game.award;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.game.award.domain.TraditionAward;
import com.bc.game.award.service.ITraditionAwardService;
import com.bc.game.k3.domain.K3Num;
import com.bc.game.k3.service.IK3Service;

@Service("k3CalculateAward")
public class K3CalculateAward {

	@Autowired
	IK3Service k3Service;

	private final String LOTTERY_TYPE = "301";

	private static final Map<Integer, Integer> K3_SUM_PRIZE = new HashMap<Integer, Integer>();// 快三和值奖金

	static {
		K3_SUM_PRIZE.put(3, 240);
		K3_SUM_PRIZE.put(4, 80);
		K3_SUM_PRIZE.put(5, 40);
		K3_SUM_PRIZE.put(6, 25);
		K3_SUM_PRIZE.put(7, 16);
		K3_SUM_PRIZE.put(8, 12);
		K3_SUM_PRIZE.put(9, 10);
		K3_SUM_PRIZE.put(10, 9);
		K3_SUM_PRIZE.put(11, 9);
		K3_SUM_PRIZE.put(12, 10);
		K3_SUM_PRIZE.put(13, 12);
		K3_SUM_PRIZE.put(14, 16);
		K3_SUM_PRIZE.put(15, 25);
		K3_SUM_PRIZE.put(16, 40);
		K3_SUM_PRIZE.put(17, 80);
		K3_SUM_PRIZE.put(18, 240);
	}

	@Autowired
	ITraditionAwardService traditionAwardService;

	/**
	 * 
	 * @param investNum
	 * @param manner
	 * @param traditionAward
	 */
	public Map<String,String> calculatePrize(String investNum, String manner, String issueNo) {
		
		Map<String,String> prizeDetail = new HashMap<String,String>();

		TraditionAward traditionAward = traditionAwardService.selectAwardByIssueAndLotteryType(issueNo, LOTTERY_TYPE);

		int prize = 0;

		String[] awardNums = traditionAward.getAwardNum().split("/");

		K3Num k3AwardNum = new K3Num(Integer.parseInt(awardNums[0]), Integer.parseInt(awardNums[1]),
				Integer.parseInt(awardNums[2])).sort();

		String prizeManner = k3Service.calculateType(k3AwardNum);

		String[] investNums = investNum.split(",");
		for (String num : investNums) {

			if (manner.equals("11")) {// 和值
				prize = calculateSumPrize(num, k3AwardNum);
			} else if (manner.equals("12") && prizeManner.equals("三同号")) {// 三同号通选
				prize = 40;
			} else if (manner.equals("13") && num.equals(traditionAward.getAwardNum())) {// 三同号单选
				prize = 240;
			} else if (manner.equals("14") && (prizeManner.equals("二同号"))) {// 二同号复选
				prize = calculateTwoEqualAll(num, k3AwardNum);
			} else if (manner.equals("15")) {// 二同号单选,有复式情况
				prize = calculateTwoEqual(num, k3AwardNum);
			} else if (manner.equals("16")) {// 三不同号,有复式情况
				prize = calculateThreeNotEqual(num, k3AwardNum);
			} else if (manner.equals("17")) {// 二不同号,复式--------------
				prize = calculateTwoNotEqual(num, k3AwardNum);
			} else if (manner.equals("18") && prizeManner.equals("三连号")) {// 三连号通选
				prize = 10;
			}

			if (prize > 0) {
				break;
			}

		}
		prizeDetail.put("prize", prize + "");
		prizeDetail.put("level", manner);
		prizeDetail.put("prizeCount", "1");
		return prizeDetail;
	}
	/**
	 * 计算二同号复选奖金
	 * @param investNum  投注号码格式 二同号单选 11/22/33$4/6
	 * @param traditionAward
	 * @return
	 */
	private int calculateTwoEqualAll(String investNum, K3Num k3AwardNum) {
		int one = 0;
		int two = 0;
		int three = 0;
		int prize = 0;

		String[] investNums = investNum.split("/");
		for (String num : investNums) {
			if (one != 1 && num.equals(k3AwardNum.getOne() + "")) {
				one = 1;
			}
			if (two != 1 && num.equals(k3AwardNum.getTwo() + "")) {
				two = 1;
			}
			if (three != 1 && num.equals(k3AwardNum.getThree() + "")) {
				three = 1;
			}
			if (one + two + three == 2) {
				prize = 15;
				break;
			}
		}
		return prize;
	}
	/**
	 * 计算二同号单选奖金
	 * @param investNum  投注号码格式 二同号单选 11/22/33$4/6
	 * @param traditionAward
	 * @return
	 */
	private int calculateTwoEqual(String investNum, K3Num k3AwardNum) {
		String[] investNums = investNum.split("\\$");
		String[] equalNums = investNums[0].split("/");
		String[] notEqualNums = investNums[1].split("/");
		int prize = 0;
		for (String equalNum : equalNums) {
			for (String notEqualNum : notEqualNums) {
				K3Num k3Num = new K3Num(Integer.parseInt(equalNum.substring(1)), Integer.parseInt(equalNum.substring(1)), Integer.parseInt(notEqualNum)).sort();
				if(k3AwardNum.equals(k3Num)){
					prize = 80;
					break;
				}
			}
			if(prize == 80){
				break;
			}
			
		}
		return prize;
	}

	/**
	 * 二不同号奖金
	 * 
	 * @param investNum
	 * @param traditionAward
	 * @return
	 */
	private int calculateTwoNotEqual(String investNum, K3Num k3AwardNum) {
		int one = 0;
		int two = 0;
		int three = 0;
		int prize = 0;

		String[] investNums = investNum.split("/");
		for (String num : investNums) {
			if (one != 1 && num.equals(k3AwardNum.getOne() + "")) {
				one = 1;
			}
			if (two != 1 && num.equals(k3AwardNum.getTwo() + "")) {
				two = 1;
			}
			if (three != 1 && num.equals(k3AwardNum.getThree() + "")) {
				three = 1;
			}
			if (one + two + three == 3) {
				prize = 24;
				break;
			}
		}
		if (one + two + three == 2) {
			prize = 8;
		}
		return prize;
	}

	/**
	 * 三不同号奖金
	 * 
	 * @param investNum
	 * @param traditionAward
	 * @return
	 */
	private int calculateThreeNotEqual(String investNum, K3Num k3AwardNum) {
		boolean one = false;
		boolean two = false;
		boolean three = false;
		int prize = 0;
		String[] investNums = investNum.split("/");
		for (String num : investNums) {
			if (!one && num.equals(k3AwardNum.getOne() + "")) {
				one = true;
			}
			if (!two && num.equals(k3AwardNum.getTwo() + "")) {
				two = true;
			}
			if (!three && num.equals(k3AwardNum.getThree() + "")) {
				three = true;
			}

			if (one && two && three) {
				prize = 40;
				break;
			}
		}
		return prize;
	}

	/**
	 * 计算和值中奖情况
	 * 
	 * @return
	 */
	private int calculateSumPrize(String investNum, K3Num k3AwardNum) {
		if (Integer.parseInt(investNum) != k3AwardNum.getSum()) {
			return 0;
		}
		return K3_SUM_PRIZE.get(k3AwardNum.getSum());
	}

	/**
	 * 计算快三中奖玩法
	 * 
	 * @param issue
	 * @return 玩法 11:和值 12:三同号通选 13:三同号单选 14:二同号复选 15:二同号单选 16：三不同号 17：二不同号
	 *         18：三连号通选
	 */
	public List<String> calculateAwardManner(String issueNo) {
		List<String> resultList = new ArrayList<String>();
		TraditionAward traditionAward = traditionAwardService.selectAwardByIssueAndLotteryType(issueNo, LOTTERY_TYPE);
		String[] awardNums = traditionAward.getAwardNum().split("/");

		K3Num k3Num = new K3Num(Integer.parseInt(awardNums[0]), Integer.parseInt(awardNums[1]),Integer.parseInt(awardNums[2]));

		String manner = k3Service.calculateType(k3Num.sort());

		resultList.add("11");

		if (manner.equals("三同号")) {
			resultList.add("12");
			resultList.add("13");
		} else if (manner.equals("二同号")) {
			resultList.add("14");
			resultList.add("15");
			resultList.add("17");
		} else if (manner.equals("三连号")) {
			resultList.add("16");
			resultList.add("17");
			resultList.add("18");
		} else if (manner.equals("三不同")) {
			resultList.add("16");
			resultList.add("17");
		}
		return resultList;
	}
}
