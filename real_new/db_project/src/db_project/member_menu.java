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
		
		JButton �޴��ֹ� = new JButton("�޴� �ֹ�"); //�޴� �ֹ� ��ư 
		�޴��ֹ�.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store_choice store_list = new store_choice(member_id,1); 
				store_list.setVisible(true);
				dispose();
			}
		});
		
		JButton �޴���ȸ = new JButton("�޴� ��ȸ"); // �޴� ��ȸ��ư 
		�޴���ȸ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				store_choice store_list = new store_choice(member_id,2); 
				store_list.setVisible(true);
				dispose();
				
				
			}
		});
		
		JButton ����Ȯ�� = new JButton("���� Ȯ��");  //����Ȯ�� dpay ���� Ȯ�ι�ư 
		����Ȯ��.addActionListener(new ActionListener() {
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
						.addComponent(�޴���ȸ, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(����Ȯ��, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(�޴��ֹ�, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE))
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addComponent(�޴��ֹ�)
					.addGap(14)
					.addComponent(�޴���ȸ)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(����Ȯ��)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
