package practice1.service;

import practice1.dao.UserDao;
import practice1.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	/**
	 * ע�Ṧ��
	 * */	
	public void login(User user) throws UserException {
		
	}
	
	public void regist(User user) throws UserException {
		//ʹ���û�����ѯ������null�����ӣ������׳��쳣
		User _user = userDao.findByName(user.getUsername());
		if(_user != null) throw new UserException("�û���"+user.getUsername()+"���ѱ�ע�ᣡ");
		
		userDao.addUser(user);
	}

}
