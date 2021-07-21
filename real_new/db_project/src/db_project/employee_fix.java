package db_project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class employee_fix extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField 이름text;
	private JTextField 나이text;
	private JTextField 전화번호text;
	private JTextField 주민등록번호text;
	private JTextField 직책text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			employee_fix dialog = new employee_fix();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public employee_fix() {
		setBounds(100, 100, 244, 222);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel 이름라벨 = new JLabel("\uC774             \uB984: ");
			contentPanel.add(이름라벨);
		}
		{
			이름text = new JTextField();
			contentPanel.add(이름text);
			이름text.setColumns(10);
		}
		{
			JLabel 나이라벨 = new JLabel("\uB098              \uC774:");
			contentPanel.add(나이라벨);
		}
		{
			나이text = new JTextField();
			contentPanel.add(나이text);
			나이text.setColumns(10);
		}
		{
			JLabel 전화번호라벨 = new JLabel("\uC804  \uD654  \uBC88  \uD638: ");
			contentPanel.add(전화번호라벨);
		}
		{
			전화번호text = new JTextField();
			contentPanel.add(전화번호text);
			전화번호text.setColumns(10);
		}
		{
			JLabel 주민등록번호라벨 = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638: ");
			contentPanel.add(주민등록번호라벨);
		}
		{
			주민등록번호text = new JTextField();
			contentPanel.add(주민등록번호text);
			주민등록번호text.setColumns(10);
		}
		{
			JLabel 직책라벨 = new JLabel("\uC9C1             \uCC45:  ");
			contentPanel.add(직책라벨);
		}
		{
			직책text = new JTextField();
			contentPanel.add(직책text);
			직책text.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public employee_fix(JFrame frame, String name, String age, String tel, String RRnum, String position, int row) {
		super(frame,"모달 다이얼로그", true); //모달다이얼로그위해서는 true 넣으면 됨
		setBounds(100, 100, 244, 222);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel 이름라벨 = new JLabel("\uC774           \uB984: ");
			contentPanel.add(이름라벨);
		}
		{
			이름text = new JTextField();
			contentPanel.add(이름text);
			이름text.setColumns(10);
			이름text.setText(name);
		}
		{
			JLabel 나이라벨 = new JLabel("\uB098            \uC774:");
			contentPanel.add(나이라벨);
		}
		{
			나이text = new JTextField();
			contentPanel.add(나이text);
			나이text.setColumns(10);
			나이text.setText(age);
		}
		{
			JLabel 전화번호라벨 = new JLabel("\uC804  \uD654  \uBC88  \uD638:");
			contentPanel.add(전화번호라벨);
		}
		{
			전화번호text = new JTextField();
			contentPanel.add(전화번호text);
			전화번호text.setColumns(10);
			전화번호text.setText(tel);
		}
		{
			JLabel 주민등록번호라벨 = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638:");
			contentPanel.add(주민등록번호라벨);
		}
		{
			주민등록번호text = new JTextField();
			contentPanel.add(주민등록번호text);
			주민등록번호text.setColumns(10);
			주민등록번호text.setText(RRnum);
		}
		{
			JLabel 직책라벨 = new JLabel("\uC9C1           \uCC45: ");
			contentPanel.add(직책라벨);
		}
		{
			직책text = new JTextField();
			contentPanel.add(직책text);
			직책text.setColumns(10);
			직책text.setText(position);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//나중에는 버튼클릭시 데이터베이스 값을 수정하고 employee_manage를 불러올때 데이터베이스에서 값을 로드하는걸로 변경하기
						employee_manage EM = new employee_manage(
								이름text.getText(), 나이text.getText(), 전화번호text.getText(), 주민등록번호text.getText(), 직책text.getText(), name, row); 
						EM.dispose();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
}
