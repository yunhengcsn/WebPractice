package practice1.service;

import practice1.dao.UserDaoXML;
import practice1.domain.User;

public class UserService {
	private UserDaoXML userDao = new UserDaoXML();
	/**
	 * 注册功能
	 * */	
	public User login(User form) throws UserException {
		//使用dao查询username
		User _user = userDao.findByName(form.getUsername());
		if(_user == null)//没找到用户
			throw new UserException("用户名"+form.getUsername()+"不存在！");
		else if(_user.getPassword() != form.getPassword())//密码错误
			throw new UserException("用户名"+form.getUsername()+"密码错误！");
		else {//用户名密码一致
			return _user;//返回所有用户信息
		}
	}
	
	public void regist(User user) throws UserException {
		//使用用户名查询，返回null完成添加，否则抛出异常
		User _user = userDao.findByName(user.getUsername());
		if(_user != null) throw new UserException("用户名"+user.getUsername()+"，已被注册！");
		
		userDao.addUser(user);
	}

}
