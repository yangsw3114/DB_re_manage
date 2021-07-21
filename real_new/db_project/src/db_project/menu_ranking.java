package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menu_ranking extends JFrame {
	DB_Conn_Query menu_ranking = new DB_Conn_Query();
	private JPanel contentPane;
	private JTextField first;
	private JTextField second;
	private JTextField third;
	private JTextField four;
	private JTextField five;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					menu_ranking frame = new menu_ranking();
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
	public menu_ranking(int buildnum) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel 판매순위라벨 = new JLabel("\uD1B5\uD569\uD310\uB9E4\uC21C\uC704");
		판매순위라벨.setBounds(12, 10, 84, 15);
		contentPane.add(판매순위라벨);
		
		JButton 뒤로가기버튼 = new JButton("BACK");
		뒤로가기버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_menu adm = new admin_menu(buildnum); 
				adm.setVisible(true);
				dispose();
			}
		});
		뒤로가기버튼.setBounds(269, 6, 97, 23);
		contentPane.add(뒤로가기버튼);
		
		JLabel lblNewLabel = new JLabel("1\uC21C\uC704");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 58, 57, 15);
		contentPane.add(lblNewLabel);
		
		first = new JTextField();
		first.setEditable(false);
		first.setBounds(81, 55, 285, 21);
		contentPane.add(first);
		first.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("2\uC21C\uC704");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 83, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("3\uC21C\uC704");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(12, 108, 57, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("4\uC21C\uC704");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(12, 133, 57, 15);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("5\uC21C\uC704");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(12, 158, 57, 15);
		contentPane.add(lblNewLabel_1_3);
		
		second = new JTextField();
		second.setEditable(false);
		second.setColumns(10);
		second.setBounds(81, 80, 285, 21);
		contentPane.add(second);
		
		third = new JTextField();
		third.setEditable(false);
		third.setColumns(10);
		third.setBounds(81, 105, 285, 21);
		contentPane.add(third);
		
		four = new JTextField();
		four.setEditable(false);
		four.setColumns(10);
		four.setBounds(81, 130, 285, 21);
		contentPane.add(four);
		
		five = new JTextField();
		five.setEditable(false);
		five.setColumns(10);
		five.setBounds(81, 155, 285, 21);
		contentPane.add(five);
		
		ArrayList<String> comboList = (ArrayList<String>) menu_ranking.Ranking();
		
		first.setText(comboList.get(0));
		second.setText(comboList.get(1));
		third.setText(comboList.get(2));
		four.setText(comboList.get(3));
		five.setText(comboList.get(4));
	}
}
