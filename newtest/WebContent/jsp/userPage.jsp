<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@	page
	import="activityManagement.userModular.entity.User,activityManagement.activityModular. dao.ActivityDao,
activityManagement.userModular.dao.UserDao,activityManagement.activityModular.entity.Activity, java.util.List,java.text.DateFormat,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>活动主页</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../layui/css/layui.css">


</head>
<%
	User loginUser = (User) session.getAttribute("loginUser");
	ActivityDao activityDao = new ActivityDao();
	List<Activity> actList = activityDao.queryAllList();
	ActivityDao activityDao1 = new ActivityDao();
	List<Activity> actList1 = activityDao1.querySignUpList(loginUser.getId());
	
	
	DateFormat df = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
%>
<body>
	<script src="../layui/layui.js"></script>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">活动首页</li>
			<li>已报名活动</li>
			<li>修改个人信息</li>
			<li class="layui-nav-item layui-layout-right"><a
				href="${pageContext.request.contextPath}/logout">用户登出</a></li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">

				<table>
					<tr align="center">
						<td valign="top">
							<%
									for (int i = 0; i < actList.size(); i++) {
											Activity act = actList.get(i);
								%>
						
					<tr>
						<td><img src="<%=act.getActPicture()%>" width="200">&nbsp;&nbsp;</td>
						<td><%=act.getActName()%><br /> 主办方：<%=act.getSponsor()%><br />
							协办方：<%=act.getCoOrganizer()%><br /> 报 名 时 间 ： <%=act.getSignUpStartDate() == null ? "" : df.format(act.getSignUpStartDate())%>&nbsp;—&nbsp;
							<%=act.getSignUpEndDate() == null ? "" : df.format(act.getSignUpEndDate())%><br />
							活 动 时 间 ： <%=act.getActStartDate() == null ? "" : df.format(act.getActStartDate())%>&nbsp;—&nbsp;
							<%=act.getActEndDate() == null ? "" : df.format(act.getActEndDate())%><br />
							<br /> <a
							href="${pageContext.request.contextPath}/jsp/activityDetail.jsp?id=<%=act.getId()%>"
							class="layui-btn layui-btn-sm layui-btn-radius">活动详情</a><br />
						<br /></td>
					</tr>
					<tr>
						<td colspan="2"></td>
					</tr>
					<%
									}
								%>
					</td>
					</tr>
				</table>

			</div>
			<div class="layui-tab-item">

				<table class="layui-table">
					<colgroup>
						<col width="150">
						<col width="200">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>序号</th>
							<th>活动名称</th>
							<th>活动开始时间</th>
							<th>活动结束时间</th>
							<th>详情</th>
							<th>取消</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = 0; i < actList1.size(); i++) {
							Activity act = actList1.get(i);
					%><tr>
							<td><%=i + 1%></td>
							<td><%=act.getActName()%></td>
							<td><%=act.getActStartDate() == null ? "" : df.format(act.getActStartDate())%>
							</td>
							<td><%=act.getActEndDate() == null ? "" : df.format(act.getActEndDate())%>
							</td>
							<td><a
								href="${pageContext.request.contextPath}/jsp/activityDetail.jsp?id=<%=act.getId()%>"
								class="layui-btn layui-btn-sm layui-btn-radius">活动详情</a>
							<td><a
								href="${pageContext.request.contextPath}/cancelSign?userId=<%=loginUser.getId()%>&actId=<%=act.getId()%>"
								class="layui-btn layui-btn-sm layui-btn-radius">取消报名</a></td>
							</td>
						</tr>
						<%
}
%>
					</tbody>
				</table>
				<%

UserDao userDao = new UserDao();
User user = userDao.queryByUserId(loginUser.getId());
								%>


			</div>
			<div class="layui-tab-item">

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
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script>
//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
  
  //…
});
layui.use('form', function() {
	var form = layui.form;

	
});
</script>
</body>

</html>

