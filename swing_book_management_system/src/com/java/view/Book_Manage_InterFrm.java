package com.java.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.model.Book;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Book_Manage_InterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookName_Txt;
	private JTextField s_author_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_bookType_Jcb;
	private JRadioButton man_Jrb;
	private JRadioButton woman_Jrb;
	private JTextArea bookDesc_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox bookType_Jcb;
	
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField id_Txt;
	private JTextField bookName_Txt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField price_Txt;
	private JTextField author_Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Manage_InterFrm frame = new Book_Manage_InterFrm();
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
	@SuppressWarnings("rawtypes")
	public Book_Manage_InterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setBounds(100, 100, 723, 660);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		
		JLabel label_3 = new JLabel("\u7F16\u53F7:");
		
		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		
		bookName_Txt = new JTextField();
		bookName_Txt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u4F5C\u8005\u6027\u522B:");
		
		man_Jrb = new JRadioButton("\u7537");
		buttonGroup.add(man_Jrb);
		man_Jrb.setSelected(true);
		
		woman_Jrb = new JRadioButton("\u5973");
		buttonGroup.add(woman_Jrb);
		
		JLabel label_6 = new JLabel("\u4EF7\u683C:");
		
		price_Txt = new JTextField();
		price_Txt.setColumns(10);
		
		JLabel label_7 = new JLabel("\u56FE\u4E66\u4F5C\u8005:");
		
		author_Txt = new JTextField();
		author_Txt.setColumns(10);
		
		JLabel label_8 = new JLabel("\u56FE\u4E66\u7C7B\u522B:");
		
		bookType_Jcb = new JComboBox();
		
		JLabel label_9 = new JLabel("\u56FE\u4E66\u63CF\u8FF0:");
		
		bookDesc_Txt = new JTextArea();
		
		//ͼ���޸��¼�
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookUpdateActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(Book_Manage_InterFrm.class.getResource("/images/modify.png")));
		
		//ͼ��ɾ���¼�
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookDeleteActionPerformed(evt);
			}
		});
		button_2.setIcon(new ImageIcon(Book_Manage_InterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_9)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookDesc_Txt, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(29)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(32)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_5)
									.addGap(14)
									.addComponent(man_Jrb)
									.addGap(18)
									.addComponent(woman_Jrb))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_8)
									.addGap(18)
									.addComponent(bookType_Jcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(244, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(105)
					.addComponent(button_2)
					.addGap(155))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(man_Jrb)
						.addComponent(woman_Jrb))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7)
						.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(bookType_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(bookDesc_Txt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		
		s_bookName_Txt = new JTextField();
		s_bookName_Txt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005:");
		
		s_author_Txt = new JTextField();
		s_author_Txt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B:");
		
		s_bookType_Jcb = new JComboBox();
		
		//ͼ���ѯ�¼�
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_author_Txt, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(s_bookType_Jcb, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(button)
						.addComponent(s_bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(s_author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(s_bookType_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		//bookTable���е���¼�
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				bookTableMousePressed(met);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(97);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(92);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(101);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(91);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);
		
		//����JTextArea�߿�Ĵ���
		bookDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		//��ѯ�����򷽷�����
		this.fillBookType("search");
		//�޸������򷽷�����
		this.fillBookType("modify");
		//���ķ���
		this.fillTable(new Book());
	}
	
	/**
	 * ͼ��ɾ���¼�����
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		//��ȡ������Ϣ
		String id=id_Txt.getText();
		//�ж�id�Ƿ�Ϊ�գ���Ϊ�գ�����ɾ��
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ļ�¼!");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ü�¼��?");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=bookDao.deleteBook(con, id);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
					this.resetValue();
					this.fillTable(new Book());
				}else{
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��!");
			}finally{
				try {
					dbUtil.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ͼ���޸��¼�����
	 * @param evt
	 */
	private void bookUpdateActionPerformed(ActionEvent evt) {
		//��ȡ������Ϣ
		String id=this.id_Txt.getText();
		String bookName=this.bookName_Txt.getText();
		String author=this.author_Txt.getText();
		String price=this.price_Txt.getText();
		String bookDesc=this.bookDesc_Txt.getText();
		
		//�ж�id�Ƿ�Ϊ�գ���Ϊ�գ������޸�
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵļ�¼!");
			return;
		}
		//�ж�������Ϣ�Ƿ�Ϊ��
		if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null, "�۸���Ϊ�գ�");
			return;
		}
		
		//��ȡ�Ա���Ϣ
		String sex="";
		if(man_Jrb.isSelected()){
			sex="��";
		}else if(woman_Jrb.isSelected()){
			sex="Ů";
		}
		
		//��ȡͼ�����id
		BookType bookType=(BookType) bookType_Jcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		
		//���з�װ
		Book book=new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
		
		//������Ӳ��������ݿ�����
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int addNum=bookDao.updateBook(con,book);
			if(addNum==1){
				JOptionPane.showMessageDialog(null, "ͼ���޸ĳɹ���");
				//��ձ�
				resetValue();
				//����
				this.fillTable(new Book());
			}else{
				JOptionPane.showMessageDialog(null, "ͼ���޸�ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ͼ���޸�ʧ�ܣ�");
		} finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ���ñ�
	 */
	private void resetValue(){
		this.id_Txt.setText("");
		this.bookName_Txt.setText("");
		this.author_Txt.setText("");
		this.price_Txt.setText("");
		this.man_Jrb.setSelected(true);
		this.bookDesc_Txt.setText("");
		if(this.bookType_Jcb.getItemCount()>0){
			//Ĭ������ѡ�е�һ��
			this.bookType_Jcb.setSelectedIndex(0);
		}
	}

	//����е���¼�����
	private void bookTableMousePressed(MouseEvent met) {
		//��ȡѡ�е��У������к�
		int row=this.bookTable.getSelectedRow();
		//��ȡ��row�У���1�е�ֵ
		this.id_Txt.setText((String)bookTable.getValueAt(row, 0));
		//��ȡ��row�У���2�е�ֵ
		this.bookName_Txt.setText((String)bookTable.getValueAt(row, 1));
		//��ȡ��row�У���3�е�ֵ
		this.author_Txt.setText((String)bookTable.getValueAt(row, 2));
		//��ȡ�Ա�
		String sex=(String)bookTable.getValueAt(row, 3);
		//�˹��ж�
		if("��".equals(sex)){
			this.man_Jrb.setSelected(true);
		}else if("Ů".equals(sex)){
			this.woman_Jrb.setSelected(true);
		}
		//��ȡ��row�У���5�е�ֵ
		this.price_Txt.setText((Float)bookTable.getValueAt(row, 4)+"");
		//��ȡ��row�У���6�е�ֵ
		this.bookDesc_Txt.setText((String)bookTable.getValueAt(row, 5));
		//��ȡ��row�У���7�е�ֵ
		String bookTypeName=(String)this.bookTable.getValueAt(row, 6);
		//��ȡ�����������
		int n=this.bookType_Jcb.getItemCount();
		//��ʼ�������������������
		for(int i=0;i<n;i++){
			//��ÿ��������������ó�����bookTypeName���бȽϣ�����ͬ��������Ϊѡ��
			BookType item=(BookType)this.bookType_Jcb.getItemAt(i);
			if(item.getBookTypeName().equals(bookTypeName)){
				this.bookType_Jcb.setSelectedIndex(i);
			}
		}
	}

	/**
	 * ͼ���ѯ����
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		//�ӽ����ȡ��Ϣ
		String bookName=this.s_bookName_Txt.getText();
		String author=this.s_author_Txt.getText();
		BookType bookType=(BookType) this.s_bookType_Jcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		
		Book book=new Book(bookName,author,bookTypeId);
		this.fillTable(book);
	}

	/**
	 * ��ʼ��������
	 * @param type ����������
	 */
	@SuppressWarnings("unchecked")
	private void fillBookType(String type){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			if("search".equals(type)){
				BookType bookType=new BookType();
				bookType.setBookTypeName("��ѡ��...");
				bookType.setId(-1);
				this.s_bookType_Jcb.addItem(bookType);
			}
			while(rs.next()){
				BookType bookType=new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if("search".equals(type)){
					this.s_bookType_Jcb.addItem(bookType);
				}else if("modify".equals(type)){
					this.bookType_Jcb.addItem(bookType);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ʼ���������
	 * @param book
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillTable(Book book){
		DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();
		//��ձ��
		dtm.setRowCount(0);
		//�������ݿ�
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=bookDao.list(con, book);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dtm.addRow(v);
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
}
