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
import javax.swing.LayoutStyle.ComponentPlacement;

public class member_menu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public member_menu(String member_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton 메뉴주문 = new JButton("메뉴 주문"); //메뉴 주문 버튼 
		메뉴주문.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store_choice store_list = new store_choice(member_id,1); 
				store_list.setVisible(true);
				dispose();
			}
		});
		
		JButton 메뉴조회 = new JButton("메뉴 조회"); // 메뉴 조회버튼 
		메뉴조회.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				store_choice store_list = new store_choice(member_id,2); 
				store_list.setVisible(true);
				dispose();
				
				
			}
		});
		
		JButton 내역확인 = new JButton("내역 확인");  //내역확인 dpay 계정 확인버튼 
		내역확인.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dpay adm = new dpay(member_id); 
				adm.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("\uCDA9\uC804\uD558\uAE30");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				charge a = new charge(member_id);
				a.setVisible(true);
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(147)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(메뉴조회, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(내역확인, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(메뉴주문, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE))
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addComponent(메뉴주문)
					.addGap(14)
					.addComponent(메뉴조회)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(내역확인)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
