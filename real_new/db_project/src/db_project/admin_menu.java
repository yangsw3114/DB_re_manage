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
		
		JButton �޴����� = new JButton("\uD1B5\uD569\uBA54\uB274\uAD00\uB9AC");
		�޴�����.setBounds(12, 10, 140, 40);
		contentPane.add(�޴�����);
		�޴�����.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_control MC = new menu_control(buildnum); 
				MC.setVisible(true);
				dispose();
			}
		});
		
		JButton �α׾ƿ� = new JButton("\uB85C\uADF8\uC544\uC6C3");
		�α׾ƿ�.setBounds(325, 10, 97, 23);
		contentPane.add(�α׾ƿ�);
		�α׾ƿ�.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame main = new LoginFrame(); 
				main.setVisible(true);
				dispose();
			}
		});
		
		JButton ����������ư = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		����������ư.setBounds(12, 110, 140, 40);
		contentPane.add(����������ư);
		����������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee_manage EM = new employee_manage(buildnum); 
				EM.setVisible(true);
				dispose();
			}
		});
		
		JButton �ְ��Ĵ�ǥ��ư = new JButton("\uC8FC\uAC04\uC2DD\uB2E8\uD45C");
		�ְ��Ĵ�ǥ��ư.setBounds(12, 160, 140, 40);
		contentPane.add(�ְ��Ĵ�ǥ��ư);
		
		JButton ���շ�ŷ��ư = new JButton("\uD1B5\uD569\uBA54\uB274\uB7AD\uD0B9");
		���շ�ŷ��ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_ranking MR = new menu_ranking(buildnum); 
				MR.setVisible(true);
				dispose();
			}
		});
		���շ�ŷ��ư.setBounds(12, 60, 140, 40);
		contentPane.add(���շ�ŷ��ư);
		
		JLabel �Ĵ��� = new JLabel("None");
		�Ĵ���.setHorizontalAlignment(SwingConstants.CENTER);
		�Ĵ���.setBounds(307, 228, 115, 23);
		contentPane.add(�Ĵ���);
		�Ĵ���.setText(admin.BuildName(buildnum));
		
		
		�ְ��Ĵ�ǥ��ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weekly_menutable menutable = new weekly_menutable(buildnum); 
				menutable.setVisible(true);
				dispose();
			}
		});
	}
}
