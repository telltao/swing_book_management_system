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

		// �ı�ϵͳĬ������
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
		label.setFont(new Font("����", Font.PLAIN, 24));

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
				// ��¼��������
				loginActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���÷�������
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
		
		//����JFrame������ʾ
		this.setLocationRelativeTo(null);
	}

	/**
	 * ��¼�¼�����
	 * 
	 */
	private void loginActionPerformed(ActionEvent evt) { 
		// �ӽ����ȡ�û���������
		String userName = this.userName_txt.getText();
		String passWord = new String(this.passWord_txt.getPassword());
		// �ж��û����������Ƿ�Ϊ��
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
			return;// �������������ټ���ִ��
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			return;
		}
		
		// �������ݿ�����
		User user=new User(userName,passWord);
		Connection con = null;
		try {
			con=dbUtil.getCon();
			/**
			 * �������ݿ��л�ȡ���û���Ϣ������currentUser���棬
			 * ��Ϊnull�����ʾ�����ݿ����ȡ����Ϣ�ʹ��û���½�����ȡ����Ϣ��һ�£�
			 * ����¼ʧ��
			 */
			User currentUser=userDao.login(con, user);
			if(currentUser!=null){
				//JOptionPane.showMessageDialog(null, "��¼�ɹ���");
				//����"��¼�ɹ���"�Ĵ���
				dispose();
				//��ϵͳ��������ʾ����
				new MainFrm().setVisible(true);;
			}else{
				JOptionPane.showMessageDialog(null, "�û������������");
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
	 * �����¼�����
	 * 
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		// ���û��������붼����Ϊ��
		this.userName_txt.setText("");
		this.passWord_txt.setText("");
	}

}
