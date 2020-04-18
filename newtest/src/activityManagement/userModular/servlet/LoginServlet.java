package activityManagement.userModular.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import activityManagement.userModular.dao.UserDao;
import activityManagement.userModular.entity.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("UTF-8"); 
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String str = new String(req.getParameter("userName").getBytes("iso-8859-1"), "utf-8");
		System.out.printf(str);
		User user = checkLogin(userName, password);
		if (user != null) {
			req.getSession().setAttribute("loginUser", user);
			if ("admin".equals(user.getUserName())) {
				resp.sendRedirect("./jsp/adminPage.jsp");
			} else {
				req.getRequestDispatcher("./jsp/index.jsp").forward(req, resp);
			}
		} else {
			out.print("<script language=\"javascript\" >alert('密码或用户错误，请重新输入!');"
					+ " window.location.href = '/newtest/jsp/login.jsp';</script>");
			out.flush();
			out.close();

		}
	}

	private User checkLogin(String userName, String password) {
		if (userName == null || password == null) {
			return null;
		}
		UserDao userDao = new UserDao();
		User user = userDao.queryByUserName(userName);
		if (user == null) {
			return null;
		}
		String pw = user.getPassword();
		if (password.equals(pw)) {
			return user;
		} else {
			return null;
		}
	}

}
