package practice1.test;

import org.junit.Test;
import practice1.dao.UserDao;
import practice1.domain.User;
/**
 * UserDao的测试类
 * */
public class UserDaoTest {
	
	@Test
	public void testAddUser() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("王大壮");
		user.setPassword("1234");
		userDao.addUser(user);
	}
	
	@Test
	public void testFindByName() {
		UserDao userDao = new UserDao();
		String name = "李四";
		User user = userDao.findByName(name);
		System.out.println(user);
	}

}
