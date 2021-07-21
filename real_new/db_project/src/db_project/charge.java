package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class charge extends JFrame{
	private JTextField textField;
	DB_Conn_Query Weekly = new DB_Conn_Query();
	
	public charge(String id)
	{
		setBounds(10,10,400,300);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		//textField.setEditable(true);
		JLabel lblNewLabel = new JLabel("\uCDA9\uC804 \uAE08\uC561");
		String list[] = {"1000원", "5000원", "10000원", "50000원", "직접입력"};
		
		JComboBox comboBox = new JComboBox(list);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem().toString() == "직접입력")
				{
					textField.setEditable(true);
					textField.setText("");
				}
				else
				{
					textField.setEditable(false);
					textField.setText(comboBox.getSelectedItem().toString());
				}
			}
		});
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
	    		  Calendar time = Calendar.getInstance();
	    		  String data = format.format(time.getTime());
				Weekly.money_charge(id, Integer.parseInt(textField.getText().replace("원", "")), data);
				JOptionPane.showMessageDialog(null,"충전에 성공했습니다.");
				dispose();
				member_menu a = new member_menu(id);
				a.setVisible(true);
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(95)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, Alignment.LEADING, 0, 188, Short.MAX_VALUE)
								.addComponent(textField, Alignment.LEADING, 188, 188, 188)
								.addComponent(btnNewButton))
							.addGap(101))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(75)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		
	}
}
