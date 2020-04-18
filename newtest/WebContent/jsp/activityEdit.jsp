<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="activityManagement.activityModular.dao.ActivityDao,
activityManagement.activityModular.entity.Activity, java.util.List,java.text.DateFormat,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活动信息</title>
<link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body style="height: 80%">
	<script src="../layui/layui.js"></script>
	<%
		String actId = request.getParameter("id");
		Activity act = null;
		if (actId == null) {
			act = new Activity();
		} else {
			ActivityDao activityDao = new ActivityDao();
			act = activityDao.queryById(Integer.parseInt(actId));
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<form class="layui-form" id="actForm"
		action="<%=request.getContextPath()%>/activityEdit" method="post"
		enctype="multipart/form-data">
		<input type="hidden" id="actId" name="actId"
			value="<%=act.getId() != null ? act.getId() : ""%>" />
		<div class="layui-form-item">
			<label class="layui-form-label">活动图片</label>
			<td>
				<%
								if (act.getActPicture() != null && !"".equals(act.getActPicture())) {
							%> <img alt="" src="<%=act.getActPicture()%>" width="100">
				<%
								}
							%><input type="file" name="actPicture" id="actPicture" />
			</td>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">活动名称</label>
			<div class="layui-input-inline">
				<input type="text"
					value="<%=act.getActName() != null ? act.getActName() : ""%>"
					name="actName" id="actName" required lay-verify="required"
					placeholder="请输入活动名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">主办方</label>
			<div class="layui-input-inline">
				<input type="text"
					value="<%=act.getSponsor() != null ? act.getSponsor() : ""%>"
					name="sponsor" id="sponsor" required lay-verify="required"
					placeholder="请输入主办方" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">协办方</label>
			<div class="layui-input-inline">
				<input type="text"
					value="<%=act.getCoOrganizer() != null ? act.getCoOrganizer() : ""%>"
					name="coOrganizer" id="coOrganizer" required lay-verify="required"
					placeholder="请输入协办方" autocomplete="off" class="layui-input">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">报名开始时间</label>
			<div class="layui-input-inline">
				<input type="date"
					value="<%=act.getSignUpStartDate() != null ? df.format(act.getSignUpStartDate()) : ""%>"
					name="signUpStart" id="signUpStart" required lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">报名结束时间</label>
			<div class="layui-input-inline">
				<input type="date"
					value="<%=act.getSignUpEndDate() != null ? df.format(act.getSignUpEndDate()) : ""%>"
					name="signUpEnd" id="signUpEnd" required lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">活动开始时间</label>
			<div class="layui-input-inline">
				<input type="date"
					value="<%=act.getActStartDate() != null ? df.format(act.getActStartDate()) : ""%>"
					name="actStart" id="actStart" required lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">活动结束时间</label>
			<div class="layui-input-inline">
				<input type="date"
					value="<%=act.getActEndDate() != null ? df.format(act.getActEndDate()) : ""%>"
					name="actEnd" id="actEnd" required lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">活动内容</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" class="layui-textarea" rows="5"
					form="actForm" name="actContent" id="actContent"><%=act.getActContent() != null ? act.getActContent() : ""%></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary"
					onclick="javascript:history.go(-1);">返回</button>
			</div>
		</div>
	</form>

	<script>
					//Demo
					layui.use('form', function() {
						var form = layui.form;

						//监听提交
						form.on('submit(formDemo)', function(data) {
							return;
						});
					});
				</script>
</body>
</html>
