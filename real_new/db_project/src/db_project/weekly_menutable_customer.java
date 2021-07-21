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
		//선택한 셀의 행 번호계산 
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
		
		JToggleButton 월버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		월버튼.setBounds(12, 26, 112, 23);
		contentPane.add(월버튼);
		
		JToggleButton 화버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		화버튼.setBounds(124, 26, 112, 23);
		contentPane.add(화버튼);
		
		JToggleButton 수버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		수버튼.setBounds(236, 26, 112, 23);
		contentPane.add(수버튼);
		
		JToggleButton 목버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		목버튼.setBounds(348, 26, 112, 23);
		contentPane.add(목버튼);
		
		JToggleButton 금버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		금버튼.setBounds(460, 26, 112, 23);
		contentPane.add(금버튼);
		
		JToggleButton 토버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		토버튼.setBounds(572, 26, 112, 23);
		contentPane.add(토버튼);
		
		JToggleButton 일버튼 = new JToggleButton("11/23 \uC6D4\uC694\uC77C");
		일버튼.setBounds(684, 26, 112, 23);
		contentPane.add(일버튼);
		//현재 날짜 구하기///////////////////////////////////////////////////////////////////////
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
		System.out.println("첫번째 요일(월요일)날짜:"+dateFormat.format(cal.getTime()));
		월버튼.setText(dateFormat.format(cal.getTime())+"월요일");
		
		cal.add(Calendar.DATE, 3 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(화요일)날짜:"+dateFormat.format(cal.getTime()));
		화버튼.setText(dateFormat.format(cal.getTime())+"화요일");
		
		cal.add(Calendar.DATE, 4 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(수요일)날짜:"+dateFormat.format(cal.getTime()));
		수버튼.setText(dateFormat.format(cal.getTime())+"수요일");
		
		cal.add(Calendar.DATE, 5 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(목요일)날짜:"+dateFormat.format(cal.getTime()));
		목버튼.setText(dateFormat.format(cal.getTime())+"목요일");
		
		cal.add(Calendar.DATE, 6 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(금요일)날짜:"+dateFormat.format(cal.getTime()));
		금버튼.setText(dateFormat.format(cal.getTime())+"금요일");
		
		cal.add(Calendar.DATE, 7 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(토요일)날짜:"+dateFormat.format(cal.getTime()));
		토버튼.setText(dateFormat.format(cal.getTime())+"토요일");
		
		cal.add(Calendar.DATE, 8 - cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(일요일)날짜:"+dateFormat.format(cal.getTime()));
		일버튼.setText(dateFormat.format(cal.getTime())+"일요일");
		//현재 날짜 구하기///////////////////////////////////////////////////////////////////////
		
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          SelectDay = abstractButton.getText().substring(0,5);
	          System.out.println(SelectDay);
	          
	          model.setNumRows(0); //Jtable 클리어

	          Weekly.WeeklyTable_member(store_name2 ,SelectDay);
	          //model.fireTableDataChanged();
	        }
	      };
	      
	    월버튼.addActionListener(actionListener);
	    화버튼.addActionListener(actionListener);
	    수버튼.addActionListener(actionListener);
	    목버튼.addActionListener(actionListener);
	    금버튼.addActionListener(actionListener);
	    토버튼.addActionListener(actionListener);
	    일버튼.addActionListener(actionListener);


		
		ButtonGroup BG = new ButtonGroup();
		BG.add(월버튼);
		BG.add(화버튼);
		BG.add(수버튼);
		BG.add(목버튼);
		BG.add(금버튼);
		BG.add(토버튼);
		BG.add(일버튼);
		
		String header[]= {"코너명", "메뉴"};
//		String contents[][] = {{"코너1", "라면"},
//				{"코너2","치즈돈까스"},
//				{"코너3","추가버튼시 등록해둔 메뉴중 선택하게 콤보박스 생성하기"}};
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
		
		JButton 뒤로가기버튼 = new JButton("BACK");
		뒤로가기버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_menu main_menu = new member_menu(id); 
				main_menu.setVisible(true);
				dispose();
			}
		});
		뒤로가기버튼.setBounds(699, 339, 97, 23);
		contentPane.add(뒤로가기버튼);
//		
//        //테이블에 데이터 추가하기
//        //원본데이터를 건들지 않고 table의 매개변수인 model에 있는 데이터를 변경합니다
//        DefaultTableModel m =
//                (DefaultTableModel)table.getModel();
//        //모델에 데이터 추가 , 1번째 출에 새로운 데이터를 추가합니다
//        m.insertRow(1, new Object[]{"d1","d2","d3"});
//        //추가를 마치고 데이터 갱신을 알립니다.
//        table.updateUI();
    
        

	}
}
