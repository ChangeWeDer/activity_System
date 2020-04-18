package activityManagement.activityModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import activityManagement.activityModular.dao.ActivityDao;
import activityManagement.activityModular.entity.Activity;

public class SignInServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");
		String actId = req.getParameter("actId");

		if (userId == null || "".equals(userId) || actId == null || "".equals(actId)) {
			out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		}
		ActivityDao activityDao = new ActivityDao();
		// 设置签到限制
		Date now = new Date();
		Activity act = null;
		act = activityDao.queryById(Integer.parseInt(actId));
		Date head = act.getActStartDate();
		Date tail = act.getActEndDate();
		if (now.getTime() < head.getTime()) {
			System.out.println("活动未开始");
			out.print("<script language=\"javascript\" >alert('活动未开始');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		} else if (now.getTime() > tail.getTime()) {
			System.out.println("活动已结束");
			out.print("<script language=\"javascript\" >alert('活动已结束');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		} else {

			Boolean isSuccess = activityDao.signIn(Integer.parseInt(userId), Integer.parseInt(actId));
			if (isSuccess) {
				resp.sendRedirect("./jsp/activityDetail.jsp?id=" + actId);
			} else {
				out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
				out.flush();
				out.close();
			}
		}
	}
}
