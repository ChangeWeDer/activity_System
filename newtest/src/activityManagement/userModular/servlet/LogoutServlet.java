package activityManagement.userModular.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 退出逻辑
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 安全退出： 删除掉session对象中指定的loginName属性即可
		// 1.得到session对象
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 2.删除属性
			session.removeAttribute("loginUser");
		}
		// 2.返回登录页面
		response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
