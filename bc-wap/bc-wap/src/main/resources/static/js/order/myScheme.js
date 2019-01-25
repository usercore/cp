function getMyScheme(){ 
	var data = {
			actina:'getMyScheme'
		};
		var succCallBackFunc = function(res) {
			if (res.erorcd == "000000") {
				res.record1.forEach(function(value,key,map){
					var scheme_details = value.scheme_detail.split("@");
					
					
					var $tr = $('<tr class="myFangan">');
					$(".zh-content").append($tr);
					
					$tr.append('<div class="myFangan-text np-mb20">形态投注</div><span class="myFangan-line"></span><div class="myFangan-text">');
					parseOrders(scheme_details,$tr);
					
					$tr.append('<div class="np-mt46 np-mb20">期号&nbsp;:&nbsp;<span id="start_issue_no"></span>' + value.start_issue_no + '期</div>');
					
					$tr.append('<div class="np-mb20"><span class="myFangan-text-l">倍投&nbsp;:&nbsp;<span id="multiple"></span>' + value.multiple + '倍</span><span class="myFangan-text-r">追号&nbsp;:&nbsp;<span id="continuous_count"></span>' + value.continuous_count + '期</span></div>');
				
					$tr.append('<div class="np-mb20"><span class="myFangan-text-l">花费&nbsp;:&nbsp;<span id="invest_money"></span>' + value.invest_money + '元</span><span class="myFangan-text-r">组合数&nbsp;:&nbsp;<span>1</span></span></div><div class="np-mb20">单注可能中<span>240</span>元</div>');
				
					$tr.append('<span class="myFangan-line"></span><div class="myFangan-time"><span>'+ value.create_time + '</span></div>');
					
					
				})
				
			} else {
				requestError(res);
			}
		}
		//ajax提交数据
		ajax_commit_commonData(data, succCallBackFunc, function() {
		})
	}

function parseOrders(scheme_details,tr){
	scheme_details.forEach(function(value,key,map){
		var orders = value.split("#");
		
		tr.append('<div class="np-mt12"><span>[' + orders[0] + ']</span>&nbsp;<span>' + orders[1] + '</span>&nbsp;[<span>'+ orders[2] + '</span>注&nbsp;<span>'+ orders[2]*2 + '</span>元]</div>');
	})
}