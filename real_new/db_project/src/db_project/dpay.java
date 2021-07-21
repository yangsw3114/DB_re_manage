package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class dpay extends JFrame {

	private JPanel contentPane;
	private JTable menutable;
	public static DefaultTableModel model;
	DB_Conn_Query dpay_list = new DB_Conn_Query();
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private static String mem_id;
	
	public dpay(String member_id) {
		mem_id = member_id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		String header[]= {"내역코드", "사용/충전내역", "금액", "날짜", "사용장소"};
		String contents[][] = {{}};
		
		model = new DefaultTableModel(contents, header);
		
		menutable = new JTable(model);
		JScrollPane sc = new JScrollPane(menutable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setLocation(12, 129);
		sc.setSize(410, 142);
		contentPane.add(sc);
		
		dpay_list.dpay_list(mem_id);
		
		
		btnNewButton = new JButton("back");
		btnNewButton.setBounds(17, 30, 59, 23);
		btnNewButton.addActionListener(new ActionListener() { // 뒤로가기 버튼
			public void actionPerformed(ActionEvent e) {
				member_menu adm = new member_menu(mem_id); 
				adm.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton_1 = new JButton("\uC0AC\uC6A9\uB0B4\uC5ED\uBCF4\uAE30");
		btnNewButton_1.setBounds(244, 15, 146, 53);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar cal = new calendar(mem_id); 
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(sc);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
	}

}
