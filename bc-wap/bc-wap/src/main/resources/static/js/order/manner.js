var map = new Map();
map.set("11", "和值");
map.set("12", "三同号通选");
map.set("13", "三同号单选");
map.set("14", "二同号复选");
map.set("15", "二同号单选");
map.set("16", "三不同号");
map.set("17", "二不同号");
map.set("18", "三连号通选");

var mannerPrize = new Map();
mannerPrize.set("11","");
mannerPrize.set("12","40");
mannerPrize.set("13","240");
mannerPrize.set("14","15");
mannerPrize.set("15","80");
mannerPrize.set("16","40");
mannerPrize.set("17","8");
mannerPrize.set("18","10");
var mannerUtil = {
	getMannerDes : function(key) {
		return map.get(key);
	},

	getMannerPrize : function(key) {
		return mannerPrize.get(key);
	}
}