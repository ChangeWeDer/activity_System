package activityManagement.activityModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import activityManagement.activityModular.dao.ActivityDao;

public class CancelSignServlet extends HttpServlet {
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

		Boolean isSuccess = activityDao.cancelSign(Integer.parseInt(userId), Integer.parseInt(actId));
		if (isSuccess) {
			// 签到取消
			Boolean inSuccess = activityDao.cancelSignIn(Integer.parseInt(userId), Integer.parseInt(actId));
			out.print("<script language=\"javascript\" >alert('取消成功');"
					+ " location.href = './jsp/userPage.jsp'</script>");
			out.flush();
			out.close();
		} else {
			out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		}
	}

}
