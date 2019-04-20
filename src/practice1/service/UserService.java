package practice1.service;

import practice1.dao.UserDao;
import practice1.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	/**
	 * 注册功能
	 * */	
	public void login(User user) throws UserException {
		
	}
	
	public void regist(User user) throws UserException {
		//使用用户名查询，返回null完成添加，否则抛出异常
		User _user = userDao.findByName(user.getUsername());
		if(_user != null) throw new UserException("用户名"+user.getUsername()+"，已被注册！");
		
		userDao.addUser(user);
	}

}
