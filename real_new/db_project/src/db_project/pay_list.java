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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class pay_list extends JFrame {

	private JPanel contentPane;
	private JTable menutable;
	public static DefaultTableModel pay_model;
	public static String total_charge;
	public static String total_spend;
	DB_Conn_Query dpay_list = new DB_Conn_Query();
	private JButton btnNewButton;


	public pay_list(String year, String month, String mem_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		String header[]= {"내역코드", "사용/충전내역", "금액", "날짜", "사용장소"};
		String contents[][] = {{}};
		
		pay_model = new DefaultTableModel(contents, header);
		
		menutable = new JTable(pay_model);
		JScrollPane sc = new JScrollPane(menutable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setLocation(12, 129);
		sc.setSize(410, 142);
		contentPane.add(sc);
		
		
		dpay_list.charge_or_pay(year,month,mem_id); // ( 정한 달의 내역 )
		
		dpay_list.menu_receipt(year,month,mem_id); // (총 사용금액 or 충전금액 불러옴 )
		
		
		
		btnNewButton = new JButton("back");
		btnNewButton.setBounds(17, 15, 77, 23);
		btnNewButton.addActionListener(new ActionListener() { // 뒤로가기 버튼
			public void actionPerformed(ActionEvent e) {
				member_menu adm = new member_menu(mem_id); 
				adm.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("총 충전액:"+total_charge);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(275, 79, 147, 15);
		
		JLabel lblNewLabel_1 = new JLabel("총사용액:"+total_spend);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(279, 104, 143, 15);
		contentPane.setLayout(null);
		contentPane.add(sc);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
	}
}
