<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
page
	import="activityManagement.userModular.entity.User,activityManagement.activityModular. dao.ActivityDao,
 
activityManagement.activityModular.entity.Activity, java.util.List,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>活动详情</title>
<link rel="stylesheet" href="../layui/css/layui.css">
</head>
<%
	User loginUser = (User) session.getAttribute("loginUser");
	String actId = request.getParameter("id");
	Activity act = null;
	ActivityDao activityDao = new ActivityDao();

	if (actId == null) {
		act = new Activity();
	} else {
		act = activityDao.queryById(Integer.parseInt(actId));
	}
	DateFormat df = new SimpleDateFormat("yyyy 年 MM 月 dd 日");	
%>
<body style="height: 80%">
	<script src="../layui/layui.js"></script>
	<table width="100%" height="100%">
		<tr align="center">
			<td valign="top">
				<table>

					<tr align="center">
						<td colspan="2">
							<h1>活动详情</h1>
							<br />
						<br />
						</td>
					</tr>
					<tr>
						<td><img src="<%=act.getActPicture()%>" width="400">&nbsp;&nbsp;&nbsp;</td>
						<td>主办方：<%=act.getSponsor()%><br /> 协办方：<%=act.getCoOrganizer()%><br />
							报 名 时 间 ： <%=act.getSignUpStartDate() == null ? "" : df.format(act.getSignUpStartDate())%>&nbsp;—&nbsp;
							<%=act.getSignUpEndDate() == null ? "" : df.format(act.getSignUpEndDate())%><br />
							活 动 时 间 ： <%=act.getActStartDate() == null ? "" : df.format(act.getActStartDate())%>&nbsp;—&nbsp;

							<%=act.getActEndDate() == null ? "" : df.format(act.getActEndDate())%><br />
							<br /> <br /> 活 动 内 容： <%
							
								if (act.getActContent() != null) {
							%> <%=act.getActContent().replaceAll("\n", "<br/>")
									
							%> <%
 	}
 %> <br /> <br /> <%
 	          if (loginUser == null) {
                           %> <a
							href="<%=request.getContextPath()%>/jsp/login.jsp">请先登录再报名</a> <%
 	}
 %> <%
 	          if (activityDao.isSignUp(loginUser.getId(), act.getId())) {
                              %> <label>已 报 名</label><br /> <br />
							<%
                              if(activityDao.isSignIn(loginUser.getId(), act.getId())){
                            	  %> <label>已 签 到</label><br /> <br />
							<%    
                              }
                              else{
                              %> <a
							href="<%=request.getContextPath()%>/signIn?userId=<%=loginUser.getId()%>&actId=<%=act.getId()%>"
							class="layui-btn layui-btn-sm layui-btn-radius">签到</a> <br />
						<br /> <%
                              }
 	           } else {
 	        	   	        	    	        	   	        	   
                           %> <a
							href="<%=request.getContextPath()%>/signUp?userId=<%=loginUser.getId()%>&actId=<%=act.getId()%>"
							class="layui-btn layui-btn-sm layui-btn-radius">报名</a> <br />
						<br /> <%
								}
							%> <br />
						<a href="http://localhost:8080/newtest/jsp/index.jsp"
							class="layui-btn layui-btn-sm layui-btn-radius">返回 </a>
						</td>
					</tr>

					<tr>

						<td></td>
					</tr>
				</table>
			</td>
		</tr>

	</table>
</body>
</html>

