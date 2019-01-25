var filterList = [];
var danChoiceList = [0,0,0,0,0,0];
//var danChoiceList = [{'1':0},{'2':0},{'3':0},{'4':0},{'5':0},{'6':0}];
var tuoChoiceList = [0,0,0,0,0,0];
function assemblyFilter(filterType,filterName) {
	var filter = {};
	filter.filterName = filterName;
	filter.filterType = filterType;
	filterList.push(filter);
	alert(JSON.stringify(filterList));
}

function choiceDan(dan,dom){
	var danStatus = danChoiceList[dan-1];
	if(danStatus == 0){
		$(dom).attr("class","active");
		$("#t"+dan).attr("class","disable");
		danChoiceList[dan-1] = 1;
		tuoChoiceList[dan-1] = 2;
	}else if(danStatus == 1){
		$(dom).attr("class","");
		$("#t"+dan).attr("class","");
		danChoiceList[dan-1] = 0;
		tuoChoiceList[dan-1] = 0;
	}
}

function choiceTuo(tuo,dom){
	
}