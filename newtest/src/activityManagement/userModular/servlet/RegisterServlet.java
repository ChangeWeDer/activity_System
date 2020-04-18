package activityManagement.userModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import activityManagement.userModular.dao.UserDao;
import activityManagement.userModular.entity.User;

public class RegisterServlet extends HttpServlet {
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
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String nickname = req.getParameter("name");
		String sex = req.getParameter("sex");
		String phoneNum = req.getParameter("phoneNum");

		UserDao userDao = new UserDao();
		User user = userDao.queryByUserName(userName);
		if (user != null) {
			out.print("<script language=\"javascript\" >alert('用户已存在');"
					+ " location.href = './jsp/register.jsp';</script>");
			out.flush();
			out.close();
			return;
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setRegisterDate(new Date());
		user.setSex(sex);
		user.setPhoneNum(phoneNum);

		Boolean isSuccess = userDao.save(user);
		if (isSuccess) {
			out.print("<script language=\"javascript\" >alert('注册成功，正在返回登陆界面');"
					+ " location.href = './jsp/login.jsp';</script>");
			out.flush();
			out.close();
		} else {
			out.print("<script language=\"javascript\" >alert('注册失败');"
					+ " location.href = './jsp/register.jsp';</script>");
			out.flush();
			out.close();
		}
	}
}
