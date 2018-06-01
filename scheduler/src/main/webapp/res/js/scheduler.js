$.post("./scheduler/getAllJob.action", null, function(data, status) {
	if (data.code > 0) {
		for (var i = 0; i < data.data.length; i++) {
			var tr = $(".template").clone(true).removeClass("template");
			tr.attr("data-value", data.data[i][tr.attr("data-value")]);
			var td = tr.find("td");
			td.each(function() {

				var field = $(this).attr("data-id");
				if (field) {
					if(field == "stateName") {
						$(this).html("<span style='font-weight:bold;color: " + getTextStyle(data.data[i].state) + "'>" + data.data[i][field] + "</span>");
					}else{
						$(this).text(data.data[i][field]);
					}
				}
				if ($(this).attr("data-state")) {
					$(this).attr("data-state",
							data.data[i][$(this).attr("data-state")]);
					$(this).find(".run").val(
							getButtonName($(this).attr("data-state")));
				}
			});
			td.end().appendTo($(".task-table tbody"));
		}
	}
}, "json")

function op(url, param) {
	$.post(url, param, function(data) {
		if (data.code > 0) {
			_refresh(param);
		} else {
			alert(data.message);
		}
	}, "json");
}

function _refresh(param) {
	$.post("./scheduler/getTaskState.action", param, function(data) {
		if (data.code > 0) {
			for (var i = 0; i < data.data.length; i++) {
				var state = data.data[i].state;
				$(
						".task-table tbody tr[data-value='" + data.data[i].id
								+ "'] td[data-id='stateName']").html("<span style='font-weight:bold;color: " + getTextStyle(data.data[i].state) + "'>" + data.data[i].stateName + "</span>");
				var td = $(".task-table tbody tr[data-value='"
						+ data.data[i].id + "'] td.op");
				td.attr("data-state", state);
				td.find("input.run").val(getButtonName(state));
			}
		}
	}, "json");
}

function getTextStyle(state) {
	return state == "NOT_START" ? "black" : ((state == "ACQUIRED"
		|| state == "BLOCKED" || state == "WAITING") ? "green"
		: ((state == "PAUSED" || state == "ERROR") ? "red" : "black"))
}

function getButtonName(state) {
	return state == "NOT_START" ? "开始" : ((state == "ACQUIRED"
			|| state == "BLOCKED" || state == "WAITING") ? "停止"
			: ((state == "PAUSED" || state == "ERROR") ? "恢复" : "不可用"))
}

function stop() {
	if (typeof (intervalID) != "undefined") {
		window.clearInterval(intervalID);
	}
}

function setRefreshRate() {
	if (typeof (intervalID) != "undefined") {
		window.clearInterval(intervalID);
	}
	intervalID = window.setInterval(_refresh, $("#rate").val() * 1000);
}
$(function() {

	$(".task-table").on(
			"click",
			".run",
			function() {
				var _this = $(this);
				var state = _this.parent().attr("data-state");
				var id = _this.parent().parent().attr("data-value");
				if (state == "NOT_START") {
					op("./scheduler/scheduleJob.action", {
						"id" : id
					});
				} else if (state == "ACQUIRED" || state == "BLOCKED"
						|| state == "WAITING") {
					if (confirm("确定停止运行该任务？")) {
						op("./scheduler/pauseJob.action", {
							"id" : id
						});
					}
				} else if (state == "PAUSED" || state == "ERROR") {
					if (confirm("确定开始运行该任务？")) {
						op("./scheduler/resumeJob.action", {
							"id" : id
						});
					}
				} else {
					alert("无效操作！");
				}
			});

	$(".task-table").on("click", ".delete", function() {
		var _this = $(this);
		var tr = _this.parent().parent();
		var state = tr.find("td.op").attr("data-state");
		if (state == "ACQUIRED" || state=="WAITING" || state == "BLOCKED" || state == "PAUSED_BLOCKED") {
			alert("请先停止任务！");
			return;
		}
		if (confirm("确定删除此任务？")) {
			var id = tr.attr("data-value");
			$.post("./scheduler/deleteJob.action", {
				"id" : id
			}, function(data) {
				alert(data.message);
				if (data.code > 0) {
					window.location.href = "./index.jsp";
				}
			}, "json");
		}
	});
	
	$(".task-table").on("click", ".runnow", function() {
		var _this = $(this);
		var tr = _this.parent().parent();
		var state = tr.find("td.op").attr("data-state");
		if (confirm("确定立即执行此任务？")) {
			var id = tr.attr("data-value");
			$.post("./scheduler/runnow.action", {
				"id" : id
			}, function(data) {
				if (data.code > 0) {
					_refresh({
						"id" : id
					});
				} else {
					alert(data.message);
				}
			}, "json");
		}
	});

	$(".task-table").on("click", ".update", function() {
		var _this = $(this);
		var tr = _this.parent().parent();
		var state = tr.find("td.op").attr("data-state");
		if (state == "ACQUIRED" || state == "BLOCKED" || state == "PAUSED_BLOCKED") {
			alert("请先停止任务！");
			return;
		}
		var id = tr.attr("data-value");
		window.location.href = "./update.jsp?id=" + id;
		// op("./scheduler/deleteJob.action", {"id" : id});
	});

	$(".addTask").on("click", function() {
		window.location.href = "./add.jsp";
	});
	$(".pauseAll").on("click", function() {
		if (confirm("确定停止所有正在运行中的任务？")) {
			op("./scheduler/pauseAll.action", null);
		}
	});
	$(".resumeAll").on("click", function() {
		if (confirm("确定全部恢复运行状态？")) {
			op("./scheduler/resumeAll.action", null);
		}
	});
	$(".reload").on("click", function() {
		if (confirm("确定重新载入任务？")) {
			op("./scheduler/reload.action", null);
		}
	});
	$("#rate").on("change", function() {
		setRefreshRate();
	});
	setRefreshRate();
	// 5分钟之后停止查询
	window.setTimeout(stop, 300000);
});