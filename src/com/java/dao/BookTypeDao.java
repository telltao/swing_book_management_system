package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.model.BookType;
import com.java.util.StringUtil;

/**
 * ͼ�����Dao
 * @author 
 *
 */
public class BookTypeDao {

	//ͼ�������ӷ���
	public int add_Book(Connection con,BookType bookType) throws Exception{
		String sql="insert into t_booktype values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		//���ز����Ľ��
		return pstmt.executeUpdate();
	}
	
	//ͼ������ѯ�ļ���
	public ResultSet list(Connection con,BookType bookType) throws Exception{
		//��̬��ϣ���StringBuffer�ȽϺ�
		StringBuffer sb=new StringBuffer("select * from t_booktype");
		//sql����ѯ���������ж��ʱ������and��ʱ���where
		if(StringUtil.isNotEmpty(bookType.getBookTypeName())){
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
		}
		//����replaceFirst������and�滻��
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	//ͼ������޸ķ���
	public int update_Book(Connection con,BookType booktype) throws Exception{
		String sql="update t_booktype set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, booktype.getBookTypeName());
		pstmt.setString(2, booktype.getBookTypeDesc());
		pstmt.setInt(3, booktype.getId());
		return pstmt.executeUpdate();
	}
	
	//ͼ�����ɾ������
	public int delete_Book(Connection con,String id) throws Exception{
		String sql="delete from t_booktype where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
}
