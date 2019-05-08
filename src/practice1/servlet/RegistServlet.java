package practice1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.commons.CommonUtils;
import practice1.domain.User;
import practice1.service.UserException;
import practice1.service.UserService;

public class RegistServlet extends HttpServlet {
	/**
	 * 封装表单数据到User对象中
	 * 
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置post请求字符编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);//map中键的名称与属性名必须一致！！！
		
		//表单校验
		HashMap<String,String> errors = new HashMap<>();//装载错误信息
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username","用户名不能为空");
		} else if(username.length() < 3 || username.length() > 15) {
			errors.put("username","用户名长度必须在3~15之间");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password","密码不能为空");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password","密码长度必须在3~15之间");
		}
		
		if(errors != null && errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		//调用userService的regist方法，传递form
		//得到异常：获取异常信息，保存至request域，转发至regist。jsp显示
		//否则输出注册成功
		try {
			userService.regist(form);
			response.getWriter().print("<h1>注册成功</h1></br> <a href='"+
													request.getContextPath()+"/user/login.jsp"+"'>点击登录</a>");//路径以/开头相对当前主机
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);//以/开头，路径相对于当前项目路径
		}
		
	}

}
