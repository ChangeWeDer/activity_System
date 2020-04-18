<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>用户登陆</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../layui/css/layui.css">
<!--图标-->
<link rel="stylesheet" type="text/css"
	href="../layui/css/font-awesome.min.css">


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

				<form class="login100-form validate-form"
					action="<%=request.getContextPath()%>/loginCheck" method="post">
					<span class="login100-form-title"> 用户登陆 </span>

					<div class="wrap-input100 validate-input">
						<input class="input100" type="text" name="userName" id="userName"
							placeholder="用户名"> <span class="focus-input100"></span> <span
							class="symbol-input100"> <i class="fa fa-envelope"
							aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input">
						<input class="input100" type="password" name="password"
							id="password" placeholder="密码"> <span
							class="focus-input100"></span> <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>

					</div>


					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">登陆</button>
					</div>

					<div class="text-center p-t-12">
						<a class="txt2" href="javascript:" onclick="check()"> 忘记密码？ </a>
					</div>

					<div class="text-center p-t-12">
						<a class="txt2" href="./register.jsp"> 还没有账号？立即注册 <i
							class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function check() {
			// to do somethink
			layui.use('layer', function() {
				var layer = layui.layer;

				layer.msg('由于经费有限，实在没钱去买手机短信验证改密码，直接联系管理员改密码吧~');
			});

			return false; // 返回值 让表单不提交 ture 提交
		}
	</script>
</body>
</html>