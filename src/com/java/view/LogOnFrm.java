package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

@SuppressWarnings("serial")
public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userName_txt;
	private JPasswordField passWord_txt;

	private static DbUtil dbUtil = new DbUtil();
	private static UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogOnFrm() {

		// 改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		@SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}

		setResizable(false);
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/logo.png")));
		label.setFont(new Font("宋体", Font.PLAIN, 24));

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/userName.png")));

		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));

		userName_txt = new JTextField();
		userName_txt.setColumns(10);

		passWord_txt = new JPasswordField();

		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 登录方法引用
				loginActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 重置方法引用
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/reset.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(152)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(label)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
										.addGap(6).addComponent(userName_txt, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(button).addGap(85)
										.addComponent(button_1))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(passWord_txt,
												GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
						.addGap(127)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(47).addComponent(label).addGap(27)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(lblNewLabel))
								.addComponent(userName_txt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(41).addComponent(label_1))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(38).addComponent(passWord_txt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(58).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(button_1))));
		contentPane.setLayout(gl_contentPane);
		
		//设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * 登录事件处理
	 * 
	 */
	private void loginActionPerformed(ActionEvent evt) { 
		// 从界面获取用户名和密码
		String userName = this.userName_txt.getText();
		String passWord = new String(this.passWord_txt.getPassword());
		// 判断用户名和密码是否为空
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;// 结束方法，不再继续执行
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		
		// 进行数据库连接
		User user=new User(userName,passWord);
		Connection con = null;
		try {
			con=dbUtil.getCon();
			/**
			 * 将从数据库中获取的用户信息保存在currentUser里面，
			 * 若为null，则表示从数据库里获取的信息和从用户登陆界面获取的信息不一致，
			 * 即登录失败
			 */
			User currentUser=userDao.login(con, user);
			if(currentUser!=null){
				//JOptionPane.showMessageDialog(null, "登录成功！");
				//销毁"登录成功！"的窗口
				dispose();
				//将系统主界面显示出来
				new MainFrm().setVisible(true);;
			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 重置事件处理
	 * 
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		// 将用户名和密码都设置为空
		this.userName_txt.setText("");
		this.passWord_txt.setText("");
	}

}
