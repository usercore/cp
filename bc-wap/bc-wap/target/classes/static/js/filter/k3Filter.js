document
		.write("<script type='text/javascript' src='/js/filter/k3Num.js'></script>");
document
		.write("<script type='text/javascript' src='/js/filter/k3Util.js'></script>");

// 0未选中、1选中、2不可选
// 胆码选择情况
var danChoiceList = [ 0, 0, 0, 0, 0, 0 ];
var danChoiceCount = 0;
// 托码选择情况
var tuoChoiceList = [ 0, 0, 0, 0, 0, 0 ];
// 类型选择情况
var typeChoiceList = [ 2, 2, 2, 2 ];
// 和值选择情况
var sumChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
// 奇偶选择情况
var odd_evenChoiceList = [ 2, 2, 2, 2 ];
// 跨度选择情况
var spanChoiceList = [ 2, 2, 2, 2, 2, 2 ];
// 012选择情况
var lyeChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
// 大中小选择情况
var dzxChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
// 组出开奖号码
var k3NumList = [];
var k3NumListChoice = [];//号码选择情况
// 号码类型选择情况 -1 未选择 其他值对应数组下标。 第一位代表类型、第二位代表和值、以此类推跨度、奇偶、012、大中小
var choiceDetail = [ -1, -1, -1, -1, -1, -1 ]; 
//页面标识  0 初始页 1 提交选号页
var pageFlag = 0;

var count = 0;//注数

var betCount = 0;
var betNumList = [];
var betTypeCount = [0,0,0,0];

function choiceAction() {
	K3CalDanTuoNum();
	calOtherFilter();
	drawPageAll();
	printResult();
}
// 选择胆码
function choiceDan(index) {
	var danStatus = danChoiceList[index];
	if(danChoiceCount >= 2){
		alert("胆码最多只能选择两个");
		return ;
	}
	if (danStatus == 0) {
		danChoiceCount = danChoiceCount + 1;
		danChoiceList[index] = 1;
		tuoChoiceList[index] = 2;
	} else if (danStatus == 1) {
		danChoiceCount = danChoiceCount - 1;
		danChoiceList[index] = 0;
		tuoChoiceList[index] = 0;
	}
	initChoiceList();
	choiceAction();
}
// 选择托码
function choiceTuo(index) {
	var tuoStatus = tuoChoiceList[index];
	if (tuoStatus == 0) {
		danChoiceList[index] = 2;
		tuoChoiceList[index] = 1;
	} else if (tuoStatus == 1) {
		danChoiceList[index] = 0;
		tuoChoiceList[index] = 0;
	}
	initChoiceList();
	choiceAction();
}
// 选择类型、跨度、和值......
function choiceOther(index, type) {
	var typeStatus = 0;
	var choiceList = [];
	var choiceDetailIndex = 0;// 选择详情数组下标

	if (type == "type") {// 类型
		choiceDetailIndex = 0;
		choiceList = typeChoiceList;
	} else if (type == "hz") {// 和值
		choiceDetailIndex = 1;
		choiceList = sumChoiceList;
	} else if (type == "span") {// 跨度
		choiceDetailIndex = 2;
		choiceList = spanChoiceList;
	} else if (type == "jo") {// 奇偶
		choiceDetailIndex = 3;
		choiceList = odd_evenChoiceList;
	} else if (type == "012") {// 012
		choiceDetailIndex = 4;
		choiceList = lyeChoiceList;
	} else if (type == "dzx") {// 大中小
		choiceDetailIndex = 5;
		choiceList = dzxChoiceList;
	}

	var typeStatus = choiceList[index];

	if (typeStatus == 0) {// 选中
		choiceList[index] = 1;
		choiceDetail[choiceDetailIndex] = index;
	} else if (typeStatus == 1) {// 取消选择
		choiceDetail[choiceDetailIndex] = -1;
		choiceList[index] = 0;
	}
	choiceAction();
}

function printResult() {
	for (var i = 0; i < k3NumList.length; i++) {
		console.log(k3NumList[i].one + " " + k3NumList[i].two + " "
				+ k3NumList[i].three);
	}
}

/**
 * 胆拖计算选择号码
 */
