package practice1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import practice1.domain.User;
import practice1.service.UserException;
import practice1.service.UserService;

public class RegistServlet extends HttpServlet {
	/**
	 * ��װ�����ݵ�User������
	 * 
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����post�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);//map�м�������������������һ�£�����
		
		//��У��
		HashMap<String,String> errors = new HashMap<>();//װ�ش�����Ϣ
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username","�û�������Ϊ��");
		} else if(username.length() < 3 || username.length() > 15) {
			errors.put("username","�û������ȱ�����3~15֮��");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password","���벻��Ϊ��");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password","���볤�ȱ�����3~15֮��");
		}
		
		if(errors != null && errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		//����userService��regist����������form
		//�õ��쳣����ȡ�쳣��Ϣ��������request��ת����regist��jsp��ʾ
		//�������ע��ɹ�
		try {
			userService.regist(form);
			response.getWriter().print("<h1>ע��ɹ�</h1></br> <a href='"+
													request.getContextPath()+"/user/login.jsp"+"'>�����¼</a>");//·����/��ͷ��Ե�ǰ����
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);//��/��ͷ��·������ڵ�ǰ��Ŀ·��
		}
		
	}

}
