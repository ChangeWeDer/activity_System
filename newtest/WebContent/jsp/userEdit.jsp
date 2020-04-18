<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="activityManagement.userModular.dao.UserDao,
activityManagement.userModular.entity.User, java.util.List,java.text.DateFormat,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户信息</title>
<link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body>
	<script src="../layui/layui.js"></script>

	<%
		String userId = request.getParameter("userId");
		UserDao userDao = new UserDao();
		User user = userDao.queryByUserId(Integer.parseInt(userId));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	%>



	<form class="layui-form"
		action="<%=request.getContextPath()%>/userEdit" method="post">
		<input type="hidden" id="userId" name="userId"
			value="<%=user.getId()%>" />
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline">
				<input type="text" name="userName" id="userName" required
					lay-verify="required" placeholder="请输入用户名" autocomplete="off"
					class="layui-input" value="<%=user.getUserName()%>">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="password" required
					lay-verify="required" placeholder="请输入密码" autocomplete="off"
					class="layui-input" value="<%=user.getPassword()%>">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="name" id="name" required
					lay-verify="required" placeholder="请输入姓名" autocomplete="off"
					class="layui-input" value="<%=user.getNickname()%>">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<input type="radio" name="sex" id="sex" value="男" title="男"
					<%if (user.getSex() != null && user.getSex().equals("男")) {%>
					checked <%}%>> <input type="radio" name="sex"
					id="sex" value="女" title="女"
					<%if (user.getSex() != null && user.getSex().equals("女")) {%>
					checked <%}%>>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">手机号码</label>
			<div class="layui-input-inline">
				<input type="number" name="phoneNum" id="phoneNum" required
					lay-verify="required" placeholder="请输入手机号码" autocomplete="off"
					class="layui-input" value="<%=user.getPhoneNum()%>">
			</div>
			<div class="layui-form-mid layui-word-aux"></div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo"
					type="submit">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary"
					onclick="javascript:history.go(-1);">返回</button>
			</div>
		</div>
	</form>

	<script>
//监听
layui.use('form', function() {
	var form = layui.form;

	
});
</script>


</body>

</html>


