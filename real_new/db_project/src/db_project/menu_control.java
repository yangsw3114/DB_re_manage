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
	private JTextField �޴�text;
	private JTextField ����text;
	private JTextField ����text;
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
		//������ ���� �� ��ȣ��� 
		selectRow = menutable.getSelectedRow();
		//System.out.println(row);
		//���̺��� �𵨰�ü ������
		TableModel data = menutable.getModel();
		
		//������ ���̺��� row�� ��� ���� �̿��Ͽ� MemberDTO��ü �����ϱ�
		
		String name = String.valueOf(data.getValueAt(selectRow,0));
		String price = String.valueOf(data.getValueAt(selectRow,1));
		String kinds = String.valueOf(data.getValueAt(selectRow,2));
		
		�޴�text.setText(name);
		����text.setText(price);
		����text.setText(kinds);
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
		
		String header[]= {"�޴���", "����", "�޴��з���"};

		
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
		
		JLabel ���� = new JLabel("\uAC00\uACA9");
		����.setHorizontalAlignment(SwingConstants.CENTER);
		����.setBounds(188, 44, 57, 15);
		contentPane.add(����);
		
		JLabel ���� = new JLabel("\uC885\uB958");
		����.setHorizontalAlignment(SwingConstants.CENTER);
		����.setBounds(188, 69, 57, 15);
		contentPane.add(����);
		
		JLabel �޴��� = new JLabel("\uBA54\uB274\uBA85");
		�޴���.setHorizontalAlignment(SwingConstants.CENTER);
		�޴���.setBounds(188, 19, 57, 15);
		contentPane.add(�޴���);
		
		�޴�text = new JTextField();
		�޴�text.setBounds(240, 16, 182, 21);
		contentPane.add(�޴�text);
		�޴�text.setColumns(10);
		
		����text = new JTextField();
		����text.setBounds(240, 41, 182, 21);
		contentPane.add(����text);
		����text.setColumns(10);
		
		����text = new JTextField();
		����text.setBounds(240, 66, 182, 21);
		contentPane.add(����text);
		����text.setColumns(10);
		
		JButton ������ư = new JButton("\uC0AD\uC81C");
		������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = menutable.getModel();
				String name = String.valueOf(data.getValueAt(selectRow, 0));
				
				System.out.println(selectRow);
				if(selectRow == -1) {
					JOptionPane.showMessageDialog(null, "���õ� ���� �����ϴ�.", "��������", JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					Menu.menu_delete(name);
					model.removeRow(selectRow);
					JOptionPane.showMessageDialog(null, "�����Ϸ�", "����", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		������ư.setBounds(352, 97, 70, 23);
		contentPane.add(������ư);
		
		JButton ������ư = new JButton("\uC218\uC815");
		������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableModel data = menutable.getModel();
				String old_name = String.valueOf(data.getValueAt(selectRow, 0));
				
				String new_name = �޴�text.getText();
				int price = Integer.parseInt(����text.getText());
				String kind = ����text.getText();
				
				model.setValueAt(new_name, selectRow, 0);
				model.setValueAt(price, selectRow, 1);
				model.setValueAt(kind, selectRow, 2);
				
				Menu.menu_update(new_name, price, kind,old_name);
				JOptionPane.showMessageDialog(null, "�����Ϸ�", "����", JOptionPane.WARNING_MESSAGE);
			}
		});
		������ư.setBounds(270, 96, 70, 23);
		contentPane.add(������ư);
		
		JButton ��Ϲ�ư = new JButton("\uB4F1\uB85D");
		��Ϲ�ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String writeName = �޴�text.getText();
				Boolean Check = true;

				int rowCount = menutable.getRowCount();
				TableModel data = menutable.getModel();
				
				for(int i=0; i < rowCount; i++) {
					//String name = String.valueOf(data.getValueAt(i, 0));
					String name = (String)data.getValueAt(i,0);

					if(writeName.equals(name)) 
					{
						JOptionPane.showMessageDialog(null, "������ �޴��� �ֽ��ϴ�.", "������ �޴�", JOptionPane.WARNING_MESSAGE);
						Check = false;
					}
				}
				
				if(Check == true) {
					
					String input[] = new String[3];

					input[0] = �޴�text.getText();
					input[1] = ����text.getText();
					input[2] = ����text.getText();
					model.addRow(input);
					JOptionPane.showMessageDialog(null, "��ϿϷ�", "���", JOptionPane.WARNING_MESSAGE);
					
					Menu.menu_insert(�޴�text.getText(), Integer.parseInt(����text.getText()), ����text.getText()); 
					
				}
			}
		});
		
		��Ϲ�ư.setBounds(188, 96, 70, 23);
		contentPane.add(��Ϲ�ư);
		
		JButton �ڷΰ����ư = new JButton("BACK");
		�ڷΰ����ư.setBounds(12, 15, 93, 23);
		contentPane.add(�ڷΰ����ư);
		
		�ڷΰ����ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu adm = new admin_menu(buildnum); 
				adm.setVisible(true);
				dispose();
			}
		});
				
				
	}
}