function K3CalDanTuoNum() {
	k3NumList.length = 0;
	var dan = [];
	var tuo = [];

	// List<K3Num> k3NumList = new ArrayList<K3Num>();

	for (var i = 0; i < danChoiceList.length; i++) {
		if (danChoiceList[i] == 1) {
			dan.push(i + 1);
		}
	}

	for (var i = 0; i < tuoChoiceList.length; i++) {
		if (tuoChoiceList[i] == 1) {
			tuo.push(i + 1);
		}
	}

	if (dan.length != 0) {
		K3CalDanNum(dan);
	}

	// 全托情况
	if (!dan || dan.length == 0) {
		for (var i = 0; i < tuo.length; i++) {// 豹子
			var tuoList = [];
			tuoList.push(tuo[i]);
			K3CalDanNum(tuoList);
		}

		for (var i = 0; i < tuo.length; i++) {
			for (var j = i + 1; j < tuo.length; j++) {
				var tuoList = [];
				tuoList.push(tuo[i]);
				tuoList.push(tuo[j]);
				K3CalDanNum(tuoList);
			}
		}

		for (var i = 0; i < tuo.length; i++) {
			for (var j = i + 1; j < tuo.length; j++) {
				for (var k = j + 1; k < tuo.length; k++) {
					var k3Num = new K3Num(tuo[i], tuo[j], tuo[k]);
					k3NumList.push(k3Num);
				}

			}
		}
	}

	if (dan.length == 1) {// 两个及以上托码情况
		for (var i = 0; i < tuo.length; i++) {
			var k3Num = new K3Num(dan[0], tuo[i], tuo[i]);
			k3NumList.push(k3Num);
			for (var j = i + 1; j < tuo.length; j++) {
				var k3Num = new K3Num(dan[0], tuo[i], tuo[j]);
				k3NumList.push(k3Num);
			}
		}
		dan.push(dan[0]);
	} else if (dan.length == 1) {
		dan.push(dan[0]);
	}

	if (dan.length == 2) {
		for (var i = 0; i < tuo.length; i++) {
			var k3Num = new K3Num(dan[0], dan[1], tuo[i]);
			k3NumList.push(k3Num);
		}
	} else if (dan.length == 3) {
		var k3Num = new K3Num(dan[0], dan[1], dan[2]);
		k3NumList.push(k3Num);
	}
}

/**
 * 全胆号码组合情况
 */
function K3CalDanNum(dan) {

	if (dan.length == 1) {
		var k3Num = new K3Num(dan[0], dan[0], dan[0]);
		k3NumList.push(k3Num);
	} else if (dan.length == 2) {
		var k3Num = new K3Num(dan[0], dan[0], dan[1]);
		k3NumList.push(k3Num);

		var k3Num1 = new K3Num(dan[0], dan[1], dan[1]);
		k3NumList.push(k3Num1);
	}
}
function initChoiceList() {
	// 类型选择情况
	typeChoiceList = [ 2, 2, 2, 2 ];
	// 和值选择情况
	sumChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
	// 奇偶选择情况
	odd_evenChoiceList = [ 2, 2, 2, 2 ];
	// 跨度选择情况
	spanChoiceList = [ 2, 2, 2, 2, 2, 2 ];
	// 012选择情况
	lyeChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
	// 大中小选择情况
	dzxChoiceList = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
	// 组出开奖号码
	choiceDetail = [ -1, -1, -1, -1, -1, -1 ];
}
/**
 * 根据号码计算形态
 */
function calOtherFilter() {
	var typeChoiceListTemp = [ 2, 2, 2, 2 ];// 类型

	var sumChoiceListTemp = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2, 2 ];// 和值选择情况
	// 跨度选择情况
	var spanChoiceListTemp = [ 2, 2, 2, 2, 2, 2 ];
	// 奇偶选择情况
	var odd_evenChoiceListTemp = [ 2, 2, 2, 2 ];
	// 012选择情况
	var lyeChoiceListTemp = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];
	// 大中小选择情况
	var dzxChoiceListTemp = [ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 ];

	var filterCondition = [[typeChoiceListTemp,0,calculateType,typeChoiceList],[sumChoiceListTemp,1,calculateSum,sumChoiceList],[spanChoiceListTemp,2,calculateSpan,spanChoiceList]
							,[odd_evenChoiceListTemp,3,calculateOddEven,odd_evenChoiceList],[lyeChoiceListTemp,4,calculate012,lyeChoiceList],[dzxChoiceListTemp,5,calculateBigMidSmall,dzxChoiceList]];
	for (var i = 0; i < k3NumList.length; i++) {
		var k3Num = k3NumList[i]
		for(var j=0;j<filterCondition.length;j++){
			var typeResult = filterCondition[j][2](k3Num);
			if (!removeK3Num(typeResult, filterCondition[j][0], i,filterCondition[j][1])) {
				i--;
				break;
			}
		}
	}
	
	betTypeCount = [0,0,0,0];
	betNumList.length = 0;
	//根据过滤后的号码计算号码形态
	for (var i = 0; i < k3NumList.length; i++) {
		var k3Num = k3NumList[i];
		betNumList.push(new betNum(k3Num));
		betTypeCount[k3Num.type] = betTypeCount[k3Num.type] +1;
		
		for(var j=0;j<filterCondition.length;j++){
			var typeResult = filterCondition[j][2](k3Num);
			filterCondition[j][0][typeResult] = 0;
		}
	}
	for(var j=0;j<filterCondition.length;j++){
		setChoiceStatus(filterCondition[j][3], filterCondition[j][0]);
	}

	// 无任何号码，初始化数据
	if (k3NumList.length == 0) {
		initChoiceList();
	}
}
//删除不符合条件的号码
function removeK3Num(typeResult, typeChoiceListTemp, i,choiceDetailIndex) {
	if (choiceDetail[choiceDetailIndex] != -1 && choiceDetail[choiceDetailIndex] != typeResult) {// 过滤掉不符合规则的号码
		k3NumList.splice(i, 1);
		return false;
	} else  {
		return true;
	} 
}

