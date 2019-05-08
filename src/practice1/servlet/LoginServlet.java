package practice1.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.commons.CommonUtils;
import practice1.domain.User;
import practice1.service.UserException;
import practice1.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userService = new UserService();
		//封装表单数据
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//装载错误信息
		HashMap<String,String> errors = new HashMap<>();
		//表单校验
		String username = form.getUsername();
		if(username == null ||username.trim().length() == 0) {
			errors.put(username,"用户名不能为空");
		} else if(username.length() <3 || username.length() > 15) {
			errors.put(username,"用户名长度必须在3~15之间");
		}
		
		String password = form.getPassword();
		if(password == null ||password.trim().length() == 0) {
			errors.put(password,"密码不能为空");
		} else if(password.length() <3 || password.length() > 15) {
			errors.put(password,"密码长度必须在3~15之间");
		}
		
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()  + "/user/welcome.jsp");
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request,response);
		}
		
	}

}
