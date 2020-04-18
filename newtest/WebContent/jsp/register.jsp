<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>新用户注册</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../layui/css/layui.css">

<!--布局框架-->
<link rel="stylesheet" type="text/css" href="../layui/css/util.css">

<!--主要样式-->
<link rel="stylesheet" type="text/css" href="../layui/css/main.css">
</head>
<body>
	<script src="../layui/layui.js"></script>

	<div class="login">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="../layui/img/img-01.png" alt="IMG">
				</div>

				<form class="layui-form"
					action="<%=request.getContextPath()%>/register" method="post">
					<span class="login100-form-title"> 用户注册 </span>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input type="text" name="userName" id="userName" required
								lay-verify="required" placeholder="请输入用户名" autocomplete="off"
								class="layui-input">
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input type="password" name="password" id="password" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux"></div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="name" id="name" required
								lay-verify="required" placeholder="请输入姓名" autocomplete="off"
								class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">性别</label>
						<div class="layui-input-block">
							<input type="radio" name="sex" id="sex" value="男" title="男"
								checked> <input type="radio" name="sex" id="sex"
								value="女" title="女">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">手机号码</label>
						<div class="layui-input-block">
							<input type="number" name="phoneNum" id="phoneNum" required
								lay-verify="required" placeholder="请输入手机号码" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux"></div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="formDemo"
								type="submit">注册</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							<button type="reset" class="layui-btn layui-btn-primary"
								onclick="javascript:history.go(-1);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		//Demo
		layui.use('form', function() {
			var form = layui.form;

		});
	</script>

</body>
</html>

