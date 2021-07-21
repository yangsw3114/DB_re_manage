package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class admin_menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					admin_menu frame = new admin_menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public admin_menu(int buildnum) {
		DB_Conn_Query admin = new DB_Conn_Query();
		System.out.println(buildnum);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton 메뉴관리 = new JButton("\uD1B5\uD569\uBA54\uB274\uAD00\uB9AC");
		메뉴관리.setBounds(12, 10, 140, 40);
		contentPane.add(메뉴관리);
		메뉴관리.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_control MC = new menu_control(buildnum); 
				MC.setVisible(true);
				dispose();
			}
		});
		
		JButton 로그아웃 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		로그아웃.setBounds(325, 10, 97, 23);
		contentPane.add(로그아웃);
		로그아웃.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame main = new LoginFrame(); 
				main.setVisible(true);
				dispose();
			}
		});
		
		JButton 직원관리버튼 = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		직원관리버튼.setBounds(12, 110, 140, 40);
		contentPane.add(직원관리버튼);
		직원관리버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee_manage EM = new employee_manage(buildnum); 
				EM.setVisible(true);
				dispose();
			}
		});
		
		JButton 주간식단표버튼 = new JButton("\uC8FC\uAC04\uC2DD\uB2E8\uD45C");
		주간식단표버튼.setBounds(12, 160, 140, 40);
		contentPane.add(주간식단표버튼);
		
		JButton 통합랭킹버튼 = new JButton("\uD1B5\uD569\uBA54\uB274\uB7AD\uD0B9");
		통합랭킹버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_ranking MR = new menu_ranking(buildnum); 
				MR.setVisible(true);
				dispose();
			}
		});
		통합랭킹버튼.setBounds(12, 60, 140, 40);
		contentPane.add(통합랭킹버튼);
		
		JLabel 식당명라벨 = new JLabel("None");
		식당명라벨.setHorizontalAlignment(SwingConstants.CENTER);
		식당명라벨.setBounds(307, 228, 115, 23);
		contentPane.add(식당명라벨);
		식당명라벨.setText(admin.BuildName(buildnum));
		
		
		주간식단표버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weekly_menutable menutable = new weekly_menutable(buildnum); 
				menutable.setVisible(true);
				dispose();
			}
		});
	}
}
