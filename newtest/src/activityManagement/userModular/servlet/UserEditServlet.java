package activityManagement.userModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import activityManagement.userModular.dao.UserDao;
import activityManagement.userModular.entity.User;

public class UserEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 初始化
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		System.out.println(userName);
		String password = req.getParameter("password");
		String nickname = req.getParameter("name");
		String sex = req.getParameter("sex");
		String phoneNum = req.getParameter("phoneNum");
		UserDao userDao = new UserDao();
		User user = new User();
		user.setId(Integer.parseInt(userId));
		user.setUserName(userName);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setSex(sex);
		user.setPhoneNum(phoneNum);
		Boolean isSuccess = userDao.update(user);
		if (isSuccess) {
			User loginUser = (User) req.getSession().getAttribute("loginUser");
			out.print("<script language=\"javascript\" >alert('修改成功');"
					+ " window.location.href = '/newtest/jsp/index.jsp';</script>");
			out.flush();
			out.close();

		} else {
			out.print("<script language=\"javascript\" >alert('修改失败');" + " javascript:history.go(-1)</script>");
			out.flush();
			out.close();
		}
	}
}
