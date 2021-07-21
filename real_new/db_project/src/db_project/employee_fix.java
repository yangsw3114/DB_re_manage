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
	private JTextField �̸�text;
	private JTextField ����text;
	private JTextField ��ȭ��ȣtext;
	private JTextField �ֹε�Ϲ�ȣtext;
	private JTextField ��åtext;

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
			JLabel �̸��� = new JLabel("\uC774             \uB984: ");
			contentPanel.add(�̸���);
		}
		{
			�̸�text = new JTextField();
			contentPanel.add(�̸�text);
			�̸�text.setColumns(10);
		}
		{
			JLabel ���̶� = new JLabel("\uB098              \uC774:");
			contentPanel.add(���̶�);
		}
		{
			����text = new JTextField();
			contentPanel.add(����text);
			����text.setColumns(10);
		}
		{
			JLabel ��ȭ��ȣ�� = new JLabel("\uC804  \uD654  \uBC88  \uD638: ");
			contentPanel.add(��ȭ��ȣ��);
		}
		{
			��ȭ��ȣtext = new JTextField();
			contentPanel.add(��ȭ��ȣtext);
			��ȭ��ȣtext.setColumns(10);
		}
		{
			JLabel �ֹε�Ϲ�ȣ�� = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638: ");
			contentPanel.add(�ֹε�Ϲ�ȣ��);
		}
		{
			�ֹε�Ϲ�ȣtext = new JTextField();
			contentPanel.add(�ֹε�Ϲ�ȣtext);
			�ֹε�Ϲ�ȣtext.setColumns(10);
		}
		{
			JLabel ��å�� = new JLabel("\uC9C1             \uCC45:  ");
			contentPanel.add(��å��);
		}
		{
			��åtext = new JTextField();
			contentPanel.add(��åtext);
			��åtext.setColumns(10);
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
		super(frame,"��� ���̾�α�", true); //��޴��̾�α����ؼ��� true ������ ��
		setBounds(100, 100, 244, 222);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel �̸��� = new JLabel("\uC774           \uB984: ");
			contentPanel.add(�̸���);
		}
		{
			�̸�text = new JTextField();
			contentPanel.add(�̸�text);
			�̸�text.setColumns(10);
			�̸�text.setText(name);
		}
		{
			JLabel ���̶� = new JLabel("\uB098            \uC774:");
			contentPanel.add(���̶�);
		}
		{
			����text = new JTextField();
			contentPanel.add(����text);
			����text.setColumns(10);
			����text.setText(age);
		}
		{
			JLabel ��ȭ��ȣ�� = new JLabel("\uC804  \uD654  \uBC88  \uD638:");
			contentPanel.add(��ȭ��ȣ��);
		}
		{
			��ȭ��ȣtext = new JTextField();
			contentPanel.add(��ȭ��ȣtext);
			��ȭ��ȣtext.setColumns(10);
			��ȭ��ȣtext.setText(tel);
		}
		{
			JLabel �ֹε�Ϲ�ȣ�� = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638:");
			contentPanel.add(�ֹε�Ϲ�ȣ��);
		}
		{
			�ֹε�Ϲ�ȣtext = new JTextField();
			contentPanel.add(�ֹε�Ϲ�ȣtext);
			�ֹε�Ϲ�ȣtext.setColumns(10);
			�ֹε�Ϲ�ȣtext.setText(RRnum);
		}
		{
			JLabel ��å�� = new JLabel("\uC9C1           \uCC45: ");
			contentPanel.add(��å��);
		}
		{
			��åtext = new JTextField();
			contentPanel.add(��åtext);
			��åtext.setColumns(10);
			��åtext.setText(position);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//���߿��� ��ưŬ���� �����ͺ��̽� ���� �����ϰ� employee_manage�� �ҷ��ö� �����ͺ��̽����� ���� �ε��ϴ°ɷ� �����ϱ�
						employee_manage EM = new employee_manage(
								�̸�text.getText(), ����text.getText(), ��ȭ��ȣtext.getText(), �ֹε�Ϲ�ȣtext.getText(), ��åtext.getText(), name, row); 
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
