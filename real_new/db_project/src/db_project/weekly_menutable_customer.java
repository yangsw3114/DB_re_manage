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

public class weekly_menutable_customer extends JFrame implements MouseListener{
	DB_Conn_Query Weekly = new DB_Conn_Query();
	private JPanel contentPane;
	private JTable menutable;
	public static DefaultTableModel model;
	private String SelectDay;
	private int selectRow;
	private String member_id;

	/**
	 * Launch the application.
	 */
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
	public weekly_menutable_customer(int store_name2,String id) {
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
		SimpleDateFormat format = new SimpleDateFormat("MMdd");
		
		String current = format.format(date);


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

	          Weekly.WeeklyTable_member(store_name2 ,SelectDay);
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
		
		JButton �ڷΰ����ư = new JButton("BACK");
		�ڷΰ����ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_menu main_menu = new member_menu(id); 
				main_menu.setVisible(true);
				dispose();
			}
		});
		�ڷΰ����ư.setBounds(699, 339, 97, 23);
		contentPane.add(�ڷΰ����ư);
//		
//        //���̺� ������ �߰��ϱ�
//        //���������͸� �ǵ��� �ʰ� table�� �Ű������� model�� �ִ� �����͸� �����մϴ�
//        DefaultTableModel m =
//                (DefaultTableModel)table.getModel();
//        //�𵨿� ������ �߰� , 1��° �⿡ ���ο� �����͸� �߰��մϴ�
//        m.insertRow(1, new Object[]{"d1","d2","d3"});
//        //�߰��� ��ġ�� ������ ������ �˸��ϴ�.
//        table.updateUI();
    
        

	}
}
