<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>定时任务管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="./res/css/style.css" />
<script type="text/javascript" src="./res/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="./res/js/jquery.form.js"></script>
<script type="text/javascript" src="./res/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div style="padding: 10px 0px;text-align: center;font-size: 18pt;">更新任务</div>
	<div>
		<form action="#"  id="addForm">
		<input type="hidden" name="args.id" />
		<table class="task-table" border="1">
			<col width="30%" />
			<col width="70%" />
			<tbody>
				<tr>
					<td class="right"><span style="color: red;">*</span>任务名称：</td>
					<td><input type="text" name="args.name"  maxlength="100" style="width: 90%;"/>（必填）</td>
				</tr>
				<tr>
					<td class="right"><span style="color: red;">*</span>任务组：</td>
					<td><input type="text" name="args.group"  maxlength="100" style="width: 90%;"/>（必填）</td>
				</tr>
				<tr>
					<td class="right"><span style="color: red;">*</span>触发条件：</td>
					<td><input type="text" name="args.cron"  style="width: 90%;"/>（必填）</td>
				</tr>
				<tr>
					<td class="right"><span style="color: red;">*</span>处理类：</td>
					<td><input type="text" name="args.clazz"  style="width: 90%;"/>（必填）</td>
				</tr>
				<tr>
					<td class="right">开始时间：</td>
					<td><input type="text" name="args.startAt"  class="datepicker" style="width: 90%;"/></td>
				</tr>
				<tr>
					<td class="right">结束时间：</td>
					<td><input type="text" name="args.endAt"  class="datepicker"  style="width: 90%;"/></td>
				</tr>
				<tr>
					<td class="right">责任人：</td>
					<td><input type="text" name="args.personLiable"  style="width: 90%;"/></td>
				</tr>
				<tr>
					<td class="right">手机号码：</td>
					<td><input type="text" name="args.phone" style="width: 90%;" placeholder="多个号码用英文逗号隔开"/></td>
				</tr>
				<tr>
					<td class="right">misfire处理规则：</td>
					<td>
						<select name="args.misfire"  style="width: 90%;">
							<option value="0">不触发立即执行</option>
							<option value="1">以当前时间为触发频率立刻触发一次执行</option>
							<option value="2">以错过的第一个频率时间立刻开始执行</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="right"><span style="color: red;">*</span>监控服务：</td>
					<td>
						<select name="args.monitor" style="width: 90%;" value="1">
							<option value="0">不监控</option>
							<option value="1">监控</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="right"><span style="color: red;">*</span>任务描述：</td>
					<td><textarea rows="3" style="width: 90%;" name="args.description"></textarea>（必填）</td>
				</tr>
				<tr>
					<td class="right">备注：</td>
					<td><textarea rows="3" style="width: 90%;" name="args.remark"></textarea></td>
				</tr>
				<tr>
					<td class="right"></td>
					<td class="op"><input value="提交" type="button" class="submit"><input value="返回列表" type="button" onclick="window.location.href='./index.jsp';" style="width:100px;"></td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$.post("./scheduler/getJobInfo.action", {"id" : "${param.id}"}, function(data) {
				if(data.code > 0) {
					$("input[name='args.id']").val(data.data.id);
					$("input[name='args.name']").val(data.data.name);
					$("input[name='args.group']").val(data.data.group);
					$("input[name='args.cron']").val(data.data.cron);
					$("input[name='args.clazz']").val(data.data.clazz);
					$("input[name='args.startAt']").val(data.data.startAt);
					$("input[name='args.endAt']").val(data.data.endAt);
					$("select[name='args.misfire']").val(data.data.misfire);
					$("select[name='args.monitor']").val(data.data.monitor);
					$("textarea[name='args.description']").val(data.data.description);
					$("textarea[name='args.remark']").val(data.data.remark);
					$("input[name='args.personLiable']").val(data.data.personLiable);
					$("input[name='args.phone']").val(data.data.phone);
				}else{
					$(".submit").prop("disabled", true);
				}
			}, "json");
			$("[class='datepicker']").on('focus', function() {
				WdatePicker({
					dateFmt : 'yyyy-MM-dd HH:mm:ss'
				})
			});
			$(".submit").on("click", function(){
				if(!$("input[name='args.name']").val() || !$("input[name='args.group']").val() || !$("input[name='args.cron']").val() || !$("input[name='args.clazz']").val() || !$("textarea[name='args.description']").val()) {
					return;
				}
				
				$("#addForm").ajaxSubmit({
					url : "./scheduler/updateJob.action",
					type : "post",
					data :$("#addForm").serialize(),
					dataType : "json",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(data){
						alert(data.message);
						if(data.code > 0){
							window.location.href="./index.jsp";
						}
					}
				});
			});
		});
	</script>
</body>
</html>
