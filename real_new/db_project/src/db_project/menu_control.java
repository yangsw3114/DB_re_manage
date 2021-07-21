package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class menu_control extends JFrame implements MouseListener {
	DB_Conn_Query Menu = new DB_Conn_Query();
	private JPanel contentPane;
	private JTable menutable;
	private JTextField 메뉴text;
	private JTextField 가격text;
	private JTextField 종류text;
	public static DefaultTableModel model;
	private int selectRow;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					menu_control frame = new menu_control();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public void mouseClicked(MouseEvent e) {
		//선택한 셀의 행 번호계산 
		selectRow = menutable.getSelectedRow();
		//System.out.println(row);
		//테이블의 모델객체 얻어오기
		TableModel data = menutable.getModel();
		
		//선택한 테이블의 row의 모든 값을 이용하여 MemberDTO객체 생성하기
		
		String name = String.valueOf(data.getValueAt(selectRow,0));
		String price = String.valueOf(data.getValueAt(selectRow,1));
		String kinds = String.valueOf(data.getValueAt(selectRow,2));
		
		메뉴text.setText(name);
		가격text.setText(price);
		종류text.setText(kinds);
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
	public menu_control(int buildnum) {
		System.out.println(buildnum);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String header[]= {"메뉴명", "가격", "메뉴분류명"};

		
		model = new DefaultTableModel(header, 0)
		{ public boolean isCellEditable(int i, int c){ 
			return false; 
			} 
		};
		
		menutable = new JTable(model);
		menutable.addMouseListener(this);
		JScrollPane sc = new JScrollPane(menutable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setLocation(12, 129);
		sc.setSize(410, 142);
		contentPane.add(sc);
		
		Menu.menu_List();
		
		JLabel 가격 = new JLabel("\uAC00\uACA9");
		가격.setHorizontalAlignment(SwingConstants.CENTER);
		가격.setBounds(188, 44, 57, 15);
		contentPane.add(가격);
		
		JLabel 종류 = new JLabel("\uC885\uB958");
		종류.setHorizontalAlignment(SwingConstants.CENTER);
		종류.setBounds(188, 69, 57, 15);
		contentPane.add(종류);
		
		JLabel 메뉴명 = new JLabel("\uBA54\uB274\uBA85");
		메뉴명.setHorizontalAlignment(SwingConstants.CENTER);
		메뉴명.setBounds(188, 19, 57, 15);
		contentPane.add(메뉴명);
		
		메뉴text = new JTextField();
		메뉴text.setBounds(240, 16, 182, 21);
		contentPane.add(메뉴text);
		메뉴text.setColumns(10);
		
		가격text = new JTextField();
		가격text.setBounds(240, 41, 182, 21);
		contentPane.add(가격text);
		가격text.setColumns(10);
		
		종류text = new JTextField();
		종류text.setBounds(240, 66, 182, 21);
		contentPane.add(종류text);
		종류text.setColumns(10);
		
		JButton 삭제버튼 = new JButton("\uC0AD\uC81C");
		삭제버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = menutable.getModel();
				String name = String.valueOf(data.getValueAt(selectRow, 0));
				
				System.out.println(selectRow);
				if(selectRow == -1) {
					JOptionPane.showMessageDialog(null, "선택된 행이 없습니다.", "삭제실패", JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					Menu.menu_delete(name);
					model.removeRow(selectRow);
					JOptionPane.showMessageDialog(null, "삭제완료", "삭제", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		삭제버튼.setBounds(352, 97, 70, 23);
		contentPane.add(삭제버튼);
		
		JButton 수정버튼 = new JButton("\uC218\uC815");
		수정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableModel data = menutable.getModel();
				String old_name = String.valueOf(data.getValueAt(selectRow, 0));
				
				String new_name = 메뉴text.getText();
				int price = Integer.parseInt(가격text.getText());
				String kind = 종류text.getText();
				
				model.setValueAt(new_name, selectRow, 0);
				model.setValueAt(price, selectRow, 1);
				model.setValueAt(kind, selectRow, 2);
				
				Menu.menu_update(new_name, price, kind,old_name);
				JOptionPane.showMessageDialog(null, "수정완료", "수정", JOptionPane.WARNING_MESSAGE);
			}
		});
		수정버튼.setBounds(270, 96, 70, 23);
		contentPane.add(수정버튼);
		
		JButton 등록버튼 = new JButton("\uB4F1\uB85D");
		등록버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String writeName = 메뉴text.getText();
				Boolean Check = true;

				int rowCount = menutable.getRowCount();
				TableModel data = menutable.getModel();
				
				for(int i=0; i < rowCount; i++) {
					//String name = String.valueOf(data.getValueAt(i, 0));
					String name = (String)data.getValueAt(i,0);

					if(writeName.equals(name)) 
					{
						JOptionPane.showMessageDialog(null, "동일한 메뉴가 있습니다.", "동일한 메뉴", JOptionPane.WARNING_MESSAGE);
						Check = false;
					}
				}
				
				if(Check == true) {
					
					String input[] = new String[3];

					input[0] = 메뉴text.getText();
					input[1] = 가격text.getText();
					input[2] = 종류text.getText();
					model.addRow(input);
					JOptionPane.showMessageDialog(null, "등록완료", "등록", JOptionPane.WARNING_MESSAGE);
					
					Menu.menu_insert(메뉴text.getText(), Integer.parseInt(가격text.getText()), 종류text.getText()); 
					
				}
			}
		});
		
		등록버튼.setBounds(188, 96, 70, 23);
		contentPane.add(등록버튼);
		
		JButton 뒤로가기버튼 = new JButton("BACK");
		뒤로가기버튼.setBounds(12, 15, 93, 23);
		contentPane.add(뒤로가기버튼);
		
		뒤로가기버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu adm = new admin_menu(buildnum); 
				adm.setVisible(true);
				dispose();
			}
		});
				
				
	}
}
