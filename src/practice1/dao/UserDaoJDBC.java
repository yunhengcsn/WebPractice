package practice1.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import practice1.domain.User;
import tools.jdbc.TxQueryRunner;

public class UserDaoJDBC {
	QueryRunner qr = new TxQueryRunner();
	
	public void addUser(User user) {
		String sql = "insert into user values(?,?)";
		Object[] params = {user.getUsername(),user.getPassword()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByName(String username) {
		try {
			String sql = "select * from user where username = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
