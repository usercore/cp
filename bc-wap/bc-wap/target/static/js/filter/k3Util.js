/**
 * 根据号码计算形态数组下标
 */
function calculateType(k3Num){
		if(k3Num.one - k3Num.three == 0){// 三同号
			return 2;
	    } else if(k3Num.two - k3Num.three == 0 || k3Num.two - k3Num.one == 0){// 二不同
	    	return 0;
	    }else{
	        if(k3Num.three - k3Num.two == 1 && k3Num.two - k3Num.one == 1){// 三连号
	        	return 3
	        }
	        // 三不同
	        return 1;
	    }
}

/**
 * 计算和值数组下标
 */
function calculateSum(k3Num){
	return k3Num.one + k3Num.two + k3Num.three - 3;
}

/**
 * 计算跨度数组下标
 */
function  calculateSpan(k3Num){
    return k3Num.three - k3Num.one;
}

/**
 * 计算奇偶数组下标
 */
function   calculateOddEven(k3Num){
    var odd = 0;
    if(k3Num.one % 2 == 1){
        ++ odd;
    }
    if(k3Num.two % 2 == 1){
        ++odd;
    }
    if(k3Num.three % 2 == 1){
        ++odd;
    }
    switch(odd){
        case 0:
            result = "1";
            break;
        case 1:
            result = "3";
            break;
        case 2:
            result = "2";
            break;
        case 3:
            result = "0";
            break;
    }
    return result;
}

/**
 * 计算012路 
 */
function calculate012(k3Num){
	var result = 0;
    var lye = new K3Num(k3Num.one % 3,k3Num.two % 3, k3Num.three % 3);
    if(lye.one == 0){
    	if(lye.two == 0){
    		if(lye.three == 0){
    			result = 0;
    		}else if(lye.three == 1){
    			result = 1;
    		}else if(lye.three == 2){
    			result = 2;
    		}
    	}else if(lye.two == 1){
    		if(lye.three == 1){
    			result = 3;
    		}else if(lye.three == 2){
    			result = 4;
    		}
    	}else if(lye.two == 2){
    		if(lye.three == 2){
    			result = 5;
    		}
    	}
    }else if(lye.one == 1){
    	if(lye.two == 1){
    		if(lye.three == 1){
    			result = 6;
    		}else if(lye.three == 2){
    			result = 7;
    		}
    	}else if(lye.two == 2){
    		if(lye.three == 2){
    			result = 8;
    		}
    	}
    }else if(lye.one == 2){
    	if(lye.two == 2){
    		if(lye.three == 2){
    			result = 9;
    		}
    	}
    }
    return result;
}

/**
 * 计算大中小
 */
function  calculateBigMidSmall(k3Num){
	var result = 0;
	
	if(k3Num.one <= 2){
		if(k3Num.two <= 2){
			if(k3Num.three <= 2){
				result = 3;
			}else if(k3Num.three <= 4){
				result = 4;
			}else if(k3Num.three <= 6){
				result = 5;
			}
		}else if(k3Num.two <= 4){
			if(k3Num.three <= 4){
				result = 6;
			}else if(k3Num.three <= 6){
				result = 0;
			}
		}else if(k3Num.two <= 6){
			if(k3Num.three <= 6){
				result = 7;
			}
		}
	}else if(k3Num.one <= 4){
		if(k3Num.two <= 4){
			if(k3Num.three <= 4){
				result = 2;
			}else if(k3Num.three <= 6){
				result = 8;
			}
		}else if(k3Num.two <= 6){
			if(k3Num.three <= 6){
				result = 9;
			}
		}
	}else if(k3Num.one <= 6){
		if(k3Num.two <= 6){
			if(k3Num.three <= 6){
				result = 1;
			}
		}
	}
	
	return result;
}

