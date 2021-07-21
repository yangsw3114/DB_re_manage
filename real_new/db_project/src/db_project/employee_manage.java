package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class employee_manage extends JFrame implements MouseListener{
	DB_Conn_Query Employee = new DB_Conn_Query();
	private JPanel contentPane;
	private JTable table;
	private JTextField �̸�textField;
	private JTextField ����textField;
	private JTextField ��ȭ��ȣtextField;
	private JTextField �ֹε�Ϲ�ȣtextField;
	public static DefaultTableModel model;
	private static employee_manage frame;
	private int selectRow;
	private JTextField ��åtextField;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new employee_manage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//������ ���� �� ��ȣ��� 
		selectRow = table.getSelectedRow();
		
		if(e.getClickCount() == 2) {
			//���̺��� �𵨰�ü ������
			TableModel data = table.getModel();
			
			//������ ���̺��� row�� ��� ���� �̿��Ͽ� MemberDTO��ü �����ϱ�
			String name = String.valueOf(data.getValueAt(selectRow,0));
			String age = String.valueOf(data.getValueAt(selectRow,1));
			String tel = String.valueOf(data.getValueAt(selectRow,2));
			String RRnum = String.valueOf(data.getValueAt(selectRow,3));
			String position = String.valueOf(data.getValueAt(selectRow,4));
			
			employee_fix EF = new employee_fix(frame, name, age, tel, RRnum, position, selectRow);
			EF.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Create the frame.
	 */
	public employee_manage(int buildnum) {
		System.out.println(buildnum);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String header[] = {"�̸�", "����", "��ȭ��ȣ", "�ֹε�Ϲ�ȣ", "��å"};
//		String contents[][] = {{"��¿�", "24", "�ڳ�3", "01071631086"},
//				{"ȫ�浿", "32", "�ڳ�1", "1234"}};


		model = new DefaultTableModel(header, 0)
		{ public boolean isCellEditable(int i, int c){ 
			return false; 
			} 
		};
		
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setLocation(12, 36);
		sc.setSize(803, 292);
		contentPane.add(sc);
		table.addMouseListener(this);
		
		Employee.employee_List(buildnum);
		
		JButton ������ư = new JButton("\uC0AD\uC81C");
		������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = table.getModel();
				String name = String.valueOf(data.getValueAt(selectRow, 0));
				
				if(selectRow == -1) {
					JOptionPane.showMessageDialog(null, "���õ� ���� �����ϴ�.", "��������", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Employee.employee_delete(name);
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		������ư.setBounds(718, 338, 97, 23);
		contentPane.add(������ư);
		
		JButton �߰���ư = new JButton("\uC800\uC7A5");
		�߰���ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input[] = new String[5];
				
				if(�̸�textField.getText().trim().isEmpty() || ����textField.getText().trim().isEmpty() || 
						��ȭ��ȣtextField .getText().trim().isEmpty() || �ֹε�Ϲ�ȣtextField.getText().trim().isEmpty() || ��åtextField.getText().trim().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "��ĭ�� ä���ּ���", "�������", JOptionPane.WARNING_MESSAGE);
				}
				else {
					input[0] = �̸�textField.getText();
					input[1] = ����textField.getText();
					input[2] = ��ȭ��ȣtextField.getText();
					input[3] = �ֹε�Ϲ�ȣtextField.getText();
					input[4] = ��åtextField.getText();
					model.addRow(input);
					
					Employee.employee_insert(�̸�textField.getText(), Integer.parseInt(����textField.getText()), 
							��ȭ��ȣtextField.getText(), �ֹε�Ϲ�ȣtextField.getText(), ��åtextField.getText(), buildnum);
					
					�̸�textField.setText("");
					����textField.setText("");
					��ȭ��ȣtextField.setText("");
					�ֹε�Ϲ�ȣtextField.setText("");
					��åtextField.setText("");
				}

			}
		});
		�߰���ư.setBounds(609, 338, 97, 23);
		contentPane.add(�߰���ư);
		
		JButton �ڷΰ����ư = new JButton("BACK");
		�ڷΰ����ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu AM = new admin_menu(buildnum);
				AM.setVisible(true);
				dispose();
			}
		});
		�ڷΰ����ư.setBounds(736, 9, 79, 16);
		contentPane.add(�ڷΰ����ư);
		
		JLabel ������϶� = new JLabel("\uC9C1\uC6D0\uBAA9\uB85D_\uB9AC\uC2A4\uD2B8");
		������϶�.setBounds(12, 10, 182, 15);
		contentPane.add(������϶�);
		
		�̸�textField = new JTextField();
		�̸�textField.setBounds(12, 339, 116, 21);
		contentPane.add(�̸�textField);
		�̸�textField.setColumns(10);
		
		����textField = new JTextField();
		����textField.setColumns(10);
		����textField.setBounds(128, 339, 116, 21);
		contentPane.add(����textField);
		
		��ȭ��ȣtextField = new JTextField();
		��ȭ��ȣtextField.setColumns(10);
		��ȭ��ȣtextField.setBounds(244, 339, 116, 21);
		contentPane.add(��ȭ��ȣtextField);
		
		�ֹε�Ϲ�ȣtextField = new JTextField();
		�ֹε�Ϲ�ȣtextField.setColumns(10);
		�ֹε�Ϲ�ȣtextField.setBounds(358, 339, 116, 21);
		contentPane.add(�ֹε�Ϲ�ȣtextField);
		
		��åtextField = new JTextField();
		��åtextField.setBounds(473, 339, 116, 21);
		contentPane.add(��åtextField);
		��åtextField.setColumns(10);
	}
	

	public void fix(String name, String age, String tel, String RRnum, String position, String oldname, int row) {
		employee_manage.model.setValueAt(name,row,0);
		employee_manage.model.setValueAt(age,row,1);
		employee_manage.model.setValueAt(tel,row,2);
		employee_manage.model.setValueAt(RRnum,row,3);
		employee_manage.model.setValueAt(position,row,4);
		
		int Age = Integer.parseInt(age);
		
//		TableModel data = table.getModel();
//		String old_name = String.valueOf(data.getValueAt(selectRow, 0));
		
		Employee.employee_update(name, Age, tel, RRnum, position, oldname);
//		
//		frame.model.setValueAt(n,r,0);
//		frame.model.setValueAt(a,r,1);
//		frame.model.setValueAt(c,r,2);
//		frame.model.setValueAt(t,r,3);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public employee_manage(String name, String age, String tel, String RRnum, String position, String oldname, int row) {
		fix(name, age, tel, RRnum, position, oldname, row);
	}
}
