package db_project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpFrame extends JFrame {

	DB_Conn_Query check = new DB_Conn_Query();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private boolean overlabcheck = false; 

	/**
	 * Create the frame.
	 */
    private boolean JoinCheck() //회원가입 조건 함수
    {
        if (textField.getText() == "") //아이디 입력이 공백일 때
        {
        	JOptionPane.showMessageDialog(null,"아이디를 입력하세요");
            this.textField.grabFocus();
            return false;
        }
        else if (textField_1.getText() == "") // 비밀번호 입력이 공백일 때
        {
        	JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요");
            this.textField.grabFocus();
            return false;
        }
        else if (textField_3.getText() == "") //이름 입력이 공백일 때
        {
        	JOptionPane.showMessageDialog(null,"이름을 입력해주세요");
            this.textField_3.grabFocus();
            return false;
        }
        else if (textField_4.getText()== "") //학번 입력이 공백일 때
        {
        	JOptionPane.showMessageDialog(null,"학번을 입력해주세요");
            this.textField_4.grabFocus();
            return false;
        }
        else if(textField_2.getText() != textField_1.getText()) //비밀번호와 비밀번호 확인이 일치하지않을 때
        {
        	JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
            this.textField_2.grabFocus();
            return false;
        }
        else //어느 텍스트상자도 공백이 아닐 때
        {
            if (textField_1.getText().length() < 4) //입력한 비밀번호의 길이가 4보다 작을 때
            {
            	JOptionPane.showMessageDialog(null,"비밀번호는 4자리 이상 해주세요");
                this.textField_1.grabFocus();
            }
            return true; //모든 조건을 만족할 때 true를 반환
        }
    }
    
	public SignUpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		
		JLabel lblNewLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC \uD655\uC778");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uC774\uB984");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uD559\uBC88");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() { //회원가입 중복 확인버튼 이벤트
			public void actionPerformed(ActionEvent e) {
				
				if(check.id_check(textField.getText())== true) {
					
		        	JOptionPane.showMessageDialog(null,"가능 ");
		        	overlabcheck = true;
				}
				else {
		        	JOptionPane.showMessageDialog(null,"중복입니다.");
		            textField.grabFocus();
		            overlabcheck = false; 
				}
				
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("\uAC00\uC785\uD558\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() { // 가입하기 버튼 
			public void actionPerformed(ActionEvent e) {
	            if(JoinCheck() == true) //회원조건 충족시
	            {
	            	
	                if (check.id_check(textField.getText()) == true) //중복된 아이디가 없을시
	                {
	                    if (overlabcheck == true)
	                    	
	                    {
	    		        	JOptionPane.showMessageDialog(null,"정상적으로 회원가입이 완료되었습니다. ");
	    		        	check.end_db();
	                    }
	                    else
	                    {
	    		        	JOptionPane.showMessageDialog(null,"정상적으로 회원가입이 되지 않았습니다. ");
	                    }
	                }
	                else
	                {
    		        	JOptionPane.showMessageDialog(null,"아이디 중복여부를 확인해주세요.");
	                }
	                
	            }


			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, Alignment.LEADING)
						.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(textField_3, Alignment.LEADING)
						.addComponent(textField_4, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(99)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
	}

}
