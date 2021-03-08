package com.java.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.java.dao.BookTypeDao;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

@SuppressWarnings("serial")
public class BookType_Add_InterFrm extends JInternalFrame {
	
	//JTextField��JTextArea��ʵ����
	private JTextField bookTypeName_Txt;
	private JTextArea bookTypeDesc_Txt;

	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookType_Add_InterFrm frame = new BookType_Add_InterFrm();
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
	public BookType_Add_InterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 566, 393);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		bookTypeName_Txt = new JTextField();
		bookTypeName_Txt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		
		bookTypeDesc_Txt = new JTextArea();
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����¼�����
				bookTypeAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookType_Add_InterFrm.class.getResource("/images/add.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����¼�����
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookType_Add_InterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(146, Short.MAX_VALUE)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bookTypeName_Txt, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(bookTypeDesc_Txt))
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(143)
							.addComponent(button_1)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(bookTypeDesc_Txt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(40))
		);
		getContentPane().setLayout(groupLayout);
		
		//����JTextArea�߿�Ĵ���
		bookTypeDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}
	
	//����¼�����
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		
		//�ӽ����ȡ�û����ͼ��������ƺ�ͼ�����������Ϣ
		String bookTypeName=this.bookTypeName_Txt.getText();
		String bookTypeDesc=this.bookTypeDesc_Txt.getText();
		//�ж�ͼ����������Ƿ�Ϊ��
		if(StringUtil.isEmpty(bookTypeName)){
			JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ�գ�");
			return;
		}
		
		//�������ݿ�����
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();//�����������ݿ�
			int result=bookTypeDao.add_Book(con,bookType);//���ص���Int���ͣ���ʾִ���˼�������
			if(result==1){
				JOptionPane.showMessageDialog(null, "ͼ�������ӳɹ���");
				resetValue();//��ӳɹ���Ҫ������������������
			}else{
				JOptionPane.showMessageDialog(null, "ͼ��������ʧ�ܣ�");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "ͼ��������ʧ�ܣ�");
		}finally{
				try {
					dbUtil.close(con);//���Ҫ�ر����ݿ�����
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	//�����¼�����
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	//���ñ�����
	private void resetValue(){
		this.bookTypeName_Txt.setText("");
		this.bookTypeDesc_Txt.setText("");
	}

}
