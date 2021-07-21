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
	private JTextField 이름textField;
	private JTextField 나이textField;
	private JTextField 전화번호textField;
	private JTextField 주민등록번호textField;
	public static DefaultTableModel model;
	private static employee_manage frame;
	private int selectRow;
	private JTextField 직책textField;
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
		
		//선택한 셀의 행 번호계산 
		selectRow = table.getSelectedRow();
		
		if(e.getClickCount() == 2) {
			//테이블의 모델객체 얻어오기
			TableModel data = table.getModel();
			
			//선택한 테이블의 row의 모든 값을 이용하여 MemberDTO객체 생성하기
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
		String header[] = {"이름", "나이", "전화번호", "주민등록번호", "직책"};
//		String contents[][] = {{"양승우", "24", "코너3", "01071631086"},
//				{"홍길동", "32", "코너1", "1234"}};


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
		
		JButton 삭제버튼 = new JButton("\uC0AD\uC81C");
		삭제버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = table.getModel();
				String name = String.valueOf(data.getValueAt(selectRow, 0));
				
				if(selectRow == -1) {
					JOptionPane.showMessageDialog(null, "선택된 행이 없습니다.", "삭제실패", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Employee.employee_delete(name);
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		삭제버튼.setBounds(718, 338, 97, 23);
		contentPane.add(삭제버튼);
		
		JButton 추가버튼 = new JButton("\uC800\uC7A5");
		추가버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input[] = new String[5];
				
				if(이름textField.getText().trim().isEmpty() || 나이textField.getText().trim().isEmpty() || 
						전화번호textField .getText().trim().isEmpty() || 주민등록번호textField.getText().trim().isEmpty() || 직책textField.getText().trim().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "빈칸을 채워주세요", "저장실패", JOptionPane.WARNING_MESSAGE);
				}
				else {
					input[0] = 이름textField.getText();
					input[1] = 나이textField.getText();
					input[2] = 전화번호textField.getText();
					input[3] = 주민등록번호textField.getText();
					input[4] = 직책textField.getText();
					model.addRow(input);
					
					Employee.employee_insert(이름textField.getText(), Integer.parseInt(나이textField.getText()), 
							전화번호textField.getText(), 주민등록번호textField.getText(), 직책textField.getText(), buildnum);
					
					이름textField.setText("");
					나이textField.setText("");
					전화번호textField.setText("");
					주민등록번호textField.setText("");
					직책textField.setText("");
				}

			}
		});
		추가버튼.setBounds(609, 338, 97, 23);
		contentPane.add(추가버튼);
		
		JButton 뒤로가기버튼 = new JButton("BACK");
		뒤로가기버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu AM = new admin_menu(buildnum);
				AM.setVisible(true);
				dispose();
			}
		});
		뒤로가기버튼.setBounds(736, 9, 79, 16);
		contentPane.add(뒤로가기버튼);
		
		JLabel 직원목록라벨 = new JLabel("\uC9C1\uC6D0\uBAA9\uB85D_\uB9AC\uC2A4\uD2B8");
		직원목록라벨.setBounds(12, 10, 182, 15);
		contentPane.add(직원목록라벨);
		
		이름textField = new JTextField();
		이름textField.setBounds(12, 339, 116, 21);
		contentPane.add(이름textField);
		이름textField.setColumns(10);
		
		나이textField = new JTextField();
		나이textField.setColumns(10);
		나이textField.setBounds(128, 339, 116, 21);
		contentPane.add(나이textField);
		
		전화번호textField = new JTextField();
		전화번호textField.setColumns(10);
		전화번호textField.setBounds(244, 339, 116, 21);
		contentPane.add(전화번호textField);
		
		주민등록번호textField = new JTextField();
		주민등록번호textField.setColumns(10);
		주민등록번호textField.setBounds(358, 339, 116, 21);
		contentPane.add(주민등록번호textField);
		
		직책textField = new JTextField();
		직책textField.setBounds(473, 339, 116, 21);
		contentPane.add(직책textField);
		직책textField.setColumns(10);
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
