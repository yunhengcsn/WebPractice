package practice1.test;

import org.junit.Test;
import practice1.dao.UserDao;
import practice1.domain.User;
/**
 * UserDao�Ĳ�����
 * */
public class UserDaoTest {
	
	@Test
	public void testAddUser() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("����׳");
		user.setPassword("1234");
		userDao.addUser(user);
	}
	
	@Test
	public void testFindByName() {
		UserDao userDao = new UserDao();
		String name = "����";
		User user = userDao.findByName(name);
		System.out.println(user);
	}

}
