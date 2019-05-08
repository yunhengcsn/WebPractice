package practice1.test;

import org.junit.Test;

import practice1.dao.UserDaoJDBC;
import practice1.dao.UserDaoXML;
import practice1.domain.User;
/**
 * UserDao的测试类
 * */
public class UserDaoTest {
	
	@Test
	public void testAddUser() {
		UserDaoXML userDao = new UserDaoXML();
		User user = new User();
		user.setUsername("王小五");
		user.setPassword("1234");
		userDao.addUser(user);
	}
	
	@Test
	public void testFindByName() {
		UserDaoXML userDao = new UserDaoXML();
		String name = "李四";
		User user = userDao.findByName(name);
		System.out.println(user);
	}
	@Test
	public void testAddUserJDBC() {
		UserDaoJDBC userDao = new UserDaoJDBC();
		User user = new User();
		user.setUsername("赵小一");
		user.setPassword("1234");
		userDao.addUser(user);
	}
	
	@Test
	public void testFindByNameJDBC() {
		UserDaoJDBC userDao = new UserDaoJDBC();
		String name = "李四";
		
		User user = userDao.findByName(name);
		System.out.println(user);
	}

}
