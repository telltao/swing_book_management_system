package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	/**
	 * 连接数据库工具类
	 */
	// mysql数据库地址
	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";

	// 用户名
	private String dbUserName = "root";

	// 密码
	private String dbPassWord = "123456";

	// 驱动名称
	private String jdbcname = "com.mysql.jdbc.Driver";

	// 数据库连接方法
	public Connection getCon() throws Exception {
		// 加载数据库驱动
		Class.forName(jdbcname);
		// 数据库驱动管理类
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
		return con;
	}

	// 数据库关闭方法
	public void close(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

}