// 设置号码可选择情况
function setChoiceStatus(choiceList, choiceListTemp) {
	for (var i = 0; i < choiceListTemp.length; i++) {
		if (choiceListTemp[i] == 2) {
			choiceList[i] = 2;
		} else if (choiceListTemp[i] == 0) {
			if (choiceList[i] == 2) {
				choiceList[i] = 0;
			}
		}
	}
}

function drawPageAll() {
	// 画胆拖
	drawPage("d", danChoiceList);
	drawPage("t", tuoChoiceList);
	// 画类型
	drawPage("lx", typeChoiceList);
	// 画和值
	drawPage("hz", sumChoiceList);
	// 画跨度
	drawPage("kd", spanChoiceList);
	// 画奇偶
	drawPage("jo", odd_evenChoiceList);
	// 画012
	drawPage("012", lyeChoiceList);
	// 画大中小
	drawPage("dzx", dzxChoiceList);
	//画注数
	countBets();
}
function drawPage(tagName, choiceList) {
	for (var i = 0; i < choiceList.length; i++) {
		if (choiceList[i] == 0) {
			$("#" + tagName + i).attr("class", "");
		} else if (choiceList[i] == 1) {
			$("#" + tagName + i).attr("class", "active");
		} else if (choiceList[i] == 2) {
			$("#" + tagName + i).attr("class", "disable");
		}

	}
}
//计算注数与填充页面
function countBets() {
	count = 0;
	$(".zh-rs-tj").html("");
	var typeName = ['二不同','三不同','三同号','三连号'];
	
	var type = "&nbsp;(";
	for(var i=0;i<betTypeCount.length;i++){
		if(betTypeCount[i] != 0){
			count = betTypeCount[i] + count;
			type = type + typeName[i] + "&nbsp;:&nbsp;<span>" + betTypeCount[i] + "</span>注，";
		}
	}
	if(type.substring(type.length-1) == '，'){
		type = type.substring(0,type.length-1) + ")";
	}else if(type.substring(type.length-1) == '('){
		type = type.substring(0,type.length-1);
	}
	
	$(".zh-rs-tj").append('共<span>' + count + '</span>注<span>' + count*2 + '</span>元' + type);
	
	$("#bets").html(count);
	$("#summoney").html(count * 2);
	$("#bets1").html(count);
	$("#summoney1").html(count * 2);
}
//组出号码
function showFilterNum(){
	$(".zh-rs-ul").html("");
	getAwardInfo();
	if(k3NumList.length == 0){
		alert("您还未选择任何号码！");
		return;
	}
	for(var i=0;i<k3NumList.length;i++){
		k3NumListChoice[i] = 1;
		var k3Num = k3NumList[i];
		var $li = $('<li><div class="zh-rs-num-chose" id="' + i + '"' + 'onclick="removeNum(this)">' + k3Num.num + '</div></li>');
		$(".zh-rs-ul").append($li);
	}
	
	  $('#filterNum').hide();
	  $('#choice').hide();
	  $('#filterNumPage').show();
	  $('#commitButton').show();
	  countMoney();
	  pageFlag = 1;
}
function removeNum(dom){
	var choiceK3Num = $(dom).html();
	var index = $(dom).attr('id');
	
	k3Num = new K3Num(choiceK3Num.substring(0,1),choiceK3Num.substring(1,2),choiceK3Num.substring(2,3));
	
	if(k3NumListChoice[index] == 0 ){
		betTypeCount[k3Num.type] = betTypeCount[k3Num.type] + 1;
		k3NumListChoice[$(dom).attr('id')] = 1;
		$(dom).attr("class", "zh-rs-num-chose");
	}else{
		betTypeCount[k3Num.type] = betTypeCount[k3Num.type] - 1;
		$(dom).attr("class", "zh-rs-num-none");
		k3NumListChoice[index] = 0;
	}
	//重新计算注数
	countBets();
}
function backChoice(){
	if(pageFlag == 1){
		$('#filterNum').show();
		$('#choice').show();
		$('#filterNumPage').hide();
		$('#commitButton').hide();
		pageFlag = 0;
	}else{
		toPage('homePage');
	}
}
function clearNum(){
	betTypeCount = [ 0, 0, 0, 0];
	for(var i=0;i<k3NumListChoice.length;i++){
		k3NumListChoice[i] = 0;
	}
	$(".zh-rs-num-chose").attr("class", "zh-rs-num-none");
	countBets();
}
function clearChoice(){
	danChoiceList = [ 0, 0, 0, 0, 0, 0 ];
	tuoChoiceList = [ 0, 0, 0, 0, 0, 0 ];
	initChoiceList();
	drawPageAll();
}
function countMoney(){
	var repeat = $("#repeat").val();
	var multiple = $("#multiple").val();
	betCount = repeat * multiple * count;
	console.log(repeat + "  " + multiple + "注数" + betCount);
	$("#bets1").html(betCount);
	$("#summoney1").html(betCount * 2);
}

