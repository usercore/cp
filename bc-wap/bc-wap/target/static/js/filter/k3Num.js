document
		.write("<script type='text/javascript' src='/js/filter/k3Util.js'></script>");
function K3Num(one,two,three){
	this.one = one;
	this.two = two;
	this.three = three;
	
	
		var temp = 0;
	
		if(this.one > this.two){
			temp = this.one;
			this.one = this.two;
			this.two = temp;
		}
		
		if(this.two > this.three){
			temp = this.two;
			this.two = this.three;
			this.three = temp;
		}
		
		if(this.one > this.two){
			temp = this.one;
			this.one = this.two;
			this.two = temp;
		}
	this.num = this.one + "" + this.two + "" + this.three;
	this.type = calculateType(this);
}
/**
 * 玩法 11:和值 12:三同号通选 13:三同号单选 14:二同号复选 15:二同号单选 16：三不同号 17：二不同号 18：三连号通选
 * 投注号码格式  二同号单选 11/22/33$4/6  （复式） 二同号 复选 1/1,2/2  三不同号 1/2/3,1/5/6 三同号 1/1/1
 * @param k3Num 投注号码
 * @param manner 玩法
 * @returns
 */
function betNum(k3Num){
	this.num = '';
	this.num = k3Num.one + "/" + k3Num.two + "/" + k3Num.three;
	if(k3Num.type == 0){//二不同
		if(k3Num.one == k3Num.two){
			this.num = k3Num.one + "" + k3Num.two + "$" + k3Num.three;
		}else{
			this.num = k3Num.three + ""  + k3Num.two + "$" + k3Num.one;
		}
		this.manner = 15;
	}else if(k3Num.type == 1){//三不同
		this.manner = 16;
	}else if(k3Num.type == 2){//三同号
		this.manner = 13;
	}else if(k3Num.type == 3){//三连号
		this.manner = 18;
	}
	
}