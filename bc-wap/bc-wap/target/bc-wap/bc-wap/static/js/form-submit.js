/**
 * ajax提交通用数据
 * @param data
 * @param succCallBackFunc
 * @param errorCallBackFunc
 * @returns
 */
function ajax_commit_commonData(data, succCallBackFunc,errorCallBackFunc){
	ajax_commit("commonData", data, succCallBackFunc, errorCallBackFunc)
}
/**
 * 打开指定页面
 * 
 * @param pageUrl
 * @returns
 */
function toPage(pageUrl) {
	var map = new Map();
	map.set('page', pageUrl);
	postCurrent("commonPage", map);
}

/**
 * form表单提交本页面打开
 * 
 * @param url
 * @param params
 */
function postCurrent(url, params) {
	var form = $('<form method="post"></form>');
	var input;
	form.attr({
		"action" : url
	});
	params.forEach(function(value, key, map) {
		input = $('<input type="hidden"/>');
		input.attr({
			"name" : key
		});
		input.val(value);
		form.append(input);
	});
	$(document.body).append(form);
	form.submit();
}
/**
 * ajax提交数据
 * @param url
 * @param data
 * @param succCallBackFunc
 * @param errorCallBackFunc
 * @returns
 */
function ajax_commit(url, data, succCallBackFunc, errorCallBackFunc) {
	$.ajax({
		type : "post",
		url : url,
		async : true,
		data : data,
		success : succCallBackFunc,
		error : errorCallBackFunc
	});
}
/**
 * 后台返回错误，非000000
 * @param res
 * @returns
 */
function requestError(res){
	if (res.erorcd == "700002"){
		alert(res.errmsg);
		toPage("login");
	}else{
		alert(res.errmsg);
	}
}
/**
 * ajax提交报错
 * @returns
 */
function ajaxError(){
}
/**
 * 清除输入框
 * @param elementId
 * @returns
 */
function clearText(elementId){
	document.getElementById(elementId).value = "";
}