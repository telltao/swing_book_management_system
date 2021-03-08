package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.User;

public class UserDao {

	/**
	 * ��¼��֤
	 * 
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */

	public User login(Connection con, User user) throws Exception {
		// ��¼��ȷ�������û�������ʵ����Ϣ
		User resultUser = null;
		String sql = "select * from t_user where userName=? and passWord=?";
		// ��ȡPreparedStatement�ӿ�
		PreparedStatement pstmt = con.prepareStatement(sql);
		// ����δ֪����ֵ
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		// ����ResultSet�����
		ResultSet rs = pstmt.executeQuery();

		// rs.next()ָ����е�һ������ ����һ����Ч���򷵻�true��������ָ��ڶ���
		if (rs.next()) {
			// ���û�����ʵ����,ȡ���е�set����;
			resultUser = new User();
			// ȡ��һ��id������Ե����ݣ���������ظ�Userʵ�����Ϣ
			resultUser.setId(rs.getInt("id"));
			// ȡ��һ��UserName������Ե����ݣ���������ظ�Userʵ�����Ϣ
			resultUser.setUserName(rs.getString("userName"));
			// ȡ��һ��PassWord������Ե����ݣ���������ظ�Userʵ�����Ϣ
			resultUser.setPassWord(rs.getString("passWord"));
		}
		return resultUser;
	}
}
