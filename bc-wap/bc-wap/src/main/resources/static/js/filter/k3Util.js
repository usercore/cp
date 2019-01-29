/**
 * 根据号码计算形态
 * 
 * @param k3Num
 * @return
 */
function calculateType(k3Num,typeChoiceListTemp){
	for(var i=0;i<k3NumList.length;i++){
		var k3Num = k3NumList[i]
		if(k3Num.one - k3Num.three == 0){// 三同号
			typeChoiceListTemp[2] = 0;
	    } else if(k3Num.two - k3Num.three == 0 || k3Num.two - k3Num.one == 0){// 二不同
	    	typeChoiceListTemp[0] = 0;
	    }else{
	        if(k3Num.three - k3Num.two == 1 && k3Num.two - k3Num.one == 1){// 三连号
	        	typeChoiceListTemp[3] = 0;
	        }
	        // 三不同
	        typeChoiceListTemp[1] = 0;
	    }
	}
}

