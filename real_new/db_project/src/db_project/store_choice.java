package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class store_choice extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public store_choice(String member_id,int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 213);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton �������а���ư = new JButton("\uC815\uBCF4\uACF5\uD559\uAD00");
		�������а���ư.setBounds(99, 10, 121, 39);
		�������а���ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (i==2) {
					weekly_menutable_customer frame = new weekly_menutable_customer(1,member_id);
					frame.setVisible(true);
					dispose();}
				else {
					store_order frame = new store_order(1,member_id);
					frame.setVisible(true);
					dispose();
				}
			}
		});
		
		JButton ��������ư = new JButton("\uC218\uB355\uC804");
		��������ư.setBounds(99, 59, 121, 39);
		��������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (i==2) {
				weekly_menutable_customer frame = new weekly_menutable_customer(2,member_id);
				frame.setVisible(true);
				dispose();}
				else {
					store_order frame = new store_order(2,member_id);
					frame.setVisible(true);
					dispose();
				}
			}
		});
		
		JButton ������ư = new JButton("\uAE30\uC219\uC0AC");
		������ư.setBounds(99, 108, 121, 39);
		������ư.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (i==2) {
				weekly_menutable_customer frame = new weekly_menutable_customer(3,member_id);
				frame.setVisible(true);
				dispose();}
				else {
					store_order frame = new store_order(3,member_id);
					frame.setVisible(true);
					dispose();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(������ư);
		contentPane.add(��������ư);
		contentPane.add(�������а���ư);
	}
}