var currentIssue = "";

function bet() {
	var schemeDetail = "";
	for(var i=0;i<betNumList.length;i++){
		if(k3NumListChoice[i] == 1){
			var betNum = betNumList[i];
			schemeDetail = schemeDetail + betNum.manner + "#" + betNum.num + "#1@" ;
		}
	}
	if(schemeDetail.substring(schemeDetail.length-1) == '@'){
		schemeDetail = schemeDetail.substring(0,schemeDetail.length-1);
	}
	if(!schemeDetail){
		alert("请至少选择一注号码");
		return;
	}
	var data = {
		schemeDetail:schemeDetail,
		actina:"createFilterScheme",
		issueNo:currentIssue,
		lotteryType:301,
		investMoney:betCount*2,
		multiple:$("#multiple").val(),
		continuousCount:$("#repeat").val()
           /* <!-- 玩法 11:和值 12:三同号通选 13:三同号单选 14:二同号复选 15:二同号单选 16：三不同号 17：二不同号 18：三连号通选 -->
            <!-- 投注号码格式  二同号单选 11/22/33$4/6  （复式） 二同号 复选 1/1,2/2  三不同号 1/2/3,1/5/6 三同号 1/1/1-->
             <Field name="schemeDetail" title="方案详情以#分隔列，以@分隔行 玩法#号码#注数@玩法#号码#注数 如：11#04,05#1@13#1/1/1,2/2/2#1"  type="String" input="O" length="1000" default=""/>
             */
	};
	var succCallBackFunc = function(res) {
		if (res.erorcd == "000000") {
			toPage('user/myInfo');
		} else {
			alert(res.errmsg);
		}
	}
	//ajax提交数据
	ajax_commit_commonData(data, succCallBackFunc, function() {
	})
}


function getAwardInfo() {
	var data = {
		lotteryType : '301',
		actina:'getCurrentIssue'
	};
	var succCallBackFunc = function(res) {
		if (res.erorcd == "000000") {
			var nums = res.record1[0].award_num.split('/');
			$("#one").html(nums[0]);
			$("#two").html(nums[1]);
			$("#three").html(nums[2]);
			$("#preIssue").html(res.record1[0].issue_no + "期开奖");
			if(res.record2[0]){
				currentIssue = res.record2[0].issue_no;
				$("#currentIssue").html(currentIssue.substring(currentIssue.length-2,currentIssue.length));
				
				var residueTime = res.record2[0].residueTime;
				var min = residueTime.split('-')[0];
				var sec = residueTime.split('-')[1]; 
				var awardTime = parseInt(min) * 60 + parseInt(sec);
				
				var endTime = res.record2[0].end_time;
				var date = new Date();
				var time = endTime - date.getTime();
				countDown(parseInt(time/1000),awardTime,currentIssue.substring(currentIssue.length-2,currentIssue.length));
			}else{
				 $("#time").html("开奖中.....");
			}
			
		} else {
			requestError(res);
		}
	}
	//ajax提交数据
	ajax_commit_commonData(data, succCallBackFunc, function() {
	})
};
function countDown(time,awardTime,issueNo){
	var interval = window.setInterval(function(){
		 time = time -1;
		 awardTime = awardTime - 1;
		 if(parseInt(time) <= 0 || awardTime<0){
			 $("#time").html("开奖中.....");
			 getAward();
			 clearInterval(interval);
			 return;
		 }
		 var min = Math.floor(time/60);
		 var sec = time%60;
		 if(min < 10){
			 min = '0' + min;
		 }
		 if(sec < 10){
			 sec = '0' + sec;
		 }
		 $("#time").html("距离" + issueNo + "期截止" + min + ":" + sec);
		    }, 1000);
}
function getAward(){
	var interval = window.setInterval(function(){
		getAwardInfo();
		if($("#time").text().indexOf("开奖中") == -1 ){
			clearInterval(interval);
		}
	}, 10000);
}