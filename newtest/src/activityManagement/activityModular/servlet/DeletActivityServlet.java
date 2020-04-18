package activityManagement.activityModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import activityManagement.activityModular.dao.ActivityDao;

public class DeletActivityServlet extends HttpServlet {
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

		String actId = req.getParameter("actId");

		if (actId == null || "".equals(actId)) {
			out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		}
		ActivityDao activityDao = new ActivityDao();

		Boolean isSuccess = activityDao.deletActivity(Integer.parseInt(actId));
		if (isSuccess) {
			out.print("<script language=\"javascript\" >alert('删除成功');"
					+ " location.href = './jsp/adminPage.jsp'</script>");
			out.flush();
			out.close();
		} else {
			out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		}
	}

}
