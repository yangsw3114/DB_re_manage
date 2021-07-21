package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class weekly_menutable extends JFrame implements MouseListener{
	DB_Conn_Query Weekly = new DB_Conn_Query();
	private JPanel contentPane;
	private JTable menutable;
	private JTextField �ڳ�text;
	public static DefaultTableModel model;
	private JComboBox<String> �޴�comboBox;
	private String SelectDay;
	private int selectRow;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					weekly_menutable frame = new weekly_menutable();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public void mouseClicked(MouseEvent e) {
		//������ ���� �� ��ȣ��� 
		selectRow = menutable.getSelectedRow();

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
	 * @throws ParseException 
	 */
	public weekly_menutable(int buildnum) {
		System.out.println(buildnum);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToggleButton ����ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		����ư.setBounds(12, 26, 112, 23);
		contentPane.add(����ư);
		
		JToggleButton ȭ��ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		ȭ��ư.setBounds(124, 26, 112, 23);
		contentPane.add(ȭ��ư);
		
		JToggleButton ����ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		����ư.setBounds(236, 26, 112, 23);
		contentPane.add(����ư);
		
		JToggleButton ���ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		���ư.setBounds(348, 26, 112, 23);
		contentPane.add(���ư);
		
		JToggleButton �ݹ�ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		�ݹ�ư.setBounds(460, 26, 112, 23);
		contentPane.add(�ݹ�ư);
		
		JToggleButton ���ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		���ư.setBounds(572, 26, 112, 23);
		contentPane.add(���ư);
		
		JToggleButton �Ϲ�ư = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		�Ϲ�ư.setBounds(684, 26, 112, 23);
		contentPane.add(�Ϲ�ư);
		//���� ��¥ ���ϱ�///////////////////////////////////////////////////////////////////////
		Date date = new Date();
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
		String current = Format.format(date);
		System.out.println(current);
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd");
		Date DayofWeek = new Date();
		try {
			DayofWeek = dateFormat.parse(current);
		}
		catch(ParseException e) {
			System.out.println(e);
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(DayofWeek);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		cal.add(Calendar.DATE, 2 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(������)��¥:"+dateFormat.format(cal.getTime()));
		����ư.setText(dateFormat.format(cal.getTime())+"������");
		
		cal.add(Calendar.DATE, 3 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(ȭ����)��¥:"+dateFormat.format(cal.getTime()));
		ȭ��ư.setText(dateFormat.format(cal.getTime())+"ȭ����");
		
		cal.add(Calendar.DATE, 4 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(������)��¥:"+dateFormat.format(cal.getTime()));
		����ư.setText(dateFormat.format(cal.getTime())+"������");
		
		cal.add(Calendar.DATE, 5 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(�����)��¥:"+dateFormat.format(cal.getTime()));
		���ư.setText(dateFormat.format(cal.getTime())+"�����");
		
		cal.add(Calendar.DATE, 6 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(�ݿ���)��¥:"+dateFormat.format(cal.getTime()));
		�ݹ�ư.setText(dateFormat.format(cal.getTime())+"�ݿ���");
		
		cal.add(Calendar.DATE, 7 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(�����)��¥:"+dateFormat.format(cal.getTime()));
		���ư.setText(dateFormat.format(cal.getTime())+"�����");
		
		cal.add(Calendar.DATE, 8 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("ù��° ����(�Ͽ���)��¥:"+dateFormat.format(cal.getTime()));
		�Ϲ�ư.setText(dateFormat.format(cal.getTime())+"�Ͽ���");
		//���� ��¥ ���ϱ�///////////////////////////////////////////////////////////////////////
		
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          SelectDay = abstractButton.getText().substring(0,5);
	          System.out.println(SelectDay);
	          
	          model.setNumRows(0); //Jtable Ŭ����
	          
	          Weekly.WeeklyTable(SelectDay, buildnum);
	          
	          //model.fireTableDataChanged();
	        }
	      };
	      
	    ����ư.addActionListener(actionListener);
	    ȭ��ư.addActionListener(actionListener);
	    ����ư.addActionListener(actionListener);
	    ���ư.addActionListener(actionListener);
	    �ݹ�ư.addActionListener(actionListener);
	    ���ư.addActionListener(actionListener);
	    �Ϲ�ư.addActionListener(actionListener);


		
		ButtonGroup BG = new ButtonGroup();
		BG.add(����ư);
		BG.add(ȭ��ư);
		BG.add(����ư);
		BG.add(���ư);
		BG.add(�ݹ�ư);
		BG.add(���ư);
		BG.add(�Ϲ�ư);
		
		
		JButton �߰���ư = new JButton("\uCD94\uAC00");
		�߰���ư.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String corner = �ڳ�text.getText();
				String menu = (String) �޴�comboBox.getSelectedItem();
				String day = SelectDay;
				
				String input[] = new String[2];

				input[0] = corner;
				input[1] = menu;
				model.addRow(input);
				
				Weekly.WeeklyTable_insert(corner, menu, day, buildnum);
				
			}
		});
		�߰���ư.setBounds(382, 339, 112, 23);
		contentPane.add(�߰���ư);
		
		JButton ������ư = new JButton("\uC0AD\uC81C");
		������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = menutable.getModel();
				String menu = String.valueOf(data.getValueAt(menutable.getSelectedRow(), 1));
				
				System.out.println(menutable.getSelectedRow());
				if(menutable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "���õ� ���� �����ϴ�.", "��������", JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					Weekly.WeeklyTable_delete(menu);
					model.removeRow(menutable.getSelectedRow());
					JOptionPane.showMessageDialog(null, "�����Ϸ�", "����", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		������ư.setBounds(506, 339, 112, 23);
		contentPane.add(������ư);
		
		String header[]= {"�ڳʸ�", "�޴�"};
	
//		String contents[][] = {{"�ڳ�1", "���"},
//				{"�ڳ�2","ġ��"},
//				{"�ڳ�3","�߰���ư�� ����ص� �޴��� �����ϰ� �޺��ڽ� �����ϱ�"}};
//		model = new DefaultTableModel(contents, header);
		
		model = new DefaultTableModel(header, 0)
		{ public boolean isCellEditable(int i, int c){ 
			return false; 
			} 
		};
		
		menutable = new JTable(model);
		JScrollPane sc = new JScrollPane(menutable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setLocation(12, 59);
		sc.setSize(784, 246);
		contentPane.add(sc);
		
		menutable.addMouseListener(this);
		
		�ڳ�text = new JTextField();
		�ڳ�text.setBounds(12, 340, 173, 21);
		contentPane.add(�ڳ�text);
		�ڳ�text.setColumns(10);
		
		�޴�comboBox = new JComboBox<String>();
		�޴�comboBox.setBounds(197, 339, 173, 23);
		contentPane.add(�޴�comboBox);
		
		ArrayList<String> comboList = (ArrayList<String>) Weekly.menu_combobox();
		int Length = comboList.size();
		for(int i =0; i < Length; i++) {
			�޴�comboBox.addItem(comboList.get(i));
		}
		
		
		
		JLabel �ڳʸ�� = new JLabel("\uCF54\uB108\uBA85");
		�ڳʸ��.setBounds(12, 315, 57, 15);
		contentPane.add(�ڳʸ��);
		
		JLabel �޴��� = new JLabel("\uBA54\uB274");
		�޴���.setBounds(197, 314, 57, 15);
		contentPane.add(�޴���);
		
		JButton �ڷΰ����ư = new JButton("BACK");
		�ڷΰ����ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu adm = new admin_menu(buildnum); 
				adm.setVisible(true);
				dispose();
			}
		});
		�ڷΰ����ư.setBounds(699, 339, 97, 23);
		contentPane.add(�ڷΰ����ư);

	}
}
