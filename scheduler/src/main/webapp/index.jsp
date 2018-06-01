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
</head>
<body>
	<div style="padding: 10px 0px;text-align: center;font-size: 18pt;">定时任务管理</div>
	<div style="width: 100%;">
	<div style="padding: 2px;">
		<div>
			<input type="button" value="添加任务" class="addTask"  style="height: 22px;margin: 2px;" />
			<input type="button" value="重新载入任务" class="reload"   style="height: 22px;margin: 2px;"/>
			<input type="button" value="全部停止" class="pauseAll"   style="height: 22px;margin: 2px;"/>
			<input type="button" value="全部恢复"  class="resumeAll"  style="height: 22px;margin: 2px;"/>
			刷新频率：<select id="rate">
				<option value="3">3秒钟</option>
				<option value="5">5秒钟</option>
				<option value="10" selected="selected">10秒钟</option>
				<option value="30">30秒钟</option>
				<option value="60">60秒钟</option>
				<option value="300">5分钟</option>
			</select>
		</div>
		<table class="task-table" border="1">
			<col width="50" />
			<col width="150" />
			<col width="150" />
			<col width="150" />
			<col width="180" />
			<col width="120" />
			<col width="140" />
			<col width="140" />
			<col width="50" />
			<col width="80" />
			<col width="220" />
			<thead>
				<tr>
					<th>任务ID</th>
					<th>任务名称</th>
					<th>任务组</th>
					<th>任务描述</th>
					<th>执行类</th>
					<th>触发条件</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>misfire处<br/>理规则</th>
					<th>运行状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="template"  data-value="id" >
					<td class="center" data-id="id"></td>
					<td class="left" data-id="name"></td>
					<td class="left" data-id="group"></td>
					<td class="left" data-id="description"></td>
					<td class="left" data-id="clazz"></td>
					<td class="left" data-id="cron"></td>
					<td class="center" data-id="startAt"></td>
					<td class="left" data-id="endAt"></td>
					<td class="center" data-id="misfire"></td>
					<td class="center" data-id="stateName"></td>
					<td class="center op" data-state="state"><input type="button" value="开始" class="run" /><input type="button" value="执行" class="runnow" /><input type="button" value="修改" class="update" /><input type="button" value="删除" class="delete" /></td>
				</tr>
			</tbody>
		</table>
		<div>
		<p style="font-size: 12pt;"><em>注意：</em></p>
		<p>misfire任务处理规则取值释意：<span style="font-weight: bold;color: red;">0表示不触发立即执行，1表示以当前时间为触发频率立刻触发一次执行，2表示以错过的第一个频率时间立刻开始执行。</span></p>
		
		<p><span style="font-weight: bold;color: red;">重新载入：</span>停止所有任务，等待所有任务执行完成，从数据库重新加载所有更新后的任务。</p>
		<p><span style="font-weight: bold;color: red;">全部停止：</span>停止所有任务的触发，正在运行的任务直到运行的任务停止。</p>
		<p><span style="font-weight: bold;color: red;">全部恢复：</span>在原来数据基础上恢复所有停止的任务（更新后的数据无效）。</p>
		<p><span style="font-weight: bold;color: red;">恢复：</span>在原来数据基础上恢复单个停止的任务（更新后的数据无效）。</p>
		<p><span style="font-weight: bold;color: red;">开始：</span>按照最新的设置执行任务。</p>
		<p><span style="font-weight: bold;color: red;">删除：</span>停止当前任务，但未完成的任务会继续执行完成后结束。</p>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="./res/js/scheduler.js?v=0308"></script>
</body>
</html>
