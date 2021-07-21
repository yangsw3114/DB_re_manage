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
	private JTextField 코너text;
	public static DefaultTableModel model;
	private JComboBox<String> 메뉴comboBox;
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
	public weekly_menutable(int buildnum) {
		System.out.println(buildnum);
		
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
	          
	          Weekly.WeeklyTable(SelectDay, buildnum);
	          
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
		
		
		JButton 추가버튼 = new JButton("\uCD94\uAC00");
		추가버튼.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String corner = 코너text.getText();
				String menu = (String) 메뉴comboBox.getSelectedItem();
				String day = SelectDay;
				
				String input[] = new String[2];

				input[0] = corner;
				input[1] = menu;
				model.addRow(input);
				
				Weekly.WeeklyTable_insert(corner, menu, day, buildnum);
				
			}
		});
		추가버튼.setBounds(382, 339, 112, 23);
		contentPane.add(추가버튼);
		
		JButton 삭제버튼 = new JButton("\uC0AD\uC81C");
		삭제버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel data = menutable.getModel();
				String menu = String.valueOf(data.getValueAt(menutable.getSelectedRow(), 1));
				
				System.out.println(menutable.getSelectedRow());
				if(menutable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "선택된 행이 없습니다.", "삭제실패", JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					Weekly.WeeklyTable_delete(menu);
					model.removeRow(menutable.getSelectedRow());
					JOptionPane.showMessageDialog(null, "삭제완료", "삭제", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		삭제버튼.setBounds(506, 339, 112, 23);
		contentPane.add(삭제버튼);
		
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
		
		menutable.addMouseListener(this);
		
		코너text = new JTextField();
		코너text.setBounds(12, 340, 173, 21);
		contentPane.add(코너text);
		코너text.setColumns(10);
		
		메뉴comboBox = new JComboBox<String>();
		메뉴comboBox.setBounds(197, 339, 173, 23);
		contentPane.add(메뉴comboBox);
		
		ArrayList<String> comboList = (ArrayList<String>) Weekly.menu_combobox();
		int Length = comboList.size();
		for(int i =0; i < Length; i++) {
			메뉴comboBox.addItem(comboList.get(i));
		}
		
		
		
		JLabel 코너명라벨 = new JLabel("\uCF54\uB108\uBA85");
		코너명라벨.setBounds(12, 315, 57, 15);
		contentPane.add(코너명라벨);
		
		JLabel 메뉴라벨 = new JLabel("\uBA54\uB274");
		메뉴라벨.setBounds(197, 314, 57, 15);
		contentPane.add(메뉴라벨);
		
		JButton 뒤로가기버튼 = new JButton("BACK");
		뒤로가기버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu adm = new admin_menu(buildnum); 
				adm.setVisible(true);
				dispose();
			}
		});
		뒤로가기버튼.setBounds(699, 339, 97, 23);
		contentPane.add(뒤로가기버튼);

	}
}
