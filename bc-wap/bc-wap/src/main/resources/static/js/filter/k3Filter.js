document.write("<script type='text/javascript' src='/js/filter/k3Num.js'></script>");
document.write("<script type='text/javascript' src='/js/filter/k3Util.js'></script>");

// 0未选中、1选中、2不可选
// 胆码选择情况
var danChoiceList = [0,0,0,0,0,0];
// 托码选择情况
var tuoChoiceList = [0,0,0,0,0,0];
// 类型选择情况
var typeChoiceList = [2,2,2,2];
//和值选择情况
var sumChoiceList = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2];
//奇偶选择情况
var odd_evenChoiceList = [2,2,2,2];
// 跨度选择情况
var spanChoiceList = [2,2,2,2,2,2];
//012选择情况
var lyeChoiceList = [2,2,2,2,2,2,2,2,2,2];
//大中小选择情况
var dzxChoiceList = [2,2,2,2,2,2,2,2,2,2];
// 组出开奖号码
var k3NumList = [];

function choiceAction(){
	K3CalDanTuoNum();
	filterNum();
	calOtherFilter();
	drawPageAll();
	printResult();
}
// 选择胆码
function choiceDan(index){
	var danStatus = danChoiceList[index];
	if(danStatus == 0){
		danChoiceList[index] = 1;
		tuoChoiceList[index] = 2;
	}else if(danStatus == 1){
		danChoiceList[index] = 0;
		tuoChoiceList[index] = 0;
	}
	choiceAction();
}
// 选择托码
function choiceTuo(index){
	var tuoStatus = tuoChoiceList[index];
	if(tuoStatus == 0){
		danChoiceList[index] = 2;
		tuoChoiceList[index] = 1;
	}else if(tuoStatus == 1){
		danChoiceList[index] = 0;
		tuoChoiceList[index] = 0;
	}
	choiceAction();
}
// 选择类型、跨度、和值......
function choiceOther(index,type){
	var typeStatus = 0;
	var choiceList = [];
	if(type == "type"){
		choiceList = typeChoiceList;
	}else if(type == "span"){
		choiceList = spanChoiceList;
	}
	
	var typeStatus = choiceList[index];
	
	if(typeStatus == 0){
		choiceList[index] = 1;
	}else if(typeStatus == 1){
		choiceList[index] = 0;
	}
	choiceAction();
}

function printResult(){
	for(var i=0;i<k3NumList.length;i++){
		console.log(k3NumList[i].one + " " + k3NumList[i].two +  " " + k3NumList[i].three);
	}
}



/**
 * 胆拖计算选择号码
 */
function K3CalDanTuoNum(){
	k3NumList.length = 0;
	var dan = [];
	var tuo = [];
	
// List<K3Num> k3NumList = new ArrayList<K3Num>();
	
	for (var i=0;i<danChoiceList.length;i++)
	{
		if(danChoiceList[i] == 1){
			dan.push(i+1);
		}
	}
	
	for (var i=0;i<tuoChoiceList.length;i++)
	{
		if(tuoChoiceList[i] == 1){
			tuo.push(i+1);
		}
	}
	
	if(dan.length != 0){
		K3CalDanNum(dan);
	}
	
	
	// 全托情况
	if(!dan || dan.length == 0){
		for(var i=0;i<tuo.length;i++){// 豹子
			var tuoList = [];
			tuoList.push(tuo[i]);
			K3CalDanNum(tuoList);
		}
		
		for(var i=0;i<tuo.length;i++){
			for(var j=i+1;j<tuo.length;j++){
				var tuoList = [];
				tuoList.push(tuo[i]);
				tuoList.push(tuo[j]);
				K3CalDanNum(tuoList);
			}
		}
		
		for(var i=0;i<tuo.length;i++){
			for(var j=i+1;j<tuo.length;j++){
				for(var k=j+1;k<tuo.length;k++){
					var k3Num = new K3Num(tuo[i],tuo[j],tuo[k]);
					k3NumList.push(k3Num);
				}
				
			}
		}
	}
	
	
	if(dan.length == 1){// 两个及以上托码情况
		for(var i=0;i<tuo.length;i++){
			var k3Num = new K3Num(dan[0],tuo[i],tuo[i]);
			k3NumList.push(k3Num);
			for(var j=i+1;j<tuo.length;j++){
				var k3Num = new K3Num(dan[0],tuo[i],tuo[j]);
				k3NumList.push(k3Num);
			}
		}
		dan.push(dan[0]);
	}else if(dan.length == 1){
		dan.push(dan[0]);
	}
	
	
	if(dan.length == 2){
		for(var i=0;i<tuo.length;i++){
			var k3Num = new K3Num(dan[0],dan[1],tuo[i]);
			k3NumList.push(k3Num);
		}
	}else if(dan.length == 3){
			var k3Num = new K3Num(dan[0],dan[1],dan[2]);
			k3NumList.push(k3Num);
	}
}


/**
 * 全胆号码组合情况
 */
function K3CalDanNum(dan){
	
	if(dan.length == 1){
		var k3Num = new K3Num(dan[0],dan[0],dan[0]);
		k3NumList.push(k3Num);
	}else if(dan.length == 2){
		var k3Num = new K3Num(dan[0],dan[0],dan[1]);
		k3NumList.push(k3Num);
		
		var k3Num1 = new K3Num(dan[0],dan[1],dan[1]);
		k3NumList.push(k3Num1);
	}
}
function filterNum(){
	
}

// //////////////////////////////计算形态
function calOtherFilter(){
	var typeChoiceListTemp = [2,2,2,2];
	// 计算与过滤类型
	calculateOtherType(typeChoiceListTemp);
	
	setChoiceStatus();
	
	if(k3NumList.length == 0){
		initChoiceList(typeChoiceList,typeChoiceListTemp);
	}
	
}
//设置号码选择情况
function setChoiceStatus(choiceList,choiceListTemp){
	for(var i=0;i<choiceListTemp.length;i++){
		if(choiceListTemp[i] == 2){
			choiceList[i] = 2;
		}else if(choiceListTemp[i] == 0){
			if(choiceList[i] == 2){
			   choiceList[i] = 0;
			} 
		}
	}
}
function initChoiceList(){
	typeChoiceList = [2,2,2,2];
	spanChoiceList = [2,2,2,2,2,2];
}
/**
 * 根据号码计算形态
 * 
 * @param k3Num
 * @return
 */
function calculateOtherType(typeChoiceListTemp){
	for(var i=0;i<k3NumList.length;i++){
		var k3Num = k3NumList[i]
		var result = calculateType(k3Num,typeChoiceListTemp);
		typeChoiceListTemp[result] = 0;
	}
	
}

function drawPageAll(){
	// 画胆拖
	drawPage("d",danChoiceList);
	drawPage("t",tuoChoiceList);
	// 画类型
	drawPage("lx",typeChoiceList);
	//画和值
	drawPage("hz",sumChoiceList);
	// 画跨度
	drawPage("kd",spanChoiceList);
	//画奇偶
	drawPage("jo",odd_evenChoiceList);
	//画012
	drawPage("012",lyeChoiceList);
	//画大中小
	drawPage("dzx",dzxChoiceList);
}
function drawPage(tagName,choiceList){
	for(var i=0;i<choiceList.length;i++){
		if(choiceList[i] == 0){
			$("#" + tagName + i).attr("class","");
		}else if(choiceList[i] == 1){
			$("#" + tagName + i).attr("class","active");
		}else if(choiceList[i] == 2){
			$("#" + tagName + i).attr("class","disable");
		}
		
	}
}