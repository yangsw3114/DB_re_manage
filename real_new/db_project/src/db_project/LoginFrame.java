package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	DB_Conn_Query check = new DB_Conn_Query();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(67, 51, 50, 15);
		
		textField = new JTextField();
		textField.setBounds(67, 76, 191, 21);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setBounds(67, 107, 50, 15);
		
		textField_1 = new JTextField();
		textField_1.setBounds(69, 132, 191, 21);
		textField_1.setColumns(10);
		
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setBounds(67, 180, 93, 23);
		btnNewButton.addActionListener(new ActionListener() { //로그인 버튼 
			public void actionPerformed(ActionEvent e) {
				if(check.login_check(textField.getText(), textField_1.getText())){
					JOptionPane.showMessageDialog(null,"로그인 완료");
					if(check.Employee_Guest == true) { //직원모드로 진입
						admin_menu AM = new admin_menu(check.buildingNum);
						AM.setVisible(true);
						dispose();
					}
					else {
						member_menu frame = new member_menu(textField.getText());
						frame.setVisible(true);
						dispose();
					}

				}
				else {
					JOptionPane.showMessageDialog(null,"로그인 실패");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.setBounds(167, 180, 93, 23);
		btnNewButton_1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { //회원가입버튼
				SignUpFrame a =new SignUpFrame();
				a.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblNewLabel_1);
		contentPane.add(textField);
		contentPane.add(lblNewLabel);
		contentPane.add(textField_1);
	}
}
