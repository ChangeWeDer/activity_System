<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="activityManagement.userModular.dao.UserDao,activityManagement.activityModular.dao.ActivityDao,
activityManagement.activityModular.entity.Activity,activityManagement.userModular.entity.User, java.util.List,java.text.DateFormat,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统管理</title>
<link rel="stylesheet" href="../layui/css/layui.css">

</head>
<%
	DateFormat df = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
%>
<body>
	<script src="../layui/layui.js"></script>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">用户管理</li>
			<li>活动管理</li>
			<li>当前活动</li>
			<li>活动发布</li>
			<li class="layui-nav-item layui-layout-right"><a
				href="${pageContext.request.contextPath}/logout">管理员登出</a></li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">

				<table class="layui-table">
					<colgroup>
						<col width="150">
						<col width="200">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>姓名</th>
							<th>性别</th>
							<th>手机号码</th>
							<th>注册日期</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<%
							UserDao userDao = new UserDao();
							List<User> userList = userDao.queryAllList();
							for (int i = 0; i < userList.size(); i++) {
								User user = userList.get(i);
						%>
						<tr>
							<td><%=i + 1%></td>
							<td><%=user.getUserName()%></td>
							<td><%=user.getNickname()%></td>
							<td>
								<%
									if (user.getSex() != null) {
								%><%=user.getSex().equals("男") ? "男" : "女"%> <%
 	}
 %>
							</td>

							<td><%=user.getPhoneNum()%></td>
							<td>
								<%
									if (user.getRegisterDate() != null) {
								%><%=df.format(user.getRegisterDate())%> <%
 	}
 %>
							</td>
							<td><a
								href="${pageContext.request.contextPath}/jsp/userEdit.jsp?userId=<%=user.getId() %>"
								class="layui-btn layui-btn-sm layui-btn-radius">修改</a>
							<td><a
								href="${pageContext.request.contextPath}/deletUser?userId=<%=user.getId() %>"
								class="layui-btn layui-btn-sm layui-btn-radius">删除</a></td>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
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
							<th>主办方</th>
							<th>协办方</th>
							<th>报名开始时间</th>
							<th>报名结束时间</th>
							<th>活动开始时间</th>
							<th>活动结束时间</th>
							<th>活动报名人数</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>


						<%
							ActivityDao activityDao = new ActivityDao();
							List<Activity> actList = activityDao.queryAllList();
							for (int i = 0; i < actList.size(); i++) {
								Activity act = actList.get(i);
						%>
						<tr>
							<td><%=i + 1%></td>
							<td><%=act.getActName()%></td>
							<td><%=act.getSponsor()%></td>
							<td><%=act.getCoOrganizer()%></td>
							<td><%=act.getSignUpStartDate() == null ? "" : df.format(act.getSignUpStartDate())%></td>
							<td><%=act.getSignUpEndDate() == null ? "" : df.format(act.getSignUpEndDate())%></td>
							<td><%=act.getActStartDate() == null ? "" : df.format(act.getActStartDate())%></td>
							<td><%=act.getActEndDate() == null ? "" : df.format(act.getActEndDate())%></td>
							<td align="center"> <a style="color:red"
								href="signUpDetail.jsp?id=<%=act.getId()%>"><%=activityDao.getSingUpNum(act.getId())%></a>
							</td>
							<td><a
								href="${pageContext.request.contextPath}/jsp/activityEdit.jsp?id=<%=act.getId()%>"
								class="layui-btn layui-btn-sm layui-btn-radius">修改</a>
							<td><a
								href="${pageContext.request.contextPath}/deletActivity?actId=<%=act.getId()%>"
								class="layui-btn layui-btn-sm layui-btn-radius">删除</a></td>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

			</div>
			<div class="layui-tab-item">

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
				<%
					String actId = request.getParameter("id");
					Activity act = null;
					if (actId == null) {
						act = new Activity();
					} else {
						ActivityDao newActivityDao = new ActivityDao();
						act = activityDao.queryById(Integer.parseInt(actId));
					}
				%>

				<form class="layui-form" id="actForm"
					action="<%=request.getContextPath()%>/activityEdit" method="post"
					enctype="multipart/form-data">
					<input type="hidden" id="actId" name="actId" class="layui-input"
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
								name="coOrganizer" id="coOrganizer" required
								lay-verify="required" placeholder="请输入协办方" autocomplete="off"
								class="layui-input">
						</div>
					</div>


					<div class="layui-form-item">
						<label class="layui-form-label">报名开始时间</label>
						<div class="layui-input-inline">
							<input type="date"
								value="<%=act.getSignUpStartDate() != null ? df.format(act.getSignUpStartDate()) : ""%>"
								name="signUpStart" id="signUpStart" required
								lay-verify="required" autocomplete="off" class="layui-input">
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
							<textarea name="desc" placeholder="请输入内容" class="layui-textarea"
								rows="5" form="actForm" name="actContent"><%=act.getActContent() != null ? act.getActContent() : ""%></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>

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
			</div>


		</div>
	</div>

	<script>
		//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			var element = layui.element;

			//…
		});
	</script>
</body>
</html>